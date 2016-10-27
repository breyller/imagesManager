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
                img = new Image(rs.getString("description"), rs.getString("title"), rs.getString("path"), rs.getString("hash"), rs.getInt("idFotos"));
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
                img = new Image(rs.getString("description"), rs.getString("title"), rs.getString("path"), rs.getString("hash"), rs.getInt("idFotos"));
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
                img = new Image(rs.getString("description"), rs.getString("title"), rs.getString("path"), rs.getString("hash"), rs.getInt("idFotos"));
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

    public Image getImageById(int id, int idAlbum) {
        Image img = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DbConnector.getConnection();
            
            stmt = conn.prepareStatement("SELECT f.* FROM fotos f JOIN album_fotos af ON f.id_foto = af.idFotos JOIN album a ON af.id_album = a.idAlbum WHERE f.idFotos = ? AND a.idAlbum = ?");
            stmt.setInt(1, id);
            stmt.setInt(2, idAlbum);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                img = new Image(rs.getString("description"), rs.getString("title"), rs.getString("path"), rs.getString("hash"), rs.getInt("idFotos"));
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

    public Image getImageByHash(String hash, int idAlbum) {
        Image img = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DbConnector.getConnection();
            
            stmt = conn.prepareStatement("SELECT f.* FROM fotos f JOIN album_fotos af ON f.id_foto = af.idFotos JOIN album a ON af.id_album = a.idAlbum WHERE f.hash = ? AND a.idAlbum = ?");
            stmt.setString(1, hash);
            stmt.setInt(2, idAlbum);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                img = new Image(rs.getString("description"), rs.getString("title"), rs.getString("path"), rs.getString("hash"), rs.getInt("idFotos"));
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

    public ArrayList<Image> getImageByTitle(String title, int idAlbum) {
        Image img = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Image> images = new ArrayList<>();
        

        try {
            conn = DbConnector.getConnection();
            
            stmt = conn.prepareStatement("SELECT f.* FROM fotos f JOIN album_fotos af ON f.id_foto = af.idFotos JOIN album a ON af.id_album = a.idAlbum WHERE f.title LIKE ? AND a.idAlbum = ?");
            stmt.setString(1, "%"+title+"%");
            stmt.setInt(2, idAlbum);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                img = new Image(rs.getString("description"), rs.getString("title"), rs.getString("path"), rs.getString("hash"), rs.getInt("idFotos"));
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

    public ArrayList<Image> getImageByDescription(String description) {
        Image img = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Image> images = new ArrayList<>();
        

        try {
            conn = DbConnector.getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM fotos WHERE title LIKE ?");
            stmt.setString(1, "%"+description+"%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                img = new Image(rs.getString("description"), rs.getString("title"), rs.getString("path"), rs.getString("hash"), rs.getInt("idFotos"));
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

    public ArrayList<Image> getImageByDescription(String description, int idAlbum) {
        Image img = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Image> images = new ArrayList<>();
        

        try {
            conn = DbConnector.getConnection();
            
            stmt = conn.prepareStatement("SELECT f.* FROM fotos f JOIN album_fotos af ON f.id_foto = af.idFotos JOIN album a ON af.id_album = a.idAlbum WHERE f.title LIKE ? AND a.idAlbum = ?");
            stmt.setString(1, "%"+description+"%");
            stmt.setInt(2, idAlbum);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                img = new Image(rs.getString("description"), rs.getString("title"), rs.getString("path"), rs.getString("hash"), rs.getInt("idFotos"));
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

    public ArrayList<Image> getImageByAlbumId(int idAlbum) {
        Image img = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Image> images = new ArrayList<>();
        

        try {
            conn = DbConnector.getConnection();
            
            stmt = conn.prepareStatement("SELECT f.* FROM fotos f JOIN album_fotos af ON f.id_foto = af.idFotos JOIN album a ON af.id_album = a.idAlbum WHERE a.idAlbum = ?");
            stmt.setString(1, "%"+idAlbum+"%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                img = new Image(rs.getString("description"), rs.getString("title"), rs.getString("path"), rs.getString("hash"), rs.getInt("idFotos"));
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

    public Album getAlbumById(int id) {
        Album alb = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DbConnector.getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM album WHERE idAlbum = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                alb = new Album(rs.getString("description"), rs.getString("title"), rs.getInt("idAlbum"));
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
        
        return alb;
    }

    public ArrayList<Album> getAlbumByTitle(String title) {
        Album alb = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Album> albumns = new ArrayList<>();
        
        try {
            conn = DbConnector.getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM fotos WHERE title LIKE ?");
            stmt.setString(1, "%"+title+"%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                alb = new Album(rs.getString("description"), rs.getString("title"), rs.getInt("idAlbum"));
            }
            albumns.add(alb);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbManipulate.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                DbConnector.closeConnection(conn, stmt, rs);
            } catch (SQLException ex) {
                Logger.getLogger(DbManipulate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return albumns;
    }

    public ArrayList<Album> getAlbumByDescription(String description) {
        Album alb = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Album> albumns = new ArrayList<>();
        
        try {
            conn = DbConnector.getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM fotos WHERE description LIKE ?");
            stmt.setString(1, "%"+description+"%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                alb = new Album(rs.getString("description"), rs.getString("title"), rs.getInt("idAlbum"));
            }
            albumns.add(alb);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbManipulate.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                DbConnector.closeConnection(conn, stmt, rs);
            } catch (SQLException ex) {
                Logger.getLogger(DbManipulate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return albumns;
    }

    public boolean setAlbum(Album alb) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stmt = null;
                
        try {
            conn = DbConnector.getConnection();
            stmt = conn.prepareStatement("INSERT INTO album(title, description) VALUES (?,?)");
            stmt.setString(1, alb.getTitle());
            stmt.setString(2, alb.getDescription());
            stmt.executeUpdate();
            result = true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbManipulate.class.getName()).log(Level.SEVERE, null, ex);
            return result;
        }finally{
            try {
                DbConnector.closeConnection(conn, stmt);
            } catch (SQLException ex) {
                Logger.getLogger(DbManipulate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }

    public boolean setImage(Image img) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stmt = null;
                
        try {
            conn = DbConnector.getConnection();
            stmt = conn.prepareStatement("INSERT INTO fotos(title, description, path, hash) VALUES (?,?,?,?)");
            stmt.setString(1, img.getTitle());
            stmt.setString(2, img.getDescription());
            stmt.setString(3, img.getPath());
            stmt.setString(4, img.getHash());
            stmt.executeUpdate();
            result = true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbManipulate.class.getName()).log(Level.SEVERE, null, ex);
            return result;
        }finally{
            try {
                DbConnector.closeConnection(conn, stmt);
            } catch (SQLException ex) {
                Logger.getLogger(DbManipulate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }

    public boolean setImageOnAlbum(int idImage, int idAlbum) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stmt = null;
                
        try {
            conn = DbConnector.getConnection();
            //@todo: 
            //fazer uma consulta para definir o order da imagem no primeiro momento.
            //criar funcao de ordenacao.
            //remover a linha abaixo.
            int order = 1;
            stmt = conn.prepareStatement("INSERT INTO album_fotos(id_album, id_foto, order) VALUES (?,?,?)");
            stmt.setInt(1, idAlbum);
            stmt.setInt(2, idImage);
            stmt.setInt(3, order);
            stmt.executeUpdate();
            result = true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbManipulate.class.getName()).log(Level.SEVERE, null, ex);
            return result;
        }finally{
            try {
                DbConnector.closeConnection(conn, stmt);
            } catch (SQLException ex) {
                Logger.getLogger(DbManipulate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }

    public boolean updateAlbum(Album alb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean updateImage(Image img) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean deleteImage(int idImage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean deleteAlbum(int idAlbum) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean deleteImageFromAlbum(Album alb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
