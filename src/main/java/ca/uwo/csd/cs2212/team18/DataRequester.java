package ca.uwo.csd.cs2212.team18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * This class handles all communication with the online weather services
 * (OpenWeatherMap.org or MarsWeather.com) to request and receive data
 * as needed
 * @author David Park
 */
public class DataRequester {
    private LocalWeatherData localData;
    //private ShortTermData shortData;
    //private LongTermData longData;
    private JSONParser parser;
    
    /**
     * Constructor for DataRequester
     * @param localData LocalWeatherData object
     */
    DataRequester(LocalWeatherData localData){
        this.localData = localData;
        parser = new JSONParser();
    }

    /**
     * requestLocal method requests for the local forecast info and updates
     * the localData object with new values
     * @param id city id as string
     */
    public void requestLocal(String id){
        // build the request string
        String requestURL = "http://api.openweathermap.org/data/2.5/weather?units=metric&id=" + id;
        
        try {
            // send request to web service
            String response = this.sendRequest(requestURL);
            // parse response
            JSONObject responseJSON = (JSONObject) parser.parse(response);
            parseLocal(responseJSON);
        } catch (IOException ex) {
            Logger.getLogger(DataRequester.class.getName()).log(Level.SEVERE, null, ex);
        }            
    }
    
    //request short
    //"http://api.openweathermap.org/data/2.5/forecast?units=metric&id="
    //request long
    //"http://api.openweathermap.org/data/2.5/forecast/daily?cnt=7&units=metric&id="
    
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
     * @param response The JSONObject with response from OpenWeatherMap
     */
    private void parseLocal(JSONObject response){
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
        localData.setTimeSunrise(responseSys.get("sunrise").toString());
        localData.setTimeSunset(responseSys.get("sunset").toString());
        localData.setHumidity(responseMain.get("humidity").toString());
        
        localData.setSkyCondition(responseWeather.get("id").toString());
        // possibly add description as well
        //localData.setDescription(responseWeather.get("description"));
    }
    
    /**
     * 
     * @param weatherData
     * @param response 
     */
    /*
    private void parseShort(ShortTermData weatherData, JSONObject response){
        JSONArray list = (JSONArray) response.get("list");
        JSONObject weatherTimeslot;
        
        for (int i=0; i<9; i++){
            weatherTimeslot = (JSONObject) list.get(i);
            
            //set temperature in array
            //weatherTimeslot.get("main").get("temp").toString()
            //set sky condition in  array
            //weatherTimeslot.get("weather").get(0).get("id")
        }
    }
    */
    
    /**
     * 
     * @param weatherData
     * @param response 
     */
    /*
    private void parseLong(LongTermData weatherData, JSONObject response){
        JSONArray list = (JSONArray) response.get("list");
        JSONObject weatherDay;
        
        for (int i=0; i < 7; i++){
            weatherDay = (JSONObject) list.get(i);
            
            //set temperature in array
            //weatherTimeslot.get("main").get("temp").toString()
            //set sky condition in  array
            //weatherTimeslot.get("weather").get(0).get("id")
        }
    }
    */
}
