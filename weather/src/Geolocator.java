import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.swing.ImageIcon;

import org.json.JSONArray;
import org.json.JSONObject;

public class Geolocator{
    double lat;
    double lon;
    static int x;
    static int y;
    private String google_query;
    JSONObject google_json = new JSONObject(); // the JSON object that contains city co ordinates
    // ImageIcon map = new ImageIcon();

    public Geolocator(String google_query, int zoom) {
        this.google_query = google_query;
        set_google_json(google_query);
        set_lat_lon();
        getTileNumber(lat, lon, zoom);
    }

    public void set_lat_lon() {
        JSONArray results = (JSONArray) google_json.get("results"); // create json array from weather
        JSONObject currentObj = (JSONObject) results.get(0); // create json obect from json array
        this.lat = currentObj.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
        this.lon = currentObj.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
    }

    public void set_google_json(String google_query) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(google_query)).build();
        this.google_json = new JSONObject(
                client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).join());
    }

    public static void getTileNumber(final double lon, final double lat, final int zoom) {
        // n = 2 ^ zoom
        // xtile = n * ((lon_deg + 180) / 360)
        // ytile = n * (1 - (log(tan(lat_rad) + sec(lat_rad)) / π)) / 2

        double  n = Math.pow(2,zoom);
        double xtile = n * ((lat+180)/360);
        double ytile = n * (1-(Math.log(Math.tan(Math.toRadians(lon)))+(1/Math.cos(Math.toRadians(lon))))/Math.PI)/2;

        // int xtile = (int) Math.floor((lon + 180) / 360 * (1 << zoom));
        // int ytile = (int) Math
        //         .floor((1 - Math.log(Math.tan(Math.toRadians(lat)) + 1 / Math.cos(Math.toRadians(lat))) / Math.PI) / 2
        //                 * (1 << zoom));
        // if (xtile < 0)
        //     xtile = 0;
        // if (xtile >= (1 << zoom))
        //     xtile = ((1 << zoom) - 1);
        // if (ytile < 0)
        //     ytile = 0;
        // if (ytile >= (1 << zoom))
        //     ytile = ((1 << zoom) - 1);
        x = (int)xtile;
        y = (int)ytile;
        System.out.println(lon + "        " + lat);
        System.out.println("Zoom: " + zoom + " xtile: " + x + " ytile: " + y);
    }
    // public void getTileNumber(final double lat, final double lon, final int zoom) {

 
        
    //     int xtile = (int)Math.floor( (lon + 180) / 360 * (1<<zoom) ) ;
    //     int ytile = (int)Math.floor( (1 - Math.log(Math.tan(Math.toRadians(lat)) + 1 / Math.cos(Math.toRadians(lat))) / Math.PI) / 2 * (1<<zoom) ) ;
    //     if (xtile < 0)
    //         xtile=0;
    //     if (xtile >= (1<<zoom))
    //         xtile=((1<<zoom)-1);
    //     if (ytile < 0)
    //         ytile=0;
    //     if (ytile >= (1<<zoom))
    //         ytile=((1<<zoom)-1);
    //     this.x = xtile;
    //     this.y = ytile;
    //     System.out.println(lon + "        " + lat);
    //     System.out.println("Zoom: " + zoom + " xtile: " + xtile + " ytile: " + ytile);
         
    // }

    // public void getTileNumber(final double lat_deg, final double lon_deg, final int zoom) {
    //     double lat_rad = Math.toRadians(lat_deg);
    //     double n = Math.pow(2.0, zoom);
    //     int xtile = (int)(n * ((lon_deg + 180) / 360));
    //     int ytile = (int)(n * (1 - (Math.log(Math.tan(lat_rad) + (1/Math.cos(lat_rad))) / Math.PI)) / 2);
        
    //     //int xtile = (int)Math.floor((lon_deg + 180.0) / 360.0 * n) ;
    //     //int ytile = (int)Math.floor(1.0 - Math.log(Math.tan(lat_deg * Math.PI / 180.0) + 1/ Math.cos(lat_deg *Math.PI / 180.0)) / 2 * n);
    //     //int ytile = (int)Math.floor( (1 - Math.log(Math.tan(Math.toRadians(lat)) + 1 / Math.cos(Math.toRadians(lat))) / Math.PI) / 2 * (1<<zoom) ) ;
    //     // if (xtile < 0)
    //     //     xtile=0;
    //     // if (xtile >= (1<<zoom))
    //     //     xtile=((1<<zoom)-1);
    //     // if (ytile < 0)
    //     //     ytile=0;
    //     // if (ytile >= (1<<zoom))
    //     //     ytile=((1<<zoom)-1);
    //     this.x = xtile;
    //     this.y = ytile;
    //     System.out.println(lat_deg + "        " + lon_deg);
    //     System.out.println("Zoom: " + zoom + " xtile: " + xtile + " ytile: " + ytile);

    // }


    


}



