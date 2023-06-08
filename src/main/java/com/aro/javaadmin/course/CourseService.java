package com.aro.javaadmin.course;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface CourseService {

    Map<String, Long> xxx(String category);

    CourseDTOrequest getCourseById(Long id);

    CourseDTOrequest createCourse(CourseDTOrequest courseDTO);

    CourseDTOrequest updateCourse(CourseDTOrequest courseDTO);

    Page<CourseDTOrequest> findCoursesByCourseName(String courseName, int page, int size);

    void assignStudentToCourse(Long courseId, Long id);

    Page<CourseDTOrequest> fetchCoursesForStudents(Long studentId, int page, int size);


    Page<CourseDTOrequest> fetchNotEnrolledCoursesForStudents(Long studentId, int page, int size);

    void removeCourse(Long courseId);

    Page<CourseDTOrequest> fetchCoursesForInstructorByInstructorId(Long instructorId, int page, int size);
}
