package ca.uwo.csd.cs2212.team18;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppMain {
	static Logger logger = LogManager.getLogger(AppMain.class.getName());	
	public static void main (String [] args) {
		logger.trace("Entering main");
		logger.warn("Hello Maven/log4j World");
		logger.info("This is Team 18's Weather App");
		logger.trace("Exiting main");
        
        SwingUtilities.invokeLater(new Runnable() {
        @Override
            public void run() {
                SelectionPage sp = new SelectionPage();
                sp.setVisible(true);
            }
        });
    }
}
