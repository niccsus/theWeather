import org.json.JSONArray;
import org.json.JSONObject;

public class Forecast {
    private JSONObject json = new JSONObject();
    private JSONArray daily = new JSONArray();
    private double temp_day;

    public Forecast(JSONObject json){
        this.json = json;
        this.daily = (JSONArray) json.get("daily");
        JSONObject currentObj = (JSONObject) daily.get(1);
        this.temp_day = currentObj.getJSONObject("temp").getDouble("day");
        System.out.println(temp_day);
    }
    
}
