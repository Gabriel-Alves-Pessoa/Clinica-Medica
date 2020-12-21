package medica_clinica;

import dao.CompromissoDao;
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
import model.Compromisso;

/**
 * FXML Controller class
 *
 * @author Alves
 */
public class Cad_compromissoController implements Initializable {
     CompromissoDao cd = new CompromissoDao();
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Cad_compromissoController.stage = stage;
    }
    
    @FXML private TextField tf_med;
    @FXML private TextField tf_data;
    @FXML private TextField tf_hi;
    @FXML private TextField tf_ht;
    @FXML private TextField tf_descricao;
    @FXML private TextField tf_id;


    @FXML
    void cadastrar(ActionEvent event) {
        if(tf_descricao.getText().isEmpty()|| tf_med.getText().isEmpty()|| tf_ht.getText().isEmpty()|| tf_data.getText().isEmpty() || tf_hi.getText().isEmpty()){
            Alert al=new Alert(Alert.AlertType.WARNING);
            al.setContentText("Preencha todos os campos!");
            al.show();
        }else{                  
            Compromisso c= new Compromisso();

            c.setId_med(Integer.parseInt(tf_med.getText()));
            c.setHora_i_comp(tf_hi.getText());
            c.setHora_t_comp(tf_ht.getText());
            c.setData_comp(tf_data.getText());
            c.setDescricao_comp(tf_descricao.getText());
            if(cd.cadastrar_comp(c)==true){

                tf_med.setText("");
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
    void ver_medicos(ActionEvent event) throws Exception {
        Listar_medicosController list_med = new Listar_medicosController();
        Stage stg = new Stage();
        list_med.start(stg);
    }
    
     @FXML
    void sair(ActionEvent event){
        stage.close();
        System.out.println("Saiu do Crud do compromisso;");
    }
    
    void start(Stage stage)throws Exception {
       
        Parent root = FXMLLoader.load(getClass().getResource("/view/telas/Cad_compromisso.fxml"));  //carrega o arquivo - tela 
        Scene scene = new Scene(root);  //intancio a cena p/ que essa tela possa aparecer
        stage.setTitle("CRUD COMPROMISSO");
        setStage(stage);  //seto a cena no stage
        stage.setScene(scene);
        stage.show();    //mostrar a janela
        System.out.println("Entrou no Crud do compromisso;");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Mascara.maskNumber(tf_med);
        Mascara.maskNumber(tf_id);
        Mascara.maskString(tf_descricao);
        Mascara.maskData(tf_data);
        Mascara.maskHora(tf_hi);
        Mascara.maskHora(tf_ht);
    }    
    
}
