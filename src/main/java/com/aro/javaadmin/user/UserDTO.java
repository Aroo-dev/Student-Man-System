package com.aro.javaadmin.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {

    @NotBlank(message = "Email must be not blank")
    @Email()
    private String email;
    @JsonIgnore
    private String password;
}
