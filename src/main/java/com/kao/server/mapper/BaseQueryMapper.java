package com.kao.server.mapper;

import com.kao.server.dto.EvaluationBase;
import com.kao.server.dto.NewsBase;

import java.util.List;

/**
 * @author 沈伟峰
 */
public interface BaseQueryMapper {

    List<EvaluationBase> queryEvaluation(Integer round) throws Exception;

    List<EvaluationBase> queryLatestEvaluation() throws Exception;

    List<NewsBase> queryLatestNews(Integer limit) throws Exception;
}
