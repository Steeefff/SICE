/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelos.Generos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
 * Versión 1.2, 21/10/2018
 * Since 1.0
 */
public class GenerosDAO {
 
    private static Conexion conexion;
    public static ResultSet rs;
    public static Statement st;
    Connection accesoDB;
    ArrayList<String> generos = new ArrayList<String> ();
    
     public  GenerosDAO(Conexion conexion,ResultSet rs,Statement st){
        this.conexion=conexion;
        this.rs=rs;
        this.st=st;
        accesoDB = this.conexion.getConexion();
    }   
     
     /////////////////////////////////////////////// LISTAR GENEROS ///////////////////////////////////////////////////////////
    public ArrayList<Generos> listarGeneros(){          
       
        ArrayList listarGeneros = new ArrayList();
        Generos tmp; // Variable Temporal 
        try{
            PreparedStatement ps = accesoDB.prepareStatement("SELECT * FROM sice.generos;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){ //Si hay registros por leer entonces..
                tmp = new Generos();
                
                tmp.setIdGenero(rs.getInt(1));
                tmp.setNombre(rs.getString(2));
           
                listarGeneros.add(tmp);
            }
        }catch (Exception e){
            e.printStackTrace();

        }
         return listarGeneros;
    }
}
