package com.studyTimer.programming.studyTimer.exception;

import lombok.Getter;

@Getter
public class TodoException extends RuntimeException {

    private String errorMessage;

    public TodoException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

}
