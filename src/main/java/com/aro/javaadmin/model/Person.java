package com.aro.javaadmin.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@Setter
@Getter
@MappedSuperclass
public class Person {
    @Column(name = "first_name", length = 45)
    protected String firstName;
    @Column(name = "last_name", length = 45)
    protected String lastName;

}
