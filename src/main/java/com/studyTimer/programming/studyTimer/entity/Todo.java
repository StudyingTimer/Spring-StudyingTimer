package com.studyTimer.programming.studyTimer.entity;

import com.studyTimer.programming.studyTimer.entityIds.TodoId;
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
@IdClass(TodoId.class)
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Todo implements Persistable<Long> {

    @Id
    @CreatedDate
    private LocalDateTime createdAt;

    @Id
    private Long id;

    private String content;

    @Override
    public boolean isNew() {
        return true;
    }

}
