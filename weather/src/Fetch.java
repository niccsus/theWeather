import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import java.net.URI;



public class Fetch {
    private final String key = "843b5b1093b07ff0e1d9f681a3a3cb0c";  //API token
    private final String google_key = "AIzaSyCVMgcmhkqfQKxbeUnJPGx6V5twYGMq-9k";    //google api token
    private String query;   //API URL
    private String google_query;    //google API URL
    JSONObject json = new JSONObject();    //the JSON object that contains weather data
    JSONObject google_json = new JSONObject();  //the JSON object that contains city co ordinates


    public Fetch(){

    }
    //constructor in case user inputs string
    public Fetch(String city) {
        //this.query = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=imperial&appid=" + key;
        this.google_query = "https://maps.googleapis.com/maps/api/geocode/json?address="+ city + "&key=" + google_key;
        Geolocator geolocator = new Geolocator(google_query);
        //this.query = "https://api.openweathermap.org/data/2.5/weather?lat=" + geolocator.lat + "&lon=" + geolocator.lon + "&units=imperial&appid=" + key;
        this.query = "https://api.openweathermap.org/data/2.5/onecall?lat="+ geolocator.lat + "&lon=" + geolocator.lon + "&units=imperial&appid=" + key;
        set_json(query);

    }
    //constructor in case user inputs lat/lon
    //WORK IN PROGRESS
    public Fetch(String[] lat_lon){
        //38.7521235,-121.2880059
        this.query = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat_lon[0] + "&lon=" + lat_lon[1] + "&units=imperial&appid=" + key;
        set_json(query);
    }

    //creates JSON object based on user input
    public void set_json(String query){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(query)).build();
        this.json = new JSONObject(client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .join());              
    }






}