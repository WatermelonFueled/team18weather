import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.text.*;

public class MainWindow extends PlainDocument {

	private static final long serialVersionUID = -5180365206246215315L;
	JComboBox comboBox;
	ComboBoxModel model;
	JTextComponent editor;

	// flag to indicate if setSelectedItem has been called
	// subsequent calls to remove/insertString should be ignored
	boolean selecting=false;

	public MainWindow(final JComboBox comboBox) {
		this.comboBox = comboBox;
		model = comboBox.getModel();
		editor = (JTextComponent) comboBox.getEditor().getEditorComponent();
	}

	public void remove(int offs, int len) throws BadLocationException {
		// return immediately when selecting an item
		if (selecting) return;
		super.remove(offs, len);
	}

	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		// return immediately when selecting an item
		if (selecting) return;
		// insert the string into the document
		super.insertString(offs, str, a);
		// lookup and select a matching item
		Object item = lookupItem(getText(0, getLength()));
		setSelectedItem(item);
		setText(item.toString());
		// select the completed part
		highlightCompletedText(offs+str.length());
	}

	private void setText(String text) throws BadLocationException {
		// remove all text and insert the completed string
		super.remove(0, getLength());
		super.insertString(0, text, null);
	}

	private void highlightCompletedText(int start) {
		editor.setSelectionStart(start);
		editor.setSelectionEnd(getLength());
	}

	private void setSelectedItem(Object item) {
		selecting = true;
		model.setSelectedItem(item);
		selecting = false;
	}

	private Object lookupItem(String pattern) {
		Object selectedItem = model.getSelectedItem();
		// only search for a different item if the currently selected does not match
		if (selectedItem != null && startsWithIgnoreCase(selectedItem.toString(), pattern)) {
			//System.out.println("Selected item '" + selectedItem + "' matches '" + pattern + "'");
			return selectedItem;
		} else {
			// iterate over all items
			for (int i=0, n=model.getSize(); i < n; i++) {
				Object currentItem = model.getElementAt(i);
				// current item starts with the pattern?
				if (startsWithIgnoreCase(currentItem.toString(), pattern)) {
					//System.out.println("New selection: '" + currentItem + "'");
					return currentItem;
				}
			}
		}
		// no item starts with the pattern => return null
		return null;
	}

	// checks if str1 starts with str2 - ignores case
	private boolean startsWithIgnoreCase(String str1, String str2) {
		return str1.toUpperCase().startsWith(str2.toUpperCase());
	}

	public static void createAndShowGUI() {
		ArrayList<String> locList = new ArrayList<String>();

		URL url;
		try {
			url = new URL("http://openweathermap.org/help/city_list.txt");
			Scanner s = new Scanner (url.openStream());
			s.nextLine();

			while (s.hasNextLine()) {
				String str = s.nextLine();
				String[] tokens= str.split("\t");
				locList.add(tokens[1]+ ", "+ tokens[4] + ", "+ tokens[0]);
			} 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// the combo box 
		final JComboBox comboBox = new JComboBox(locList.toArray());
		// has to be editable
		comboBox.setEditable(true);
		// get the combo boxes editor component
		JTextComponent editor = (JTextComponent) comboBox.getEditor().getEditorComponent();
		// change the editor's document
		editor.setDocument(new MainWindow(comboBox));

		// create and show a window containing the combo box
		JFrame frame = new JFrame("Main Window - Weather App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();

		contentPane.add(comboBox, BorderLayout.NORTH);

		final JTextArea textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		

		comboBox.getEditor().addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent arg0) {
				textArea.append("Selected: " + comboBox.getSelectedItem());
				textArea.append(System.getProperty("line.separator"));
				/*
				 * Code to view weather of selected location goes here
				 */
			}               
		});

		frame.setSize(300, 200);
		frame.setVisible(true);
	}


	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});

	}
}