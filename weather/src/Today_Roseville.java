import java.io.IOException;

public class Today_Roseville extends Today {

    public Today_Roseville(String user_input, String view, int zoom) throws IOException {
        super(user_input, view, zoom);
        if(user_input.equals("roseville")){
            System.out.println("This is an inherited function.\nYou picked Roseville!!!");
        }
        else{
            System.out.println("This is an inherited function.\nYou did not pick Roseville.");
        }
    }
    
}
