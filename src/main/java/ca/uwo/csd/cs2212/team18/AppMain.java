package ca.uwo.csd.cs2212.team18;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppMain{

    static Logger logger = LogManager.getLogger(AppMain.class.getName());
    
    public static void main (String [] args) {
        logger.info("This is Team 18's Weather App - starting...");

        // initialize objects
        
        
        LocalWeatherData localData = new LocalWeatherData();
        DataRequester dataRequester = new DataRequester(localData);

        LocalWeatherView localView = new LocalWeatherView(localData);

        SelectionPage selectionPage = new SelectionPage(localView, dataRequester);

        TabbedViews tabs = new TabbedViews(selectionPage, localView);

        JFrame mainFrame = new JFrame("Team18 Weather");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setBackground(Color.WHITE);
        mainFrame.setSize(1280, 720);
        mainFrame.add(tabs, BorderLayout.CENTER);
        mainFrame.setVisible(true);

        //search

        //request data

        //send to local view and display



    }


}
