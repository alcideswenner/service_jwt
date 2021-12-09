package com.alcideswenner.service_jwt.services;

import java.util.List;

import com.alcideswenner.service_jwt.domain.Role;
import com.alcideswenner.service_jwt.domain.User;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User saveUser(User user);
    public Role saveRole(Role role);
    public void addRoleToUser(String username,String roleName);
    public User getUser(String username);
    public List<User>getUsers();
}
