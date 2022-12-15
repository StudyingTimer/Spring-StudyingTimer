package com.studyTimer.programming.studyTimer.repository;

import com.studyTimer.programming.studyTimer.entity.Subject;
import com.studyTimer.programming.studyTimer.entityIds.SubjectId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, SubjectId> {

    Optional<Subject> findByIdAndTitle(Long id, String title);

    List<Subject> findSubjectsByIdEquals(Long id);

    void deleteByIdAndTitle(Long id, String title);

}
