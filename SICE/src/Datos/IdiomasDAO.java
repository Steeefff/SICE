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
    public String insertarPersona (Idiomas i){
       String respuestaRegistro=null;
        try{
            Connection accesoDB = conexion.getConexion();
            PreparedStatement ps = accesoDB.prepareStatement("INSERT INTO `idiomas`(`idIdioma`, `nombre`) "
                    + "VALUES (?);"
            );
            ps.setString(1, i.getNombre()); 
 
           
            int numFAfectadas = ps.executeUpdate(); //toma el numero de filas afectadas
           
            if(numFAfectadas>0){
                respuestaRegistro="Idioma insertado con Exito! ";
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return respuestaRegistro;
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
