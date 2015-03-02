
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class handles all communication with the online weather services
 * (OpenWeatherMap.org or MarsWeather.com) to request and receive data
 * as needed
 * @author David Park
 */

public class DataRequester {
    
    private String OWMAddress = "http://api.openweathermap.org/data/2.5/";
    private String OWMLocal = "weather?units=metric&id=";
    private String OWMShort = "forecast?units=metric&id=";
    private String OWMLong = "forecast/daily?cnt=7&units=metric&id=";
    //private String MarsAddress = 
    
    private JSONParser parser;
    
    
    public DataRequester(){
        parser = new JSONParser();
    }

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
        URL requestURL = new URL(request);
        HttpURLConnection connection = (HttpURLConnection) requestURL.openConnection();
        
        int responseCode = connection.getResponseCode();
        // check if there was a client error during the request
        if (responseCode > 400){
            // (action to take with error)
            
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
    
    private void parseResponse(WeatherData weatherData, String response){
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
    
    private void parseLocal(LocalWeatherData weatherData, JSONObject response){
        JSONObject responseMain = (JSONObject) response.get("main");
        JSONObject responseWind = (JSONObject) response.get("wind");
        JSONObject responseSys = (JSONObject) response.get("sys");
        JSONObject responseWeather = (JSONObject) response.get("weather");
        
        weatherData.setTemperature((String) responseMain.get("temp"));
        weatherData.setWindSpeed((String) responseWind.get("speed"));
        weatherData.setWihdDirection((String) responseWind.get("deg"));
        weatherData.setSkyCondition((String) responseWeather.get("id"));
        // should we get more than the condition id?
        weatherData.setAirPressure((String) responseMain.get("pressure"));
        weatherData.setMinTemperature((String) responseMain.get("temp_min"));
        weatherData.setMaxTemperature((String) responseMain.get("temp_max"));
        weatherData.setTimeSunrise((String) responseSys.get("sunrise"));
        weatherData.setTimeSunset((String) responseSys.get("sunset"));
        weatherData.setHumidity((String) responseMain.get("humidity"));
    }
}
