package com.studyTimer.programming.studyTimer.dto;

import com.studyTimer.programming.studyTimer.entity.Subject;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectListAndLength {

    private List<Subject> list;

    private int length;

    public static SubjectListAndLength fromEntity(List<Subject> list, int length) {
        return SubjectListAndLength.builder()
                .list(list)
                .length(length)
                .build();
    }

}
