package com.chd9.manage.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chd9.manage.entity.RoleMenu;
import com.chd9.manage.entity.UserRole;
import org.springframework.stereotype.Repository;

/**
 * @Author: donnie99
 * @Date: 2020/4/30 23:28
 * @Version 1.0
 */
@Repository(value="roleMenuMapper")
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    Integer deleteByRoleId(Integer roleId);
}
