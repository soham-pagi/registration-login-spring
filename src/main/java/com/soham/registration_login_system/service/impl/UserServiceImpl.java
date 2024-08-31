package com.soham.registration_login_system.service.impl;

import com.soham.registration_login_system.dto.UserDto;
import com.soham.registration_login_system.entity.Role;
import com.soham.registration_login_system.entity.User;
import com.soham.registration_login_system.repository.RolesRepository;
import com.soham.registration_login_system.repository.UserRepository;
import com.soham.registration_login_system.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RolesRepository rolesRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = rolesRepository.findByName("ROLE_ADMIN");

        if (role == null) {
            role = checkRoleExist();
        }

        user.setRoles(Arrays.asList(role));

        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(
                user -> new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword())
        ).collect(Collectors.toList());
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");

        return rolesRepository.save(role);
    }
}
