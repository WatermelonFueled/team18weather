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
 * @author David Park
 */
public class DataRequester {
    private LocalWeatherData localData;
    private ShortTermData shortTermData;
    private LongTermData longTermData;
    private JSONParser parser;
    
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

    /**
     * requestLocal method requests for the local forecast info and updates
     * the localData object with new values
     * @param id city id as string
     */
    public void requestLocal(String id){
        String requestURL = "http://api.openweathermap.org/data/2.5/weather?units=metric&id=" + id;
        
        JSONObject responseJSON = request(requestURL);
        if (responseJSON != null){
            parseLocal(responseJSON);
        }
    }
    
    public void requestShort(String id){
        String requestURL = "http://api.openweathermap.org/data/2.5/forecast?units=metric&id=" + id;
        
        JSONObject responseJSON = request(requestURL);
        if (responseJSON != null){
            parseShort(responseJSON);
        }
    }
    
    public void requestLong(String id){
        String requestURL = "http://api.openweathermap.org/data/2.5/forecast/daily?cnt=7&units=metric&id=" + id;
        
        JSONObject responseJSON = request(requestURL);
        if (responseJSON != null){
            parseLong(responseJSON);
        }
    }
    
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
        localData.setHumidity(responseMain.get("humidity").toString());
        
        /////
        //Diana Testing icon and descriptions
        localData.setSkyCondition(responseWeather.get("description").toString());
        localData.setSkyIcon(responseWeather.get("icon").toString());
        /////
        
        String sunrise = responseSys.get("sunrise").toString();
        String sunset = responseSys.get("sunset").toString();
        localData.setTimeSunrise(convertUTCtoReadable(sunrise));
        localData.setTimeSunset(convertUTCtoReadable(sunset));
    }
    
    /**
     * 
     * @param weatherData
     * @param response 
     */
    private void parseShort(JSONObject response){
        JSONArray list = (JSONArray) response.get("list");
        JSONObject weatherTimeslot;
        String[] temperature = shortTermData.getTemperature();
        String[] skyCondition = shortTermData.getSkyCondition();
        
        for (int i=0; i<9; i++){
            weatherTimeslot = (JSONObject) list.get(i);
            
            //set temperature in array
            temperature[i] = ((JSONObject)weatherTimeslot.get("main")).get("temp").toString();
            //set sky condition in  array
            skyCondition[i] = ((JSONObject)((JSONArray)weatherTimeslot.get("weather")).get(0)).get("id").toString();
        }
    }
    
    /**
     * 
     * @param weatherData
     * @param response 
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
     * 
     * @param utc
     * @return 
     */
    private String convertUTCtoReadable(String utc){
        utc = utc+"000";
        Date date = new Date(Long.parseLong(utc,10));
        SimpleDateFormat format = new SimpleDateFormat("h:mm a");
        return format.format(date);
    }    
}
