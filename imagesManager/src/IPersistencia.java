import java.util.ArrayList;

public interface IPersistencia {
    
    Image getImageById(int id);
    Image getImageById(int id, int idAlbum);
    Image getImageByHash(String hash);
    Image getImageByHash(String hash, int idAlbum);
    ArrayList<Image> getImageByTitle(String title);
    ArrayList<Image> getImageByTitle(String title, int idAlbum);
    ArrayList<Image> getImageByDescription(String description);
    ArrayList<Image> getImageByDescription(String description, int idAlbum);
    ArrayList<Image> getImageByAlbumId(int idAlbum);
    Album getAlbumById(int id);
    ArrayList<Album> getAlbumByTitle(String title);
    ArrayList<Album> getAlbumByDescription(String description);
    boolean setAlbum(Album alb);
    boolean setImage(Image img);
    boolean setImageOnAlbum(Image img, int idAlbum);
    boolean updateAlbum(Album alb);
    boolean updateImage(Image img);
    boolean deleteImage(Image img);
    boolean deleteAlbum(Album alb);
    boolean setImageToAlbum(Image img, Album alb);
}
