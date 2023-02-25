package com.aro.javaadmin.user;

import java.util.Optional;

public interface UserService {


    User findUserByEmail(String email);

    User createUser(String email, String password);

    void assignRoleToStudent(String email, String roleName);

}
