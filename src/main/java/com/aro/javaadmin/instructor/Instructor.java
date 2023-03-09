package com.aro.javaadmin.instructor;

import com.aro.javaadmin.course.Course;
import com.aro.javaadmin.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;


@ToString
@NoArgsConstructor
@Entity
@Table(name = "instructors")
@Getter
@Setter
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instructor_id")
    private Long instructorId;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;
    @Column(nullable = false, name = "summary")
    private String summary;

    @OrderBy("name")
    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Course> courses = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public Instructor(Long instructorId, String firstName, String lastName, User user, String summary) {
        this.instructorId = instructorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
        this.summary = summary;
    }
}
