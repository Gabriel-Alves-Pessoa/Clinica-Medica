package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Compromisso;

/**
 *
 * @author Rivaldo
 * @author Alves 
 */
public class CompromissoDao {
    private Connection con;
    private Statement stm;
    
    public CompromissoDao() {
        try {
            con=(Connection) ConexaoFactory.getConnection();
            stm=con.createStatement();
        } catch (ClassNotFoundException | SQLException e) { }
    }
    
    public boolean cadastrar_comp(Compromisso c){
       try{
            int id_medico = c.getId_med();
            String horario_inicio = c.getHora_i_comp();
            String data  = c.getData_comp();
            String horario_termino = c.getHora_t_comp();
            String descricao = c.getDescricao_comp();
            String stts = c.getEstado();
            
            System.out.println("Ok");
            
            stm.executeUpdate("insert into compromisso(id_med, hora_i_comp, hora_t_comp, data_comp, descricao_comp, estado) values ('"+id_medico+"','"+horario_inicio+"','"+horario_termino+"','"+data+"','"+descricao+"', '"+stts+"')");
            return true;
        }catch(SQLException ex){
            System.out.println(ex.getErrorCode());
            return false;     
        }   
    }
    
    
    public ArrayList<Compromisso> lista_compromissos(int id_med) throws SQLException{
         ResultSet medico = stm.executeQuery("select * from medico where id_med='"+id_med+"'");
        
        if(medico.next()){
            ResultSet rs = stm.executeQuery("select * from compromisso where id_med='"+id_med+"'");
            ArrayList<Compromisso> lista = new ArrayList<>();

            while(rs.next()){
                Compromisso comp = new Compromisso();            

                comp.setId_comp(rs.getInt("id_comp"));
                comp.setId_med(rs.getInt("id_med"));
                comp.setData_comp(rs.getString("data_comp"));
                comp.setDescricao_comp(rs.getString("descricao_comp"));
                comp.setHora_i_comp(rs.getString("hora_i_comp"));
                comp.setHora_t_comp(rs.getString("hora_t_comp"));
                comp.setEstado(rs.getString("estado"));

                lista.add(comp);
            }
            return lista;
        }else{
            return null;
        }
    }
}
