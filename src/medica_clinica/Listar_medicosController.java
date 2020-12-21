package medica_clinica;

import dao.MedicoDao;
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
import model.Medico;

/**
 * FXML Controller class
 *
 * @author Rivaldo
 */
public class Listar_medicosController implements Initializable {
    
    MedicoDao md = new MedicoDao();
    private static Stage stage;
    
    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Listar_medicosController.stage = stage;
    }
    
    @FXML
    private TableView<Medico> tableViewMedico;

    @FXML
    private TableColumn<Medico, String> colunaMedicoNome;

    @FXML
    private TableColumn<Medico, String> colunaMedicoCrm;

    @FXML
    private TableColumn<Medico, String> colunaMedicoId;
    
    @FXML
    private TableColumn<Medico, String> colunaMedicoCpf;
    
    private List<Medico> listMedico = new ArrayList();
    
    private ObservableList<Medico> obsMedico;
    
    public void carregarTableMedicos() throws SQLException{
        colunaMedicoNome.setCellValueFactory(new PropertyValueFactory<>("nome_med"));
        colunaMedicoId.setCellValueFactory(new PropertyValueFactory<>("id_med"));
        colunaMedicoCrm.setCellValueFactory(new PropertyValueFactory<>("crm_med"));
        colunaMedicoCpf.setCellValueFactory(new PropertyValueFactory<>("cpf_med"));
        
        listMedico = md.lista_medicos();
        obsMedico = FXCollections.observableArrayList(listMedico);
        
        tableViewMedico.setItems(obsMedico);
        
    }
    @FXML
    void sair(ActionEvent event) {
        stage.close();
    }
    
    public void start(Stage stage) throws Exception {
        //carrega o arquivo - tela 
        Parent root = FXMLLoader.load(getClass().getResource("/view/telas/listar_medicos.fxml"));
        //intancio a cena p/ que essa tela possa aparecer
        Scene scene = new Scene(root);
        //seto a cena no stage
        stage.setTitle("LISTA DE MÉDICOS");
        setStage(stage);
        stage.setScene(scene);
        //mostrar a janela
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            carregarTableMedicos();
        } catch (SQLException ex) {
            Logger.getLogger(Listar_medicosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
