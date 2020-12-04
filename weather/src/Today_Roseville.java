import java.io.IOException;

public class Today_Roseville extends Today {

    public Today_Roseville(String user_input, String view, int zoom) throws IOException {
        super(user_input, view, zoom);
        if(user_input.equals("roseville")){
            System.out.println("You picked Roseville!!!");
        }
        else{
            System.out.println("You did not pick Roseville.");
        }
    }
    
}
