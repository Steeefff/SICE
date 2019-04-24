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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
    PreparedStatement ps;
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
                tmp = new Generos(conexion, rs, st);
                tmp.setNombre(rs.getString(2));
           
                listarGeneros.add(tmp);
            }
        }catch (Exception e){
            e.printStackTrace();

        }
         return listarGeneros;
    }
    
    public void deshabiliar(String nombre){       
       try{
            st.executeUpdate("UPDATE sice.generos SET estado=0 WHERE nombre='"+nombre+"'");
        }
        catch (Exception e){//En caso de error
            JOptionPane.showMessageDialog(null,"Ha habido un error.");
            e.printStackTrace();
        }
    }
    
    public void habilitar(String nombre){       
       try{
            st.executeUpdate("UPDATE sice.generos SET estado=1 WHERE nombre='"+nombre+"'");
        }
        catch (Exception e){//En caso de error
            JOptionPane.showMessageDialog(null,"Ha habido un error.");
            e.printStackTrace();
        }
    }
    
    ///////////////////////////////////////////////////////////INSERTAR GENERO///////////////////////////////////////////////////////////////////
   public String insertaGenero (Generos generoAux) throws SQLException{
       String respuestaRegistro=null;
       if(this.buscarRegistro(generoAux.getNombre())==null){
               try{
                   ps = accesoDB.prepareStatement(
                       "INSERT INTO `sice`.`generos` (`nombre`,`estado`) VALUES (?,?);");
                   ps.setString(1, generoAux.getNombre());
                   ps.setInt(2, 1);
                   int numFAfectadas = ps.executeUpdate(); //Toma el numero de filas afectadas
                   if(numFAfectadas>0){                     
                       respuestaRegistro="¡El género "+generoAux.getNombre()+" ha sido guardado con éxito! ";
                   }else{
                       respuestaRegistro="Hubo un error al guardar el género "+generoAux.getNombre()+". Intene de nuevo.";
                   }            
               }catch(Exception e){
                   JOptionPane.showMessageDialog(null, "Ha habido un error. Intente de nuevo.");
                   e.printStackTrace();
               }  
     }else{
           respuestaRegistro="El género "+generoAux.getNombre()+" ya existe en el registro.";
       }
       return respuestaRegistro;
   }
   
   //////////////////////////////// BUSCAR GENERO //////////////////////////////////////////////////////////////

    public Generos buscarRegistro(String nombre) throws SQLException{
        Generos genero = null;
        String sql = "SELECT * FROM sice.generos WHERE nombre='"+nombre+"'";
        try {
            rs = st.executeQuery(sql);         
        }catch (Exception e) {
            System.out.println("Hubo un error");
            e.printStackTrace();
        }
        genero= asignar();
        return genero; 
    }
    
    //Asigna los datos de un registro de tipo generos a una instancia de tipo Generos
    public Generos asignar(){
      Generos genero = null;
      String nombre;
      try {
            if(rs.first()){
                nombre= rs.getString("nombre");//Nombre
                genero= new Generos(this.conexion,this.rs,this.st);
                genero.setNombre(nombre);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return genero;          
    }
    
    ////////////////////////////////MOSTRAR, BUSCAR GÉNEROS ////////////////////////////////////////////////////////////
    public DefaultTableModel mostrarBuscarGenerosEnMantenimiento(String genero){
        DefaultTableModel modelo;
        String[] titulos = {"Código","Género","Estado"};
        String[] registro = new String[3];
        modelo = new DefaultTableModel(null, titulos);
        String estados[] = {"Deshabilitado","Habilitado"};
        
            try {                
                ps = accesoDB.prepareStatement("SELECT * FROM sice.generos WHERE idGenero LIKE '%"+genero+"%' OR nombre LIKE '%"+genero+"%'");
                rs = ps.executeQuery();
                while (rs.next()) {
                    registro[0] = rs.getString(1);//Id genero
                    registro[1] = rs.getString(2);//Nombre
                    registro[2] = estados[rs.getInt(4)];//Estado
                                        
                    modelo.addRow(registro);
                }
                return modelo;
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Ha habido un error al mostrar los géneros.");
                e.printStackTrace();
            }
        return null;
    }
    
    ///////////////////////////////////////////////////////////MODIFICAR GENERO///////////////////////////////////////////////////////////////////
   public String modificarGenero (Generos generosAux, String buscado ) throws SQLException{
       String respuestaRegistro=null;
       if(this.buscarRegistro(generosAux.getNombre())==null){
               try{
                   ps = accesoDB.prepareStatement(
                       "UPDATE `sice`.`generos` SET nombre='"+generosAux.getNombre()+"' WHERE nombre='"+buscado+"'");
                   int numFAfectadas = ps.executeUpdate(); //Toma el numero de filas afectadas
                   if(numFAfectadas>0){                     
                       respuestaRegistro="¡El género "+generosAux.getNombre()+" ha sido modificado con éxito! ";
                   }else{
                       respuestaRegistro="Hubo un error al modificar el género "+generosAux.getNombre()+". Intene de nuevo.";
                   }            
               }catch(Exception e){
                   JOptionPane.showMessageDialog(null, "Ha habido un error. Intente de nuevo.");
                   e.printStackTrace();
               }  
     }else{
           respuestaRegistro="El género "+generosAux.getNombre()+" ya existe en el registro.";
       }
       return respuestaRegistro;
   }
}
