package com.studyTimer.programming.studyTimer.repository;

import com.studyTimer.programming.studyTimer.entity.Todo;
import com.studyTimer.programming.studyTimer.entityIds.TodoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, TodoId> {

    Optional<Todo> findByIdAndContent(Long id, String content);

    List<Todo> findTodosByIdEquals(Long id);

    void deleteByIdAndContent(Long id, String content);

}
