package medica_clinica;

import dao.PacienteDao;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Paciente;

/**
 * FXML Controller class
 *
 * @author Rivaldo
 */
public class Listar_pacientesController implements Initializable {
    
    PacienteDao pc = new PacienteDao();
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Listar_pacientesController.stage = stage;
    }
    
    @FXML
    private TableView<Paciente> tableViewPaciente;

    @FXML
    private TableColumn<Paciente, String> colunaPacienteNome;

    @FXML
    private TableColumn<Paciente, String> colunaPacienteCPF;

    @FXML
    private TableColumn<Paciente, String> colunaPacienteID;
    
    private List<Paciente> listPaciente = new ArrayList();
    
    private ObservableList<Paciente> obsPaciente;
    
    public void carregarTablePacientes() throws SQLException{
        colunaPacienteNome.setCellValueFactory(new PropertyValueFactory<>("nome_paciente"));
        colunaPacienteCPF.setCellValueFactory(new PropertyValueFactory<>("cpf_paciente"));
        colunaPacienteID.setCellValueFactory(new PropertyValueFactory<>("id_paciente"));
        
        listPaciente = pc.lista_pacientes();
        obsPaciente = FXCollections.observableArrayList(listPaciente);
        
        tableViewPaciente.setItems(obsPaciente);
        
    }

    @FXML
    void sair(ActionEvent event) {
        stage.close();
    }
    
    public void start(Stage stage) throws Exception {
        //carrega o arquivo - tela 
        Parent root = FXMLLoader.load(getClass().getResource("/view/telas/listar_pacientes.fxml"));
        //intancio a cena p/ que essa tela possa aparecer
        Scene scene = new Scene(root);
        //seto a cena no stage
        stage.setTitle("LISTA DE PACIENTES");
        setStage(stage);
        stage.setScene(scene);
        //mostrar a janela
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            carregarTablePacientes();
        } catch (SQLException ex) {
            Logger.getLogger(Listar_pacientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
