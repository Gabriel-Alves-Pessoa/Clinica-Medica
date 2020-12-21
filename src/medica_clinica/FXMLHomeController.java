package medica_clinica;
//correto
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Rivaldo
 * @author Alves
 * @author Bianca
 */


public class FXMLHomeController implements Initializable {
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        FXMLHomeController.stage = stage;
    }
   
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    } 
    @FXML
    void abrir_tela_user(ActionEvent event) throws Exception {
       Cad_usuarioController cad_user =new Cad_usuarioController();
       Stage stage = new Stage();
       cad_user.start(stage);
    }
    
    @FXML
    void abrir_tela_convenio(ActionEvent event) throws Exception {
        Cad_convenioController cad_con =new Cad_convenioController();
        Stage stage = new Stage();
        cad_con.start(stage);
    }

    @FXML
    void abrir_tela_medico(ActionEvent event) throws Exception {
        Cad_medicoController cad_medico =new Cad_medicoController();
        Stage stage = new Stage();
        cad_medico.start(stage);
    }

    @FXML
    void abrir_tela_paciente(ActionEvent event) throws Exception {
        Cad_pacienteController cad_paciente =new Cad_pacienteController();
        Stage stage = new Stage();
        cad_paciente.start(stage);
    }
    @FXML
    void abrir_tela_consulta(ActionEvent event) throws Exception {
        Cad_consultaController cad_consulta =new Cad_consultaController();
        Stage stage = new Stage();
        cad_consulta.start(stage);
    }
    
    @FXML
    void abrir_tela_comp(ActionEvent event) throws Exception {
        Cad_compromissoController cad_comp =new Cad_compromissoController();
        Stage stage = new Stage();
        cad_comp.start(stage);
    }
    
    @FXML
    void abrir_tela_agenda(ActionEvent event) throws Exception {
        FXMLAgenda_medController agenda =new FXMLAgenda_medController();
        Stage stage = new Stage();
        agenda.start(stage);
    }
    
    @FXML
    void sair(ActionEvent event) {
        stage.close();
    }

    
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/telas/FXMLHome.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("CRUD HOME");
        stage.setScene(scene);
        stage.show();
        
        setStage(stage);
    }
    
}

