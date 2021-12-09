package com.alcideswenner.service_jwt;

import java.util.ArrayList;

import com.alcideswenner.service_jwt.domain.Role;
import com.alcideswenner.service_jwt.domain.User;
import com.alcideswenner.service_jwt.services.UserService;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ServiceJwtApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceJwtApplication.class, args);
	}
/*  InitializingBean init(){
	return ()->{
    
	};
 } */

 @Bean
 PasswordEncoder passwordEncoder(){
	 return new BCryptPasswordEncoder();
 }
@Bean
 CommandLineRunner init2(UserService userService){
	return args->{
	  userService.saveRole(new Role(null, "ROLE_USER"));
	  userService.saveRole(new Role(null, "ROLE_MANAGER"));
	  userService.saveRole(new Role(null, "ROLE_ADMIN"));
	  userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

	  userService.saveUser(new User(null, "Wenner", "alcideswenner", "1234", new ArrayList<>()));
	  userService.saveUser(new User(null, "Alcides", "alcides", "1234", new ArrayList<>()));
	  userService.saveUser(new User(null, "Jacque", "jacque", "1234", new ArrayList<>()));
	  userService.saveUser(new User(null, "Osvaldo", "osvaldo", "1234", new ArrayList<>()));

	  userService.addRoleToUser("alcideswenner", "ROLE_USER");
	  userService.addRoleToUser("alcides", "ROLE_SUPER_ADMIN");
	  userService.addRoleToUser("jacque", "ROLE_USER");
	  userService.addRoleToUser("osvaldo", "ROLE_ADMIN");
	  userService.addRoleToUser("jacque", "ROLE_ADMIN");
	  userService.addRoleToUser("alcideswenner", "ROLE_MANAGER");
	};
 }

}
