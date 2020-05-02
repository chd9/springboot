package com.chd9.manage.controller;

import com.chd9.manage.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * @Author: donnie99
 * @Date: 2020/4/30 23:28
 * @Version 1.0
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(HttpSession session, ModelMap map){
        User user = (User)session.getAttribute("admin_user");
        if(user == null){
            return "redirect:auth/login";
        }
        map.addAttribute("adminUserName",user.getUsername());
        return "index/index";
    }
    @GetMapping("/console")
    public String console(ModelMap map) throws Exception {
//        String[] cmds = {"sh","-c","ps aux | grep jtimer"};
//        Process process = Runtime.getRuntime().exec(cmds);
//        process.waitFor();
//        InputStream in = process.getInputStream();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//        String line = null;
//        StringBuilder sb = new StringBuilder();
//        while ((line = reader.readLine()) != null) {
//            sb.append(line + "<br>");
//        }
//        map.addAttribute("status","OK");
        return "index/console";
    }
}
