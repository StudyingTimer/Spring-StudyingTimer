package com.studyTimer.programming.studyTimer.service;

import com.studyTimer.programming.studyTimer.dto.*;
import com.studyTimer.programming.studyTimer.entity.Student;
import com.studyTimer.programming.studyTimer.entity.Subject;
import com.studyTimer.programming.studyTimer.entity.Todo;
import com.studyTimer.programming.studyTimer.exception.StudentException;
import com.studyTimer.programming.studyTimer.exception.SubjectException;
import com.studyTimer.programming.studyTimer.exception.TodoException;
import com.studyTimer.programming.studyTimer.repository.StudentRepository;
import com.studyTimer.programming.studyTimer.repository.SubjectRepository;
import com.studyTimer.programming.studyTimer.repository.TodoRepository;
import leehj050211.bsmOauth.BsmOauth;
import leehj050211.bsmOauth.dto.response.BsmResourceResponse;
import leehj050211.bsmOauth.exceptions.BsmAuthCodeNotFoundException;
import leehj050211.bsmOauth.exceptions.BsmAuthInvalidClientException;
import leehj050211.bsmOauth.exceptions.BsmAuthTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudyTimerService {

    private final StudentRepository studentRepository;

    private final SubjectRepository subjectRepository;

    private final TodoRepository todoRepository;

    private final BsmOauth bsmOauth;


    @Transactional
    public CreateStudent.Response sighUp(CreateStudent.Request request) {
        validateStudent(request);

        Student student;

        try {
            BsmResourceResponse resource = bsmOauth.getResource(request.getToken());

            student = Student.builder()
                    .id(resource.getUserCode())
                    .grade(resource.getStudent().getGrade())
                    .classNumber(resource.getStudent().getClassNo())
                    .studentNumber(resource.getStudent().getStudentNo())
                    .name(resource.getStudent().getName())
                    .nickname(request.getNickname())
                    .build();

            studentRepository.save(student);
        } catch (IOException e) {
            student = new Student();
        } catch (BsmAuthTokenNotFoundException e) {
            student = new Student();
        } catch (BsmAuthInvalidClientException e) {
            student = new Student();
        }

        return CreateStudent.Response.fromEntity(student);
    }

    @Transactional
    public CheckStudent oauth(String code) {
        CheckStudent checkStudent;

        try {
            String token = bsmOauth.getToken(code);
            BsmResourceResponse resource = bsmOauth.getResource(token);
            Student extraStudent = Student.builder()
                    .id(resource.getUserCode())
                    .grade(resource.getStudent().getGrade())
                    .classNumber(resource.getStudent().getClassNo())
                    .studentNumber(resource.getStudent().getStudentNo())
                    .name(resource.getStudent().getName())
                    .build();
            checkStudent = studentRepository.findById(resource.getUserCode())
                    .map(student -> CheckStudent.fromEntity(student, token, true))
                    .orElse(CheckStudent.fromEntity(extraStudent, token, false));
        } catch (IOException e) {
            checkStudent = CheckStudent.builder()
                    .isStudent(false)
                    .build();
        } catch (BsmAuthCodeNotFoundException e) {
            checkStudent = CheckStudent.builder()
                    .isStudent(false)
                    .build();
        } catch (BsmAuthInvalidClientException e) {
            checkStudent = CheckStudent.builder()
                    .isStudent(false)
                    .build();
        } catch (BsmAuthTokenNotFoundException e) {
            checkStudent = CheckStudent.builder()
                    .isStudent(false)
                    .build();
        }

        return checkStudent;
    }

    @Transactional
    public Student getStudent(String token) {
        Student student;

        try {
            BsmResourceResponse resource = bsmOauth.getResource(token);
            student = studentRepository.findById(resource.getUserCode())
                    .orElseThrow(() -> new StudentException("NO_STUDENT"));
        } catch (IOException e) {
            student = new Student();
        } catch (BsmAuthTokenNotFoundException e) {
            student = new Student();
        } catch (BsmAuthInvalidClientException e) {
            student = new Student();
        }

        return student;
    }

    @Transactional
    public RankingListAndLength getAllRanking() {
        return RankingListAndLength.fromEntity(studentRepository.findAll(Sort.by(Sort.Direction.DESC, "time")), studentRepository.findAll().size());
    }

    @Transactional
    public int getRanking(String token) {
        List<Student> students = studentRepository.findAll(Sort.by(Sort.Direction.DESC, "time"));
        int ranking = 0;

        try {
            BsmResourceResponse resource = bsmOauth.getResource(token);
            for(Student student : students) {
                ranking++;
                if(student.getId() == resource.getUserCode()) break;
            }
        } catch (IOException e) {
            throw new StudentException("ERROR");
        } catch (BsmAuthTokenNotFoundException e) {
            throw new StudentException("NO STUDENT");
        } catch (BsmAuthInvalidClientException e) {
            throw new StudentException("ERROR");
        }

        return ranking;
    }

    @Transactional
    public void insertSubject(CDSubject cdSubject) {
        validateSubject(cdSubject);

        try {
            BsmResourceResponse resource = bsmOauth.getResource(cdSubject.getToken());
            Subject subject = Subject.builder()
                    .id(resource.getUserCode())
                    .title(cdSubject.getTitle())
                    .studyHour(0)
                    .studyMinute(0)
                    .studySecond(0)
                    .build();
            subjectRepository.save(subject);

        } catch (IOException e) {
            throw new SubjectException("ERROR");
        } catch (BsmAuthTokenNotFoundException e) {
            throw new StudentException("NO_STUDENT");
        } catch (BsmAuthInvalidClientException e) {
            throw new SubjectException("ERROR");
        }
    }

    public SubjectListAndLength getAllSubjects(String token) {
        try {
            BsmResourceResponse resource = bsmOauth.getResource(token);
            return SubjectListAndLength.fromEntity(subjectRepository.findSubjectsByIdEquals(resource.getUserCode()), subjectRepository.findSubjectsByIdEquals(resource.getUserCode()).size());
        } catch (IOException e) {
            throw new SubjectException("ERROR");
        } catch (BsmAuthTokenNotFoundException e) {
            throw new SubjectException("NO_STUDENT");
        } catch (BsmAuthInvalidClientException e) {
            throw new SubjectException("ERROR");
        }
    }

    @Transactional
    public void updateSubjectTitle(UpdateSubjectTitle oldSubject) {
        try {
            BsmResourceResponse resource = bsmOauth.getResource(oldSubject.getToken());
            Subject newSubject = subjectRepository.findByIdAndTitle(resource.getUserCode(), oldSubject.getOldTitle())
                    .orElseThrow(() -> new SubjectException("NO_SUBJECT"));
            newSubject.setTitle(oldSubject.getNewTitle());
        } catch (IOException e) {
            throw new SubjectException("ERROR");
        } catch (BsmAuthTokenNotFoundException e) {
            throw new SubjectException("NO_STUDENT");
        } catch (BsmAuthInvalidClientException e) {
            throw new SubjectException("ERROR");
        }
    }

    @Transactional
    public void updateSubjectTime(UpdateSubjectTime oldSubject) {
        int studyHour;
        int studyMinute;
        int studySecond;
        try {
            BsmResourceResponse resource = bsmOauth.getResource(oldSubject.getToken());
            Subject subject = subjectRepository.findByIdAndTitle(resource.getUserCode(), oldSubject.getTitle())
                    .orElseThrow(() -> new SubjectException("NO_SUBJECT"));
            studyHour = oldSubject.getStudyHour() - subject.getStudyHour();
            studyMinute = oldSubject.getStudyMinute() - subject.getStudyMinute();
            studySecond = oldSubject.getStudySecond() - subject.getStudySecond();
            subject.setStudyHour(oldSubject.getStudyHour());
            subject.setStudyMinute(oldSubject.getStudyMinute());
            subject.setStudySecond(oldSubject.getStudySecond());

            Student student = studentRepository.findById(resource.getUserCode())
                    .orElseThrow(() -> new StudentException("NO_STUDENT"));
            long time = (studyHour + student.getStudyHour()) * 60 * 60 + (studyMinute + student.getStudyMinute()) * 60 + (studySecond + student.getStudySecond());
            student.setStudyHour(time / 60 / 60);
            student.setStudyMinute(time / 60 % 60);
            student.setStudySecond(time % 60);
            student.setTime(time);
        } catch (IOException e) {
            throw new SubjectException("ERROR");
        } catch (BsmAuthTokenNotFoundException e) {
            throw new StudentException("NO_STUDENT");
        } catch (BsmAuthInvalidClientException e) {
            throw new SubjectException("ERROR");
        }
    }

    @Transactional
    public void deleteSubject(CDSubject cdSubject) {
        Student student;
        Subject subject;
        try {
            BsmResourceResponse resource = bsmOauth.getResource(cdSubject.getToken());

            student = studentRepository.findById(resource.getUserCode())
                    .orElseThrow(() -> new StudentException("NO_STUDENT"));
            subject = subjectRepository.findByIdAndTitle(resource.getUserCode(), cdSubject.getTitle())
                    .orElseThrow(() -> new SubjectException("NO_SUBJECT"));
            long time = student.getTime() - (subject.getStudyHour() * 60 * 60 + subject.getStudyMinute() * 60 + subject.getStudySecond());
            student.setStudyHour(time / 60 / 60);
            student.setStudyMinute(time / 60 % 60);
            student.setStudySecond(time % 60);
            student.setTime(time);
            subjectRepository.deleteByIdAndTitle(resource.getUserCode(), cdSubject.getTitle());
        } catch (IOException e) {
            throw new SubjectException("ERROR");
        } catch (BsmAuthTokenNotFoundException e) {
            throw new SubjectException("NO_STUDENT");
        } catch (BsmAuthInvalidClientException e) {
            throw new SubjectException("ERROR");
        }
    }

    @Transactional
    public void insertTodo(CDTodo cdTodo) {
        validateTodo(cdTodo);

        try {
            BsmResourceResponse resource = bsmOauth.getResource(cdTodo.getToken());
            Todo todo = Todo.builder()
                    .id(resource.getUserCode())
                    .content(cdTodo.getContent())
                    .build();
            todoRepository.save(todo);
        } catch (IOException e) {
            throw new TodoException("ERROR");
        } catch (BsmAuthTokenNotFoundException e) {
            throw new TodoException("NO_STUDENT");
        } catch (BsmAuthInvalidClientException e) {
            throw new TodoException("ERROR");
        }
    }

    public TodoListAndLength getAllTodo(String token) {
        try {
            BsmResourceResponse resource = bsmOauth.getResource(token);
            return TodoListAndLength.fromEntity(todoRepository.findTodosByIdEquals(resource.getUserCode()), todoRepository.findTodosByIdEquals(resource.getUserCode()).size());
        } catch (IOException e) {
            throw new TodoException("ERROR");
        } catch (BsmAuthTokenNotFoundException e) {
            throw new TodoException("NO_STUDENT");
        } catch (BsmAuthInvalidClientException e) {
            throw new TodoException("ERROR");
        }
    }

    @Transactional
    public void updateTodo(UpdateTodo updateTodo) {
        try {
            BsmResourceResponse resource = bsmOauth.getResource(updateTodo.getToken());
            Todo newTodo = todoRepository.findByIdAndContent(resource.getUserCode(), updateTodo.getOldContent())
                    .orElseThrow(() -> new TodoException("NO_TODO"));
            newTodo.setContent(updateTodo.getNewContent());
        } catch (IOException e) {
            throw new TodoException("ERROR");
        } catch (BsmAuthTokenNotFoundException e) {
            throw new TodoException("NO_STUDENT");
        } catch (BsmAuthInvalidClientException e) {
            throw new TodoException("ERROR");
        }
    }

    @Transactional
    public void deleteTodo(CDTodo cdTodo) {
        try {
            BsmResourceResponse resource = bsmOauth.getResource(cdTodo.getToken());
            todoRepository.deleteByIdAndContent(resource.getUserCode(), cdTodo.getContent());
        } catch (IOException e) {
            throw new TodoException("ERROR");
        } catch (BsmAuthTokenNotFoundException e) {
            throw new TodoException("NO_STUDENT");
        } catch (BsmAuthInvalidClientException e) {
            throw new TodoException("ERROR");
        }
    }

    private void validateStudent(CreateStudent.Request request) {
        try {
            BsmResourceResponse resource = bsmOauth.getResource(request.getToken());
            studentRepository.findById(resource.getUserCode()).ifPresent((
                    student -> {throw new StudentException("DUPLICATED_STUDENT");}
                    ));
        } catch (IOException e) {
            throw new StudentException("ERROR");
        } catch (BsmAuthTokenNotFoundException e) {
            throw new StudentException("NO_STUDENT");
        } catch (BsmAuthInvalidClientException e) {
            throw new StudentException("ERROR");
        }
    }

    private void validateSubject(CDSubject cdSubject) {
        try {
            BsmResourceResponse resource = bsmOauth.getResource(cdSubject.getToken());
            subjectRepository.findByIdAndTitle(resource.getUserCode(), cdSubject.getTitle())
                    .ifPresent((subject -> {throw new SubjectException("DUPLICATED_SUBJECT");}));
        } catch (IOException e) {
            throw new SubjectException("ERROR");
        } catch (BsmAuthTokenNotFoundException e) {
            throw new SubjectException("NO_STUDENT");
        } catch (BsmAuthInvalidClientException e) {
            throw new SubjectException("ERROR");
        }
    }

    private void validateTodo(CDTodo cdTodo) {
        try {
            BsmResourceResponse resource = bsmOauth.getResource(cdTodo.getToken());
            todoRepository.findByIdAndContent(resource.getUserCode(), cdTodo.getContent())
                    .ifPresent((todo -> {throw new TodoException("DUPLICATED_TODO");}));
        } catch (IOException e) {
            throw new TodoException("ERROR");
        } catch (BsmAuthTokenNotFoundException e) {
            throw new TodoException("NO_STUDENT");
        } catch (BsmAuthInvalidClientException e) {
            throw new TodoException("ERROR");
        }
    }

}
