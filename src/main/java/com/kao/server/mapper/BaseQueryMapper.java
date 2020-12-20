package com.kao.server.mapper;

import com.kao.server.dto.*;
import com.kao.server.entity.College;

import java.util.List;

/**
 * @author 沈伟峰
 */
public interface BaseQueryMapper {

    List<EvaluationBase> queryEvaluation(Integer round) throws Exception;

    List<EvaluationBase> queryLatestEvaluation() throws Exception;

    List<NewsBase> queryLatestNews(Integer limit) throws Exception;

    List<College> queryCollege(List<String> cidList) throws Exception;

    List<TutorRoleBaseWithName> queryTutor(String cid) throws Exception;

    /**
     * 查询夏令营信息
     *
     * @return 按时间降序排列的夏令营信息列表
     * @throws Exception 数据库操作异常
     */
    List<SummerCampMessage> querySummerCamp() throws Exception;

    /**
     * 查询九月推免信息
     *
     * @return 按时间降序排列的九月推免信息列表
     * @throws Exception 数据库操作异常
     */
    List<ExemptionMessage> queryExemption() throws Exception;

    /**
     * 根据学校代码查找录取率
     *
     * @param cid 学校代码
     * @return 近几年的录取率(时间降序)
     * @throws Exception 数据库操作异常
     */
    List<AcceptanceRateMessage> queryAcceptanceRate(String cid) throws Exception;

    /**
     * 查询学校最新排名结果
     *
     * @param cid 学校代码
     * @return 各个排行榜最新的排名结果
     * @throws Exception 数据库操作异常
     */
    List<LatestCollegeRank> queryLatestCollegeRank(String cid) throws Exception;
}
