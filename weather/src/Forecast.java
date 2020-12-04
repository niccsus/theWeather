import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.sql.Date;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.json.JSONArray;
import org.json.JSONObject;

public class Forecast {
    private JSONObject json;
    private JSONArray daily;    
    private double temp_day,temp_night,feel_day,feel_night,windspeed,temp_min,temp_max,temp_eve,temp_morn,feel_eve,feel_morn;
    private int sunrise_dt,sunset_dt,cloudPercentage,humid,pressure,dt;
    private String timezone,sunrise,sunset,day_of_week;
    private String icon_url = "";
    ImageIcon icon = new ImageIcon();
    JLabel week_day_label;
    JLabel icon_label = new JLabel("");
    JLabel temp_min_label = new JLabel("");
    JLabel temp_max_label = new JLabel("");

    public Forecast(){
        
    }
 
    public Forecast(JSONObject json, int day) throws IOException{
        this.json = json;
        this.daily = (JSONArray) json.get("daily");
        JSONObject currentObj = (JSONObject) daily.get(day);

        this.timezone = (String) json.get("timezone");
        
        this.temp_day = currentObj.getJSONObject("temp").getDouble("day");  //get day temp
        this.temp_night = currentObj.getJSONObject("temp").getDouble("night");  //get night temp
        this.temp_min = currentObj.getJSONObject("temp").getDouble("min");  //get min temp
        this.temp_max = currentObj.getJSONObject("temp").getDouble("max");  //getmax temp
        this.temp_eve = currentObj.getJSONObject("temp").getDouble("eve");  //get evening temp
        this.temp_morn = currentObj.getJSONObject("temp").getDouble("morn");    //get morning temp

        this.feel_day = currentObj.getJSONObject("feels_like").getDouble("night");  //get feels like night temp
        this.feel_night = currentObj.getJSONObject("feels_like").getDouble("day");  //get feels likes day temp
        this.feel_eve = currentObj.getJSONObject("feels_like").getDouble("eve");    //get feels like in evening temp
        this.feel_morn = currentObj.getJSONObject("feels_like").getDouble("morn");  //get feels like in morning temp


        this.windspeed = currentObj.getDouble("wind_speed");    //get winf speed
        this.cloudPercentage = currentObj.getInt("clouds");     //get cloud %
        this.sunrise_dt = currentObj.getInt("sunrise");    //get sunrise time
        this.sunset_dt = currentObj.getInt("sunset");      //get sunset time
        this.humid = currentObj.getInt("humidity");        //get humidty
        this.pressure = currentObj.getInt("pressure");  //get pressure
        this.dt = currentObj.getInt("dt");

        JSONArray cloud_condition = (JSONArray) currentObj.get("weather");        //create json array from weather
        JSONObject icon_object = (JSONObject) cloud_condition.get(0); 
        this.icon_url = "http://openweathermap.org/img/w/" + icon_object.get("icon") + ".png";   //get icon url from api
        URL url = new URL(icon_url);
        BufferedImage img = ImageIO.read(url);       
        icon = new ImageIcon(img);  //icon for current weather
        
        this.sunrise = unix_timestamp_convertor(sunrise_dt);    //set sunrise date/time
        this.sunset = unix_timestamp_convertor(sunset_dt);      //set sunset date/time
        day_of_week_calculator();
        this.week_day_label = new JLabel(this.day_of_week);
        //this.temp_min_label = new JLabel("");//new JLabel("Min: " + this.temp_min);
        //this.temp_max_label = new JLabel("");//new JLabel("Max: " + this.temp_max);
        


    }

    //decode unix timestamp into time format based on search location's timezone
    private String unix_timestamp_convertor(int dt){
         //Unix seconds
         long unix_seconds = dt;
         //convert seconds to milliseconds
         Date date = new Date(unix_seconds*1000L); 
         // format of the date
         SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
         jdf.setTimeZone(TimeZone.getTimeZone(timezone));
         String java_date = jdf.format(date);
         return java_date;
         
    }

    private void day_of_week_calculator(){
        int day_num = (int) (Math.floor(this.dt/86400)+4)%7;
        switch(day_num){
            case 0: this.day_of_week = "Monday";
            break;

            case 1: this.day_of_week = "Tuesday";
            break;

            case 2: this.day_of_week = "Wednesday";
            break;

            case 3: this.day_of_week = "Thursday";
            break;

            case 4: this.day_of_week = "Friday";
            break;

            case 5: this.day_of_week = "Saturday";
            break;

            case 6: this.day_of_week = "Sunday";
            break;

            default: this.day_of_week = "INVALID";
            break;
        }
        //System.out.println(this.day_of_week);
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

    public String get_icon_url(){
        return icon_url;
    }
    //return weather icon
    public ImageIcon get_icon(){
        return icon;
    }

    public double get_temp_min(){
        return temp_min;
    }

    public double get_temp_max(){
        return temp_max;
    }

    public double get_temp_eve(){
        return temp_eve;
    }

    public double get_temp_morn(){
        return temp_morn;
    }

    public double get_feel_eve(){
        return feel_eve;
    }

    public double get_feel_morn(){
        return feel_morn;
    }

    public int get_humid(){
        return humid;
    }

    public int get_pressure(){
        return pressure;
    }

    public int get_sunrise_dt(){
        return sunrise_dt;
    }

    public int get_sunset_dt(){
        return sunset_dt;
    }

    public String get_sunset(){
        return sunset;
    }

    public String get_sunrise(){
        return sunrise;
    }

    public String get_day_of_week(){
        return day_of_week;
    }

    public JLabel get_day_label(){
        return week_day_label;
    }

    public JLabel get_icon_label(){
        return icon_label;
    }

    public JLabel get_temp_min_label(){
        return temp_min_label;
    }

    public JLabel get_temp_max_label(){
        return temp_max_label;
    }

    
}