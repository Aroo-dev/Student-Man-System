package com.aro.javaadmin.runner;

import com.aro.javaadmin.course.CourseDTO;
import com.aro.javaadmin.course.CourseService;
import com.aro.javaadmin.instructor.InstructorDTO;
import com.aro.javaadmin.instructor.InstructorService;
import com.aro.javaadmin.role.RoleService;
import com.aro.javaadmin.student.StudentDTO;
import com.aro.javaadmin.user.UserDTO;
import com.aro.javaadmin.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.actuate.endpoint.web.Link;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;

@RequiredArgsConstructor
@Component
public class CustomRunner implements CommandLineRunner {


    private final RoleService roleService;
    private final UserService userService;
    private final InstructorService instructorService;
    private final CourseService courseService;


    @Override
    public void run(String... args) throws Exception {
        createRoles();
        createAdmin();
        createInstructor();
        createCourse();

    }




        private void    createCourse() {

        for (int i = 0; i < 20; i++) {
            CourseDTO courseDTO = new CourseDTO("java"+1, Duration.ofMinutes(142), "String");
            InstructorDTO instructorDTO = new InstructorDTO();
            instructorDTO.setInstructorId(1L);
            courseDTO.setInstructor(instructorDTO);
            courseService.createCourse(courseDTO);
        }

    }


    private void createInstructor() {
        for (int i = 0; i < 10; i++) {
            InstructorDTO instructorDTO = new InstructorDTO();
            instructorDTO.setFirstName("Artur" + i + "FN");
            instructorDTO.setLastName("isntructor" + i + "LN");
            instructorDTO.setSummary("12e23123 " + i);
            UserDTO userDTO = new UserDTO("Henry65" + i + "@gmail.com", "pass" + i);
            instructorDTO.setUser(userDTO);
            instructorService.createInstructor(instructorDTO);


        }

    }

    private void createAdmin() {
        userService.createUser("axxrxxo@gmail.com", "Henry6591@");
        userService.assignRoleToStudent("axxrxxo@gmail.com", "Admin");

    }

    private void createRoles() {
        Arrays.asList("Admin", "Instructor", "Student").forEach(roleService::createRole);


    }

}
