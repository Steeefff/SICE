package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/*
  @author Grupo #30 Ingeniería 2018-2019 
 *@author David Rodríguez Zamora
 *@author Katherine Jiménez Soto
 *@author Melany Monge Montero
 *@author Stefanny Villalobos Uva
 * Proyecto de Ingeniería - Universidad Nacional de Costa Rica
 * Sistema Interno de Control de Estudiantes, SICE
 * Profesor: Rafael Alvarado Arley
 * Dueño del producto: Yensy Soto, Centro Cultural Corporación Costa Rica
 * Versión 1.3, 17/03/2019
 * Since 1.0
 */
public class Conexion {
    
    private static Connection conexion;
    public static Statement st;
    
    /*public Conexion(){
        
    }*/
    //En agregar profesor usar Conexion en lugar de getConexion
    public Connection Conexion(){
        conexion = null;
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //Class.forName("com.mysql.jc.jdbc.Driver").newInstance(); //La de los que actualizan mysql
            conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/sice?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root"); //Tiene que llamarse igual a la instancia de mi server(Mysql)
            st=conexion.createStatement();
            System.out.println("CONECTADO");
        }catch(Exception e){
            e.printStackTrace();
        }
        return conexion;
    }

    public static Statement getSt() {
        return st;
    }
    
    
    public Connection getConexion(){
        return conexion;
    }
    
    public void desconectar(){
        conexion=null;
        if(conexion == null) System.out.println("La conexion a MySQL ha sido terminada.");
    }
}
