package Datos;

import Modelos.Personas;
import Vista.ModificarProfesor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class PersonasDAO {
    
    private static Conexion conexion;
    public static ResultSet rs;
    public static Statement st;
    
     public  PersonasDAO(){
         conexion=new Conexion();
     }     
     
     
     
   ////////////////////////////////MODIFICAR PROFESOR ////////////////////////////////////////////////////////////
     
    public boolean modificar(String identificacionBuscada, String identificacion, String nombre, String apellido1, String apellido2, int telefono, String direccion, String fechaNacimiento, String correo, int genero, int idioma){// Metodo para modificar
        boolean modificado = false;
        try{
            
            conexion = new Conexion();
            conexion.Conexion();
            st=Conexion.getSt ();
            Personas r = new Personas();
            PersonasDAO personasDAO = new PersonasDAO();
            
            if(!identificacionBuscada.equals(identificacion)) {
                try {
                     r = personasDAO.buscarReg(identificacionBuscada);
                 }catch (SQLException ex) {
                    Logger.getLogger(ModificarProfesor.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if(r==null){
                    st.executeUpdate("UPDATE sice.personas SET identificacion='"+identificacion+"', nombre='"+nombre+"', apellido1='"+apellido1+"',apellido2='"+apellido2+"',telefono='"+telefono+"',direccion='"+direccion+"',fechaNacimiento='"+fechaNacimiento+"',correo='"+correo+"',genero='"+genero+"',idioma='"+idioma+"'  WHERE identificacion='"+identificacionBuscada+"'");
                    JOptionPane.showMessageDialog(null, "Se ha actualizado el profesor "+nombre+" "+apellido1+" "+apellido2);
                    modificado=true;   
                }else{
                        JOptionPane.showMessageDialog(null, "Ya existe un profesor con esa identificación");
                }
            }else{
                st.executeUpdate("UPDATE sice.personas SET identificacion='"+identificacion+"', nombre='"+nombre+"', apellido1='"+apellido1+"',apellido2='"+apellido2+"',telefono='"+telefono+"',direccion='"+direccion+"',fechaNacimiento='"+fechaNacimiento+"',correo='"+correo+"',genero='"+genero+"',idioma='"+idioma+"'  WHERE identificacion='"+identificacionBuscada+"'");
                JOptionPane.showMessageDialog(null, "Se ha actualizado el profesor "+nombre+" "+apellido1+" "+apellido2);
                modificado=true; 
            }
        }
        catch (Exception e){//En caso de error
            JOptionPane.showMessageDialog(null,"Ha habido un error");
            e.printStackTrace();
        }return modificado;
    }
   
   //////////////////////////////// BUSCAR PROFESOR //////////////////////////////////////////////////////////////

    public static Personas buscarReg(String identificacion) throws SQLException{
        conexion = new Conexion();
        conexion.Conexion();
        Personas r = null;
        st=conexion.getSt ();
        String sql = "SELECT * FROM sice.personas WHERE identificacion='"+identificacion+"'";
        try {
            rs = st.executeQuery(sql);
            System.out.println("Profesor encontrado");           
        }catch (Exception e) {
            System.out.println("No Correcto");
            e.printStackTrace();
        }
        r= asignar();
        return r; 
    }
    
    public static Personas asignar(){
      Personas r = null;
      String identificacion,Nombre,Apellido1,Apellido2,Direccion,FechaNacimiento,Correo,Contraseña;
      int Telefono,Genero,IdTipoPersona,Idioma; //Guarda en la vaiable el valor recibido de cada txt.
        try {
            if(rs.first()){
                identificacion= rs.getString("identificacion");
                Nombre= rs.getString("Nombre");
                Apellido1= rs.getString("Apellido1");
                Apellido2= rs.getString("Apellido2");
                Telefono= rs.getInt("Telefono");
                Direccion= rs.getString("Direccion");
                FechaNacimiento= rs.getString("FechaNacimiento");
                Correo= rs.getString("Correo");
                Contraseña= rs.getString("Contraseña");
                Genero= rs.getInt("Genero");
                IdTipoPersona= rs.getInt("IdTipoPersona"); 
                Idioma=rs.getInt("Idioma");
                
                r= new Personas (identificacion,Nombre,Apellido1,Apellido2,Telefono,Direccion,FechaNacimiento,Correo,Contraseña,Genero,IdTipoPersona,Idioma);
            }
        }catch (Exception e) {
        }
      
      return r;
                
    }
    
   ///////////////////////////////////////////////////////////INSERTAR PROFESOR///////////////////////////////////////////////////////////////////
   public String insertarPersona (Personas p){
       String respuestaRegistro=null;
        try{
            Connection accesoDB = conexion.Conexion();
            PreparedStatement ps = accesoDB.prepareStatement(
            "INSERT INTO `sice`.`personas` (`identificacion`, `nombre`, `apellido1`, `apellido2`, `telefono`, `genero`, `direccion`, `fechaNacimiento`, `correo`, `contraseña`, `idTipoPersona`,`idioma`) "
            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?);"
            
            );
            ps.setString(1, p.getIdentificacion()); 
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getApellido1());
            ps.setString(4, p.getApellido2());
            ps.setInt(5, p.getTelefono());
            ps.setInt(6, p.getGenero());
            ps.setString(7, p.getDireccion());
            ps.setString(8, p.getFechaNacimiento());
            ps.setString(9, p.getCorreo());
            ps.setString(10, p.getContraseña());
            ps.setInt(11, p.getIdTipoPersona());
            ps.setInt(12, p.getIdioma());
           
            int numFAfectadas = ps.executeUpdate(); //Toma el numero de filas afectadas
            
            if(numFAfectadas>0){                     //Se cambia la palabra INSERTADO por REGISTRADO ya que el profesor realiza un comentario que se suena raro la palabra insertado.
                respuestaRegistro="El profesor "+p.getNombre()+" "+p.getApellido1()+" "+p.getApellido2()+" ha sido registrado con éxito! ";
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return respuestaRegistro;
    }
   
   
   ////////////////////////////////////////////////////////////LISTAR PROFESOR/////////////////////////////////////////////////////////7
   
    public ArrayList<Personas> listarPersonas(){          //metodo listar
       
        ArrayList listarPersonas = new ArrayList();
        Personas tmp; //
        try{
            Connection acceDB = conexion.Conexion();
            PreparedStatement ps = acceDB.prepareStatement("SELECT * FROM sice.personas;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){ //si hay registros por leer entonces..
                tmp = new Personas();
                
                tmp.setIdentificacion(rs.getString(1));
                tmp.setNombre(rs.getString(2));
                tmp.setApellido1(rs.getString(3));
                tmp.setApellido2(rs.getString(4));
                tmp.setTelefono(rs.getInt(5));
                tmp.setGenero(rs.getInt(6));
                tmp.setDireccion(rs.getString(7));
                tmp.setFechaNacimiento(rs.getString(8));
                tmp.setCorreo(rs.getString(9));
                tmp.setContraseña(rs.getString(10));
                tmp.setIdTipoPersona(rs.getInt(11));
                tmp.setIdioma(rs.getInt(12));
                tmp.setHabilitado(rs.getInt(13));
                
              listarPersonas.add(tmp);
            }
            
        }catch (Exception e){
            e.printStackTrace();

        }
         return listarPersonas;
    }
    
    
    ///////////////////////////////////////VALIDAR PERSONA/////////////////////////////////////////////////////////////////////
    public Boolean validarPersona(int id_per) {
        try {
           Connection accesoDB = conexion.Conexion();
           PreparedStatement ps = accesoDB.prepareStatement("SELECT identificacion FROM sice.personas where identificacion=?");
           ps.setInt(1, id_per); 
            
           ResultSet rs = ps.executeQuery();
            if (rs.next()) {
               return false;
            }
            else
                return true;
            
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }
   
    ////////////////////////////////DESHABILITAR ES COMO UN UPDATE////////////////////////////////////////////////////
     public void deshabiliar(String identificacion){       
       try{
            conexion = new Conexion();
            conexion.Conexion();
            st=Conexion.getSt ();
            st.executeUpdate("UPDATE sice.personas SET Habilitado=0 WHERE identificacion='"+identificacion+"'");
        }
        catch (Exception e){//En caso de error
            JOptionPane.showMessageDialog(null,"Ha habido un error");
        }
    }
      
     ////////////////////////////////DESHABILITAR ES COMO UN UPDATE////////////////////////////////////////////////////
     public void habiliar(String identificacion){       
       try{
            conexion = new Conexion();
            conexion.Conexion();
            st=Conexion.getSt ();
            st.executeUpdate("UPDATE sice.personas SET Habilitado=1 WHERE identificacion='"+identificacion+"'");
        }
        catch (Exception e){//En caso de error
            JOptionPane.showMessageDialog(null,"Ha habido un error");
        }
    }
    
    ////////////////////////////////BOTON BUSCAR EN MANTENIMIENTO PROFESORES///////////////////////////////////////////////////
    public DefaultTableModel mostrarBuscar(String buscar) {
        DefaultTableModel modelo;
        String[] titulos = {"identificacion", "Nombre", "Apellido 1", "Apellido 2","Teléfono", "Género", "Dirección", "Nacimiento", "Correo", "Idiomas", "Estado"};
        String[] registro = new String[11];
        modelo = new DefaultTableModel(null, titulos);
        ArrayList<String> generos = new ArrayList();
        ArrayList<String> idiomas = new ArrayList();
        String estados[] = {"Deshablitado","Hablitado"};

        try {
            Connection acceDB = conexion.Conexion();
            PreparedStatement ps = acceDB.prepareStatement("SELECT * FROM sice.generos");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                generos.add(rs.getString("nombre"));
            }
            ps = acceDB.prepareStatement("SELECT * FROM sice.idiomas");
            rs = ps.executeQuery();
            while(rs.next()){
                idiomas.add(rs.getString("nombre"));
            }
            
            
            
            ps = acceDB.prepareStatement("SELECT * FROM sice.personas WHERE identificacion LIKE '%"+buscar+"%' OR nombre LIKE '%"+buscar+"%' OR apellido1 LIKE '%"+buscar+
                                    "%' OR apellido2 LIKE '%"+buscar+"%' OR genero LIKE '%"+buscar+"%' OR direccion LIKE '%"+buscar+"%' OR idioma LIKE '%"+buscar+"%' OR Habilitado LIKE '%"+buscar+"%'");
            rs = ps.executeQuery();

            while (rs.next()) {
                registro[0] = rs.getString(1);//identificacion
                registro[1] = rs.getString(2);//Nombre
                registro[2] = rs.getString(3);//Apellido 1
                registro[3] = rs.getString(4);//Apellido 2
                registro[4] = Integer.toString(rs.getInt(5)); //Telefono                
                registro[5] = generos.get(rs.getInt(6)-1); //Genero
                registro[6] = rs.getString(7); //Direccion
                registro[7] = rs.getString(8); //Fecha Nacimiento
                registro[8] = rs.getString(9); //Correo
                registro[9] = idiomas.get(rs.getInt(12)-1);
                registro[10] = estados[rs.getInt(13)];

                modelo.addRow(registro);

            }
            return modelo;

        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ha habido un probema");
            
        }
        return null;
    }
}
