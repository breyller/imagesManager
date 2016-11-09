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


public class MainController implements Initializable {

    public static final String directory = "src/Img/";
    public int count = 0;
    
    /*Popular combo albuns*/    
    //@FXML
    //public ComboBox<String> albuns;
    
    DbManipulate bancoDados = new DbManipulate();
    ArrayList<Album> arAlbuns = bancoDados.getAllAbuns();
    ArrayList<String> nomesAlbuns = null;
            
    //for(Album alb : arAlbuns) {
    //    nomesAlbuns.add(alb.getTitle());
    //}
    //ObservableList<String> comboData = FXCollections.observableArrayList(nomesAlbuns);
    
    /*Popular lista*/
    @FXML
    public ComboBox<String> combo;    
    
   
    public void initialize(URL url, ResourceBundle resource){
        ObservableList<String> comboData = FXCollections.observableArrayList("Album1", "Album2", "Album3");
        combo.getItems().clear(); 
        combo.setItems(comboData);
    }
  
            
//    public void populateComboList(ActionEvent listComboData){
//        combo.getItems().clear(); 
//        combo.setItems(comboData);

        
 
       // combo.setItems(comboData);
       //combo.getItems().addAll(
       //     "Option 4",
       //     "Option 5",
       //     "Option 6"
       // );
        //combo.getItens().addAll("Album1", "Album2", "Album3");
    //}
    
    @FXML
    public Button btnteste;
    
    //public void doAction(){
   // for(ActionListener listComboData: btnteste.getActionListeners()) {
   // listComboData.actionPerformed(new java.awt.event.ActionEvent(this, java.awt.event.ActionEvent.ACTION_PERFORMED, null) {
          //Nothing need go here, the actionPerformed method (with the
          //above arguments) will trigger the respective listener
   // });
   // };
   // }
    
    /*Popular lista*/
    @FXML
    public ListView<String> list;
    ObservableList<String> data = FXCollections.observableArrayList("Single", "Double", "Suite", "FamilyApp", "FamilyApp", "FamilyApp", "FamilyApp", "FamilyApp", "FamilyApp");

    public void populateList(ActionEvent listData){
        list.setItems(data);
    }
    
    /*Exemplo*/
    @FXML
    private Label myMessage;
    
    public void generateRandom(ActionEvent event){
        Random rand = new Random();
        int myrand = rand.nextInt(50);
        myMessage.setText(Integer.toString(myrand));
    }
   
    /*Botao Sair*/
    public void exit(ActionEvent exit){
        System.exit(0);
    }
    
    
    /*Diretorio de imagens*/

    @FXML
    public ImageView teste;
    
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
    }
     
    @FXML
    public Button btn1;
    @FXML
    public ListView getItens;
    
    public void btn1Action(ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("src/Img/"));
                
        List<File> selectedFiles = fc.showOpenMultipleDialog(null);
        
        if(selectedFiles != null){
            for (int i = 0; i < selectedFiles.size();i++){
            getItens.getItems().add(selectedFiles.get(i).getAbsolutePath());
            }
        } else {
            System.out.println("File is not valid");
        }
    } 
}