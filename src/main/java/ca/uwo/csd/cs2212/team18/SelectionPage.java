package ca.uwo.csd.cs2212.team18;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

/**
 *  <h1> Selection Page </h1> 
 *  This class creates a window for the selections page in which a 
 *  user can select their preferences. This includes choosing 
 *  current location and temperature format. This class will be 
 *  used on initial startup when there are no saved preferences 
 *  and when a user wants to change their current preferences.
 *  
 *  @author DianaGodoy
 */

public class SelectionPage extends JFrame{

	private static final long serialVersionUID = -5681712855274371085L;

	/**
	 * Creates a new SelectionPage and initializes the user 
	 * interface. Window is not visible by default.
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public SelectionPage() throws FileNotFoundException, UnsupportedEncodingException {
		this.initUI();
	}

	/**
	 * Initializes a window with all necessary components.
	 * This includes a JComboBox, JButton, multiple JRadioButtons 
	 * and multiple JLabels into the appropriate panels and adds 
	 * them to the current frame. 
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	private void initUI() throws FileNotFoundException, UnsupportedEncodingException {
		
		/**
		 * testing to see where .txt files get saved into in mvn package
		 */
		PrintWriter writer = new PrintWriter("test.txt", "UTF-8");
        writer.println("The first line");
        writer.println("The second line");
        writer.close();
        
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
		radCelsius.setForeground(Color.WHITE);
		radFahrenheit = new JRadioButton("Fahrenheit");
		radFahrenheit.setForeground(Color.WHITE);

		ButtonGroup grpTemp = new ButtonGroup();
		grpTemp.add(radCelsius);
		grpTemp.add(radFahrenheit);

		//Search buttons and selected value text
		JLabel lblHello = new JLabel("Choose your location...");
		lblHello.setForeground(Color.WHITE);
		final JLabel lblText = new JLabel();
		lblText.setForeground(Color.WHITE);
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
					//LocalWeatherView lwv = new LocalWeatherView();
					//lwv.setVisible(true);
				}
			}
		});      

		//Set Preferred width of combo box
		combo.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXX");

		//Create Frames
		this.setTitle("CS 2212 - Weather App - Selection Page"); 
		this.setSize(600, 150);
		this.setBackground(Color.BLACK);
		this.setForeground(Color.WHITE);

		//Add panes to window
		JPanel paneNorth = new JPanel();
		JPanel paneCenter = new JPanel();
		JPanel paneSouth = new JPanel();
		this.add(paneNorth, BorderLayout.NORTH);
		this.add(paneCenter, BorderLayout.CENTER);
		this.add(paneSouth, BorderLayout.SOUTH);

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

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}