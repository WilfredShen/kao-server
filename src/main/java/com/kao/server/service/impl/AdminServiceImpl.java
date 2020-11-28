package com.kao.server.service.impl;

import com.kao.server.dto.AdminViewEvaluation;
import com.kao.server.dto.EvaluationBase;
import com.kao.server.dto.NewsBase;
import com.kao.server.entity.Admin;
import com.kao.server.mapper.AdminMapper;
import com.kao.server.service.AdminService;
import com.kao.server.util.checker.LoginChecker;
import com.kao.server.util.json.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 全鸿润、沈伟峰
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findUserByUsername(String username) {
        return adminMapper.findUserByUsername(username);
    }

    @Override
    public JsonResult handleLogin(String username, String password) {

        Admin admin = adminMapper.findUserByUsername(username);
        return LoginChecker.checkLogin(admin, username, password);
    }

    @Override
    public List<AdminViewEvaluation> findEvaluationByRound(int round) {
        return adminMapper.findEvaluationByRound(round);
    }

    @Override
    public Integer updateEvaluationResult(String cid, String mid, int round, int adminId, String result) {
        return adminMapper.updateEvaluationResult(
                cid,
                mid,
                round,
                adminId,
                result
        );
    }

    @Override
    public Integer uploadEvaluationResult(List<EvaluationBase> result) {
        return adminMapper.uploadEvaluationResult(result);
    }

    /**
     * 上传新闻
     *
     * @param news    新闻内容
     * @param adminId 管理员编号
     * @return 添加结果
     */
    @Override
    public Integer uploadNews(NewsBase news, Integer adminId) {
        int count = 0;
        try {
            count = adminMapper.uploadNews(news, adminId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 查询新闻
     *
     * @return 查询结果
     */
    @Override
    public List<NewsBase> queryNews() {
        List<NewsBase> data = null;
        try {
            data = adminMapper.queryNews();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 更新新闻
     *
     * @param news    更新后的内容
     * @param adminId 管理员编号
     * @return 更新结果
     */
    @Override
    public Integer updateNews(NewsBase news, Integer adminId) {
        int count = 0;
        try {
            count = adminMapper.updateNews(news, adminId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
