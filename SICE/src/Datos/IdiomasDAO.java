/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;
import Modelos.Idiomas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/*
  @author Grupo #30 Ingeniería 2018-2019 
 *@author David Rodríguez Zamora
 *@author Katherine Jiménez Soto
 *@author Melany Monge Montero
 *@author Stefanny Villalobos Uva
 * Proyecto de Ingeniería - Universidad Nacional de Costa Rica
 * Sistema Interno de Control de Estudiantes, SICE
 * Profesor: Rafael Alvarado Arley
 * Dueño del producto: Yensy Soto, Centro Cultural Corporación Costa Rica
 * Versión 1.2, 21/10/2018
 * Since 1.0
 */
public class IdiomasDAO {
 
    private static Conexion conexion;
    public static ResultSet rs;
    public static Statement st;
    Connection accesoDB;
    PreparedStatement ps;
    
    public  IdiomasDAO(Conexion conexion,ResultSet rs,Statement st){
        this.conexion=conexion;
        this.rs=rs;
        this.st=st;
        accesoDB = this.conexion.getConexion();
    }   
    
    
    public void deshabiliar(String idIdioma){       
       try{
            st.executeUpdate("UPDATE sice.idiomas SET estado=0 WHERE idIdioma='"+idIdioma+"'");
        }
        catch (Exception e){//En caso de error
            JOptionPane.showMessageDialog(null,"Ha habido un error.");
            e.printStackTrace();
        }
    }
    
    public void habilitar(String idIdioma){       
       try{
            st.executeUpdate("UPDATE sice.idiomas SET estado=1 WHERE idIdioma='"+idIdioma+"'");
        }
        catch (Exception e){//En caso de error
            JOptionPane.showMessageDialog(null,"Ha habido un error.");
            e.printStackTrace();
        }
    }
    
    ///////////////////////////////////////////////////LISTAR IDIOMAS /////////////////////////////////////////////////////////
    public ArrayList<Idiomas> listarIdiomas(){          
       
        ArrayList listarIdiomas = new ArrayList();
        Idiomas tmp; // Variable Temporal
        try{
            PreparedStatement ps = accesoDB.prepareStatement("SELECT * FROM sice.idiomas;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){ //Si hay registros por leer entonces..
                tmp = new Idiomas();
                
                tmp.setNombre(rs.getString(2));
           
                listarIdiomas.add(tmp);
            }
        }catch (Exception e){
            e.printStackTrace();

        }
         return listarIdiomas;
    }
    
    public int cantidadIdiomasPersona(String identificacion){
        int cantidadIdiomas = 0;
        
        try {
            PreparedStatement ps = accesoDB.prepareStatement("SELECT COUNT(*) FROM sice.idiomasprofesor WHERE identificacion = '"+identificacion+"'");
            rs = ps.executeQuery();
            while(rs.next()){
                cantidadIdiomas = rs.getInt(1);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cantidadIdiomas;
    }
    
    public int[]  vectorIdiomasPersona(String identificacion){
        int[] Idioma = new int[cantidadIdiomasPersona(identificacion)];
        System.out.println("Cant idiomas profesor: "+cantidadIdiomasPersona(identificacion));
        
        try{
            PreparedStatement ps = accesoDB.prepareStatement("SELECT idIdioma FROM idiomasprofesor WHERE identificacion ='"+identificacion+"'");
            ps.executeQuery();
            int i=0;
            rs = ps.executeQuery();
            while(rs.next()){
                Idioma[i] = ((rs.getInt("idIdioma"))+1);
               // System.out.println("Agregando ID:"+rs.getInt("idIdioma"));
                i++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return Idioma;
    }
    ///////////////////////////////////////////////////////////INSERTAR IDIOMA///////////////////////////////////////////////////////////////////
   public String insertaIdioma (Idiomas idiomaAux) throws SQLException{
       String respuestaRegistro=null;
       if(this.buscarRegistro(idiomaAux.getNombre())==null){
               try{
                   ps = accesoDB.prepareStatement(
                       "INSERT INTO `sice`.`idiomas` (`nombre`,`estado`) VALUES (?,?);");
                   ps.setString(1, idiomaAux.getNombre());
                   ps.setInt(2, 1);
                   int numFAfectadas = ps.executeUpdate(); //Toma el numero de filas afectadas
                   if(numFAfectadas>0){                     
                       respuestaRegistro="¡El idioma "+idiomaAux.getNombre()+" ha sido guardado con éxito! ";
                   }else{
                       respuestaRegistro="Hubo un error al guardar el idioma "+idiomaAux.getNombre()+". Intene de nuevo.";
                   }            
               }catch(Exception e){
                   JOptionPane.showMessageDialog(null, "Ha habido un error. Intente de nuevo.");
                   e.printStackTrace();
               }  
     }else{
           respuestaRegistro="El idioma "+idiomaAux.getNombre()+" ya existe en el registro.";
       }
       return respuestaRegistro;
   }
   
   //////////////////////////////// BUSCAR IDIOMA //////////////////////////////////////////////////////////////

    public Idiomas buscarRegistro(String nombre) throws SQLException{
        Idiomas idioma = null;
        String sql = "SELECT * FROM sice.idiomas WHERE nombre='"+nombre+"'";
        try {
            rs = st.executeQuery(sql);         
        }catch (Exception e) {
            System.out.println("Hubo un error");
            e.printStackTrace();
        }
        idioma= asignar();
        return idioma; 
    }
    
    //Asigna los datos de un registro de tipo personas e idiomasprofesor a una instancia de tipo Personas
    public Idiomas asignar(){
      Idiomas idioma = null;
      String nombre;
      try {
            if(rs.first()){
                nombre= rs.getString("nombre");//Idiomas
                idioma= new Idiomas (nombre);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return idioma;          
    }
    
    ////////////////////////////////MOSTRAR, BUSCAR IDIOMAS ////////////////////////////////////////////////////////////
    public DefaultTableModel mostrarBuscarIdiomasEnMantenimiento(String idioma){
        DefaultTableModel modelo;
        String[] titulos = {"Código","Idioma","Estado"};
        String[] registro = new String[3];
        modelo = new DefaultTableModel(null, titulos);
        String estados[] = {"Deshabilitado","Habilitado"};
        
            try {                
                ps = accesoDB.prepareStatement("SELECT * FROM sice.idiomas WHERE idIdioma LIKE '%"+idioma+"%' OR nombre LIKE '%"+idioma+"%'");
                rs = ps.executeQuery();
                while (rs.next()) {
                    registro[0] = rs.getString(1);//Id idioma
                    registro[1] = rs.getString(2);//Nombre
                    registro[2] = estados[rs.getInt(3)];//Estado
                                        
                    modelo.addRow(registro);
                }
                return modelo;
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Ha habido un error al mostrar los idiomas.");
                e.printStackTrace();
            }
        return null;
    }
}
