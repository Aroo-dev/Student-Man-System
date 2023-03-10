package com.aro.javaadmin.student;

import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;


public interface StudentService {

    StudentDTO createStudent(StudentDTO studentDTO);

    Page<StudentDTO> findStudentsByName(String name, int page, int size);

    StudentDTO findStudentById(Long id);

    void removeStudent(Long id);

    StudentDTO updateStudent(StudentDTO studentDTO, Authentication authentication);

    StudentDTO findStudentByUserEmail(String email);
}
