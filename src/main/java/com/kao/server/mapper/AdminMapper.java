package com.kao.server.mapper;

import com.kao.server.dto.AdminViewEvaluation;
import com.kao.server.dto.EvaluationBase;
import com.kao.server.dto.NewsBase;
import com.kao.server.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @author 全鸿润、沈伟峰
 */
public interface AdminMapper {
    /**
     * 通过用户名查找管理员
     *
     * @param username
     * @return Admin
     */
    public Admin findUserByUsername(String username);

    /**
     * 通过轮次查询评估结果
     *
     * @param round
     * @return 评估结果列表
     */
    public List<AdminViewEvaluation> findEvaluationByRound(int round);

    /**
     * 管理员界面的根据轮次获取评估结果
     *
     * @param result
     * @return 评估结果列表
     */
    public Integer uploadEvaluationResult(List<EvaluationBase> result);

    /**
     * 更新单条评估结果
     *
     * @param cid
     * @param mid
     * @param round
     * @param adminId
     * @param result
     * @return 修改行数
     */
    public Integer updateEvaluationResult(String cid, String mid, int round, int adminId, String result);

    Integer uploadNews(NewsBase news, Integer adminId) throws Exception;

    List<NewsBase> queryNews() throws Exception;

    Integer updateNews(NewsBase news, Integer adminId) throws Exception;

}
