package com.assessment.restcountrieswrapper.exception;

public class InternalServerError extends RuntimeException{

    public InternalServerError(String msg) {
        super(msg);
    }
}
