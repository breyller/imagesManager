import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
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


public class MainController implements Initializable {

    public static final String directory = "src/Img/";
    public int count = 0;
    
    /*Popular combo albuns*/    
    //@FXML
    //public ComboBox<String> albuns;
    
    DbManipulate bancoDados = new DbManipulate();
    ArrayList<Album> arAlbuns = bancoDados.getAllAlbuns();
    ArrayList<String> nomesAlbuns = null;
            
    //for(Album alb : arAlbuns) {
    //    nomesAlbuns.add(alb.getTitle());
    //}
    //ObservableList<String> comboData = FXCollections.observableArrayList(nomesAlbuns);
    
    /*Popular lista*/

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
    public DbManipulate db = new DbManipulate();
    
    public void newAlbum(ActionEvent setNewAlbum){
        String TituloAlbum;
        String DescAlbum;
        TituloAlbum = getAlbumName.getText();
        DescAlbum = getAlbumDesc.getText();
        boolean retorno = false;
        
        
        if (getAlbumName.getText() == null || getAlbumName.getText().trim().isEmpty() || getAlbumDesc.getText() == null || getAlbumDesc.getText().trim().isEmpty()) {
            System.out.println("textField is empty");
        }else{
            System.out.println("textField is not empty");
        } 
        
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
            retorno = db.setAlbum(alb);
            
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
            

            //newAlbum(null);
            }
        }
    }
    
    public void popCombo2(ActionEvent listComboData){
        DbManipulate bancoDados = new DbManipulate();
        ArrayList<String> nomesImagens = new ArrayList<String>();
        ArrayList<Imagem> arImagens = bancoDados.getAllImages();
        
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
    
                 
    public void populateComboList(ActionEvent listComboData){
        DbManipulate bancoDados = new DbManipulate();
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
    
    @FXML
    public Button btnteste;
    /*Popular lista*/
    @FXML
    public ComboBox<String> combo;
    @FXML
    public ListView<String> list;
    
    ObservableList<String> data = FXCollections.observableArrayList();
    
    public void populateList(ActionEvent listData){
        
        data.add(combo.getValue());
        list.setItems(data);
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
        
        image = new Image("file:///" + path.getAbsolutePath() + "/" + count + ".jpg");
        teste.setImage(image);
        count++;
            
        if(count == 5){
            count = 0;
        }
        
        selectTitle = new Imagem("teste", "teste123", "file:///C:\\Users\\b140528.FT.001\\Documents\\NetBeansProjects\\imagesManager\\imagesManager\\src\\Img\\2.jpg", "12312312312sadd", 1);
                
        String Titulo = selectTitle.getTitle();
        text.setText(Titulo);
    }
     
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
        lImageName.setText(selectedFile.getName());
        
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
    
    public void insertImage(ActionEvent event){
    
        
        if (lImageName.getText() == null || lImageName.getText().trim().isEmpty()){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("Selecione uma imagem para ser inserida!");
            alert.showAndWait();
        } else if (txtImageName.getText() == null || txtImageName.getText().trim().isEmpty() || txtImageDesc.getText() == null || txtImageDesc.getText().trim().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("Insira nome e/ou a descrição da Imagem!");
            alert.showAndWait();
        } else if (combo3.getValue() == null){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("Escolha o album!");
            alert.showAndWait();
        } else {
            System.out.println(combo3.getValue());
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("Imagem Inserida com sucesso");
            alert.showAndWait();
            lImageName.setText(null);
            txtImageName.setText("");
            txtImageDesc.setText("");
            combo3.setValue(null);
        }
    }
}