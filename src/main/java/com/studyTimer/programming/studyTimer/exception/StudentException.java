package com.studyTimer.programming.studyTimer.exception;

import lombok.Getter;

@Getter
public class StudentException extends RuntimeException {

    private String errorMessage;

    public StudentException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

}
