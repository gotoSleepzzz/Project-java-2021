package view;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.LogManager;

public class Province {

    // make singleton
    private static Province instance = null;
    static org.apache.logging.log4j.Logger logger = LogManager.getLogger(Province.class);
    JSONParser jsonParser = new JSONParser();
    JSONArray provinces;
    JSONArray districts;
    JSONArray communes;

    public static Province getInstance() {
        if (instance == null) {
            try {
                instance = new Province();
            } catch (IOException | ParseException e) {
                logger.error(e);
            }
        }
        return instance;
    }

    public Province() throws IOException, ParseException {
        FileReader reader = null;
        try {
            reader = new FileReader("D:\\HK1_YEAR3\\Java\\Project-java-2021\\db.json");
        } catch (FileNotFoundException e) {
            logger.error(e);
        }
        Object obj = jsonParser.parse(reader);
        JSONObject text = (JSONObject) obj;
        provinces = (JSONArray) text.get("province");
        districts = (JSONArray) text.get("district");
        communes = (JSONArray) text.get("commune");
    }

    public List getListOfProvines() {
        return provinces.stream().map(o -> ((JSONObject)(o)).get("name")).toList();
    }

    public List getListOfDistricts(String name) {
        List x = provinces.stream().filter(o -> ((JSONObject)(o)).get("name").equals(name)).toList();
        String idProvince = ((JSONObject)(x.get(0))).get("idProvince").toString();
        return districts.stream().filter(o -> ((JSONObject)(o)).get("idProvince").equals(idProvince)).map(o -> ((JSONObject)(o)).get("name")).toList();
    }

    public List getListOfCommunes(String name) {
        List x = districts.stream().filter(o -> ((JSONObject)(o)).get("name").equals(name)).toList();
        String idDistrict = ((JSONObject)(x.get(0))).get("idDistrict").toString();
        return communes.stream().filter(o -> ((JSONObject)(o)).get("idDistrict").equals(idDistrict)).map(o -> ((JSONObject)(o)).get("name")).toList();
    }

}
