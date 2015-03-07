package ca.uwo.csd.cs2212.team18;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

public class LocalWeatherView extends JFrame {

	private static final long serialVersionUID = -5393294539017896082L;
//	private JTextField lblTemp;
	private JLabel lblText;
	
	public LocalWeatherView() {
		this.initUI();
	}
	
	private void initUI() {
	
		/*
	    Color steelBlue = new Color(70, 130, 180);
		Color steelBlue1 = new Color(99, 184, 255);
		Color steelBlue2 = new Color(92, 172, 238);
		Color steelBlue3 = new Color(79, 148, 205);
		*/
		
		this.setTitle("CS 2212 - Weather App"); 
		this.setSize(500, 650);
		this.setBackground(Color.BLACK);
		this.setForeground(Color.WHITE);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		//JPanel pnlWest = new JPanel(); 
		//pnlWest.setBackground(steelBlue1); 
		//pnlWest.setPreferredSize(new Dimension(100,200));
		
		JPanel pnlEast = new JPanel(); 
		pnlEast.setBackground(getBackground()); 
		pnlEast.setPreferredSize(new Dimension(100,200));
		
		JPanel pnlNorth = new JPanel(); 
		pnlNorth.setBackground(getBackground()); 
		pnlNorth.setPreferredSize(new Dimension(300,50));
		lblText = new JLabel("Current Location Goes Here");
		pnlNorth.add(lblText);	
		
		JPanel pnlSouth = new JPanel(); 
		pnlSouth.setBackground(getBackground()); 
		pnlSouth.setPreferredSize(new Dimension(300,25));
		lblText = new JLabel("Last Update Goes Here");
		pnlSouth.add(lblText);		
		
		this.setLayout(new BorderLayout()); 
		this.add(this.createForm(), BorderLayout.CENTER);
		
		this.add(pnlEast, BorderLayout.EAST);
		//this.add(pnlWest, BorderLayout.WEST);
		this.add(pnlNorth, BorderLayout.NORTH);
		this.add(pnlSouth, BorderLayout.SOUTH);
		
	}
	
	private JPanel createForm() {
		//Color steelBlue1 = new Color(99, 184, 255);

		JPanel panel = new JPanel();
		panel.setBackground(getBackground());
		
		JLabel lblTemp = new JLabel("Current Temperature:");
		lblTemp.setHorizontalAlignment(JLabel.CENTER);
		lblTemp.setForeground(Color.WHITE);
		
		JLabel lblskypic = new JLabel("PICTURE OF CURRENT WEATHER");
		lblskypic.setHorizontalAlignment(JLabel.CENTER);
		lblskypic.setForeground(Color.WHITE);

		JLabel lblairp = new JLabel("Air Pressure: ");
		lblairp.setHorizontalAlignment(JLabel.CENTER);
		lblairp.setForeground(Color.WHITE);

		JLabel lblhumidity = new JLabel("Humidity: ");
		lblhumidity.setHorizontalAlignment(JLabel.CENTER);
		lblhumidity.setForeground(Color.WHITE);

		JLabel lblwind = new JLabel("Wind Speed and Direction: ");
		lblwind.setHorizontalAlignment(JLabel.CENTER);
		lblwind.setForeground(Color.WHITE);

		JLabel lblskyc = new JLabel("Sky Condition: ");
		lblskyc.setHorizontalAlignment(JLabel.CENTER);
		lblskyc.setForeground(Color.WHITE);

		JLabel lblmintemp = new JLabel("Minimum Temperature: ");
		lblmintemp.setHorizontalAlignment(JLabel.CENTER);
		lblmintemp.setForeground(Color.WHITE);

		JLabel lblmaxtemp = new JLabel("Maximum Temperature: ");
		lblmaxtemp.setHorizontalAlignment(JLabel.CENTER);
		lblmaxtemp.setForeground(Color.WHITE);

		JLabel lblsunrise = new JLabel("Sunrise: ");
		lblsunrise.setHorizontalAlignment(JLabel.CENTER);
		lblsunrise.setForeground(Color.WHITE);

		JLabel lblsunset = new JLabel("Sunset: ");
		lblsunset.setHorizontalAlignment(JLabel.CENTER);
		lblsunset.setForeground(Color.WHITE);

		
		//txtLocation = new JTextField();
		
		//lblText = new JLabel();
		//JButton btnSearch = new JButton("Search");
		
		//btnSearch.addActionListener(new ActionListener() {
			//@Override
			//public void actionPerformed(ActionEvent event) {
				//String msg = "Selected: " + txtLocation.getText();
				//lblText.setText(msg);
			//}
		//});
		
		GroupLayout layout = new GroupLayout(panel); 
		layout.setAutoCreateGaps(true); 
		layout.setAutoCreateContainerGaps(true); 
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING) 
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING) 
										.addComponent(lblTemp)
										.addComponent(lblskyc)
										.addComponent(lblmaxtemp)
										.addComponent(lblmintemp)
										.addComponent(lblsunrise)
										.addComponent(lblsunset)
										.addComponent(lblairp)
										.addComponent(lblhumidity)
										.addComponent(lblwind)
								)
				
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING) 
									.addGroup(layout.createSequentialGroup()
											.addComponent(lblmaxtemp)
											.addComponent(lblmintemp)
											)
											)
										//.addComponent(txtLocation)
								//)
								)
								)
					);
						//.addComponent(lblText)
			//	)
			//	.addComponent(btnSearch) ); 
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE) 
						.addComponent(lblTemp)
						//.addComponent(lblmaxtemp)
						//.addComponent(lblsunrise)
						//.addComponent(lblsunset)

						//.addComponent(txtLocation)
				) 
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addGroup(layout.createSequentialGroup()
								.addComponent(lblskyc)
								.addComponent(lblmaxtemp)
								.addComponent(lblmintemp)
								.addComponent(lblsunrise)
								.addComponent(lblsunset)
								.addComponent(lblairp)
								.addComponent(lblhumidity)
								.addComponent(lblwind)
								)
						//.addComponent(lblText)
					//	.addComponent(btnSearch) 
				)
		);
		
		panel.setLayout(layout);
		return panel;
	}
}
