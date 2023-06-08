package com.aro.javaadmin.instructor;

import com.aro.javaadmin.user.UserDTORequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
public class InstructorDTO {

    private Long instructorId;
    private String firstName;
    private String lastName;
    private String summary;
    @NotBlank
    private UserDTORequest user;


}
