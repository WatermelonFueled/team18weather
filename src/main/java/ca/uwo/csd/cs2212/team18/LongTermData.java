package ca.uwo.csd.cs2212.team18;

/**
 * <h1> Long Term Data </h1>
 * Class holds and returns values for the long term view.
 * Holds the min and max temperature and sky condition values in string arrays
 * for the following 7 days.
 * @author DaParkVid
 */
public class LongTermData {
	 private String[] temperatureMin = {"N/A","N/A","N/A","N/A","N/A","N/A","N/A"};
	    private String[] temperatureMax = {"N/A","N/A","N/A","N/A","N/A","N/A","N/A"};
	    private String[] skyCondition = {"N/A","N/A","N/A","N/A","N/A","N/A","N/A"};
	    private char unit;
	    
	    /**
	     * constructor creates the string arrays for values; length 7 (days)
	     */
	    LongTermData(){
	        //temperatureMin = new String[7];
	        //temperatureMax = new String[7];
	        //skyCondition = new String[7];
	    }

	    /**
	     * @return temperatureMin minimum temperature array
	     */
	    public String[] getTemperatureMin() {
	        return temperatureMin;
	    }
	    
	    /**
	     * @return temperatureMax maximum temperature array
	     */
	    public String[] getTemperatureMax() {
	        return temperatureMax;
	    }

	    /**
	     * @return skyCondition sky condition array
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
	    
	    public void clear(){
	        temperatureMin = new String[7];
	        temperatureMax = new String[7];
	        skyCondition = new String[7];
	    }
}
