
package Datos;
import Modelos.pagos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
 * Profesor: Rafael Alvarado Arley
             Pablo Gamboa Camacho
 * Dueño del producto: Yensy Soto, Centro Cultural Corporación Costa Rica
 * Versión 1.3, 17/03/2019
 * Since 1.0
 */

public class PagosDAO {
    
    private static Conexion conexion;
    public static ResultSet rs;
    public static Statement st;
    Connection accesoDB;
    PreparedStatement ps;
    
    public PagosDAO(Conexion conexion,ResultSet rs,Statement st){
        this.conexion=conexion;
        this.rs=rs;
        this.st=st;
        accesoDB = this.conexion.getConexion();
    }
    
    ////////////////////////////////LISTAR PAGOS ////////////////////////////////////////////////////////////
    public DefaultTableModel mostrarPagos(String buscar){
        DefaultTableModel modelo;
        String[] titulos = {"Identificación", "Número de recibo", "Observación", "Mes","Año"};
        String[] registro = new String[5];
        modelo = new DefaultTableModel(null, titulos);

        try {
            ps = accesoDB.prepareStatement("SELECT * FROM sice.vista_pagos WHERE idEstudiante='"+buscar+"'");
            rs = ps.executeQuery();
            while (rs.next()) {
                registro[0] = rs.getString(1);//identificacion
                registro[1] = rs.getString(5);//Número de recibo
                registro[2] = rs.getString(6);//Observación
                registro[3] = rs.getString(7);//Mes
                registro[4] = rs.getString(8); //Año   
                modelo.addRow(registro);
            }
            return modelo;
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ha habido un error al mostrar recibos. Si el error persiste contacte a su equipo de TI.");
            e.printStackTrace();
        }
        return null;
    }
    
    ////////////////////////////////INSERTAR PAGOS ////////////////////////////////////////////////////////////
    public String insertarPago(pagos pago){
        String respuestaRegistro=null;
        
        try{
            ps = accesoDB.prepareStatement("INSERT INTO sice.pagos (idEstudiante,numeroRecibo,observacion,mes,anio) VALUES (?, ?, ?, ?, ?)");  
                ps.setString(1, pago.getIdEstudiante());
                ps.setString(2, pago.getNumeroRecibo());
                ps.setString(3, pago.getObservacion());
                ps.setString(4, pago.getMes());
                ps.setString(5, pago.getAnio());
                int numFAfectadasCursos = ps.executeUpdate(); //Toma el numero de filas afectadas              
                
                if(numFAfectadasCursos>0){                     
                    respuestaRegistro="¡El registro con el número de recibo "+pago.getNumeroRecibo()+"para el estudiante identificación "+pago.getIdEstudiante()+" ha sido guardado con éxito! ";
                }else{
                    respuestaRegistro="Hubo un error al guardar el registro del estudiante identificación "+pago.getIdEstudiante()+". Intente de nuevo.";
                }            
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Ha habido un error. Intente de nuevo.");
                e.printStackTrace();
            }
      return respuestaRegistro;
    }
    
}
