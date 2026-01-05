package com.hasanur.learneinbisschengerman.course;

import com.hasanur.learneinbisschengerman.course.Dtos.CourseCreateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(CourseCreateDto courseCreateDto) {
        Course course = new Course();
        course.setTitle(courseCreateDto.title());
        course.setDescription(courseCreateDto.description());
        course.setLevel(courseCreateDto.level());
        return courseRepository.save(course);
    }

    public List<Course> getCourses(){
        return courseRepository.findAll();
    }
}
