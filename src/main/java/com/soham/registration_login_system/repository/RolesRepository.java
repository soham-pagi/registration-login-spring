package com.soham.registration_login_system.repository;

import com.soham.registration_login_system.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Long> {
    Role findByName(String role);
}
