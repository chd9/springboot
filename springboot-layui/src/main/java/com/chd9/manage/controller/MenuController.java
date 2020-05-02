package com.chd9.manage.controller;

import com.chd9.manage.entity.Menu;
import com.chd9.manage.mapper.MenuMapper;
import com.chd9.manage.utils.Response;
import com.chd9.manage.utils.TableSet;
import com.chd9.manage.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping("/Menu")
public class MenuController {

    @Autowired
    private MenuMapper menuMapper;

    @GetMapping("/index")
    public String index(){
        return "menu/index";
    }


    @PostMapping("/doUpdate")
    @ResponseBody
    public Object doUpdate(@Valid Menu menu) throws NoSuchAlgorithmException {
        Integer id = menu.getId();
        int num;
        Date nowDate = new Date();
        menu.setUpdateDate(nowDate);
        if(id != null && id > 0){
            num = menuMapper.updateById(menu);
        }else{
            menu.setCreateDate(nowDate);
            num = menuMapper.insert(menu);
        }
        if(num > 0){
            return Response.successAndJump("/Menu/index");
        }else{
            return Response.error("操作失败");
        }

    }

    @PostMapping("/changeStatus")
    @ResponseBody
    public Object changeStatus(Menu menu){
        Integer id = menu.getId();
        int num = 0;
        if(id != null && id > 0){
            menu.setUpdateDate(new Date());
            num = menuMapper.updateById(menu);
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
        return "menu/add";
    }
    @GetMapping(value = "/edit")
    public String edit(Integer id, ModelMap map){
        if(null != id && id > 0){
            map.addAttribute("vo",menuMapper.selectById(id));
        }
//        map.addAttribute("cateList",cateMapper.selectList(null));
        return "menu/edit";
    }

    @GetMapping("/help")
    public String help(){
        return "menu/help";
    }

    @GetMapping("/lists")
    @ResponseBody
    public Object lists(Integer page, Integer limit){
        TableSet<Role> datasets = new TableSet<>(page,limit);
        List<Role> list = menuMapper.selectMenuPage(datasets);
//        TableSet<CronTask> datasets1 = new TableSet<>(page,limit);
//        List<CronTask> list1 = cronTaskMapper.selectTaskPage(datasets1);
        return datasets.setRecords(list).response();
    }

    @GetMapping("doDel")
    @ResponseBody
    public Object doDel(Integer id){
        int num = 0;
        if(null != id && id > 0){
            num = menuMapper.deleteById(id);
        }
        if(num > 0){
            return Response.success();
        }else{
            return Response.error();
        }
    }
}
