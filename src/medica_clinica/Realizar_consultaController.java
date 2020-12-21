package medica_clinica;

import dao.ConsultaDao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Consulta;

/**
 * FXML Controller class
 *
 * @author Alves
 */
public class Realizar_consultaController implements Initializable {
    ConsultaDao cs = new ConsultaDao();
    static Stage stage;
    
    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Realizar_consultaController.stage = stage;
    }
    
    @FXML private TextField tf_diagnostico;
    @FXML private TextField tf_id;
    
    
    
    @FXML void realizar(ActionEvent event){
        if(tf_id.getText().isEmpty() || tf_diagnostico.getText().isEmpty()){
             Alert al=new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("Preencha todos os campos!");
            al.show();
        }else{
            Consulta con = new Consulta();
            
            con.setConsulta_id(Integer.parseInt(tf_id.getText()));
            con.setDiagnostico(tf_diagnostico.getText());
            if(cs.realizar_con(con)==true){
                tf_id.setText("");
                tf_diagnostico.setText("");
                
                Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                al.setContentText("Consulta Realizada!");
                al.show();
            }else{
                Alert al=new Alert(Alert.AlertType.WARNING);
                al.setContentText("Erro ocorrendo!");
                al.show();
            }
        }
    }

    @FXML
    void sair(ActionEvent event){
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Mascara.maskNumber(tf_id);
        Mascara.maskString(tf_diagnostico);
    }    
    
    void start(Stage stage)throws Exception {
       //carrega o arquivo - tela 
        Parent root = FXMLLoader.load(getClass().getResource("/view/telas/realizar_consulta.fxml"));
        //intancio a cena p/ que essa tela possa aparecer
        Scene scene = new Scene(root);
        //seto a cena no stage
        stage.setTitle("REALIZAR CONSULTA");
        setStage(stage);
        stage.setScene(scene);
        //mostrar a janela
        stage.show();
        System.out.println("medica_clinica.Realizar_consultaController.start() entrou no realizar consultas");
    }
}
