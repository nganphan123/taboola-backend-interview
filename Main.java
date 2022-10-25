import java.util.Arrays;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String json = "{ \"debug\" : \"on\", \"window\" : { \"title\" : \"sample\", \"size\" : 500 } }";
        System.out.println(JSONParser.parse(json));
        json = "{ \"debug\" : \"on\", \"window\" : { \"title\" : \"sample\", \"size\" : 500 }, \"number\" : 600, \"decimal\" : 1.222, \"bool\" : true }";
        Map<String,Object> m = JSONParser.parse(json);
        System.out.println(m.get("decimal").getClass());
        System.out.println(m.get("bool").getClass());
        System.out.println(m.get("window").getClass());
    }
}
