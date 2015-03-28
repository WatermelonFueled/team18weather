package ca.uwo.csd.cs2212.team18;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;

/**
 * The class is model of table it contains list of LocalWeatherData objects and method to
 * manage this list
 * 
 * This part written by Samirah
 * 
 */
public class WeatherDataTableModel extends AbstractTableModel implements ChangeListener {

    private String[] columnNames = {"Temperature", "WindSpeed", 
        "Wind Direction", "Air Pressure", "Humidity", "Min Temp", "Max Temp"};
    private List<LocalWeatherData> items = new ArrayList<LocalWeatherData>();
    
    /**
     * constructor
     */
    public WeatherDataTableModel() {
       
    }

    /**
     * change states
     */
    public void stateChanged(ChangeEvent arg0) {
        fireTableDataChanged();
    }

    /**
     * clear model
     */
    public void clear() {
        items.clear();
        fireTableDataChanged();
    }

    /**
     * get column name
     * @param col
     * @return columnNames
     */
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    /**
     * get number of columns
     * @return  number of columns
     */
    public int getColumnCount() {
        return columnNames.length;
    }
    

    /**
     * add a row
     * @param a
     */
    public void add(LocalWeatherData a) {
        if (a != null) {
            items.add(a);
            fireTableRowsInserted(items.size() - 1, items.size() - 1);
            fireTableDataChanged();
        }
    }

    /**
     * get number of rows
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
                val = items.get(row).getTemperature();
                break;
            case 1:
                val = items.get(row).getWindSpeed();
                break;
            case 2:
                val = items.get(row).getWindDirection();
                break;
            case 3:
                val = items.get(row).getAirPressure();
                break;
            case 4:
                val = items.get(row).getHumidity();
                break;
            case 5:
                val = items.get(row).getMinTemperature();
                break;
            case 6:
                val = items.get(row).getMaxTemperature();
                break;   
             
        }
        return val;
    }
}

