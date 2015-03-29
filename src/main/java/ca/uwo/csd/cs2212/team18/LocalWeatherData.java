package ca.uwo.csd.cs2212.team18; 
/**
 * LocalWeatherData class: holds and returns values for the local weather view
 */
public class LocalWeatherData {
	
        private String time;
	private String temperature;
	private String windSpeed;
	private String windDirection;
	private String skyCondition;
	private String humidity;
	private String airPressure;
	private String minTemperature;
	private String maxTemperature;
	private String timeSunrise;
	private String timeSunset;
	private String skyIcon;
	private String timeUpdated;
	private char unit; //C or F
        
	/**
	 * constructor
	 */
	LocalWeatherData() {
		/*temperature = 0;
		windSpeed= 0;
		windDirection =0;
		skyCondition= "";
		humidity = 0;
		airPressure = 0;
		minTemperature = 0;
		maxTemperature = 0;
		timeSunrise = "00:00:00";
		timeSunset = "00:00:00";
		*/
	}
	
	/**
	 * @return the temperature
	 */
	public String getTemperature() {
		return temperature;
	}
	
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	/**
	 * @return the windSpeed
	 */
	public String getWindSpeed() {
		return windSpeed;
	}
	
	/**
	 * @return the windDirection
	 */
	public void setWindSpeed(String windSpeed) {
		if (windSpeed != null){
			this.windSpeed = windSpeed;
		} else {
			this.windSpeed = "Not available";
		}
	}
	
	/**
	 * @return the windDirection
	 */
	public String getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(String windDirection) {
		if (windDirection != null){
			double degree = Double.parseDouble(windDirection);
			String directions[] = {"N", "NE", "E", "SE", "S", "SW", "W", "NW", "N"};
			this.windDirection = directions[ (int)Math.round((  ((double)degree % 360) / 45)) ];
		} else {
			this.windDirection = "Not available";
		}
	}

	/**
	 * @return the skyCondition
	 */
	public String getSkyCondition() {
		return skyCondition;
	}

	/**
	 * @param skyCondition the skyCondition to set
	 */
	public void setSkyCondition(String skyCondition) {
		this.skyCondition = skyCondition;
	}

	/**
	 * @return the humidity
	 */
	public String getHumidity() {
		return humidity;
	}

	/**
	 * @param humidity the humidity to set
	 */
	public void setHumidity(String humidity) {
		if (humidity != null){
			this.humidity = humidity;
		}else {
			this.humidity = "Not available";
		}
	}

	/**
	 * @return the airPressure
	 */
	public String getAirPressure() {
		return airPressure;
	}

	/**
	 * @param airPressure the airPressure to set
	 */
	public void setAirPressure(String airPressure) {
		this.airPressure = airPressure;
	}

	/**
	 * @return the minTemperature
	 */
	public String getMinTemperature() {
		return minTemperature;
	}

	/**
	 * @param minTemperature the minTemperature to set
	 */
	public void setMinTemperature(String minTemperature) {
		this.minTemperature = minTemperature;
	}

	/**
	 * @return the maxTemperature
	 */
	public String getMaxTemperature() {
		return maxTemperature;
	}

	/**
	 * @param maxTemperature the maxTemperature to set
	 */
	public void setMaxTemperature(String maxTemperature) {
		this.maxTemperature = maxTemperature;
	}

	/**
	 * @return the timeSunrise
	 */
	public String getTimeSunrise() {
		return timeSunrise;
	}

	/**
	 * @param timeSunrise: the time of Sunrise to set (00:00)
	 */
	public void setTimeSunrise(String timeSunrise) {
		this.timeSunrise = timeSunrise;
	}

	/**
	 * @return the timeSunset
	 */
	public String getTimeSunset() {
		return timeSunset;
	}

	/**
	 * @param timeSunset: the time of Sunset to set (00:00)
	 */
	public void setTimeSunset(String timeSunset) {
		this.timeSunset = timeSunset;
	}

	
	public void setSkyIcon(String skyIconNum) {
		this.skyIcon = skyIconNum;
	}
	
	public String getSkyIcon(){
		return skyIcon;
	}
	
	
	public String getTimeUpdated(){
		return timeUpdated;
	}
	
	public void setTimeUpdated(String timeUpdated){
		this.timeUpdated = timeUpdated;
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

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

	
}
