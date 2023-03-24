package com.aro.javaadmin.student;

import com.aro.javaadmin.user.UserDTORequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDTO {

    private static final String FIELD_NAME_MUST_CONTAIN_AT_LEAST_TWO_CHARACTERS = "Field Name must contain at least two characters";

    public StudentRequestDTO(String firstName, String lastName, String level, UserDTORequest user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.level = level;
        this.user = user;
    }

    private Long studentId;
    @NotEmpty
    @NotBlank
    @Size(max = 12, min = 2, message = FIELD_NAME_MUST_CONTAIN_AT_LEAST_TWO_CHARACTERS)
    private String firstName;

    @NotBlank
    @Size(max = 12, min = 2, message = FIELD_NAME_MUST_CONTAIN_AT_LEAST_TWO_CHARACTERS)
    private String lastName;

    @NotBlank
    private String level;
    @Valid
    private UserDTORequest user;

}
