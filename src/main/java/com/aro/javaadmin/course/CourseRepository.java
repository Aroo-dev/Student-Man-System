package com.aro.javaadmin.course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select count(c) from Course c where c.category = ?1")
    long countByCategory(String category);

    @Query(value = "select * from courses as c where c.course_id in (select  e.course_fk from enrolled as e where e.student_fk =:studentId)", nativeQuery = true)
//    @Query(value = "SELECT c from  Course as c, Enrolled as e  WHERE e.student_fk=:studentId")
    Page<Course> findCoursesByStudentId(@Param("studentId") Long studentId, Pageable pageable);

    @Query("select c from Course c where c.name like concat('%', ?1, '%')")
    Page<Course> findCourseByNameContains(String keyword, Pageable pageable);



//    @Query(value = "select * FROM courses as c where c.course_id not in (select  e.course_fk from enrolled as e where e.student_fk =:studentId)", nativeQuery = true)
    @Query("SELECT c FROM Course c LEFT JOIN c.students s ON s.studentId = :studentId WHERE s.studentId IS NULL")
    Page<Course> getNonEnrolledCourses(@Param("studentId") Long studentId, Pageable pageable);


    @Query("select c from Course c where c.instructor.instructorId = ?1")
    Page<Course> findCoursesByInstructorInstructorId(Long instructorId, Pageable pageable);






}
