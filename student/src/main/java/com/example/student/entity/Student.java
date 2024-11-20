package com.example.student.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    @ManyToMany(mappedBy = "students",cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.DETACH})
    List<Course>courses=new ArrayList<>();

    public void addCourse(Course course){
        courses.add(course);
        course.getStudents().add(this);
    }
}
