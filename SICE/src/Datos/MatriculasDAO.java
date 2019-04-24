/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelos.FechasDePago;
import Modelos.Matriculas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JOptionPane;

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
public class MatriculasDAO {
    private static Conexion conexion;
    public static ResultSet rs;
    public static Statement st;
    Connection accesoDB;
    PreparedStatement ps;
    FechasDePagoDAO fechasDePagoDAO;
    
    public MatriculasDAO(Conexion conexion,ResultSet rs,Statement st){
        this.conexion=conexion;
        this.rs=rs;
        this.st=st;
        accesoDB = this.conexion.getConexion();
    }
    
    public String fecha(){
        /*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaComoCadena = sdf.format(new Date());
        return fechaComoCadena;*/
        
       /* String date = request.getParameter("date");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy"); // your template here
        Date date=System.getProperties().
        java.util.Date dateStr = formatter.parse(date);
        java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());*/
       
       //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
       LocalDate localDate = LocalDate.now();
       String date=DateTimeFormatter.ofPattern("yyyy/MM/dd").format(localDate);
       return date;
    }
    
//////////////////////////////////////////////MATRICULAR/////////////////////
    public String matricular(Matriculas matricula,String fechaProxPago){
        String sql="INSERT INTO sice.matriculas (idEstudiante,nombreGrupo,fechaDeMatricula) VALUES (?, ?, ?)";
        String respuestaRegistro=null;
        fechasDePagoDAO = new FechasDePagoDAO(this.conexion,this.rs,this.st);
        try{
                ps = accesoDB.prepareStatement(sql);  
                ps.setString(1, matricula.getIdEstudiante());
                ps.setString(2, matricula.getNombreGrupo()); 
                ps.setString(3, this.fecha());
                int numFAfectadasCursos = ps.executeUpdate(); //Toma el numero de filas afectadas
                fechasDePagoDAO.insertaFecha(matricula.getIdEstudiante(),fechaProxPago);                
                
                if(numFAfectadasCursos>0){                     
                    respuestaRegistro="¡El registro de la matrícula del estudiante identificación "+matricula.getIdEstudiante()+" ha sido guardado con éxito! ";
                }else{
                    respuestaRegistro="Hubo un error al guardar el registro de la matrícula del estudiante identificación "+matricula.getIdEstudiante()+". Intente de nuevo.";
                }            
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Ha habido un error. Intente de nuevo.");
                e.printStackTrace();
            }
        return respuestaRegistro;
    }
}
