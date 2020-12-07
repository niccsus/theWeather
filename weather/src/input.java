import java.io.IOException; 
 

//class for taking user input and formatting it to work with API
public class input {
    private String input;
    String city_name;
    private String lat;
    private String lon;
    boolean is_name;    //true if input is city name
    boolean is_lat_lon; //true if input is lat and long
    boolean is_other;   //true if input is not categorized
    String[] lat_lon = new String[2];   //array of strings containing lat in index 0 abd lon in index 1


    public input(String input) throws IOException {
        //types of input, all initialized to false
        is_name = false;
        is_lat_lon = false;
        is_other = false;
        
        this.input = input;
        check_type();
    }

    public void check_type(){
               
        //if user input starts with digits, assume it's lat and lon and set values
        if(input.substring(0, 1).matches("\\d")){
            this.lat = input.split(",")[0];
            this.lon = input.split(",")[1];
            this.lat_lon[0] = lat;
            this.lat_lon[1] = lon;  
            is_lat_lon = true;     //sets type of input to cityname
        //if user input starts with alphabet characters, assume it's city name and set values
        } else if(input.substring(0, 1).matches("^[a-zA-Z]*$")){
            this.city_name = this.input;
            this.city_name = this.city_name.replaceAll(" ","+");  //required for openweather API to ereplace all space with +
            is_name = true;  //sets type of input to lat_lon
        //if user input starts with naything else, set it as city name
        } else{
            is_other = true;    //sets type of input to other
        }
    }


    
}
