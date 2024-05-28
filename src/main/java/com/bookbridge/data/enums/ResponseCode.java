package com.bookbridge.data.enums;

import org.springframework.http.HttpStatus;

/**
 * Created by Demilade Oladugba on 5/11/2021
 **/

public enum ResponseCode {
    SUCCESSFUL(HttpStatus.OK.value(), "Successful"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "Bad request"),
    DUPLICATE_REQUEST(HttpStatus.CONFLICT.value(), "Entity already exist"),
    NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Entity doesn't exist"),
    SYSTEM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal System Error, Please try again later."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "Unauthorized"),
    FORBIDDEN(HttpStatus.FORBIDDEN.value(), "Forbidden");

    public final int code;
    public final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
