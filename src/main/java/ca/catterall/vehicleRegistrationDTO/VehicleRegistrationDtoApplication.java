package ca.catterall.vehicleRegistrationDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class VehicleRegistrationDtoApplication {

	@Autowired
	ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(VehicleRegistrationDtoApplication.class, args);
	}

	@PostConstruct
	public void loadData(){
		context.getBean("InitData");
	}

}
