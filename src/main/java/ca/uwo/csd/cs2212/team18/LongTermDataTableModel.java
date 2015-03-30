package ca.uwo.csd.cs2212.team18;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;

public class LongTermDataTableModel extends AbstractTableModel implements ChangeListener {

	private static final long serialVersionUID = 5087358490416370361L;

	/**
   * columns
   */
  private String[] columnNames = {"Time", "Sky Condition", "Min Temp", "Max Temp"};
 
  private String[] wkdays = new String[7];
  
  private LongTermData longTermData;

  
  /**
   * constructor
   * @param items 
   */
  public LongTermDataTableModel() {
  	SimpleDateFormat format = new SimpleDateFormat("EEE dd/MM/yyyy");
  	 
      Calendar date = Calendar.getInstance();
      for(int i = 0; i < 7; i++){
      	wkdays[i] = format.format(date.getTime());
        date.add(Calendar.DATE  , 1);
        System.out.println(wkdays[i]);               
      }
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
  	if(longTermData!=null)
  	{
  	longTermData.clear();
      fireTableDataChanged();
  	}
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
  
  public void setLongTermData(LongTermData ltdata) {
      if (ltdata != null) {
          this.longTermData = ltdata;
          fireTableDataChanged();
      }
  }

  /**
   * get number of rows
   *
   * @return number of rows
   */
  public int getRowCount() {
      return 7;
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
              val = wkdays[col];
              break;
          case 1:
              val = longTermData.getSkyCondition()[col];
              break;
          
          case 2:
              val = longTermData.getTemperatureMin()[col];
              break;
          case 3:
              val = longTermData.getTemperatureMin()[col];
              break;             
      }
      return val;
  }
	
}
