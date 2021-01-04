package com.kao.server.service.impl;

import com.kao.server.dto.*;
import com.kao.server.entity.Admin;
import com.kao.server.entity.User;
import com.kao.server.mapper.AdminMapper;
import com.kao.server.mapper.BaseQueryMapper;
import com.kao.server.mapper.UserMapper;
import com.kao.server.service.AdminService;
import com.kao.server.util.accounttype.AccountTypeConstant;
import com.kao.server.util.checker.LoginChecker;
import com.kao.server.util.properties.RedisPrefixProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 全鸿润、沈伟峰
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@PropertySource(value = {"classpath:application.yml"})
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${redis.key.expired.commandExpireTime}")
    private Long expireTime;

    @Autowired
    private BaseQueryMapper baseQueryMapper;

    @Autowired
    private UserMapper userMapper;

    private final String prefix = "admin";


    @Override
    public Admin findAdminByUsername(String username) {
        Admin admin;
        try {
            String key = prefix + username;
            Boolean flag = redisTemplate.hasKey(key);
            if (flag != null && flag) {
                admin = (Admin) redisTemplate.opsForValue().get(key);
            } else {
                admin = adminMapper.findAdminByUsername(username);
                redisTemplate.opsForValue().set(key, admin);
                redisTemplate.expire(key, expireTime, TimeUnit.MINUTES);
            }
            System.err.println("findAdminByUsername" + username);
            return admin;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer handleLogin(String username, String password) {

        String key = prefix + username;
        try {
            Admin admin = null;
            Boolean flag = redisTemplate.hasKey(key);
            if (flag != null && flag) {
                admin = (Admin) redisTemplate.opsForValue().get(key);
            } else {
                admin = adminMapper.findAdminByUsername(username);
                if (admin != null) {
                    redisTemplate.opsForValue().set(key, admin);
                    redisTemplate.expire(key, expireTime, TimeUnit.MINUTES);
                }
            }
            return LoginChecker.checkLogin(admin, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
            Integer row = adminMapper.updateEvaluationResult(
                    cid,
                    mid,
                    round,
                    adminId,
                    result
            );
            if (row != null && row == 1) {
                redisTemplate.opsForValue().set(RedisPrefixProperties.EVALUATIONS + round, baseQueryMapper.queryEvaluation(round));
                redisTemplate.opsForValue().set(RedisPrefixProperties.LATEST_EVALUATION, baseQueryMapper.queryLatestEvaluation());
                redisTemplate.expire(RedisPrefixProperties.EVALUATIONS + round, 30, TimeUnit.MINUTES);
                redisTemplate.expire(RedisPrefixProperties.LATEST_EVALUATION + round, 30, TimeUnit.MINUTES);
            }
            return row;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer uploadEvaluationResult(EvaluationBase result, int adminId) {
        try {
            Integer row = adminMapper.uploadEvaluationResult(result, adminId);
            if (row != null) {
                redisTemplate.opsForValue().set(RedisPrefixProperties.EVALUATIONS + result.getRound(), baseQueryMapper.queryEvaluation(result.getRound()));
                redisTemplate.opsForValue().set(RedisPrefixProperties.LATEST_EVALUATION, baseQueryMapper.queryLatestEvaluation());
                redisTemplate.expire(RedisPrefixProperties.EVALUATIONS + result.getRound(), 30, TimeUnit.MINUTES);
                redisTemplate.expire(RedisPrefixProperties.LATEST_EVALUATION, expireTime, TimeUnit.MINUTES);
            }
            return row;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer uploadNews(NewsBase news, Integer adminId) {
        Integer count = null;
        try {
            count = adminMapper.uploadNews(news, adminId);
            if (count != null && count == 1) {
                redisTemplate.opsForValue().set(RedisPrefixProperties.LATEST_NEWS, baseQueryMapper.queryLatestNews(6));
                redisTemplate.opsForValue().set(RedisPrefixProperties.NEWS, adminMapper.queryNews());
                redisTemplate.expire(RedisPrefixProperties.LATEST_NEWS, expireTime, TimeUnit.MINUTES);
                redisTemplate.expire(RedisPrefixProperties.NEWS, expireTime, TimeUnit.MINUTES);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<NewsBase> queryNews() {
        List<NewsBase> data = null;
        try {
            Boolean flag = redisTemplate.hasKey(RedisPrefixProperties.NEWS);
            if (flag != null && flag) {
                data = (List<NewsBase>) redisTemplate.opsForValue().get(RedisPrefixProperties.NEWS);
            } else {
                data = adminMapper.queryNews();
                redisTemplate.opsForValue().set(RedisPrefixProperties.NEWS, data);
                redisTemplate.expire(RedisPrefixProperties.NEWS, expireTime, TimeUnit.MINUTES);
            }
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
            System.err.println("修改新闻条数： " + count);
            if (count != null) {
                redisTemplate.opsForValue().set(RedisPrefixProperties.LATEST_NEWS, baseQueryMapper.queryLatestNews(6));
                redisTemplate.opsForValue().set(RedisPrefixProperties.NEWS, adminMapper.queryNews());
                redisTemplate.expire(RedisPrefixProperties.LATEST_NEWS, expireTime, TimeUnit.MINUTES);
                redisTemplate.expire(RedisPrefixProperties.NEWS, expireTime, TimeUnit.MINUTES);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<UserMessageByAdmin> getUserMessageList() {
        try {
            return adminMapper.getUserMessageList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer deleteUser(Integer uid) {
        try {
            System.err.println("deleteUser: " + uid);
            User user = userMapper.findUserByUserId(uid);
            Integer row = adminMapper.deleteUser(uid);
            if (row != null && row == 1) {
                redisTemplate.delete(user.getUsername());
                redisTemplate.delete(String.valueOf(uid));
            }
            return row;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer updateUser(UpdatedUserMessage message) {
        try {
            System.err.println("updateUser: " + message.getUsername());
            Integer uid = message.getUid();
            //先保存旧用户信息
            User user = userMapper.findUserByUserId(uid);
            Integer row = adminMapper.updateUser(message);
            if (row != null && row == 1 && user != null) {

                redisTemplate.delete(user.getUsername());
                //更新findUserById
                redisTemplate.opsForValue().set("findUserByUserId" + uid, user);
                redisTemplate.expire("findUserByUserId" + uid, expireTime, TimeUnit.MINUTES);
                //更新findUserByName
                redisTemplate.opsForValue().set(message.getUsername(), user);
                redisTemplate.expire(message.getUsername(), expireTime, TimeUnit.MINUTES);
                //根据用户类型更新缓存
                if (AccountTypeConstant.getStudentType().equals(user.getAccountType())) {
                    redisTemplate.opsForValue().set(String.valueOf(uid), userMapper.getStudentUserMessageById(uid));
                } else if (AccountTypeConstant.getTeacherType().equals(user.getAccountType())) {
                    redisTemplate.opsForValue().set(String.valueOf(uid), userMapper.getTutorUserMessageById(uid));
                } else if (user.getAccountType() == null) {
                    redisTemplate.opsForValue().set(String.valueOf(uid), userMapper.getNotVerifiedUserMessageById(uid));
                }
                redisTemplate.expire(String.valueOf(uid), expireTime, TimeUnit.MINUTES);
            }
            return row;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
