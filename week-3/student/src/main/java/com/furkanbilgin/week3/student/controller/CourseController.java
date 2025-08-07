package com.furkanbilgin.week3.student.controller;

import com.furkanbilgin.week3.student.dto.CourseDTO;
import com.furkanbilgin.week3.student.dto.UserDTO;
import com.furkanbilgin.week3.student.model.User;
import com.furkanbilgin.week3.student.repository.UserRepository;
import com.furkanbilgin.week3.student.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    private final UserRepository userRepository;

    @Autowired
    public CourseController(CourseService courseService, UserRepository userRepository) {
        this.courseService = courseService;
        this.userRepository = userRepository;
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('LECTURER')")
    @PostMapping("/create")
    public CourseDTO createCourse(@RequestBody CourseDTO courseDTO) {
        return courseService.saveCourse(courseDTO);
    }

    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return courseService.findAllCourses();
    }

    @GetMapping("/{id}")
    public CourseDTO getCourseById(@PathVariable Long id) {
        return courseService.findCourseById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('LECTURER')")
    @PutMapping("/{id}")
    public CourseDTO updateCourse(@PathVariable Long id, @RequestBody CourseDTO courseDTO) {
        return courseService.updateCourse(id, courseDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/{id}/enroll")
    public void enrollInCourse(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        courseService.enrollStudentInCourse(id, userDetails.getUsername());
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @DeleteMapping("/{id}/unenroll")
    public void unenrollFromCourse(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        courseService.unenrollStudentFromCourse(id, userDetails.getUsername());
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('LECTURER')")
    @GetMapping("/{id}/students")
    public List<UserDTO> findUsersInCourse(@PathVariable Long id) {
        return courseService.findStudentsByCourseId(id);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('LECTURER')")
    @PostMapping("/{courseId}/addUser/{userId}")
    public void addUserToCourse(@PathVariable Long courseId, @PathVariable Long userId) {
        courseService.enrollStudentInCourse(courseId, userRepository.findById(userId).get().getUsername());
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('LECTURER')")
    @DeleteMapping("/{courseId}/removeUser/{userId}")
    public void removeUserFromCourse(@PathVariable Long courseId, @PathVariable Long userId) {
        courseService.unenrollStudentFromCourse(courseId, userRepository.findById(userId).get().getUsername());
    }
}
