package com.aro.javaadmin.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor

public class UserDTO {
    @NotBlank(message = "Email must be not blank")
    @Email()
    private String email;
    @JsonIgnore
    private String password;
}
