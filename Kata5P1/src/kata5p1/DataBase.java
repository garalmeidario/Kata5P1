package kata5p1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase{

    String url = "jdbc:sqlite:";
    
    public DataBase(String fileName){
        url = url + fileName;
    }
    
    public void createNewDatabase(){
        try (Connection conn= DriverManager.getConnection(url)) {
            if(conn!= null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("El driver es " + meta.getDriverName());
                System.out.println("Se ha creado una nueva BD.");
            }
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        }
    }
    
    private Connection connect() {
    // Cadena de conexión SQLite
        Connection conn= null;
        try {
            conn= DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    
    public void createNewTable(String table) {
        // Instrucción SQL para crear nueva tabla
        String sql= "CREATE TABLE IF NOT EXISTS " + table + " (\n"
            + "id integer PRIMARY KEY AUTOINCREMENT,\n"
            + "direccion text NOT NULL);";
        try (Connection conn= DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // Se crea la nueva tabla
            stmt.execute(sql);
            System.out.println("Tabla creada");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void insert(String email, String table) {
        String sql = "INSERT INTO " + table + "(direccion)" + " VALUES(?)";
        try (Connection conn= this.connect();
            PreparedStatement pstmt= conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
