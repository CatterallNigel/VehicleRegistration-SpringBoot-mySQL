package ca.equifax.vehicle.registration.dto.tag;


import ca.equifax.vehicle.registration.annotations.RequireFastTagAnnotations;
import ca.equifax.vehicle.registration.controller.VehicleRegistrationController;
import ca.equifax.vehicle.registration.repo.VehicleRegistrationRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TagVehicleRegistrationAddDeleteTests {

    @InjectMocks
    VehicleRegistrationController vehicleRegistrationController;

    @Mock
    VehicleRegistrationRepository vehicleRegistrationRepository;

    @Test
    @RequireFastTagAnnotations
    @DisplayName("Test that a NEW Registration is added")
    public void testAddSuccessfulRegistration(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        String registrationId = "NCA123";
        String carManufacture = "Ford";
        String carModel = "Sierra";
        String dateOfRegistration = "30-11-2020";
        String yearOfManufacture = "2015";

        ResponseEntity<String> responseEntity =
                vehicleRegistrationController.addRegistration(registrationId,carManufacture,carModel,
                        dateOfRegistration,yearOfManufacture);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().equalsIgnoreCase("{\"id\":\"1\"}"));
    }

    @Test
    @Tag("RegistrationDeleteTest")
    @DisplayName("Test that a Registration is Deleted")
    public void testDeleteRegistration(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        String registrationId = "NCA123";
        String carManufacture = "Ford";
        String carModel = "Sierra";
        String dateOfRegistration = "30-11-2020";
        String yearOfManufacture = "2015";

        ResponseEntity<String> responseEntity =
                vehicleRegistrationController.addRegistration(registrationId,carManufacture,carModel,
                        dateOfRegistration,yearOfManufacture);

        responseEntity =
                vehicleRegistrationController.deleteRegistration("1");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);

        responseEntity =
                vehicleRegistrationController.getVehicleRegistrations();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().equalsIgnoreCase("{}"));

    }
}

