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
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Medico;

/**
 * FXML Controller class
 *
 * @author Rivaldo
 */
public class Cad_medicoController implements Initializable {

    MedicoDao md = new MedicoDao();
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Cad_medicoController.stage = stage;
    }
    
    @FXML
    private TextField tf_nome;

    @FXML
    private TextField tf_cpf;

    @FXML
    private TextField tf_crm;

    @FXML
    private TextField tf_rg;

    @FXML
    private TextField tf_login;

    @FXML
    private PasswordField tf_senha;

    @FXML
    private TextField tf_id;

    @FXML
    private TextField tf_buscar;
    
    @FXML
    private TableView<Medico> tableViewMedico;

    @FXML
    private TableColumn<Medico, String> colunaMedicoNome;

    @FXML
    private TableColumn<Medico, String> colunaMedicoCpf;

    @FXML
    private TableColumn<Medico, String> colunaMedicoCrm;

    @FXML
    private TableColumn<Medico, String> colunaMedicoRg;

    @FXML
    private TableColumn<Medico, String> colunaMedicoLogin;

    @FXML
    private TableColumn<Medico, String> colunaMedicoSenha;

    @FXML
    private TableColumn<Medico, String> colunaMedicoId;
    
    private List<Medico> listMedico = new ArrayList();
    
    private ObservableList<Medico> obsMedico;

    public void carregarTablePacientes() throws SQLException{
        colunaMedicoNome.setCellValueFactory(new PropertyValueFactory<>("nome_med"));
        colunaMedicoCpf.setCellValueFactory(new PropertyValueFactory<>("cpf_med"));
        colunaMedicoRg.setCellValueFactory(new PropertyValueFactory<>("rg_med"));
        colunaMedicoId.setCellValueFactory(new PropertyValueFactory<>("id_med"));
        colunaMedicoCrm.setCellValueFactory(new PropertyValueFactory<>("crm_med"));
        colunaMedicoLogin.setCellValueFactory(new PropertyValueFactory<>("login_med"));
        colunaMedicoSenha.setCellValueFactory(new PropertyValueFactory<>("senha_med"));
        
        listMedico = md.lista_medicos();
        obsMedico = FXCollections.observableArrayList(listMedico);
        
        tableViewMedico.setItems(obsMedico);
        
    }
    
    @FXML
    void atualizar(ActionEvent event) throws SQLException {
        if(tf_crm.getText().isEmpty() || tf_rg.getText().isEmpty() || tf_cpf.getText().isEmpty() || tf_id.getText().isEmpty() || tf_login.getText().isEmpty() || tf_senha.getText().isEmpty() ||tf_nome.getText().isEmpty()){
            Alert al=new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("Preencha todos os campos!");
            al.show();
        }else{
            Medico med = new Medico();
                       
            med.setId_med(Integer.parseInt(tf_id.getText()));
            med.setCpf_med(tf_cpf.getText());
            med.setLogin_med(tf_login.getText());
            med.setSenha_med(tf_senha.getText());
            med.setNome_med(tf_nome.getText());
            med.setCrm_med(tf_crm.getText());
            med.setRg_med(tf_rg.getText());

            if(md.atualizar(med)==true){
                tf_nome.setText("");
                tf_cpf.setText("");
                tf_login.setText("");
                tf_senha.setText("");
                tf_id.setText("");
                tf_rg.setText("");
                tf_crm.setText("");
                
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
            String crm = tf_buscar.getText();
            Medico m = md.pesquisar(crm);

            if(m==null){
                Alert al=new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("Medico não encontrado, tente novamente!");
                al.show();
            }else{
                tf_cpf.setText(m.getCpf_med());
                tf_nome.setText(m.getNome_med());
                tf_login.setText(m.getLogin_med());
                tf_senha.setText(m.getSenha_med());
                tf_id.setText(""+m.getId_med());
                tf_rg.setText(m.getRg_med());
                tf_crm.setText(m.getCrm_med());
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
        if(tf_rg.getText().isEmpty() || tf_crm.getText().isEmpty() ||tf_cpf.getText().isEmpty() || tf_login.getText().isEmpty() || tf_senha.getText().isEmpty() ||tf_nome.getText().isEmpty()){
            Alert al=new Alert(Alert.AlertType.WARNING);
            al.setContentText("Preencha todos os campos!");
            al.show();
        }else{                  
            Medico med = new Medico();

            med.setCpf_med(tf_cpf.getText());
            med.setLogin_med(tf_login.getText());
            med.setSenha_med(tf_senha.getText());
            med.setNome_med(tf_nome.getText());
            med.setCrm_med(tf_crm.getText());
            med.setRg_med(tf_rg.getText());
        
            if(md.cadastrar_med(med)==true){

                tf_nome.setText("");
                tf_cpf.setText("");
                tf_login.setText("");
                tf_senha.setText("");
                tf_rg.setText("");
                tf_crm.setText("");

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
    void deletar(ActionEvent event) throws SQLException {
        if(!tf_id.getText().isEmpty()){
            if(md.excluir(Integer.parseInt(tf_id.getText())) == true){ 
                tf_nome.setText("");
                tf_cpf.setText("");
                tf_login.setText("");
                tf_senha.setText("");
                tf_rg.setText("");
                tf_crm.setText("");
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
        Parent root = FXMLLoader.load(getClass().getResource("/view/telas/Cad_medico.fxml"));
        //intancio a cena p/ que essa tela possa aparecer
        Scene scene = new Scene(root);
        //seto a cena no stage
        stage.setTitle("CRUD MÉDICO");
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
            Logger.getLogger(Cad_medicoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Mascara.maskString(tf_nome);
        Mascara.maskCpf(tf_cpf);
        Mascara.maskCrm(tf_buscar);
        Mascara.maskRg(tf_rg);
        Mascara.maskNumber(tf_id);
        Mascara.maskStringOrNumberNoSpace(tf_login);
        Mascara.maskStringOrNumberNoSpace(tf_senha);
        Mascara.maskCrm(tf_crm);
    }     
}