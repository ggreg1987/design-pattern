package br.com.dio;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Person API", version = "1.0",
		description = "Bootcamp by DIO",
		contact = @Contact(name = "Gabriel Gregorio",
				email = "gr3g1987@gmail.com",url = "http://github.com/ggreg1987")))
public class MsPersonApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPersonApplication.class, args);
	}

}
