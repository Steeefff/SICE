/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelos.Grupos;
import Modelos.Vista_mantenimientoGrupos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *@author Grupo #30 Ingeniería 2018-2019 
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
public class GruposDAO {
    
    private static Conexion conexion;
    public static ResultSet rs;
    public static Statement st;
    Connection accesoDB;
    PreparedStatement ps;
    Grupos grupos;
    Vista_mantenimientoGrupos vista_mantenimientoGrupos;
    
    
    public GruposDAO(Conexion conexion,ResultSet rs,Statement st){
        this.conexion=conexion;
        this.rs=rs;
        this.st=st;
        accesoDB = this.conexion.getConexion();
    }
    
    ////////////////////////////////ASIGNAR GRUPO///////////////////////////////////////////////////
    public Grupos buscarGrupoCodigo(String idgrupos) throws SQLException{
       Grupos gruposAux=null;
       try{
           ps = accesoDB.prepareStatement("SELECT nombre FROM grupos WHERE idgrupos='"+idgrupos+"'");
           rs= ps.executeQuery();
           if(rs.first()){
               //CursosDAO cursosDAO=new CursosDAO(this.conexion,this.rs,this.st);
               gruposAux=asignarGrupo(idgrupos);
           }
       }catch(Exception e){ JOptionPane.showMessageDialog(null, "Hubo un error al buscar el código ingresado. Por favor intente de nuevo y si el error persiste contacte a su equipo de TI.");e.printStackTrace();}
       return gruposAux;
    }
    
    //ASIGNA LA INFORMACIÓN DE UN GRUPO DESDE LA DB A UN OBJETO TIPO GRUPOS
    public Grupos asignarGrupo(String idgrupo) throws SQLException{
        Grupos grupos=null;
        String idgrupos,nombre,idcurso,idProfesor,horaInicio,dia;
        int estado; 
        try {
           ps = accesoDB.prepareStatement("SELECT * FROM grupos WHERE idgrupos='"+idgrupo+"'");
           rs= ps.executeQuery();
            if(rs.first()){
                idgrupos=rs.getString("idgrupos");
                nombre=rs.getString("nombre");
                idcurso=rs.getString("idcurso");
                idProfesor=rs.getString("idProfesor");
                horaInicio=rs.getString("horaInicio");
                dia=rs.getString("dia");
                estado=rs.getInt("estado");
                
                grupos=new Grupos(idgrupos,nombre,idcurso,idProfesor,horaInicio,estado,dia);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return grupos;
    }
    
    ////////////////////////////////INSERTAR GRUPO///////////////////////////////////////////////////
    public String insertarGrupo(Grupos grupo){
        String respuestaRegistro=null;
        
        try{
            ps = accesoDB.prepareStatement("INSERT INTO sice.grupos (idgrupos, nombre, idcurso, idProfesor, horaInicio, estado, dia) VALUES (?,?,?,?,?,?,?)");  
                ps.setString(1, grupo.getIdgrupos());
                ps.setString(2, grupo.getNombre());
                ps.setString(3, grupo.getIdcurso());
                ps.setString(4, grupo.getIdProfesor());
                ps.setString(5, grupo.getHoraInicio());
                ps.setInt(6, grupo.getEstado());
                ps.setString(7, grupo.getDia());
                int numFAfectadasCursos = ps.executeUpdate(); //Toma el numero de filas afectadas
                /*String sql="SELECT idcurso FROM sice.cursos WHERE nombre='"+curso.getNombre()+"';";
                rs = st.executeQuery(sql);
                String idcurso;
                if(rs.next());
                idcurso = rs.getString(1);
                int numFAfectadasRequisitos = insertarRequisitos(curso.getIdcurso(),requisitos);*/                
                
                if(numFAfectadasCursos>0){                     
                    respuestaRegistro="¡El registro del grupo "+grupo.getIdcurso()+" "+grupo.getNombre()+" ha sido guardado con éxito! ";
                }else{
                    respuestaRegistro="Hubo un error al guardar el registro del grupo "+grupo.getIdcurso()+" "+grupo.getNombre()+". Intente de nuevo.";
                }            
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Ha habido un error al guardar el grupo. Si el error persiste contacte a su equipo de TI.");
                e.printStackTrace();
            }
      return respuestaRegistro;
    }
    
    ////////////////////////////////BOTON BUSCAR EN MANTENIMIENTO GRUPOS///////////////////////////////////////////////////
    public DefaultTableModel mostrarBuscarGruposEnAgregar(String buscar){
        DefaultTableModel modelo;
        String[] titulos = {"Código","Nombre","Curso", "Profesor", "Estado"};
        String[] registro = new String[5];
        modelo = new DefaultTableModel(null, titulos);
        String estados[] = {"Deshabilitado","Habilitado"};
        
            try {
                ps = accesoDB.prepareStatement("SELECT * FROM sice.grupos WHERE idgrupos LIKE '%"+buscar+"%' OR nombre LIKE '%"+buscar+"%' OR idProfesor LIKE '%"+buscar+"%' OR idcurso LIKE '%"+buscar+"%'");
                rs = ps.executeQuery();
                while (rs.next()) {
                    registro[0] = rs.getString(1);//Id grupo
                    registro[1] = rs.getString(2);//Nombre
                    registro[2] = rs.getString(3);//idcurso
                    registro[4] = estados[rs.getInt(6)];//Estado

                    PreparedStatement ps2 = accesoDB.prepareStatement("SELECT * FROM personas WHERE `identificacion` = '"+rs.getString(4)+"'");
                    ResultSet rs2 = ps2.executeQuery();
                    String profesor="";
                    while (rs2.next()) {
                      profesor=(profesor+rs2.getString(2)+" "+profesor+rs2.getString(3)+" "+profesor+rs2.getString(4));
                    } 
                    registro[3]=profesor;
                    modelo.addRow(registro);
                }
                return modelo;
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Ha habido un error al mostrar los grupos.");
                e.printStackTrace();
            }
        return null;
    }
    
    public DefaultTableModel mostrarBuscarGruposEnMantenimiento(String buscar){
        DefaultTableModel modelo;
        String[] titulos = {"Código","Nombre","Curso", "Profesor","Horario", "Estado"};
        String[] registro = new String[6];
        modelo = new DefaultTableModel(null, titulos);
        String estados[] = {"Deshabilitado","Habilitado"};
        
            try {                
                ps = accesoDB.prepareStatement("SELECT * FROM sice.vista_mantenimientogrupos WHERE idgrupos LIKE '%"+buscar+"%' OR nombreGrupo LIKE '%"+buscar+"%' OR idProfesor LIKE '%"+buscar+"%' OR idcurso LIKE '%"+buscar+"%' OR nombreCurso LIKE '%"+buscar+"%' OR estado LIKE '%"+buscar+"%' OR dia LIKE '%"+buscar+"%' OR nombreProfesor LIKE '%"+buscar+"%' OR apellido1 LIKE '%"+buscar+"%' OR apellido2 LIKE '%"+buscar+"%'");
                rs = ps.executeQuery();
                while (rs.next()) {
                    registro[0] = rs.getString(1);//Id grupo
                    registro[1] = rs.getString(2);//Nombre
                    registro[2] = rs.getString(3)+", "+rs.getString(11);//Curso
                    registro[3] = rs.getString(8)+" "+rs.getString(9)+" "+rs.getString(10);//Profesor
                    registro[4] = rs.getString(7)+" "+rs.getString(5);//Horario
                    registro[5] = estados[rs.getInt(6)];//Estado
                                        
                    modelo.addRow(registro);
                }
                return modelo;
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Ha habido un error al mostrar los grupos.");
                e.printStackTrace();
            }
        return null;
    }
    
    public void deshabiliar(String codigo){       
       try{
            st.executeUpdate("UPDATE sice.grupos SET estado=0 WHERE idgrupos='"+codigo+"'");
        }
        catch (Exception e){//En caso de error
            JOptionPane.showMessageDialog(null,"Ha habido un error.");
            e.printStackTrace();
        }
    }
    public void habilitar(String codigo){       
       try{
            st.executeUpdate("UPDATE sice.grupos SET estado=1 WHERE idgrupos='"+codigo+"'");
        }
        catch (Exception e){//En caso de error
            JOptionPane.showMessageDialog(null,"Ha habido un error.");
            e.printStackTrace();
        }
    }
    ////////////////////////////////MODIFICAR GRUPO ////////////////////////////////////////////////////////////
     
    public boolean modificar(Grupos grupos, String codigoBuscado){// Metodo para modificar
        boolean modificado=false;
            try{
                if(!codigoBuscado.equals(grupos.getIdcurso())) {
                        try {
                             modificado = this.buscarExistenciaDeGrupos(grupos.getIdcurso());
                         }catch (SQLException ex) {
                            ex.printStackTrace();
                        }                
                        if(modificado==false){
                                st.executeUpdate("UPDATE grupos SET idgrupos='"+grupos.getIdgrupos()+"', nombre='"+grupos.getNombre()+"', idcurso='"+grupos.getIdcurso()+"',idProfesor='"+grupos.getIdProfesor()+"',horaInicio='"+grupos.getHoraInicio()+"',estado='"+grupos.getEstado()+"',dia='"+grupos.getDia()+"'  WHERE idgrupos ='"+codigoBuscado+"'");
                                JOptionPane.showMessageDialog(null, "Se ha actualizado el registro "+grupos.getIdgrupos()+" "+grupos.getNombre());
                        }
                }else{
                    st.executeUpdate("UPDATE sice.grupos SET nombre='"+grupos.getNombre()+"', idcurso='"+grupos.getIdcurso()+"',idProfesor='"+grupos.getIdProfesor()+"',horaInicio='"+grupos.getHoraInicio()+"',estado='"+grupos.getEstado()+"',dia='"+grupos.getDia()+"'  WHERE idgrupos ='"+grupos.getIdgrupos()+"'");
                    JOptionPane.showMessageDialog(null, "Se ha actualizado el registro "+grupos.getIdgrupos()+" "+grupos.getNombre());
                  }
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null,"Ha habido un error.");
                e.printStackTrace();
            }
        return modificado;
    }
    
    //////////////////////////////// BUSCAR GRUPO //////////////////////////////////////////////////////////////

    public boolean buscarExistenciaDeGrupos(String idgrupos) throws SQLException{
        boolean existe = false;
        String sql = "SELECT * FROM sice.grupos WHERE idgrupos='"+idgrupos+"'";
        try {
            rs = st.executeQuery(sql); 
            if(rs.first())
                existe=true;
        }catch (Exception e) {
            System.out.println("Hubo un error al buscar el registro.");
            e.printStackTrace();
        }
        return existe; 
    }
    
    public Grupos buscarRegistro(String idgrupos) throws SQLException{
        Grupos grupos = null;
        String sql = "SELECT * FROM sice.grupos WHERE idgrupos='"+idgrupos+"'";
        try {
            rs = st.executeQuery(sql);         
        }catch (Exception e) {
            System.out.println("Hubo un error");
            e.printStackTrace();
        }
        grupos= asignarGrupo(idgrupos);
        return grupos; 
    }
    
    ////////////////////////////////////////////////////////////LISTAR GRUPOS///////////////////////////////////////////////////////// 
    public Vector<Vista_mantenimientoGrupos> listarGruposVista(){  
        Vector vecGrupos = new Vector();
        Vista_mantenimientoGrupos grupos;
                try{
                    ps = accesoDB.prepareStatement("SELECT * FROM sice.vista_mantenimientogrupos WHERE estado=1;");
                    rs = ps.executeQuery();
                    while(rs.next()){ //si hay registros por leer entonces..
                       grupos = new Vista_mantenimientoGrupos();
                       grupos.setIdGrupos(rs.getString(1));
                       grupos.setNombreGrupo(rs.getString(2));
                       grupos.setIdCurso(rs.getString(3));
                       grupos.setIdProfesor(rs.getString(4));
                       grupos.setHoraInicio(rs.getString(5));
                       grupos.setEstado(rs.getInt(6));
                       grupos.setDia(rs.getString(7));
                       grupos.setNombreProfesor(rs.getString(8));
                       grupos.setApellido1(rs.getString(9));
                       grupos.setApelliddo2(rs.getString(10));
                       grupos.setNombreCurso(rs.getString(11));
                       vecGrupos.add(grupos);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
         return vecGrupos;
    }
}