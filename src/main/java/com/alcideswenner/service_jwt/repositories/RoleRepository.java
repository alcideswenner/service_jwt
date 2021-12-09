package com.alcideswenner.service_jwt.repositories;

import com.alcideswenner.service_jwt.domain.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long>{   
    Role findByName(String name);
}
