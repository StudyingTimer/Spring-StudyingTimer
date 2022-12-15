package com.studyTimer.programming.studyTimer.entity;

import com.studyTimer.programming.studyTimer.entityIds.SubjectId;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(SubjectId.class)
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Subject implements Persistable<Long> {

    @Id
    @CreatedDate
    private LocalDateTime createdAt;

    @Id
    private Long id;

    private String title;

    private int studyHour;

    private int studyMinute;

    private int studySecond;

    @Override
    public boolean isNew() {
        return studyHour == 0 && studyMinute == 0 && studySecond == 0;
    }

}
