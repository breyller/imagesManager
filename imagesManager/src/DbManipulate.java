import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/*
* @author Bruno Lopes
* @author Bruno Reyller
* @author Henrique
* Classe que realiza as diversas opera��es com o banco de dados.
* @see Imagem
* @see Album
* @see DbConnector
*/
public class DbManipulate implements IPersistencia{
    /*
    * Fun��o pra enviar a imagem usando o identificador desta para seleciona-la no Banco de Dados
    * @param id - Identificador da Imagem a ser encontrada e enviada
    * @return - Retorna um Objeto do tipo Imagem da imagem desejada
    */
    public Imagem getImageById(int id){
        Imagem img = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DbConnector.getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM fotos WHERE idFotos = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                img = new Imagem(rs.getString("description"), rs.getString("title"), rs.getString("path"), rs.getString("hash"), rs.getInt("idFotos"));
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
    /*
    * Fun��o pra enviar a imagem usando a Hash em MD5 desta para seleciona-la no Banco de Dados
    * @param hash - Hash da Imagem a ser encontrada e enviada
    * @return - Retorna um Objeto do tipo Imagem da imagem desejada
    */
    public Imagem getImageByHash(String hash) {
        Imagem img = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DbConnector.getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM fotos WHERE hash = ?");
            stmt.setString(1, hash);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                img = new Imagem(rs.getString("description"), rs.getString("title"), rs.getString("path"), rs.getString("hash"), rs.getInt("idFotos"));
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
    /*
    * Fun��o pra enviar a imagem usando o titulo desta para seleciona-la no Banco de Dados
    * @param title - Titulo ou parte dele da Imagem a ser encontrada e enviada
    * @return - Retorna uma ArrayList de Objeto do tipo Imagem da imagem desejada, caso haja resultados parciais, vai adicionando todos os resultados a ArrayList
    */
    public ArrayList<Imagem> getImageByTitle(String title) {
        Imagem img = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Imagem> images = new ArrayList<>();
        
        try {
            conn = DbConnector.getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM fotos WHERE title LIKE ?");
            stmt.setString(1, "%"+title+"%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                img = new Imagem(rs.getString("description"), rs.getString("title"), rs.getString("path"), rs.getString("hash"), rs.getInt("idFotos"));
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
    /*
    * Fun��o pra enviar a imagem usando a Hash em MD5 desta para seleciona-la no Banco de Dados
    * @param hash - Hash da Imagem a ser encontrada e enviada
    * @return - Retorna um Objeto do tipo Imagem da imagem desejada
    */
    public Imagem getImageById(int id, int idAlbum) {
        Imagem img = null;
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
                img = new Imagem(rs.getString("description"), rs.getString("title"), rs.getString("path"), rs.getString("hash"), rs.getInt("idFotos"));
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

    public Imagem getImageByHash(String hash, int idAlbum) {
        Imagem img = null;
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
                img = new Imagem(rs.getString("description"), rs.getString("title"), rs.getString("path"), rs.getString("hash"), rs.getInt("idFotos"));
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

    public ArrayList<Imagem> getImageByTitle(String title, int idAlbum) {
        Imagem img = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Imagem> images = new ArrayList<>();
        
        try {
            conn = DbConnector.getConnection();
            
            stmt = conn.prepareStatement("SELECT f.* FROM fotos f JOIN album_fotos af ON f.id_foto = af.idFotos JOIN album a ON af.id_album = a.idAlbum WHERE f.title LIKE ? AND a.idAlbum = ?");
            stmt.setString(1, "%"+title+"%");
            stmt.setInt(2, idAlbum);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                img = new Imagem(rs.getString("description"), rs.getString("title"), rs.getString("path"), rs.getString("hash"), rs.getInt("idFotos"));
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

    public ArrayList<Imagem> getImageByDescription(String description) {
        Imagem img = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Imagem> images = new ArrayList<>();
        
        try {
            conn = DbConnector.getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM fotos WHERE description LIKE ?");
            stmt.setString(1, "%"+description+"%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                img = new Imagem(rs.getString("description"), rs.getString("title"), rs.getString("path"), rs.getString("hash"), rs.getInt("idFotos"));
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

    public ArrayList<Imagem> getImageByDescription(String description, int idAlbum) {
        Imagem img = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Imagem> images = new ArrayList<>();
        
        try {
            conn = DbConnector.getConnection();
            
            stmt = conn.prepareStatement("SELECT f.* FROM fotos f JOIN album_fotos af ON f.id_foto = af.idFotos JOIN album a ON af.id_album = a.idAlbum WHERE f.title LIKE ? AND a.idAlbum = ?");
            stmt.setString(1, "%"+description+"%");
            stmt.setInt(2, idAlbum);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                img = new Imagem(rs.getString("description"), rs.getString("title"), rs.getString("path"), rs.getString("hash"), rs.getInt("idFotos"));
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

    public ArrayList<Imagem> getImageByAlbumId(int idAlbum) {
        Imagem img = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Imagem> images = new ArrayList<>();
        
        try {
            conn = DbConnector.getConnection();
            
            stmt = conn.prepareStatement("SELECT f.* FROM fotos f JOIN album_fotos af ON f.id_foto = af.idFotos JOIN album a ON af.id_album = a.idAlbum WHERE a.idAlbum = ?");
            stmt.setString(1, "%"+idAlbum+"%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                img = new Imagem(rs.getString("description"), rs.getString("title"), rs.getString("path"), rs.getString("hash"), rs.getInt("idFotos"));
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

    public ArrayList<Imagem> getAllImages(){
        Imagem img = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Imagem> images = new ArrayList<Imagem>();
        
        try {
            conn = DbConnector.getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM album");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                img = new Imagem(rs.getString("description"), rs.getString("title"), rs.getString("path"), rs.getString("hash"), rs.getInt("idFotos"));
                //img = new Imagem(rs.getString("description"), rs.getString("title"), rs.getInt("idAlbum"));
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
    
    public ArrayList<Album> getAllAlbuns(){
        Album alb = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Album> albumns = new ArrayList<>();
        
        try {
            conn = DbConnector.getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM album");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                alb = new Album(rs.getString("description"), rs.getString("title"), rs.getInt("idAlbum"));
                albumns.add(alb);
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
        
        return albumns;
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
        ArrayList<Album> albumns = new ArrayList<Album>();
        
        try {
            conn = DbConnector.getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM album WHERE title LIKE ?");
            stmt.setString(1, "%"+title+"%");
            rs = stmt.executeQuery();
            while(rs.next()){
                alb = new Album(rs.getString("description"), rs.getString("title"), rs.getInt("idAlbum"));
                albumns.add(alb);
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
                albumns.add(alb);
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

    public boolean setImage(Imagem img) {
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
        PreparedStatement stmt = null, stmt2 = null;
        ResultSet rs = null;
        
        try {
            conn = DbConnector.getConnection();
            int ordem = 0;
            stmt2 = conn.prepareStatement("SELECT COUNT(*) AS qtd_fotos FROM album_fotos WHERE id_album = ?");
            stmt2.setInt(1, idAlbum);
            rs = stmt2.executeQuery();
            
            while(rs.next()){
                ordem = rs.getInt("qtd_fotos");
                ordem = ordem + 1;
            }
            
            stmt = conn.prepareStatement("INSERT INTO album_fotos(id_album, id_foto, ordem) VALUES (?,?,?)");
            stmt.setInt(1, idAlbum);
            stmt.setInt(2, idImage);
            stmt.setInt(3, ordem);
            stmt.executeUpdate();
            result = true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbManipulate.class.getName()).log(Level.SEVERE, null, ex);
            return result;
        }finally{
            try {
                DbConnector.closeConnection(conn, stmt);
                DbConnector.closeConnection(conn, stmt2);
            } catch (SQLException ex) {
                Logger.getLogger(DbManipulate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }

    public boolean updateAlbum(Album alb) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stmt = null;
                
        try {
            conn = DbConnector.getConnection();
            stmt = conn.prepareStatement("UPDATE album SET title = ?, description = ? WHERE idAlbum = ?");
            stmt.setString(1, alb.getTitle());
            stmt.setString(2, alb.getDescription());
            stmt.setInt(3, alb.getId());
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

    public boolean updateImage(Imagem img) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stmt = null;
                
        try {
            conn = DbConnector.getConnection();
            stmt = conn.prepareStatement("UPDATE fotos SET title = ?, description = ?, path = ?, hash = ? WHERE idFotos = ?");
            stmt.setString(1, img.getTitle());
            stmt.setString(2, img.getDescription());
            stmt.setString(3, img.getPath());
            stmt.setString(4, img.getHash());
            stmt.setInt(5, img.getId());
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

    public boolean deleteImage(int idImage) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DbConnector.getConnection();
            stmt = conn.prepareStatement("DELETE FROM fotos WHERE idFotos = ?");
            stmt.setInt(1, idImage);
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

    public boolean deleteAlbum(int idAlbum) {
        throw new UnsupportedOperationException("Metodo indisponivel.");
    }
    
    public boolean deleteImageFromAlbum(Album alb) {
        throw new UnsupportedOperationException("Metodo indisponivel.");
    }
    
    public int getLastId(String table) {
        int id = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DbConnector.getConnection();
            
            stmt = conn.prepareStatement("SELECT MAX(id"+StringUtils.capitalize(table)+") AS id FROM "+table);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                id = rs.getInt("id");
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
        
        return id;
    }
}
