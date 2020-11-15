import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Fetch {
    private static HttpURLConnection connection;
    private final String key = "843b5b1093b07ff0e1d9f681a3a3cb0c";
    private String query;

    public Fetch(String city) {
        this.query = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + key;
        http(query);

    }

    public void http(String query){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(query)).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                        .thenApply(HttpResponse::body)
                        .thenAccept(System.out::println)
                        .join();
    }

    public void json(String query){
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        //establish connection
        try{
            URL url = new URL(query);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(50000);

            int status = connection.getResponseCode();
            //Test if connection established
            if(status>299){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            } else{
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }
            

        } catch(MalformedURLException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        } finally{
            connection.disconnect();
        }
    }



}