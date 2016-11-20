
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Imagem {
    private Description desc;
    private Title title;
    private String path;
    private String hash;
    private int id;
    
    public Imagem(){
        this.desc = new Description();
        this.title = new Title();
        this.setPath(null);
        this.setHash(null);
    }
    
    public Imagem(String desc, String title, String path, String hash, int id){
        this.desc = new Description(desc);
        this.title = new Title(title);
        this.setPath(path);
        this.setHash(hash);
        this.setId(id);
    }
    
    public boolean setPath(String path){
        boolean result = false;
        
        this.path = path;
        if(this.path != "")
            result = true;
        
        return result;
    }
    
    public boolean setHash(String hash){
        boolean result = false;
        
        this.hash = hash;
        if(this.hash != "")
            result = true;
        
        return result;
    }
    
    public boolean setId(int id){
        boolean result = false;
        
        this.id = id;
        if(this.id != 0)
            result = true;
        
        return result;
    }
    
    public boolean setTitle(String title){
        boolean result = false;
        
        this.title = new Title(title);
        if(this.getTitle() != null)
            result = true;
        
        return result;
    }
    
    public boolean setDescription(String desc){
        boolean result = false;
        
        this.desc = new Description(desc);
        if(this.getDescription() != null)
            result = true;
        
        return result;
    }
    
    public String getDescription(){
        return this.desc.getDescription();
    }
    
    public String getTitle(){
        return this.title.getTitle();
    }
    
    public String getHash(){
        return this.hash;
    }
    
    public String getPath(){
        return this.path;
    }
    
    public int getId(){
        return this.id;
    }

    void add(ArrayList<Imagem> images) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
