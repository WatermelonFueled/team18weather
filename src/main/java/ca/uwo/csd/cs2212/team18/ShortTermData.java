package ca.uwo.csd.cs2212.team18;

import java.util.ArrayList;
import java.util.List;

/**
 * ShortTermData class: holds and returns weather values from every 3 hour
 * increment. Each increment has a LocalWeatherData object holding values
 * for each.
 */
public class ShortTermData{
    
    private List<LocalWeatherData> data;
    private char unit; // C or F
    
    public ShortTermData(){
        data = new ArrayList<LocalWeatherData>();
    }
    
    /**
     * add an item to list
     * @param weatherData 
     */
    public void addShortTermData(LocalWeatherData weatherData){
        data.add(weatherData);
    }
    
    /**
     * clear data
     */
    public void clear(){
        data.clear();
    }

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