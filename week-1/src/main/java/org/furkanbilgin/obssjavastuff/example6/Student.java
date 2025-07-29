package org.furkanbilgin.obssjavastuff.example6;

import java.io.Serializable;

public class Student implements Serializable {
    private final String studentId;
    private final String name;
    private final String surname;
    private final Course course;

    public Student(String studentId, String name, String surname, Course course) {
        this.studentId = studentId;
        this.name = name;
        this.surname = surname;
        this.course = course;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Course getCourse() {
        return course;
    }

}
