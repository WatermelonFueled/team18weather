package ca.uwo.csd.cs2212.team18;

import java.util.ArrayList;
import java.util.List;

/**
 * This part written by Samirah
 */
public class ShortTermData{
    
    private List<LocalWeatherData> data;
    private char unit;
    
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
    
    public void setUnit(char unit){
        this.unit = unit;
    }		

    public char getUnit(){
        return unit;
    }
}