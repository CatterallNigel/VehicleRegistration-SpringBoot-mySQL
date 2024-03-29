package ca.equifax.vehicle.registration.data;


import ca.equifax.vehicle.registration.utils.Converters;
import ca.equifax.vehicle.registration.model.VehicleRegistrationDTO;
import ca.equifax.vehicle.registration.repo.VehicleRegistrationRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

import static org.slf4j.LoggerFactory.getLogger;

@Configuration
public class DummyData {

    Logger logger = getLogger(DummyData.class);

    @Autowired
    VehicleRegistrationRepository vehicleRegistrationRepository;

    public static String[] registrationId = {"ABC123", "DEF456","GHI789"};
    public static String[] carManufacturers = {"Ford", "GMC", "Toyota"};
    public static String[] carModel = {"Sierra", "Ka", "Explorer"};

    public static String[] dateOfRegistration = {"30-11-1980", "01-01-2010", "14-02-2014"};
    public static int[] yearOfManufacture = {1979, 2009, 2013};

    @Bean("InitData")
    public void populateVehicleRegistrations(){

        for(int v = 0 ; v < 3 ; v++){
            try {
                VehicleRegistrationDTO vr = new VehicleRegistrationDTO();
                vr.setRegistrationId(registrationId[v]);
                vr.setCarManufacturer(carManufacturers[v]);
                vr.setCarModel(carModel[v]);
                Date dateRegistered = Converters.parseStringToDate(dateOfRegistration[v]);
                int yearManufactured = yearOfManufacture[v];
                vr.setYearOfManufacture(yearManufactured);
                if(dateRegistered != null ){
                    vr.setDateOfRegistration(dateRegistered);
                    vehicleRegistrationRepository.save(vr);
                    logger.info("Vehicle Registration UniqueId: " + vr.getId());
                }else{
                    logger.error("Unable to parse 'Date Registered' " + dateOfRegistration[v]);
                }

            }catch(Exception ex){
                logger.error("Unable to create Registration - Error:  " +  ex.getMessage());
            }
        }
    }
}
