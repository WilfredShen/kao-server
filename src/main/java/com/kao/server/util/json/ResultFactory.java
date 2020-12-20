package com.kao.server.util.json;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author 沈伟峰
 */
public class ResultFactory {

    /**
     * 创建一个请求结果
     *
     * @param status  请求状态
     * @param message 描述信息
     * @param data    返回的数据
     * @return 请求结果
     */
    static public JsonResult buildJsonResult(Integer status, String message, Object data) {
        return new JsonResult(status, message, data);
    }

    /**
     * 创建一个表示成功的请求结果，使用默认的描述信息，不携带数据
     *
     * @return 请求结果
     */
    static public JsonResult buildSuccessJsonResult() {
        return ResultFactory.buildJsonResult(JsonResultStatus.SUCCESS, JsonResultStatus.SUCCESS_DESC, null);
    }

    /**
     * 创建一个表示成功的请求结果，并携带数据
     *
     * @param data 返回的数据
     * @return 请求结果
     */
    static public JsonResult buildSuccessJsonResult(Object data) {
        return ResultFactory.buildJsonResult(JsonResultStatus.SUCCESS, JsonResultStatus.SUCCESS_DESC, data);
    }

    /**
     * 创建一个表示成功的请求结果，自定义描述信息，并携带数据
     *
     * @param data 返回的数据
     * @return 请求结果
     */
    static public JsonResult buildSuccessJsonResult(String message, Object data) {
        return ResultFactory.buildJsonResult(JsonResultStatus.SUCCESS, message, data);
    }

    /**
     * 创建一个表示失败的请求结果
     *
     * @return 请求结果
     */
    static public JsonResult buildFailJsonResult() {
        return ResultFactory.buildJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC, null);
    }

    /**
     * 创建一个表示失败的请求结果，使用 key 指定的请求状态和描述信息（使用反射机制实现）
     *
     * @param key 指定的请求状态和描述信息
     * @return 请求结果
     */
    static public JsonResult buildFailJsonResult(String key) {
        try {
            Field statusField = JsonResult.class.getDeclaredField(key);
            Field descField = JsonResult.class.getDeclaredField(key + "_DESC");
            int status = (int) statusField.get(null);
            String desc = (String) descField.get(null);
            return ResultFactory.buildJsonResult(status, desc, null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return null;
        }
    }

    /**
     * 创建一个表示失败的请求结果，自定义请求状态和描述信息
     *
     * @param status  请求状态
     * @param message 描述信息
     * @return 请求结果
     */
    static public JsonResult buildFailJsonResult(Integer status, String message) {
        return ResultFactory.buildJsonResult(status, message, null);
    }

    /**
     * 创建一个表示警告的请求结果，使用 key 指定的请求状态和描述信息（使用反射机制实现）
     *
     * @param key 指定的请求状态和描述信息
     * @return 请求结果
     */
    static public JsonResult buildWarnJsonResult(String key) {
        return buildFailJsonResult(key);
    }

    /**
     * 创建一个表示警告的请求结果，自定义请求状态和描述信息
     *
     * @param status  请求状态
     * @param message 描述信息
     * @return 请求结果
     */
    static public JsonResult buildWarnJsonResult(Integer status, String message) {
        return ResultFactory.buildJsonResult(status, message, null);
    }

    /**
     * 简单判断列表是否为 null、为空或携带数据，来设置相应的请求状态和描述信息，封装成请求结果
     *
     * @param data 返回的数据
     * @return 请求结果
     */
    public static <T> JsonResult listPack(List<T> data) {
        JsonResult jsonResult;
        if (data == null) {
            jsonResult = ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC);
        } else if (data.size() == 0) {
            jsonResult = ResultFactory.buildFailJsonResult(JsonResultStatus.NOT_FOUND, JsonResultStatus.NOT_FOUND_DESC);
        } else {
            jsonResult = ResultFactory.buildSuccessJsonResult(data);
        }
        return jsonResult;
    }
}
