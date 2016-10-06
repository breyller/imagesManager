public interface IPersistencia {
    Image getImageById(int id);
    Image getImageByHash(String hash);
    Image getImageByName(String name);
    
}
