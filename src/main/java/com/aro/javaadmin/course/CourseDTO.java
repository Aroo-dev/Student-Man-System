package com.aro.javaadmin.course;

import com.aro.javaadmin.instructor.InstructorDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
@NoArgsConstructor
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

    public CourseDTO(String name, Duration duration, String description, InstructorDTO instructor) {
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.instructor = instructor;
    }

    public CourseDTO(Long courseId, String name, Duration duration, String description) {
        this.courseId = courseId;
        this.name = name;
        this.duration = duration;
        this.description = description;
    }

    public CourseDTO(Long courseId, String name, Duration duration, String description, InstructorDTO instructor) {
        this.courseId = courseId;
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "courseId=" + courseId +
                ", courseName='" + name + '\'' +
                ", courseDuration='" + duration + '\'' +
                ", courseDescription='" + description + '\'' +
                '}';
    }
}
