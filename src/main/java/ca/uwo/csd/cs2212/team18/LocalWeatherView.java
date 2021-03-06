package ca.uwo.csd.cs2212.team18;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

/**
 * <h1> Local Weather View </h1>
 * 
 * Class creates a panel to display local weather data
 * 
 * @author DaParkVid, DianaGodoy
 */

public class LocalWeatherView extends JPanel {

	private static final long serialVersionUID = -5393294539017896082L;

	//Initializing variables
	private String cityName;
	final private Color labelColor = Color.BLACK;
	private LocalWeatherData localWeatherData;

	//Info labels
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
	private JLabel lblTime;
	private JLabel lblDate;
	private JLabel lblUpdate;
	private JLabel lblWeatherUpdate;
	private JLabel lblWeatherReportFor;
	private JLabel lblWeatherIcon;
	private JLabel lblIcon;

	//Initializing panels
	private JPanel topPanel;
	private JPanel rightPanel;
	private JPanel leftPanel;
	private RefreshButton btnRefresh;

	/**
	 * Constructor for LocalWeatherView
	 * @param localWeatherData 
	 * @param btnRefresh
	 * @throws IOException 
	 */
	public LocalWeatherView(LocalWeatherData localWeatherData, RefreshButton btnRefresh) throws IOException {
		setBackground(Color.WHITE);
		this.setSize(600, 450);
		this.localWeatherData = localWeatherData;
		this.btnRefresh = btnRefresh;
		this.initUI();
	}

	/**
	 * Initializes the view panel
	 * @throws IOException 
	 */
	private void initUI() throws IOException {

		//Setting panels
		topPanel = new JPanel();
		topPanel.setBackground(SystemColor.textHighlight);
		rightPanel = new JPanel();
		rightPanel.setBackground(Color.WHITE);
		leftPanel = new JPanel();
		leftPanel.setBackground(Color.WHITE);

		//Setting text for labels
		lblWeatherUpdate = new JLabel("Weather Update");
		lblWeatherReportFor = new JLabel("Weather report for: ");
		lblCity = new JLabel("No city",SwingConstants.LEFT);
		lblTime = new JLabel("");
		lblDate = new JLabel("Never");
		lblWeatherIcon = new JLabel("");
		lblTemperature = new JLabel("N/A",JLabel.CENTER);
		lblMaxTemperature = new JLabel("High: N/A",JLabel.CENTER);
		lblHumidity = new JLabel("Humidity: N/A",SwingConstants.LEADING);
		lblMinTemperature = new JLabel("Low: N/A",SwingConstants.LEFT);
		lblSunrise = new JLabel("Sunrise: N/A",SwingConstants.LEFT);
		lblSunset = new JLabel("Sunset: N/A",SwingConstants.LEFT);
		lblSkyCondition = new JLabel("Sky Condition: N/A",SwingConstants.LEFT);
		lblAirPressure = new JLabel("Air Pressure: N/A",SwingConstants.LEADING);
		lblWind = new JLabel("Wind: N/A",SwingConstants.LEADING);
		lblUpdate = new JLabel("Last Update:");

		//Set font and size of font of labels
		lblWeatherUpdate.setFont(new Font("Myanmar MN", Font.BOLD, 20));
		lblWeatherReportFor.setFont(new Font("Myanmar MN", Font.PLAIN, 13));
		lblCity.setFont(new Font("Myanmar MN", Font.PLAIN, 13));
		lblTime.setFont(new Font("Myanmar MN", Font.BOLD, 14));
		lblDate.setFont(new Font("Myanmar MN", Font.BOLD, 14));
		lblTemperature.setFont(new Font("Myanmar MN", Font.BOLD, 60));
		lblMaxTemperature.setFont(new Font("Myanmar MN", Font.BOLD, 16));
		lblMinTemperature.setFont(new Font("Myanmar MN", Font.BOLD, 16));
		lblSunrise.setFont(new Font("Myanmar MN", Font.PLAIN, 11));
		lblSunset.setFont(new Font("Myanmar MN", Font.PLAIN, 11));
		lblHumidity.setFont(new Font("Myanmar MN", Font.PLAIN, 16));
		lblSkyCondition.setFont(new Font("Myanmar MN", Font.PLAIN, 18));
		lblAirPressure.setFont(new Font("Myanmar MN", Font.PLAIN, 16));
		lblWind.setFont(new Font("Myanmar MN", Font.PLAIN, 16));

		//Setting color of items
		lblCity.setForeground(labelColor);
		lblTemperature.setForeground(labelColor);
		lblMaxTemperature.setForeground(Color.RED);
		lblMinTemperature.setForeground(Color.BLUE);
		lblSunrise.setForeground(labelColor);
		lblSunset.setForeground(labelColor); 
		lblHumidity.setForeground(labelColor);
		lblSkyCondition.setForeground(labelColor);
		lblAirPressure.setForeground(labelColor);
		lblWind.setForeground(labelColor);
		btnRefresh.setBackground(Color.WHITE);

		//Setting horizontal alignment of labels
		lblTime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWeatherIcon.setHorizontalAlignment(SwingConstants.RIGHT);

		//Team Logo
		URL urlLogo = new URL("http://www.18assetmanagement.com/sites/all/themes/custom/am18/logo.png");
		Image imageLogo = ImageIO.read(urlLogo);
		Image newimgLogo = imageLogo.getScaledInstance(160, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
		lblIcon = new JLabel(new ImageIcon(newimgLogo));
		lblIcon.setHorizontalAlignment(SwingConstants.LEFT);

		//Refresh button action
		btnRefresh.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					btnRefresh.refresh();	
					setLabels();    
				} catch (IOException e1) {}
			}
		});


		//Layout for the Left Panel
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

		//Layout for the Right Panel
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
		
		//Layout for entire Window
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
		
		//Layout for Top Panel
		GroupLayout gl_topPanel = new GroupLayout(topPanel);
		gl_topPanel.setHorizontalGroup(
				gl_topPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topPanel.createSequentialGroup()
						.addGap(6)
						.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_topPanel.createSequentialGroup()
										.addGap(18)
										.addComponent(lblWeatherReportFor)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblCity, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_topPanel.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(lblWeatherUpdate)
												.addPreferredGap(ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
												.addComponent(lblUpdate)
												.addPreferredGap(ComponentPlacement.RELATED)))
												.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
														.addGroup(Alignment.TRAILING, gl_topPanel.createSequentialGroup()
																.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
																.addGap(31))
																.addGroup(gl_topPanel.createSequentialGroup()
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(lblTime, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
																		.addContainerGap())))
				);
		gl_topPanel.setVerticalGroup(
				gl_topPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topPanel.createSequentialGroup()
						.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_topPanel.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblWeatherUpdate)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblWeatherReportFor)
												.addComponent(lblCity)))
												.addGroup(gl_topPanel.createSequentialGroup()
														.addContainerGap()
														.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
																.addComponent(lblDate)
																.addComponent(lblUpdate))
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(lblTime, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)))
																.addGap(6))
				);
		topPanel.setLayout(gl_topPanel);
		setLayout(groupLayout);
	}

	/**
	 * Updates the text labels for various information
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
		if(!(currentTemp.equals(na))){
			if(currentTemp.contains(".")) {
				String newcurrentTemp = localWeatherData.getTemperature().substring(0, localWeatherData.getTemperature().indexOf('.'));
				lblTemperature.setText(newcurrentTemp + "\u00b0" + currentTempValue);
			}
			else lblTemperature.setText(currentTemp + "\u00b0" + currentTempValue);
		}
		else lblTemperature.setText("N/A");

		if(!(minTemp.equals(na))) { 
			if(minTemp.contains(".")) {
				String newMinTemp = localWeatherData.getMinTemperature().substring(0, localWeatherData.getMinTemperature().indexOf('.'));
				lblMinTemperature.setText(newMinTemp + "\u00b0" + currentTempValue);
			}
			else lblMinTemperature.setText(minTemp + "\u00b0" + currentTempValue);
		}
		else lblMinTemperature.setText("N/A");

		if(!(maxTemp.equals(na))) {
			if(maxTemp.contains(".")) {
				String newMaxTemp = localWeatherData.getMaxTemperature().substring(0, localWeatherData.getMaxTemperature().indexOf('.'));
				lblMaxTemperature.setText(newMaxTemp + "\u00b0" + currentTempValue);
			}
			else lblMaxTemperature.setText(maxTemp + "\u00b0" + currentTempValue);
		}
		else lblMinTemperature.setText("N/A");

		//Check if other values exist, and display accordingly 
		if(!(airPressure.equals(na))) 
			lblAirPressure.setText("Air Pressure: " + localWeatherData.getAirPressure() + " hPa");
		else lblAirPressure.setText("N/A");

		if(!(humidity.equals(na))) 
			lblHumidity.setText("Humidity: " + localWeatherData.getHumidity() + "%");
		else lblHumidity.setText("N/A");

		if(!(wind.equals(na))) 
			lblWind.setText("Wind Speed: " + localWeatherData.getWindSpeed() + " m/s " + localWeatherData.getWindDirection());
		else lblWind.setText("N/A");


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
		String timeStamp = new SimpleDateFormat("h:mm a").format(Calendar.getInstance().getTime());
		lblDate.setText(dateStamp);
		lblTime.setText(timeStamp);
	}

	/**
	 * Gets the name of the city for local weather view
	 * @return cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * Sets the name of the city for local weather view
	 * @param cityName
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}
