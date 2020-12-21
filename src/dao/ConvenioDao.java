/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Convenio;
/**
 *
 * @author Alves
 */

    public class ConvenioDao {
    
    private Connection con;
    private Statement stm;
    public ConvenioDao() {
        try {
            con=(Connection) ConexaoFactory.getConnection();
            stm=con.createStatement();
        } catch (ClassNotFoundException | SQLException e) { }
    }
    
    public boolean cadastrar_con(Convenio c){
       try{
            String nome = c.getNome_con();
            String cnpj = c.getCnpj_con();
      
            stm.executeUpdate("INSERT INTO convenio(nome_con, cnpj_con) VALUES('"+nome+"','"+cnpj+"')");
            return true;
        }catch(SQLException ex){
            System.out.println(ex.getErrorCode());
            return false;     
        }   
    }
    
    public boolean atualizar_con(Convenio c) throws SQLException{
        try{
            stm.executeUpdate("UPDATE convenio SET nome_con='"+c.getNome_con()+"', cnpj_con= '"+c.getCnpj_con()+"' WHERE id_con='"+c.getId_con()+"'");
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    public Convenio pesquisar_con(String cnpj) throws SQLException{
        
        ResultSet rs=stm.executeQuery("select * from convenio where cnpj_con='"+cnpj+"'");
        
        if(rs.next()){
            Convenio cn = new Convenio();
            cn.setNome_con(rs.getString("nome_con"));
            cn.setCnpj_con(rs.getString("cnpj_con"));
            cn.setId_con(rs.getInt("id_con"));
            
            return cn;
        }else{
            return null;
        }
    }
    
    public ArrayList<Convenio> lista_convenios() throws SQLException{
        
        ResultSet rs = stm.executeQuery("select * from convenio");
        ArrayList<Convenio> lista = new ArrayList<>();
        
        while(rs.next()){
            Convenio con = new Convenio();
            con.setNome_con(rs.getString("nome_con"));
            con.setCnpj_con(rs.getString("cnpj_con"));
            con.setId_con(rs.getInt("id_con"));
            
            lista.add(con);
        }
            return lista;
    }
    
    public boolean excluir_con(int id_con) throws SQLException{
    try{
        stm.executeUpdate("DELETE FROM convenio WHERE id_con='"+id_con+"'");
        return true;
        
        }catch(Exception ex){
            return false;
        }

    }
    }