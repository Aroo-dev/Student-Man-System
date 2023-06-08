package com.aro.javaadmin.student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class StudentResponseDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String level;
}
