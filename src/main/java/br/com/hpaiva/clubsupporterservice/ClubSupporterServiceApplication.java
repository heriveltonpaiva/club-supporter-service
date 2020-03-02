package br.com.hpaiva.clubsupporterservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ClubSupporterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClubSupporterServiceApplication.class, args);
	}

}
