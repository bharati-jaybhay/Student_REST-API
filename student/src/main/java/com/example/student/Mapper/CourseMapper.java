package com.example.student.Mapper;

import com.example.student.dto.CourseDto;
import com.example.student.entity.Course;

public class CourseMapper {

    public static Course courseDtoToCourse(CourseDto courseDto, Course course) {
        course.setCourseId(courseDto.getCourseId());
        course.setCourseName(courseDto.getCourseName());
        course.setDuration(courseDto.getDuration());
        course.setFees(courseDto.getFees());
        return course;
    }

    public static CourseDto courseToCourseDto(Course course, CourseDto courseDto) {
        courseDto.setCourseId(course.getCourseId());
        courseDto.setCourseName(course.getCourseName());
        courseDto.setDuration(course.getDuration());
        courseDto.setFees(course.getFees());
        return courseDto;
    }
}
