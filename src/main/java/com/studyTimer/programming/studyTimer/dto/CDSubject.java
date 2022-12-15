package com.studyTimer.programming.studyTimer.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CDSubject {

    @NotNull
    private String token;

    @NotNull
    private String title;

}
