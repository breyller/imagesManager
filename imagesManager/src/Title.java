public class Title {
    
    private String title;
    
    public Title(){
        this.setTitle(null);
    }
    
    public Title(String title){
        this.setTitle(title);
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public boolean setTitle(String title){
        boolean result = false;
        
        this.title = title;
        if(this.title != "")
            result = true;
        
        return result;        
    }
}
