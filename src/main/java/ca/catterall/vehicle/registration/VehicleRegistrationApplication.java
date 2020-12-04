package ca.catterall.vehicle.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class VehicleRegistrationApplication {

	@Autowired
	ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(VehicleRegistrationApplication.class, args);
	}

	@PostConstruct
	public void loadData(){
		context.getBean("InitData");
	}

}
