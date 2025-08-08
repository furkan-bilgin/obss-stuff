package com.furkanbilgin.finalproject.movieportal.service.impl;

import com.furkanbilgin.finalproject.movieportal.dto.CourseDTO;
import com.furkanbilgin.finalproject.movieportal.dto.UserDTO;
import com.furkanbilgin.finalproject.movieportal.mapper.CourseMapper;
import com.furkanbilgin.finalproject.movieportal.mapper.UserMapper;
import com.furkanbilgin.finalproject.movieportal.model.Course;
import com.furkanbilgin.finalproject.movieportal.model.User;
import com.furkanbilgin.finalproject.movieportal.repository.CourseRepository;
import com.furkanbilgin.finalproject.movieportal.repository.UserRepository;
import com.furkanbilgin.finalproject.movieportal.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public CourseDTO saveCourse(CourseDTO courseDTO) {
        List<User> students =
                courseDTO.getStudentIds() != null
                        ? userRepository.findAllById(courseDTO.getStudentIds())
                        : List.of();
        Course course = CourseMapper.toEntity(courseDTO, students);
        return CourseMapper.toDTO(courseRepository.save(course));
    }

    public List<CourseDTO> findAllCourses() {
        return courseRepository.findAll().stream()
                .map(CourseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO findCourseById(Long id) {
        return courseRepository.findById(id).map(CourseMapper::toDTO).orElse(null);
    }

    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        var courseOptional = courseRepository.findById(id);
        if (courseOptional.isEmpty()) {
            return null;
        }
        Course course = courseOptional.get();
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        List<User> students =
                courseDTO.getStudentIds() != null
                        ? userRepository.findAllById(courseDTO.getStudentIds())
                        : List.of();
        course.setStudents(students);
        return CourseMapper.toDTO(courseRepository.save(course));
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    private Pair<Course, User> getCourseAndStudent(Long courseId, String studentUsername) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        Optional<User> studentOptional = userRepository.findByUsername(studentUsername);
        if (courseOptional.isEmpty() || studentOptional.isEmpty()) {
            throw new IllegalArgumentException("Course or student not found");
        }
        return Pair.of(courseOptional.get(), studentOptional.get());
    }

    public void enrollStudentInCourse(Long courseId, String studentUsername) {
        var pair = getCourseAndStudent(courseId, studentUsername);
        var course = pair.getFirst();
        var student = pair.getSecond();
        if (course.getStudents().contains(student)) {
            return; // Student is already enrolled
        }

        course.getStudents().add(student);
        courseRepository.save(course);
    }

    public void unenrollStudentFromCourse(Long courseId, String studentUsername) {
        var pair = getCourseAndStudent(courseId, studentUsername);
        var course = pair.getFirst();
        var student = pair.getSecond();
        if (!course.getStudents().contains(student)) {
            return; // Student is not enrolled, can't unenroll
        }

        course.getStudents().remove(student);
        courseRepository.save(course);
    }

    public List<UserDTO> findStudentsByCourseId(Long courseId) {
        return courseRepository
                .findById(courseId)
                .map(Course::getStudents)
                .map(
                        students ->
                                students.stream()
                                        .map(UserMapper::toDTO)
                                        .collect(Collectors.toList()))
                .orElseThrow(
                        () ->
                                new IllegalArgumentException(
                                        "Course not found with id: " + courseId));
    }
}
