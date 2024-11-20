package com.example.student.controller;

import com.example.student.Mapper.StudentMapper;
import com.example.student.dto.StudentDto;
import com.example.student.entity.Student;
import com.example.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/saveStudent")
    public StudentDto saveStudent(@RequestBody StudentDto studentDto) {
        Student student = StudentMapper.convertStudentDtoToStudent(studentDto, new Student());
        studentRepository.save(student);
        studentDto.setStudentId(student.getStudentId());
        return studentDto;
    }

    @GetMapping("/getAllStudents")
    public List<StudentDto> getAllStudents(){
        List<Student>students=studentRepository.findAll();
        List<StudentDto>studentDtos = new ArrayList<>();

        for(Student student: students){
            StudentDto studentDto=StudentMapper.convertStudentToStudentDto(student, new StudentDto());
            studentDtos.add(studentDto);
        }
        return studentDtos;
    }

    @GetMapping("/getStudent/{studentId}")
    public StudentDto getStudentById(@PathVariable int studentId){
        Student student= studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not fount"));
        StudentDto studentDto = StudentMapper.convertStudentToStudentDto(student, new StudentDto());
        return studentDto;
    }

    @DeleteMapping("/deleteStudent/{studentId}")
    public String deleteStudent(@PathVariable int studentId){
        if(!studentRepository.existsById(studentId)){
            throw new RuntimeException("Student not found");
        }
        studentRepository.deleteById(studentId);
        return "student deleted successfully";
    }

    @PutMapping("/updateStudent/{studentId}")
    public StudentDto updateStudentById(@PathVariable int studentId, @RequestBody StudentDto studentDto) {
        // Retrieve the existing student
        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student with ID " + studentId + " not found"));

        // Update fields using Optional to reduce null checks
        Optional.ofNullable(studentDto.getFirstName()).ifPresent(existingStudent::setFirstName);
        Optional.ofNullable(studentDto.getLastName()).ifPresent(existingStudent::setLastName);
        Optional.ofNullable(studentDto.getEmail()).ifPresent(existingStudent::setEmail);
        Optional.ofNullable(studentDto.getPhone()).ifPresent(existingStudent::setPhone);

        // Save and map to DTO in one step
        return StudentMapper.convertStudentToStudentDto(studentRepository.save(existingStudent), new StudentDto());
    }


}
