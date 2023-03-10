package com.aro.javaadmin.instructor;

import com.aro.javaadmin.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class InstructorDTO {

    private Long instructorId;
    private String firstName;
    private String lastName;
    private String summary;
    @NotBlank
    private UserDTO user;


}
