
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class main {


    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.openweathermap.org/data/2.5/weather?q=London&appid=d2afdfba65254611a0db8b9de6727417")).build();


        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }

    /*public static String parse(String responseBody){
        JSONArray weather = new JSONArray(responseBody);
        JSONObject attribute = weather.getJSONObject(1);
        int lon = attribute.getInt("lon");
        System.out.println("lon: " + lon);
*//*        for(int i = 0; i < weather.length();i++){
            //each iteration get one JSON object
            JSONObject attribute = weather.getJSONObject(i);
            int id = weather.getInt("id");
            int temp = weather.getInt("temp");
            String title = album.getString("title");
            System.out.println("ID: "+ id);
            System.out.println("Title: " + title);
            System.out.println("UserID: " + userID );
            System.out.println();
        }*//*
        return null;
    }*/
}
