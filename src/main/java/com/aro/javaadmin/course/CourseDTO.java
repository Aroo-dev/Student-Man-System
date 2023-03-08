package com.aro.javaadmin.course;

import com.aro.javaadmin.instructor.InstructorDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.Duration;

@Getter
@Setter
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

    public String toString() {
        return "CourseDTO(courseId=" + this.getCourseId() + ", name=" + this.getName() +
                ", duration=" + this.getDuration() + ", description=" + this.getDescription() +
                ", instructor=" + this.getInstructor().getFirstName() + this.instructor.getUser().getEmail() + ")";
    }
}
