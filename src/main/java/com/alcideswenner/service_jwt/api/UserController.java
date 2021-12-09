package com.alcideswenner.service_jwt.api;

import java.net.URI;
import java.util.List;
import com.alcideswenner.service_jwt.domain.Role;
import com.alcideswenner.service_jwt.domain.User;
import com.alcideswenner.service_jwt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.Data;
import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api")
public class UserController {
  @Autowired
  private final UserService userService;

  @GetMapping("/users")
  public ResponseEntity<List<User>>getUsers() {
      return ResponseEntity.ok().body(userService.getUsers());
  }

  @PostMapping("/user/save")
  public ResponseEntity<User>saveUser(@RequestBody User user){
  URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
  return ResponseEntity.created(uri).body(userService.saveUser(user));
  }

  @PostMapping("/role/save")
  public ResponseEntity<Role>saveRole(@RequestBody Role role){
  URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
  return ResponseEntity.created(uri).body(userService.saveRole(role));
  }
  @PostMapping("/role/addToUser")
  public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form){
  userService.addRoleToUser(form.getUsername(),form.getRoleName());
  return ResponseEntity.ok().build();
  }
  @Data
  class RoleToUserForm{
      private String username;
      private String roleName;
  }
}
