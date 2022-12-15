package com.studyTimer.programming.studyTimer.controller;

import com.studyTimer.programming.studyTimer.dto.*;
import com.studyTimer.programming.studyTimer.entity.Student;
import com.studyTimer.programming.studyTimer.service.StudyTimerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StudyTimerController {

    private final StudyTimerService studyTimerService;

    @GetMapping("/")
    public String emptyURL() {
        return "Welcome To StudyTimer!";
    }

    @PostMapping("/student/signUp")
    public CreateStudent.Response signUp(
            @Valid @RequestBody CreateStudent.Request request
    ) {
        log.info("Post /student/signUp");

        return studyTimerService.sighUp(request);
    }

    @GetMapping("/student/oauth/{code}")
    public CheckStudent oauth(
            @PathVariable String code
    ) {
        log.info("Get /student/oauth/{code}");

        return studyTimerService.oauth(code);
    }

    @GetMapping("/student/{token}")
    public Student getStudent(
            @PathVariable String token
    ) {
        return studyTimerService.getStudent(token);
    }

    @GetMapping("/ranking/all")
    public RankingListAndLength getAllRanking() {
        return studyTimerService.getAllRanking();
    }

    @GetMapping("/ranking/{token}")
    public int getRanking(
           @PathVariable String token
    ) {
        return studyTimerService.getRanking(token);
    }

    @PostMapping("/subject/insert")
    public void insertSubject(
            @Valid @RequestBody CDSubject cdSubject
    ) {
        log.info("Post /subject/insert");

        studyTimerService.insertSubject(cdSubject);
    }

    @GetMapping("/subjects/{token}")
    public SubjectListAndLength getAllSubjects(
            @PathVariable String token
    ) {
        log.info("Get /subjects/{token}");

        return studyTimerService.getAllSubjects(token);
    }

    @PutMapping("/subject/title/update")
    public void updateSubjectTitle(
            @Valid @RequestBody UpdateSubjectTitle oldSubject
    ) {
        log.info("Put /subject/title/update");

        studyTimerService.updateSubjectTitle(oldSubject);
    }

    @PutMapping("/subject/time/update")
    public void updateSubjectTime(
            @Valid @RequestBody UpdateSubjectTime oldSubject
    ) {
        log.info("Put /subject/time/update");

        studyTimerService.updateSubjectTime(oldSubject);
    }

    @DeleteMapping("/subject/delete")
    public void deleteSubject(
            @Valid @RequestBody CDSubject cdSubject
    ) {
        log.info("Delete /subject/delete");

        studyTimerService.deleteSubject(cdSubject);
    }

    @PostMapping("/todo/insert")
    public void insertTodo(
            @Valid @RequestBody CDTodo cdTodo
    ) {
        log.info("Post /todo/insert");

        studyTimerService.insertTodo(cdTodo);
    }

    @GetMapping("/todos/{token}")
    public TodoListAndLength getAllTodo(
            @PathVariable String token
    ) {
        log.info("Get /todos/{token}");

        return studyTimerService.getAllTodo(token);
    }

    @PutMapping("/todo/update")
    public void updateTodo(
            @Valid @RequestBody UpdateTodo updateTodo
    ) {
        log.info("Put /todo/update");

        studyTimerService.updateTodo(updateTodo);
    }

    @DeleteMapping("/todo/delete")
    public void deleteTodo(
            @Valid @RequestBody CDTodo cdTodo
    ) {
        log.info("Delete /todo/delete");

        studyTimerService.deleteTodo(cdTodo);
    }

}
