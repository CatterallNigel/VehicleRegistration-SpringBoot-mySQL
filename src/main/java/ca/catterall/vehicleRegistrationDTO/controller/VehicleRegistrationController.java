package ca.catterall.vehicleRegistrationDTO.controller;


import ca.catterall.vehicleRegistrationDTO.Utils.Consts;
import ca.catterall.vehicleRegistrationDTO.Utils.Converters;
import ca.catterall.vehicleRegistrationDTO.Utils.Validators;
import ca.catterall.vehicleRegistrationDTO.model.VehicleRegistration;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ca.catterall.vehicleRegistrationDTO.repo.VehicleRegistrationRepository;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = {"*"})
public class VehicleRegistrationController {

    Logger logger = getLogger(VehicleRegistrationController.class);
    @Autowired
    VehicleRegistrationRepository vehicleRegistrationRepository;


    @GetMapping(value="/list")
    public @ResponseBody ResponseEntity<String> getVehicleRegistrations(){
        List<VehicleRegistration> listing = new ArrayList<>();
        vehicleRegistrationRepository.findAll().forEach( r -> {
            listing.add(r);
        });
        String response = Converters.convertObjToJson(listing);
        if(response.equalsIgnoreCase(Consts.FAILED_TO_CONVERT_OBJ_TO_JSON)){
            //Error occurred in conversion
            String message = "{\"message\": \"Unable to process your request, please try later\"}";
            return new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //Returns a List of Registrations
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @PostMapping(value="/save")
    public @ResponseBody  ResponseEntity<String> addRegistration(@RequestParam("registrationId") String registrationId,
                                                                 @RequestParam("carManufacture") String carManufacture,
                                                                 @RequestParam("carModel") String carModel,
                                                                 @RequestParam("dateOfRegistration") String dateOfRegistration,
                                                                 @RequestParam("yearOfManufacture") String yearOfManufacture){

        //Check fields are not null or empty, and dates are valid;
        String validation = Validators.validateRegistration(registrationId,carManufacture,carModel
                ,dateOfRegistration,yearOfManufacture);
        if(!validation.equalsIgnoreCase(Consts.STRING_EMPTY)) {
            String message = "{\"message\": \"" + validation + "\"}";
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        }

        VehicleRegistration vr = new VehicleRegistration();
        vr.setRegistrationId(registrationId);
        vr.setCarManufacturer(carManufacture);
        vr.setCarModel(carModel);
        vr.setDateOfRegistration(Converters.parseStringToDate(dateOfRegistration));
        vr.setYearOfManufacture(Converters.tryParseInteger(yearOfManufacture));
        vehicleRegistrationRepository.save(vr);

        //Returns  the Id of the New Registration.
        String response = "{\"id\":" + vr.getId() + "\"}";
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @DeleteMapping(value="/remove")
    public @ResponseBody  ResponseEntity<String> deleteRegistration(@RequestParam("id") String registrationId){
        Integer id = Converters.tryParseInteger(registrationId);
        if(id != null) {
            vehicleRegistrationRepository.deleteById(id);
            //Doesn't return anything more than the Status Code
            return new ResponseEntity<String>(HttpStatus.OK);
        }else {
            String message = "{\"message\": \"Invalid ID: " + registrationId + "\'}";
            return new ResponseEntity<String>( message, HttpStatus.BAD_REQUEST);
        }
    }
}
