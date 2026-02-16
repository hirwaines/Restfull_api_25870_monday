package auca.ac.rw.question5_TaskManagement._api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "auca.ac.rw.question5_TaskManagement")
public class Question5TaskManagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Question5TaskManagementApiApplication.class, args);
	}

}
