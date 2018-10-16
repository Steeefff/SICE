/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelos.Personas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fany-
 */
public class PersonasDAO {
    
    Conexion conexion;
    
     public  PersonasDAO(){
   
   conexion=new Conexion();
   }   
    
   ///////////////////////////////////////////////////////////INSERTAR PROFESOR///////////////////////////////////////////////////////////////////
   public String insertarPersona (Personas p){
       String respuestaRegistro=null;
        try{
            Connection accesoDB = conexion.getConexion();
            PreparedStatement ps = accesoDB.prepareStatement(
            "INSERT INTO `sice`.`personas` (`cedula`, `nombre`, `apellido1`, `apellido2`, `telefono`, `genero`, `direccion`, `fechaNacimiento`, `correo`, `contraseña`, `idTipoPersona`,`idioma`) "
            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?);"
            
            );
            ps.setInt(1, p.getCedula()); 
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getApellido1());
            ps.setString(4, p.getApellido2());
            ps.setInt(5, p.getTelefono());
            ps.setInt(6, p.getGenero());
            ps.setString(7, p.getDireccion());
            ps.setString(8, p.getFechaNacimiento());
            ps.setString(9, p.getCorreo());
            ps.setString(10, p.getContraseña());
            ps.setInt(11, p.getIdTipoPersona());
            ps.setInt(12, p.getIdioma());
           
            int numFAfectadas = ps.executeUpdate(); //toma el numero de filas afectadas
           
            if(numFAfectadas>0){
                respuestaRegistro="Profesor Insertado con Exito! ";
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return respuestaRegistro;
   }
   
   ////////////////////////////////////////////////////////////LISTAR PROFESOR/////////////////////////////////////////////////////////7
   
    public ArrayList<Personas> listarPersonas(){          //metodo listar
       
        ArrayList listarPersonas = new ArrayList();
        Personas tmp; //
        try{
            Connection acceDB = conexion.getConexion();
            PreparedStatement ps = acceDB.prepareStatement("SELECT * FROM sice.personas;");
             ResultSet rs = ps.executeQuery();
            while(rs.next()){ //si hay registros por leer entonces..
                tmp = new Personas();
                
                tmp.setCedula(rs.getInt(1));
                tmp.setNombre(rs.getString(2));
                tmp.setApellido1(rs.getString(3));
                tmp.setApellido2(rs.getString(4));
                tmp.setTelefono(rs.getInt(5));
                tmp.setGenero(rs.getInt(6));
                tmp.setDireccion(rs.getString(7));
                tmp.setFechaNacimiento(rs.getString(8));
                tmp.setCorreo(rs.getString(9));
                tmp.setContraseña(rs.getString(10));
                tmp.setIdTipoPersona(rs.getInt(11));
                tmp.setIdioma(rs.getInt(12));
                tmp.setHabilitado(rs.getInt(13));
                
              listarPersonas.add(tmp);
            }
            
        }catch (Exception e){
            e.printStackTrace();

        }
         return listarPersonas;
    }
    
    /////////////////////////////////////////////////// EDITAR PROFESOR///////////////////////////////////////////////////////////
    //UPDATE `sice`.`personas` SET `cedula`='4021904497', `nombre`='StFefanny', `apellido1`='VillaloFbos', `apellido2`='UFva', `telefono`='874453715', `genero`='1', `direccion`='San RafaFel de Alajuela', `fechaNacimiento`='15/06/9995', `correo`='fany-0315@hotmail.com', `contraseña`='',`idTipoPersona`='1', `idioma`='2' WHERE `cedula`='402190497';
   
    public String editarPersona (Personas p){
       String respuestaRegistro=null;
        try{
            Connection accesoDB = conexion.getConexion();
            PreparedStatement ps = accesoDB.prepareStatement(
            "UPDATE INTO personas set nombre=?,apellido1=?,apellido2=?,telefono=?,genero=?,direccion=?,"
            + "fechaNacimiento=?,correo=?,contraseña=?,idTipoPersona=?,idioma=? WHERE cedula=?");
            
            ps.setInt(1, p.getCedula()); 
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getApellido1());
            ps.setString(4, p.getApellido2());
            ps.setInt(5, p.getTelefono());
            ps.setInt(6, p.getGenero());
            ps.setString(7, p.getDireccion());
            ps.setString(8, p.getFechaNacimiento());
            ps.setString(9, p.getCorreo());
            ps.setString(10, p.getContraseña());
            ps.setInt(11, p.getIdTipoPersona());
            ps.setInt(12, p.getIdioma());
           
            int numFAfectadas = ps.executeUpdate(); //toma el numero de filas afectadas
           
            if(numFAfectadas>0){
                respuestaRegistro="Profesor Insertado con Exito! ";
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return respuestaRegistro;
   }
    /////////////////////////////////////////////////MOSTRAR////////////////////////////////////////////////////////////////
    
    
    /////////////////////////////////////////////////////BUSCAR////////////////////////////////////////////////////////////////
    
    
    ////////////////////////////////DESHABILITAR ES COMO UN UPDATE///////////////////////////////////////////////////
    
     ///////////////////////////////////////VALIDAR PERSONA/////////////////////////////////////////////////////////////////////
    public Boolean validarPersona(int id_per) {
        try {
           Connection accesoDB = conexion.getConexion();
           PreparedStatement ps = accesoDB.prepareStatement("SELECT cedula FROM sice.personas where cedula=?");
           ps.setInt(1, id_per); 
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
               return false;
            }
            else
                return true;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }
}
