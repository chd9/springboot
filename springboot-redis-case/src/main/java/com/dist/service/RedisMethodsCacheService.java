package com.dist.service;

/**
 * @author donnie99
 * @description redis 缓存接口的方法返回的数据测试
 * @data 2020/04/27
 */
public interface RedisMethodsCacheService {
    String getData();

    String saveData(String parameter);
    String getData(String parameter);
    String putData(String parameter);
    String deleteData(String parameter);
    String deleteAllData(String parameter);
}
