package com.aro.javaadmin.student;

import com.aro.javaadmin.user.UserDTO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StudentDTO {


    private Long studentId;

    private String firstName;

    private String lastName;

    private String level;

    private UserDTO user;
}
