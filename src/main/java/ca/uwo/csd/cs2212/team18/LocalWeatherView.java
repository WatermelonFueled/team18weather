package ca.uwo.csd.cs2212.team18;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

public class LocalWeatherView extends JPanel {

    private static final long serialVersionUID = -5393294539017896082L;
    
    //weather info labels
    private JLabel lblCity;
    private JLabel lblTemperature;
    private JLabel lblSkyPicture;
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

    /**
     * Constructor for LocalWeatherView
     * @param localWeatherData 
     */
    public LocalWeatherView(LocalWeatherData localWeatherData) {
        this.localWeatherData = localWeatherData;
        this.initUI();
    }

    /**
     * initializes the view panel
     * currently aligns data labels in a vertical column
     */
    private void initUI() {
        
        initLabels();

        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(lblTemperature)
                                        .addComponent(lblSkyCondition)
                                        .addComponent(lblMaxTemperature)
                                        .addComponent(lblMinTemperature)
                                        .addComponent(lblSunrise)
                                        .addComponent(lblSunset)
                                        .addComponent(lblAirPressure)
                                        .addComponent(lblHumidity)
                                        .addComponent(lblWindSpeed)
                                        .addComponent(lblWindDirection)
                                )
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblMaxTemperature)
                                                .addComponent(lblMinTemperature)
                                        )
                                )
                        //.addComponent(txtLocation)
                        //)
                        )
                )
        );
						//.addComponent(lblText)
        //	)
        //	.addComponent(btnSearch) ); 
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTemperature)
                //.addComponent(lblmaxtemp)
                //.addComponent(lblsunrise)
                //.addComponent(lblsunset)

                //.addComponent(txtLocation)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSkyCondition)
                                .addComponent(lblMaxTemperature)
                                .addComponent(lblMinTemperature)
                                .addComponent(lblSunrise)
                                .addComponent(lblSunset)
                                .addComponent(lblAirPressure)
                                .addComponent(lblHumidity)
                                .addComponent(lblWindSpeed)
                                .addComponent(lblWindDirection)
                        )
                //.addComponent(lblText)
                //	.addComponent(btnSearch) 
                )
        );
        
        this.setLayout(layout);
        
        /*
        JPanel pnlEast = new JPanel();
        pnlEast.setBackground(getBackground());
        pnlEast.setPreferredSize(new Dimension(100, 200));

        JPanel pnlNorth = new JPanel();
        pnlNorth.setBackground(getBackground());
        pnlNorth.setPreferredSize(new Dimension(300, 50));
        lblText = new JLabel("Current Location Goes Here");
        pnlNorth.add(lblText);

        JPanel pnlSouth = new JPanel();
        pnlSouth.setBackground(getBackground());
        pnlSouth.setPreferredSize(new Dimension(300, 25));
        lblText = new JLabel("Last Update Goes Here");
        pnlSouth.add(lblText);

        this.setLayout(new BorderLayout());
        this.add(this.createForm(), BorderLayout.CENTER);

        this.add(pnlEast, BorderLayout.EAST);
        //this.add(pnlWest, BorderLayout.WEST);
        this.add(pnlNorth, BorderLayout.NORTH);
        this.add(pnlSouth, BorderLayout.SOUTH);
        */
    }

    /**
     * Initializes the data labels and sets their colour
     */
    private void initLabels(){
        lblCity = new JLabel("No city",JLabel.CENTER);
        lblTemperature = new JLabel("Current Temperature: ",JLabel.CENTER);
        lblSkyPicture = new JLabel("PICTURE OF CURRENT WEATHER",JLabel.CENTER);
        lblAirPressure = new JLabel("Air Pressure: ",JLabel.CENTER);
        lblHumidity = new JLabel("Humidity: ",JLabel.CENTER);
        lblWindSpeed = new JLabel("Wind Speed: ",JLabel.CENTER);
        lblWindDirection = new JLabel("Wind Direction: ",JLabel.CENTER);
        lblSkyCondition = new JLabel("Sky Condition: ",JLabel.CENTER);
        lblMinTemperature = new JLabel("Minimum Temperature: ",JLabel.CENTER);
        lblMaxTemperature = new JLabel("Maximum Temperature: ",JLabel.CENTER);
        lblSunrise = new JLabel("Sunrise: ",JLabel.CENTER);
        lblSunset = new JLabel("Sunset: ",JLabel.CENTER);
        
        lblCity.setForeground(labelColor);
        lblTemperature.setForeground(labelColor);
        lblSkyPicture.setForeground(labelColor);
        lblAirPressure.setForeground(labelColor);
        lblHumidity.setForeground(labelColor);
        lblWindSpeed.setForeground(labelColor);
        lblWindDirection.setForeground(labelColor);
        lblSkyCondition.setForeground(labelColor);
        lblMinTemperature.setForeground(labelColor);
        lblMaxTemperature.setForeground(labelColor);
        lblSunrise.setForeground(labelColor);
        lblSunset.setForeground(labelColor); 
    }
    
    /**
     * updates the text labels for various information
     */
    public void setLabels(){
        lblCity.setText(cityName);
        lblTemperature.setText("Current Temperature: " + localWeatherData.getTemperature());
        lblSkyPicture.setText("PICTURE OF CURRENT WEATHER");
        lblAirPressure.setText("Air Pressure: " + localWeatherData.getAirPressure());
        lblHumidity.setText("Humidity: " + localWeatherData.getHumidity());
        lblWindSpeed.setText("Wind Speed: " + localWeatherData.getWindSpeed());
        lblWindDirection.setText("Wind Direction: " + localWeatherData.getWindDirection());
        lblSkyCondition.setText("Sky Condition: " + localWeatherData.getSkyCondition());
        lblMinTemperature.setText("Minimum Temperature: " + localWeatherData.getMinTemperature());
        lblMaxTemperature.setText("Maximum Temperature: " + localWeatherData.getMaxTemperature());
        lblSunrise.setText("Sunrise: " + localWeatherData.getTimeSunrise());
        lblSunset.setText("Sunset: " + localWeatherData.getTimeSunset());   
    }
    
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}
