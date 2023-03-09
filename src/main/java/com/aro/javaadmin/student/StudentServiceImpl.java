package com.aro.javaadmin.student;

import com.aro.javaadmin.course.Course;
import com.aro.javaadmin.exception.EmailAlreadyTakenException;
import com.aro.javaadmin.exception.EmailNotFoundException;
import com.aro.javaadmin.exception.ResourceNotFoundException;
import com.aro.javaadmin.exception.StudentNotFoundException;
import com.aro.javaadmin.security.AuthenticationHandler;
import com.aro.javaadmin.user.User;
import com.aro.javaadmin.user.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {

    private final AuthenticationHandler<Student> authenticationHelper;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student studentByUserEmail = studentRepository.findStudentByUserEmail(studentDTO.getUser().getEmail());
        if (studentByUserEmail != null) {
            throw new EmailAlreadyTakenException("Email", studentDTO.getUser().getEmail());
        }

        User user = userService
                .createUser(studentDTO.getUser().getEmail(), studentDTO.getUser().getPassword());
        userService.assignRoleToUser(user.getEmail(), "Student");
        Student student = modelMapper.map(studentDTO, Student.class);
        student.setUser(user);
        studentRepository.save(student);
        return modelMapper.map(student, StudentDTO.class);
    }

    public StudentDTO updateStudent(StudentDTO studentDTO, Authentication authentication) {
        Student student = authenticationHelper.authenticateStudent(studentDTO, authentication);
        Student mappedStudent = modelMapper.map(studentDTO, Student.class);
        mappedStudent.setCourses(student.getCourses());
        mappedStudent.setUser(student.getUser());
        Student savedStudent = studentRepository.save(mappedStudent);
        return modelMapper.map(savedStudent, StudentDTO.class);
    }

    public void removeStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
        for (Course course : student.getCourses()) {
            course.removeStudentFromCourse(student);
        }
        studentRepository.delete(student);


    }

    public StudentDTO findStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
        return modelMapper.map(student, StudentDTO.class);

    }

    @Override
    public Page<StudentDTO> findStudentsByName(String name, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Student> listOfStudents = studentRepository.findStudentsByName(name, pageRequest);
        if (listOfStudents == null) {
            throw new StudentNotFoundException("Students", name);
        }
        return new PageImpl<>(listOfStudents
                .getContent()
                .stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList()), pageRequest, listOfStudents.getTotalElements());

    }

    @Override
    public StudentDTO findStudentByUserEmail(String email) {
        Student student = studentRepository.findStudentByUserEmail(email);
        if (student == null) {
            throw new EmailNotFoundException("Email", email);
        }

        return modelMapper.map(student, StudentDTO.class);
    }
}
