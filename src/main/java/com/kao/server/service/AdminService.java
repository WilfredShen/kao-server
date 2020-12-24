package com.kao.server.service;

import com.kao.server.dto.*;
import com.kao.server.entity.Admin;

import java.util.List;

/**
 * @author 全鸿润、沈伟峰
 */
public interface AdminService {

    /**
     * 通过用户名查找管理员
     *
     * @param username 用户名
     * @return Admin 管理员对象
     */
    Admin findUserByUsername(String username);

    /**
     * 处理登录逻辑
     *
     * @param admin    通过cookie里面的adminId查找到的管理员对象
     * @param username 用户名
     * @param password 密码
     * @return 处理状态码
     */
    Integer handleLogin(String username, String password);

    /**
     * 管理员界面的根据轮次获取评估结果
     *
     * @param result  新的评估结果
     * @param adminId 管理员id
     * @return 评估结果列表
     */
    Integer uploadEvaluationResult(EvaluationBase result, int adminId);

    /**
     * 管理员通过轮次查询评估结果
     *
     * @param round   评估轮次
     * @param major   专业名称
     * @param college 学校名称
     * @return 评估结果列表
     */
    List<AdminViewEvaluation> findEvaluationByRound(int round, String major, String college);

    /**
     * 修改评估结果(单行)
     *
     * @param cid     学校代码
     * @param mid     专业代码
     * @param round   评估轮次
     * @param adminId 管理员id
     * @param result  修改后的评估结果
     * @return 修改行数
     */
    Integer updateEvaluationResult(String cid, String mid, int round, int adminId, String result);

    /**
     * 上传新闻
     *
     * @param news    新闻内容
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
     * @param news    更新后的内容
     * @param adminId 管理员编号
     * @return 更新结果
     */
    Integer updateNews(NewsBase news, Integer adminId);

    /**
     * 获取用户信息列表
     *
     * @return 用户信息列表
     */
    List<UserMessageByAdmin> getUserMessageList();


    /**
     * 删除用户
     *
     * @param uid 用户id
     * @return 删除行数
     */
    Integer deleteUser(Integer uid);

    /**
     * 管理员修改用户信息
     *
     * @param message 修改的用户信息
     * @return 修改的行数
     */
    Integer updateUser(UpdatedUserMessage message);

}
