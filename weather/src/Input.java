import java.io.IOException; 
 

//class for taking user input and formatting it to work with API
public class Input {
    private String input;
    String location = "";
    


    public Input(String input) throws IOException {
        this.input = input;
        this.location = this.input.replaceAll(" ","+");
    }

    public String get_loc(){
        return location;
        
    }

    



    
}
