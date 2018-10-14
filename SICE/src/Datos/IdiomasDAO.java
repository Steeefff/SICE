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
/**
 *
 * @author fany-
 */
public class IdiomasDAO {
 
    Conexion conexion;
    
     public  IdiomasDAO(){
   
        conexion=new Conexion();
    }   
    
    
    public ArrayList<Idiomas> listarIdiomas(){          //metodo listar
       
         ArrayList listarIdiomas = new ArrayList();
        Idiomas tmp; //
        try{
            Connection acceDB = conexion.getConexion();
            PreparedStatement ps = acceDB.prepareStatement("SELECT * FROM sice.idiomas;");
             ResultSet rs = ps.executeQuery();
            while(rs.next()){ //si hay registros por leer entonces..
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
