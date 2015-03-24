package ca.uwo.csd.cs2212.team18;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


/**
 * Creates the tabbed panel to switch between the various views
 * @author DaParkVid, updated by Samirah
 * 
 */
public class TabbedViews extends JPanel{
    
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
        
        //local forecast tab
        tabbedPane.addTab("Local Forecast", localView);
        tabbedPane.setMnemonicAt(0,KeyEvent.VK_1);
        
        //selection tab
        tabbedPane.addTab("Preferences", selectionView);
        tabbedPane.setMnemonicAt(1,KeyEvent.VK_2);
        
        //selection tab
        tabbedPane.addTab("Short Term Forecast", shortTermView);
        tabbedPane.setMnemonicAt(1,KeyEvent.VK_3);
        
        add(tabbedPane);
    }
    
