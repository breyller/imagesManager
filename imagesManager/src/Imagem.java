import java.util.ArrayList;
/*
* Classe para definição e operações usando Objeto Imagem
* @author Bruno Lopes
* @author Bruno Reyller
* @author Henrique
*/
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
    /*
    * Contrutor para Imagem
    * @param String desc - Descrição de Imagem
    * @param String ttle - Titulo de Imagem
    * @param String path - Caminho de Imagem
    * @param String Hash - Hash de Imagem
    * @param int id - Identificador de Imagem
    */
    public Imagem(String desc, String title, String path, String hash, int id){
        this.desc = new Description(desc);
        this.title = new Title(title);
        this.setPath(path);
        this.setHash(hash);
        this.setId(id);
    }
    /*
    Define Path
    @param String path - Caminho a ser definido
    @return - Confirmação booleana
    */
    public boolean setPath(String path){
        boolean result = false;
        
        this.path = path;
        if(this.path != "")
            result = true;
        
        return result;
    }
    /*
    Define Hash
    @param String hash - Hash a ser definido
    @return - Confirmação booleana
    */
    public boolean setHash(String hash){
        boolean result = false;
        
        this.hash = hash;
        if(this.hash != "")
            result = true;
        
        return result;
    }
        /*
    Define identificador
    @param int it - Identificador a ser definido
    @return - Confirmação booleana
    */
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
    /*
    Define Descrição
    @param String desc - Descrição a ser definida
    @return - Confirmação booleana
    */
    public boolean setDescription(String desc){
        boolean result = false;
        
        this.desc = new Description(desc);
        if(this.getDescription() != null)
            result = true;
        
        return result;
    }
    /*
    * Envia descrição
    * @return - Descrição de Imagem
    */
    public String getDescription(){
        return this.desc.getDescription();
    }
    /*
    * Envia Title
    * @return - Titulo de Imagem
    */
    public String getTitle(){
        return this.title.getTitle();
    }
     /*
    * Envia Hash
    * @return - Hash de Imagem
    */   
    public String getHash(){
        return this.hash;
    }
    /*
    * Envia path
    * @return - Path de Imagem
    */    
    public String getPath(){
        return this.path;
    }
    /*
    * Envia identificador
    * @return - Identificador de Imagem
    */    
    public int getId(){
        return this.id;
    }

    void add(ArrayList<Imagem> images) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
