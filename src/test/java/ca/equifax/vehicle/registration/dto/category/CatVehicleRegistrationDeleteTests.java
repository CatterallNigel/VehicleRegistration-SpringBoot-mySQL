package ca.equifax.vehicle.registration.dto.category;

import ca.equifax.vehicle.registration.TestCategories;
import ca.equifax.vehicle.registration.controller.VehicleRegistrationController;
import ca.equifax.vehicle.registration.repo.VehicleRegistrationRepository;
import org.junit.Test;
import org.junit.experimental.categories.Category;
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
@Category(TestCategories.class)
public class CatVehicleRegistrationDeleteTests {

    @InjectMocks
    VehicleRegistrationController vehicleRegistrationController;

    @Mock
    VehicleRegistrationRepository vehicleRegistrationRepository;

    @Test
    @Category(TestCategories.RegistrationDeleteTest.class)
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
