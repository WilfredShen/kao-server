package com.kao.server.service.impl;

import com.kao.server.dto.AdminViewEvaluation;
import com.kao.server.dto.EvaluationBase;
import com.kao.server.dto.NewsBase;
import com.kao.server.entity.Admin;
import com.kao.server.mapper.AdminMapper;
import com.kao.server.service.AdminService;
import com.kao.server.util.checker.LoginChecker;
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
        try {
            return adminMapper.findUserByUsername(username);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int handleLogin(Admin admin, String username, String password) {

        return LoginChecker.checkLogin(admin, username, password);
    }

    @Override
    public List<AdminViewEvaluation> findEvaluationByRound(int round, String major, String college) {
        try {
            return adminMapper.findEvaluationByRound(round, major, college);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer updateEvaluationResult(String cid, String mid, int round, int adminId, String result) {
        try {
            return adminMapper.updateEvaluationResult(
                    cid,
                    mid,
                    round,
                    adminId,
                    result
            );
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer uploadEvaluationResult(EvaluationBase result, int adminId) {
        try {
            return adminMapper.uploadEvaluationResult(result, adminId);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer uploadNews(NewsBase news, Integer adminId) {
        Integer count = null;
        try {
            count = adminMapper.uploadNews(news, adminId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

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

    @Override
    public Integer updateNews(NewsBase news, Integer adminId) {
        Integer count = 0;
        try {
            count = adminMapper.updateNews(news, adminId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
