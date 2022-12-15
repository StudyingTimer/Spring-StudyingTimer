package com.studyTimer.programming.studyTimer.dto;

import com.studyTimer.programming.studyTimer.entity.Todo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoListAndLength {

    private List<Todo> list;

    private int length;

    public static TodoListAndLength fromEntity(List<Todo> list, int length) {
        return TodoListAndLength.builder()
                .list(list)
                .length(length)
                .build();
    }

}
