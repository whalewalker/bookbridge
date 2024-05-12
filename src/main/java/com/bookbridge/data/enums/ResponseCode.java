package com.bookbridge.data.enums;

import org.springframework.http.HttpStatus;

/**
 * Created by Demilade Oladugba on 5/11/2021
 **/

public enum ResponseCode {
    SUCCESSFUL(HttpStatus.OK, "Successful"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad request"),
    DUPLICATE_REQUEST(HttpStatus.CONFLICT, "Entity already exist"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "Entity doesn't exist"),
    SYSTEM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal System Error, Please try again later."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Unauthorized");

    public final HttpStatus code;
    public final String message;

    ResponseCode(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }
}
