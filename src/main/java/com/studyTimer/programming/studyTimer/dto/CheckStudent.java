package com.studyTimer.programming.studyTimer.dto;

import com.studyTimer.programming.studyTimer.entity.Student;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckStudent {

    private Long id;

    private String token;

    private int grade;

    private int classNumber;

    private int studentNumber;

    private String name;

    private String nickname;

    private boolean isStudent;


    public static CheckStudent fromEntity(Student student, String token, boolean isStudent) {
        return CheckStudent.builder()
                .id(student.getId())
                .token(token)
                .grade(student.getGrade())
                .classNumber(student.getClassNumber())
                .studentNumber(student.getStudentNumber())
                .name(student.getName())
                .nickname(student.getNickname())
                .token(token)
                .isStudent(isStudent)
                .build();
    }

}
