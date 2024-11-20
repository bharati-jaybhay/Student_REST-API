package com.example.student.controller;

import com.example.student.Mapper.CourseMapper;
import com.example.student.dto.CourseDto;
import com.example.student.entity.Course;
import com.example.student.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/saveCourse")
    public CourseDto saveCourse(@RequestBody CourseDto courseDto){
        Course course = CourseMapper.courseDtoToCourse(courseDto, new Course());
        courseRepository.save(course);
        courseDto.setCourseId(course.getCourseId());
        return courseDto;
    }

    @GetMapping("/getAllCourses")
    public List<CourseDto> getAllCourses(){
        List<Course>courses=courseRepository.findAll();
        List<CourseDto>courseDtos=new ArrayList<>();

        for(Course course:courses){
            CourseDto courseDto=CourseMapper.courseToCourseDto(course,new CourseDto());
            courseDtos.add(courseDto);
        }
        return courseDtos;
    }

    @GetMapping("/getCourse/{courseId}")
    public CourseDto getCourseByID(@PathVariable int courseId){
        Course course=courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        CourseDto courseDto=CourseMapper.courseToCourseDto(course , new CourseDto());
        return courseDto;
    }

    @DeleteMapping("/deleteCourse/{courseId}")
    public String deleteCourseByID(@PathVariable int courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new RuntimeException("Course not found");
        }
        courseRepository.deleteById(courseId); // Perform the deletion
        return "Course deleted successfully";
    }

    @PutMapping("/updateCourse/{courseId}")
    public CourseDto updateCourseById(@PathVariable int courseId, @RequestBody CourseDto courseDto) {
        Course existingCourse = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course with ID " + courseId + " not found"));

        if (courseDto.getCourseName() != null) {
            existingCourse.setCourseName(courseDto.getCourseName());
        }
        if (courseDto.getDuration() != null) {
            existingCourse.setDuration(courseDto.getDuration());
        }

        Course updatedCourse = courseRepository.save(existingCourse);

        CourseDto updatedCourseDto = CourseMapper.courseToCourseDto(updatedCourse, new CourseDto());

        return courseDto;
    }


}
