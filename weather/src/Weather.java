import java.io.IOException; 

public class Weather {
    public static void main(String[] args) throws IOException {

        begin();
        
    }

    public static void begin() throws IOException  
    { 
 
        System.out.println("Enter CIty Name: ");
        Forecast forecast = new Forecast();
        System.out.println(forecast.get_temp());
        System.out.println(forecast.get_cloud());

        
        
        
    } 
}