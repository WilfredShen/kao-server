package com.kao.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.entity.User;
import com.kao.server.mapper.UserMapper;
import com.kao.server.service.UserService;
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
        return userMapper.updatePhone(uid,phoneNumber);
    }

    @Override
    public Integer updateEmail(int uid, String email) {
        return userMapper.updateEmail(uid,email);
    }

    @Override
    public Integer updateAccountType(int uid, String accountType) {
        return userMapper.updateAccountType(uid,accountType);
    }


    public JsonResult getUserMessage(HttpServletRequest request) {
        int uid;
        try {
            uid = Integer.parseInt(request.getParameter("uid"));
            User user = this.findUserByUserId(uid);
            if (user != null) {
                return ResultFactory.buildSuccessJsonResult(null, user);
            } else {
                return ResultFactory.buildFailJsonResult(400, "未知错误,请稍后再试");
            }
        } catch (Exception e) {
            return ResultFactory.buildFailJsonResult(400, "未知错误,请稍后再试");
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
        //这里data应该返回修改后的用户信息
        return ResultFactory.buildSuccessJsonResult("修改成功!", null);

    }
}
