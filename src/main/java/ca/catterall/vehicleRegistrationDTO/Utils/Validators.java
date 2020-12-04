package ca.catterall.vehicleRegistrationDTO.Utils;

import java.util.ArrayList;
import java.util.List;

public class Validators {

    public static String validateRegistration(String registrationId,String carManufacture,
                                       String carModel, String dateOfRegistration,
                                       String yearOfManufacture) {

        List<String> errors = new ArrayList<String>(){{
            add("Missing or invalid field(s) ");
        }};

        registrationId = registrationId.replaceAll("[\\n\\t ]", "");
        if(registrationId == null || registrationId.equalsIgnoreCase(Consts.STRING_EMPTY))
            errors.add("Registration ID");
        carManufacture = carManufacture.replaceAll("[\\n\\t ]", "");
        if(carManufacture == null || carManufacture.equalsIgnoreCase(Consts.STRING_EMPTY))
            errors.add("Car Manufacturer");
        carModel = carModel.replaceAll("[\\n\\t ]", "");
        if(carModel == null || carModel.equalsIgnoreCase(Consts.STRING_EMPTY))
            errors.add("Car Model");
        dateOfRegistration = dateOfRegistration.replaceAll("[\\n\\t ]", "");
        if(dateOfRegistration == null  || dateOfRegistration.equalsIgnoreCase(Consts.STRING_EMPTY)
                || Converters.parseStringToDate(dateOfRegistration) == null)
            errors.add("Date of Registration");
        yearOfManufacture = yearOfManufacture.replaceAll("[\\n\\t ]", "");
        if(yearOfManufacture == null  || yearOfManufacture.equalsIgnoreCase(Consts.STRING_EMPTY)
                || Converters.tryParseInteger(yearOfManufacture) == null)
            errors.add("Year of Manufacture");

        if(errors.size() > 1) {
            return String.join(", ", errors.stream().toArray(String[]::new));
        }
        return Consts.STRING_EMPTY;
    }
}
