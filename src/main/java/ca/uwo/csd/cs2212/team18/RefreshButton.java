package ca.uwo.csd.cs2212.team18;

import java.io.IOException;
import javax.swing.JButton;

public class RefreshButton extends JButton {
    DataRequester dataRequester;
    
    public RefreshButton (DataRequester dataRequester){
        super("Refresh");
        this.dataRequester = dataRequester;
    }
    
    public void refresh() throws IOException {
        dataRequester.update();
    }
    
}