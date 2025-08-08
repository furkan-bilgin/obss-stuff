package com.furkanbilgin.finalproject.movieportal.mapper;

import com.furkanbilgin.finalproject.movieportal.dto.CourseDTO;
import com.furkanbilgin.finalproject.movieportal.model.Course;
import com.furkanbilgin.finalproject.movieportal.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class CourseMapper {
    public static CourseDTO toDTO(Course course) {
        if (course == null) return null;
        return CourseDTO.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .studentIds(course.getStudents() != null ? course.getStudents().stream().map(User::getId).collect(Collectors.toList()) : null)
                .build();
    }

    public static Course toEntity(CourseDTO dto, List<User> students) {
        if (dto == null) return null;
        return Course.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .students(students)
                .build();
    }
}

