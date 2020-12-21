package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Consulta;

/**
 *
 * @author Bianca Lopes
 */
public class ConsultaDao {
    private Connection con;
    private Statement stm;
    
    public ConsultaDao() {
        try {
            con=(Connection) ConexaoFactory.getConnection();
            stm=con.createStatement();
        } catch (ClassNotFoundException | SQLException e) { }
    }
    
    public boolean cadastrar_cons(Consulta cons){
       try{
            int id_medico = cons.getId_medico();
            int id_paciente  = cons.getId_paciente();
            String horario_inicio = cons.getHorario_inicio();
            String data  = cons.getData();
            String horario_termino = cons.getHorario_termino();
            String descricao = cons.getDescricao();
            String stts ="Pendente";
            String diag = "Em andamento";
            
            stm.executeUpdate("insert into consulta(medico_id,paciente_id,horario_inicio,horario_termino,consulta_data,descricao,estado, diagnostico) values('"+id_medico+"','"+id_paciente+"','"+horario_inicio+"','"+horario_termino+"','"+data+"','"+descricao+"', '"+stts+"','"+diag+"')");
            return true;
        }catch(SQLException ex){
            System.out.println(ex.getErrorCode());
            return false;     
        }   
    }
    
    public boolean realizar_con(Consulta con){
        try{
            int id = con.getConsulta_id();
            String dig = con.getDiagnostico();
            String stts = "Realizada";
            
            stm.executeUpdate("UPDATE consulta SET diagnostico='"+dig+"', estado='"+stts+"' WHERE consulta_id='"+id+"';");
            return true;
            
        }catch(SQLException ex){
            System.out.println(ex.getErrorCode());
            return false;    
        }
    }
    
    public ArrayList<Consulta> lista_consultas(int id_med) throws SQLException{
        
        ResultSet medico = stm.executeQuery("select * from medico where id_med='"+id_med+"'");
        if(medico.next()){
            ResultSet rs = stm.executeQuery("select * from consulta where medico_id='"+id_med+"'");
            ArrayList<Consulta> lista = new ArrayList<>();

            while(rs.next()){
                Consulta con = new Consulta();            

                con.setConsulta_id(rs.getInt("consulta_id"));
                con.setId_medico(rs.getInt("medico_id"));
                con.setId_paciente(rs.getInt("paciente_id"));
                con.setData(rs.getString("consulta_data"));
                con.setHorario_inicio(rs.getString("horario_inicio"));
                con.setHorario_termino(rs.getString("horario_termino"));
                con.setDescricao(rs.getString("descricao"));

                lista.add(con);
            }
            return lista;
        }else{
            return null;
        }
    }
    
    public ArrayList<Consulta> lista_consultasCpf(String cpf) throws SQLException{
        
        ResultSet medico = stm.executeQuery("select * from medico where cpf_med='"+cpf+"'");
        
        if(medico.next()){
            int id_med = medico.getInt("id_med");
            ResultSet rs = stm.executeQuery("select * from consulta where medico_id='"+id_med+"'");
            ArrayList<Consulta> lista = new ArrayList<>();

            while(rs.next()){
                Consulta con = new Consulta();            

                con.setConsulta_id(rs.getInt("consulta_id"));
                con.setId_medico(rs.getInt("medico_id"));
                con.setId_paciente(rs.getInt("paciente_id"));
                con.setData(rs.getString("consulta_data"));
                con.setHorario_inicio(rs.getString("horario_inicio"));
                con.setHorario_termino(rs.getString("horario_termino"));

                lista.add(con);
            }
            return lista;
        }else{
            return null;
        }
    }
    
    public ArrayList<Consulta> lista_consultasCrm(String crm) throws SQLException{
        
        ResultSet medico = stm.executeQuery("select * from medico where crm_med='"+crm+"'");
        
        if(medico.next()){
            int id_med = medico.getInt("id_med");
            ResultSet rs = stm.executeQuery("select * from consulta where medico_id='"+id_med+"'");
            ArrayList<Consulta> lista = new ArrayList<>();

            while(rs.next()){
                Consulta con = new Consulta();            

                con.setConsulta_id(rs.getInt("consulta_id"));
                con.setId_paciente(rs.getInt("paciente_id"));
                con.setId_medico(rs.getInt("medico_id"));
                con.setData(rs.getString("consulta_data"));
                con.setHorario_inicio(rs.getString("horario_inicio"));
                con.setHorario_termino(rs.getString("horario_termino"));

                lista.add(con);
            }
            return lista;
        }else{
            return null;
        }
    }
    
    public ArrayList<Consulta> lista_consultasData(String data) throws SQLException{
        
        ResultSet d = stm.executeQuery("select * from consulta where consulta_data ='"+data+"'");
        if(d.next()){
            ResultSet dat = stm.executeQuery("select * from consulta where consulta_data ='"+data+"'");
            ArrayList<Consulta> lista = new ArrayList<>();

            while(dat.next()){
                Consulta con = new Consulta();            

                con.setConsulta_id(dat.getInt("consulta_id"));
                con.setId_medico(dat.getInt("medico_id"));
                con.setId_paciente(dat.getInt("paciente_id"));
                con.setData(dat.getString("consulta_data"));
                con.setHorario_inicio(dat.getString("horario_inicio"));
                con.setHorario_termino(dat.getString("horario_termino"));

                lista.add(con);
            }
            return lista;
        }else{
            return null;
        }
    }
    
    /*public Consulta pesquisar(String nome_paciente) throws SQLException{
        
        ResultSet rs=stm.executeQuery("select * from consulta where nome_paciente='"+nome_paciente+"'");
        
        if(rs.next()){
            Consulta cons = new Consulta();
            cons.setId_paciente(rs.getInt("id_paciente"));
            cons.setConsulta_id(rs.getInt("consulta_id"));
            cons.setId_medico(rs.getInt("id_medico"));
            cons.setHorario_inicio(rs.getString("horario_inicio"));
            cons.setData(rs.getString("data"));
            cons.setHorario_termino(rs.getString("horario_termino"));
            cons.setDescricao(rs.getString("descricao"));
            
            return cons;
        }else{
            return null;
        }
    }*/
    
    //public boolean atualizar(Consulta cons) throws SQLException{
        //try{
            //stm.executeUpdate("update consulta set nome_paciente = '"+cons.getId_paciente()+"', nome_med='"+cons.getNome_med()+"', horario_inicio='"+cons.getHorario_inicio()+"',horario_termino='"+cons.getHorario_termino()+"', data='"+cons.getData()+"' where codigo='"+cons.getCodigo()+"'");
            //return true;
        //}catch(Exception ex){
            //return false;
        //}
    //}
    
    /*public boolean cancelar(int id) throws SQLException{
        try{
            stm.executeUpdate("delete from consulta where consulta_id='"+id+"'");
            return true;
        }catch(Exception ex){
            return false;
        }
    }*/
}
