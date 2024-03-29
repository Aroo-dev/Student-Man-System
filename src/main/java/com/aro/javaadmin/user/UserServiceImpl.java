package com.aro.javaadmin.user;

import com.aro.javaadmin.role.Role;
import com.aro.javaadmin.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    public static final String ROLE_NOT_FOUND = "Role not found";
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
    
    @Override
    @Transactional
    public User createUser(String email, String password, String confirmPassword) {
        //TODO generate random password by default, and then send it to user so he/she can change it
        String encodedPassword = passwordEncoder.encode(password);
        return userRepository.save(new User(email, encodedPassword));
    }

    @Override
    @Transactional
    public void assignRoleToUser(String email, String roleName) {
        User user = userRepository.findUserByEmail(email);

        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            throw new NullPointerException(ROLE_NOT_FOUND);
        }
        user.addRoleToUser(role);
        userRepository.save(user);
    }
}
