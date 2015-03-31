package ca.uwo.csd.cs2212.team18;

import java.awt.*;
import java.io.*;
import javax.swing.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <h1> App Main </h1>
 * Main class for team 18 weather app; Initializes all necessary components
 */
public class AppMain{

	static Logger logger = LogManager.getLogger(AppMain.class.getName());

	/**
	 * Main method for AppMain class, initializes frame and subclasses
	 * @param args
	 * @throws IOException
	 */
	public static void main (String [] args) throws IOException {
		logger.info("This is Team 18's Weather App - starting...");

		// data
		logger.info("Initializing weather data components");
		LocalWeatherData localData = new LocalWeatherData();
		ShortTermData shortTermData = new ShortTermData();
		DataRequester dataRequester = new DataRequester(localData,shortTermData);
		logger.info("End of weather data components");

		RefreshButton refreshButton = new RefreshButton(dataRequester);

		// views
		logger.info("Initializing weather views/tabs");
		LocalWeatherView localView = new LocalWeatherView(localData, refreshButton);
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
