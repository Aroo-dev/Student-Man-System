package com.aro.javaadmin.course;

import com.aro.javaadmin.instructor.Instructor;
import com.aro.javaadmin.student.Student;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter

@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "course_name", nullable = false, length = 45)
    private String name;

    @Column(name = "course_duration", nullable = false, length = 45)
    private Duration duration;

    @Column(name = "course_description", nullable = false, length = 500)
    private String description;

    @Column(name = "category", nullable = false, length = 225)
    private String category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "enrolled",
            joinColumns = {@JoinColumn(name = "course_fk")},
            inverseJoinColumns = {@JoinColumn(name = "student_fk")})
    private Set<Student> students = new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id",
            nullable = false,
            referencedColumnName = "instructor_id")
    private Instructor instructor;

    public Course(String name, Duration courseDuration, String courseDescription, Instructor instructor) {
        this.name = name;
        this.duration = courseDuration;
        this.description = courseDescription;
        this.instructor = instructor;
    }

    public void assignStudentToCourse(Student student) {
        this.students.add(student);
        student.getCourses().add(this);
    }

    public void removeStudentFromCourse(Student student) {
        this.students.remove(student);
        student.getCourses().remove(this);
    }

}
