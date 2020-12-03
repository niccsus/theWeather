import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import java.awt.AlphaComposite;
import java.awt.Graphics;

public class Fetch {
    private final String key = "843b5b1093b07ff0e1d9f681a3a3cb0c"; // API token
    private final String google_key = "AIzaSyCVMgcmhkqfQKxbeUnJPGx6V5twYGMq-9k"; // google api token
    private String query; // API URL
    private String google_query; // google API URL
    private String weather_map_query;
    JSONObject json = new JSONObject(); // the JSON object that contains weather data
    JSONObject google_json = new JSONObject(); // the JSON object that contains city co ordinates
    ImageIcon map = new ImageIcon();
    String static_map_query;
    Geolocator geolocator;
    ImageIcon weather_map = new ImageIcon();
    ImageIcon composite = new ImageIcon();
    final private int zoom = 6;

    // constructor in case user inputs string
    public Fetch(String city) throws IOException {
        this.google_query = "https://maps.googleapis.com/maps/api/geocode/json?address=" + city + "&key=" + google_key;
        geolocator = new Geolocator(google_query, zoom);
        this.query = "https://api.openweathermap.org/data/2.5/onecall?lat=" + geolocator.lat + "&lon=" + geolocator.lon
                + "&units=imperial&appid=" + key;
        set_json(query);
        static_map_query = "https://maps.googleapis.com/maps/api/staticmap?center=" + geolocator.lat + ","
                + geolocator.lon + "&zoom="+zoom+"&size=256x256&maptype=satellite&key=" + google_key;
        URL url = new URL(static_map_query);
        BufferedImage img = ImageIO.read(url);
        map = new ImageIcon(img);

        weather_map_query = "https://tile.openweathermap.org/map/clouds_new/"+zoom+"/" + geolocator.x + "/" + geolocator.y
                + ".png?appid=" + key;
        System.out.println(weather_map_query);
        System.out.println(static_map_query);
        URL url2 = new URL(weather_map_query);
        BufferedImage img2 = ImageIO.read(url2);
        weather_map = new ImageIcon(get_weather_map_img(img2));
    }

    // creates JSON object based on user input
    public void set_json(String query) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(query)).build();
        this.json = new JSONObject(
                client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).join());
    }

    public BufferedImage get_weather_map_img(BufferedImage image) {

        BufferedImage tmpImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) tmpImg.getGraphics();
        g2d.setComposite(AlphaComposite.SrcOver.derive(1.0f));
        // set the transparency level in range 0.0f - 1.0f
        g2d.drawImage(image, 0, 0, null);
        image = tmpImg;
        return image;
    }

    public void get_composite() throws IOException {
        // Assumed that these are non-null
        BufferedImage bigIcon, smallIcon;
        URL url = new URL(static_map_query);
        URL url2 = new URL(weather_map_query);
        bigIcon = ImageIO.read(url);
        smallIcon = ImageIO.read(url2);
        // Create a new image.
        BufferedImage finalIcon = new BufferedImage(bigIcon.getWidth(), bigIcon.getHeight(),BufferedImage.TYPE_INT_ARGB); // start transparent

        // Get the graphics object. This is like the canvas you draw on.
        Graphics g = finalIcon.getGraphics();

        // Now we draw the images.
        g.drawImage(bigIcon, 0, 0, null); // start at (0, 0)
        g.drawImage(smallIcon, 0, 0, null); // start at (10, 10)

        // Once we're done drawing on the Graphics object, we should
        // call dispose() on it to free up memory.
        g.dispose();

        // Finally, convert to ImageIcon and apply.
        composite = new ImageIcon(finalIcon);
    }
}