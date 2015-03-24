package ca.uwo.csd.cs2212.team18;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

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
		try {
			locationList(locList);
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

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

		//Search buttons and selected value text
		JLabel lblHello = new JLabel("Choose your location...");
		lblHello.setForeground(Color.BLACK);
		final JLabel lblText = new JLabel();
		lblText.setForeground(Color.BLACK);
		JButton GWButton = new JButton("Get Local Weather");

		//Auto Complete Combo Box
		StringSearchable searchable = new StringSearchable(locList);
		final AutoCompleteJComboBox combo = new AutoCompleteJComboBox(searchable);
		combo.setEditable(true);

		//KeyListener for when hitting enter in window
		KeyListener eListener = new KeyListener() {
			public void keyReleased(KeyEvent event) {
				if (event.getKeyChar() == KeyEvent.VK_ENTER) {
					combo.setPopupVisible(true);
					if (!(locList.contains(combo.getSelectedItem()))) {
						String err = "Error: Incorrect Location, Please Try Again";
						lblText.setText(err);
					} else {
						String msg = "Selected: " + combo.getSelectedItem();
						if (radFahrenheit.isSelected()) {
							msg += " in Fahrenheit";
						} else {
							msg += " in Celsius";
						}
						lblText.setText(msg);
					}
				}
			}

			public void keyTyped(KeyEvent arg0) {
			}

			public void keyPressed(KeyEvent e) {
			}
		};

		//If hit enter select entered values and display them
		radCelsius.addKeyListener(eListener);
		radFahrenheit.addKeyListener(eListener);
		combo.getEditor().getEditorComponent().addKeyListener(eListener);

		//Add action listener to button "Get Local Weather"
		GWButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!(locList.contains(combo.getSelectedItem()))) {
					String err = "Error: Incorrect Location, Please Try Again";
					lblText.setText(err);
					//combo.setPopupVisible(true);
				} else {
					String msg = "Selected: " + combo.getSelectedItem();
					if (radFahrenheit.isSelected()) {
						msg += " in Fahrenheit";
					} else {
						msg += " in Celsius";
					}
					lblText.setText(msg);
					
					// Print result to preferences file for secondary startup
					try {
						PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preferences.txt", true)));
						out.println(lblText.getText().toString());
						out.close();
					} catch (IOException f) {}

					String city = combo.getSelectedItem().toString();
					String cityName = city.substring(0, city.indexOf('['));
					String cityId = city.substring(city.indexOf('[')+1, city.indexOf(']'));

					//updates local, short term, long term data for selected city
					dataRequester.requestLocal(cityId);
					dataRequester.requestShort(cityId);
					dataRequester.requestLong(cityId);
					localWeatherView.setCityName(cityName);
					localWeatherView.setLabels();
                                        
                                        shortTermView.display();
					
				}
			}
		});

		//Set Preferred width of combo box
		combo.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXX");

		//Add panes to window
		JPanel paneNorth = new JPanel();
		JPanel paneCenter = new JPanel();
		JPanel paneSouth = new JPanel();

		//Top pane
		paneNorth.setLayout(new FlowLayout(FlowLayout.LEFT));
		paneNorth.add(lblHello);

		//Center pane
		paneCenter.add(combo);
		paneCenter.add(radFahrenheit);
		paneCenter.add(radCelsius);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(paneCenter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(paneNorth, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(paneSouth, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(36))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(paneNorth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(paneCenter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(paneSouth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(179))
		);
		GroupLayout gl_paneSouth = new GroupLayout(paneSouth);
		gl_paneSouth.setHorizontalGroup(
			gl_paneSouth.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_paneSouth.createSequentialGroup()
					.addGap(7)
					.addComponent(lblText, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(GWButton)
					.addGap(57))
		);
		gl_paneSouth.setVerticalGroup(
			gl_paneSouth.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_paneSouth.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_paneSouth.createParallelGroup(Alignment.LEADING)
						.addComponent(lblText, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(GWButton, Alignment.TRAILING))
					.addContainerGap())
		);
		paneSouth.setLayout(gl_paneSouth);
		setLayout(groupLayout);
	}

	/**
	 * reads from city list and places information in the Array List
	 * @param locList to populate with city info
	 * @throws FileNotFoundException 
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
