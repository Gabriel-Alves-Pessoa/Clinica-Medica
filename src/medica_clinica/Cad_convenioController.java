package medica_clinica;

import dao.ConvenioDao;
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
import model.Convenio;

/**
 * FXML Controller class
 *
 * @author Alves
 */
public class Cad_convenioController implements Initializable{
    
    ConvenioDao c = new ConvenioDao();
    private static Stage stage;
    
    public static void setStage(Stage stage){
        Cad_convenioController.stage = stage;
    }
    public static Stage getStage(){
        return stage;
    } 
    
    @FXML
    private TextField tf_id;

    @FXML
    private TextField tf_nome;

    @FXML
    private TextField tf_cnpj;

    @FXML
    private TextField tf_buscar;
    
    @FXML
    private TableView<Convenio> tableViewConvenio;

    @FXML
    private TableColumn<Convenio, String> colunaConvenioNome;

    @FXML
    private TableColumn<Convenio, String> colunaConvenioCnpj;

    @FXML
    private TableColumn<Convenio, String> colunaConvenioId;
    
    private List<Convenio> listConvenio = new ArrayList();
    
    private ObservableList<Convenio> obsConvenio;

    public void carregarTablePacientes() throws SQLException{
        colunaConvenioNome.setCellValueFactory(new PropertyValueFactory<>("nome_con"));
        colunaConvenioCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj_con"));
        colunaConvenioId.setCellValueFactory(new PropertyValueFactory<>("id_con"));
        
        listConvenio = c.lista_convenios();
        obsConvenio = FXCollections.observableArrayList(listConvenio);
        
        tableViewConvenio.setItems(obsConvenio);        
    }
    
    @FXML
    public void atualizar(ActionEvent event) throws SQLException {
        if(tf_nome.getText().isEmpty() || tf_cnpj.getText() .isEmpty() || tf_id.getText().isEmpty()){
                
            Alert al=new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("Preencha todos os campos!");
            al.show();
        }else{
            Convenio con = new Convenio();
                       
            con.setNome_con(tf_nome.getText());
            con.setCnpj_con(tf_cnpj.getText());
            con.setId_con(Integer.parseInt(tf_id.getText()));
        
            if(c.atualizar_con(con)==true){
                tf_nome.setText("");
                tf_id.setText("");
                tf_cnpj.setText("");

                
                Alert al=new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("Dados atualizados com sucesso!");
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
    void buscar(ActionEvent event) throws SQLException {
        if(!tf_buscar.getText().isEmpty()){
            String cnpj = tf_buscar.getText();
            
            Convenio convenio = c.pesquisar_con(cnpj);
            if(convenio==null){
                Alert al=new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("Convênio não encontrado, tente novamente!");
                al.show();
            }else{
   
                tf_cnpj.setText(convenio.getCnpj_con());
                tf_nome.setText(convenio.getNome_con());
                tf_id.setText(""+convenio.getId_con());
                tf_buscar.setText("");}
        }else{
            Alert al=new Alert(Alert.AlertType.WARNING);
            al.setContentText("Preencha o campo PESQUISAR!");
            al.show();
        }
    }

    @FXML
    void cadastrar(ActionEvent event) throws SQLException {
        if(tf_cnpj.getText() .isEmpty() || tf_nome.getText() .isEmpty()){
             Alert al=new Alert(Alert.AlertType.WARNING);
            al.setContentText("Preencha todos os campos!");
            al.show();
        }else{
            Convenio cn = new Convenio();
            cn.setNome_con(tf_nome.getText());
            cn.setCnpj_con(tf_cnpj.getText());
            
            if(c.cadastrar_con(cn)==true){
                tf_nome.setText("");
                tf_cnpj.setText("");
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
        if(tf_id.getText().isEmpty()){
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setContentText("Preencha o campo 'id'.");
        }else{
           int id = Integer.parseInt(tf_id.getText());
           if(c.excluir_con(id)==true){
               Alert al = new Alert(Alert.AlertType.CONFIRMATION);
               al.setContentText("Dados apagados com sucesso");
               tf_id.setText("");
               al.show();
               carregarTablePacientes();
           }else{
               Alert al = new Alert(Alert.AlertType.ERROR);
               al.setContentText("Error!");
               al.show();
        }
        }
    }

    @FXML
    void sair(ActionEvent event) {
        stage.close();
    }
    
    public void start(Stage stage) throws Exception {
        //carrega o arquivo - tela 
        Parent root = FXMLLoader.load(getClass().getResource("/view/telas/Cad_convenio.fxml"));
        //intancio a cena p/ que essa tela possa aparecer
        Scene scene = new Scene(root);
        //seto a cena no stage
        stage.setTitle("CRUD CONVENIO");
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
            Logger.getLogger(Cad_convenioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Mascara.maskString(tf_nome);
        Mascara.maskCnpj(tf_buscar);
        Mascara.maskNumber(tf_id);
        Mascara.maskCnpj(tf_cnpj);
    }    
    
}

