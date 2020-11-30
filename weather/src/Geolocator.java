import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.swing.ImageIcon;

import org.json.JSONArray;
import org.json.JSONObject;

public class Geolocator {
    double lat;
    double lon;
    private String google_query;
    JSONObject google_json = new JSONObject();  //the JSON object that contains city co ordinates
    ImageIcon map = new ImageIcon();

    public Geolocator(String google_query){
        this.google_query = google_query;
        set_google_json(google_query);
        set_lat_lon();

        
    }

    public void set_lat_lon(){
        JSONArray results = (JSONArray) google_json.get("results");        //create json array from weather
        JSONObject currentObj = (JSONObject) results.get(0);        //create json obect from json array 
        this.lat = currentObj.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
        this.lon = currentObj.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
    }
    public void set_google_json(String google_query){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(google_query)).build();
        this.google_json = new JSONObject(client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .join());   
    }
}
