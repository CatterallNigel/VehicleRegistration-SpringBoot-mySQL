package ca.equifax.vehicle.registration.intergration;

import ca.equifax.vehicle.registration.VehicleRegistrationApplication;
import ca.equifax.vehicle.registration.controller.VehicleRegistrationController;
import ca.equifax.vehicle.registration.repo.VehicleRegistrationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { VehicleRegistrationApplication.class })
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VehicleRegistrationIntegrationsTestIT {

    @Autowired
    VehicleRegistrationController vehicleRegistrationController;

    @Autowired
    VehicleRegistrationRepository vehicleRegistrationRepository;

    @Test
    public void isIntegrationTest(){
        assertThat(200).isEqualTo(200);
    }

}
