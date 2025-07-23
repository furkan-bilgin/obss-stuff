package org.furkanbilgin.obssjavastuff.example6;

import java.io.Serializable;

public class Course implements Serializable {
    private final String courseId;
    private final String courseName;

    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }
}
