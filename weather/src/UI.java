import javax.swing.*;  
public class UI {  
public static void main(String[] args) {  
JFrame frame = new JFrame();
JButton button = new JButton("The Weather");

frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.getContentPane().add(button);
frame.setSize(300,300);
frame.setVisible(true);
}
}