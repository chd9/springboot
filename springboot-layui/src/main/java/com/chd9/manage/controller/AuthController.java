package com.chd9.manage.controller;

import com.chd9.manage.entity.Setting;
import com.chd9.manage.entity.User;
import com.chd9.manage.mapper.SettingMapper;
import com.chd9.manage.mapper.UserMapper;
import com.chd9.manage.utils.CommonUtil;
import com.chd9.manage.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

/**
 * @Author: donnie99
 * @Date: 2020/4/30 23:28
 * @Version 1.0
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SettingMapper settingMapper;

    @GetMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("admin_user");
        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(HttpSession session){
        User user = (User)session.getAttribute("admin_user");
        if(user == null){
            return "auth/login";
        }else{
            return "redirect:/";
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public Object login(@Valid User user, BindingResult bindResult, HttpSession session, ModelMap map) throws NoSuchAlgorithmException {

        if(bindResult.hasErrors()){
            return Response.error(bindResult.getFieldError().getDefaultMessage());
        }
        if(user.getUsername() == "" || user.getPassword() == ""){
            return Response.error("用户或密码有误");
        }

        String password = user.getPassword();
        user.setPassword(null);

        User existUser = userMapper.selectOne(user);
        if(null == existUser){
            return Response.error("用户或密码有误");
        }else{
            String encryPwd = CommonUtil.encryPwd(password,existUser.getSalt());
            if(!existUser.getPassword().equals(encryPwd)){
                return Response.error("用户或密码有误");
            }
            Calendar c = Calendar.getInstance();
            long now = c.getTimeInMillis();
            c.setTime(existUser.getUpdateDate());
            long last = c.getTimeInMillis();
            Setting s =new Setting();
            s.setName("password_safe_times");
            Setting setting = settingMapper.selectOne(s);
            long safe = Long.parseLong(setting.getValue());
            String passFlag = (String) session.getAttribute("passFlag");
            if (now-last>=((safe-7)*24*60*60*1000)&&now-last<(safe*24*60*60*1000)&&passFlag==null){
                session.setAttribute("passFlag","1");
                return Response.error("密码快到期了，请自行修改密码！");
            }
            if (now-last>=(safe*24*60*60*1000)){
                return Response.error("密码已过期，请联系管理员修改密码！");
            }
            session.setAttribute("admin_user",existUser);
            return Response.successAndJump("/");
        }
    }
}
