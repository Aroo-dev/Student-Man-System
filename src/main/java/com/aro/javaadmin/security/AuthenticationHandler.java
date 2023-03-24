package com.aro.javaadmin.security;

import com.aro.javaadmin.exception.BadAuthenticationException;
import com.aro.javaadmin.exception.ResourceNotFoundException;
import com.aro.javaadmin.instructor.Instructor;
import com.aro.javaadmin.instructor.InstructorDTO;
import com.aro.javaadmin.instructor.InstructorRepository;
import com.aro.javaadmin.student.Student;
import com.aro.javaadmin.student.StudentRequestDTO;
import com.aro.javaadmin.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthenticationHandler<T> {

    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;


    public Instructor authenticateInstructor(InstructorDTO instructorDTO, Authentication authentication) {
        java.lang.String email = authentication.getName();
        Instructor instructor = instructorRepository
                .findById(instructorDTO.getInstructorId())
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Instructor", "id", instructorDTO.getInstructorId()));
        if (!instructor.getUser().getEmail().equals(email)) {
            throw new BadAuthenticationException("instructor");
        }
        return instructor;
    }

    public Student authenticateStudent(StudentRequestDTO studentDTO, Authentication authentication) {
        String email = authentication.getName();
        Student student = studentRepository
                .findById(studentDTO.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Instructor", "id", studentDTO.getStudentId()));
        if (!student.getUser().getEmail().equals(email)) {
            throw new BadAuthenticationException("student");
        }
        return student;
    }


}
