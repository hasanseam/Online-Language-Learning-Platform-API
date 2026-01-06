package com.hasanur.learneinbisschengerman.lesson.Dtos;

public record LessonResponseDto(
        Long id,
        String title,
        String contentText,
        String videoUrl,
        Integer orderNumber
) {
}
