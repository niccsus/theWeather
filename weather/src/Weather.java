import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
public class Weather {
    public static void main(String[] args) throws IOException {

        input();
        
    }

    public static void input() throws IOException  
    { 
        //Enter data using BufferReader 
        BufferedReader reader =  
                   new BufferedReader(new InputStreamReader(System.in)); 
        // Reading data using readLine 
        System.out.println("Enter CIty Name: ");
        String input = reader.readLine();
        input = input.replaceAll(" ","+");  //required for openweather API to ereplace all space with +
        Fetch weather = new Fetch(input);
        Forecast forecast = new Forecast(weather.json);
        System.out.println(forecast.get_temp());
        //temp_max = weather.json.getJSONObject("main").getInt("temp_max");
        // char charArray[] = input.toCharArray();
        // if(Character.isDigit(charArray[0])){
        //     String[] inputs = input.split(",");
        //     Fetch weather = new Fetch(inputs);
        //     temp_max = weather.json.getJSONObject("main").getInt("temp_max");
        // } else{
        //     Fetch weather = new Fetch(input);
        //     temp_max = weather.json.getJSONObject("main").getInt("temp_max");
        // }
        
        
        
    } 
}