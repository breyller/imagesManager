public class Description {
    
    private String description;
    
    public Description(){
        this.setDescription(null);
    }
    
    public Description(String desc){
        this.setDescription(desc);
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public boolean setDescription(String desc){
        boolean result = false;
        
        this.description = desc;
        
        if(this.description != "")
            result = true;
                
        return result;
    }
    
}
