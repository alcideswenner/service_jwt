package com.alcideswenner.service_jwt.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import com.alcideswenner.service_jwt.domain.Role;
import com.alcideswenner.service_jwt.domain.User;
import com.alcideswenner.service_jwt.repositories.RoleRepository;
import com.alcideswenner.service_jwt.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public User saveUser(User user) {
        log.info("Salvando usuario "+user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
     log.info("Salvando perfil/funcao "+role.getName());
     return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
    log.info("Adicionando perfil ao usuário "+username+" de "+ roleName);
    User user=userRepository.findByUsername(username);  
    Role role=roleRepository.findByName(roleName);
    user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
      log.info("Listando dados do usuário "+username);
      return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Listando todos os dados ");
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username);
        if(user==null){
            log.info("User nao encontrado");
            throw new UsernameNotFoundException("Usuário nao encontrado");
        }else{
            log.info("User encontrado");
        }
        Collection<SimpleGrantedAuthority>authorities=new ArrayList<>();
        user.getRoles().forEach(role->{
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
    
}
