/*
* Classe para as descrições 
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
    * @param String desc - Descrição do Objeto
    */
    public Description(String desc){
        this.description = desc;
    }
    /*
    * Função para enviar a descrição
    */
    public String getDescription(){
        return this.description;
    }
    /*
    * Função para definir a descrição
    */
    public boolean setDescription(String desc){
        boolean result = false;
        
        this.description = desc;
        
        if(this.description != "")
            result = true;
                            
        return result;
    }
}
