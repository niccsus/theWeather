import javax.swing.*;  
import java.awt.*;

public class UI {  
    public static void frame() {
        JFrame frame = new JFrame("The Weather");
       // JButton button = new JButton("The Weather");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JLabel(new ImageIcon("/Users/victorperaza/git/theWeather/theWeather/weather/src/pic.jpg"))); //adds picture(change file path for it to work)
        frame.setSize(1500,1000);
        frame.setVisible(true);
    }
}