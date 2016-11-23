import java.util.ArrayList;
/*
* @author Bruno Lopes
* @author Bruno Reyller
* @author Henrique
* Classe Album usada para criar instancias do objeto album, enviar suas informacoes e receber suas mudancas.
* Um album somente e uma colecao de imagens, nao sendo um tipo de arquivo.
*/
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
    /*
    Construtor da classe com imagens dentro
    @param desc - Descriçao do album
    @param title - Titulo do Album
    @param id - Chave primaria dentro do banco de dados
    @param images - Array list de todas as imagens de objeto tipo Imagem que fazem parte deste album
    */
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
    /*
    Construtor da classe sobrecarregado sem um array de imagens
    @param desc - Descriçao do album
    @param title - Titulo do Album
    @param id - Chave primaria dentro do banco de dados
    */  
    Album(String desc, String title, int id){
        this.setDescription(desc);
        this.setTitle(title);
        if(!this.setId(id)){
            System.out.println("Erro ao setar id.");
        }
    }
    /*
    Função para definir as imagens selecionadas dentro do album
    @param images - Array List com as imagens que se deseja colocar dentro do album
    @return - confirmacao se ocorreu ou nao com sucesso
    */  
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
    /*
    Funcao para definir o identificador (chave primaria) do album
    @param id - numero inteiro que sera o id do album
    @return - confirmacao se ocorreu ou nao com sucesso
    */  
    private boolean setId(int id){
        boolean result = false;
        
        this.id = id;
        if(this.id != 0)
            result = true;
        
        return result;
    }
    /*
    Funcao para definir a descricao do album
    @param desc - String que contem a descricao desejada do album
    */  
    public void setDescription(String desc){
        this.desc = new Description(desc);
    }
    /*
    Funcao para definir o titulo do album
    @param title - Titulo desejado para o album
    */
    public void setTitle(String title){
        this.title = new Title(title);
    }
    /*
    Funcao para enviar a descricao do album
    @return Retorna uma string com a descricao do album
    */
    public String getDescription(){
        return this.desc.getDescription();
    }
    /*
    Funcao para enviar a titulo do album
    @return Retorna uma string com o titulo do album
    */
    public String getTitle(){
        return this.title.getTitle();
    }
    /*
    Funcao para enviar o identificador do album
    @return Retorna um inteiro com o identificador do album
    */
    public int getId(){
        return this.id;
    }
    /*
    Funcao para enviar a array list com todas as imagens do album
    @return Retorna uma array list com todas as imagens do album
    */
    public ArrayList<Imagem> getAllImages(){
        return this.albumImages;
    }
}
