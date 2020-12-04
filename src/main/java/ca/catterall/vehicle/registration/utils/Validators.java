package ca.catterall.vehicle.registration.utils;

import java.util.ArrayList;
import java.util.List;

public class Validators {

    public static String validateRegistration(String registrationId,String carManufacture,
                                       String carModel, String dateOfRegistration,
                                       String yearOfManufacture) {

        List<String> errors = new ArrayList<String>(){{
            add(Consts.VALIDATION_FAILURE_TXT);
        }};

        registrationId = Converters.trimFormatCharacters(registrationId);
        if(registrationId == null || registrationId.equalsIgnoreCase(Consts.STRING_EMPTY))
            errors.add(Consts.VALIDATION_FAILURE_REGISTRATION_ID);

        carManufacture = Converters.trimFormatCharacters(carManufacture);
        if(carManufacture == null || carManufacture.equalsIgnoreCase(Consts.STRING_EMPTY))
            errors.add(Consts.VALIDATION_FAILURE_CAR_MANUFACTURER);

        carModel = Converters.trimFormatCharacters(carModel);
        if(carModel == null || carModel.equalsIgnoreCase(Consts.STRING_EMPTY))
            errors.add(Consts.VALIDATION_FAILURE_CAR_MODEL);

        dateOfRegistration = Converters.trimFormatCharacters(dateOfRegistration);
        if(dateOfRegistration == null  || dateOfRegistration.equalsIgnoreCase(Consts.STRING_EMPTY)
                || Converters.parseStringToDate(dateOfRegistration) == null)
            errors.add(Consts.VALIDATION_FAILURE_DATE_OF_REG);

        yearOfManufacture = Converters.trimFormatCharacters(yearOfManufacture);
        if(yearOfManufacture == null  || yearOfManufacture.equalsIgnoreCase(Consts.STRING_EMPTY)
                || Converters.tryParseInteger(yearOfManufacture) == null)
            errors.add(Consts.VALIDATION_FAILURE_YEAR_OF_MANU);

        if(errors.size() > 1) {
            return String.join(", ", errors.stream().toArray(String[]::new));
        }
        return Consts.STRING_EMPTY;
    }
}
