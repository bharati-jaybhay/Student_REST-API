package com.example.student.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentWithCourseDto {

    private String firstName;

    private String lastName;

    private String phone;

    private List<CourseDto> courses;
}
