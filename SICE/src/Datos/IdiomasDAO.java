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
public class IdiomasDAO {
 
    private static Conexion conexion;
    public static ResultSet rs;
    public static Statement st;
    Connection accesoDB;
    
    public  IdiomasDAO(Conexion conexion,ResultSet rs,Statement st){
        this.conexion=conexion;
        this.rs=rs;
        this.st=st;
        accesoDB = this.conexion.getConexion();
    }   
    
    ///////////////////////////////////////////////////LISTAR IDIOMAS /////////////////////////////////////////////////////////
    public ArrayList<Idiomas> listarIdiomas(){          
       
        ArrayList listarIdiomas = new ArrayList();
        Idiomas tmp; // Variable Temporal
        try{
            PreparedStatement ps = accesoDB.prepareStatement("SELECT * FROM sice.idiomas;");
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
    
    public int cantidadIdiomasPersona(String identificacion){
        int cantidadIdiomas = 0;
        
        try {
            PreparedStatement ps = accesoDB.prepareStatement("SELECT COUNT(*) FROM sice.idiomasprofesor WHERE identificacion = '"+identificacion+"'");
            rs = ps.executeQuery();
            while(rs.next()){
                cantidadIdiomas = rs.getInt(1);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cantidadIdiomas;
    }
    
    public int[]  vectorIdiomasPersona(String identificacion){
        int[] Idioma = new int[cantidadIdiomasPersona(identificacion)];
        System.out.println("Cant idiomas profesor: "+cantidadIdiomasPersona(identificacion));
        
        try{
            PreparedStatement ps = accesoDB.prepareStatement("SELECT idIdioma FROM idiomasprofesor WHERE identificacion ='"+identificacion+"'");
            ps.executeQuery();
            int i=0;
            rs = ps.executeQuery();
            while(rs.next()){
                Idioma[i] = ((rs.getInt("idIdioma"))+1);
               // System.out.println("Agregando ID:"+rs.getInt("idIdioma"));
                i++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return Idioma;
    }
}
