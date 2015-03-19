package ca.uwo.csd.cs2212.team18;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;


/**
 * JComboBox with an autocomplete drop down menu. This class is hard-coded for String objects. 
 *
 * @author DianaGodoy
 *
 * Code adapted from {@link http://www.algosome.com/articles/java-jcombobox-autocomplete.html Source Code}
 */

public class AutoCompleteJComboBox extends JComboBox{

	static final long serialVersionUID = 4321421L;

	private final Searchable<String,String> searchable;

	/**
	 * Constructs a new object based upon the parameter searchable
	 * @param s
	 */
	public AutoCompleteJComboBox(Searchable<String,String> s){
		super();
		this.searchable = s;
		setEditable(true);
		Component c = getEditor().getEditorComponent();
		if ( c instanceof JTextComponent ){
			final JTextComponent tc = (JTextComponent)c;
			tc.getDocument().addDocumentListener(new DocumentListener(){

				public void changedUpdate(DocumentEvent arg0) {}

				public void insertUpdate(DocumentEvent arg0) {
					update();
				}

				public void removeUpdate(DocumentEvent arg0) {
					update();
				}

				public void update(){
					//perform separately, as listener conflicts between the editing component
					//and JComboBox will result in an IllegalStateException due to editing 
					//the component when it is locked. 
					SwingUtilities.invokeLater(new Runnable(){

						public void run() {
							List<String> founds = new ArrayList<String>(searchable.search(tc.getText()));
							Set<String> foundSet = new HashSet<String>();
							for ( String s : founds ){
								foundSet.add(s.toLowerCase());
							}
							//Collections.sort(founds);//sort alphabetically

							setEditable(false);
							removeAllItems();
							//if founds contains the search text, then only add once.
							if ( !foundSet.contains( tc.getText().toLowerCase()) ){
								addItem( tc.getText() );
							}

							for (String s : founds) {
								addItem(s);
							}
							setEditable(true);
							//setPopupVisible(true);
							tc.requestFocus();
						}
					});
				}
			});
			//When the text component changes, focus is gained 
			//and the menu disappears. To account for this, whenever the focus
			//is gained by the JTextComponent and it has searchable values, we show the popup.
			tc.addFocusListener(new FocusListener(){
				public void focusGained(FocusEvent arg0) {
					if ( tc.getText().length() > 0 ){
						//setPopupVisible(true);
						tc.setCaretPosition(tc.getText().length());
					}
				}

				public void focusLost(FocusEvent arg0) {						
				}
			});
		}else{
			throw new IllegalStateException("Editing component is not a JTextComponent!");
		}
	}
}