/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author fany-
 */
public class Conexion {
    public Conexion(){
        
    }
    
    public Connection getConexion(){
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sice","root",""); //tiene que llamarse igual a la instancia de mi server(Mysql)
            System.out.println("CONECTADO");
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
