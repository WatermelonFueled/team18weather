import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

public class SelectionPage{

	public static void main(String[] args) throws Exception{
		SwingUtilities.invokeAndWait(new Runnable(){

			@Override
			public void run() {

				//Get data for comboBox
				final ArrayList<String> locList = new ArrayList<String>();
				try{
					Scanner s = new Scanner(new File("orderedList.txt"));
					while (s.hasNext()){
						locList.add(s.nextLine());
					}
					s.close();
				}
				catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				//Radio Buttons
				JRadioButton radCelsius;
				final JRadioButton radFahrenheit;

				radCelsius = new JRadioButton("Celsius");
				radFahrenheit = new JRadioButton("Fahrenheit");

				ButtonGroup grpTemp = new ButtonGroup();
				grpTemp.add(radCelsius);
				grpTemp.add(radFahrenheit);

				//Search buttons and selected value text
				JLabel lblHello = new JLabel("Choose your location...");
				final JLabel lblText = new JLabel();
				JButton GWButton = new JButton ("Get Local Weather");

				//Auto Complete Combo Box
				StringSearchable searchable = new StringSearchable(locList);
				final AutoCompleteJComboBox combo = new AutoCompleteJComboBox(searchable);
				combo.setEditable(true);

				//KeyListener for when hitting enter in window
				KeyListener eListener = new KeyListener() {
					public void keyReleased(KeyEvent event) {
						if (event.getKeyChar() == KeyEvent.VK_ENTER) {		
							combo.setPopupVisible(true);
							if(!(locList.contains(combo.getSelectedItem()))) {
								String err = "Error: Incorrect Location, Please Try Again";
								lblText.setText(err);
							}
							else {
								String msg = "Selected: " + combo.getSelectedItem();
								if (radFahrenheit.isSelected())
									msg += " in Fahrenheit";

								else msg += " in Celsius";
								lblText.setText(msg);
							} 
						} 
					}
					@Override
					public void keyTyped(KeyEvent arg0) {}
					@Override
					public void keyPressed(KeyEvent e) {}
				};

				//If hit enter select entered values and display them
				radCelsius.addKeyListener(eListener);
				radFahrenheit.addKeyListener(eListener);
				combo.getEditor().getEditorComponent().addKeyListener(eListener);

				//Add action listener to button "Get Local Weather"
				GWButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e)
					{
						if(!(locList.contains(combo.getSelectedItem()))) {
							String err = "Error: Incorrect Location, Please Try Again";
							lblText.setText(err);
							//combo.setPopupVisible(true);
						}
						else {
							// Print result to preferences file for secondary startup
							try {
								PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preferences.txt", true)));
								out.println(lblText.getText().toString());
								out.close();
							} catch (IOException f) {}

							lblText.setText("-->");		//For testing purposes only
							/*
							 * run DataRequestor/Local Weather View
							 */
						}
					}
				});      

				//Set Preferred width of combo box
				combo.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXX");

				//Create Frames
				JFrame frame = new JFrame();
				frame.setTitle("CS 2212 - Weather App - Welcome Page"); 
				frame.setSize(600, 150);

				//Add panes to window
				JPanel paneNorth = new JPanel();
				JPanel paneCenter = new JPanel();
				JPanel paneSouth = new JPanel();
				frame.add(paneNorth, BorderLayout.NORTH);
				frame.add(paneCenter, BorderLayout.CENTER);
				frame.add(paneSouth, BorderLayout.SOUTH);

				//Top pane
				paneNorth.setLayout(new FlowLayout(FlowLayout.LEFT));
				paneNorth.add(lblHello);

				//Center pane
				paneCenter.add(combo);
				paneCenter.add(radFahrenheit);
				paneCenter.add(radCelsius);

				//Bottom pane
				paneSouth.add(lblText);
				paneSouth.add(GWButton);

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);

			}
		});
	}
}