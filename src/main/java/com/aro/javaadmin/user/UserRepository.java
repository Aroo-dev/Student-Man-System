package com.aro.javaadmin.user;

import com.aro.javaadmin.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);

}
