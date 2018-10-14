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
import java.util.ArrayList;

/**
 *
 * @author fany-
 */
public class GenerosDAO {
 
    Conexion conexion;
    
     public  GenerosDAO(){
   
        conexion=new Conexion();
    }   
    
    
    public ArrayList<Generos> listarGeneros(){          //metodo listar
       
         ArrayList listarGeneros = new ArrayList();
        Generos tmp; //
        try{
            Connection acceDB = conexion.getConexion();
            PreparedStatement ps = acceDB.prepareStatement("SELECT * FROM sice.genero;");
             ResultSet rs = ps.executeQuery();
            while(rs.next()){ //si hay registros por leer entonces..
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
