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
	static boolean unit;	//if true, requests F
	static JLabel lblNewLabel = new JLabel(""); 
	static JLabel map_label = new JLabel("");
	static JLabel icon = new JLabel("");
	static Forecast[] forecast = new Forecast[8];

	static String img = "";
	Color text_color = Color.WHITE;

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
		button.setForeground(Color.BLACK);
		button.setBackground(new Color(100, 149, 237)); // BUTTON COLOR
		button.setBounds(515, 348, 102, 23); // BUTTON PLACEMENT
		frame.getContentPane().add(button); // ADDING BUTTTON

		/************** CELSIUS RADIO BUTTON ******************/
		celciusButton = new JRadioButton("Celsius");
		celciusButton.setForeground(new Color(230, 230, 250));
		celciusButton.setOpaque(false);
		celciusButton.setBackground(new Color(0, 0, 0));
		celciusButton.setBounds(499, 122, 141, 23); // RADIO BUTTON PLACEMENT
		frame.getContentPane().add(celciusButton); // ADDS RADIO BUTTON

		/*************** FAHRENHEIT RADIO BUTTON ***************/
		fahreneitButton = new JRadioButton("Fahrenheit ");
		fahreneitButton.setSelected(true);
		unit = true;
		fahreneitButton.setForeground(new Color(230, 230, 250));
		fahreneitButton.setOpaque(false);
		fahreneitButton.setBackground(new Color(0, 0, 0));
		fahreneitButton.setBounds(499, 87, 141, 23); // RADIO BUTTON PLACEMENT
		frame.getContentPane().add(fahreneitButton); // ADDS RADIO BUTTON

		/*************** TEXTFIELD AREA **********************/
		textField = new JTextField("Roseville");
		textField.setHorizontalAlignment(SwingConstants.CENTER); // TEXT PLACEMENT
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 21)); // FONT
		textField.setBounds(166, 348, 354, 48); // TEXTFIELD PLACEMENT
		frame.getContentPane().add(textField); // ADDS TEXTFIELD
		textField.setColumns(10);
		textField.setBackground(UIManager.getColor("Button.highlight"));

		/*************** TEMPERATURE **********************/
		tempLabel = new JLabel("");
		tempLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tempLabel.setForeground(text_color);
		tempLabel.setFont(new Font("Lucida Grande", Font.BOLD, 57));
		tempLabel.setBounds(230, 6, 340, 187); // first one is X axis, second one is Y axis, width is 3rd, height is 4th
		frame.getContentPane().add(tempLabel);

		/*************** HUMIDITY **********************/
		humidLabel = new JLabel("");
		humidLabel.setFont(new Font("Lucida Grande", Font.BOLD, 38));
		humidLabel.setBounds(6, -20, 199, 177);
		// frame.getContentPane().add(humidLabel);

		/*************** WEATHER TYPE **********************/
		cloudLabel = new JLabel("");
		cloudLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cloudLabel.setForeground(text_color);
		cloudLabel.setFont(new Font("Lucida Grande", Font.BOLD, 10));
		cloudLabel.setBounds(330, 122, 102, 48);
		frame.getContentPane().add(cloudLabel);

		/************** COMBO BOX ***************************/
		String[] boxOptions = { "Sacramento", "San Franisco", "Los Angeles", "San Diego", "New York" };
		JComboBox<String> comboBox = new JComboBox<>(boxOptions);
		comboBox.setBounds(650, 6, 152, 27);
		frame.getContentPane().add(comboBox);// allows the saved cities to be acessed faster

		/***
		 * WEATHER INFO
		 */
		JLabel infoLabels = new JLabel("");
		infoLabels.setForeground(text_color);
		infoLabels.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabels.setBounds(652, 74, 118, 27);
		frame.getContentPane().add(infoLabels);

		JLabel lblNewLabel_4_1 = new JLabel("");
		lblNewLabel_4_1.setForeground(text_color);
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setBounds(652, 102, 118, 27);
		frame.getContentPane().add(lblNewLabel_4_1);

		JLabel lblNewLabel_4_1_1 = new JLabel("");
		lblNewLabel_4_1_1.setForeground(text_color);
		lblNewLabel_4_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1_1.setBounds(652, 133, 118, 27);
		frame.getContentPane().add(lblNewLabel_4_1_1);

		JLabel lblNewLabel_4_1_2 = new JLabel("");
		lblNewLabel_4_1_2.setForeground(text_color);
		lblNewLabel_4_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1_2.setBounds(652, 159, 118, 27);
		frame.getContentPane().add(lblNewLabel_4_1_2);

		JLabel lblNewLabel_4_1_3 = new JLabel("");
		lblNewLabel_4_1_3.setForeground(text_color);
		lblNewLabel_4_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1_3.setBounds(652, 187, 118, 27);
		frame.getContentPane().add(lblNewLabel_4_1_3);

		/***
		 * 
		 */

		/************* FAVORITE CITY BUTTON************************* */
		JButton saveCity = new JButton("Favorite");
		saveCity.setBounds(515, 371, 102, 23);
		frame.getContentPane().add(saveCity);

		/*************START APP WITH CITY POPULATED************************* */
		search_button_action("Roseville", frame);


		/**************** CELSIUS ACTION LISTENER *****************/
		celciusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				celciusButton.setSelected(true);
				if (celciusButton.isSelected()) {
					fahreneitButton.setSelected(false);
					unit = false;
					tempLabel.setText("" + (int) today.get_CelsiusTemp() + "°C");
					// for(int i=0; i<8; i++){
					// 	forecast[i].get_temp_min_label().setText("Min: " + (int) get_CelsiusTemp(forecast[i].get_temp_min()) + "°C");
					// 	forecast[i].get_temp_max_label().setText("Max: " + (int) get_CelsiusTemp(forecast[i].get_temp_max()) + "°C");
					// }
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
					// for(int i=0; i<8; i++){
					// 	forecast[i].get_temp_min_label().setText("Min: " + (int) forecast[i].get_temp_min() + "°F");
					// 	forecast[i].get_temp_max_label().setText("Max: " + (int) forecast[i].get_temp_max() + "°F");
					// }
				}
			}
		});

		//initial_set_background_image(frame);

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

				set_icon(frame);
				set_map(frame);
				set_Background_Image(frame);
			}
		});

	}

	// /**************** INITIAL BACKGROUND IMAGE ******************/
	// public static void initial_set_background_image(JFrame frame) {
	// 	lblNewLabel.setText("");
	// 	Image picture = new ImageIcon("weather/picture/cloud.jpg").getImage();
	// 	lblNewLabel.setIcon(new ImageIcon(picture));
	// 	lblNewLabel.setBounds(0, 0, 800, 405); // IMAGE PLACEMENT
	// 	frame.getContentPane().add(lblNewLabel);
	// }

	/**************** BACKGROUND IMAGE ******************/
	public static void set_Background_Image(JFrame frame) {
		lblNewLabel.setText("");
		String cl = today.get_icon_id();
		//System.out.println(cl);
		if (cl.equals("02d") || cl.equals("02n") || cl.equals("03d") || cl.equals("03n") || cl.equals("04d")
				|| cl.equals("04n")) {
			img = "cloud.jpg";
		} else if ((cl.equals("01n") || cl.equals("50n"))) {
			img = "moon.png";
		} else if (cl.equals("01d") || cl.equals("50d")) {
			img = "sun.png";
		} else if (cl.equals("13d") || cl.equals("13n")) {
			img = "snow.jpg";
		} else if (cl.equals("11d") || cl.equals("11n")) {
			img = "thunder.jpg";
		} else if (cl.equals("10d") || cl.equals("10n") || cl.equals("09d") || cl.equals("09n")) {
			img = "rain.png";
		}
		Image picture = new ImageIcon("weather/picture/" + img).getImage();
		lblNewLabel.setIcon(new ImageIcon(picture));
		lblNewLabel.setBounds(0, 0, 800, 405); // IMAGE PLACEMENT
		frame.getContentPane().add(lblNewLabel);

		
	}
	/**************** ICONS ***********************/
	public static void set_icon(JFrame frame) {
		icon.setIcon(today.get_icon());
		icon.setBounds(420, 129, 110, 35); // IMAGE PLACEMENT 129
		frame.getContentPane().add(icon);
	}

	/**************** STATIC MAP ******************/
	public static void set_map(JFrame frame) {
		map_label.setHorizontalAlignment(SwingConstants.CENTER);
		map_label.setIcon(today.map);
		map_label.setBounds(7, 6, 209, 187); // IMAGE PLACEMENT
		frame.getContentPane().add(map_label);
	}

	/**************** BUTTON ACTION ******************/
	public void search_button_action(String input, JFrame frame) {
		try {
			today = new Today(input);
			get_forecast();
			
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		for(int i=0; i<8; i++){
			forecast[i].get_temp_min_label().setText("");
			forecast[i].get_temp_max_label().setText("");
		}
		if (UI.unit) {
			tempLabel.setText("" + today.get_temp() + "°F");
			// for(int i=0; i<8; i++){
			// 	forecast[i].get_temp_min_label().setText("Min: " + (int) forecast[i].get_temp_min() + "°F");
			// 	forecast[i].get_temp_max_label().setText("Max: " + (int) forecast[i].get_temp_max() + "°F");
			// 	//System.out.println("TEST 1");
			// } 
		} else {
			tempLabel.setText("" + (int) today.get_CelsiusTemp() + "°C");
			// for(int i=0; i<8; i++){
			// 	forecast[i].get_temp_min_label().setText("Min: " + (int) get_CelsiusTemp(forecast[i].get_temp_min()) + "°C");
			// 	forecast[i].get_temp_max_label().setText("Max: " + (int) get_CelsiusTemp(forecast[i].get_temp_max()) + "°C");
			// 	//System.out.println("TEST 2");
			// }
		}
		
		humidLabel.setText("" + today.get_humidity() + "%");
		cloudLabel.setText("" + today.get_cloud());
		// cloudLabel.setText("" + forecast[7].getTemp_day());

		set_forecast_days(frame);
		set_icon(frame);
		set_map(frame);
		set_Background_Image(frame);
	}

	public static void get_forecast() {
		for (int i = 0; i < 8; i++) {
			try {
				forecast[i] = new Forecast(today.json, i);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void set_forecast_days(JFrame frame) {
		int[] x = {47,144,241,338,435,532,640};
		for(int i=0; i<7; i++){
			forecast[i].get_day_label().setBounds(x[i], 205, 85, 128);	//Weekday placement
			forecast[i].get_day_label().setForeground(Color.GREEN);
			frame.getContentPane().add(forecast[i].get_day_label());

			forecast[i].get_icon_label().setIcon(forecast[i].get_icon());	//Icon placemnt
			forecast[i].get_icon_label().setBounds(x[i], 180, 85, 128); 
			frame.getContentPane().add(forecast[i].get_icon_label());

			forecast[i].get_temp_min_label().setBounds(x[i], 230, 85, 128);	//Min temp placement
			forecast[i].get_temp_min_label().setForeground(Color.YELLOW);
			frame.getContentPane().add(forecast[i].get_temp_min_label());
			
			forecast[i].get_temp_max_label().setBounds(x[i], 250, 85, 128);	//Max temp placement
			forecast[i].get_temp_max_label().setForeground(Color.RED);
			frame.getContentPane().add(forecast[i].get_temp_max_label());

			
			
		}
		
	}

	public double get_CelsiusTemp(double tem){
        return (tem-32)*0.5556;
    }
}