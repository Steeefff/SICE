/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;
import Modelos.Idiomas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class IdiomasDAO {
 
    Conexion conexion;
    
    public  IdiomasDAO(){
   
        conexion=new Conexion();
    }   
    
    ///////////////////////////////////////////////////LISTAR IDIOMAS /////////////////////////////////////////////////////////
    public ArrayList<Idiomas> listarIdiomas(){          
       
        ArrayList listarIdiomas = new ArrayList();
        Idiomas tmp; // Variable Temporal
        try{
            Connection acceDB = conexion.getConexion();
            PreparedStatement ps = acceDB.prepareStatement("SELECT * FROM sice.idiomas;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){ //Si hay registros por leer entonces..
                tmp = new Idiomas();
                
                tmp.setIdIdioma(rs.getInt(1));
                tmp.setNombre(rs.getString(2));
           
                listarIdiomas.add(tmp);
            }
        }catch (Exception e){
            e.printStackTrace();

        }
         return listarIdiomas;
    }
}
