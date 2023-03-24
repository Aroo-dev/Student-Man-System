package com.aro.javaadmin.user;

public interface UserService {


    User findUserByEmail(String email);

    User createUser(String email, String password, String confirmedPassword);

    void assignRoleToUser(String email, String roleName);

}
