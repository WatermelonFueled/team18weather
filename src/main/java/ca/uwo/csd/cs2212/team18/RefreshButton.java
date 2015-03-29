package ca.uwo.csd.cs2212.team18;

import javax.swing.JButton;

public class RefreshButton extends JButton {
    DataRequester dataRequester;
    
    public RefreshButton (DataRequester dataRequester){
        super("Refresh");
        this.dataRequester = dataRequester;
    }
    
    public void refresh(){
        dataRequester.update();
    }
    
}