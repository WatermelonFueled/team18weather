package ca.uwo.csd.cs2212.team18;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * <h1> Short Term View </h1>
 * This class creates a pane for the short term view 
 * @author Samirah
 */
public class ShortTermView extends javax.swing.JPanel {

	//Initializing variables
	private static final long serialVersionUID = 4187891373796404502L;
	private WeatherDataTableModel weatherTableModel;
	@SuppressWarnings("unused")
	private List<LocalWeatherData> items;
	private JTable weatherTable;
	private ShortTermData shortTermData;

	/**
	 * Creates new form ShortTermView
	 * @param shortTermData
	 */
	public ShortTermView(ShortTermData shortTermData) {

		initComponents();
		this.shortTermData = shortTermData;

		weatherTableModel = new WeatherDataTableModel();
		weatherTable = new JTable(weatherTableModel){

			private static final long serialVersionUID = -7896139589589100520L;
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int column){
				return getValueAt(0, column).getClass();
			}
		};

		weatherTable.setRowHeight(45);

		// For use with mouse listener and events on table when clicked
		weatherTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

		//add table to scroll pane
		JScrollPane jScrollPane= new javax.swing.JScrollPane();
		jScrollPane.setViewportView(weatherTable);

		//set layout
		setLayout(new BorderLayout());
		add(jScrollPane, BorderLayout.CENTER);
	}

	/**
	 * Display new data (max 8 lines: 3 hours * 8 = next 24 hours)
	 */
	public void display(){
		weatherTableModel.clear();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE HH:mm");
		for (int i = 0; i < 8 && i < shortTermData.getData().size(); i++) {
			shortTermData.getData().get(i).setTime(sdf.format(cal.getTime()));
			cal.add(Calendar.HOUR_OF_DAY, 3);
			weatherTableModel.add(shortTermData.getData().get(i));
		}
	}

	/**
	 * Clear table values, for when request to url returns nothing
	 * This will over write any existing data from previous selections
	 */
	public void clear(){
		weatherTableModel.clear();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 400, Short.MAX_VALUE)
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 300, Short.MAX_VALUE)
				);
	}// </editor-fold>//GEN-END:initComponents

	// Variables declaration - do not modify//GEN-BEGIN:variables
	// End of variables declaration//GEN-END:variables
}

