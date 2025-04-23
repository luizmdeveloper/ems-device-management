package br.com.luizmariodev.ems.device.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class EmsDeviceManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmsDeviceManagementApplication.class, args);
	}

}
