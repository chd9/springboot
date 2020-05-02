package com.chd9.manage.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chd9.manage.entity.Setting;
import org.springframework.stereotype.Repository;
/**
 * @Author: donnie99
 * @Date: 2020/4/30 23:28
 * @Version 1.0
 */
@Repository(value="settingMapper")
public interface SettingMapper extends BaseMapper<Setting> {

    Integer updateValueById(Setting t);
}
