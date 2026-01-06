package com.hasanur.learneinbisschengerman.lesson;

import com.hasanur.learneinbisschengerman.course.Course;
import com.hasanur.learneinbisschengerman.course.CourseService;
import com.hasanur.learneinbisschengerman.exceptions.ResourceNotFoundException;
import com.hasanur.learneinbisschengerman.lesson.Dtos.CreateLessonDto;
import com.hasanur.learneinbisschengerman.lesson.Dtos.LessonResponseDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LessonService {
    public final LessonRepository lessonRepository;
    private final CourseService courseService;

    public LessonService(LessonRepository lessonRepository, CourseService courseService) {
        this.lessonRepository = lessonRepository;
        this.courseService = courseService;
    }


    public Lesson createLesson(Long courseId, CreateLessonDto createLessonDto){

        Course course = courseService.getCourseOrThrow(courseId);

        Lesson lesson = new Lesson();
        lesson.setTitle(createLessonDto.title());
        lesson.setContentText(createLessonDto.contentText());
        lesson.setVideoUrl(createLessonDto.videoUrl());
        lesson.setOrderNumber(createLessonDto.orderNumber());

        lesson.setCourse(course);

        return this.lessonRepository.save(lesson);
    }

    public List<LessonResponseDto> getLessonsByCourse(Long courseId){
        // 1️⃣ Check if course exists
        if (!courseService.isCourseAvailable(courseId)) {
            throw new ResourceNotFoundException("Course is not found");
        }

        // 2️⃣ Fetch lessons
        return lessonRepository.findByCourseIdOrderByOrderNumber(courseId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public LessonResponseDto getLessonById(Long courseId, Long lessonId) {
        Lesson lesson = lessonRepository.findByIdAndCourseId(lessonId, courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found"));

        return mapToDto(lesson);
    }

    public LessonResponseDto updateLesson(Long courseId, Long lessonId, CreateLessonDto dto) {
        Lesson lesson = lessonRepository.findByIdAndCourseId(lessonId, courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found"));

        lesson.setTitle(dto.title());
        lesson.setContentText(dto.contentText());
        lesson.setVideoUrl(dto.videoUrl());
        lesson.setOrderNumber(dto.orderNumber());

        lessonRepository.save(lesson);
        return mapToDto(lesson);
    }

    public void deleteLesson(Long courseId, Long lessonId) {
        Lesson lesson = lessonRepository.findByIdAndCourseId(lessonId, courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found"));
        lessonRepository.delete(lesson);
    }

    public LessonResponseDto mapToDto(Lesson lesson) {
        return new LessonResponseDto(
                lesson.getId(),
                lesson.getTitle(),
                lesson.getContentText(),
                lesson.getVideoUrl(),
                lesson.getOrderNumber()
        );
    }

}
