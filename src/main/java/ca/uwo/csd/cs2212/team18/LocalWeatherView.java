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
        
        topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        
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
        
        GroupLayout gl_topPanel = new GroupLayout(topPanel);
        gl_topPanel.setHorizontalGroup(
        	gl_topPanel.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_topPanel.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
        			.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_topPanel.createSequentialGroup()
        					.addGap(34)
        					.addComponent(lblWeatherReportFor)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(lblCity))
        				.addGroup(gl_topPanel.createSequentialGroup()
        					.addGap(18)
        					.addComponent(lblWeatherUpdate)))
        			.addGap(127))
        );
        gl_topPanel.setVerticalGroup(
        	gl_topPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_topPanel.createSequentialGroup()
        			.addGap(6)
        			.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
        				.addGroup(gl_topPanel.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(lblWeatherUpdate)
        					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lblWeatherReportFor)
        						.addComponent(lblCity))
        					.addGap(52))))
        );
        topPanel.setLayout(gl_topPanel);
        
        rightPanel = new JPanel();
        
        leftPanel = new JPanel();
        
        lblWeatherIcon = new JLabel("Weather Icon");
        lblWeatherIcon.setHorizontalAlignment(SwingConstants.CENTER);
        lblTemperature = new JLabel("<<TEMP>>",JLabel.CENTER);
        lblTemperature.setFont(new Font("Monaco", Font.BOLD, 21));
        lblTemperature.setForeground(labelColor);
        lblMaxTemperature = new JLabel("High:",JLabel.CENTER);
        lblMaxTemperature.setFont(new Font("Monaco", Font.BOLD, 13));
        lblMaxTemperature.setForeground(Color.RED);
        lblMinTemperature = new JLabel("Low:",JLabel.CENTER);
        lblMinTemperature.setFont(new Font("Monaco", Font.BOLD, 13));
        lblMinTemperature.setForeground(Color.BLUE);
        lblSunrise = new JLabel("Sunrise: ",SwingConstants.LEFT);
        lblSunrise.setFont(new Font("Monaco", Font.PLAIN, 11));
        lblSunrise.setForeground(labelColor);
        lblSunset = new JLabel("Sunset: ",SwingConstants.LEFT);
        lblSunset.setFont(new Font("Monaco", Font.PLAIN, 11));
        lblSunset.setForeground(labelColor); 
        GroupLayout gl_leftPanel = new GroupLayout(leftPanel);
        gl_leftPanel.setHorizontalGroup(
        	gl_leftPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_leftPanel.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblTemperature, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
        				.addComponent(lblWeatherIcon, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
        				.addGroup(Alignment.TRAILING, gl_leftPanel.createSequentialGroup()
        					.addGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblMaxTemperature, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lblSunrise, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
        					.addGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING)
        						.addGroup(gl_leftPanel.createSequentialGroup()
        							.addGap(34)
        							.addComponent(lblMinTemperature, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
        						.addGroup(gl_leftPanel.createSequentialGroup()
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(lblSunset, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        			.addContainerGap())
        );
        gl_leftPanel.setVerticalGroup(
        	gl_leftPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_leftPanel.createSequentialGroup()
        			.addComponent(lblWeatherIcon, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(lblTemperature)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(gl_leftPanel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblMaxTemperature)
        				.addComponent(lblMinTemperature))
        			.addGap(18)
        			.addGroup(gl_leftPanel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblSunrise)
        				.addComponent(lblSunset))
        			.addContainerGap(33, Short.MAX_VALUE))
        );
        leftPanel.setLayout(gl_leftPanel);
        lblHumidity = new JLabel("Humidity: ",SwingConstants.LEADING);
        lblHumidity.setFont(new Font("Monaco", Font.PLAIN, 13));
        lblHumidity.setForeground(labelColor);
        lblSkyCondition = new JLabel("Sky Condition: ",JLabel.CENTER);
        lblSkyCondition.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        lblSkyCondition.setForeground(labelColor);
        lblAirPressure = new JLabel("Air Pressure: ",SwingConstants.LEADING);
        lblAirPressure.setFont(new Font("Monaco", Font.PLAIN, 13));
        lblAirPressure.setForeground(labelColor);
        lblWind = new JLabel("Wind: ",SwingConstants.LEADING);
        lblWind.setFont(new Font("Monaco", Font.PLAIN, 13));
        lblWind.setForeground(labelColor);
        GroupLayout gl_rightPanel = new GroupLayout(rightPanel);
        gl_rightPanel.setHorizontalGroup(
        	gl_rightPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_rightPanel.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblSkyCondition)
        				.addComponent(lblAirPressure, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
        				.addComponent(lblHumidity, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
        				.addComponent(lblWind, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
        			.addGap(30))
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
        			.addContainerGap(83, Short.MAX_VALUE))
        );
        rightPanel.setLayout(gl_rightPanel);
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(6)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(topPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(leftPanel, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
        					.addGap(42)
        					.addComponent(rightPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addGap(150))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(6)
        			.addComponent(topPanel, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(leftPanel, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
        				.addComponent(rightPanel, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        setLayout(groupLayout);
    }

    /**
     * Initializes the data labels and sets their colour
     */
    private void initLabels(){
    }
    
    /**
     * updates the text labels for various information
     */
    public void setLabels(){
    	//determine whether C or F selected
    	char currentTempValue = localWeatherData.getUnit();
    	
        lblCity.setText(cityName);
	//lblTemperature.setText(localWeatherData.getTemperature().substring(0, 4) + "¡" + currentTempValue);
        lblTemperature.setText(localWeatherData.getTemperature() + "¡" + currentTempValue);
        lblAirPressure.setText("Air Pressure: " + localWeatherData.getAirPressure() + " hpa");
        lblHumidity.setText("Humidity: " + localWeatherData.getHumidity() + "%");
        lblWind.setText("Wind Speed: " + localWeatherData.getWindSpeed() + " m/s " + localWeatherData.getWindDirection());
        lblMinTemperature.setText(localWeatherData.getMinTemperature()+ "¡" + currentTempValue); 
        lblMaxTemperature.setText(localWeatherData.getMaxTemperature()+ "¡" + currentTempValue);
        lblSunrise.setText("Sunrise: " + localWeatherData.getTimeSunrise());
        lblSunset.setText("Sunset: " + localWeatherData.getTimeSunset()); 
        lblSkyCondition.setText(localWeatherData.getSkyCondition().toUpperCase());
       
        /*
         * Add Icon display here
         */
    }
    
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}
