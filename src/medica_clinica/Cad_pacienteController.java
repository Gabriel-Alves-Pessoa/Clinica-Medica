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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Paciente;

/**
 * FXML Controller class
 *
 * @author Rivaldo
 */
public class Cad_pacienteController implements Initializable {
    PacienteDao pc = new PacienteDao();
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Cad_pacienteController.stage = stage;
    }
    @FXML
    private TableView<Paciente> tableViewPacientes;

    @FXML
    private TableColumn<Paciente, String> colunaPacienteNome;

    @FXML
    private TableColumn<Paciente, String> colunaPacienteCPF;
    
    @FXML
    private TableColumn<Paciente, String> colunaPacienteRG;

    @FXML
    private TableColumn<Paciente, String> colunaPacienteID;
    
    @FXML
    private TextField tf_nome;

    @FXML
    private TextField tf_cpf;

    @FXML
    private TextField tf_id;

    @FXML
    private TextField tf_buscar;

    @FXML
    private TextField tf_rg;
    
    private List<Paciente> listPaciente = new ArrayList();
    
    private ObservableList<Paciente> obsPaciente;

    public void carregarTablePacientes() throws SQLException{
        colunaPacienteNome.setCellValueFactory(new PropertyValueFactory<>("nome_paciente"));
        colunaPacienteCPF.setCellValueFactory(new PropertyValueFactory<>("cpf_paciente"));
        colunaPacienteRG.setCellValueFactory(new PropertyValueFactory<>("rg_paciente"));
        colunaPacienteID.setCellValueFactory(new PropertyValueFactory<>("id_paciente"));
        
        listPaciente = pc.lista_pacientes();
        obsPaciente = FXCollections.observableArrayList(listPaciente);
        
        tableViewPacientes.setItems(obsPaciente);
        
    }
    
    @FXML
    void atualizar(ActionEvent event) throws SQLException {
        if(tf_rg.getText().isEmpty() || tf_cpf.getText().isEmpty() || tf_id.getText().isEmpty() || tf_nome.getText().isEmpty()){
            Alert al=new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("Preencha todos os campos!");
            al.show();
        }else{
            Paciente pac = new Paciente();
                       
            pac.setId_paciente(Integer.parseInt(tf_id.getText()));
            pac.setCpf_paciente(tf_cpf.getText());
            pac.setNome_paciente(tf_nome.getText());
            pac.setRg_paciente(tf_rg.getText());

            if(pc.atualizar(pac)==true){
                tf_nome.setText("");
                tf_cpf.setText("");
                tf_id.setText("");
                tf_rg.setText("");
                
                Alert al=new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("Dados atualizados com sucesso!");
                al.show();
                carregarTablePacientes();
            }
        }   
    }

    @FXML
    void buscar(ActionEvent event) throws SQLException {
        if(!tf_buscar.getText().isEmpty()){
            String cpf = tf_buscar.getText();
            Paciente pac = pc.pesquisar(cpf);

            if(pac==null){
                Alert al=new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("Paciente não encontrado, tente novamente!");
                al.show();
            }else{
                tf_cpf.setText(pac.getCpf_paciente());
                tf_nome.setText(pac.getNome_paciente());
                tf_id.setText(""+pac.getId_paciente());
                tf_rg.setText(pac.getRg_paciente());
                tf_buscar.setText("");
            }
        }else{
            Alert al=new Alert(Alert.AlertType.WARNING);
            al.setContentText("Preencha o campo PESQUISAR!");
            al.show();
        }
    }

    @FXML
    void cadastrar(ActionEvent event) throws SQLException {
        if(tf_rg.getText().isEmpty()|| tf_cpf.getText().isEmpty() || tf_nome.getText().isEmpty()){
            Alert al=new Alert(Alert.AlertType.WARNING);
            al.setContentText("Preencha todos os campos!");
            al.show();
        }else{                  
            Paciente pac = new Paciente();

            pac.setCpf_paciente(tf_cpf.getText());
            pac.setNome_paciente(tf_nome.getText());
            pac.setRg_paciente(tf_rg.getText());
        
            if(pc.cadastrar_pac(pac)==true){

                tf_nome.setText("");
                tf_cpf.setText("");
                tf_rg.setText("");

                Alert al=new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("Cadastro feito com sucesso!");
                al.show();
                carregarTablePacientes();
            }else{
                Alert al=new Alert(Alert.AlertType.WARNING);
                al.setContentText("Erro ocorrendo!");
                al.show();
            }
        }
    }

    @FXML
    void excluir(ActionEvent event) throws SQLException {
        if(!tf_id.getText().isEmpty()){
            if(pc.excluir(Integer.parseInt(tf_id.getText())) == true){ 
                tf_nome.setText("");
                tf_cpf.setText("");
                tf_rg.setText("");
                tf_id.setText("");
                
                Alert al=new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("Dados excluídos com sucesso!");
                al.show();
                carregarTablePacientes();
            }else{
                Alert al=new Alert(Alert.AlertType.WARNING);
                al.setContentText("A exclusão não foi possível, tente novamente!");
                al.show();
            }
        }else{
            Alert al=new Alert(Alert.AlertType.WARNING);
            al.setContentText("Preencha o campo ID!");
            al.show();
        }
    }

    @FXML
    void sair(ActionEvent event) {
        stage.close();
    }
    
    public void start(Stage stage) throws Exception {
       //carrega o arquivo - tela 
        Parent root = FXMLLoader.load(getClass().getResource("/view/telas/Cad_paciente.fxml"));
        //intancio a cena p/ que essa tela possa aparecer
        Scene scene = new Scene(root);
        //seto a cena no stage
        stage.setTitle("CRUD PACIENTE");
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
            Logger.getLogger(Cad_pacienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Mascara.maskString(tf_nome);
        Mascara.maskCpf(tf_cpf);
        Mascara.maskCpf(tf_buscar);
        Mascara.maskRg(tf_rg);
        Mascara.maskNumber(tf_id);
    }    
    
}
