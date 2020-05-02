package com.chd9.manage.controller;

import com.chd9.manage.entity.Role;
import com.chd9.manage.entity.User;
import com.chd9.manage.entity.UserRole;
import com.chd9.manage.mapper.RoleMapper;
import com.chd9.manage.mapper.UserMapper;
import com.chd9.manage.mapper.UserRoleMapper;
import com.chd9.manage.utils.CommonUtil;
import com.chd9.manage.utils.Response;
import com.chd9.manage.utils.TableSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

/**
 * @Author: donnie99
 * @Date: 2020/4/30 23:28
 * @Version 1.0
 */
@Controller
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;

    @GetMapping("/alterPwd")
    public String alterPwd( ModelMap map, HttpSession session){
        User user = (User)session.getAttribute("admin_user");
        String username ="";
        if(user != null){
            username =user.getUsername();
        }
        map.addAttribute("username",username);
        return "user/alter_pwd";
    }
    @PostMapping("/doAlterPwd")
    @ResponseBody
    public Object doAlterPwd(String username,String password, String confirmPassword, String newPassword, HttpSession session) throws NoSuchAlgorithmException {

        User user = (User)session.getAttribute("admin_user");

        if(user == null){
            return Response.error("请先登录");
        }

        if(!confirmPassword.equals(newPassword)){
            return Response.error("两次输入密码不一致");
        }

        if(newPassword.length() < 5){
            return Response.error("密码长度至少6位");
        }

        String encryPwd = CommonUtil.encryPwd(password,user.getSalt());
        if(!encryPwd.equals(user.getPassword())){
            return Response.error("旧密码错误");
        }

        User updateUser = new User();
        String newEncryPwd = CommonUtil.encryPwd(newPassword,user.getSalt());
        updateUser.setId(user.getId());
        updateUser.setPassword(newEncryPwd);
        int num = userMapper.updateById(updateUser);
        if(num > 0){
            user.setPassword(newEncryPwd);
            session.setAttribute("admin_user",user);
            return Response.success();
        }else{
            return Response.error();
        }
    }

    @GetMapping("/index")
    public String index(){
        return "user/index";
    }


    @PostMapping("/doUpdate")
    @ResponseBody
    public Object doUpdate(@Valid User user, String confirmPassword) throws NoSuchAlgorithmException {
        Integer id = user.getId();
        int num;
        Date nowDate = new Date();
        user.setUpdateDate(nowDate);
        if(id != null && id > 0){
            num = userMapper.updateById(user);
        }else{
            if(!confirmPassword.equals(user.getPassword())){
                return Response.error("两次输入密码不一致");
            }
            String encryPwd = CommonUtil.encryPwd(user.getPassword(),user.getSalt());
            user.setPassword(encryPwd);
            user.setCreateDate(nowDate);
            num = userMapper.insert(user);
        }
        if(num > 0){
            return Response.successAndJump("/User/index");
        }else{
            return Response.error("操作失败");
        }

    }

    @PostMapping("/changeStatus")
    @ResponseBody
    public Object changeStatus(User user){
        Integer id = user.getId();
        int num = 0;
        if(id != null && id > 0){
            user.setUpdateDate(new Date());
            num = userMapper.updateById(user);
        }
        if(num > 0){
            return Response.success();
        }else{
            return Response.error();
        }

    }

    @GetMapping(value = {"/add"})
    public String add(ModelMap map){
//        map.addAttribute("cateList",cateMapper.selectList(null));
        return "user/add";
    }
    @GetMapping(value = "/edit")
    public String edit(Integer id, ModelMap map){
        if(null != id && id > 0){
            map.addAttribute("vo",userMapper.selectById(id));
        }
//        map.addAttribute("cateList",cateMapper.selectList(null));
        return "user/edit";
    }

    @GetMapping("/help")
    public String help(){
        return "user/help";
    }

    @GetMapping("/lists")
    @ResponseBody
    public Object lists(Integer page, Integer limit){
        TableSet<User> datasets = new TableSet<>(page,limit);
        List<User> list = userMapper.selectUserPage(datasets);
//        TableSet<CronTask> datasets1 = new TableSet<>(page,limit);
//        List<CronTask> list1 = cronTaskMapper.selectTaskPage(datasets1);
        return datasets.setRecords(list).response();
    }

    @GetMapping("doDel")
    @ResponseBody
    public Object doDel(Integer id){
        int num = 0;
        if(null != id && id > 0){
            num = userMapper.deleteById(id);
        }
        if(num > 0){
            return Response.success();
        }else{
            return Response.error();
        }
    }

    @GetMapping("/roleEdit")
    public Object roleEdit(String id, ModelMap map){
        List<Role> allList = roleMapper.selectRoles();
        map.addAttribute("allVos",allList);
        map.addAttribute("userId",id);
        return "user/roleEdit";
    }
    @PostMapping("/roleList")
    @ResponseBody
    public Object roleList(String id, ModelMap map){
        List<Role> list = roleMapper.selectRolesByUser(id);
        return Response.dataset(list);
    }
    @PostMapping("/doRoleEdit")
    @ResponseBody
    public Object doRoleEdit(Integer userId,String roleId){
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRoleMapper.deleteByUserId(userId);
        if (!StringUtils.isEmpty(roleId)){
            String[] ids = roleId.split(",");
            for(String id:ids){
                userRole.setRoleId(Integer.parseInt(id));
                userRoleMapper.insert(userRole);
            }
        }
        return Response.success();
    }
}
