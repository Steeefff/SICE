package Datos;

import static Datos.PersonasDAO.rs;
import Modelos.Cursos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
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
    Vector<Cursos> vecCursos;
    
    public CursosDAO(Conexion conexion,ResultSet rs,Statement st){
        this.conexion=conexion;
        this.rs=rs;
        this.st=st;
    }
    
    public int cantidadRequisitos(String idCurso){
        int cantidadRequisitos = 0;
        
        try {
            accesoDB = this.conexion.getConexion();
            ps = accesoDB.prepareStatement("SELECT COUNT(*) FROM sice.requisitoscursos WHERE idCursoAgregado = '"+idCurso+"'");
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
            accesoDB = this.conexion.getConexion();
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
    public Cursos asignarCurso(String idcursos) throws SQLException{
        cursos=null;
        int estado,idIdioma;
        String nombre,idcurso;
        String[] requisitos;
        try {
           ps = accesoDB.prepareStatement("SELECT * FROM cursos WHERE idcurso='"+idcursos+"'");
           rs= ps.executeQuery();
            if(rs.first()){
                idcurso=rs.getString("idcurso");
                nombre=rs.getString("nombre");
                estado=rs.getInt("estado");
                idIdioma=rs.getInt("idIdioma");
                requisitos=new String[cantidadRequisitos(idcurso)];
                
                accesoDB = this.conexion.getConexion();
                ps = accesoDB.prepareStatement("SELECT idCursoRequisito FROM requisitoscursos WHERE idCursoAgregado='"+idcurso+"'");
                int i=0;
                rs = ps.executeQuery();
                while(rs.next()){
                    requisitos[i] = rs.getString("idCursoRequisito");
                    i++;
                }
                cursos=new Cursos(idcurso,nombre,estado,requisitos,idIdioma);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return cursos;
    }
   
    //BUSCA SI EXISTE EL CURSO Y SI EXISTE LLAMA A ASIGNAR
    public Cursos buscarCursoCodigo(String idcurso) throws SQLException{
       Cursos cursosAux=null;
       try{
           accesoDB = this.conexion.getConexion();
           ps = accesoDB.prepareStatement("SELECT nombre FROM cursos WHERE idcurso='"+idcurso+"'");
           rs= ps.executeQuery();
           if(rs.first()){
               //CursosDAO cursosDAO=new CursosDAO(this.conexion,this.rs,this.st);
               cursosAux=asignarCurso(idcurso);
           }
       }catch(Exception e){ e.printStackTrace();}
       return cursosAux;
    }
    
    public Vector<Cursos> buscarCursosRequisitos(String idcurso) throws SQLException{
       Vector<Cursos> cursosAux= new Vector<Cursos>();
       Vector<String> idRequisitos = new Vector<String>();
       try{
           accesoDB = this.conexion.getConexion();
           ps = accesoDB.prepareStatement("SELECT idCursoRequisito FROM requisitoscursos WHERE idCursoAgregado='"+idcurso+"'");
           rs= ps.executeQuery();
           while(rs.next()){
               idRequisitos.add(rs.getString(1));
           }
           for(int i=0; i<idRequisitos.size(); i++){
                ps = accesoDB.prepareStatement("SELECT * FROM cursos WHERE idcurso='"+idRequisitos.get(i)+"'");
                rs= ps.executeQuery();
                if(rs.first()){
                    cursosAux.add(asignarCurso(rs.getString(1)));
                }
           }
       }catch(Exception e){ e.printStackTrace();}
       return cursosAux;
    }
    
    public Vector<Cursos> buscarTodosCursos() throws SQLException{
       Vector<Cursos> cursosAux= new Vector<Cursos>();
       Cursos cursoAux=new Cursos();
       String requisitos[]=new String[]{};
       try{
           accesoDB = this.conexion.getConexion();
           ps = accesoDB.prepareStatement("SELECT * FROM cursos");
           rs= ps.executeQuery();
           while(rs.next()){
               cursosAux.add(new Cursos(rs.getString("idcurso"),rs.getString("nombre"),rs.getInt("estado"),requisitos,rs.getInt("idIdioma")));
           }
       }catch(Exception e){ 
           JOptionPane.showMessageDialog(null,"Hubo un error al buscar el listado de los cursos. Por favor intente de nuevo y si persiste el error contacte a su equipo de TI.");
           e.printStackTrace();}
       return cursosAux;
    }
    
    //BUSCA TODOS LOS CURSOS QUE EXISTEN PARA EL IDIOMA SELECCIONADO Y DEVUELVE UN VECTOR DE TIPO CURSOS
    public Vector<Cursos> buscarCursosPorIdioma(int idIdioma) throws SQLException{
        int cant = cantidadCursosPorIdioma(idIdioma);
        cursos=new Cursos();
        
        vecCursos = new Vector<Cursos>();
        String sql=("SELECT * FROM cursos WHERE idIdioma='"+idIdioma+"';");
         try {
            rs = st.executeQuery(sql); 
            
            while(rs.next()){
                String [] auxiliar=new String[1];
                vecCursos.add(new Cursos(rs.getString("idcurso"),rs.getString("nombre"),rs.getInt("estado"),auxiliar,rs.getInt("idIdioma")));
            }
        }catch (Exception e) {
            System.out.println("Hubo un error en buscarCursosPorIdioma");
            e.printStackTrace();
        }     
        
        return vecCursos;
    }
    
    
    public String insertarCurso(Cursos curso, String[] requisitos){
        String respuestaRegistro=null;
        
        try{
            accesoDB = this.conexion.getConexion();
            ps = accesoDB.prepareStatement("INSERT INTO sice.cursos (idcurso, nombre, idIdioma, estado) VALUES (?, ?, ?, ?)");  
                ps.setString(1, curso.getIdcurso());
                ps.setString(2, curso.getNombre());
                ps.setInt(3, curso.getIdIdioma());
                ps.setInt(4, curso.getEstado());                          
                int numFAfectadasCursos = ps.executeUpdate(); //Toma el numero de filas afectadas
                String sql="SELECT idcurso FROM sice.cursos WHERE nombre='"+curso.getNombre()+"';";
                rs = st.executeQuery(sql);
                String idcurso;
                if(rs.next());
                idcurso = rs.getString(1);
                int numFAfectadasRequisitos = insertarRequisitos(curso.getIdcurso(),requisitos);
                
                
                if(numFAfectadasCursos>0){                     
                    respuestaRegistro="¡El registro del curso "+curso.getIdcurso()+" ha sido guardado con éxito! ";
                }else{
                    respuestaRegistro="Hubo un error al guardar el registro del cursos "+curso.getIdcurso()+". Intente de nuevo.";
                }            
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Ha habido un error. Intente de nuevo.");
                e.printStackTrace();
            }
      return respuestaRegistro;
    }
    private int insertarRequisitos(String idCurso, String[] requisitos) throws SQLException{
        int numFAfectadas =0;
        try {
            accesoDB = this.conexion.getConexion();
            ps = accesoDB.prepareStatement("INSERT INTO requisitoscursos (idCursoAgregado,idCursoRequisito) VALUES (?,?);");
            for(int i=0; i<requisitos.length; i++){
                ps.setString(1, idCurso);
                ps.setString(2, requisitos[i]);
                numFAfectadas=ps.executeUpdate();
            }        
        }catch (Exception e) {
            System.out.println("Hubo un error al insertar requisitos");
            e.printStackTrace();
        }
        return numFAfectadas;
    }
    ////////////////////////////////BOTON BUSCAR EN MANTENIMIENTO CURSOS///////////////////////////////////////////////////
    public boolean modificarCurso(Cursos cursoAux, String[] requisitos, String idBuscado) throws SQLException{        
        boolean modificado=false;
        Cursos cursoRespuesta = null;
             
        if(cursoAux.getIdcurso().equals(idBuscado)){
                System.out.println("Es igual");
                try{
                    st.executeUpdate("UPDATE sice.cursos SET nombre='"+cursoAux.getNombre()+"', idIdioma='"+cursoAux.getIdIdioma()+"' WHERE idcurso='"+idBuscado+"'");
                        st.execute("DELETE FROM requisitoscursos WHERE idCursoAgregado = '"+idBuscado+"';");
                        modificado=this.modificarRequisitos(idBuscado, requisitos);
                        if(modificado==true)
                            JOptionPane.showMessageDialog(null, "Se ha actualizado el registro "+cursoAux.getNombre()+" "+cursoAux.getIdcurso());
                        else
                            JOptionPane.showMessageDialog(null, "Ha habido un error al modificar el registro: "+cursoAux.getNombre()+" "+cursoAux.getIdcurso());
                 }catch(Exception e){
                     e.printStackTrace();
                     System.out.println(e.getMessage());
                 }
            
        }else{
                System.out.println("Es diferente");
                try {
                        cursoRespuesta = this.buscarCursoCodigo(cursoAux.getIdcurso());
                }catch (SQLException ex) {
                        ex.printStackTrace();
               }                

                if(cursoRespuesta==null){
                        st.executeUpdate("DELETE FROM requisitoscursos WHERE idCursoAgregado = '"+idBuscado+"';");
                        st.executeUpdate("UPDATE sice.cursos SET idcurso='"+cursoAux.getIdcurso()+"', nombre='"+cursoAux.getNombre()+"', idIdioma='"+cursoAux.getIdIdioma()+"' WHERE idcurso='"+idBuscado+"'");
                        modificado=true;
                        this.modificarRequisitos(cursoAux.getIdcurso(), requisitos);
                        if(modificado==true)
                                JOptionPane.showMessageDialog(null, "Se ha actualizado el registro "+cursoAux.getNombre()+" "+cursoAux.getIdcurso());
                        else
                                JOptionPane.showMessageDialog(null, "Ha habido un error al modificar el registro: "+cursoAux.getNombre()+" "+cursoAux.getIdcurso());
                }else{
                        JOptionPane.showMessageDialog(null, "Ya existe un registro con esa identificación: "+cursoAux.getNombre()+" "+cursoAux.getIdcurso());                
                }
        }
        
        return modificado;
    }
    
    private boolean modificarRequisitos(String idCurso, String[] requisitos) throws SQLException{
        boolean modificado=false;
        int numFAfectadas =0;
        try {
            accesoDB = this.conexion.getConexion();
            //ps = accesoDB.prepareStatement("DELETE FROM requisitoscursos WHERE idCursoAgregado='"+idCurso+"';");
            ps = accesoDB.prepareStatement("INSERT INTO requisitoscursos (idCursoAgregado,idCursoRequisito) VALUES (?,?);");
            for(int i=0; i<requisitos.length; i++){
                System.out.println("Tamaño requisitos: "+requisitos.length);
                ps.setString(1, idCurso);
                ps.setString(2, requisitos[i]);
                System.out.println("ID requisito: "+requisitos.length);
                numFAfectadas=ps.executeUpdate();
            }        
        }catch (Exception e) {
            System.out.println("Hubo un error al insertar requisitos");
            e.printStackTrace();
        }
        if(numFAfectadas>0)
            modificado=true;
        return modificado;
    }
    
    ////////////////////////////////BOTON BUSCAR EN MANTENIMIENTO CURSOS///////////////////////////////////////////////////
    public DefaultTableModel mostrarBuscarCursosEnAgregar(String buscar){
        DefaultTableModel modelo;
        String[] titulos = {"Código","Nombre","Requisitos", "Estado"};
        String[] registro = new String[4];
        modelo = new DefaultTableModel(null, titulos);
        String estados[] = {"Deshabilitado","Habilitado"};
        
            try {
                accesoDB = this.conexion.getConexion();
                ps = accesoDB.prepareStatement("SELECT * FROM sice.cursos WHERE idcurso LIKE '%"+buscar+"%' OR nombre LIKE '%"+buscar+"%'");
                rs = ps.executeQuery();
                while (rs.next()) {
                    registro[0] = rs.getString(1);//Id curso
                    registro[1] = rs.getString(2);//Nombre                
                    registro[3] = estados[rs.getInt(4)];//Estado

                    PreparedStatement ps2 = accesoDB.prepareStatement("SELECT * FROM requisitoscursos WHERE `idCursoAgregado` = '"+registro[0]+"'");
                    ResultSet rs2 = ps2.executeQuery();
                    String requisitos="";
                    while (rs2.next()) {
                      requisitos=(requisitos+rs2.getString(2)+".");
                    } 
                    registro[2]=requisitos;
                    modelo.addRow(registro);
                }
                return modelo;
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Ha habido un error.");
                e.printStackTrace();
            }
        return null;
    }
    
    public DefaultTableModel mostrarBuscarCursos(String buscar){
        DefaultTableModel modelo; 
        String[] titulos = {"Código","Nombre","Idioma", "Requisitos", "Estado"};
        String[] registro = new String[5];
        modelo = new DefaultTableModel(null, titulos);
        String estados[] = {"Deshablitado","Hablitado"};

        try {
            accesoDB = this.conexion.getConexion();
            ps = accesoDB.prepareStatement("SELECT * FROM sice.vista_mantenimientocursos WHERE idcurso='%"+buscar+"%' OR nombreCurso LIKE '%"+buscar+"%' OR nombreIdioma LIKE '%"+buscar+"%'");
            rs = ps.executeQuery();
            while (rs.next()) {
                registro[0] = rs.getString(1);//idCurso
                registro[1] = rs.getString(2);//Nombre
                registro[2] = rs.getString(5);//Idioma
                registro[4] = estados[rs.getInt(4)];//Estado
                
                PreparedStatement ps2 = accesoDB.prepareStatement("SELECT `idCursoRequisito` FROM `requisitoscursos` WHERE `idCursoAgregado` = '"+rs.getString(1)+"'");
                ResultSet rs2 = ps2.executeQuery();
                String requisitos="";
                while (rs2.next()) {
                    requisitos=requisitos+rs2.getString(1)+".";
                }               
                registro[3]=requisitos;
                modelo.addRow(registro);
            }
            return modelo;
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ha habido un error.");
            e.printStackTrace();
        }
        return null;
    }
    
    public void deshabiliar(String codigo){       
       try{
            st.executeUpdate("UPDATE sice.cursos SET estado=0 WHERE idcurso='"+codigo+"'");
        }
        catch (Exception e){//En caso de error
            JOptionPane.showMessageDialog(null,"Ha habido un error.");
            e.printStackTrace();
        }
    }
    public void habilitar(String codigo){       
       try{
            st.executeUpdate("UPDATE sice.cursos SET estado=1 WHERE idcurso='"+codigo+"'");
        }
        catch (Exception e){//En caso de error
            JOptionPane.showMessageDialog(null,"Ha habido un error.");
            e.printStackTrace();
        }
    }
}   

