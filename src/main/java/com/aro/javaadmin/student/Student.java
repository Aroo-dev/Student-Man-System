package com.aro.javaadmin.student;

import com.aro.javaadmin.course.Course;
import com.aro.javaadmin.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "first_name", length = 45)
    private String firstName;

    @Column(name = "last_name", length = 45)
    private String lastName;

    @Column(name = "level", length = 45)
    private String level;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses = new HashSet<>();
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_fk",referencedColumnName = "user_id",nullable = false)
    private User user;

    public Student(String studentFirstName, String lastName, String level, User user) {
        this.firstName = studentFirstName;
        this.lastName = lastName;
        this.level = level;
        this.user = user;
    }

    public Student(String studentFirstName, String lastName, String level) {
        this.firstName = studentFirstName;
        this.lastName = lastName;
        this.level = level;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentFirstName='" + firstName + '\'' +
                ", studentLastName='" + lastName + '\'' +
                ", level='" + level + '\'' +
                ", courses=" + courses +
                '}';
    }
}
