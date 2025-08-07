package com.furkanbilgin.week3.student.service;

import com.furkanbilgin.week3.student.dto.CourseDTO;
import com.furkanbilgin.week3.student.dto.UserDTO;
import com.furkanbilgin.week3.student.model.User;

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
