package com.kao.server.service;

/**
 * @author 全鸿润
 */
public interface RedisService {

    /**
     * 存储数据
     *
     * @param key   键
     * @param value 值
     */
    void set(String key, String value);

    /**
     * 获取数据
     *
     * @param key 键
     * @return 值
     */
    String get(String key);

    /**
     * 设置超时时间
     *
     * @param key    键
     * @param expire 超时时间
     * @return 设置是否成功
     */
    Boolean expire(String key, long expire);

    /**
     * 删除数据
     *
     * @param key 键
     */
    void remove(String key);

    /**
     * 自增1操作
     *
     * @param key   键
     * @param delta 自增步长
     * @return 步长
     */
    Long increment(String key, long delta);
}
