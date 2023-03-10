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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/courses")

public class CourseRestController {

    private final CourseService courseService;

    @GetMapping()
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<Page<CourseDTO>> searchAllCourses(@RequestParam(name = "keyword",defaultValue = "") String keyword,
                                                            @RequestParam(name = "page",defaultValue = "0") int page,
                                                            @RequestParam(name = "size",defaultValue = "5") int size) {
        Page<CourseDTO> coursesByCourseName = courseService.findCoursesByCourseName(keyword, page, size);
        return new ResponseEntity<>(coursesByCourseName, HttpStatus.OK);
    }

    @DeleteMapping({"/{courseId}"})
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<CourseDTO> deleteCourseById(@PathVariable("courseId") Long courseId){
        courseService.removeCourse(courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable("courseId") Long courseId){
        CourseDTO courseById = courseService.getCourseById(courseId);
        return new ResponseEntity<>(courseById, HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('Admin','Instructor')")
    public ResponseEntity<CourseDTO> saveCourse(@RequestBody CourseDTO courseDTO){
        CourseDTO course = courseService.createCourse(courseDTO);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }


    @PutMapping("/{courseId}")
    @PreAuthorize("hasAnyAuthority('Admin','Instructor')")

    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO, @PathVariable Long courseId){
        courseDTO.setCourseId(courseId);
        CourseDTO course = courseService.updateCourse(courseDTO);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PostMapping("/{courseId}/enroll/students/{studentId}")
    @PreAuthorize("hasAuthority('Student')")
    public ResponseEntity<CourseDTO> enrollStudentToCourse(@PathVariable Long courseId, @PathVariable("studentId") Long studentId){
        courseService.assignStudentToCourse(courseId,studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
