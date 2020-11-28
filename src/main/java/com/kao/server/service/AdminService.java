package com.kao.server.service;

import com.kao.server.dto.AdminViewEvaluation;
import com.kao.server.dto.EvaluationBase;
import com.kao.server.dto.NewsBase;
import com.kao.server.entity.Admin;
import com.kao.server.util.json.JsonResult;

import java.util.List;

/**
 * @author 全鸿润、沈伟峰
 */
public interface AdminService {

    /**
     * 通过用户名查找管理员
     *
     * @param username
     * @return Admin
     */
    public Admin findUserByUsername(String username);

    /**
     * 处理登录逻辑
     *
     * @param username
     * @param password
     * @return json格式的处理结果
     */
    public JsonResult handleLogin(String username, String password);

    /**
     * 管理员界面的根据轮次获取评估结果
     *
     * @param result
     * @return 评估结果列表
     */
    public Integer uploadEvaluationResult(List<EvaluationBase> result);

    /**
     * @param round
     * @return
     */
    public List<AdminViewEvaluation> findEvaluationByRound(int round);

    /**
     * 修改当行评估结果
     *
     * @param cid
     * @param mid
     * @param round
     * @param adminId
     * @param result
     * @return
     */
    public Integer updateEvaluationResult(String cid, String mid, int round, int adminId, String result);

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
