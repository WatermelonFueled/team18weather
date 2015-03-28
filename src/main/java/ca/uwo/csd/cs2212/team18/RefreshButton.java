package ca.uwo.csd.cs2212.team18;

import javax.swing.JButton;

public class RefreshButton extends JButton {
    DataRequester dataRequester;
    
    public RefreshButton (DataRequester dataRequester){
        this.dataRequester = dataRequester;
    }
    
}