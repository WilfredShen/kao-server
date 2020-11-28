package com.kao.server.service;

import com.kao.server.dto.NewsBase;

import java.util.List;

/**
 * @author 沈伟峰
 */
public interface AdminService {

    /**
     * 上传新闻
     *
     * @param news 新闻内容
     * @param adminId 管理员编号
     * @return 添加结果
     */
    Integer uploadNews(NewsBase news, Integer adminId);

    /**
     * 查询新闻
     *
     * @return 查询结果
     */
    List<NewsBase> queryNews();

    /**
     * 更新新闻
     *
     * @param news 更新后的内容
     * @param adminId 管理员编号
     * @return 更新结果
     */
    Integer updateNews(NewsBase news, Integer adminId);
}
