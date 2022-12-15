package com.studyTimer.programming.studyTimer.dto;

import com.studyTimer.programming.studyTimer.entity.Student;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RankingListAndLength {

    private List<Student> list;

    private int length;

    public static RankingListAndLength fromEntity(List<Student> list, int length) {
        return RankingListAndLength.builder()
                .list(list)
                .length(length)
                .build();
    }

}
