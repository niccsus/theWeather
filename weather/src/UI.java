import javax.swing.*;  
import java.awt.*;
//windowBuilder

public class UI {  
    public static void frame() {
        JFrame frame = new JFrame("The Weather");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800,900));

        // Bottom Text Field
        JPanel southCenter = new JPanel(new GridLayout(1, 1));
        southCenter.add(new JTextField());

        //Bottom Text Button
        JPanel southEast = new JPanel(new GridLayout(1, 1));
        southEast.add(new JButton("Search"));


        // sets bottom layout
        JPanel south = new JPanel(new BorderLayout());
        south.add(southCenter, BorderLayout.CENTER);
        south.add(southEast, BorderLayout.EAST);

        //Bottom frame
        frame.add(south, BorderLayout.SOUTH);
        frame.add(new JTextArea(), BorderLayout.CENTER);


        //Background Image
        frame.add(new JLabel(new ImageIcon("/Users/victorperaza/git/theWeather/theWeather/weather/src/pic.gif"))); //adds picture(change file path for it to work)

        frame.setVisible(true);

    }
}