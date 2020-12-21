package medica_clinica;

import dao.LoginDao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Usuario;

/**
 *
 * @author Rivaldo
 * @author Alves
 */

public class FXMLDocumentController implements Initializable {
    private static Stage stage;
    @FXML
    private TextField tf_login;

    @FXML
    private PasswordField tf_password;
    
    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        FXMLDocumentController.stage = stage;
    }
    
    @FXML
    private Label label;
    
    @FXML
    void logar(ActionEvent event) throws Exception {
        LoginDao ld=new LoginDao();
        Usuario us=new Usuario();
        us.setLogin(tf_login.getText());
        us.setPassword(tf_password.getText());
        if(ld.verificar_login(us)==true){
                FXMLHomeController hc=new FXMLHomeController();
                Stage stage=new Stage();
                hc.start(stage);
                FXMLDocumentController.getStage().close();
        }else{
          Alert al=new Alert(Alert.AlertType.WARNING);
          al.setContentText("Login ou Password Incorretos!");
          al.show();
        }
    }

    @FXML
    void sair(ActionEvent event) {
     stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Mascara.maskStringOrNumberNoSpace(tf_login);
        Mascara.maskStringOrNumberNoSpace(tf_password);
    }    
    public void start(Stage stage) throws Exception {
       //carrega o arquivo - tela 
        Parent root = FXMLLoader.load(getClass().getResource("/view/telas/FXMLDocument.fxml"));
        //intancio a cena p/ que essa tela possa aparecer
        Scene scene = new Scene(root);
        setStage(stage);
        //seto a cena no stage
        stage.setTitle("Clinica Medica");
        stage.setScene(scene);
        //mostrar a janela
        stage.show();
    }
}
