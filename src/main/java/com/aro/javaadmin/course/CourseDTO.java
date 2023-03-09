package com.aro.javaadmin.course;

import com.aro.javaadmin.instructor.InstructorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.Duration;

@Data
@AllArgsConstructor
public class CourseDTO {

    private Long courseId;
    private String name;
    private Duration duration;
    private String description;
    private InstructorDTO instructor;

    public CourseDTO(String name, Duration duration, String description) {
        this.name = name;
        this.duration = duration;
        this.description = description;
    }

    public CourseDTO() {
    }
}
