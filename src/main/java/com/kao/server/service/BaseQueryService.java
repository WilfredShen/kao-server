package com.kao.server.service;

import com.kao.server.dto.EvaluationBase;
import com.kao.server.dto.NewsBase;
import com.kao.server.dto.TutorRoleBaseWithName;
import com.kao.server.entity.College;

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
     *
     * @param limit 限定获取的数量，0或负数表示全部
     * @return 新闻
     */
    List<NewsBase> queryLatestNews(Integer limit);

    /**
     * 获取指定学校的详情
     *
     * @param cidList 学校代码
     * @return 学校详情
     */
    List<College> queryCollege(List<String> cidList);

    /**
     * 获得指定学校的所有导师
     *
     * @param cid 学校代码
     * @return 导师
     */
    List<TutorRoleBaseWithName> queryTutor(String cid);
}
