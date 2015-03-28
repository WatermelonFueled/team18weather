package ca.uwo.csd.cs2212.team18;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


public class LocalWeatherView extends JPanel {

	private static final long serialVersionUID = -5393294539017896082L;

	//weather info labels
	private JLabel lblCity;
	private JLabel lblTemperature;
	private JLabel lblAirPressure;
	private JLabel lblHumidity;
	private JLabel lblWind;
	private JLabel lblSkyCondition;
	private JLabel lblMinTemperature;
	private JLabel lblMaxTemperature;
	private JLabel lblSunrise;
	private JLabel lblSunset;

	final private Color labelColor = Color.BLACK;

	private LocalWeatherData localWeatherData;
	private String cityName;
	private JPanel topPanel;
	private JLabel lblWeatherUpdate;
	private JLabel lblWeatherReportFor;
	private JLabel lblWeatherIcon;
	private JLabel lblIcon;
	private JPanel rightPanel;
	private JPanel leftPanel;
	private JButton btnRefresh = new JButton("Refresh");
	private JLabel lblTime;
	private JLabel lblDate;
	private JLabel lblUpdate;

	/**
	 * Constructor for LocalWeatherView
	 * @param localWeatherData 
	 * @throws IOException 
	 */
	public LocalWeatherView(LocalWeatherData localWeatherData) throws IOException {
		setBackground(Color.WHITE);
		this.setSize(600, 450);
		this.localWeatherData = localWeatherData;
		this.initUI();
	}

	/**
	 * initializes the view panel
	 * currently aligns data labels in a vertical column
	 * @throws IOException 
	 */
	private void initUI() throws IOException {

		initLabels();

		
		topPanel = new JPanel();
		topPanel.setBackground(SystemColor.textHighlight);

		lblWeatherUpdate = new JLabel("Weather Update");
		lblWeatherUpdate.setFont(new Font("Myanmar MN", Font.BOLD, 20));

		lblWeatherReportFor = new JLabel("Weather report for: ");
		lblWeatherReportFor.setFont(new Font("Myanmar MN", Font.PLAIN, 13));
		
		lblCity = new JLabel("No city",JLabel.CENTER);
		lblCity.setFont(new Font("Myanmar MN", Font.PLAIN, 13));
		lblCity.setForeground(labelColor);
		
		lblTime = new JLabel("");
		lblTime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTime.setFont(new Font("Myanmar MN", Font.BOLD, 14));
		
		lblDate = new JLabel("Never");
		lblDate.setFont(new Font("Myanmar MN", Font.BOLD, 14));
		
		//Refresh button action
		btnRefresh.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					setLabels();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		//Team Logo
		URL urlLogo = new URL("http://www.18assetmanagement.com/sites/all/themes/custom/am18/logo.png");
		Image imageLogo = ImageIO.read(urlLogo);
		Image newimgLogo = imageLogo.getScaledInstance(160, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
		lblIcon = new JLabel(new ImageIcon(newimgLogo));
		lblIcon.setHorizontalAlignment(SwingConstants.LEFT);

		rightPanel = new JPanel();
		rightPanel.setBackground(Color.WHITE);

		leftPanel = new JPanel();
		leftPanel.setBackground(Color.WHITE);

		lblWeatherIcon = new JLabel("");
		lblWeatherIcon.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTemperature = new JLabel("N/A",JLabel.CENTER);
		lblTemperature.setFont(new Font("Myanmar MN", Font.BOLD, 60));
		lblTemperature.setForeground(labelColor);
		lblMaxTemperature = new JLabel("High: N/A",JLabel.CENTER);
		lblMaxTemperature.setFont(new Font("Myanmar MN", Font.BOLD, 16));
		lblMaxTemperature.setForeground(Color.RED);
		lblMinTemperature = new JLabel("Low: N/A",SwingConstants.LEFT);
		lblMinTemperature.setFont(new Font("Myanmar MN", Font.BOLD, 16));
		lblMinTemperature.setForeground(Color.BLUE);
		lblSunrise = new JLabel("Sunrise: N/A",SwingConstants.LEFT);
		lblSunrise.setFont(new Font("Myanmar MN", Font.PLAIN, 11));
		lblSunrise.setForeground(labelColor);
		lblSunset = new JLabel("Sunset: N/A",SwingConstants.LEFT);
		lblSunset.setFont(new Font("Myanmar MN", Font.PLAIN, 11));
		lblSunset.setForeground(labelColor); 
		GroupLayout gl_leftPanel = new GroupLayout(leftPanel);
		gl_leftPanel.setHorizontalGroup(
				gl_leftPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_leftPanel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_leftPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblWeatherIcon, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTemperature, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
								.addGroup(gl_leftPanel.createSequentialGroup()
										.addGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(lblMaxTemperature, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblSunrise, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
												.addGap(0)
												.addGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING)
														.addComponent(lblSunset, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblMinTemperature, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))))
														.addContainerGap())
				);
		gl_leftPanel.setVerticalGroup(
				gl_leftPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_leftPanel.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblWeatherIcon, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblTemperature, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
						.addGap(24)
						.addGroup(gl_leftPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMaxTemperature)
								.addComponent(lblMinTemperature))
								.addGap(18)
								.addGroup(gl_leftPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblSunrise)
										.addComponent(lblSunset))
										.addContainerGap(100, Short.MAX_VALUE))
				);
		leftPanel.setLayout(gl_leftPanel);
		lblHumidity = new JLabel("Humidity: N/A",SwingConstants.LEADING);
		lblHumidity.setFont(new Font("Myanmar MN", Font.PLAIN, 16));
		lblHumidity.setForeground(labelColor);
		lblSkyCondition = new JLabel("Sky Condition: N/A",SwingConstants.LEFT);
		lblSkyCondition.setFont(new Font("Myanmar MN", Font.PLAIN, 18));
		lblSkyCondition.setForeground(labelColor);
		lblAirPressure = new JLabel("Air Pressure: N/A",SwingConstants.LEADING);
		lblAirPressure.setFont(new Font("Myanmar MN", Font.PLAIN, 16));
		lblAirPressure.setForeground(labelColor);
		lblWind = new JLabel("Wind: N/A",SwingConstants.LEADING);
		lblWind.setFont(new Font("Myanmar MN", Font.PLAIN, 16));
		lblWind.setForeground(labelColor);
		GroupLayout gl_rightPanel = new GroupLayout(rightPanel);
		gl_rightPanel.setHorizontalGroup(
				gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rightPanel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblAirPressure, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblWind, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHumidity, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSkyCondition, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE))
								.addContainerGap())
				);
		gl_rightPanel.setVerticalGroup(
				gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rightPanel.createSequentialGroup()
						.addGap(8)
						.addComponent(lblSkyCondition)
						.addGap(31)
						.addComponent(lblHumidity)
						.addGap(33)
						.addComponent(lblAirPressure)
						.addGap(37)
						.addComponent(lblWind)
						.addContainerGap(59, Short.MAX_VALUE))
				);
		rightPanel.setLayout(gl_rightPanel);
		
		
		btnRefresh.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(0, 0, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(leftPanel, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(42)
									.addComponent(rightPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnRefresh)
									.addGap(21))))
						.addComponent(topPanel, GroupLayout.PREFERRED_SIZE, 601, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(topPanel, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(rightPanel, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
							.addComponent(btnRefresh))
						.addComponent(leftPanel, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		lblUpdate = new JLabel("Last Update:");
		GroupLayout gl_topPanel = new GroupLayout(topPanel);
		gl_topPanel.setHorizontalGroup(
			gl_topPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topPanel.createSequentialGroup()
					.addGap(6)
					.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_topPanel.createSequentialGroup()
							.addGap(44)
							.addComponent(lblWeatherUpdate)
							.addGap(12))
						.addGroup(Alignment.TRAILING, gl_topPanel.createSequentialGroup()
							.addGap(47)
							.addComponent(lblWeatherReportFor)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCity, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addPreferredGap(ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_topPanel.createSequentialGroup()
							.addComponent(lblUpdate)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblTime, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
					.addGap(31))
		);
		gl_topPanel.setVerticalGroup(
			gl_topPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_topPanel.createSequentialGroup()
					.addGap(6)
					.addComponent(lblWeatherUpdate)
					.addGap(25))
				.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_topPanel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblUpdate)
							.addComponent(lblDate))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblTime, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addGap(21))
					.addGroup(gl_topPanel.createSequentialGroup()
						.addGap(35)
						.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblWeatherReportFor)
							.addComponent(lblCity))))
		);
		topPanel.setLayout(gl_topPanel);
		setLayout(groupLayout);
	}

	/**
	 * Initializes the data labels and sets their colour
	 */
	private void initLabels(){
	}

	/**
	 * updates the text labels for various information
	 * @throws IOException 
	 */
	public void setLabels() throws IOException{
		//determine whether C or F selected
		char currentTempValue = localWeatherData.getUnit();

		lblCity.setText(cityName);

		//Get String of all values for value checks
		String currentTemp = localWeatherData.getTemperature();
		String minTemp = localWeatherData.getMinTemperature();
		String maxTemp = localWeatherData.getMaxTemperature();
		String airPressure = localWeatherData.getAirPressure();
		String humidity = localWeatherData.getHumidity();
		String wind = localWeatherData.getWindSpeed();
		String na = "Not available";

		//Check if temp values exist and display accordingly
		if(!(currentTemp == na)){
			if(currentTemp.contains(".")) {
				String newcurrentTemp = localWeatherData.getTemperature().substring(0, localWeatherData.getTemperature().indexOf('.'));
				lblTemperature.setText(newcurrentTemp + "¼" + currentTempValue);
			}
			else lblTemperature.setText(currentTemp + "¼" + currentTempValue);
		}

		if(!(minTemp == na)) { 
			if(minTemp.contains(".")) {
				String newMinTemp = localWeatherData.getMinTemperature().substring(0, localWeatherData.getMinTemperature().indexOf('.'));
				lblMinTemperature.setText(newMinTemp + "¼" + currentTempValue);
			}
			else lblMinTemperature.setText(minTemp + "¼" + currentTempValue);
		}

		if(!(maxTemp == na)) {
			if(maxTemp.contains(".")) {
				String newMaxTemp = localWeatherData.getMaxTemperature().substring(0, localWeatherData.getMaxTemperature().indexOf('.'));
				lblMaxTemperature.setText(newMaxTemp + "¼" + currentTempValue);
			}
			else lblMaxTemperature.setText(maxTemp + "¼" + currentTempValue);
		}

		//Check if other values exist, and display accordingly 
		if(!(airPressure == na)) 
			lblAirPressure.setText("Air Pressure: " + localWeatherData.getAirPressure() + " hPa");
		
		if(!(humidity == na)) 
			lblHumidity.setText("Humidity: " + localWeatherData.getHumidity() + "%");

		if(!(wind == na)) 
			lblWind.setText("Wind Speed: " + localWeatherData.getWindSpeed() + " m/s " + localWeatherData.getWindDirection());
		
		
		lblSunrise.setText("Sunrise: " + localWeatherData.getTimeSunrise());
		lblSunset.setText("Sunset: " + localWeatherData.getTimeSunset()); 
		lblSkyCondition.setText(localWeatherData.getSkyCondition().toUpperCase());


		//Display sky icon by creating URL link based on current sky icon
		String icon = localWeatherData.getSkyIcon();
		String urlIcon = "http://openweathermap.org/img/w/"+ icon + ".png";
		URL urlSkyIcon = new URL(urlIcon);
		ImageIcon imageIcon = new ImageIcon(urlSkyIcon);
		
		//Resize Icon to make it larger
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back

		lblWeatherIcon.setIcon(imageIcon);  
		
		//Update time label
		String dateStamp = new SimpleDateFormat("MMMM dd, yyyy").format(Calendar.getInstance().getTime());
		String timeStamp = new SimpleDateFormat("hh:mm:ss a").format(Calendar.getInstance().getTime());
		lblDate.setText(dateStamp);
		lblTime.setText(timeStamp);
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}
