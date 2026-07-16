import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ViewTripsFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public ViewTripsFrame() {
        // Setup Window
        setTitle("Saved Trips History");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Only close this window, not the whole app
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create Table Model with column names
        String[] columns = {"ID", "Name", "City", "Days", "Travellers", "Type", "Budget"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        
        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Add Refresh Button
        JButton refreshBtn = new JButton("Refresh Data");
        refreshBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });
        add(refreshBtn, BorderLayout.SOUTH);

        // Initial data load
        loadData();
    }

    // Method to fetch data from DatabaseHelper and populate the table
    private void loadData() {
        tableModel.setRowCount(0); // Clear existing rows
        
        try (Connection conn = DatabaseHelper.getConnection();
             Statement stmt = conn.createStatement()) {
            
            ResultSet rs = DatabaseHelper.getAllTrips(stmt);
            
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("city"),
                    rs.getInt("days"),
                    rs.getInt("travellers"),
                    rs.getString("travelType"),
                    rs.getString("budget")
                };
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }
    }
}
