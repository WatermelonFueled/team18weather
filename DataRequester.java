
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class handles all communication with the online weather services
 * (OpenWeatherMap.org or MarsWeather.com) to request and receive data
 * as needed
 * @author David Park
 */

public class DataRequester {
    
    private String OWMAddress = "http://api.openweathermap.org/data/2.5/";
    private String OWMFind = "find?units=metric&q=";
    private String OWMLocal = "weather?units=metric&q=";
    private String OWMShort = "forecast?units=metric&q=";
    private String OWMLong = "forecast/daily?cnt=7&units=metric&q=";
    //private String MarsAddress = 
    

    /**
     * Sends the request using HttpURLConnection and returns the response
     * @param request the request to be sent (the url)
     * @return the response from the web service as a String
     * @throws MalformedURLException
     * @throws IOException 
     */
    public String sendRequest(String request) throws MalformedURLException, IOException{
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
}
