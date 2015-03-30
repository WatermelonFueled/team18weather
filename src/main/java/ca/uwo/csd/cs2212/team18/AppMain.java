package ca.uwo.csd.cs2212.team18;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main class for team 18 weather app; Initializes all necessary components
 * @author DaParkVid
 */
public class AppMain{

	static Logger logger = LogManager.getLogger(AppMain.class.getName());

	/*
	 * Checks if there is a previous preference on file and auto-populate info
	 * @throws IOException
	 * @throws FileNotFoundException

	private static void checkPreferences() throws IOException, FileNotFoundException{
		//Check preferences file
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("preferences.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		if(br.readLine() != null){
			System.out.println("Preferences not null");	//check to see if can find file
			String location = br.readLine();
			String cityName;
			if (location.equalsIgnoreCase("mars")){
				dataRequester.requestMars();
				cityName = "Mars";
			}else{
				cityName = location.substring(0, location.indexOf('['));
				String cityId = location.substring(location.indexOf('[')+1, location.indexOf(']'));

				//updates local, short term, long term data for selected city
				dataRequester.requestLocal(cityId);
				dataRequester.requestShort(cityId);
				dataRequester.requestLong(cityId);
			}

			//Set Labels
			localWeatherView.setCityName(cityName);
			try {
				localWeatherView.setLabels();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			shortTermView.display();
		}
	};*/

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
		LongTermData longTermData = new LongTermData();
		DataRequester dataRequester = new DataRequester(localData,shortTermData,longTermData);
		logger.info("End of weather data components");

		RefreshButton refreshButton = new RefreshButton(dataRequester);

		// views
		logger.info("Initializing views: local weather and selection/preferences");
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

		//checkPreferences();
	}


}
