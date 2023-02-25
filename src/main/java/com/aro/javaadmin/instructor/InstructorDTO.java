package com.aro.javaadmin.instructor;


import com.aro.javaadmin.user.UserDTO;

import javax.validation.constraints.NotBlank;

public class InstructorDTO {


    private Long instructorId;

    private String firstName;
    private String lastName;
    private String summary;
    @NotBlank
    private UserDTO user;

    public InstructorDTO(String firstName, String lastName, String summary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.summary = summary;
    }

    public InstructorDTO(String firstName, String lastName, String summary, UserDTO user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.summary = summary;
        this.user = user;

    }

    public InstructorDTO(Long instructorId, String firstName, String lastName, String summary) {
        this.instructorId = instructorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.summary = summary;
    }

    public InstructorDTO() {
    }

    @Override
    public String toString() {
        return "InstructorDTO{" +
                "instructorId=" + instructorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", summary='" + summary + '\'' +
                ", userDTO=" + user +
                '}';
    }

    public Long getInstructorId() {
        return this.instructorId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getSummary() {
        return this.summary;
    }

    public @NotBlank UserDTO getUser() {
        return this.user;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setUser(@NotBlank UserDTO user) {
        this.user = user;
    }
}
