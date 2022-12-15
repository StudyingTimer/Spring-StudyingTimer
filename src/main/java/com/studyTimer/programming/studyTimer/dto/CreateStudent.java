package com.studyTimer.programming.studyTimer.dto;

import com.studyTimer.programming.studyTimer.entity.Student;
import lombok.*;

import javax.validation.constraints.NotNull;

public class CreateStudent {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {

        @NotNull
        private String token;

        @NotNull
        private String nickname;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {

        private Long id;

        private int grade;

        private int classNumber;

        private int studentNumber;

        private String name;

        private String nickname;

        private long studyHour;

        private long studyMinute;

        private long studySecond;

        private long time;

        public static Response fromEntity(Student student) {
            return Response.builder()
                    .id(student.getId())
                    .grade(student.getGrade())
                    .classNumber(student.getClassNumber())
                    .studentNumber(student.getStudentNumber())
                    .name(student.getName())
                    .nickname(student.getNickname())
                    .studyHour(0)
                    .studyMinute(0)
                    .studySecond(0)
                    .time(0)
                    .build();
        }

    }

}
