package com.example.blog;
import com.example.blog.Repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


@SpringBootApplication
public class BlogApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}


	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}


	@Override
	public void run(String... args) throws Exception {

//		try {
//			Role role = new Role();
//			role.setId(AppConstants.ADMIN_USER);
//			role.setName("admin_user");
//			Role role1 = new Role();
//			role1.setId(AppConstants.NORMAL_USER);
//			role1.setName("normal_user");
//			List<Role> roles = List.of(role, role1);
//			List<Role> result = roleRepository.saveAll(roles);
//			result.forEach(r -> {
//				System.out.println(r.getName());
//			});
//		}
//		catch (HibernateException e){
//			e.printStackTrace();
//		}
	}
}
