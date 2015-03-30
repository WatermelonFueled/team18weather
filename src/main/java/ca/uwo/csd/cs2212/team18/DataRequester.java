package ca.uwo.csd.cs2212.team18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This class handles all communication with the online weather services
 * (OpenWeatherMap.org or MarsWeather.com) to request and receive data
 * as needed
 */
public class DataRequester {
    private LocalWeatherData localData;
    private ShortTermData shortTermData;
    private LongTermData longTermData;
    private String cityId;
    private JSONParser parser;
    
    public static enum Unit {CELCIUS, FAHRENHEIT};
    public static Unit unit = Unit.CELCIUS;
    
    /**
     * Constructor for DataRequester
     * @param localData LocalWeatherData object
     */
    DataRequester(LocalWeatherData localData, ShortTermData shortTermData, LongTermData longTermData){
        this.localData = localData;
        this.shortTermData = shortTermData;
        this.longTermData = longTermData;
        parser = new JSONParser();
    }

    public void update() throws IOException{
        if (cityId != null){
            if (cityId.equalsIgnoreCase("mars")){
                requestMars();
            } else {
                requestLocal();
                requestShort();
                requestLong();
            }
        } else {
            throw new IOException();
        }
    }
    
    public void update(String id){
        cityId = id;
        try {
            this.update();
        } catch (IOException ex) {
            Logger.getLogger(DataRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * requests for the local forecast info and updates
     * the localData object with new values
     * @param id City id as string
     */
    private void requestLocal(){
        String requestURL = null;
        switch (unit){
            case CELCIUS:
                requestURL = "http://api.openweathermap.org/data/2.5/weather?units=metric&id=" + cityId;
                localData.setUnit('C');
                break;
            case FAHRENHEIT:
                requestURL = "http://api.openweathermap.org/data/2.5/weather?units=imperial&id=" + cityId;
                localData.setUnit('F');
                break;
        }
        
        JSONObject responseJSON = request(requestURL);
        if (responseJSON != null){
            parseLocal(responseJSON,localData);
        }
    }
    
    /**
     * requests for short term forecast info and updates
     * the shortTermData object with new values
     * @param id City id as string
     */
    private void requestShort(){
        String requestURL = null;
        switch (unit){
            case CELCIUS:
                requestURL = "http://api.openweathermap.org/data/2.5/forecast?units=metric&id=" + cityId;
                shortTermData.setUnit('C');
                break;
            case FAHRENHEIT:
                requestURL = "http://api.openweathermap.org/data/2.5/forecast?units=imperial&id=" + cityId;
                shortTermData.setUnit('F');
                break;
        }
        shortTermData.clear(); //delete old items
        JSONObject responseJSON = request(requestURL);
        if (responseJSON != null){
            parseShort(responseJSON);
        }
    }
    
    /**
     * requests for long term forecast info and updates
     * the longTermData object with new values
     * @param id City id as string
     */
    private void requestLong(){
        String requestURL = null;
        switch (unit){
            case CELCIUS:
                requestURL = "http://api.openweathermap.org/data/2.5/forecast/daily?cnt=7&units=metric&id=" + cityId;
                longTermData.setUnit('C');
                break;
            case FAHRENHEIT:
                requestURL = "http://api.openweathermap.org/data/2.5/forecast/daily?cnt=7&units=imperial&id=" + cityId;
                longTermData.setUnit('F');
                break;
        }
        JSONObject responseJSON = request(requestURL);
        if (responseJSON != null){
            parseLong(responseJSON);
        }
    }
    
    /**
     * requests for mars info and updates
     * the localWeatherData object with new values
     */
    private void requestMars(){
        String requestURL = "http://marsweather.ingenology.com/v1/latest/?format=json";
        JSONObject responseJSON = request(requestURL);
        if (responseJSON != null){
            parseMars(responseJSON);
        }
    }
    
    /**
     * handles sending request and catching possible errors and makes
     * JSONObject of the response
     * @param request URL of request
     * @return responseJSON The response from web service JSONObject
     */
    private JSONObject request(String request){
        JSONObject responseJSON = null;
        try{
            String response = this.sendRequest(request); //send to web service
            responseJSON = (JSONObject) parser.parse(response); //response as JSONObject
        } catch (IOException ex){
            Logger.getLogger(DataRequester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex){
            Logger.getLogger(DataRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
        return responseJSON;
    }

    /**
     * Sends the request using HttpURLConnection and returns the response
     * @param request the request to be sent (the url)
     * @return the response from the web service as a String
     * @throws MalformedURLException
     * @throws IOException 
     */
    private String sendRequest(String request) throws MalformedURLException, IOException{
        final int CONNECTION_TIMEOUT_SECONDS = 10;
        URL requestURL = new URL(request);
        HttpURLConnection connection = (HttpURLConnection) requestURL.openConnection();
        connection.setConnectTimeout(CONNECTION_TIMEOUT_SECONDS*1000);
        
        int responseCode = connection.getResponseCode();
        // check if there was a client error during the request
        if (responseCode > 400 || responseCode == -1){
            // (action to take with http error)
            throw new IOException("HTTP error. Response code: "+responseCode);
        }
        
        // store the response from the web service in a StringBuilder
        BufferedReader input;
        input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = input.readLine()) != null){
            response.append(inputLine);
        }
        
        input.close();
        
        // returns the response as a string
        return response.toString();
    }
    
    /**
     * parseLocal populates the fields of localData with new results
     * @param response The JSONObject with response from web service
     */
    private void parseLocal(JSONObject response, LocalWeatherData localData){
        JSONObject responseMain = (JSONObject) response.get("main");
        JSONObject responseWind = (JSONObject) response.get("wind");
        JSONObject responseSys = (JSONObject) response.get("sys");
        JSONArray responseWeatherArr = (JSONArray) response.get("weather");
        JSONObject responseWeather = (JSONObject) responseWeatherArr.get(0);
        
        // update values
        localData.setTemperature(responseMain.get("temp").toString());
        localData.setWindSpeed(responseWind.get("speed").toString());
        localData.setWindDirection(responseWind.get("deg").toString());
        localData.setAirPressure(responseMain.get("pressure").toString());
        localData.setMinTemperature(responseMain.get("temp_min").toString());
        localData.setMaxTemperature(responseMain.get("temp_max").toString());
        localData.setHumidity(responseMain.get("humidity").toString());
        localData.setSkyCondition(responseWeather.get("description").toString());
        localData.setSkyIcon(responseWeather.get("icon").toString());
        
        String timeUpdated = response.get("dt").toString();
        localData.setTimeUpdated(convertUTCtoReadable(timeUpdated));
        if (responseSys.get("sunrise") != null){
                String sunrise = responseSys.get("sunrise").toString();
                localData.setTimeSunrise(convertUTCtoReadable(sunrise));
        }

        if (responseSys.get("sunset") != null){
                String sunset = responseSys.get("sunset").toString();   
                localData.setTimeSunset(convertUTCtoReadable(sunset));
        }
    }
    
    /**
     * populates fields of shortTermData with new results
     * @param response The JSONObject with response from web service
     */
    private void parseShort(JSONObject response){
        JSONArray list = (JSONArray) response.get("list");
        JSONObject weatherTimeslot;        
        
        for (int i=0; i< list.size(); i++){
            weatherTimeslot = (JSONObject) list.get(i);
            LocalWeatherData weatherData = new LocalWeatherData();
            
            parseLocal(weatherTimeslot, weatherData);
            
            shortTermData.addShortTermData(weatherData);
        }
    }
    
    /**
     * populates fields of longTermData with new results
     * @param response The JSONObject with response from web service
     */
    private void parseLong(JSONObject response){
        JSONArray list = (JSONArray) response.get("list");
        JSONObject weatherDay;
        String[] temperatureMin = longTermData.getTemperatureMin();
        String[] temperatureMax = longTermData.getTemperatureMax();
        String[] skyCondition = longTermData.getSkyCondition();
        
        for (int i=0; i < 7; i++){
            weatherDay = (JSONObject) list.get(i);
            
            //set temperature in array
            temperatureMin[i] = ((JSONObject)weatherDay.get("temp")).get("min").toString();
            temperatureMax[i] = ((JSONObject)weatherDay.get("temp")).get("max").toString();
            //set sky condition in  array
            skyCondition[i] = ((JSONObject)((JSONArray)weatherDay.get("weather")).get(0)).get("id").toString();
        }
    }
    
    /**
     * populates fields of localWeatherData with new results
     * @param response The JSONObject with response from web service
     */
    private void parseMars(JSONObject response){
        JSONObject report = (JSONObject) response.get("report");
        
        localData.setTemperature("Not available");
        if (report.get("wind_speed") != null){
			localData.setWindSpeed(report.get("wind_speed").toString());
		} else {
			localData.setWindSpeed(null);
		}
		if (report.get("wind_direction") != null && !report.get("wind_direction").toString().equals("--")){
			localData.setWindDirection(report.get("wind_direction").toString());
		} else {
			localData.setWindDirection(null);
		}
        localData.setAirPressure(report.get("pressure").toString());
        switch (unit){
            case CELCIUS:
                localData.setMinTemperature(report.get("min_temp").toString());
                localData.setMaxTemperature(report.get("max_temp").toString());
                localData.setUnit('C');
                break;
            case FAHRENHEIT:
                localData.setMinTemperature(report.get("min_temp_fahrenheit").toString());
                localData.setMaxTemperature(report.get("max_temp_fahrenheit").toString());
                localData.setUnit('F');
                break;
        }
		if (report.get("abs_humidity") != null){
			localData.setHumidity(report.get("abs_humidity").toString());
		} else {
			localData.setHumidity(null);
		}
        localData.setSkyCondition(report.get("atmo_opacity").toString());
        localData.setSkyIcon("");
        String sunrise = report.get("sunrise").toString();
        String sunset = report.get("sunset").toString();
	String lastUpdate = report.get("terrestrial_date").toString();
        localData.setTimeSunrise(sunrise.substring(11,16)+" ("+sunrise.substring(5, 10)+")");
        localData.setTimeSunset(sunset.substring(11,16)+" ("+sunset.substring(5, 10)+")");
        localData.setTimeUpdated(lastUpdate);
    }

    /**
     * converts UTC time value returned from api to human readable hh:mm am/pm
     * @param utc 
     * @return time in hh:mm am/pm
     */
    private String convertUTCtoReadable(String utc){
        utc = utc+"000";
        Date date = new Date(Long.parseLong(utc,10));
        SimpleDateFormat format = new SimpleDateFormat("h:mm a");
        return format.format(date);
    }   
    
    /**
     * sets unit format to celsius
     */
    public void setCelcius(){
        unit = Unit.CELCIUS;
    }
    
    /**
     * sets unit format to fahrenheit
     */
    public void setFahrenheit(){
        unit = Unit.FAHRENHEIT;
    }

}
