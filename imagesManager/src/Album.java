import java.util.ArrayList;

public class Album {
    Description desc;
    Title title;
    int id;
    ArrayList<Image> albumImages;
    
    Album(){
        this.desc = new Description();
        this.title = new Title();
        this.id = 0;
        Image img = new Image();
        this.albumImages.add(img);
    }
    
    Album(String desc, String title, int id, ArrayList<Image> images){
        this.desc = new Description(desc);
        this.title = new Title(title);
        if(!this.setId(id)){
            System.out.println("Erro ao setar id.");
        }
        if(!this.setImages(images)){
            System.out.println("Erro ao setar imagens.");
        }
    }
      
    private boolean setImages(ArrayList<Image> images){
        boolean result = true;
        
        try{
            for(Image img : images){
                this.albumImages.add(img);
            }
        } catch (Exception e){
            result = false;
        }
        
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
    
    public int getId(){
        return this.id;
    }
    
    public ArrayList<Image> getAllImages(){
        return this.albumImages;
    }
}