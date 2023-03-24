package com.aro.javaadmin.student;

import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;


public interface StudentService {

    StudentResponseDTO createStudent(StudentRequestDTO studentDTO);

    Page<StudentRequestDTO> findStudentsByName(String name, int page, int size);

    StudentRequestDTO findStudentById(Long id);

    void removeStudent(Long id);

    StudentResponseDTO updateStudent(StudentRequestDTO studentDTO, Authentication authentication);

    StudentRequestDTO findStudentByUserEmail(String email);
}
