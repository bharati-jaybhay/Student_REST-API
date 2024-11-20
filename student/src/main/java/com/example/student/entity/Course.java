package com.example.student.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;

    private String courseName;

    private String duration;

    private String fees;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    List<Student>students=new ArrayList<>();

    public void addStudent(Student student){
        students.add(student);
        student.getCourses().add(this);
    }

}
