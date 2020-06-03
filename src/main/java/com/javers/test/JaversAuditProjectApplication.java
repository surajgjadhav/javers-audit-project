package com.javers.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JaversAuditProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JaversAuditProjectApplication.class, args);
	}

//    @Bean
//    CommandLineRunner initDatabase(UserRepository userRepository) {
//        return args -> {
//        	userRepository.save(new User("suraj", "satara", "suraj@gmail.com",Date.valueOf("1999-03-08")));
//        	userRepository.save(new User("suresh", "sangli", "suresh@gmail.com",Date.valueOf("1970-4-8")));
//        	userRepository.save(new User("ramesh","karad","ramesh@gmail.com",Date.valueOf("1980-5-9")));
//        	userRepository.save(new User("steve","america","steve@gmail.com",Date.valueOf("1955-2-24")));
//        };
//    }
}
