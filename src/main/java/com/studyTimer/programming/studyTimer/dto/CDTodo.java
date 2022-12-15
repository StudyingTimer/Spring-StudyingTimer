package com.studyTimer.programming.studyTimer.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CDTodo {

    @NotNull
    private String token;

    @NotNull
    private String content;

}
