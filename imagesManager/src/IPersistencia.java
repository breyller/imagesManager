import java.util.ArrayList;

public interface IPersistencia {
    Image getImageById(int id);
    Image getImageByHash(String hash);
    ArrayList<Image> getImageByTitle(String title);
    
}
