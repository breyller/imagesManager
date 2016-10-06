import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManipulate implements IPersistencia{
    
    private Connection conn;
    
    public DbManipulate() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3059/imagesManager", "root", "root");
        System.out.println("Conectado com sucesso.");
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
    
    public static void main(String[] args) {
        try{
            DbManipulate a;
            a = new DbManipulate();
        }catch(Exception e){
            System.out.println("Erro ao instanciar: " + e);
        }
    }
    
}
