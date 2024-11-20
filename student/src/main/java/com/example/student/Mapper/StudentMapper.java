package com.example.student.Mapper;

import com.example.student.dto.CourseDto;
import com.example.student.dto.StudentDto;
import com.example.student.dto.StudentWithCourseDto;
import com.example.student.entity.Course;
import com.example.student.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentMapper {

    // Converts StudentDto to Student (Entity)
    public static Student convertStudentDtoToStudent(StudentDto studentDto, Student student) {
        student.setStudentId(studentDto.getStudentId());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setPhone(studentDto.getPhone());
        student.setEmail(studentDto.getEmail());
        return student;
    }

    // Converts Student (Entity) to StudentDto
    public static StudentDto convertStudentToStudentDto(Student student, StudentDto studentDto) {
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setPhone(student.getPhone());
        studentDto.setEmail(studentDto.getEmail());
        return studentDto;
    }

    public static StudentWithCourseDto convertStudentToStudentWithCourseDto(Student student,StudentWithCourseDto studentWithCourseDto){
        studentWithCourseDto.setFirstName(student.getFirstName());
        studentWithCourseDto.setLastName(student.getLastName());
        studentWithCourseDto.setPhone(student.getPhone());

        List<Course> courses = student.getCourses();
        List<CourseDto> courseDtos = new ArrayList<>();
        for(Course course : courses){
            CourseDto courseDto = CourseMapper.courseToCourseDto(course,new CourseDto());
            courseDtos.add(courseDto);
        }

        studentWithCourseDto.setCourses(courseDtos);

        return studentWithCourseDto;
    }
}
