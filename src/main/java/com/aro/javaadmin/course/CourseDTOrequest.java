package com.aro.javaadmin.course;

import com.aro.javaadmin.instructor.InstructorDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTOrequest {

    private Long courseId;
    private String name;
    private Duration duration;
    private String description;
    private String category;
    @JsonIgnore
    private InstructorDTO instructor;

    public CourseDTOrequest(String name, Duration duration, String description, String category) {
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.category = category;
    }

    public CourseDTOrequest(String name, Duration duration, String description) {
        this.name = name;
        this.duration = duration;
        this.description = description;
    }


}
