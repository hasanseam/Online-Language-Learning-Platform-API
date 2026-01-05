package com.hasanur.learneinbisschengerman.course;

import com.hasanur.learneinbisschengerman.course.Dtos.CourseCreateDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public Course createCourse(@RequestBody CourseCreateDto courseCreateDto) {
        return this.courseService.createCourse(courseCreateDto);
    }

    @GetMapping
    public List<Course> getAllCourse() {
        return courseService.getCourses();
    }
}
