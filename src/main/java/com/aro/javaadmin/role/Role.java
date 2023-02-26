package com.aro.javaadmin.role;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity

@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Long roleId;
    @Column(name = "name", nullable = false, unique = true)
    private String name;



    public Role(String name) {
        this.name = name;
    }






}
