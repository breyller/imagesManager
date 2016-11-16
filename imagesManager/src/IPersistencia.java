import java.util.ArrayList;

public interface IPersistencia {
    
    Imagem getImageById(int id);
    Imagem getImageById(int id, int idAlbum);
    Imagem getImageByHash(String hash);
    Imagem getImageByHash(String hash, int idAlbum);
    ArrayList<Imagem> getImageByTitle(String title);
    ArrayList<Imagem> getImageByTitle(String title, int idAlbum);
    ArrayList<Imagem> getImageByDescription(String description);
    ArrayList<Imagem> getImageByDescription(String description, int idAlbum);
    ArrayList<Imagem> getImageByAlbumId(int idAlbum);
    Album getAlbumById(int id);
    ArrayList<Album> getAlbumByTitle(String title);
    ArrayList<Album> getAlbumByDescription(String description);
    boolean setAlbum(Album alb);
    boolean setImage(Imagem img);
    boolean setImageOnAlbum(int idImage, int idAlbum);
    boolean updateAlbum(Album alb);
    boolean updateImage(Imagem img);
    boolean deleteImage(int idImage);
    boolean deleteAlbum(int idAlbum);
    boolean deleteImageFromAlbum(Album alb);
}
