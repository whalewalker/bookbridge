package com.bookbridge.data.enums;

import org.springframework.http.HttpStatus;

/**
 * Created by Demilade Oladugba on 5/11/2021
 **/

public enum ResponseCode {
    SUCCESSFUL(HttpStatus.OK.toString(), "Successful"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.toString(), "Bad request"),
    DUPLICATE_REQUEST(HttpStatus.CONFLICT.toString(), "Entity already exist"),
    NOT_FOUND(HttpStatus.NOT_FOUND.toString(), "Entity doesn't exist"),
    SYSTEM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Internal System Error, Please try again later."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.toString(), "Unauthorized");

    public final String code;
    public final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
