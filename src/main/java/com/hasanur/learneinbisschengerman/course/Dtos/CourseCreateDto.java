package com.hasanur.learneinbisschengerman.course.Dtos;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CourseCreateDto(

        @Size(max = 2000, message = "Description cannot exceed 2000 characters")
        String description,

        @NotEmpty(message = "Title must not be empty")
        @Size(min = 3, max = 255, message = "Title must be between 3 and 255 characters")
        String title,

        @NotEmpty(message = "Level must not be empty")
        @Pattern(regexp = "A1|A2|B1|B2|C1|C2", message = "Level must be one of: A1, A2, B1, B2, C1, C2")
        String level
)
{

}
