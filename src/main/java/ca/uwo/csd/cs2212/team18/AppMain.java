package ca.uwo.csd.cs2212.team18;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main class for team 18 weather app; Initializes all necessary components
 * @author DaParkVid
 */
public class AppMain{

    static Logger logger = LogManager.getLogger(AppMain.class.getName());
    
    public static void main (String [] args) throws IOException {
        logger.info("This is Team 18's Weather App - starting...");

        // data
        logger.info("Initializing weather data components");
        LocalWeatherData localData = new LocalWeatherData();
        ShortTermData shortTermData = new ShortTermData();
        LongTermData longTermData = new LongTermData();
        DataRequester dataRequester = new DataRequester(localData,shortTermData,longTermData);
        logger.info("End of weather data components");
        
        // views
        logger.info("Initializing views: local weather and selection/preferences");
        LocalWeatherView localView = new LocalWeatherView(localData);
        ShortTermView shortView = new ShortTermView(shortTermData);
        SelectionPage selectionPage = new SelectionPage(localView, shortView, dataRequester);
        logger.info("End of views");

        // place views into tabs
        logger.info("Putting views into tabs");
        TabbedViews tabs = new TabbedViews(selectionPage, localView, shortView);

        //main window/frame
        logger.info("Initializing main JFrame with tabbed view");
        JFrame mainFrame = new JFrame("Team18 Weather");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setBackground(Color.WHITE);
        mainFrame.setSize(600, 450);
        mainFrame.add(tabs, BorderLayout.CENTER);
        mainFrame.setVisible(true);
        logger.info("Main window created");
    }


}
