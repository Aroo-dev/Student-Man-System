package com.aro.javaadmin.instructor;

import com.aro.javaadmin.course.Course;
import com.aro.javaadmin.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


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

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public Instructor(Long instructorId, String firstName, String lastName, User user, String summary) {
        this.instructorId = instructorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
        this.summary = summary;
    }


    @Override
    public String toString() {
        return "Instructor{" + "instructorId='" +
                instructorId + '\'' + ", firstName='"
                + firstName + '\'' + ", lastName='" +
                lastName + '\'' + ", summary='" + summary + '\'' + '}';
    }
}
