package com.bookbridge.data.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {
    private HttpStatus responseCode;
    private String responseMessage;
    private List<Error> errors;
    private List<T> modelList;
    private long count;
}
