package com.kao.server.util.json;

/**
 * @author 沈伟峰
 */
public class ResultFactory {

    static public JsonResult buildJsonResult(Integer status, String message, Object data) {
        return new JsonResult(status, message, data);
    }

    static public JsonResult buildSuccessJsonResult(String message, Object data) {
        return ResultFactory.buildJsonResult(JsonResultStatus.SUCCESS, message, data);
    }

    static public JsonResult buildFailJsonResult(Integer status, String message) {
        return ResultFactory.buildJsonResult(status, message, null);
    }
}
