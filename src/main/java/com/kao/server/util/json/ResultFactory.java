package com.kao.server.util.json;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author 沈伟峰
 */
public class ResultFactory {

    static public JsonResult buildJsonResult(Integer status, String message, Object data) {
        return new JsonResult(status, message, data);
    }

    static public JsonResult buildSuccessJsonResult() {
        return ResultFactory.buildJsonResult(JsonResultStatus.SUCCESS, JsonResultStatus.SUCCESS_DESC, null);
    }

    static public JsonResult buildSuccessJsonResult(Object data) {
        return ResultFactory.buildJsonResult(JsonResultStatus.SUCCESS, JsonResultStatus.SUCCESS_DESC, data);
    }

    static public JsonResult buildSuccessJsonResult(String message, Object data) {
        return ResultFactory.buildJsonResult(JsonResultStatus.SUCCESS, message, data);
    }

    static public JsonResult buildFailJsonResult() {
        return ResultFactory.buildJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC, null);
    }

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

    static public JsonResult buildFailJsonResult(Integer status, String message) {
        return ResultFactory.buildJsonResult(status, message, null);
    }

    static public JsonResult buildWarnJsonResult(String key) {
        return buildFailJsonResult(key);
    }

    static public JsonResult buildWarnJsonResult(Integer status, String message) {
        return ResultFactory.buildJsonResult(status, message, null);
    }

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
