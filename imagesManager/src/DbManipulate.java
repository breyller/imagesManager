import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbManipulate implements IPersistencia{
    
    public Image getImageById(int id){
        Image img = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DbConnector.getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM fotos WHERE idFotos = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                img = new Image();
                img.setId(rs.getInt("idFotos"));
                img.setTitle(rs.getString("title"));
                img.setDescription(rs.getString("description"));
                img.setPath(rs.getString("path"));
                img.setHash(rs.getString("hash"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbManipulate.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                DbConnector.closeConnection(conn, stmt, rs);
            } catch (SQLException ex) {
                Logger.getLogger(DbManipulate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return img;
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
