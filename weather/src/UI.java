import javax.swing.*;  
public class UI {  
public static void main(String[] args) {  
JFrame frame =new JFrame();//
          
JButton button =new JButton("Check Weather");//
button.setBounds(100,5,200, 50);//x axis, y axis, width, height  
          
frame.add(button);
          
frame.setSize(400,500);
frame.setLayout(null);
frame.setVisible(true);//makes frame
}  
}  