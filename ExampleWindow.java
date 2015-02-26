import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class ExampleWindow extends JFrame {

	private static final long serialVersionUID = -5393294539017896082L;
	private JTextField txtLocation;
	private JLabel lblText;
	
	public ExampleWindow() {
		this.initUI();
	}
	
	private void initUI() {
	
	    Color steelBlue = new Color(70, 130, 180);
		Color steelBlue1 = new Color(99, 184, 255);
		Color steelBlue2 = new Color(92, 172, 238);
		Color steelBlue3 = new Color(79, 148, 205);
		
		this.setTitle("CS 2212 - Weather App"); 
		this.setSize(500, 650);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		JPanel pnlWest = new JPanel(); 
		pnlWest.setBackground(steelBlue1); 
		pnlWest.setPreferredSize(new Dimension(100,200));
		
		JPanel pnlEast = new JPanel(); 
		pnlEast.setBackground(steelBlue1); 
		pnlEast.setPreferredSize(new Dimension(100,200));
		
		JPanel pnlNorth = new JPanel(); 
		pnlNorth.setBackground(steelBlue); 
		pnlNorth.setPreferredSize(new Dimension(300,50));
		lblText = new JLabel("Current Location Goes Here");
		pnlNorth.add(lblText);	
		
		JPanel pnlSouth = new JPanel(); 
		pnlSouth.setBackground(steelBlue); 
		pnlSouth.setPreferredSize(new Dimension(300,25));
		lblText = new JLabel("Last Update Goes Here");
		pnlSouth.add(lblText);		
		
		this.setLayout(new BorderLayout()); 
		this.add(this.createForm(), BorderLayout.CENTER);
		
		this.add(pnlEast, BorderLayout.EAST);
		this.add(pnlWest, BorderLayout.WEST);
		this.add(pnlNorth, BorderLayout.NORTH);
		this.add(pnlSouth, BorderLayout.SOUTH);
		
	}
	
	private JPanel createForm() {
		Color steelBlue1 = new Color(99, 184, 255);

		JPanel panel = new JPanel();
		panel.setBackground(steelBlue1);
		JLabel lblName = new JLabel("Location:");
		lblName.setHorizontalAlignment(JLabel.CENTER);
		
		txtLocation = new JTextField();
		
		lblText = new JLabel();
		JButton btnSearch = new JButton("Search");
		
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String msg = "Selected: " + txtLocation.getText();
				lblText.setText(msg);
			}
		});
		
		GroupLayout layout = new GroupLayout(panel); 
		layout.setAutoCreateGaps(true); 
		layout.setAutoCreateContainerGaps(true); 
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING) 
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING) 
										.addComponent(lblName)
								)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING) 
										.addComponent(txtLocation)
								)
						) 
						.addComponent(lblText)
				)
				.addComponent(btnSearch) ); 
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE) 
						.addComponent(lblName)
						.addComponent(txtLocation)
				) 
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lblText)
						.addComponent(btnSearch) 
				)
		);
		
		panel.setLayout(layout);
		return panel;
	}
}
