import javax.swing.*;  
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.http.WebSocket.Listener;

public class UI implements ActionListener {

    static JLabel label;
    private static Forecast forecast;

    public static void frame() throws IOException {

        System.out.println("Enter CIty Name: ");
        forecast = new Forecast();
        JFrame frame = new JFrame("The Weather");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 900));

        // Bottom Text Field
        JPanel southCenter = new JPanel(new GridLayout(1, 1));
        southCenter.add(new JTextField());



        // Bottom Text Button
        JPanel southEast = new JPanel(new GridLayout(1, 1));
        JButton search = new JButton("Search");
        southEast.add(search);


        // sets bottom layout
        JPanel south = new JPanel(new BorderLayout());
        south.add(southCenter, BorderLayout.CENTER);
        south.add(southEast, BorderLayout.EAST);

        //Bottom frame
        frame.add(south, BorderLayout.SOUTH);
        frame.add(new JTextArea(), BorderLayout.CENTER);


        //Background Image
        //frame.add(new JLabel(new ImageIcon("C:\\Users\\Foad_Olfat\\vs_project\\theWeather\\weather\\src\\pic.gif"))); //adds picture(change file path for it to work)
        frame.setVisible(true);


        label = new JLabel("");
        
        frame.add(label, BorderLayout.NORTH);

        search.addActionListener(new UI());

        

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        label.setText(""+forecast.get_temp());

    }
}