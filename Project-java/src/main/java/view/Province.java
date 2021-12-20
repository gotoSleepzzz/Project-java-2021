package view;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Province {
    JSONParser jsonParser = new JSONParser();
    JSONArray provinces;
    JSONArray districts;
    JSONArray communes;


    public Province() throws IOException, ParseException {
        FileReader reader = null;
        try {
            // get pwd of the project
            String path = System.getProperty("user.dir");
            System.out.println(path);
            reader = new FileReader(path + "/Project-java/src/main/java/resources/db.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

        var x = provinces.stream().filter(o -> ((JSONObject)(o)).get("name").equals(name)).toList();
        String idProvince = ((JSONObject)(x.get(0))).get("idProvince").toString();
        return districts.stream().filter(o -> ((JSONObject)(o)).get("idProvince").equals(idProvince)).map(o -> ((JSONObject)(o)).get("name")).toList();
    }

    public List getListOfCommunes(String name) {
        var x = districts.stream().filter(o -> ((JSONObject)(o)).get("name").equals(name)).toList();
        var idDistrict = ((JSONObject)(x.get(0))).get("idDistrict").toString();

        return communes.stream().filter(o -> ((JSONObject)(o)).get("idDistrict").equals(idDistrict)).map(o -> ((JSONObject)(o)).get("name")).toList();
    }

}
