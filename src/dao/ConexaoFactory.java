package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Rivaldo
 */

public class ConexaoFactory {
    private static Connection con;
    
public static Connection getConnection() throws ClassNotFoundException, SQLException{
       if(con==null){
      Class.forName("org.postgresql.Driver");
               String url="jdbc:postgresql://localhost:5432/Clinica2";
               String user="postgres";
               String pass="123";
               con= DriverManager.getConnection(url, user, pass);
               System.out.println("conectado!");
               return con;
       }else{
            return con;
       }
    }
    
}
