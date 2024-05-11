package com.bookbridge.util;

import com.bookbridge.data.enums.ResponseCode;
import com.bookbridge.data.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
public class Util {
    public static void logError(String message, Exception e) {
        log.error(message);
        log.error("Error information -> {} \n ", e.getMessage());
        e.printStackTrace();
    }

    public static <T> Response<T> successfulResponse(List<T> list) {
        Response<T> response = new Response<>();
        response.setResponseCode(ResponseCode.SUCCESSFUL.code);
        response.setResponseMessage(ResponseCode.SUCCESSFUL.message);
        response.setModelList(list);
        response.setCount(CollectionUtils.isEmpty(list) ? 0 : list.size());
        return response;
    }
}
