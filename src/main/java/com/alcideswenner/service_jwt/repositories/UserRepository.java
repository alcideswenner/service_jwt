package com.alcideswenner.service_jwt.repositories;

import com.alcideswenner.service_jwt.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{
    User findByUsername(String username);
}
