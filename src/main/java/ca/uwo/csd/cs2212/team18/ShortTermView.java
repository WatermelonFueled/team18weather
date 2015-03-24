package ca.uwo.csd.cs2212.team18;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * This part is written by Samirah
 */
public class ShortTermView extends javax.swing.JPanel {

     /**
     * table model
     */
    private WeatherDataTableModel weatherTableModel;
    
    /**
     * list of data
     */
    private List<LocalWeatherData> items;
    
    /**
     * table
     */
    private JTable weatherTable;
    
    /**
     * a reference to LocalWeatherData object
     */
    private ShortTermData shortTermData;
    /**
     * Creates new form ShortTermView
     */
    public ShortTermView(ShortTermData shortTermData) {
        initComponents();
        
        this.shortTermData = shortTermData;
        
        weatherTableModel = new WeatherDataTableModel();
        weatherTable = new JTable(weatherTableModel);
        
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
     * display new data (max 8 lines: 3 hours * 8 = next 24 hours)
     */
    public void display(){
        weatherTableModel.clear();
        for (int i = 0; i < 8 && i < shortTermData.getData().size(); i++) {
            weatherTableModel.add(shortTermData.getData().get(i));
        }
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

