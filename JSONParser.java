import java.rmi.ConnectIOException;
import java.util.HashMap;
import java.util.Map;

/*
 * TODO:
 * Parse array
 * Refactor
 * Handle error when character is not correct
 * 
 * Modify while loop
*/

/* Parse array:
 * Starts with [ -> know array
 * Handle each string until reach ]
 * Outer loop go thru each char until ]:
 *  Inner loop handle each value
*/
public class JSONParser {
    // static String json;
    // public static Map<String,Object> parse(String input) {
    //     json = input;
    //     return parseObject(json);
    // }

    // parse string
    private static Object[] parseString(String json, int i){
        char c = json.charAt(i);
        String k = "";
        while(c != '"'){
            k += c;
            c = json.charAt(++i);
        }        
        return new Object[]{k,i};
    }

    // parse object
    public static Map<String,Object> parse(String json) {
        Map<String,Object> result = new HashMap<>();
        int i = 1;
        while(i < json.length()-1){
            if (json.charAt(i) == ' ' || json.charAt(i) == ','){
                i++;
                continue;
            }
            String k = "";
            Object v;
            // get key
            char c = json.charAt(++i);
            while(c != '"'){
                k += c;
                c = json.charAt(++i);
            }
            //skip " : "
            i+=4;
            //get val
            char markVal = json.charAt(i);
            // parse string
            if(markVal == '"'){
                c = json.charAt(++i);
                String valString = "";
                while(c != '"') {
                    valString += c;
                    c = json.charAt(++i);
                }
                v = valString;
            // parse array
            }else if (markVal == '[') {
                c = markVal;
                String valArray = "";
                while(c != ']') {
                    valArray += c;
                    c = json.charAt(++i);
                }
                valArray += c;
                v = valArray;
            // parse object
            }else if (markVal == '{') {
                c = markVal;
                String valMap = "";
                while(c != '}') {
                    valMap += c;
                    c = json.charAt(++i);
                }
                valMap += c;
                v = parse(valMap);
            // parse true, false, null, number
            }else {
                c = markVal;
                String val = "";
                while (c != ',' && c != ' ') {
                    val += c;
                    c = json.charAt(++i);
                }
                if (val.equals("true") || val.equals("false")) {
                    v = Boolean.parseBoolean(val);
                }else if (val.equals("null")) {
                    v = null;
                }else if (val.contains(".")){
                    v = Float.parseFloat(val);
                }else {
                    v = Integer.parseInt(val);
                }
            }
            result.put(k,v);
            k = "";
            v = "";
            i++;
        }
        return result;
    }
}