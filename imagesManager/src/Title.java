/*
* Fun��o para Cria��o e Manipula��o de Titulos
*/
public class Title {
    
    private String title;
    
    public Title(){
        this.setTitle(null);
    }
    /* 
    * Contrutor de Title
    * @param String Title - Titulo a ser definido inicialmente
    */
    public Title(String title){
        this.setTitle(title);
    }
    /* 
    * Fun��o para enviar o Titulo
    * @return - Retorna uma String do titulo
    */
    public String getTitle(){
        return this.title;
    }
    /* 
    * Fun��o para definir o Titulo
    * @param String title - Titulo a ser definido
    * @return - Confirma��o boolana
    */    
    public boolean setTitle(String title){
        boolean result = false;
        
        this.title = title;
        if(this.title != "")
            result = true;
        
        return result;        
    }
}
