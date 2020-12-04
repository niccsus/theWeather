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
    int x;
    int y;
    private String google_query;
    JSONObject google_json = new JSONObject();  //the JSON object that contains city co ordinates
    //ImageIcon map = new ImageIcon();

    public Geolocator(String google_query, int zoom){
        this.google_query = google_query;
        set_google_json(google_query);
        set_lat_lon();   
        getTileNumber(lat,lon,zoom);
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


    public  void getTileNumber(final double lat, final double lon, final int zoom) {
        int xtile = (int)Math.floor( (lon + 180) / 360 * (1<<zoom) ) ;
        int ytile = (int)Math.floor( (1 - Math.log(Math.tan(Math.toRadians(lat)) + 1 / Math.cos(Math.toRadians(lat))) / Math.PI) / 2 * (1<<zoom) ) ;
        if (xtile < 0)
            xtile=0;
        if (xtile >= (1<<zoom))
            xtile=((1<<zoom)-1);
        if (ytile < 0)
            ytile=0;
        if (ytile >= (1<<zoom))
            ytile=((1<<zoom)-1);
        this.x = xtile;
        this.y = ytile;
         
    }


}


