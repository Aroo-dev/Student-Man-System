package com.aro.javaadmin.instructor;

import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InstructorService {


    Page<InstructorDTO> findInstructorByNameOrLastName(String name, int page, int size);
    InstructorDTO findInstructorById(Long id);
    InstructorDTO findInstructorByEmail(String email);
    InstructorDTO createInstructor(InstructorDTO instructorDTO);
    InstructorDTO updateInstructor(InstructorDTO instructorDTO, Authentication authentication);
    List<InstructorDTO> fetchInstructors();
     void removeInstructor(Long id);
}
