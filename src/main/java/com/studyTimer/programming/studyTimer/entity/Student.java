package com.studyTimer.programming.studyTimer.entity;

import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student implements Persistable<Long> {

    @Id
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

    @Override
    public boolean isNew() {
        return time == 0;
    }

}
