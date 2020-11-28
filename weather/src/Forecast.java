import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

//Class for going through json files and returning specific data points
public class Forecast {
    private JSONObject json = new JSONObject();
    private int feel;
    private int temp;
    private int max_temp;
    private int min_temp;
    private int humidity;
    private int pressure;
    private String cloud;
    private int wind_speed;
    private int wind_deg;



    public Forecast(String user_input) throws IOException {
        this.json = set_json(user_input);
        this.max_temp = this.json.getJSONObject("main").getInt("temp_max");  //get max_temp from json
        this.feel = this.json.getJSONObject("main").getInt("feels_like");   //get feels like temp from json
        this.temp = this.json.getJSONObject("main").getInt("temp");     //get temp from json
        this.min_temp = this.json.getJSONObject("main").getInt("temp_min");     //get min temp from json
        this.pressure = this.json.getJSONObject("main").getInt("pressure");     //get pressure from json
        this.humidity = this.json.getJSONObject("main").getInt("humidity");     //get humidity from json
        this.wind_speed = this.json.getJSONObject("wind").getInt("speed");      //get wind speed from json
        this.wind_deg = this.json.getJSONObject("wind").getInt("deg");      //get wind degree from json
        JSONArray cloud_condition = (JSONArray) json.get("weather");        //create json array from weather
        JSONObject currentObj = (JSONObject) cloud_condition.get(0);        //create json obect from json array
        this.cloud = (String) currentObj.get("description");                //get cloud description from json
        

    }

    //calls Input to get user input
    //calls Fetch to get json file
    //returns json to constructor
    private JSONObject set_json(String user_input) throws IOException{
        Input input = new Input(user_input);
        Fetch fetch = new Fetch();
        if(input.is_name){
            fetch = new Fetch(input.city_name);
        }
        else if(input.is_lat_lon){
            fetch = new Fetch(input.lat_lon);
        }
        else if(input.is_other){
            fetch = new Fetch();
        }
        
        return fetch.json;
    }
    //return temp
    public int get_temp(){
        return temp;
    }
    //return min temp
    public int get_min_temp(){
        return min_temp;
    }
    //return max temp
    public int get_max_temp(){
        return max_temp;
    }
    //return feels like temp
    public int get_feel(){
        return feel;
    }
    //return humidity
    public int get_humidity(){
        return humidity;
    }
    //return pressure
    public int get_pressure(){
        return pressure;
    }
    //return wind speed
    public int get_wind_speed(){
        return wind_speed;
    }
    //return wind degree
    public int get_wind_deg(){
        return wind_deg;
    }
    //return cloudy status
    public String get_cloud(){
        return cloud;
    }

}
