package com.hasanur.learneinbisschengerman.lesson;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByCourseIdOrderByOrderNumber(Long courseId);
    Optional<Lesson> findByIdAndCourseId(Long lessonId, Long courseId);
}
