package com.aro.javaadmin.user;

import com.aro.javaadmin.utils.ConfirmedField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@ConfirmedField(originalField ="password",confirmationField ="confirmPassword", message = "Chuj w dupe, nie dziala")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTORequest {

    @NotBlank(message = "Email must be not blank")
    @Email(message = "Not proper email")
    private String email;

    private String password;

    private String confirmPassword;
}
