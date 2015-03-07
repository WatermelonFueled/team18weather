package ca.uwo.csd.cs2212.team18

import javax.swing.SwingUtilities;

public class RunLocalWeatherView {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				LocalWeatherView lwv = new LocalWeatherView();
				lwv.setVisible(true);
			}
		}); 
	}
}
