package ca.uwo.csd.cs2212.team18;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


/**
 * <h1> Tabbed Views </h1>
 * Class creates the tabbed panel to switch between the various views
 */
public class TabbedViews extends JPanel{

	private static final long serialVersionUID = -5885613404969139842L;

	/**
	 * Constructor for TabbedViews
	 * @param selectionView
	 * @param localView 
	 * @param shortTermView 
	 */
	public TabbedViews(SelectionPage selectionView, LocalWeatherView localView,
			ShortTermView shortTermView){
		super(new GridLayout(1,1));

		JTabbedPane tabbedPane = new JTabbedPane();

		//selection tab
		tabbedPane.addTab("Preferences", selectionView);
		tabbedPane.setMnemonicAt(0,KeyEvent.VK_1);

		//local forecast tab
		tabbedPane.addTab("Local Forecast", localView);
		tabbedPane.setMnemonicAt(1,KeyEvent.VK_2);

		//short term forecast tab
		tabbedPane.addTab("Short Term Forecast", shortTermView);
		tabbedPane.setMnemonicAt(2,KeyEvent.VK_3);

		add(tabbedPane);
	}

}