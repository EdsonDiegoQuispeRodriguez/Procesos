
package komatsu.corp.procesos.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    Connection con;
    public Conexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_controlprocesos","root","");
            System.out.println("TODO BIEN");

        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    public Connection getConexion(){
        return con;
    }
}
