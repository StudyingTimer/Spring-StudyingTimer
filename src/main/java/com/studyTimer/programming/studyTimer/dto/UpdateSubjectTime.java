package com.studyTimer.programming.studyTimer.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateSubjectTime {

    @NotNull
    private String token;

    @NotNull
    private String title;

    @NotNull
    private int studyHour;

    @NotNull
    private int studyMinute;

    @NotNull
    private int studySecond;

}
