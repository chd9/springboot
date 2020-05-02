package com.chd9.manage.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.chd9.manage.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: donnie99
 * @Date: 2020/4/30 23:28
 * @Version 1.0
 */
@Repository(value="userMapper")
public interface UserMapper extends BaseMapper<User> {
    public List<User> selectUserPage(Page<User> page);
}
