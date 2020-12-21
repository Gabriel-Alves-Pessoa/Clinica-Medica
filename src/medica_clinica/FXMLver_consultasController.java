package medica_clinica;

import dao.ConsultaDao;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Consulta;

/**
 * FXML Controller class
 *
 * @author Rivaldo
 */
public class FXMLver_consultasController implements Initializable {

    ConsultaDao cd = new ConsultaDao();
    private static Stage stage;
    
    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        FXMLver_consultasController.stage = stage;
    }
    
     @FXML
    private TextField buscar_crm;

    @FXML
    private TextField buscar_cpf;

    @FXML
    private TextField buscar_data;

    @FXML
    private TableView<Consulta> tabela_consulta;

    @FXML
    private TableColumn<Consulta, String> data;

    @FXML
    private TableColumn<Consulta, String> hi;

    @FXML
    private TableColumn<Consulta, String> hf;

    @FXML
    private TableColumn<Consulta, String> id;
    
    @FXML
    private TableColumn<Consulta, String> id_c;
    
     @FXML
    private TableColumn<Consulta, String> id_m;
    
    private List<Consulta> listConsulta = new ArrayList();
    private ObservableList<Consulta> obsConsulta;

    @FXML
    void buscar_cpf(ActionEvent event) throws SQLException {
        if(buscar_cpf.getText().isEmpty()){
            Alert al=new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("Insira um CPF");
            al.show();
        }else{
            listConsulta = cd.lista_consultasCpf(buscar_cpf.getText());
            
            if(listConsulta == null){
                Alert al=new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("CPF inexistente em médicos");
                al.show();
            }else{
                id.setCellValueFactory(new PropertyValueFactory<>("id_paciente"));
                id_c.setCellValueFactory(new PropertyValueFactory<>("consulta_id"));
                id_m.setCellValueFactory(new PropertyValueFactory<>("id_medico"));
                data.setCellValueFactory(new PropertyValueFactory<>("data"));
                hi.setCellValueFactory(new PropertyValueFactory<>("horario_inicio"));
                hf.setCellValueFactory(new PropertyValueFactory<>("horario_termino"));
                
                obsConsulta = FXCollections.observableArrayList(listConsulta);
        
                tabela_consulta.setItems(obsConsulta);
            }
        }
    }

    @FXML
    void buscar_crm(ActionEvent event) throws SQLException {
        if(buscar_crm.getText().isEmpty()){
            Alert al=new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("Insira um CRM");
            al.show();
        }else{
            listConsulta = cd.lista_consultasCrm(buscar_crm.getText());
            
            if(listConsulta == null){
                Alert al=new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("CRM inexistente em médicos");
                al.show();
            }else{
                id.setCellValueFactory(new PropertyValueFactory<>("id_paciente"));
                id_c.setCellValueFactory(new PropertyValueFactory<>("consulta_id"));
                id_m.setCellValueFactory(new PropertyValueFactory<>("id_medico"));
                data.setCellValueFactory(new PropertyValueFactory<>("data"));
                hi.setCellValueFactory(new PropertyValueFactory<>("horario_inicio"));
                hf.setCellValueFactory(new PropertyValueFactory<>("horario_termino"));
                
                obsConsulta = FXCollections.observableArrayList(listConsulta);
        
                tabela_consulta.setItems(obsConsulta);
            }
        }
    }

    @FXML
    void buscar_data(ActionEvent event) throws SQLException {
        if(buscar_data.getText().isEmpty()){
            Alert al=new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("Insira uma Data");
            al.show();
        }else{
            listConsulta = cd.lista_consultasData(buscar_data.getText());
            
            if(listConsulta == null){
                Alert al=new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("Data inexistente em consultas");
                al.show();
            }else{
                id.setCellValueFactory(new PropertyValueFactory<>("id_paciente"));
                id_c.setCellValueFactory(new PropertyValueFactory<>("consulta_id"));
                id_m.setCellValueFactory(new PropertyValueFactory<>("id_medico"));
                data.setCellValueFactory(new PropertyValueFactory<>("data"));
                hi.setCellValueFactory(new PropertyValueFactory<>("horario_inicio"));
                hf.setCellValueFactory(new PropertyValueFactory<>("horario_termino"));
                
                obsConsulta = FXCollections.observableArrayList(listConsulta);
        
                tabela_consulta.setItems(obsConsulta);
            }
        }
    }

    @FXML
    void sair(ActionEvent event) {
        stage.close();
    }
    
    @FXML
    void ver_m(ActionEvent event) throws Exception {
        Listar_medicosController list_med = new Listar_medicosController();
        Stage stage = new Stage();
        list_med.start(stage);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Mascara.maskCrm(buscar_crm);
        Mascara.maskCpf(buscar_cpf);
        Mascara.maskData(buscar_data);
    }    
    
    void start(Stage stage)throws Exception {
       //carrega o arquivo - tela 
        Parent root = FXMLLoader.load(getClass().getResource("/view/telas/FXMLver_consultas.fxml"));
        //intancio a cena p/ que essa tela possa aparecer
        Scene scene = new Scene(root);
        //seto a cena no stage
        stage.setTitle("VER CONSULTAS");
        setStage(stage);
        stage.setScene(scene);
        //mostrar a janela
        stage.show();
        
    }
}
