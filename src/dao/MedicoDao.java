package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Medico;

/**
 *
 * @author Rivaldo
 */
public class MedicoDao {
    
    private Connection con;
    private Statement stm;
    
    public MedicoDao() {
        try {
            con=(Connection) ConexaoFactory.getConnection();
            stm=con.createStatement();
        } catch (ClassNotFoundException | SQLException e) { }
    }
    
    public boolean cadastrar_med(Medico md){
       try{
            String login = md.getLogin_med();
            String senha = md.getSenha_med();
            String cpf = md.getCpf_med();
            String nome  = md.getNome_med();
            String crm = md.getCrm_med();
            String rg = md.getRg_med();
         
            stm.executeUpdate("insert into medico(login_med, senha_med, cpf_med, nome_med, crm_med, rg_med) values('"+login+"','"+senha+"','"+cpf+"','"+nome+"','"+crm+"','"+rg+"')");
            return true;
        }catch(SQLException ex){
            System.out.println(ex.getErrorCode());
            return false;     
        }   
    }
    
    public boolean atualizar(Medico md) throws SQLException{
        try{
            stm.executeUpdate("update medico set login_med='"+md.getLogin_med()+"',rg_med = '"+md.getRg_med()+"', crm_med = '"+md.getCrm_med()+"', senha_med='"+md.getSenha_med()+"', cpf_med='"+md.getCpf_med()+"', nome_med='"+md.getNome_med()+"' where id_med='"+md.getId_med()+"'");
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    public Medico pesquisar(String crm) throws SQLException{
        
        ResultSet rs=stm.executeQuery("select * from medico where crm_med='"+crm+"'");
        
        if(rs.next()){
            Medico md = new Medico();
            md.setNome_med(rs.getString("nome_med"));
            md.setId_med(rs.getInt("id_med"));
            md.setCpf_med(rs.getString("cpf_med"));
            md.setLogin_med(rs.getString("login_med"));
            md.setSenha_med(rs.getString("senha_med"));
            md.setCrm_med(rs.getString("crm_med"));
            md.setRg_med(rs.getString("rg_med"));
            
            return md;
        }else{
            return null;
        }
    }
    
    public ArrayList<Medico> lista_medicos() throws SQLException{
        
        ResultSet rs = stm.executeQuery("select * from medico");
        ArrayList<Medico> lista = new ArrayList<>();
        
        while(rs.next()){
            Medico med = new Medico();
            med.setCpf_med(rs.getString("cpf_med"));
            med.setNome_med(rs.getString("nome_med"));
            //med.setRg_med(rs.getString("rg_med"));
            med.setId_med(rs.getInt("id_med"));
            med.setCrm_med(rs.getString("crm_med"));
            //med.setLogin_med(rs.getString("login_med"));
            //med.setSenha_med(rs.getString("senha_med"));
            
            lista.add(med);
        }
            return lista;
    }
    
    public String nome_medico(int id_med) throws SQLException{
        ResultSet rs = stm.executeQuery("select nome_med from medico where id_med='"+id_med+"'");
        
        while(rs.next()){
            return rs.getString("nome_med");
        }
        return null;
    }
    
    public boolean excluir(int id_med) throws SQLException{
        try{
            stm.executeUpdate("delete from medico where id_med='"+id_med+"'");
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
}
