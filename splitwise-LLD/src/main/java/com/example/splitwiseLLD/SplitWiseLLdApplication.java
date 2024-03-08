package com.example.splitwiseLLD;

import com.example.splitwiseLLD.models.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SplitWiseLLdApplication {

	public static void main(String[] args) {
//		SpringApplication.run(SplitwiseLldApplication.class, args);
		System.out.println("Anshu");
		User user = new User();
		user.setName("Anshu");
		user.setId(34567890L);

		System.out.println(user.getName() + " " + user.getId());
	}

}
