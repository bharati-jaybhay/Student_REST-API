package com.example.student.controller;

import com.example.student.Mapper.StudentMapper;
import com.example.student.dto.StudentWithCourseDto;
import com.example.student.entity.Course;
import com.example.student.entity.Student;
import com.example.student.repository.CourseRepository;
import com.example.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/courseAssignToStudent/{courseId}/{studentId}")
    public String assignCourseToStudent(@PathVariable int courseId, @PathVariable int studentId){
        Student student = studentRepository.findById(studentId).orElseThrow(()->new RuntimeException("Student Not found"));

        Course course = courseRepository.findById(courseId).orElseThrow(()->new RuntimeException("course Not found"));
        student.addCourse(course);  // This will add the course and maintain bidirectional association
//        course.addStudent(student);
        studentRepository.save(student);  // Save the student, and both sides of the relationship should be persisted

        return "Course added successfully";

    }

    @GetMapping("/get-student-with-courses/{studentId}")
    public StudentWithCourseDto getStudentWithCourses(@PathVariable int studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->new RuntimeException("Student Not found"));
        StudentWithCourseDto studentWithCourseDto = StudentMapper.convertStudentToStudentWithCourseDto(student,new StudentWithCourseDto());

        return studentWithCourseDto;
    }
}
