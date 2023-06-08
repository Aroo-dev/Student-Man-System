package com.aro.javaadmin.course;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/courses")

public class CourseRestController {

    private final CourseService courseService;


    @GetMapping()
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<Page<CourseDTOrequest>> searchAllCourses(@RequestParam(name = "keyword",defaultValue = "") String keyword,
                                                                   @RequestParam(name = "page",defaultValue = "0") int page,
                                                                   @RequestParam(name = "size",defaultValue = "5") int size) {
        Page<CourseDTOrequest> coursesByCourseName = courseService.findCoursesByCourseName(keyword, page, size);
        return new ResponseEntity<>(coursesByCourseName, HttpStatus.OK);
    }

    @DeleteMapping({"/{courseId}"})
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<CourseDTOrequest> deleteCourseById(@PathVariable("courseId") Long courseId){
        courseService.removeCourse(courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDTOrequest> getCourseById(@PathVariable("courseId") Long courseId){
        CourseDTOrequest courseById = courseService.getCourseById(courseId);
        return new ResponseEntity<>(courseById, HttpStatus.OK);
    }


    @PostMapping()
    @PreAuthorize("hasAnyAuthority('Admin','Instructor')")
    public ResponseEntity<CourseDTOrequest> saveCourse(@RequestBody CourseDTOrequest courseDTO){
        CourseDTOrequest course = courseService.createCourse(courseDTO);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }


    @PutMapping("/{courseId}")
    @PreAuthorize("hasAnyAuthority('Admin','Instructor')")

    public ResponseEntity<CourseDTOrequest> updateCourse(@RequestBody CourseDTOrequest courseDTO, @PathVariable Long courseId){
        courseDTO.setCourseId(courseId);
        CourseDTOrequest course = courseService.updateCourse(courseDTO);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PostMapping("/{courseId}/enroll/students/{studentId}")
    @PreAuthorize("hasAuthority('Student')")
    public ResponseEntity<CourseDTOrequest> enrollStudentToCourse(@PathVariable Long courseId, @PathVariable("studentId") Long studentId){
        courseService.assignStudentToCourse(courseId,studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/aroczek")
    @PreAuthorize("hasAuthority('Admin')")
    public Map<String, Long> xxx(@RequestParam(value = "category", defaultValue = "") String category){
        return courseService.xxx(category);
    }
}
