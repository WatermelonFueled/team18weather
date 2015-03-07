/**
 *  
 *
 */
public class LocalWeatherData {
	
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
		this.windSpeed = windSpeed;
	}
	
	/**
	 * @return the windDirection
	 */
	public String getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
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
		this.humidity = humidity;
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
			
	

}