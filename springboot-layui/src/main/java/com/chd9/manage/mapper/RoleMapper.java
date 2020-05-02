package com.chd9.manage.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.chd9.manage.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: donnie99
 * @Date: 2020/4/30 23:28
 * @Version 1.0
 */
@Repository(value="roleMapper")
public interface RoleMapper extends BaseMapper<Role> {
    public List<Role> selectRolePage(Page<Role> page);
    public List<Role> selectRoles();
    public List<Role> selectRolesByUser(String id);
}
