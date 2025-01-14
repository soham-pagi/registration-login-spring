package com.soham.registration_login_system.service;

import com.soham.registration_login_system.dto.UserDto;
import com.soham.registration_login_system.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();
}
