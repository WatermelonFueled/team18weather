package ca.uwo.csd.cs2212.team18;

import java.util.ArrayList;
import java.util.List;

/**
 * ShortTermData class: holds and returns weather values from every 3 hour
 * increment. Each increment has a LocalWeatherData object holding values
 * for each.
 * @author Samirah
 */
public class ShortTermData{
    
    private List<LocalWeatherData> data;
    private char unit; // C or F
    
    /**
     * Constructor for ShortTermData
     */
    public ShortTermData(){
        data = new ArrayList<LocalWeatherData>();
    }
    
    /**
     * Add an item to list
     * @param weatherData 
     */
    public void addShortTermData(LocalWeatherData weatherData){
        data.add(weatherData);
    }
    
    /**
     * Clear data
     */
    public void clear(){
        data.clear();
    }

    /**
     * Returns the list of local weather data object
     * @return data
     */
    public List<LocalWeatherData> getData() {
        return data;
    }
    
    /**
     * Sets the temperature unit symbol
     * @param unit 
     */
    public void setUnit(char unit){
        this.unit = unit;
    }		
    
    /**
     * Returns the temperature unit symbol
     * @return unit of temperature
     */
    public char getUnit(){
        return unit;
    }
}