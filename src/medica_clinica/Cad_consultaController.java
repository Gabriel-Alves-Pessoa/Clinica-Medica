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
 * @author Bianca Lopes
 * @author Alves
 */
public class Cad_consultaController implements Initializable {
    ConsultaDao cs = new ConsultaDao();
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Cad_consultaController.stage = stage;
    }

     @FXML
    private TextField td_id;

    @FXML
    private TextField tf_pac;

    @FXML
    private TextField tf_med;

    @FXML
    private TextField tf_data;

    @FXML
    private TextField tf_hi;

    @FXML
    private TextField tf_ht;
    
    @FXML
    private TextField tf_descricao;
    
    @FXML
    void cadastrar(ActionEvent event) {
        if(tf_descricao.getText().isEmpty()|| tf_pac.getText().isEmpty()|| tf_med.getText().isEmpty()|| tf_ht.getText().isEmpty()|| tf_data.getText().isEmpty() || tf_hi.getText().isEmpty()){
            Alert al=new Alert(Alert.AlertType.WARNING);
            al.setContentText("Preencha todos os campos!");
            al.show();
        }else{                  
            Consulta cons = new Consulta();

            cons.setId_medico(Integer.parseInt(tf_med.getText()));
            cons.setId_paciente(Integer.parseInt(tf_pac.getText()));
            cons.setHorario_inicio(tf_hi.getText());
            cons.setHorario_termino(tf_ht.getText());
            cons.setData(tf_data.getText());
            cons.setDescricao(tf_descricao.getText());
            if(cs.cadastrar_cons(cons)==true){

                tf_med.setText("");
                tf_pac.setText("");
                tf_hi.setText("");
                tf_ht.setText("");
                tf_data.setText("");
                tf_descricao.setText("");
                
                Alert al=new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("Cadastro feito com sucesso!");
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
    
    @FXML
    void ver_medicos(ActionEvent event) throws Exception {
        Listar_medicosController list_med = new Listar_medicosController();
        Stage stage = new Stage();
        list_med.start(stage);
    }

    @FXML
    void ver_pacientes(ActionEvent event) throws Exception {
        Listar_pacientesController list_pac = new Listar_pacientesController();
        Stage stage = new Stage();
        list_pac.start(stage);
    }
    
    @FXML 
    void fazer(ActionEvent event) throws Exception{
        Realizar_consultaController meh = new Realizar_consultaController();
        Stage stage = new Stage();
        meh.start(stage);
    }
    
    @FXML
    void ver_consultas(ActionEvent event) throws Exception {
        FXMLver_consultasController ver = new FXMLver_consultasController();
        Stage stage = new Stage();
        ver.start(stage);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Mascara.maskNumber(td_id);
        Mascara.maskNumber(tf_pac);
        Mascara.maskNumber(tf_med);
        Mascara.maskString(tf_descricao);
        Mascara.maskData(tf_data);
        Mascara.maskHora(tf_hi);
        Mascara.maskHora(tf_ht);
    }    

    void start(Stage stage)throws Exception {
       //carrega o arquivo - tela 
        Parent root = FXMLLoader.load(getClass().getResource("/view/telas/Cad_consulta.fxml"));
        //intancio a cena p/ que essa tela possa aparecer
        Scene scene = new Scene(root);
        //seto a cena no stage
        stage.setTitle("CRUD CONSULTA");
        setStage(stage);
        stage.setScene(scene);
        //mostrar a janela
        stage.show();
        
    }
}
