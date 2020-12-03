package ca.catterall.vehicleRegistrationDTO.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;

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
        }
        return json;
    }

    public static Integer tryParseInteger(String val){
        try{
            return Integer.parseInt(val);
        }catch(Exception ex){
            return -1;
        }
    }
}
