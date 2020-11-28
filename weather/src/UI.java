import javax.swing.*;  
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class UI {

    static JTextField text_field;
	static JLabel tempLabel;
	static JLabel humidLabel;
	static JLabel cloudLabel;
    private static Forecast forecast;
    private JFrame frame;
	private JTextField textField;
	private JRadioButton celciusButton;
	private JRadioButton fahreneitButton;
    
    public static void frame() throws IOException {
        
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }

    public UI() {
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
		
		celciusButton = new JRadioButton("Celsius");
		celciusButton.setForeground(new Color(230, 230, 250));
		celciusButton.setBounds(632, 273, 141, 23);	//RADIO BUTTON PLACEMENT
		frame.getContentPane().add(celciusButton);	//ADDS RADIO BUTTON
		
		/***************FAHRENHEIT RADIO BUTTON***************/
		
		fahreneitButton = new JRadioButton("Fahrenheit ");
		fahreneitButton.setForeground(new Color(230, 230, 250));
		fahreneitButton.setBounds(632, 308, 141, 23);	//RADIO BUTTON PLACEMENT
		frame.getContentPane().add(fahreneitButton);	//ADDS RADIO BUTTON
		
		/***************TEXTFIELD AREA**********************/
		
		textField = new JTextField();
		
		textField.setHorizontalAlignment(SwingConstants.CENTER);		//TEXT PLACEMENT
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 21));	//FONT
		textField.setBounds(106, 342, 514, 45);							//TEXTFIELD PLACEMENT
		frame.getContentPane().add(textField);							//ADDS TEXTFIELD
		textField.setColumns(10);
        textField.setBackground(UIManager.getColor("Button.highlight"));
		
		//Temp display location
		tempLabel = new JLabel("");
		tempLabel.setFont(new Font("Lucida Grande", Font.BOLD, 38));
        tempLabel.setBounds(400, 50, 141, 61);
		frame.getContentPane().add(tempLabel);

		//humidity display location
		humidLabel = new JLabel("");
		humidLabel.setFont(new Font("Lucida Grande", Font.BOLD, 38));
        humidLabel.setBounds(300, 50, 141, 61);
		frame.getContentPane().add(humidLabel);
		
		//cloud display location
		cloudLabel = new JLabel("");
		cloudLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        cloudLabel.setBounds(350, 100, 141, 61);
		frame.getContentPane().add(cloudLabel);

		/****************CELSIUS ACTION LISTENER*****************/
		celciusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (celciusButton.isSelected()) {
					fahreneitButton.setSelected(false);
					
				}
			}
		});

		/****************FAHRENHEIT ACTION LISTENER*****************/
		fahreneitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fahreneitButton.isSelected()) {
					celciusButton.setSelected(false);
					
				}
			}
		});
		
		/****************TEXTFIELD (PRESS ENTER) ACTION LISTENER******************/
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					//textField.setText(textField.getText());
					String input = textField.getText();
					try {
						forecast = new Forecast(input);
					} catch (IOException e1) {
		
						e1.printStackTrace();
					}
					tempLabel.setText(""+forecast.get_temp());
					humidLabel.setText(""+forecast.get_humidity()+"%");
					cloudLabel.setText(""+forecast.get_cloud());
					
				}
			}
		});

        /****************BUTTON AND ACTION LISTENER******************/
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = textField.getText();
            try {
                forecast = new Forecast(input);
            } catch (IOException e1) {

                e1.printStackTrace();
            }
			tempLabel.setText(""+forecast.get_temp());
			humidLabel.setText(""+forecast.get_humidity()+"%");
			cloudLabel.setText(""+forecast.get_cloud());
                }
        });
		/****************BACKGROUND IMAGE******************/		
		JLabel lblNewLabel = new JLabel("");
		Image picture = new ImageIcon("weather/picture/night.gif").getImage();	//SETS IMAGE
		lblNewLabel.setIcon(new ImageIcon(picture));
		lblNewLabel.setBounds(0, 0, 800, 405); //IMAGE PLACEMENT
		frame.getContentPane().add(lblNewLabel);   
	}




}