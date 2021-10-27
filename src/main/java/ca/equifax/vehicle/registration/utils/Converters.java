package ca.equifax.vehicle.registration.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.slf4j.LoggerFactory.getLogger;

public class Converters {

    static Logger logger = getLogger(Converters.class);

    public static String convertObjToJson(Object obj){
        String json = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(obj);
        }
        catch(Exception ex){
            logger.error("Unable to convert 'Object' to JSON - Error: " + ex.getMessage());
            json = Consts.FAILED_TO_CONVERT_OBJ_TO_JSON;
        }
        return json;
    }

    public static Integer tryParseInteger(String val){
        try{
            val = trimFormatCharacters(val);
            return Integer.parseInt(val);
        }catch(Exception ex){
            logger.error("Unable to parse value:  " + val + " Error: " + ex.getMessage());
            return null;
        }
    }

    public static Date parseStringToDate(String date){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(Consts.CONVERTERS_DATE_FORMAT);
            sdf.setLenient(false);
            date = trimFormatCharacters(date);
            return sdf.parse(date);
        } catch(Exception ex){
            logger.error("Unable to parse date:  " + date + " Error: " + ex.getMessage());
            return null;
        }
    }

    public static String parseDateToString(Date date){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(Consts.CONVERTERS_DATE_FORMAT);
            return sdf.format(date);
        } catch(Exception ex){
            logger.error("Unable to parse date:  " + date + " Error: " + ex.getMessage());
            return null;
        }
    }

    public static String trimFormatCharacters(String str){
        return str.replaceAll(Consts.CONVERTERS_FORMAT_CHAR_REGX, "");
    }
}
