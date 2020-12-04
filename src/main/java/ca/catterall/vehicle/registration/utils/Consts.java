package ca.catterall.vehicle.registration.utils;

public class Consts {

    public final static String FAILED_TO_CONVERT_OBJ_TO_JSON = "Failed to convert Obj";
    public static final String STRING_EMPTY = "";

    public final static String LISTING_SUCCESS_RESULT_MESSAGE = "{\"registrations\":%s }";
    public final static String LISTING_INTERNAL_ERROR_MESSAGE = "{\"message\": \"Unable to process your request, please try later\"}";
    public final static String INVALID_REGISTRATION_MESSAGE = "{\"message\": \"%s\"}";
    public final static String SUCCESS_REGISTRATION_MESSAGE = "{\"id\": \"%s\"}";
    public final static String DELETE_ID_NOT_FOUND_MESSAGE = "{\"message\": \"ID Not Found: %s\"}";
    public final static String DELETE_INVALID_ID_MESSAGE = "{\"message\": \"Invalid ID: %s\"}";

    public final static String CONVERTERS_DATE_FORMAT = "dd-MM-yyyy";
    public final static String CONVERTERS_FORMAT_CHAR_REGX = "[\\n\\t ]";

    public static final String VALIDATION_FAILURE_TXT = "Missing or invalid field(s) ";
    public static final String VALIDATION_FAILURE_REGISTRATION_ID = "Registration ID";
    public static final String VALIDATION_FAILURE_CAR_MANUFACTURER = "Car Manufacturer";
    public static final String VALIDATION_FAILURE__CAR_MODEL = "Car Model";
    public static final String VALIDATION_FAILURE_DATE_OF_REG = "Date of Registration";
    public static final String VALIDATION_FAILURE_YEAR_OF_MANU = "Year of Manufacture";
}
