package venkat.cars.model;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import venkat.cars.model.domain.Role;
import venkat.cars.model.domain.User;
import venkat.cars.model.service.UserService;

@SpringBootApplication
@EnableSwagger2
public class ModelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModelApplication.class, args);
	}
	
	 @Bean
	   public Docket productApi() {
	      return new Docket(DocumentationType.SWAGGER_2).select()
	         .apis(RequestHandlerSelectors.basePackage("venkat.cars.model")).build();
	   }
	 @Bean
	 PasswordEncoder passwordEncoder() {
		 return new  BCryptPasswordEncoder();
	 }
	 
	 
	 @Bean
	 CommandLineRunner run(UserService userService) {
		 return args -> {
			 userService.saveRole(new Role("ROLE_USER"));
			 userService.saveRole(new Role("ROLE_MANAGER"));
			 userService.saveRole(new Role("ROLE_ADMIN"));
			 userService.saveRole(new Role("ROLE_SUPER_ADMIN"));
			 
			userService.saveUser(new User("venkat rao","venkat","1234",new ArrayList<>()));
			userService.saveUser(new User("nithin sai ","nithin","1234",new ArrayList<>()));
			userService.saveUser(new User("pavan kumar","pavan","1234",new ArrayList<>()));
			userService.saveUser(new User("harsha rao","harsha","1234",new ArrayList<>()));
			
			userService.addRoletoUser("venkat", "ROLE_USER");
			userService.addRoletoUser("venkat", "ROLE_MANAGER");
			userService.addRoletoUser("nithin", "ROLE_MANAGER");
			userService.addRoletoUser("nithin", "ROLE_ADMIN");
			userService.addRoletoUser("pavan", "ROLE_USER");
			userService.addRoletoUser("pavan", "ROLE_SUPER_ADMIN");
			userService.addRoletoUser("pavan", "ROLE_ADMIN");
		 };
	 }

}
