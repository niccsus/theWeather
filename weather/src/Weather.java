import java.io.IOException; 

public class Weather {
    public static void main(String[] args) throws IOException {

        begin();
        
    }
    
    public static void begin() throws IOException  
    { 
 
        System.out.println("Enter CIty Name: ");
        Input input = new Input();
        Fetch weather = new Fetch();
        if(input.is_name){
            weather = new Fetch(input.city_name);
        }
        else if(input.is_lat_lon){
            weather = new Fetch(input.lat_lon);
        }
        else if(input.is_other){
            weather = new Fetch();
        }
        Forecast forecast = new Forecast(weather.json);
        System.out.println(forecast.get_temp());
        System.out.println(forecast.get_cloud());

        
        
        
    } 
}