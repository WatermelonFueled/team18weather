package ca.uwo.csd.cs2212.team18;
import java.io.PrintWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppMain {

    static Logger logger = LogManager.getLogger(AppMain.class.getName());	
    public static void main (String [] args) {
            logger.info("This is Team 18's Weather App");

            PrintWriter writer = new PrintWriter("test.txt", "UTF-8");
            writer.println("The first line");
            writer.println("The second line");
            writer.close();
            // initialize objects
            
            LocalWeatherData localData = new LocalWeatherData();
            DataRequester dataRequester = new DataRequester(localData);
            LocalWeatherView localView = new LocalWeatherView(localData, dataRequester);
            SelectionPage selectionPage = new SelectionPage(localView);
	    selectionPage.setVisible(true);
            //search

            //request data

            //send to local view and display



    }


}
