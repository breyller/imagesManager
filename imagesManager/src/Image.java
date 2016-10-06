public class Image {
    private Description desc;
    private Title title;
    private String path;
    private String hash;
    private int id;
    
    public Image(){
        this.desc = new Description();
        this.title = new Title();
        this.setPath(null);
        this.setHash(null);
    }
    
    public Image(String desc, String title, String path, String hash, int id){
        this.desc = new Description(desc);
        this.title = new Title(title);
        this.setPath(path);
        this.setHash(hash);
        this.setId(id);
    }
    
    private boolean setPath(String path){
        boolean result = false;
        
        this.path = path;
        if(this.path != "")
            result = true;
        
        return result;
    }
    
    private boolean setHash(String hash){
        boolean result = false;
        
        this.hash = hash;
        if(this.hash != "")
            result = true;
        
        return result;
    }
    
    private boolean setId(int id){
        boolean result = false;
        
        this.id = id;
        if(this.id != 0)
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
}