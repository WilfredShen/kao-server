package com.kao.server.mapper;

import com.kao.server.dto.NewsBase;

import java.util.List;

/**
 * @author 沈伟峰
 */
public interface AdminMapper {

    Integer uploadNews(NewsBase news, Integer adminId) throws Exception;

    List<NewsBase> queryNews() throws Exception;

    Integer updateNews(NewsBase news, Integer adminId) throws Exception;
}
