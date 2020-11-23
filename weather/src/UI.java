import javax.swing.*;  
import java.awt.*;
<<<<<<< HEAD
import java.awt.event.*;
import java.io.IOException;
import java.net.http.WebSocket.Listener;
=======
>>>>>>> 6739f783c0b9f1d3f68892f0065c425a3432942e
//windowBuilder

public class UI implements ActionListener {

    static JTextField text_field;
    static JLabel label;
    private static Forecast forecast;

    public static void frame() throws IOException {
        
        JFrame frame = new JFrame("The Weather");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 900));

        // Bottom Text Field
        JPanel southCenter = new JPanel(new GridLayout(1, 1));
        text_field = new JTextField();
        southCenter.add(text_field);



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
        
        String input = text_field.getText();
        try {
            forecast = new Forecast(input);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        label.setText(""+forecast.get_temp());

    }
}