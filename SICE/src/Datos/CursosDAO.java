package Datos;

import Modelos.Cursos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
public class CursosDAO {
    
    private static Conexion conexion;
    public static ResultSet rs;
    public static Statement st;
    Connection accesoDB;
    PreparedStatement ps;
    Cursos cursos;
    
    public CursosDAO(Conexion conexion,ResultSet rs,Statement st){
        this.conexion=conexion;
        this.rs=rs;
        this.st=st;
        accesoDB = this.conexion.getConexion();
    }
    
    public int cantidadRequisitos(int idCurso){
        int cantidadRequisitos = 0;
        
        try {
            ps = accesoDB.prepareStatement("SELECT COUNT(*) FROM sice.requisitos WHERE idcurso = '"+idCurso+"'");
            rs = ps.executeQuery();
            while(rs.next()){
                cantidadRequisitos = rs.getInt(1);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cantidadRequisitos;
    }
    
    public int cantidadCursosPorIdioma(int idIdioma){
        int cantidadCursos = 0;
        
        try {
            ps = accesoDB.prepareStatement("SELECT COUNT(*) FROM sice.cursos WHERE idIdioma = '"+idIdioma+"'");
            rs = ps.executeQuery();
            while(rs.next()){
                cantidadCursos = rs.getInt(1);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cantidadCursos;
    }
    
    //ASIGNA LA INFORMACIÓN DE UN CURSO DESDE LA DB A UN OBJETO TIPO CURSOS
    public Cursos asignar() throws SQLException{
        cursos=null;
        int idcurso,estado,idIdioma;
        String nombre;
        int[] requisitos;
        
        try {
            if(rs.first()){
                idcurso=rs.getInt("idcurso");
                estado=rs.getInt("estado");
                idIdioma=rs.getInt("idIdioma");
                nombre=rs.getString("nombre");
                requisitos=new int[cantidadRequisitos(idcurso)];
                
                ps = accesoDB.prepareStatement("SELECT idRequisito FROM requisitos WHERE idcurso='"+idcurso+"'");
                int i=0;
                rs = ps.executeQuery();
                while(rs.next()){
                    requisitos[i] = rs.getInt("idRequisito");
                    i++;
                }
                cursos=new Cursos(idcurso, nombre, estado, requisitos, idIdioma);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return cursos;
    }
    
    //BUSCA SI EXISTE EL CURSO Y SI EXISTE LLAMA A ASIGNAR
    public Cursos buscarCurso(String nombre) throws SQLException{
        cursos=null;
        String sql=("SELECT * FROM cursos WHERE nombre='"+nombre+"';");
        st.executeQuery(sql);        
        
         try {
            rs = st.executeQuery(sql);         
        }catch (Exception e) {
            System.out.println("Hubo un error");
            e.printStackTrace();
        }
        cursos= asignar();
        
        return cursos;
    }
    
    //BUSCA TODOS LOS CURSOS QUE EXISTEN PARA EL IDIOMA SELECCIONADO Y DEVUELVE UN VECTOR DE TIPO CURSOS
    public String[] buscarCursosPorIdioma(int idIdioma) throws SQLException{
        int cant = cantidadCursosPorIdioma(idIdioma);
        String [] cursosNombres =new String[cant];
        System.out.println("Cant. Cursos: "+cant);
        cursos=new Cursos();
        String sql=("SELECT * FROM cursos WHERE idIdioma='"+idIdioma+"';");
        
         try {
            rs = st.executeQuery(sql);         
            int i=0;
            while(rs.next()){
                cursos=asignar();
                cursosNombres[i]=cursos.getNombre();
                System.out.println(cursosNombres[i]);
                i++;
            }
        }catch (Exception e) {
            System.out.println("Hubo un error");
            e.printStackTrace();
        }
        
        return cursosNombres;
    }
    
    public void insertarCurso(){
        
    }
   
}
