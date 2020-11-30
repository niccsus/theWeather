import org.json.JSONArray;
import org.json.JSONObject;

public class Forecast {
    private JSONObject json;
    private JSONArray daily;
    private double temp_day,temp_night,feel_day,feel_night,windspeed;
    private int cloudPercentage;

    public Forecast(JSONObject json){
        this.json = json;
        this.daily = (JSONArray) json.get("daily");
        JSONObject currentObj = (JSONObject) daily.get(1);
        this.temp_day = currentObj.getJSONObject("temp").getDouble("day");
        System.out.println(temp_day);
    }

    public Forecast(JSONObject json, int day){
        this.json = json;
        this.daily = (JSONArray) json.get("daily");
        JSONObject currentObj = (JSONObject) daily.get(day);
        this.temp_day = currentObj.getJSONObject("temp").getDouble("day");
        this.temp_night = currentObj.getJSONObject("temp").getDouble("night");
        this.feel_day = currentObj.getJSONObject("feels_like").getDouble("night");
        this.feel_night = currentObj.getJSONObject("feels_like").getDouble("day");
        this.windspeed = currentObj.getDouble("wind_speed");
        this.cloudPercentage = currentObj.getInt("clouds");

        System.out.println(temp_day);
    }

    public double getTemp_day() {
        return temp_day;
    }

    public double getTemp_night() {
        return temp_night;
    }

    public double getFeel_day() {
        return feel_day;
    }

    public double getFeel_night() {
        return feel_night;
    }

    public double getWindspeed() {
        return windspeed;
    }

    public int getCloudPercentage() {
        return cloudPercentage;
    }
}
