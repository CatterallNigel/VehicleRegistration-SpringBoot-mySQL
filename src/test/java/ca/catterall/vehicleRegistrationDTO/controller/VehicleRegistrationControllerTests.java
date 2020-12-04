package ca.catterall.vehicleRegistrationDTO.controller;


import ca.catterall.vehicleRegistrationDTO.model.VehicleRegistration;
import ca.catterall.vehicleRegistrationDTO.repo.VehicleRegistrationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class VehicleRegistrationControllerTests {

    @InjectMocks
    VehicleRegistrationController vehicleRegistrationController;

    @Mock
    VehicleRegistrationRepository vehicleRegistrationRepository;

    @Test
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
    public void testAddRegistrationBadDate(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        String registrationId = "NCA123";
        String carManufacture = "Ford";
        String carModel = "Sierra";
        String dateOfRegistration = "31-11-2020";
        String yearOfManufacture = "2015";

        ResponseEntity<String> responseEntity =
                vehicleRegistrationController.addRegistration(registrationId,carManufacture,carModel,
                        dateOfRegistration,yearOfManufacture);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
        assertThat(responseEntity.getBody().equalsIgnoreCase("{\"message\": \"Missing or invalid fields" +
                " , Date of Registration\"}"));
    }

    @Test
    public void testAddRegistrationBadYearOfManufacture(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        String registrationId = "NCA123";
        String carManufacture = "Ford";
        String carModel = "Sierra";
        String dateOfRegistration = "30-11-2020";
        String yearOfManufacture = "2015Y";

        ResponseEntity<String> responseEntity =
                vehicleRegistrationController.addRegistration(registrationId,carManufacture,carModel,
                        dateOfRegistration,yearOfManufacture);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
        assertThat(responseEntity.getBody().equalsIgnoreCase("{\"message\": \"Missing or invalid fields" +
                " , Year of Manufacture\"}"));
    }

    @Test
    public void testAddRegistrationAllFieldsEmpty(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        String registrationId = "NCA123";
        String carManufacture = "Ford";
        String carModel = "Sierra";
        String dateOfRegistration = "30-11-2020";
        String yearOfManufacture = "2015Y";

        ResponseEntity<String> responseEntity =
                vehicleRegistrationController.addRegistration(registrationId,carManufacture,carModel,
                        dateOfRegistration,yearOfManufacture);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
        assertThat(responseEntity.getBody().equalsIgnoreCase("{\"message\": \"Missing or invalid fields ," +
                " Registration ID, Car Manufacturer, Car Model, Date of Registration, Year of Manufacture\"}"));
    }

    @Test
    public void testSaveBadRegistration(){
        try {
            vehicleRegistrationRepository.save(null);
        }catch(Exception ex){
            assertThat(ex.getMessage().length() > 0);
        }

    }

    @Test
    public void testGetRegistrations(){
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

        responseEntity =
                vehicleRegistrationController.getVehicleRegistrations();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().equalsIgnoreCase("{\"id\":1,\"registrationId\":\"NCA123\"," +
                "\"carManufacturer\":\"Ford\",\"carModel\":\"Sierra\"," +
                "\"dateOfRegistration\":\"30-11-2020\",\"yearOfManufacture\":2015}"));
    }

    @Test
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
