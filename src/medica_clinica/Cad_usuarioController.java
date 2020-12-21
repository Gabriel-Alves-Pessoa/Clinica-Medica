package medica_clinica;

import dao.LoginDao;
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
import model.Usuario;

/**
 *
 * @author Rivaldo
 * @author Alves
 */

public class Cad_usuarioController implements Initializable {
    LoginDao lg = new LoginDao();
    int id_user;
    private static Stage stage;
    @FXML
    private TextField tf_busca_cpf;
    @FXML
    private TextField tf_cpf;
    @FXML
    private TextField tf_nome;
    @FXML
    private TextField tf_login;
    @FXML
    private PasswordField tf_senha;
    @FXML
    private TextField tf_id;
    @FXML
    private TableColumn<Usuario, String> colunaUsuarioNome;

    @FXML
    private TableColumn<Usuario, String> colunaUsuarioCpf;

    @FXML
    private TableColumn<Usuario, String> colunaUsuarioLogin;

    @FXML
    private TableColumn<Usuario, String> colunaUsuarioSenha;

    @FXML
    private TableColumn<Usuario, String> colunaUsuarioId;
    
    @FXML
    private TableView<Usuario> tableViewUsuarios;
    
    private List<Usuario> listUsuario = new ArrayList();
    
    private ObservableList<Usuario> obsUsuario;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Cad_usuarioController.stage = stage;
    }

    public void carregarTableUsuarios() throws SQLException{
        colunaUsuarioNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaUsuarioCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colunaUsuarioLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        colunaUsuarioId.setCellValueFactory(new PropertyValueFactory<>("id_usuario"));
        colunaUsuarioSenha.setCellValueFactory(new PropertyValueFactory<>("password"));
        
        listUsuario = lg.lista_usuarios();
        obsUsuario = FXCollections.observableArrayList(listUsuario);
        
        tableViewUsuarios.setItems(obsUsuario);
    }
    
    public void start(Stage stage) throws Exception {
       //carrega o arquivo - tela 
        Parent root = FXMLLoader.load(getClass().getResource("/view/telas/Cad_usuario.fxml"));
        //intancio a cena p/ que essa tela possa aparecer
        Scene scene = new Scene(root);
        //seto a cena no stage
        stage.setTitle("CRUD USUÁRIO");
        setStage(stage);
        stage.setScene(scene);
        //mostrar a janela
        stage.show();
    }
    
    @FXML
    void cad_user(ActionEvent event) throws SQLException {
        if(tf_cpf.getText().isEmpty() || tf_login.getText().isEmpty() || tf_senha.getText().isEmpty() ||tf_nome.getText().isEmpty()){
            Alert al=new Alert(Alert.AlertType.WARNING);
            al.setContentText("Preencha todos os campos!");
            al.show();
        }else{                  
            Usuario us = new Usuario();

            us.setCpf(tf_cpf.getText());
            us.setLogin(tf_login.getText());
            us.setPassword(tf_senha.getText());
            us.setNome(tf_nome.getText());
        
            if(lg.cadastrar_login(us)==true){

                tf_nome.setText("");
                tf_cpf.setText("");
                tf_login.setText("");
                tf_senha.setText("");

                Alert al=new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("Cadastro feito com sucesso!");
                al.show();
                carregarTableUsuarios();
            }else{
                Alert al=new Alert(Alert.AlertType.WARNING);
                al.setContentText("Erro ocorrendo!");
                al.show();
            }
        }
    }
      
    @FXML
    void sair(ActionEvent event) {
        stage.close();
    }
    
    @FXML
    void atualizar(ActionEvent event) throws SQLException {
        if(tf_cpf.getText().isEmpty() || tf_id.getText().isEmpty() || tf_login.getText().isEmpty() || tf_senha.getText().isEmpty() ||tf_nome.getText().isEmpty()){
            Alert al=new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("Preencha todos os campos!");
            al.show();
        }else{
            Usuario us = new Usuario();
                       
            us.setId_usuario(Integer.parseInt(tf_id.getText()));
            us.setCpf(tf_cpf.getText());
            us.setLogin(tf_login.getText());
            us.setPassword(tf_senha.getText());
            us.setNome(tf_nome.getText());

            if(lg.atualizar(us)==true){
                tf_nome.setText("");
                tf_cpf.setText("");
                tf_login.setText("");
                tf_senha.setText("");
                tf_id.setText("");
                
                Alert al=new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("Dados atualizados com sucesso!");
                al.show();
                carregarTableUsuarios();
            }
        }   
    }
    
    @FXML
    void excluir(ActionEvent event) throws SQLException {
        if(!tf_id.getText().isEmpty()){
            if(lg.excluir(Integer.parseInt(tf_id.getText())) == true){ 
                tf_nome.setText("");
                tf_cpf.setText("");
                tf_login.setText("");
                tf_senha.setText("");
                tf_id.setText("");
                
                Alert al=new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("Dados excluídos com sucesso!");
                al.show();
                carregarTableUsuarios();
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
    void search(ActionEvent event) throws SQLException {
        if(!tf_busca_cpf.getText().isEmpty()){
            String cpf = tf_busca_cpf.getText();
            Usuario u = lg.pesquisar(cpf);

            if(u==null){
                Alert al=new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("Usuário não encontrado, tente novamente!");
                al.show();
            }else{
                tf_cpf.setText(u.getCpf());
                tf_nome.setText(u.getNome());
                tf_login.setText(u.getLogin());
                tf_senha.setText(u.getPassword());
                tf_id.setText(""+u.getId_usuario());
                tf_busca_cpf.setText("");
            }
        }else{
            Alert al=new Alert(Alert.AlertType.WARNING);
            al.setContentText("Preencha o campo PESQUISAR!");
            al.show();
        }
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        try {
            carregarTableUsuarios();
        } catch (SQLException ex) {
            Logger.getLogger(Cad_usuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Mascara.maskString(tf_nome);
        Mascara.maskCpf(tf_cpf);
        Mascara.maskCpf(tf_busca_cpf);
        Mascara.maskStringOrNumberNoSpace(tf_login);
        Mascara.maskStringOrNumberNoSpace(tf_senha);
        Mascara.maskNumber(tf_id);
    }    
}
