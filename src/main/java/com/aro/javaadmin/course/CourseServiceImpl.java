package com.aro.javaadmin.course;

import com.aro.javaadmin.exception.ResourceNotFoundException;
import com.aro.javaadmin.instructor.Instructor;
import com.aro.javaadmin.instructor.InstructorRepository;
import com.aro.javaadmin.student.Student;
import com.aro.javaadmin.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Map;
import java.util.stream.Collectors;


@Transactional
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    
    private final ModelMapper modelMapper;
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;


    @Override
    public Map<String, Long> xxx(String category) {
        long l = courseRepository.countByCategory(category);
        return Map.of(category,l);

    }

    @Override
    public CourseDTOrequest getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
        return modelMapper.map(course, CourseDTOrequest.class);
    }

    @Override
    public CourseDTOrequest createCourse(CourseDTOrequest courseDTO) {
        Course course = modelMapper.map(courseDTO, Course.class);
        Instructor instructor = instructorRepository.findById(courseDTO
                .getInstructor()
                .getInstructorId())
                .orElseThrow(() -> new ResourceNotFoundException("Instructor", "id", courseDTO.getInstructor().getInstructorId()));
        course.setInstructor(instructor);
        Course savedCourse = courseRepository.save(course);
        return modelMapper.map(savedCourse, CourseDTOrequest.class);
    }

    @Override
    public CourseDTOrequest updateCourse(CourseDTOrequest courseDTO) {
        Course course = modelMapper.map(courseDTO, Course.class);
        Course course1 = courseRepository.findById(course.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseDTO.getCourseId()));
        Instructor instructor = instructorRepository.findById(courseDTO.getInstructor()
                .getInstructorId())
                .orElseThrow(() -> new ResourceNotFoundException("Instructor", "id", courseDTO.getInstructor().getInstructorId()));
        course1.setInstructor(instructor);
        course1.setStudents(course.getStudents());

        course1.setDuration(courseDTO.getDuration());
        course1.setDescription(courseDTO.getDescription());
        course1.setName(courseDTO.getName());

        return modelMapper.map(course1, CourseDTOrequest.class);
    }

    // TODO adjust cache config to your requirements

    @Cacheable(cacheNames = "CoursesByCourseName" , key = "#courseName")
    @Override
    public Page<CourseDTOrequest> findCoursesByCourseName(String courseName, int page, int size) {
        PageRequest pageRequest = getPageRequest(page, size);
        Page<Course> courseByCourseNameContains = courseRepository
                .findCourseByNameContains(courseName, pageRequest);
        return new PageImpl<>(courseByCourseNameContains
                .getContent()
                .stream()
                .map(course -> modelMapper.map(course, CourseDTOrequest.class))
                .collect(Collectors.toList()));
    }

    @Override
    public void assignStudentToCourse(Long courseId, Long studentId) {
        Student student = studentRepository
                .findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
        Course course = courseRepository.
                findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", courseId));
        course.assignStudentToCourse(student);
    }

    @Override
    @Cacheable(cacheNames = "CoursesForStudents", key = "#studentId")
    public Page<CourseDTOrequest> fetchCoursesForStudents(Long studentId, int page, int size) {

        Page<Course> coursesByStudentId = courseRepository.findCoursesByStudentId(studentId, getPageRequest(page, size));
        return new PageImpl<>(coursesByStudentId.getContent()
                .stream()
                .map(course -> modelMapper.map(course, CourseDTOrequest.class))
                .collect(Collectors.toList()),getPageRequest(page,size),coursesByStudentId.getTotalElements());
    }

    @Override
    @Cacheable(cacheNames = "NotEnrolledCoursesForStudents", key = "#studentId")

    public Page<CourseDTOrequest> fetchNotEnrolledCoursesForStudents(Long studentId, int page, int size) {
        Page<Course> nonEnrolledCourses = courseRepository.getNonEnrolledCourses(studentId, getPageRequest(page, size));
        return new PageImpl<>(nonEnrolledCourses.getContent()
                .stream()
                .map(course -> modelMapper.map(course, CourseDTOrequest.class))
                .collect(Collectors.toList()),getPageRequest(page,size),nonEnrolledCourses.getTotalElements());
    }

    @Override
    public void removeCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    public Page<CourseDTOrequest> fetchCoursesForInstructorByInstructorId(Long instructorId, int page, int size) {
        Page<Course> coursesByInstructorInstructorId = courseRepository.findCoursesByInstructorInstructorId(instructorId, getPageRequest(page, size));
        return new PageImpl<>(coursesByInstructorInstructorId.getContent()
                .stream()
                .map(course -> modelMapper.map(course, CourseDTOrequest.class))
                .collect(Collectors.toList()),getPageRequest(page,size),coursesByInstructorInstructorId.getTotalElements());
    }

    private static PageRequest getPageRequest(int page, int size) {
        return PageRequest.of(page, size);
    }
}

