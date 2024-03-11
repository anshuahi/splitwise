package com.example.splitwiseLLD;

import com.example.splitwiseLLD.dtos.SettleUpGroupRequestDTO;
import com.example.splitwiseLLD.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SplitWiseLLdApplication implements CommandLineRunner {

//	@Autowired
//	private GroupCon
	public static void main(String[] args) {
		SpringApplication.run(SplitWiseLLdApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SettleUpGroupRequestDTO requestDTO = new SettleUpGroupRequestDTO();
		requestDTO.setGroupId(1L);

	}
}
