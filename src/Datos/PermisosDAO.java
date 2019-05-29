
package Datos;

import Modelos.Permisos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
     
     
     public String insertarPermisos(Permisos permisos){
        String respuestaRegistro=null;   
        try{
            ps = accesoDB.prepareStatement("INSERT INTO `permisos`(`descripcion`, `matricular`, `agregarCurso`, `agregarGrupo`, `agregarProfesor`, `agregarEstudiante`, `mantenimientoCurso`, `mantenimientoGrupo`, `mantenimientoProfesor`, `mantenimientoEstudiante`, `mantenimientoSistema`) VALUES (?,?,?,?,?,?,?,?,?,?,?);");
                ps.setString(1, permisos.getDescripcion());
                ps.setBoolean(2, permisos.getMatricular());
                ps.setBoolean(3, permisos.getAgregarCurso());
                ps.setBoolean(4, permisos.getAgregarGrupo());
                ps.setBoolean(5, permisos.getAgregarProfesor());
                ps.setBoolean(6, permisos.getMatricular());//Se repite ya que agregarEstudiante sería lo mismo que matricular
                ps.setBoolean(7, permisos.getMantenimientoCurso());
                ps.setBoolean(8, permisos.getMantenimientoGrupo());
                ps.setBoolean(9, permisos.getMantenimientoProfesor());
                ps.setBoolean(10, permisos.getMantenimientoEstudiante());
                ps.setBoolean(11, permisos.getMantenimientoSistema());
                
                
                int numFAfectadasCursos = ps.executeUpdate(); //Toma el numero de filas afectadas              
                
                if(numFAfectadasCursos>0){                     
                    respuestaRegistro="¡El registro de los permisos a sido agregados con éxito! ";
                }else{
                    respuestaRegistro="Hubo un error al guardar el registro de de los permisos. Intente de nuevo.";
                }            
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Ha habido un error. Intente de nuevo.");
                e.printStackTrace();
            }
      return respuestaRegistro;
    }
     
    public String modificarPermisos(Permisos permisos, int idPermiso){
        String respuestaRegistro=null;   
        try{
            ps = accesoDB.prepareStatement("UPDATE sice.permisos SET descripcion=?,matricular=?,agregarCurso=?,agregarGrupo=?,agregarProfesor=?,agregarEstudiante=?,mantenimientoCurso=?,mantenimientoGrupo=?,mantenimientoProfesor=?,mantenimientoEstudiante=?,mantenimientoSistema=? WHERE idPermiso='"+idPermiso+"';");
            
            ps.setString(1, permisos.getDescripcion());
            ps.setBoolean(2, permisos.getMatricular());
            ps.setBoolean(3, permisos.getAgregarCurso());
            ps.setBoolean(4, permisos.getAgregarGrupo());
            ps.setBoolean(5, permisos.getAgregarProfesor());
            ps.setBoolean(6, permisos.getMatricular());//Se repite ya que agregarEstudiante sería lo mismo que matricular
            ps.setBoolean(7, permisos.getMantenimientoCurso());
            ps.setBoolean(8, permisos.getMantenimientoGrupo());
            ps.setBoolean(9, permisos.getMantenimientoProfesor());
            ps.setBoolean(10, permisos.getMantenimientoEstudiante());
            ps.setBoolean(11, permisos.getMantenimientoSistema());
            
                int numFAfectadasCursos = ps.executeUpdate(); //Toma el numero de filas afectadas              
                
                if(numFAfectadasCursos>0){                     
                    respuestaRegistro="¡El registro de los permisos a sido agregados con éxito! ";
                }else{
                    respuestaRegistro="Hubo un error al guardar el registro de de los permisos. Intente de nuevo.";
                }            
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Ha habido un error. Intente de nuevo.");
                e.printStackTrace();
            }
      return respuestaRegistro;
    }
    
    public Permisos listarPermisos(int tipoPersona) throws SQLException{
        Permisos permisos = null;
        String sql = "SELECT p.idPermiso,p.descripcion ,p.matricular, p.agregarCurso, p.agregarGrupo,p.agregarProfesor,p.agregarEstudiante,"+
                "p.mantenimientoCurso, p.mantenimientoGrupo,p.mantenimientoProfesor,p.mantenimientoEstudiante,p.mantenimientoSistema, p.administracionUsuarios FROM tipopersonas AS t INNER JOIN permisos AS p ON p.idPermiso =t.idPermiso WHERE idTipoPersonas = '"+tipoPersona+"'";
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
      Boolean matricular,agregarCurso,agregarGrupo,agregarProfesor,agregarEstudiante,mantenimientoCurso,mantenimientoGrupo,mantenimientoProfesor,mantenimientoEstudiante,mantenimientoSistema,administracionUsuarios;
      String descripcion;
      
      try {
            if(rs.first()){
                idPermiso = rs.getInt(1);
                descripcion = rs.getString(2);
                matricular = rs.getBoolean(3);
                agregarCurso = rs.getBoolean(4);
                agregarGrupo = rs.getBoolean(5);
                agregarProfesor = rs.getBoolean(6);
                agregarEstudiante = rs.getBoolean(7);
                mantenimientoCurso = rs.getBoolean(8);
                mantenimientoGrupo = rs.getBoolean(9);
                mantenimientoProfesor = rs.getBoolean(10);
                mantenimientoEstudiante = rs.getBoolean(11);
                mantenimientoSistema = rs.getBoolean(12);
                administracionUsuarios = rs.getBoolean(13);
                
                permisos= new Permisos(idPermiso,descripcion,matricular,agregarCurso,agregarGrupo,agregarProfesor,agregarEstudiante,mantenimientoCurso,mantenimientoGrupo,mantenimientoProfesor,mantenimientoEstudiante,mantenimientoSistema,administracionUsuarios);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return permisos;          
    } 
    
    public Permisos buscarRegistro(int idPermiso) throws SQLException {
        Permisos permisos = null;

        String sql = "SELECT * FROM sice.permisos WHERE idPermiso='" + idPermiso + "';";
        try {
            rs = st.executeQuery(sql);
        } catch (Exception e) {
            System.out.println("Hubo un error");
            e.printStackTrace();
        }
        permisos = asignar();
        return permisos;
    }

    
    public int maxID() throws SQLException{
        String sql ="SELECT MAX(idPermiso) FROM sice.permisos";
        ps = accesoDB.prepareStatement(sql);
        rs = ps.executeQuery();
        int maxID=0;

        if(rs.first()){
           maxID = (rs.getInt(1));
        }
        return maxID;
    }
}
