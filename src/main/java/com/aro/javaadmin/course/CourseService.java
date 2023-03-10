package com.aro.javaadmin.course;

import org.springframework.data.domain.Page;

public interface CourseService {

    CourseDTO getCourseById(Long id);

    CourseDTO createCourse(CourseDTO courseDTO);

    CourseDTO updateCourse(CourseDTO courseDTO);

    Page<CourseDTO> findCoursesByCourseName(String courseName, int page, int size);

    void assignStudentToCourse(Long courseId, Long id);

    Page<CourseDTO> fetchCoursesForStudents(Long studentId,  int page, int size);


    Page<CourseDTO> fetchNotEnrolledCoursesForStudents(Long studentId, int page, int size);

    void removeCourse(Long courseId);

    Page<CourseDTO> fetchCoursesForInstructorByInstructorId(Long instructorId, int page, int size);
}
