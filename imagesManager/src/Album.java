import java.util.ArrayList;

public class Album {
    Description desc;
    Title title;
    int id;
    ArrayList<Imagem> albumImages;
    
    Album(){
        this.desc = new Description();
        this.title = new Title();
        this.id = 0;
        //Imagem img = new Imagem();
        //this.albumImages.add(img);
    }
    
    Album(String desc, String title, int id, ArrayList<Imagem> images){
        this.desc = new Description(desc);
        this.title = new Title(title);
        if(!this.setId(id)){
            System.out.println("Erro ao setar id.");
        }
        if(!this.setImages(images)){
            System.out.println("Erro ao setar imagens.");
        }
    }
      
    Album(String desc, String title, int id){
        this.setDescription(desc);
        this.setTitle(title);
        if(!this.setId(id)){
            System.out.println("Erro ao setar id.");
        }
    }
      
    private boolean setImages(ArrayList<Imagem> images){
        boolean result = true;
        
        try{
            for(Imagem img : images){
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

    public void setDescription(String desc){
        this.desc = new Description(desc);
    }
    
    public void setTitle(String title){
        this.title = new Title(title);
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
    
    public ArrayList<Imagem> getAllImages(){
        return this.albumImages;
    }
}
