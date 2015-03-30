package ca.uwo.csd.cs2212.team18;

import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

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

    /**
     * columns
     */
    private String[] columnNames = {"Time", "Temperature", "Sky Condition", 
        "", "Min Temp", "Max Temp"};

    /**
     * list of items
     */
    private List<LocalWeatherData> items = new ArrayList<LocalWeatherData>();
    
    /**
     * constructor
     * @param items 
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
     * @return 
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
     *
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
     *
     * @return number of rows
     */
    public int getRowCount() {
        return items.size();
    }

    /**
     * get value at cell of table
     *
     * @return value at cell
     */
    public Object getValueAt(int row, int col) {
    	Object val = null;
        switch (col) {
            case 0:
                val = items.get(row).getTime();
                break;
            case 1:
                val = items.get(row).getTemperature() + " ¼" + items.get(row).getUnit();
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
                val = items.get(row).getMinTemperature() + " ¼" + items.get(row).getUnit();
                break;
            case 5:
                val = items.get(row).getMaxTemperature() + " ¼" + items.get(row).getUnit();
                break;             
        }
        return val;
    }
}

