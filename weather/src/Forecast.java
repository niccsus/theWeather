import org.json.JSONArray;
import org.json.JSONObject;

public class Forecast {
    private JSONObject json = new JSONObject();
    private JSONArray daily = new JSONArray();
    private double temp_day;

    public Forecast(JSONObject json, int index){
        this.json = json;
        this.daily = (JSONArray) json.get("daily");
        JSONObject currentObj = (JSONObject) daily.get(index);
        this.temp_day = currentObj.getJSONObject("temp").getDouble("day");
    }
    
}
