package Datos;

import static Datos.PersonasDAO.rs;
import Modelos.Cursos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JCheckBox;
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
        String [] cursosNombres = new String[cant];
        
        cursos=new Cursos();
        String sql=("SELECT nombre FROM cursos WHERE idIdioma='"+idIdioma+"';");
         try {
            rs = st.executeQuery(sql);         
            int i=0;
            
            while(rs.next()){
                cursosNombres[i] = rs.getString("nombre");
                i++;
            }
        }catch (Exception e) {
            System.out.println("Hubo un error");
            e.printStackTrace();
        }
      
        
        return cursosNombres;
    }
    
    
    public String insertarCurso(Cursos curso){
        String respuestaRegistro=null;
        
        try{
            ps = accesoDB.prepareStatement(
                    "INSERT INTO `sice`.`cursos` (`idcurso`, `nombre`, `idIdioma`, `estado`) "+
                            "VALUES (?,?,?,?);");            
                ps.setInt(1, curso.getIdcurso());
                ps.setString(2, curso.getNombre());
                ps.setInt(3, curso.getIdIdioma());
                ps.setInt(4, curso.getEstado());
                          
                int numFAfectadas = ps.executeUpdate(); //Toma el numero de filas afectadas
                if(numFAfectadas>0){                     
                    respuestaRegistro="¡El registro del curso "+curso.getNombre()+" ha sido guardado con éxito! ";
                }else{
                    respuestaRegistro="Hubo un error al guardar el registro del curos "+curso.getNombre()+". Intene de nuevo.";
                }            
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Ha habido un error. Intente de nuevo.");
                e.printStackTrace();
            }
      return respuestaRegistro;
    }
    
    ////////////////////////////////BOTON BUSCAR EN MANTENIMIENTO PROFESORES///////////////////////////////////////////////////
    public DefaultTableModel mostrarBuscarCursos(String buscar){
        DefaultTableModel modelo;
        String[] titulos = {"Id curso","Nombre","Idioma", "Requisitos", "Estado"};
        String[] registro = new String[5];
        modelo = new DefaultTableModel(null, titulos);
        ArrayList<String> generos = new ArrayList();
        String estados[] = {"Deshablitado","Hablitado"};

        try {
            ps = accesoDB.prepareStatement("SELECT * FROM sice.cursos WHERE nombre LIKE '%"+buscar+"%' OR apellido1 LIKE '%"+buscar+
                                    "%'OR Habilitado LIKE '%"+buscar+"%'");
            rs = ps.executeQuery();
            while (rs.next()) {
                registro[0] = Integer.toString(rs.getInt(1));//Id curso NO MOSTRAR EN LA VISTA
                registro[1] = rs.getString(2);//Nombre
                registro[2] = Integer.toString(rs.getInt(3));//Id idioma
                
                PreparedStatement ps2 = accesoDB.prepareStatement("SELECT `idRequisito` FROM `requisitos` WHERE `idcurso` = '"+rs.getInt(1)+"'");
                ResultSet rs2 = ps2.executeQuery();
                String requsitos="";
                while (rs2.next()) {
                    //AGREGAR REQUISITOS AL REGISTRO[3]---->rs2.getInt("idRequisito")
                }
                registro[4] = Integer.toString(rs.getInt(5)); //Estado                
                
                modelo.addRow(registro);
            }
            return modelo;
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ha habido un error.");
            e.printStackTrace();
        }
        return null;
    }
}
