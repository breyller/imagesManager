import java.io.File;

import java.util.List;
import java.util.Random;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.stage.FileChooser;

public class MainController{

    public static final String directory = "src/Img/";
    public int count = 0;

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
            
        if(count == 4){
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