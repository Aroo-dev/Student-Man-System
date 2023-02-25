package com.aro.javaadmin.user;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor

public class UserDTO {
    @NotBlank
    @Email()
    private String email;

    private String password;
}
