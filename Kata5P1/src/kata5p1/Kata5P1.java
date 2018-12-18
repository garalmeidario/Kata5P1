package kata5p1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Kata5P1 {

    private final static String url = "jdbc:sqlite:Kata5.db";
    private final static String path = "";
    
    public static void main(String[] args) throws SQLException, IOException {
        String table = "EMAIL";
        List<String> listMails = new MailListReader().read(path);
        String sql = "INSERT INTO " + table + "(mail)" + " VALUES(?)";
        
        try (Connection conn= connect();
            PreparedStatement pstmt= conn.prepareStatement(sql)) {
            for (String email : listMails) {
                pstmt.setString(1, email);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }  
        
    public static Connection connect() {
    // Cadena de conexión SQLite
        Connection conn= null;
        try {
            conn= DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
