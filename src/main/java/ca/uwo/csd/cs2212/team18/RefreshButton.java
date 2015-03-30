package ca.uwo.csd.cs2212.team18;

import java.io.IOException;
import javax.swing.JButton;

/**
 * <h1> Refresh Button </h1>
 * @author DaParkVid
 */

public class RefreshButton extends JButton {

	private static final long serialVersionUID = -1305765000429617581L;
	DataRequester dataRequester;
    
    public RefreshButton (DataRequester dataRequester){
        super("Refresh");
        this.dataRequester = dataRequester;
    }
    
    public void refresh() throws IOException {
        dataRequester.update();
    }
    
}