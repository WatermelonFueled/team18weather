
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
import org.json.simple.parser.ParseException;

/**
 * This class handles all communication with the online weather services
 * (OpenWeatherMap.org or MarsWeather.com) to request and receive data
 * as needed
 * @author David Park
 */
public class DataRequester {
    
    // url segments for request
    // NOTE: may be better having less concat vs longer strings
    private String OWMAddress = "http://api.openweathermap.org/data/2.5/";
    private String OWMLocal = "weather?units=metric&id=";
    private String OWMShort = "forecast?units=metric&id=";
    private String OWMLong = "forecast/daily?cnt=7&units=metric&id=";
    //private String MarsAddress = 
    
    /**
     * Constructor for DataRequestor
     */
    public DataRequester(){
        //
    }

    /**
     * request method performs request for specified city (id) and populate
     * appropriate data
     * @param weatherData to insert values in; can be local, long or short term
     * @param id of location (city)
     */
    public void request(WeatherData weatherData, String id){
        String requestURL = this.concatURL(weatherData, id);
        
        String response;
        try {
            response = this.sendRequest(requestURL);
            this.parseResponse(weatherData, response);
            
        } catch (IOException | ParseException ex) {
            Logger.getLogger(DataRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
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
    
    
    private String concatURL(WeatherData weatherData, String id){
        String resultURL = OWMAddress;
        
        Class dataType = weatherData.getClass();
        switch (dataType){
            case LocalWeatherData.class:
                // create URL for local forecast
                resultURL = resultURL.concat(OWMLocal).concat(id);
                break;
            case ShortTermData.class:
                // create URL for short term forecast
                resultURL = resultURL.concat(OWMShort).concat(id);
                break;
            case LongTermData.class:
                // create URL for long term forecast
                resultURL = resultURL.concat(OWMLong).concat(id);
                break;
            default:
                // error, currently causes method to return null
                resultURL = null;
                break;
        }// end switch
        
        return resultURL;
    }
    
    /**
     * parseResponse parses the response from the weather services by calling
     * the appropriate parsing method matching the type of weatherData
     * @param weatherData data object to be updated
     * @param response from the web service
     * @throws ParseException 
     */
    private void parseResponse(WeatherData weatherData, String response) throws ParseException{
        JSONParser parser = new JSONParser();
        // create JSONObject of response
        JSONObject responseJSON = (JSONObject) parser.parse(response);
        
        Class dataType = weatherData.getClass();
        switch (dataType){
            case LocalWeatherData.class:
                // parse for local forecast
                this.parseLocal(weatherData, responseJSON);
                break;
            case ShortTermData.class:
                // parse for short term forecast
                
                break;
            case LongTermData.class:
                // parse for long term forecast
                
                break;
            default:
                // error
                break;
        }// end switch    
    }
    
    /**
     * parseLocal populates the fields of weatherData; appropriate for local
     * @param weatherData data object to put in new data
     * @param response The JSONObject with response from OpenWeatherMap
     */
    private void parseLocal(LocalWeatherData weatherData, JSONObject response){
        JSONObject responseMain = (JSONObject) response.get("main");
        JSONObject responseWind = (JSONObject) response.get("wind");
        JSONObject responseSys = (JSONObject) response.get("sys");
        JSONArray responseWeatherArr = (JSONArray) response.get("weather");
        JSONObject responseWeather = (JSONObject) responseWeatherArr.get(0);
        
        weatherData.setTemperature(responseMain.get("temp").toString());
        weatherData.setWindSpeed(responseWind.get("speed").toString());
        weatherData.setWindDirection(responseWind.get("deg").toString());
        weatherData.setAirPressure(responseMain.get("pressure").toString());
        weatherData.setMinTemperature(responseMain.get("temp_min").toString());
        weatherData.setMaxTemperature(responseMain.get("temp_max").toString());
        weatherData.setTimeSunrise(responseSys.get("sunrise").toString());
        weatherData.setTimeSunset(responseSys.get("sunset").toString());
        weatherData.setHumidity(responseMain.get("humidity").toString());
        
        weatherData.setSkyCondition(responseWeather.get("id"));
        // possibly add description as well
        //weatherData.setDescription(responseWeather.get("description"));
    }
    
    /**
     * 
     * @param weatherData
     * @param response 
     */
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
    
    /**
     * 
     * @param weatherData
     * @param response 
     */
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
}
