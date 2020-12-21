  
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Paciente;

/**
 *
 * @author Rivaldo
 */

public class PacienteDao {
    private Connection con;
    private Statement stm;
    
    public PacienteDao() {
        try {
            con=(Connection) ConexaoFactory.getConnection();
            stm=con.createStatement();
        } catch (ClassNotFoundException | SQLException e) { }
    }
    
    public boolean cadastrar_pac(Paciente pac){
       try{
            String cpf = pac.getCpf_paciente();
            String nome  = pac.getNome_paciente();
            String rg = pac.getRg_paciente();
         
            stm.executeUpdate("insert into paciente(cpf_paciente,nome_paciente,rg_paciente) values('"+cpf+"','"+nome+"','"+rg+"')");
            return true;
        }catch(SQLException ex){
            System.out.println(ex.getErrorCode());
            return false;     
        }   
    }
    
    public Paciente pesquisar(String cpf) throws SQLException{
        
        ResultSet rs=stm.executeQuery("select * from paciente where cpf_paciente='"+cpf+"'");
        
        if(rs.next()){
            Paciente pac = new Paciente();
            pac.setNome_paciente(rs.getString("nome_paciente"));
            pac.setId_paciente(rs.getInt("id_paciente"));
            pac.setCpf_paciente(rs.getString("cpf_paciente"));
            pac.setRg_paciente(rs.getString("rg_paciente"));
            
            return pac;
        }else{
            return null;
        }
    }
    
    public ArrayList<Paciente> lista_pacientes() throws SQLException{
        
        ResultSet rs = stm.executeQuery("select * from paciente");
        ArrayList<Paciente> lista = new ArrayList<>();
        
        while(rs.next()){
            Paciente pac = new Paciente();
            pac.setCpf_paciente(rs.getString("cpf_paciente"));
            pac.setNome_paciente(rs.getString("nome_paciente"));
            pac.setRg_paciente(rs.getString("rg_paciente"));
            pac.setId_paciente(rs.getInt("id_paciente"));
            
            lista.add(pac);
        }
            return lista;
    }
    
    public boolean atualizar(Paciente pac) throws SQLException{
        try{
            stm.executeUpdate("update paciente set rg_paciente = '"+pac.getRg_paciente()+"', cpf_paciente='"+pac.getCpf_paciente()+"', nome_paciente='"+pac.getNome_paciente()+"' where id_paciente='"+pac.getId_paciente()+"'");
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    public boolean excluir(int id_pac) throws SQLException{
        try{
            stm.executeUpdate("delete from paciente where id_paciente='"+id_pac+"'");
            return true;
        }catch(Exception ex){
            return false;
        }
    }
}
