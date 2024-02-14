package com.assessment.restcountrieswrapper.exception;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiErrorResponse {

    private int statusCode;
    private String message;
}
