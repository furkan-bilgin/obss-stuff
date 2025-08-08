package com.furkanbilgin.finalproject.movieportal.service;

import com.furkanbilgin.finalproject.movieportal.dto.CourseDTO;
import com.furkanbilgin.finalproject.movieportal.dto.UserDTO;

import java.util.List;

public interface CourseService {
    CourseDTO saveCourse(CourseDTO courseDTO);

    List<CourseDTO> findAllCourses();

    CourseDTO findCourseById(Long id);

    CourseDTO updateCourse(Long id, CourseDTO courseDTO);

    void deleteCourse(Long id);

    void enrollStudentInCourse(Long courseId, String username);

    void unenrollStudentFromCourse(Long courseId, String username);

    List<UserDTO> findStudentsByCourseId(Long courseId);
}
