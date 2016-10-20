import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnector {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/imagesManager";
    private static final String USER = "root";
    private static final String PASS = "root";
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASS);
    }
    
    public static void closeConnection(Connection conn) throws SQLException{
        if(conn != null){
            conn.close();
        }
    }
    
    public static void closeConnection(Connection conn, PreparedStatement stmt) throws SQLException{
        closeConnection(conn);
        
        if(stmt != null){
            stmt.close();
        }
    }
    
    public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs) throws SQLException{
        closeConnection(conn, stmt);
        
        if(rs != null){
            rs.close();
        }
    }
}