package com.kao.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.dto.UserMessage;
import com.kao.server.entity.User;
import com.kao.server.mapper.UserMapper;
import com.kao.server.service.UserService;
import com.kao.server.util.intercepter.AccountTypeConstant;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User findUserByUserId(int userId) {
        return userMapper.findUserByUserId(userId);
    }

    @Override
    public Integer updatePhone(int uid, String phoneNumber) {
        return userMapper.updatePhone(uid, phoneNumber);
    }

    @Override
    public Integer updateEmail(int uid, String email) {
        return userMapper.updateEmail(uid, email);
    }

    @Override
    public Integer updateAccountType(int uid, String accountType) {
        return userMapper.updateAccountType(uid, accountType);
    }

    @Override
    public UserMessage getNotVerifiedUserMessageById(int uid) {
        return userMapper.getNotVerifiedUserMessageById(uid);
    }

    @Override
    public UserMessage getStudentUserMessageById(int uid) {
        return userMapper.getStudentUserMessageById(uid);
    }


    public JsonResult getUserMessage(HttpServletRequest request) {
        int uid;
        try {
            uid = Integer.parseInt(request.getParameter("uid"));
            User user = this.findUserByUserId(uid);
            UserMessage userMessage = null;
            if (user.getAccountType()==null) {
                userMessage = userMapper.getNotVerifiedUserMessageById(uid);
            } else if (user.getAccountType().equals(AccountTypeConstant.getStudentType())) {
                userMessage = userMapper.getStudentUserMessageById(uid);
            }else if (user.getAccountType().equals(AccountTypeConstant.getTeacherType())){
                userMessage = userMapper.getTutorUserMessageById(uid);
            }
            if (userMessage != null) {
                return ResultFactory.buildSuccessJsonResult(null, userMessage);
            } else {
                return ResultFactory.buildFailJsonResult(400, "结果为空");
            }
        } catch (Exception e) {
            return ResultFactory.buildFailJsonResult(400, "异常");
        }

    }

    public JsonResult updateUserMsg(@RequestBody JSONObject userMsg) {

        String phoneNumber = userMsg.getString("phoneNumber");
        String email = userMsg.getString("email");
        String accountType = userMsg.getString("accountType");
        String uid = userMsg.getString("uid");
        JsonResult result = ResultFactory.buildJsonResult(null, null, null);
        if (phoneNumber != null) {
            if (this.updatePhone(Integer.parseInt(uid), phoneNumber) == 1) {
                result.setStatus(JsonResultStatus.SUCCESS);
                result.setMessage("修改成功!");
            } else {
                result.setStatus(JsonResultStatus.UNKOWN_ERROR);
                result.setMessage("修改失败!未知错误");
                return result;
            }

        } else {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.PHONENUMBER_ISNULL, "手机号不能为空");
        }
        if (email != null) {
            if (this.updateEmail(Integer.parseInt(uid), email) == 1) {
                result.setStatus(JsonResultStatus.SUCCESS);
                result.setMessage("修改成功!");
            } else {
                result.setStatus(JsonResultStatus.UNKOWN_ERROR);
                result.setMessage("修改失败!未知错误");
                return result;
            }

        }
        if (accountType != null) {
            if (this.updateAccountType(Integer.parseInt(uid), accountType) == 1) {
                result.setStatus(JsonResultStatus.SUCCESS);
                result.setMessage("修改成功!");
            } else {
                result.setStatus(JsonResultStatus.UNKOWN_ERROR);
                result.setMessage("修改失败!未知错误");
                return result;
            }
        }
        User user = this.findUserByUserId(Integer.parseInt(uid));

        UserMessage userMessage = null;
        if (user.getAccountType() == null) {
            userMessage = userMapper.getNotVerifiedUserMessageById(Integer.parseInt(uid));
        } else if ("学生".equals(user.getAccountType())) {
            userMessage = userMapper.getStudentUserMessageById(Integer.parseInt(uid));
        }
        if (userMessage != null) {
            return ResultFactory.buildSuccessJsonResult(null, userMessage);
        } else {
            return ResultFactory.buildFailJsonResult(400, "结果为空");
        }

    }
}
