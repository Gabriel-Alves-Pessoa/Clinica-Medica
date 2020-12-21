package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Usuario;

/**
 *
 * @author Rivaldo
 */

public class LoginDao {
    
    private Connection con;
    private Statement stm; 

    public LoginDao() {
        
        try {
            con=(Connection) ConexaoFactory.getConnection();
            stm=con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
           
        }
    }
    
    public boolean cadastrar_login(Usuario lg){
       try{
         String l = lg.getLogin();
         String p = lg.getPassword();
         String cpf = lg.getCpf();
         String nome  = lg.getNome();
         stm.executeUpdate("insert into usuario(login,senha,cpf,nome) values('"+l+"','"+p+"','"+cpf+"','"+nome+"')");
         return true;
         }catch(SQLException ex){
             System.out.println(ex.getErrorCode());
          return false;     
         }
    
    }
    
    public boolean verificar_login(Usuario lg) 
            throws SQLException{
         String l=lg.getLogin();
         String s=lg.getPassword();
         ResultSet rs=stm.executeQuery
        ("select * from usuario where "
         + "login='"+l+"' and senha='"+s+"'");
         if(rs.next()){
             return true;
         }else{
             return false;
         }
    }
    
    public boolean atualizar(Usuario us) throws SQLException{
        try{
            stm.executeUpdate("update usuario set login='"+us.getLogin()+"', senha='"+us.getPassword()+"', cpf='"+us.getCpf()+"', nome='"+us.getNome()+"' where id_usuario='"+us.getId_usuario()+"'");
            return true;
        }catch(Exception ex){
            return false;
        }
    }
      
      
    public boolean excluir(int id_usuario) throws SQLException{
        try{
            stm.executeUpdate("delete from usuario where id_usuario='"+id_usuario+"'");
        return true;
        }catch(Exception ex){
            return false;
        }
    }
     
    public Usuario pesquisar(String cpf) throws SQLException{
        
        ResultSet rs=stm.executeQuery("select * from usuario where cpf='"+cpf+"'");
        
        if(rs.next()){
            Usuario us=new Usuario();
            us.setNome(rs.getString("nome"));
            us.setId_usuario(rs.getInt("id_usuario"));
            us.setCpf(rs.getString("cpf"));
            us.setLogin(rs.getString("login"));
            us.setPassword(rs.getString("senha"));
            return us;
        }else{
            return null;
        }
    }
    public ArrayList<Usuario> lista_usuarios() throws SQLException{
        
        ResultSet rs = stm.executeQuery("select * from usuario");
        ArrayList<Usuario> lista = new ArrayList<>();
        
        while(rs.next()){
            Usuario us = new Usuario();
            us.setCpf(rs.getString("cpf"));
            us.setNome(rs.getString("nome"));
            us.setLogin(rs.getString("login"));
            us.setPassword(rs.getString("senha"));
            us.setId_usuario(rs.getInt("id_usuario"));
            
            lista.add(us);
        }
            return lista;
    }
    
}
