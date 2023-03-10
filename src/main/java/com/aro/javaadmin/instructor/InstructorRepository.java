package com.aro.javaadmin.instructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    @Query(value = " select i  from Instructor  as i where i.firstName like %:name% or i.lastName like %:name%")
    Page<Instructor> findInstructorByFirstNameOrLastNameContaining(@Param("name") String name, Pageable pageable);


    @Query(value = "select i from Instructor  as i where i.user.email=:email")
    Instructor findInstructorByUserEmail(String email);



}
