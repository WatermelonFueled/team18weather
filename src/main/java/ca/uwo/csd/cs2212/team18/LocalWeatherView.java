import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.GroupLayout;
import java.awt.BorderLayout;

public class LocalWeatherView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4030151571743652812L;

	public LocalWeatherView()
	{
		this.initUI();
	}
	
	private void initUI()
	{
		this.setTitle("Team 18 - Local Weather View");
		this.setSize(800,750);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setJMenuBar(this.createMenubar());
		
		
		this.setLayout(new BorderLayout()); 
		this.add(this.createView(), BorderLayout.CENTER);
	}
	
	private JMenuBar createMenubar() 
	{
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		menuFile.setMnemonic(KeyEvent.VK_F);
		JMenuItem menuFileExit = new JMenuItem("Exit");
		menuFileExit.setMnemonic(KeyEvent.VK_E);
		menuFileExit.setToolTipText("Exit Local Weater View");
		menuFileExit.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				System.exit(0); 
			}
		});
		menuFile.add(menuFileExit);
		menuBar.add(menuFile);
		return menuBar;
	}
	
	private JPanel createView() 
	{
		JPanel panel = new JPanel();
		JLabel lblcurloc = new JLabel("Current Location: ");
		JLabel lbltemp = new JLabel("Current Temperature: ");
		JLabel lblskypic = new JLabel("PICTURE OF CURRENT WEATHER");
		JLabel lblwind = new JLabel("Wind: ");
		JLabel lblairp = new JLabel("Air Pressure: ");
		JLabel lblhumidity = new JLabel("Humidity: ");
		JLabel lblskyc = new JLabel("Sky Condition: ");
		JLabel lblmintemp = new JLabel("Minimum Temperature: ");
		JLabel lblmaxtemp = new JLabel("Maximum Temperature: ");
		JLabel lblsunrise = new JLabel("Sunrise: ");
		JLabel lblsunset = new JLabel("Sunset: ");

		GroupLayout layout = new GroupLayout(panel); 
		layout.setAutoCreateGaps(true); 
		layout.setAutoCreateContainerGaps(true); 
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING) 
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING) 
									.addComponent(lblcurloc)
									.addComponent(lblskypic)
									.addComponent(lbltemp) 
							)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING) 
								.addGroup(layout.createSequentialGroup() 
										.addComponent(lblmaxtemp) 
										.addComponent(lblmintemp) 
								) 
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING) 
								.addGroup(layout.createSequentialGroup() 
										.addComponent(lblsunrise) 
										.addComponent(lblsunset) 
								)
								)
							)
						)
					)
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lblmaxtemp)
						.addComponent(lblmintemp)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lblsunrise)
						.addComponent(lblsunset)
				)
		);

		panel.setLayout(layout);
		return panel;
	}
}
