package com.aro.javaadmin.instructor;

import com.aro.javaadmin.course.CourseDTO;
import com.aro.javaadmin.course.CourseService;
import com.aro.javaadmin.exception.EmailAlreadyTakenException;
import com.aro.javaadmin.user.User;
import com.aro.javaadmin.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/instructors")
public class InstructorRestController {

    private final InstructorService instructorService;
    private final CourseService courseService;
    private final UserService userService;

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/find")
    public ResponseEntity<InstructorDTO> getInstructorByEmail(@RequestParam(name = "email", defaultValue = "") String email) {
        InstructorDTO instructorByEmail = instructorService.findInstructorByEmail(email);
        return new ResponseEntity<>(instructorByEmail, HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping()
    public ResponseEntity<Page<InstructorDTO>> getInstructorByFirstNameOrLastName(@RequestParam(name = "name", defaultValue = "") String name,
                                                                                  @RequestParam(name = "page", defaultValue = "0") int page,
                                                                                  @RequestParam(name = "size", defaultValue = "5") int size) {


        Page<InstructorDTO> instructorByNameOrLastName = instructorService.findInstructorByNameOrLastName(name, page, size);
        return new ResponseEntity<>(instructorByNameOrLastName, HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/{instructorId}")
    public ResponseEntity<InstructorDTO> deleteInstructorById(@PathVariable Long instructorId) {
        instructorService.removeInstructor(instructorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/all")
    public ResponseEntity<List<InstructorDTO>> findAllInstructors() {
        return new ResponseEntity<>(instructorService.fetchInstructors(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    InstructorDTO saveInstructor(@RequestBody InstructorDTO instructorDTO) {
        User userFoundByEmail = userService.findUserByEmail(instructorDTO.getUser().getEmail());
        if (userFoundByEmail == null) {
            return instructorService.createInstructor(instructorDTO);
        }
        throw new EmailAlreadyTakenException("Email", instructorDTO.getUser().getEmail());
    }
    @PreAuthorize("hasAuthority('Instructor')")
    @PutMapping("/{instructorId}")
    public ResponseEntity<InstructorDTO> updateInstructor(@RequestBody InstructorDTO instructorDTO, @PathVariable Long instructorId, Authentication authentication) {

        instructorDTO.setInstructorId(instructorId);
        InstructorDTO updatedInstructor = instructorService.updateInstructor(instructorDTO, authentication);
        return new ResponseEntity<>(updatedInstructor, HttpStatus.OK);

    }
    @PreAuthorize("hasAnyAuthority('Admin','Instructor')")
    @GetMapping("/{instructorId}/courses")
    public ResponseEntity<Page<CourseDTO>> getCoursesByInstructorId(@PathVariable Long instructorId,
                                                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                                                    @RequestParam(name = "size", defaultValue = "5") int size) {
        Page<CourseDTO> coursesFound = courseService.fetchCoursesForInstructorByInstructorId(instructorId, page, size);
        return new ResponseEntity<>(coursesFound, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/{instructorId}")
    public ResponseEntity<InstructorDTO> getInstructorByInstructorId(@PathVariable() Long instructorId){
        InstructorDTO instructorFoundById = instructorService.findInstructorById(instructorId);
        return new ResponseEntity<>(instructorFoundById,HttpStatus.OK);

    }


}
