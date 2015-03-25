package ca.uwo.csd.cs2212.team18;

/**
 * LongTermData class: holds and returns values for the long term view.
 * Holds the min and max temperature and sky condition values in string arrays
 * for the following 7 days.
 */
public class LongTermData{
    private String[] temperatureMin;
    private String[] temperatureMax;
    private String[] skyCondition;
    private char unit;
    
    /**
     * constructor creates the string arrays for values; length 7 (days)
     */
    LongTermData(){
        temperatureMin = new String[7];
        temperatureMax = new String[7];
        skyCondition = new String[7];
    }

    /**
     * @return minimum temperature array
     */
    public String[] getTemperatureMin() {
        return temperatureMin;
    }
    
    /**
     * @return maximum temperature array
     */
    public String[] getTemperatureMax() {
        return temperatureMax;
    }

    /**
     * @return sky condition array
     */
    public String[] getSkyCondition() {
        return skyCondition;
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