package com.fastcamp.getinline.controller.error;

import com.fastcamp.getinline.constant.ErrorCode;
import com.fastcamp.getinline.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler
    public ModelAndView general(GeneralException e) {
        ErrorCode errorCode = e.getErrorCode();
        HttpStatus status = errorCode.getHttpStatus();

        return new ModelAndView(
                "error",
                Map.of(
                        "statusCode", status.value(),
                        "errorCode", errorCode.getCode(),
                        "message", errorCode.getMessage(e)
                ),
                status);
    }

    @ExceptionHandler
    public ModelAndView exception(Exception e) {
        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
        HttpStatus status = errorCode.getHttpStatus();

        return new ModelAndView(
                "error",
                Map.of(
                        "statusCode", status.value(),
                        "errorCode", errorCode.getCode(),
                        "message", errorCode.getMessage(e)
                ),
                status);
    }

}
