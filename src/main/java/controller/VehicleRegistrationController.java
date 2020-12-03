package controller;


import ca.catterall.vehicleRegistrationDTO.Utils.Converters;
import model.VehicleRegistration;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repo.VehicleRegistrationRepository;

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
        if(!response.equalsIgnoreCase("")){
            //Error occurred in conversion
            return new ResponseEntity<String>("Unable to process your request, please try later", HttpStatus.INTERNAL_SERVER_ERROR);
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

        //Check fields are not null or empty
        //return new ResponseEntity<String>("Missing Field Required: ", HttpStatus.BAD_REQUEST);

        //Returns  the Id of the New Registration.
        return new ResponseEntity<String>("Return Id of New Listing", HttpStatus.OK);
    }

    @DeleteMapping(value="/remove")
    public @ResponseBody  ResponseEntity<String> deleteRegistration(@RequestParam("id") String registrationId){
        Integer id = Converters.tryParseInteger(registrationId);
        if(id != -1) {
            vehicleRegistrationRepository.deleteById(id);
            //Doesn't return anything more than the Status Code
            return new ResponseEntity<String>(HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("Invalid ID: " + registrationId, HttpStatus.BAD_REQUEST);
        }
    }
}
