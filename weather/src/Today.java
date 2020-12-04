import org.json.JSONArray;
import org.json.JSONObject;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import java.util.*;
import java.text.*;

//Class for going through json files and returning specific data points
public class Today{
    JSONObject json = new JSONObject();
    private JSONObject current = new JSONObject();
    private int feel,temp,humidity,pressure,cloud_percent,wind_speed,wind_deg,dt,time,zoom;
    private String cloud,timezone,date,icon_id,view;
    ImageIcon map = new ImageIcon();
    private String icon_url = "";
    ImageIcon icon = new ImageIcon();
    ImageIcon weather_map = new ImageIcon();
    ImageIcon composite = new ImageIcon();
    Fetch fetch;
    


    public Today(String user_input, String view, int zoom) throws IOException {
        user_input=user_input.replaceAll(" ","+");
        this.view = view;
        this.zoom = zoom;
        this.json = set_json(user_input);
        this.current = this.json.getJSONObject("current");
        this.feel = current.getInt("feels_like");   //get feels like temp from json
        this.temp = current.getInt("temp");     //get temp from json
        this.pressure = current.getInt("pressure");     //get pressure from json
        this.humidity = current.getInt("humidity");     //get humidity from json
        this.wind_speed = current.getInt("wind_speed");      //get wind speed from json
        this.wind_deg = current.getInt("wind_deg");      //get wind degree from json
        this.cloud_percent = current.getInt("clouds");  //get cloud condition
        JSONArray cloud_condition = (JSONArray) current.get("weather");        //create json array from weather
        JSONObject currentObj = (JSONObject) cloud_condition.get(0);        //create json obect from json array
        this.cloud = (String) currentObj.get("description");    //get cloud description from json
        this.icon_id = (String) currentObj.get("icon"); 
        this.icon_url = "http://openweathermap.org/img/w/" + icon_id + ".png";   //get icon url from api
        this.dt = current.getInt("dt");     //gets unix timestamp
        this.timezone = (String) json.get("timezone");  //get search location timezone
        unix_timestamp_convertor();

        URL url = new URL(icon_url);
        BufferedImage img = ImageIO.read(url);       
        icon = new ImageIcon(img);  //icon for current weather

        

    }

    //calls Fetch to get json file
    //returns json to constructor
    private JSONObject set_json(String user_input) throws IOException{
        fetch = new Fetch(user_input, view, zoom);
        this.map = fetch.map;
        this.weather_map = fetch.weather_map;
        this.composite = fetch.composite;
        return fetch.json;
    }
    //decode unix timestamp into time format based on search location's timezone
    private void unix_timestamp_convertor(){
        //Unix seconds
        long unix_seconds = dt;
        //convert seconds to milliseconds
        Date date = new Date(unix_seconds*1000L); 
        // format of the date
        SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        jdf.setTimeZone(TimeZone.getTimeZone(timezone));
        String java_date = jdf.format(date);
        this.date = java_date;




        String dateTime = java_date;
        Calendar c = Calendar.getInstance();
        try {
            Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime);
            c.setTime(d);
            // System.out.println("Year: " + c.get(Calendar.YEAR));
            // System.out.println("Month: " + c.get(Calendar.MONTH));
            // System.out.println("Day in month: " + c.get(Calendar.DAY_OF_MONTH));
            // System.out.println("Hour: " + c.get(Calendar.HOUR_OF_DAY));
            // System.out.println("Minute: " + c.get(Calendar.MINUTE));
            // System.out.println("Second: " + c.get(Calendar.SECOND));
            this.time = c.get(Calendar.HOUR_OF_DAY);
            //System.out.println(time);
        }
        catch (Throwable t) {
            // test
            t.printStackTrace();
        }


        //System.out.println(time);
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
    //return icon url
    public String get_icon_url(){
        return icon_url;
    }
    //return weather icon
    public ImageIcon get_icon(){
        return icon;
    }
    //return cloud %
    public int get_cloud_percent(){
        return cloud_percent;
    }
    //return date
    public String get_date(){
        return date;
    }

    public int get_time(){
        return time;
    }

    public String get_icon_id(){
        return icon_id;
    }

    public ImageIcon get_map(){
        this.map = fetch.map;
        return map;
    }

    public ImageIcon get_weather_map(){
        this.weather_map = fetch.weather_map;
        return weather_map;
    }

   

}
