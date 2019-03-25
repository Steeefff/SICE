/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;
import Datos.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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


public class Generos {

   
    private int idGenero;
    private String nombre;
    private String descripcion;
    
    
    /*Codigo nuevo*/
    public ArrayList<String> listaGeneros = new ArrayList<String>();
    public Conexion conexion;
    public Connection connection;
    public Statement st;
    public ResultSet rs;
    /*Codigo nuevo*/
    
    
    public Generos(Conexion conexion,ResultSet rs,Statement st) {
        
        this.conexion=conexion;
        this.rs=rs;
        this.st=st;
        /*try{        
            conexion = new Conexion();
            connection = conexion.Conexion();
            st = connection.createStatement();
        }catch(Exception e){
        }*/
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

   public ResultSet cargarGeneros(String sql){
        try{
        rs = st.executeQuery(sql); }catch(SQLException e){
        }
        return rs;
    }
    
}
