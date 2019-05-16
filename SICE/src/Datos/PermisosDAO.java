
package Datos;

import Modelos.Permisos;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

public class PermisosDAO {
    
    private static Conexion conexion;
    public static ResultSet rs;
    public static Statement st;
    Connection accesoDB;
    PreparedStatement ps;
    public ArrayList listaIdiomas = new ArrayList();
    
     public  PermisosDAO(Conexion conexion,ResultSet rs,Statement st){
        this.conexion=conexion;
        this.rs=rs;
        this.st=st;
        accesoDB = this.conexion.getConexion();
     }   
    public Permisos listarPermisos(int tipoPersona) throws SQLException{
        Permisos permisos = null;
        String sql = "SELECT p.idPermiso,p.descripcion ,p.matricular, p.agregarCurso, p.agregarGrupo,p.agregarProfesor,p.agregarEstudiante,"+
                "p.mantenimientoCurso, p.mantenimientoGrupo,p.mantenimientoProfesor,p.mantenimientoEstudiante,p.mantenimientoSistema FROM tipopersonas AS t INNER JOIN permisos AS p ON p.idPermiso =t.idPermiso WHERE idTipoPersonas = '"+tipoPersona+"'";
        try {
            rs = st.executeQuery(sql);         
        }catch (Exception e) {
            System.out.println("Hubo un error");
            e.printStackTrace();
        }
        permisos= asignar();
        return permisos; 
    }
    
    public Permisos asignar(){
      Permisos permisos = null;
      int idPermiso;
      Boolean matricular,agregarCurso,agregarGrupo,agregarProfesor,agregarEstudiante,mantenimientoCurso,mantenimientoGrupo,mantenimientoProfesor,mantenimientoEstudiante,mantenimientoSistema;
      String descripcion;
      
      try {
            if(rs.first()){
                idPermiso = rs.getInt("idPermiso");
                descripcion = rs.getString("descripcion");
                matricular = rs.getBoolean("matricular");
                agregarCurso = rs.getBoolean("agregarCurso");
                agregarGrupo = rs.getBoolean("agregarGrupo");
                agregarProfesor = rs.getBoolean("agregarProfesor");
                agregarEstudiante = rs.getBoolean("agregarEstudiante");
                mantenimientoCurso = rs.getBoolean("mantenimientoCurso");
                mantenimientoGrupo = rs.getBoolean("mantenimientoGrupo");
                mantenimientoProfesor = rs.getBoolean("mantenimientoProfesor");
                mantenimientoEstudiante = rs.getBoolean("mantenimientoEstudiante");
                mantenimientoSistema = rs.getBoolean("mantenimientoSistema");

                permisos= new Permisos(idPermiso,descripcion,matricular,agregarCurso,agregarGrupo,agregarProfesor,agregarEstudiante,mantenimientoCurso,mantenimientoGrupo,mantenimientoProfesor,mantenimientoEstudiante,mantenimientoSistema);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return permisos;          
    } 
}
