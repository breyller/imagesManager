/*
* Classe para as descri��es 
* @author Bruno Lopes
* @author Bruno Reyller
* @author Henrique
*/

public class Description {
    
    private String description;
    
    public Description(){
        this.description = null;
    }
    /*
    * Construtor de Description
    * @param String desc - Descri��o do Objeto
    */
    public Description(String desc){
        this.description = desc;
    }
    /*
    * Fun��o para enviar a descri��o
    */
    public String getDescription(){
        return this.description;
    }
    /*
    * Fun��o para definir a descri��o
    */
    public boolean setDescription(String desc){
        boolean result = false;
        
        this.description = desc;
        
        if(this.description != "")
            result = true;
                            
        return result;
    }
}
