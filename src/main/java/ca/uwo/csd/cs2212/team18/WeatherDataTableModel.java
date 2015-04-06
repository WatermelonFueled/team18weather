package ca.uwo.csd.cs2212.team18;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;

/**
 * <h1> Weather Data Table Model </h1>
 * The class is model of table it contains list of LocalWeatherData objects and method to
 * manage this list
 * 
 * @author Samirah
 * 
 */
public class WeatherDataTableModel extends AbstractTableModel implements ChangeListener {

	private static final long serialVersionUID = 4209513855606418173L;

	private String[] columnNames = {"Time", "Temperature", "Sky Condition", 
			"", "Min Temp", "Max Temp"};

	private List<LocalWeatherData> items = new ArrayList<LocalWeatherData>();

	/**
	 * Constructor
	 */
	public WeatherDataTableModel() {}

	/**
	 * Change states
	 */
	public void stateChanged(ChangeEvent arg0) {
		fireTableDataChanged();
	}

	/**
	 * Clear model
	 */
	public void clear() {
		items.clear();
		fireTableDataChanged();
	}

	/**
	 * @return  column name
	 */
	public String getColumnName(int col) {
		return columnNames[col];
	}

	/**
	 * @return  number of columns
	 */
	public int getColumnCount() {
		return columnNames.length;
	}


	/**
	 * Add row of data 
	 * @param a local weather data object
	 */
	public void add(LocalWeatherData a) {
		if (a != null) {
			items.add(a);
			fireTableRowsInserted(items.size() - 1, items.size() - 1);
			fireTableDataChanged();
		}
	}

	/**
	 * @return number of rows
	 */
	public int getRowCount() {
		return items.size();
	}

	/**
	 * get value at cell of table
	 * @param row
	 * @param col
	 * @return value at cell
	 */
	public Object getValueAt(int row, int col) {
		Object val = null;
		switch (col) {
		case 0:
			val = items.get(row).getTime();
			break;
		case 1:
			val = items.get(row).getTemperature() + "\u00b0" + ((DataRequester.unit == DataRequester.Unit.CELCIUS)?"C":"F");
			break;
		case 2:
			val = items.get(row).getSkyCondition();
			break;
		case 3:
			URL url;
			try {
				url = new URL("http://openweathermap.org/img/w/" + items.get(row).getSkyIcon() + ".png");
				val = new ImageIcon(url);                    
			} catch (Exception ex) {
				Logger.getLogger(WeatherDataTableModel.class.getName()).log(Level.SEVERE, null, ex);
				val = "";
			}
			break;
		case 4:
			val = items.get(row).getMinTemperature()+ "\u00b0" + ((DataRequester.unit == DataRequester.Unit.CELCIUS)?"C":"F");
			break;
		case 5:
			val = items.get(row).getMaxTemperature() + "\u00b0" + ((DataRequester.unit == DataRequester.Unit.CELCIUS)?"C":"F");
			break;             
		}
		return val;
	}
}

