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
	static JLabel celsius;
	private boolean unit;	//if true, requests F 

    
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
		celciusButton.setOpaque(false);
		celciusButton.setBackground(new Color(0, 0, 0));
		celciusButton.setBounds(632, 273, 141, 23);	//RADIO BUTTON PLACEMENT
		frame.getContentPane().add(celciusButton);	//ADDS RADIO BUTTON
		
		/***************FAHRENHEIT RADIO BUTTON***************/
		
		fahreneitButton = new JRadioButton("Fahrenheit ");
		fahreneitButton.setSelected(true);
		unit = true;
		fahreneitButton.setForeground(new Color(230, 230, 250));
		fahreneitButton.setOpaque(false);
		fahreneitButton.setBackground(new Color(0, 0, 0));
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
		
		/***************TEMPERATURE**********************/
		tempLabel = new JLabel("");
		tempLabel.setFont(new Font("Lucida Grande", Font.BOLD, 38));
        tempLabel.setBounds(400, 50, 141, 61);
		frame.getContentPane().add(tempLabel);

		/***************HUMIDITY**********************/
		humidLabel = new JLabel("");
		humidLabel.setFont(new Font("Lucida Grande", Font.BOLD, 38));
        humidLabel.setBounds(300, 50, 141, 61);
		frame.getContentPane().add(humidLabel);
		
		/***************WEATHER TYPE**********************/
		cloudLabel = new JLabel("");
		cloudLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        cloudLabel.setBounds(350, 100, 141, 61);
		frame.getContentPane().add(cloudLabel);

		/****************CELSIUS ACTION LISTENER*****************/
		celciusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				celciusButton.setSelected(true);
				if (celciusButton.isSelected()) {
					fahreneitButton.setSelected(false);
					unit = false;
					tempLabel.setText(""+(int)forecast.get_CelsiusTemp() + "°C");
					
				}
			}
		});

		/****************FAHRENHEIT ACTION LISTENER*****************/
		fahreneitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fahreneitButton.setSelected(true);
				if (fahreneitButton.isSelected()) {
					celciusButton.setSelected(false);
					unit = true;
					tempLabel.setText(""+forecast.get_temp() + "°F");
					
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
					if(unit){
						tempLabel.setText(""+forecast.get_temp()+ "°F");
					}
					else{
						tempLabel.setText(""+(int)forecast.get_CelsiusTemp()+ "°C");
					}
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
			if(unit){
				tempLabel.setText(""+forecast.get_temp()+ "°F");
			}
			else{
				tempLabel.setText(""+(int)forecast.get_CelsiusTemp()+ "°C");
			}
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