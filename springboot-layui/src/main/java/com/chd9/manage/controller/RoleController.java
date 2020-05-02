package com.chd9.manage.controller;

import com.chd9.manage.entity.Menu;
import com.chd9.manage.entity.Role;
import com.chd9.manage.entity.RoleMenu;
import com.chd9.manage.entity.UserRole;
import com.chd9.manage.mapper.MenuMapper;
import com.chd9.manage.mapper.RoleMapper;
import com.chd9.manage.mapper.RoleMenuMapper;
import com.chd9.manage.utils.Response;
import com.chd9.manage.utils.TableSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
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
@RequestMapping("/Role")
public class RoleController {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private MenuMapper menuMapper;

    @GetMapping("/index")
    public String index(){
        return "role/index";
    }


    @PostMapping("/doUpdate")
    @ResponseBody
    public Object doUpdate(@Valid Role role) throws NoSuchAlgorithmException {
        Integer id = role.getId();
        int num;
        Date nowDate = new Date();
        role.setUpdateDate(nowDate);
        if(id != null && id > 0){
            num = roleMapper.updateById(role);
        }else{
            role.setCreateDate(nowDate);
            num = roleMapper.insert(role);
        }
        if(num > 0){
            return Response.successAndJump("/Role/index");
        }else{
            return Response.error("操作失败");
        }

    }

    @PostMapping("/changeStatus")
    @ResponseBody
    public Object changeStatus(Role role){
        Integer id = role.getId();
        int num = 0;
        if(id != null && id > 0){
            role.setUpdateDate(new Date());
            num = roleMapper.updateById(role);
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
        return "role/add";
    }
    @GetMapping(value = "/edit")
    public String edit(Integer id, ModelMap map){
        if(null != id && id > 0){
            map.addAttribute("vo",roleMapper.selectById(id));
        }
//        map.addAttribute("cateList",cateMapper.selectList(null));
        return "role/edit";
    }

    @GetMapping("/help")
    public String help(){
        return "role/help";
    }

    @GetMapping("/lists")
    @ResponseBody
    public Object lists(Integer page, Integer limit){
        TableSet<Role> datasets = new TableSet<>(page,limit);
        List<Role> list = roleMapper.selectRolePage(datasets);
//        TableSet<CronTask> datasets1 = new TableSet<>(page,limit);
//        List<CronTask> list1 = cronTaskMapper.selectTaskPage(datasets1);
        return datasets.setRecords(list).response();
    }

    @GetMapping("doDel")
    @ResponseBody
    public Object doDel(Integer id){
        int num = 0;
        if(null != id && id > 0){
            num = roleMapper.deleteById(id);
        }
        if(num > 0){
            return Response.success();
        }else{
            return Response.error();
        }
    }
    @GetMapping("/menuEdit")
    public Object menuEdit(String id, ModelMap map){
        List<Menu> allList = menuMapper.selectMenus();
        map.addAttribute("allVos",allList);
        map.addAttribute("roleId",id);
        return "role/menuEdit";
    }
    @PostMapping("/menuList")
    @ResponseBody
    public Object menuList(String id, ModelMap map){
        List<Menu> list = menuMapper.selectMenusByUser(id);
        return Response.dataset(list);
    }
    @PostMapping("/doMenuEdit")
    @ResponseBody
    public Object doMenuEdit(Integer roleId,String menuId){
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setRoleId(roleId);
        roleMenuMapper.deleteByRoleId(roleId);
        if(!StringUtils.isEmpty(menuId)){
            String[] ids = menuId.split(",");
            for(String id:ids){
                roleMenu.setMenuId(Integer.parseInt(id));
                roleMenuMapper.insert(roleMenu);
            }
        }
        return Response.success();
    }
}
