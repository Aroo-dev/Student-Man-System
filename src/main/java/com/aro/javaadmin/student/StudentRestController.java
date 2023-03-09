package com.aro.javaadmin.student;

import com.aro.javaadmin.course.CourseDTO;
import com.aro.javaadmin.course.CourseService;
import com.aro.javaadmin.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentRestController {


    private final StudentService studentService;
    private final CourseService courseService;
    private final UserService userService;

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long studentId, @RequestHeader("security") String header) {
        StudentDTO studentById = studentService.findStudentById(studentId);
        return new ResponseEntity<>(studentById, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/search")
    public ResponseEntity<Page<StudentDTO>> getStudentsByFirstNameOrLastName(@RequestParam(name = "name", defaultValue = "") String name,
                                                                             @RequestParam(name = "page", defaultValue = "0") int page,
                                                                             @RequestParam(name = "size", defaultValue = "6") int size) {

        Page<StudentDTO> studentsByName = studentService.findStudentsByName(name, page, size);
        return new ResponseEntity<>(studentsByName, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/{studentId}")
    public ResponseEntity<StudentDTO> deleteStudentById(@PathVariable Long studentId) {
        studentService.removeStudent(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping()
    public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO student = studentService.createStudent(studentDTO);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('Student')")
    @PutMapping("/{studentId}")
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO, @PathVariable Long studentId, Authentication authentication) {
        studentDTO.setStudentId(studentId);
        StudentDTO student = studentService.updateStudent(studentDTO, authentication);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping()
    public ResponseEntity<StudentDTO> findStudentByEmail(@RequestParam(name = "email") String email) {
        StudentDTO studentByUserEmail = studentService.findStudentByUserEmail(email);
        return new ResponseEntity<>(studentByUserEmail, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Student')")
    @GetMapping("{studentId}/courses")
    public ResponseEntity<Page<CourseDTO>> getStudentEnrolledCoursesById(@PathVariable Long studentId,
                                                                         @RequestParam(name = "page", defaultValue = "0") int page,
                                                                         @RequestParam(name = "size", defaultValue = "6") int size) {

        Page<CourseDTO> courses = courseService.fetchCoursesForStudents(studentId, page, size);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Student')")
    @GetMapping("{studentId}/available-courses")
    public ResponseEntity<Page<CourseDTO>> getStudentNonEnrolledCoursesById(@PathVariable Long studentId,
                                                                            @RequestParam(name = "page", defaultValue = "0") int page,
                                                                            @RequestParam(name = "size", defaultValue = "6") int size) {

        Page<CourseDTO> courses = courseService.fetchNotEnrolledCoursesForStudents(studentId, page, size);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }


}
