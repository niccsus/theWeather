import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

//Class for going through json files and returning specific data points
public class Today {
    private JSONObject json = new JSONObject();
    private JSONObject current = new JSONObject();
    private int feel;
    private int temp;
    private int humidity;
    private int pressure;
    private String cloud;
    private int cloud_percent;
    private int wind_speed;
    private int wind_deg;



    public Today(String user_input) throws IOException {
        this.json = set_json(user_input);
        this.current = this.json.getJSONObject("current");
        this.feel = current.getInt("feels_like");   //get feels like temp from json
        this.temp = current.getInt("temp");     //get temp from json
        this.pressure = current.getInt("pressure");     //get pressure from json
        this.humidity = current.getInt("humidity");     //get humidity from json
        this.wind_speed = current.getInt("wind_speed");      //get wind speed from json
        this.wind_deg = current.getInt("wind_deg");      //get wind degree from json
        this.cloud_percent = current.getInt("clouds");
        JSONArray cloud_condition = (JSONArray) current.get("weather");        //create json array from weather
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
    //return temp in celsius
    public double get_CelsiusTemp(){
        return (temp-32)*0.5556;
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