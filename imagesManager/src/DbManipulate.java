import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManipulate implements IPersistencia{
    
    private Connection conn;
    
    public DbManipulate(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3059/imagesManager", "root", "root");
            System.out.println("Conectado com sucesso.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro de class not found: " + ex);
        } catch (SQLException ex) {
            System.out.println("Erro de conexao mysql: " + ex);
        }
        
    }
    
    public Image getImageById(int id){
        Image a = new Image();
        return a;
    }

    public Image getImageByHash(String hash) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public Image getImageByName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
