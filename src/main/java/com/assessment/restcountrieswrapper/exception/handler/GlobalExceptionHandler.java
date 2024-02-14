package com.assessment.restcountrieswrapper.exception.handler;

import com.assessment.restcountrieswrapper.exception.ApiErrorResponse;
import com.assessment.restcountrieswrapper.exception.DataNotFoundException;
import com.assessment.restcountrieswrapper.exception.InternalServerError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleDataNotFoundException(DataNotFoundException exception) {
        int statusCode = HttpStatus.NOT_FOUND.value();
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .statusCode(statusCode)
                .message(exception.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorResponse);
    }

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<ApiErrorResponse> handleBadRequestException(InternalServerError exception) {
        int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .statusCode(statusCode)
                .message(exception.getMessage()).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiErrorResponse);
    }
}
