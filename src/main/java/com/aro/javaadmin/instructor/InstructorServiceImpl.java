package com.aro.javaadmin.instructor;

import com.aro.javaadmin.email.EmailSenderService;
import com.aro.javaadmin.exception.ResourceNotFoundException;
import com.aro.javaadmin.security.AuthenticationHandler;
import com.aro.javaadmin.user.User;
import com.aro.javaadmin.user.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service

class InstructorServiceImpl implements InstructorService {
    private static final Logger logger = LoggerFactory.getLogger(InstructorServiceImpl.class);
    public static final java.lang.String INSTRUCTOR_NOT_FOUND = "Instructor not found";
    private final EmailSenderService emailSenderService;
    private final ModelMapper modelMapper;
    private final InstructorRepository instructorRepository;
    private final UserService userService;
    private final AuthenticationHandler authenticationHelper;

    @Transactional
    public InstructorDTO assignStudentRoleToInstructorById(Long id){
        InstructorDTO instructorFoundById = getInstructorById(id);
        String email = instructorFoundById.getUser().getEmail();
        userService.assignRoleToUser(email,"Student");
        return modelMapper.map(instructorRepository.findById(id), InstructorDTO.class);

    }

    @Override
    public Page<InstructorDTO> findInstructorByNameOrLastName(String name, int page, int size) {
        if (size >100){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Page size cannot exceed 100");
        }
        Page<Instructor> instructorsFoundByFirstNameOrLastNameContaining =
                instructorRepository
                        .findInstructorByFirstNameOrLastNameContaining(name, PageRequest.of(page, size));
        return new PageImpl<>(instructorsFoundByFirstNameOrLastNameContaining.getContent()
                .stream()
                .map(instructor -> modelMapper.map(instructor, InstructorDTO.class))
                .collect(Collectors.toList()),
                PageRequest.of(page, size),
                instructorsFoundByFirstNameOrLastNameContaining.getTotalElements());

    }

    @Override
    public InstructorDTO findInstructorById(Long id) {
        return getInstructorById(id);
    }

    private InstructorDTO getInstructorById(Long id) {
        Instructor instructor = instructorRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, INSTRUCTOR_NOT_FOUND));
        return modelMapper.map(instructor, InstructorDTO.class);
    }

    @Override
    public InstructorDTO findInstructorByEmail(String email) {
        try{
            Instructor instructorByUserEmail = instructorRepository.findInstructorByUserEmail(email);

            return modelMapper.map(instructorByUserEmail, InstructorDTO.class);
        }catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,INSTRUCTOR_NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public InstructorDTO createInstructor(InstructorDTO instructorDTO) {
        String password = instructorDTO.getUser().getPassword();
        User user = userService.
                createUser(instructorDTO.getUser().getEmail(),
                        instructorDTO.getUser().getPassword(), instructorDTO.getUser().getPassword());
        userService.assignRoleToUser(user.getEmail(), "Instructor");
        Instructor instructor = modelMapper.map(instructorDTO, Instructor.class);
        instructor.setUser(user);
        Instructor savedInstructor = instructorRepository.save(instructor);
        emailSenderService.sendEmail(savedInstructor.getUser().getEmail(),
                "Please consider changing your password."
                        + " Your default password is " + password);
        return modelMapper.map(savedInstructor, InstructorDTO.class);
    }

    @Override
    @Transactional
    public InstructorDTO updateInstructor(InstructorDTO instructorDTO, Authentication authentication) {
        Instructor instructor = authenticationHelper.
                authenticateInstructor(instructorDTO, authentication);
        Instructor instructor1 = modelMapper.map(instructorDTO, Instructor.class);
        instructor1.setUser(instructor.getUser());
        instructor1.setCourses(instructor.getCourses());
        instructorRepository.save(instructor1);
        return modelMapper.map(instructor1, InstructorDTO.class);
    }


    public List<InstructorDTO> fetchInstructors() {

        List<Instructor> listOfInstructors = instructorRepository.findAll();
        List<InstructorDTO> collect = listOfInstructors
                .stream()
                .map(instructor -> modelMapper.map(instructor, InstructorDTO.class))
                .collect(Collectors.toUnmodifiableList());
        return collect;

    }

    @Transactional
    public void removeInstructor(Long id) {
        Instructor instructor = instructorRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,INSTRUCTOR_NOT_FOUND));

        instructorRepository.deleteById(instructor.getInstructorId());
    }
}
