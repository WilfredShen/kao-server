package com.kao.server.service;

import com.kao.server.dto.AdminViewEvaluation;
import com.kao.server.dto.EvaluationBase;
import com.kao.server.entity.Admin;
import com.kao.server.util.json.JsonResult;

import java.util.List;

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

}
