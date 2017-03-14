package com.example.Util;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Xin09 on 2017/2/14.
 */


public class JsonUtil {
    private static final String ERROR_KEY = "errorMsg";

    /** 成功Key */
    private static final String SUCCESS_KEY = "success";

    /** 错误编码Key */
    private static final String ERROR_CODE_KEY = "errorCode";

    public static String getSuccess() {
        return result(true, "", "");
    }
    public static String getFailure() {
        return result(false, "", "");
    }
    public static String result(boolean success, String errorMsg, String errorCode) {
        JSONObject result = new JSONObject();
        result.put(SUCCESS_KEY, success);
        result.put(ERROR_KEY, errorMsg);
        result.put(ERROR_CODE_KEY, errorCode);
        return result.toString();
    }
}
