package ca.uwo.csd.cs2212.team18;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

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
    private JLabel lblWindSpeed;
    private JLabel lblWindDirection;
    private JLabel lblSkyCondition;
    private JLabel lblMinTemperature;
    private JLabel lblMaxTemperature;
    private JLabel lblSunrise;
    private JLabel lblSunset;
    
    final private Color labelColor = Color.BLACK;
    
    private LocalWeatherData localWeatherData;
    private String cityName;
    private JPanel panel;
    private JLabel lblWeatherUpdate;
    private JLabel lblWeatherReportFor;
    private JLabel lblWeatherIcon;
    private JLabel lblIcon;

    /**
     * Constructor for LocalWeatherView
     * @param localWeatherData 
     * @throws IOException 
     */
    public LocalWeatherView(LocalWeatherData localWeatherData) throws IOException {
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
        
        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        
        lblWeatherUpdate = new JLabel("Weather Update");
        lblWeatherUpdate.setFont(new Font("Monaco", Font.BOLD, 16));
        
        lblWeatherReportFor = new JLabel("Weather report for: ");
        lblWeatherReportFor.setFont(new Font("Monaco", Font.PLAIN, 13));
        lblCity = new JLabel("No city",JLabel.CENTER);
        lblCity.setFont(new Font("Monaco", Font.PLAIN, 13));
        
        lblCity.setForeground(labelColor);
        
        //top left icon
        URL url = new URL("http://files.softicons.com/download/toolbar-icons/mono-reflection-yellow-icons-by-double-j-design/png/64/weather-sun.png");
        Image image = ImageIO.read(url);
        lblIcon = new JLabel(new ImageIcon(image));
        
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(34)
        					.addComponent(lblWeatherReportFor)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(lblCity))
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(18)
        					.addComponent(lblWeatherUpdate)))
        			.addGap(127))
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(6)
        			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(lblWeatherUpdate)
        					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lblWeatherReportFor)
        						.addComponent(lblCity))
        					.addGap(52))))
        );
        panel.setLayout(gl_panel);
        
        lblWeatherIcon = new JLabel("Weather Icon");
        lblWeatherIcon.setHorizontalAlignment(SwingConstants.CENTER);
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 439, GroupLayout.PREFERRED_SIZE))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(groupLayout.createSequentialGroup()
        							.addGap(16)
        							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        								.addGroup(groupLayout.createSequentialGroup()
        									.addComponent(lblMaxTemperature, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
        									.addGap(18)
        									.addComponent(lblMinTemperature, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
        									.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE))
        								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        										.addComponent(lblTemperature, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
        										.addGroup(groupLayout.createSequentialGroup()
        											.addComponent(lblSunrise, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        											.addGap(18)
        											.addComponent(lblSunset, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)))
        									.addGap(22))))
        						.addGroup(groupLayout.createSequentialGroup()
        							.addGap(34)
        							.addComponent(lblWeatherIcon, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)))
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(groupLayout.createSequentialGroup()
        							.addGap(60)
        							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        								.addComponent(lblHumidity)
        								.addGroup(groupLayout.createSequentialGroup()
        									.addComponent(lblWindSpeed)
        									.addGap(14)
        									.addComponent(lblWindDirection))
        								.addComponent(lblAirPressure)))
        						.addGroup(groupLayout.createSequentialGroup()
        							.addGap(32)
        							.addComponent(lblSkyCondition)))))
        			.addGap(5))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(panel, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(52)
        					.addComponent(lblSkyCondition)
        					.addGap(18)
        					.addComponent(lblHumidity))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(18)
        					.addComponent(lblWeatherIcon, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lblAirPressure)
        						.addComponent(lblTemperature))))
        			.addGap(21)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblWindSpeed)
        				.addComponent(lblWindDirection)
        				.addComponent(lblMaxTemperature)
        				.addComponent(lblMinTemperature))
        			.addGap(18)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblSunrise)
        				.addComponent(lblSunset))
        			.addGap(121))
        );
        setLayout(groupLayout);
    }

    /**
     * Initializes the data labels and sets their colour
     */
    private void initLabels(){
        lblTemperature = new JLabel("<<TEMP>>",JLabel.CENTER);
        lblTemperature.setFont(new Font("Lucida Grande", Font.BOLD, 21));
        lblAirPressure = new JLabel("Air Pressure: ",JLabel.CENTER);
        lblHumidity = new JLabel("Humidity: ",JLabel.CENTER);
        lblWindSpeed = new JLabel("Wind Speed: ",JLabel.CENTER);
        lblWindDirection = new JLabel("direction",JLabel.CENTER);
        lblSkyCondition = new JLabel("Sky Condition: ",JLabel.CENTER);
        lblSkyCondition.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        lblMinTemperature = new JLabel("Low:",JLabel.CENTER);
        lblMinTemperature.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        lblMaxTemperature = new JLabel("High:",JLabel.CENTER);
        lblMaxTemperature.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        lblSunrise = new JLabel("Sunrise: ",JLabel.CENTER);
        lblSunrise.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
        lblSunset = new JLabel("Sunset: ",JLabel.CENTER);
        lblSunset.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
        lblTemperature.setForeground(labelColor);
        lblAirPressure.setForeground(labelColor);
        lblHumidity.setForeground(labelColor);
        lblWindSpeed.setForeground(labelColor);
        lblWindDirection.setForeground(labelColor);
        lblSkyCondition.setForeground(labelColor);
        lblMinTemperature.setForeground(Color.BLUE);
        lblMaxTemperature.setForeground(Color.RED);
        lblSunrise.setForeground(labelColor);
        lblSunset.setForeground(labelColor); 
    }
    
    /**
     * updates the text labels for various information
     */
    public void setLabels(){
        lblCity.setText(cityName);
        lblTemperature.setText(localWeatherData.getTemperature().substring(0, 4));
        lblAirPressure.setText("Air Pressure: " + localWeatherData.getAirPressure());
        lblHumidity.setText("Humidity: " + localWeatherData.getHumidity());
        lblWindSpeed.setText("Wind Speed: " + localWeatherData.getWindSpeed());
        lblMinTemperature.setText(localWeatherData.getMinTemperature());
        lblMaxTemperature.setText(localWeatherData.getMaxTemperature());
        lblSunrise.setText("Sunrise: " + localWeatherData.getTimeSunrise());
        lblSunset.setText("Sunset: " + localWeatherData.getTimeSunset()); 
        
        ///////////////////////////////////////////////////////////////////////////////////////
        //Translate into NE-SW arrows?
        lblWindDirection.setText("Wind Direction: " + localWeatherData.getWindDirection());
        ///////////////////////////////////////////////////////////////////////////////////////
        

        ///////////////////////////////////////////////////////////////////////////////////////
        //Translate sky condition into Image
        lblSkyCondition.setText("Sky Condition: " + localWeatherData.getSkyCondition());
        ///////////////////////////////////////////////////////////////////////////////////////
       
    }
    
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}
