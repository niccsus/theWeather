import java.io.IOException; 

public class Weather {
    public static void main(String[] args) throws IOException {

        begin();
        
    }

    public static void begin() throws IOException  
    { 
        UI.frame(); //calls the frame method
        System.out.println("Enter CIty Name: ");
        Forecast forecast = new Forecast();
        System.out.println(forecast.get_temp());
        System.out.println("Celsius = " + String.format("%.3f",forecast.get_CelsiusTemp()));
        System.out.println(forecast.get_cloud());

        
        
        
    } 
}