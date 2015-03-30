package ca.uwo.csd.cs2212.team18;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

/**
 * <h1> Selection Page </h1>
 * This class creates a window for the selections page in which a user can
 * select their preferences. This includes choosing current location and
 * temperature format. This class will be used on initial startup when there are
 * no saved preferences and when a user wants to change their current
 * preferences.
 *
 * @author DianaGodoy
 */
public class SelectionPage extends JPanel {

	private static final long serialVersionUID = -5681712855274371085L;
	private DataRequester dataRequester;
	private LocalWeatherView localWeatherView;
	private ShortTermView shortTermView;

	/**
	 * Creates a new SelectionPage and initializes the user interface. Window is
	 * not visible by default.
	 * @param localWeatherView
	 * @param shortTermView
	 * @param dataRequester
	 */
	public SelectionPage(LocalWeatherView localWeatherView, ShortTermView shortTermView,
			DataRequester dataRequester) {
		this.localWeatherView = localWeatherView;
		this.shortTermView = shortTermView;
		this.dataRequester = dataRequester;
		this.initUI(); 
	}

	/**
	 * Initializes a window with all necessary components. This includes a
	 * JComboBox, JButton, multiple JRadioButtons and multiple JLabels into the
	 * appropriate panels and adds them to the current frame.
	 */
	private void initUI(){

		//Get data for comboBox
		final ArrayList<String> locList = new ArrayList<String>();

		//Catch exceptions
		try {
			locationList(locList);
		} 
		catch (FileNotFoundException e){
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		//Search buttons and selected value text
		JLabel lblHello = new JLabel("Choose your location...");
		lblHello.setForeground(Color.BLACK);
		final JLabel lblText = new JLabel();
		lblText.setForeground(Color.BLACK);

		//Radio Buttons
		JRadioButton radCelsius;
		final JRadioButton radFahrenheit;

		radCelsius = new JRadioButton("Celsius");
		radCelsius.setSelected(true);
		radCelsius.setForeground(Color.BLACK);
		radFahrenheit = new JRadioButton("Fahrenheit");
		radFahrenheit.setForeground(Color.BLACK);

		ButtonGroup grpTemp = new ButtonGroup();
		grpTemp.add(radCelsius);
		grpTemp.add(radFahrenheit);

		//Auto Complete Combo Box
		StringSearchable searchable = new StringSearchable(locList);
		final AutoCompleteJComboBox combo = new AutoCompleteJComboBox(searchable);
		combo.setEditable(true);

		//Change listener for when switching between radio buttons
		radCelsius.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				String msg = "Selected: " + combo.getSelectedItem() + " in Celsius";
				lblText.setText(msg);
			}
		});

		//Change listener for when switching between radio buttons
		radFahrenheit.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				String msg = "Selected: " + combo.getSelectedItem() + " in Fahrenheit";
				lblText.setText(msg);
			}
		});

		//KeyListener for when hitting enter in window
		KeyListener eListener = new KeyListener() {
			public void keyReleased(KeyEvent event) {
				if (event.getKeyChar() == KeyEvent.VK_ENTER) {
					combo.setPopupVisible(true);
					if (!(locList.contains(combo.getSelectedItem()))) {
						String err = "Error: Incorrect Location, Please Try Again";
						lblText.setText(err);
					} 
					else {
						String msg = "Selected: " + combo.getSelectedItem();
						if (radFahrenheit.isSelected())	msg += " in Fahrenheit";
						else msg += " in Celsius";
						lblText.setText(msg);
					}
				}
			}
			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent e) {}
		};


		//Action listeners for window
		radCelsius.addKeyListener(eListener);
		radFahrenheit.addKeyListener(eListener);
		combo.getEditor().getEditorComponent().addKeyListener(eListener);

		//Set Preferred width of combo box
		combo.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXX");

		//Add panes to window
		JPanel paneNorth = new JPanel();
		JPanel paneCenter = new JPanel();
		JPanel paneSouth = new JPanel();

		JButton GWButton = new JButton("Get Weather");

		//Add action listener to button "Get Local Weather"
		GWButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!(combo.getSelectedItem().toString().equalsIgnoreCase("mars")) && !(locList.contains(combo.getSelectedItem()))) {
					String err = "Error: Incorrect Location, Please Try Again";
					lblText.setText(err);
					//combo.setPopupVisible(true);
				} else {
					String msg = "Selected: " + combo.getSelectedItem();
					if (radFahrenheit.isSelected()) {
						msg += " in Fahrenheit";
						dataRequester.setFahrenheit();
					} else {
						msg += " in Celsius";
						dataRequester.setCelcius();
					}
					lblText.setText(msg);

					// Print result to preferences file for secondary startup
					try {
						PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preferences.txt", true)));
						out.println(lblText.getText().toString());
						out.close();
					} catch (IOException f) {}

					String city = combo.getSelectedItem().toString();
					String cityName;
					String cityId;
					if (city.equalsIgnoreCase("mars")){
						cityName = "Mars";
						cityId = "Mars";
					}else{
						cityName = city.substring(0, city.indexOf('['));
						cityId = city.substring(city.indexOf('[')+1, city.indexOf(']'));
					}

					if(cityId.equals("Mars")) {
						dataRequester.update(cityId);
						localWeatherView.setCityName(cityName);
						try {
							localWeatherView.setLabels();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						shortTermView.clear();
					}
					else{
						dataRequester.update(cityId);
						localWeatherView.setCityName(cityName);
						try {
							localWeatherView.setLabels();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						shortTermView.display();
					}



					lblText.setText("Weather Fetched");
				}
			}
		});

		//Layout for window
		paneNorth.setLayout(new FlowLayout(FlowLayout.LEFT));
		paneNorth.add(lblHello);


		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(36)
										.addComponent(paneNorth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
												.addContainerGap()
												.addComponent(paneCenter, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
												.addGroup(groupLayout.createSequentialGroup()
														.addContainerGap()
														.addComponent(paneSouth, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)))
														.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(5)
						.addComponent(paneNorth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(paneCenter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(paneSouth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(181))
				);
		GroupLayout gl_paneCenter = new GroupLayout(paneCenter);
		gl_paneCenter.setHorizontalGroup(
				gl_paneCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_paneCenter.createSequentialGroup()
						.addContainerGap()
						.addComponent(combo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(radFahrenheit)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(radCelsius)
						.addGap(62))
				);
		gl_paneCenter.setVerticalGroup(
				gl_paneCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_paneCenter.createSequentialGroup()
						.addGap(7)
						.addGroup(gl_paneCenter.createParallelGroup(Alignment.BASELINE)
								.addComponent(combo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(radFahrenheit)
								.addComponent(radCelsius)))
				);
		paneCenter.setLayout(gl_paneCenter);		

		GroupLayout gl_paneSouth = new GroupLayout(paneSouth);
		gl_paneSouth.setHorizontalGroup(
				gl_paneSouth.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_paneSouth.createSequentialGroup()
						.addGap(7)
						.addComponent(lblText, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(GWButton)
						.addContainerGap())
				);
		gl_paneSouth.setVerticalGroup(
				gl_paneSouth.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_paneSouth.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_paneSouth.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblText, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
								.addComponent(GWButton, Alignment.LEADING))
								.addContainerGap())
				);
		paneSouth.setLayout(gl_paneSouth);
		setLayout(groupLayout);
	}

	/**
	 * Reads from city list and places information in the Array List
	 * @param locList populates with city info
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void locationList(ArrayList<String> locList) throws FileNotFoundException, IOException{
		//Get file from resources folder
		InputStream input = getClass().getClassLoader().getResourceAsStream("cityList.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));

		String line;
		while ((line = reader.readLine()) != null){
			locList.add(line);
		}
		reader.close();
	}
}
