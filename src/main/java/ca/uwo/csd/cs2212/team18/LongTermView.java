package ca.uwo.csd.cs2212.team18;
	

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

	/**
	 * This part is written by Sahaana
	 */
	public class LongTermView extends JPanel {

	    private LongTermDataTableModel longTermDataTableModel;
	    private JTable weatherTable;
	    private LongTermData longTermData;

	    /**
	     * Creates new form LongTermView
	     * @param longTermData
	     */
	    public LongTermView(LongTermData longTermData) {
	        initComponents();
	        
	        this.longTermData = longTermData;
	        
	        longTermDataTableModel = new LongTermDataTableModel();
	        weatherTable = new JTable(longTermDataTableModel){
	            
	            public Class getColumnClass(int column)
	            {
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
	     * display new data 
	     */
	    public void display(){
	        longTermDataTableModel.clear();
	        longTermDataTableModel.setLongTermData(longTermData);
	        }
	    
	    
	    /**
	     * Clear data, for when requesting Mars for example
	     */
	    public void clear(){
	    	longTermDataTableModel.clear();
	    }

	    /**
	     * This method is called from within the constructor to initialize the form.
	     * WARNING: Do NOT modify this code. The content of this method is always
	     * regenerated by the Form Editor.
	     */
	    @SuppressWarnings("unchecked")
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