import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        Image img = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DbConnector.getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM fotos WHERE hash = ?");
            stmt.setString(1, hash);
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
    
    public ArrayList<Image> getImageByTitle(String title) {
        Image img = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Image> images = new ArrayList<>();
        

        try {
            conn = DbConnector.getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM fotos WHERE title LIKE ?");
            stmt.setString(1, "%"+title+"%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                img = new Image();
                img.setId(rs.getInt("idFotos"));
                img.setTitle(rs.getString("title"));
                img.setDescription(rs.getString("description"));
                img.setPath(rs.getString("path"));
                img.setHash(rs.getString("hash"));
                
                images.add(img);
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
        
        return images;
    }
    
    
}
