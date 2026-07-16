import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    // Database credentials
    // IMPORTANT: Change "your_password" to the password you set during MySQL installation!
    private static final String URL = "jdbc:mysql://localhost:3306/tourplanner";
    private static final String USER = "root";
    private static final String PASSWORD = "Always_me2006"; 

    // Method to get a connection to the database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Method to save trip details into the database
    public static boolean saveTrip(String name, String city, int days, int travellers, String type, String budget, String plan) {
        String sql = "INSERT INTO trips (name, city, days, travellers, travelType, budget, plan) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, name);
            pstmt.setString(2, city);
            pstmt.setInt(3, days);
            pstmt.setInt(4, travellers);
            pstmt.setString(5, type);
            pstmt.setString(6, budget);
            pstmt.setString(7, plan);
            
            pstmt.executeUpdate();
            return true; // Return true if save is successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if there is an error
        }
    }

    // Method to retrieve all trips from the database for the JTable
    public static ResultSet getAllTrips(Statement stmt) throws SQLException {
        String sql = "SELECT id, name, city, days, travellers, travelType, budget FROM trips";
        return stmt.executeQuery(sql);
    }
}
