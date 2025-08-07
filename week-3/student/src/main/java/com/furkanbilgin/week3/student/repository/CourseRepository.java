package com.furkanbilgin.week3.student.repository;

import com.furkanbilgin.week3.student.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}

