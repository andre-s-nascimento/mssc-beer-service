package guru.springframework.msscbeerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"guru.springframework.msscbeerservice.MsscBeerServiceApplication"})
public class MsscBeerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsscBeerServiceApplication.class, args);
	}

}
