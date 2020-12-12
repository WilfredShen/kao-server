package com.kao.server.mapper;

import com.kao.server.dto.AdminViewEvaluation;
import com.kao.server.dto.EvaluationBase;
import com.kao.server.dto.NewsBase;
import com.kao.server.entity.Admin;

import java.util.List;

/**
 * @author 全鸿润、沈伟峰
 */
public interface AdminMapper {

    /**
     * 通过用户名查找管理员
     *
     * @param username 用户名
     * @return Admin
     */
    Admin findUserByUsername(String username) throws Exception;

    /**
     * 通过轮次查询评估结果
     *
     * @param round 轮次
     * @return 评估结果列表
     */
    List<AdminViewEvaluation> findEvaluationByRound(int round) throws Exception;

    /**
     * 管理员界面的根据轮次获取评估结果
     *
     * @param result  更新的评估结果
     * @param adminId 管理员Id
     * @return 评估结果列表
     */
    Integer uploadEvaluationResult(EvaluationBase result, int adminId) throws Exception;

    /**
     * 更新单条评估结果
     *
     * @param cid     学校代码
     * @param mid     专业代码
     * @param round   轮次
     * @param adminId 管理员id
     * @param result  修改的结果
     * @return 修改行数
     */
    Integer updateEvaluationResult(String cid, String mid, int round, int adminId, String result) throws Exception;

    Integer uploadNews(NewsBase news, Integer adminId) throws Exception;

    List<NewsBase> queryNews() throws Exception;

    Integer updateNews(NewsBase news, Integer adminId) throws Exception;

}
