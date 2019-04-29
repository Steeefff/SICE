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
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
public class MatriculasDAO {
    private static Conexion conexion;
    public static ResultSet rs;
    public static Statement st;
    Connection accesoDB;
    PreparedStatement ps;
    
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
    public String matricular(Matriculas matricula){
        String sql="INSERT INTO sice.matriculas (idEstudiante,nombreGrupo,fechaDeMatricula,fechaProxPago) VALUES (?, ?, ?, ?)";
        String respuestaRegistro=null;
        try{
                ps = accesoDB.prepareStatement(sql);  
                ps.setString(1, matricula.getIdEstudiante());
                ps.setString(2, matricula.getNombreGrupo()); 
                ps.setString(3, this.fecha());
                ps.setString(4,matricula.getFechaProxPago());
                int numFAfectadasCursos = ps.executeUpdate(); //Toma el numero de filas afectadas               
                
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
    
    ////////////////////////////////MOSTRAR MATRICULAS POR ESTUDIANTE ////////////////////////////////////////////////////////////
    public DefaultTableModel mostrarMatriculas(String buscar){
        DefaultTableModel modelo;
        String[] titulos = {"Código", "Curso", "Idioma", "Grupo","Profesor"};
        String[] registro = new String[5];
        modelo = new DefaultTableModel(null, titulos);

        try {
            ps = accesoDB.prepareStatement("SELECT * FROM sice.vista_matriculas WHERE idEstudiante='"+buscar+"'");
            rs = ps.executeQuery();
            while (rs.next()) {
                registro[0] = rs.getString(2);//Código
                registro[1] = rs.getString(3);//Curso
                registro[2] = rs.getString(4);//Idioma
                registro[3] = rs.getString(5);//Grupo
                registro[4] = rs.getString(6)+" "+rs.getString(7)+" "+rs.getString(8);//Profesor
                modelo.addRow(registro);
            }
            return modelo;
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ha habido un error al mostrar matrículas. Si el error persiste contacte a su equipo de TI.");
            e.printStackTrace();
        }
        return null;
    }
}
