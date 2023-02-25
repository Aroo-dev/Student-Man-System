package com.aro.javaadmin.user;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserRestController {

        private final UserService userService;

        @PreAuthorize("hasAuthority('Admin')")
        @GetMapping("/users")
        public boolean checkIfEmailExists(@RequestParam(name = "email",defaultValue = "") String email) {
            return userService.findUserByEmail(email) != null;
        }
    }

