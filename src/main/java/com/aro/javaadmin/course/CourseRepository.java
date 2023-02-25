package com.aro.javaadmin.course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query(value = "select * from courses as c where c.course_id in (select  e.course_fk from enrolled as e where e.student_fk =:studentId)", nativeQuery = true)
    Page<Course> findCoursesByStudentId(@Param("studentId") Long studentId, Pageable pageable);


    Page<Course> findCourseByNameContains(String keyword, Pageable pageable);

    @Query(value = "select * FROM courses as c where c.course_id not in (select  e.course_fk from enrolled as e where e.student_fk =:studentId)", nativeQuery = true)
    Page<Course> getNonEnrolledCourses(@Param("studentId") Long studentId, Pageable pageable);


    Page<Course> findCoursesByInstructorInstructorId( Long instructorId, Pageable pageable);

//    @Query(value = "SELECT i from Course  as i where i.students.=:email")
//    void findCoursesBys(Long studentId);


}
