package com.studyTimer.programming.studyTimer.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateTodo {

    @NotNull
    private String token;

    @NotNull
    private String oldContent;

    @NotNull
    private String newContent;

}
