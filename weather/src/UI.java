import javax.swing.*;  
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.plaf.basic.BasicTextFieldUI;  

public class UI {

    static JTextField text_field;
	static JLabel tempLabel;
	static JLabel humidLabel;
	static JLabel cloudLabel;
    private static Today today;
    private JFrame frame;
	private JTextField textField;
	private JRadioButton celciusButton;
	private JRadioButton fahreneitButton;
	static JLabel celsius;
	private static boolean unit;	//if true, requests F
	static JLabel lblNewLabel = new JLabel(""); 
	static JLabel map_label = new JLabel("");
	static JLabel icon = new JLabel("");
	static Forecast[] forecast = new Forecast[8];

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

		/*************** WINDOW FRAME ************************/
		frame = new JFrame();
		frame.setTitle("The Weather");
		frame.setBackground(Color.BLACK);
		frame.setForeground(Color.WHITE);
		frame.setBounds(100, 100, 800, 430);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		/*************** SEARCH BUTTON **********************/
		JButton button = new JButton("Search");
		button.setForeground(new Color(47, 79, 79));
		button.setBackground(new Color(100, 149, 237)); // BUTTON COLOR
		button.setBounds(632, 337, 117, 50); // BUTTON PLACEMENT
		frame.getContentPane().add(button); // ADDING BUTTTON

		/************** CELSIUS RADIO BUTTON ******************/
		celciusButton = new JRadioButton("Celsius");
		celciusButton.setForeground(new Color(230, 230, 250));
		celciusButton.setOpaque(false);
		celciusButton.setBackground(new Color(0, 0, 0));
		celciusButton.setBounds(632, 273, 141, 23); // RADIO BUTTON PLACEMENT
		frame.getContentPane().add(celciusButton); // ADDS RADIO BUTTON

		/*************** FAHRENHEIT RADIO BUTTON ***************/
		fahreneitButton = new JRadioButton("Fahrenheit ");
		fahreneitButton.setSelected(true);
		unit = true;
		fahreneitButton.setForeground(new Color(230, 230, 250));
		fahreneitButton.setOpaque(false);
		fahreneitButton.setBackground(new Color(0, 0, 0));
		fahreneitButton.setBounds(632, 308, 141, 23); // RADIO BUTTON PLACEMENT
		frame.getContentPane().add(fahreneitButton); // ADDS RADIO BUTTON

		/*************** TEXTFIELD AREA **********************/
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER); // TEXT PLACEMENT
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 21)); // FONT
		textField.setBounds(106, 342, 514, 45); // TEXTFIELD PLACEMENT
		frame.getContentPane().add(textField); // ADDS TEXTFIELD
		textField.setColumns(10);
		textField.setBackground(UIManager.getColor("Button.highlight"));

		/*************** TEMPERATURE **********************/
		tempLabel = new JLabel("");
		tempLabel.setFont(new Font("Lucida Grande", Font.BOLD, 38));
		tempLabel.setBounds(400, 50, 141, 61);
		frame.getContentPane().add(tempLabel);

		/*************** HUMIDITY **********************/
		humidLabel = new JLabel("");
		humidLabel.setFont(new Font("Lucida Grande", Font.BOLD, 38));
		humidLabel.setBounds(300, 50, 141, 61);
		frame.getContentPane().add(humidLabel);

		/*************** WEATHER TYPE **********************/
		cloudLabel = new JLabel("");
		cloudLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		cloudLabel.setBounds(350, 100, 141, 61);
		frame.getContentPane().add(cloudLabel);

		/************** COMBO BOX ***************************/
		String[] boxOptions = { "Sacramento", "San Franisco", "Los Angeles", "San Diego", "New York" };
		JComboBox<String> comboBox = new JComboBox<>(boxOptions);
		comboBox.setBounds(650, 6, 152, 27);
		frame.getContentPane().add(comboBox);// allows the saved cities to be acessed faster 

		/**************** CELSIUS ACTION LISTENER *****************/
		celciusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				celciusButton.setSelected(true);
				if (celciusButton.isSelected()) {
					fahreneitButton.setSelected(false);
					unit = false;
					tempLabel.setText("" + (int) today.get_CelsiusTemp() + "°C");

				}
			}
		});

		/**************** FAHRENHEIT ACTION LISTENER *****************/
		fahreneitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fahreneitButton.setSelected(true);
				if (fahreneitButton.isSelected()) {
					celciusButton.setSelected(false);
					unit = true;
					tempLabel.setText("" + today.get_temp() + "°F");

				}
			}
		});

		set_Background_Image(frame);

		/**************** TEXTFIELD (PRESS ENTER) ACTION LISTENER ******************/
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String input = textField.getText();
					search_button_action(input, frame);

				}
			}
		});

		/**************** BUTTON AND ACTION LISTENER ******************/
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = textField.getText();
				search_button_action(input, frame);

			}
		});

	}

	/**************** BACKGROUND IMAGE ******************/
	public static void set_Background_Image(JFrame frame) {
		lblNewLabel.setText("");
		Image picture = new ImageIcon("weather/picture/night.gif").getImage(); // SETS IMAGE
		lblNewLabel.setIcon(new ImageIcon(picture));
		lblNewLabel.setBounds(0, 0, 800, 405); // IMAGE PLACEMENT
		frame.getContentPane().add(lblNewLabel);
	}

	/**************** Icon ******************/
	public static void set_icon(JFrame frame) {
		icon.setIcon(today.get_icon());
		icon.setBounds(0, 0, 800, 405); // IMAGE PLACEMENT
		frame.getContentPane().add(icon);
	}

	/**************** STATIC MAP ******************/
	public static void set_map(JFrame frame) {

		map_label.setIcon(today.map);
		map_label.setBounds(0, 0, 800, 405); // IMAGE PLACEMENT
		frame.getContentPane().add(map_label);
	}

	/**************** BUTTON ACTION ******************/
	public void search_button_action(String input, JFrame frame) {
		try {
			today = new Today(input);
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		if (UI.unit) {
			tempLabel.setText("" + today.get_temp() + "°F");
		} else {
			tempLabel.setText("" + (int) today.get_CelsiusTemp() + "°C");
		}
		get_forecast();
		humidLabel.setText("" + today.get_humidity() + "%");
		cloudLabel.setText("" + today.get_cloud());
		//cloudLabel.setText("" + forecast[7].getTemp_day());

		set_icon(frame);
		set_map(frame);
		set_Background_Image(frame);
	}

	public static void get_forecast() {
		for(int i=1; i<8; i++){
			try {
				forecast[i] = new Forecast(today.json, i);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

	}

}