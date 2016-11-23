
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.TextField;
import static jdk.nashorn.tools.ShellFunctions.input;


    /**
    * Classe controladora principal; controla as ações pela interface gráfica usando métodos de outras classes.
    * 
    * @author Bruno Lopes
    * @author Bruno Reyller
    * @author Henrique
    */

public class MainController implements Initializable {

    public static final String directory = "src/Img/";
    public int count = 0; 
    
    DbManipulate bancoDados = new DbManipulate();
    ManipuladorArquivos mani = new ManipuladorArquivos();
    ArrayList<Album> arAlbuns = bancoDados.getAllAlbuns();
    ArrayList<String> nomesAlbuns = null;
    
     /**
    * Inicializa listas a serem exibidas
    * 
    * @param URL url   
    * @param  ResourceBundle resource
    * @author  Bruno Lopes
    */
    
    @FXML
    public ComboBox<String> combo1; 
    @FXML
    public ComboBox<String> combo2;
    @FXML
    public ComboBox<String> combo3;
    @FXML
    public ComboBox<String> combo4;
    @FXML
    public ComboBox<String> combo5;
    
    public void initialize(URL url, ResourceBundle resource){
        populateComboList(null);
    }
    
    //btnNewAlbum - onAction
    @FXML
    public TextField getAlbumName;
    @FXML
    public TextField getAlbumDesc;
    
    public void newAlbum(ActionEvent setNewAlbum){
        String TituloAlbum;
        String DescAlbum;
        TituloAlbum = getAlbumName.getText();
        DescAlbum = getAlbumDesc.getText();
        boolean retorno = false;
        
        
//        if (getAlbumName.getText() == null || getAlbumName.getText().trim().isEmpty() || getAlbumDesc.getText() == null || getAlbumDesc.getText().trim().isEmpty()) {
//            System.out.println("textField is empty");
//        }else{
//            System.out.println("textField is not empty");
//        } 
        
        if (getAlbumName.getText() == null || getAlbumName.getText().trim().isEmpty() || getAlbumDesc.getText() == null || getAlbumDesc.getText().trim().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("Preencha os campos em brancos (nome e descrição) para inserir os albuns!");
            alert.showAndWait();
        } else {
            Album alb = new Album();
            alb.setTitle(TituloAlbum);
            alb.setDescription(DescAlbum);
            retorno = bancoDados.setAlbum(alb);
            
            if(retorno){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("Album inserido com sucesso!");
            alert.showAndWait();
            getAlbumName.setText("");
            getAlbumDesc.setText("");
            populateComboList(null);

            }
            else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("Falha ao inserir album!");
            alert.showAndWait();
            }
        }
    }
      
    public void populateComboList(ActionEvent listComboData){
        ArrayList<String> nomesAlbuns = new ArrayList<String>();
        ArrayList<Album> arAlbuns = bancoDados.getAllAlbuns();
        
        for(int i = 0; i < arAlbuns.size(); i++){
            nomesAlbuns.add(arAlbuns.get(i).getTitle());
        }

        ObservableList<String> comboData = FXCollections.observableArrayList(nomesAlbuns);
        combo.getItems().clear(); 
        combo.setItems(comboData);

        combo1.getItems().clear(); 
        combo1.setItems(comboData);
        
        combo3.getItems().clear(); 
        combo3.setItems(comboData);
        
        combo4.getItems().clear(); 
        combo4.setItems(comboData);     
 
        combo5.getItems().clear(); 
        combo5.setItems(comboData);        
        
    }
    
        public void popCombo2(ActionEvent listComboData){
        ArrayList<String> nomesImagens = new ArrayList<String>();
        ArrayList<Imagem> arImagens = bancoDados.getAllImages(); //se eu listo todas as imagens, porque eu escolho o album?
        
        for(int i = 0; i < arImagens.size(); i++){
            nomesImagens.add(arImagens.get(i).getTitle());
        }

        ObservableList<String> comboData = FXCollections.observableArrayList(nomesImagens);
            String out = combo1.getValue();
            if (out != null){
                combo2.setDisable(false);
                combo2.getItems().clear(); 
                combo2.setItems(comboData);
            }
    }
        
    @FXML
    public TextField txtImagePath;
    
    /**
     * Funcao de exportar a imagem do BD para o repositorio local escolhido pelo usuário
     * @param Action - apertar do botao exportar imagem
     */
    public void exportImage(ActionEvent listData) throws IOException
    {
        Imagem imgOrigem = null; // Inicializa uma instancia de Imagem
        for(int i = 0; i < bancoDados.getAllImages().size(); i++) // Percorre todas as imagens para usar a imagem selecionada
        {
            if (combo2.getValue().equals(bancoDados.getAllImages().get(i).getTitle())); // para na com titulo igual
            {
                imgOrigem = bancoDados.getAllImages().get(i); // Recebe o objeto Imagem da imagem selecionada pelo if
            }
        }    
        String endOrigem = imgOrigem.getPath(); //Null pointer não sei porque...
        String destino = txtImagePath.getText(); // Pega o local destino da imagem por caminho absoluto
        File origem = new File(endOrigem); //receives the file destination from the argument lines
        
        mani.writeImage(origem, destino); // Salva a imagem no local desejado

        
        
        if (combo1.getValue() == null){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("Escolha o album!");
            alert.showAndWait();
        } else if (combo2.getValue() == null){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("Escolha a imagem!");
            alert.showAndWait();
        } else if (txtImagePath.getText() == null || txtImagePath.getText().trim().isEmpty()){    
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("Escolha o diretório!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("Imagem exportada com sucesso!");
            alert.showAndWait();
            combo1.setValue(null);
            combo2.setValue(null);
        }
    }
    
    /*Popular lista*/    
    @FXML
    public Button btnteste;
    @FXML
    public ComboBox<String> combo;
    @FXML
    public ListView<String> list;
    
    ObservableList<String> data = FXCollections.observableArrayList();
    
    public void populateList(ActionEvent listData){
        
        data.add(combo.getValue());
        list.setItems(data);
    }
    
    public void deleteItemList(ActionEvent listData){
        list.getItems().remove(list.getSelectionModel().getSelectedItem());
    }
    
    /*Botao Sair*/
    public void exit(ActionEvent exit){
        System.exit(0);
    }
    
    /*Diretorio de imagens*/
    @FXML
    public ImageView teste;
    @FXML
    public Label text;
    public Imagem selectTitle;
    
    public void array(ActionEvent array){
        File path = null;
        Image image = null;
        path = new File("src/Img");
        
        //image = new Image("file:///" + path.getAbsolutePath() + "/" + count + ".jpg");
        //teste.setImage(image);
        //count++;
            
        if(count == 5){
            count = 0;
        }

        //selectTitle = new Imagem("teste", "Dory", "file:///C:\\Users\\Bruno Fernandes\\Documents\\NetBeansProjects\\imagesManager\\imagesManager\\src\\Img\\1.jpg", "12312312312sadd", 1);
        
        //String dir = selectTitle.getPath();
        //image = new Image(dir);
        //teste.setImage(image);
        
        String Titulo = selectTitle.getTitle();
        text.setText(Titulo);
        
        
    }
     
    
    /*Inserir imagem*/
    @FXML
    public Button btninsertImage;
    @FXML
    public Label lImageName;
    @FXML
    public TextField txtImageName;
    @FXML
    public TextField txtImageDesc;
    
    public void selectImage(ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("src/Img/"));
        File selectedFile = fc.showOpenDialog(null);
        lImageName.setText(selectedFile.getAbsolutePath());
        
       /*Seleção multipla*/
//        List<File> selectedFiles = fc.showOpenMultipleDialog(null);
//
//        
//        if(selectedFiles != null){
//            for (int i = 0; i < selectedFiles.size();i++){
//            getItens.getItems().add(selectedFiles.get(i).getAbsolutePath());
//            }
//        } else {
//            System.out.println("File is not valid");
//        }
    } 
    
    
    /**
     * Insere Objeto Imagem no BD e uma File da imagem desejada no repositorio local
     * @param event - Evento de apertar o botão
     */
    public void insertImage(ActionEvent event) throws NoSuchAlgorithmException, IOException 
    {
        // Tratamento de falhas na interface
        if (lImageName.getText() == null || lImageName.getText().trim().isEmpty()){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("Selecione uma imagem para ser inserida!");
            alert.showAndWait();
            return;
        } else if (txtImageName.getText() == null || txtImageName.getText().trim().isEmpty() || txtImageDesc.getText() == null || txtImageDesc.getText().trim().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("Insira nome e/ou a descrição da Imagem!");
            alert.showAndWait();
            return;
        } else if (combo3.getValue() == null){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("Escolha o album!");
            alert.showAndWait();
            return;
        }
        boolean bdSet = false;        
        File img = new File(lImageName.getText());
        String imgDesc = txtImageDesc.getText();
        String imgTitle = txtImageName.getText();
        String imgPath = lImageName.getText();
        String imgHash = MD5.gerarMD5(img);
        int imgId = 0;
        Imagem imgInserida = new Imagem(imgDesc, imgTitle, imgPath, imgHash, imgId);
        boolean verifica = mani.setImage(imgInserida);
        if(verifica){
            boolean imgIsSet = false;
            bdSet = bancoDados.setImage(imgInserida);
                
            //poe a imagem no album selecionado
            if (combo3.getValue() != null){
                //verificar se está funfando getAlbumByTitle.
                int albId = bancoDados.getAlbumByTitle(combo3.getValue()).get(0).getId(); //Recebe o ID do album selecionado
                System.out.println("albId: " + albId);
                imgIsSet = bancoDados.setImageOnAlbum(imgInserida.getId(), albId); //Coloca a imagem no album selecionado
                System.out.println("imgIsSet: " + imgIsSet);
                if (imgIsSet == false){
                    System.out.println(combo3.getValue());
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Informação");
                    alert.setHeaderText(null);
                    alert.setContentText("Falha na insercao de imagem no album");
                    alert.showAndWait();
                    return;
                }
            }
        }
        else{
//            if(bdSet){
//            Alert alert = new Alert(AlertType.INFORMATION);
//            alert.setTitle("Informação");
//            alert.setHeaderText(null);
//            alert.setContentText("Imagem Inserida com sucesso");
//            alert.showAndWait();
//            lImageName.setText(null);
//            txtImageName.setText("");
//            txtImageDesc.setText("");
//        }
//        else{
//            Alert alert = new Alert(AlertType.ERROR);
//            alert.setTitle("Erro");
//            alert.setHeaderText(null);
//            alert.setContentText("Erro ao inserir imagem!");
//            alert.showAndWait();
//            lImageName.setText(null);
//            txtImageName.setText("");
//            txtImageDesc.setText("");           
                return;
        }
        
            
        
    }
    
    /*Exportar Album*/
    @FXML
    public TextField txtAlbumName;

    /*
    * Funcao de exportacao de album, isto e, todas as imagens inseridas dentro do album sao salvas em um repositorio na maquina do usuario
    * @param event - Botao sendo ativado pelo usuario
    */
   
    public void exportAlbum(ActionEvent event) throws IOException
    {
                
        // Receber a array list de objetos Imagem do Album; e o endereço onde serão copiadas para
        String end = txtAlbumName.getText(); //Endereço onde serão salvas as imagens
        ArrayList<Imagem> imagens = bancoDados.getAlbumByTitle(combo4.getValue()).get(0).getAllImages(); //Recebe as imagens do album selecionado em combo 4; 
        
        // Copia as imagens para o local desejado
        for (int i = 0; i < imagens.size(); i++)
        {
            File arquivo = new File(imagens.get(i).getPath()); //Recebe o caminho absoluto da imagem pelo banco e a instancia
            mani.writeImage(arquivo, end); // Escreve a imagem no diretorio desejado
        }     
        
        if (combo4.getValue() == null){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("Escolha o album!");
            alert.showAndWait();
        } else if (txtAlbumName.getText() == null || txtAlbumName.getText().trim().isEmpty()){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("Insira o destino do Album!");
            alert.showAndWait();
        } else {
            //System.out.println(combo3.getValue());
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("Album exportado com sucesso!");
            alert.showAndWait();
            combo4.setValue(null);
            txtAlbumName.setText("");
        }
    }
    
    /*Exportar Album*/
    @FXML
    
    
    public void exportHTML(ActionEvent event) throws IOException, InterruptedException, URISyntaxException{
        
        //File path = new File("src/HTML").toURI().toURL();
        
        
        //String sDirHTML = new String(path.getAbsolutePath() + "/imagesManagerSite.html");
        //URI dirHTML = new URI(sDirHTML);
        //dirHTML = URLEncoder.encode(dirHTML, "UTF-8"); 
        //URI u = path.toURI();
        //URI u = path.toURI();
        
        //URL url = new URL("http://www.google.com"); //Some instantiated URL object
        //URI uri = url.toURI();
        
        if (combo.getValue() == null){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("Escolha o album!");
            alert.showAndWait();
            

        } else {
            //System.out.println(combo3.getValue());
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("HTML com albun(s) exportado com sucesso!");
            alert.showAndWait();
        File path = new File("src/HTML");    
        String url = new String(path.getAbsolutePath()+ "/imagesManagerSite.html");
        File dir = new File (url);
        
        
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(dir.toURI());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        combo.setValue(null);
        //list.setItems(null);
        list.getItems().clear();   

        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + url);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }    
        //Process p = Runtime.getRuntime().exec("\"/Program Files (x86)/Google/Chrome/Application/chrome.exe\" C:\\Users\\blopes\\Desktop\\imagesManagerSite\\imagesManagerSite.html");
        //   System.out.println(dirHTML);
        //Process p = Runtime.getRuntime().exec("\"/Program Files (x86)/Google/Chrome/Application/chrome.exe\"" + dirHTML);
        //p.waitFor();
        }
    }
    
    
    /*PRÉ VISUALIZAÇÃO*/
    /*Array de imagens*/
    @FXML
    public Button btnArrayUP;
    @FXML
    public Label atualPos;
    @FXML
    public Label totalPos;
    
    int iAtualPosi = 0;
    int iTotalPos = 20;
    
    String atualPosi;
    String TotalPos;
    
    public void upArrayImage(ActionEvent event){
        TotalPos = Integer.toString(iTotalPos);
        totalPos.setText(TotalPos);
        
        if (iAtualPosi < iTotalPos){
            iAtualPosi++;        
            atualPosi = Integer.toString(iAtualPosi);
            atualPos.setText(atualPosi);
        }  else if (iAtualPosi > iTotalPos){
        }
        totalPos.setText(TotalPos);
    }
                
    public void downArrayImage(ActionEvent event){
        TotalPos = Integer.toString(iTotalPos);
        totalPos.setText(TotalPos);
        
        if (iAtualPosi <= iTotalPos && iAtualPosi > 0){
            iAtualPosi--;        
            atualPosi = Integer.toString(iAtualPosi);
            atualPos.setText(atualPosi);
        } else {
        }
        totalPos.setText(TotalPos);
    }
    
    public void savePosImage(ActionEvent event){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText("Posição salva");
        alert.showAndWait();
    }
    
    /*Pesquisa por nome ou descr*/
    @FXML
    public TextField txtPesq;
    public ArrayList<Imagem> Title;
    public Imagem img;
            
    public void pesqNome(ActionEvent event){
        if (txtPesq.getText() == null || txtPesq.getText().trim().isEmpty()){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("Insira o nome a ser pesquisado!");
            alert.showAndWait();
        } else {
            //receber o nome
            //enviar para o metodo getImageByTitle
            //receber imagem do metodo
            //exibir imagem no teste.
            String title = txtPesq.getText();
            
            //Imagem image<Imagem> = new Imagem();
            //Title = bancoDados.getImageByTitle(title);
            //img.getPath();
            //bancoDados.
            
            //teste.setImage(Title);
            ArrayList< Imagem > consultaImagens = bancoDados.getImageByTitle(title);
            File path = new File("src/Img");
            if(consultaImagens != null){
                Imagem imgSelecionada = consultaImagens.get(0);
                //Image imgJava = new Image(imgSelecionada.getPath());
                Image imgJava = new Image("file:///" + path.getAbsolutePath() + "/" +  imgSelecionada.getPath());
                System.out.println("file:///" + path.getAbsolutePath() + "/" + imgSelecionada.getPath());
                //teste.seti
                teste.setImage(imgJava);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informação");
                alert.setHeaderText(null);
                alert.setContentText("Imagem Encontrada!");
                alert.showAndWait();
            //
            }
            else{
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Informação");
                alert.setHeaderText(null);
                alert.setContentText("Imagem nao econtrada!");
                alert.showAndWait();
            }
           // Imagem image = new Image();
         //conexao com bd e exibir imagem no image view
        }
    }
    
    public void pesqDesc(ActionEvent event){
        if (txtPesq.getText() == null || txtPesq.getText().trim().isEmpty()){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("Insira o texto da descrição a ser pesquisado!");
            alert.showAndWait();
        } else {
         //conexao com bd e exibir imagem no image view
        }
    }
    
    
    
}
