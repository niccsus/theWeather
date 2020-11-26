import javax.swing.*;  
import java.awt.*;

public class window {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window window = new window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		/***************WINDOW FRAME************************/
		
		frame = new JFrame();
		frame.setTitle("The Weather");
		frame.setBackground(Color.BLACK);
		frame.setForeground(Color.WHITE);
		frame.setBounds(100, 100, 800, 430);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		
		/***************SEARCH BUTTON**********************/
		
		JButton button = new JButton("Search");
		button.setForeground(new Color(47, 79, 79));
		button.setBackground(new Color(100, 149, 237)); //BUTTON COLOR
		button.setBounds(632, 337, 117, 50);			//BUTTON PLACEMENT
		frame.getContentPane().add(button);				//ADDING BUTTTON
		
		/**************CELSIUS RADIO BUTTON******************/
		
		JRadioButton radiobutton1 = new JRadioButton("Celsius");
		radiobutton1.setForeground(new Color(230, 230, 250));
		radiobutton1.setBounds(632, 273, 141, 23);	//RADIO BUTTON PLACEMENT
		frame.getContentPane().add(radiobutton1);	//ADDS RADIO BUTTON
		
		/***************FAHRENHEIT RADIO BUTTON***************/
		
		JRadioButton radiobutton2 = new JRadioButton("Fahrenheit ");
		radiobutton2.setForeground(new Color(230, 230, 250));
		radiobutton2.setBounds(632, 308, 141, 23);	//RADIO BUTTON PLACEMENT
		frame.getContentPane().add(radiobutton2);	//ADDS RADIO BUTTON
		
		/***************TEXTFIELD AREA**********************/
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);		//TEXT PLACEMENT
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 21));	//FONT
		textField.setBounds(106, 342, 514, 45);							//TEXTFIELD PLACEMENT
		frame.getContentPane().add(textField);							//ADDS TEXTFIELD
		textField.setColumns(10);
		textField.setBackground(UIManager.getColor("Button.highlight"));

		/****************BACKGROUND IMAGE******************/
		
		JLabel lblNewLabel = new JLabel("");
		Image picture = new ImageIcon(this.getClass().getResource("/night.gif")).getImage();	//SETS IMAGE
		lblNewLabel.setIcon(new ImageIcon(picture));
		lblNewLabel.setBounds(0, 0, 800, 405);													//IMAGE PLACEMENT
		frame.getContentPane().add(lblNewLabel);
	}
}
