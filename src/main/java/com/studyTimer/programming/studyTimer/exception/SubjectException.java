package com.studyTimer.programming.studyTimer.exception;

import lombok.Getter;

@Getter
public class SubjectException extends RuntimeException {

    private String errorMessage;

    public SubjectException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

}
