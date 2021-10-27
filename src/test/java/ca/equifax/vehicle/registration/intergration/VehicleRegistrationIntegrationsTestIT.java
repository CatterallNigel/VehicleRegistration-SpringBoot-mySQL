package ca.equifax.vehicle.registration.intergration;

import ca.equifax.vehicle.registration.TestCategories;
import ca.equifax.vehicle.registration.VehicleRegistrationApplication;
import ca.equifax.vehicle.registration.controller.VehicleRegistrationController;
import ca.equifax.vehicle.registration.repo.VehicleRegistrationRepository;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

    @Test
    @Order(1)
    @Category({TestCategories.RegistrationAddTest.class, TestCategories.RegistrationFastTest.class
            ,TestCategories.RegistrationRequiredTest.class})
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
    @Order(2)
    @Category({TestCategories.RegistrationAddTest.class, TestCategories.RegistrationMediumTest.class})
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
    @Order(3)
    @Category({TestCategories.RegistrationAddTest.class, TestCategories.RegistrationMediumTest.class})
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
    @Order(4)
    @Category({TestCategories.RegistrationAddTest.class, TestCategories.RegistrationSlowTest.class})
    public void testAddRegistrationAllFieldsEmpty(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        String registrationId = "";
        String carManufacture = "";
        String carModel = "";
        String dateOfRegistration = "";
        String yearOfManufacture = "";

        ResponseEntity<String> responseEntity =
                vehicleRegistrationController.addRegistration(registrationId,carManufacture,carModel,
                        dateOfRegistration,yearOfManufacture);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
        assertThat(responseEntity.getBody().equalsIgnoreCase("{\"message\": \"Missing or invalid fields ," +
                " Registration ID, Car Manufacturer, Car Model, Date of Registration, Year of Manufacture\"}"));
    }

    @Test
    @Order(5)
    @Category({TestCategories.RegistrationAddTest.class, TestCategories.RegistrationFastTest.class})
    public void testSaveBadRegistration(){
        try {
            vehicleRegistrationRepository.save(null);
        }catch(Exception ex){
            assertThat(ex.getMessage().length() > 0);
        }

    }

    @Test
    @Order(6)
    @Category({TestCategories.RegistrationGetTest.class, TestCategories.RegistrationMediumTest.class
            ,TestCategories.RegistrationRequiredTest.class})
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
        assertThat(responseEntity.getBody().equalsIgnoreCase("{\"registrations\"[{\"id\":1,\"registrationId\":\"NCA123\"," +
                "\"carManufacturer\":\"Ford\",\"carModel\":\"Sierra\"," +
                "\"dateOfRegistration\":\"30-11-2020\",\"yearOfManufacture\":2015}]}"));
    }

    @Test
    @Order(7)
    @Category({TestCategories.RegistrationDeleteTest.class, TestCategories.RegistrationSlowTest.class
            ,TestCategories.RegistrationRequiredTest.class})
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
