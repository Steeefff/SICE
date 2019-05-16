/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelos.TipoPersonas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
  @author Grupo #30 Ingeniería 2018-2019 
 *@author David Rodríguez Zamora
 *@author Katherine Jiménez Soto
 *@author Melany Monge Montero
 *@author Stefanny Villalobos Uva
 * Proyecto de Ingeniería - Universidad Nacional de Costa Rica
 * Sistema Interno de Control de Estudiantes, SICE
 * Profesores: Rafael Alvarado Arley
               Pablo Gamboa Camacho
 * Dueño del producto: Yensy Soto, Centro Cultural Corporación Costa Rica
 * Versión 1.3, 17/03/2019
 * Since 1.0
 */
public class TipoPersonasDAO {
    
    private static Conexion conexion;
    public static ResultSet rs;
    public static Statement st;
    Connection accesoDB;
    PreparedStatement ps;
    TipoPersonas tipoPersona;
    
    public TipoPersonasDAO(Conexion conexion,ResultSet rs,Statement st){
        this.conexion=conexion;
        this.rs=rs;
        this.st=st;
        accesoDB = this.conexion.getConexion();
    }
 
    ///////////////////////////////////////////////////////////INSERTAR TIPO DE PERSONA///////////////////////////////////////////////////////////////////
   public String insertaTipoPersona (TipoPersonas tipoPersonasAux ) throws SQLException{
       String respuestaRegistro=null;
       if(this.buscarRegistro(tipoPersonasAux.getRol())==null){
               try{
                   ps = accesoDB.prepareStatement(
                       "INSERT INTO `sice`.`tipopersonas` (`rol`) VALUES (?);");
                   ps.setString(1, tipoPersonasAux.getRol());
                   int numFAfectadas = ps.executeUpdate(); //Toma el numero de filas afectadas
                   if(numFAfectadas>0){                     
                       respuestaRegistro="¡El tipo de persona "+tipoPersonasAux.getRol()+" ha sido guardado con éxito! ";
                   }else{
                       respuestaRegistro="Hubo un error al guardar el tipo de persona "+tipoPersonasAux.getRol()+". Intene de nuevo.";
                   }            
               }catch(Exception e){
                   JOptionPane.showMessageDialog(null, "Ha habido un error. Intente de nuevo.");
                   e.printStackTrace();
               }  
     }else{
           respuestaRegistro="El tipo de persona "+tipoPersonasAux.getRol()+" ya existe en el registro.";
       }
       return respuestaRegistro;
   }
   
   ///////////////////////////////////////////////////////////MODIFICAR TIPO DE PERSONA///////////////////////////////////////////////////////////////////
   public String modificarTipoPersona (TipoPersonas tipoPersonasAux, String buscado ) throws SQLException{
       String respuestaRegistro=null;
       if(this.buscarRegistro(tipoPersonasAux.getRol())==null){
               try{
                   ps = accesoDB.prepareStatement(
                       "UPDATE `sice`.`tipopersonas` SET rol='"+tipoPersonasAux.getRol()+"' WHERE rol='"+buscado+"'");
                   int numFAfectadas = ps.executeUpdate(); //Toma el numero de filas afectadas
                   if(numFAfectadas>0){                     
                       respuestaRegistro="¡El tipo de persona "+tipoPersonasAux.getRol()+" ha sido modificado con éxito! ";
                   }else{
                       respuestaRegistro="Hubo un error al modificar el tipo de persona "+tipoPersonasAux.getRol()+". Intene de nuevo.";
                   }            
               }catch(Exception e){
                   JOptionPane.showMessageDialog(null, "Ha habido un error. Intente de nuevo.");
                   e.printStackTrace();
               }  
     }else{
           respuestaRegistro="El tipo de persona "+tipoPersonasAux.getRol()+" ya existe en el registro.";
       }
       return respuestaRegistro;
   }
   
   //////////////////////////////// BUSCAR TIPO DE PERSONAS //////////////////////////////////////////////////////////////

    public TipoPersonas buscarRegistro(String rol) throws SQLException{
        TipoPersonas tipoPersona = null;
        String sql = "SELECT * FROM sice.tipopersonas WHERE rol='"+rol+"'";
        try {
            rs = st.executeQuery(sql);         
        }catch (Exception e) {
            System.out.println("Hubo un error");
            e.printStackTrace();
        }
        tipoPersona= asignar();
        return tipoPersona; 
    }
    
    //Asigna los datos de un registro de tipo tipoPersona a una instancia de tipo tipoPersona
    public TipoPersonas asignar(){
      TipoPersonas tipoPersona = null;
      String rol;
      try {
            if(rs.first()){
                rol= rs.getString("rol");//Rol
                tipoPersona= new TipoPersonas();
                tipoPersona.setRol(rol);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return tipoPersona;          
    }
    ////////////////////////////////MOSTRAR, BUSCAR GÉNEROS ////////////////////////////////////////////////////////////
    public DefaultTableModel mostrarBuscarTipoPersonaEnMantenimiento(String rol){
        DefaultTableModel modelo;
        String[] titulos = {"Código","Rol"};
        String[] registro = new String[2];
        modelo = new DefaultTableModel(null, titulos);
        
            try {                
                ps = accesoDB.prepareStatement("SELECT * FROM sice.tipopersonas WHERE rol LIKE '%"+rol+"%'");
                rs = ps.executeQuery();
                while (rs.next()) {
                    registro[0] = rs.getString(1);//Id tipoPersona
                    registro[1] = rs.getString(2);//rol
                    
                    modelo.addRow(registro);
                }
                return modelo;
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Ha habido un error al mostrar los tipos de personas.");
                e.printStackTrace();
            }
        return null;
    }
    ///////////////////////////////////////////////////LISTAR IDIOMAS /////////////////////////////////////////////////////////
    public ArrayList<TipoPersonas> listarTipoPersona(){          
        ArrayList listarTipoPersona = new ArrayList();
        TipoPersonas tipoPersona = null;
        try{
            PreparedStatement ps = accesoDB.prepareStatement("SELECT * FROM sice.tipopersonas;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){ //Si hay registros por leer entonces..

                listarTipoPersona.add(rs.getString(2));
            }
        }catch (Exception e){
            e.printStackTrace();

        }
         return listarTipoPersona;
    }
}
