package com.kao.server.service;

import com.kao.server.dto.EvaluationBase;
import com.kao.server.dto.NewsBase;

import java.util.List;

/**
 * @author 沈伟峰
 */
public interface BaseQueryService {

    /**
     * 获取指定轮次的所有评估结果
     *
     * @param round 轮次
     * @return 评估结果
     */
    List<EvaluationBase> queryEvaluation(Integer round);

    /**
     * 获取最新轮次的所有评估结果
     *
     * @return 评估结果
     */
    List<EvaluationBase> queryLatestEvaluation();

    /**
     * 获取最新的新闻（根据日期降序排序）
     * @param limit 限定获取的数量，0或负数表示全部
     * @return 新闻
     */
    List<NewsBase> queryLatestNews(Integer limit);
}
