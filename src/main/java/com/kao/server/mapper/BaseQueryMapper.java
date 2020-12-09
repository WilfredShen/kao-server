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

    List<SummerCampMessage> querySummerCamp() throws Exception;

    List<ExemptionMessage> queryExemption() throws Exception;

    List<AcceptanceRateMessage> queryAcceptanceRate(String cid) throws Exception;

    List<LatestCollegeRank> queryLatestCollegeRank(String cid) throws Exception;
}
