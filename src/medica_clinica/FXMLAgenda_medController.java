package medica_clinica;

import dao.CompromissoDao;
import dao.ConsultaDao;
import dao.MedicoDao;
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
import model.Compromisso;
import model.Consulta;

/**
 * FXML Controller class
 *
 * @author Rivaldo
 */
public class FXMLAgenda_medController implements Initializable {

    private static Stage stage;
    ConsultaDao cd = new ConsultaDao();
    MedicoDao md = new MedicoDao();
    CompromissoDao compro = new CompromissoDao();
    private int id_med;
    private String nome_med;
    //PacienteDao pd = new PacienteDao();

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        FXMLAgenda_medController.stage = stage;
    }
    
    @FXML
    private TextField tf_id_med;

    @FXML
    private TableView<Consulta> tableConsulta;

    @FXML
    private TableColumn<Consulta, String> consultaData;

    @FXML
    private TableColumn<Consulta, String> consultaHi;

    @FXML
    private TableColumn<Consulta, String> consultaHt;

    @FXML
    private TableColumn<Consulta, String> ConsultaPaciente;

    @FXML
    private TableView<Compromisso> tableCompromissos;

    @FXML
    private TableColumn<Compromisso, String> comproData;

    @FXML
    private TableColumn<Compromisso, String> comproHi;

    @FXML
    private TableColumn<Compromisso, String> comproHt;

    @FXML
    private TableColumn<Compromisso, String> comproDescricao;
    
    private List<Consulta> listConsulta = new ArrayList();
    private ObservableList<Consulta> obsConsulta;
    
    private List<Compromisso> listCompromisso = new ArrayList();
    private ObservableList<Compromisso> obsCompromisso;
    
    
    public void carregarTableConsultas(int id_med) throws SQLException{
        ConsultaPaciente.setCellValueFactory(new PropertyValueFactory<>("id_paciente"));
        consultaData.setCellValueFactory(new PropertyValueFactory<>("data"));
        consultaHi.setCellValueFactory(new PropertyValueFactory<>("horario_inicio"));
        consultaHt.setCellValueFactory(new PropertyValueFactory<>("horario_termino"));
        
        listConsulta = cd.lista_consultas(id_med);
        if(listConsulta == null){
            Alert al=new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("Médico inexistente!");
            al.show();
        }
        else{
            obsConsulta = FXCollections.observableArrayList(listConsulta);
        
            tableConsulta.setItems(obsConsulta);
        }
    }
    
    public void carregarTableCompromissos(int id_med) throws SQLException{
        comproDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao_comp"));
        comproData.setCellValueFactory(new PropertyValueFactory<>("data_comp"));
        comproHi.setCellValueFactory(new PropertyValueFactory<>("hora_i_comp"));
        comproHt.setCellValueFactory(new PropertyValueFactory<>("hora_t_comp"));
        
        listCompromisso = compro.lista_compromissos(id_med);
        obsCompromisso = FXCollections.observableArrayList(listCompromisso);
        
        tableCompromissos.setItems(obsCompromisso);
        
    }

    @FXML
    void buscar(ActionEvent event) throws SQLException {
        if(tf_id_med.getText().isEmpty()){
            Alert al=new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("Preencha o ID do médico!");
            al.show();
        }else{
            id_med = Integer.parseInt(tf_id_med.getText());
            carregarTableConsultas(id_med);
            carregarTableCompromissos(id_med);

            nome_med = md.nome_medico(id_med);
            tf_id_med.setText(nome_med);
        }  
    }

    @FXML
    void sair(ActionEvent event) {
        stage.close();
    }

    @FXML
    void ver_med(ActionEvent event) throws Exception {
        Listar_medicosController list_med = new Listar_medicosController();
        Stage stage = new Stage();
        list_med.start(stage);
    }
    
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/telas/FXMLAgenda_med.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("AGENDA MÉDICA");
        stage.setScene(scene);
        stage.show();
        
        setStage(stage);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Mascara.maskNumber(tf_id_med);
    }    
    
}
