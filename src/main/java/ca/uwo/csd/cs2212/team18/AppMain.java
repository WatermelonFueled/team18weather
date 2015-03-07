package ca.uwo.csd.cs2212.team18;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppMain {

    
    SelectionPage selectionPage = new SelectionPage();
    DataRequester dataRequester = new dataRequester();
    LocalWeatherData localData = new LocalWeatherData();

    
    static Logger logger = LogManager.getLogger(AppMain.class.getName());	
    public static void main (String [] args) {
            logger.info("This is Team 18's Weather App");

            // initialize objects
            SelectionPage selectionPage = new SelectionPage();
            DataRequester dataRequester = new dataRequester();
            LocalWeatherData localData = new LocalWeatherData();
            //search

            //request data

            //send to local view and display



    }


}
