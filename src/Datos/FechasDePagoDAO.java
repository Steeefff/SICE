/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

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
public class FechasDePagoDAO {
    
    private static Conexion conexion;
    public static ResultSet rs;
    public static Statement st;
    Connection accesoDB;
    PreparedStatement ps;
    
    public FechasDePagoDAO(Conexion conexion,ResultSet rs,Statement st){
        this.conexion=conexion;
        this.rs=rs;
        this.st=st;
        accesoDB = this.conexion.getConexion();
    }
    
    public String proximaFechaDePago(String idEstudiante) throws SQLException{
            String fecha=null;
            String sql="SELECT MAX(fechaProxPago) FROM sice.fechasdepago WHERE idEstudiante='"+idEstudiante+"'";
            ps = accesoDB.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.first())
                fecha=rs.getString(1);
            return fecha;
    }
    
    public void insertaFecha(String idEstudiante,String fechaDePago){
        String sql="INSERT INTO sice.fechasdepago (idEstudiante,fechaDePago) VALUES (?,?)";
        try{
            ps = accesoDB.prepareStatement(sql);  
                ps.setString(1, idEstudiante);
                ps.setString(2, fechaDePago);                         
                int numFAfectadasCursos =ps.executeUpdate();                
                
                if(numFAfectadasCursos>0){                     
                    System.out.println("¡El registro fecha de pago del estudiante "+idEstudiante+" ha sido guardado con éxito!");
                }else{
                    System.out.println("Hubo un error al guardar el registro fecha de pago del del estudiante "+idEstudiante+". Intente de nuevo.");
                }            
            }catch(Exception e){
                System.out.println("Ha habido un error. Intente de nuevo.");
                e.printStackTrace();
            }
    }
}
