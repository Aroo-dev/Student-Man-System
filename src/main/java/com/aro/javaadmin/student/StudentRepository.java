package com.aro.javaadmin.student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "select s from Student  as s where  s.lastName like %:name or s.firstName like  %:name")
    Page<Student> findStudentsByName(@Param("name") String name, Pageable pageable);


    Student findStudentByUserEmail(String email);
}
