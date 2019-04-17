package Datos;

import Modelos.Personas;
import Vista.ModificarProfesor;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;
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
             Pablo Gamboa Camacho
 * Dueño del producto: Yensy Soto, Centro Cultural Corporación Costa Rica
 * Versión 1.3, 17/03/2019
 * Since 1.0
 */
public class PersonasDAO {
    
    private static Conexion conexion;
    public static ResultSet rs;
    public static Statement st;
    Connection accesoDB;
    PreparedStatement ps;
    public ArrayList listaIdiomas = new ArrayList();
    
     public  PersonasDAO(Conexion conexion,ResultSet rs,Statement st){
        this.conexion=conexion;
        this.rs=rs;
        this.st=st;
        accesoDB = this.conexion.getConexion();
     }     
     
   ////////////////////////////////MODIFICAR PERSONA ////////////////////////////////////////////////////////////
     
    public boolean modificar(Personas persona, String identificacionBuscada){// Metodo para modificar
        boolean modificado=false;
        //Si tipo de persona es profesor
        if(persona.getIdTipoPersona()==2){
            try{
                if(!identificacionBuscada.equals(persona.getIdentificacion())) {
                        try {
                             persona = this.buscarRegistro(persona.getIdentificacion());
                         }catch (SQLException ex) {
                            ex.printStackTrace();
                        }                
                        if(persona==null){
                                st.executeUpdate("DELETE FROM idiomasprofesor WHERE identificacion= '"+identificacionBuscada+"'");
                                st.executeUpdate("UPDATE personas SET identificacion='"+persona.getIdentificacion()+"', nombre='"+persona.getNombre()+"', apellido1='"+persona.getApellido1()+"',apellido2='"+persona.getApellido2()+"',telefono='"+persona.getTelefono()+"',direccion='"+persona.getDireccion()+"',fechaNacimiento='"+persona.getFechaNacimiento()+"',correo='"+persona.getCorreo()+"',genero='"+persona.getGenero()+"'  WHERE identificacion ='"+identificacionBuscada+"'");
                                modificado=this.insertaIdioma(persona);
                                if(modificado==true)
                                    JOptionPane.showMessageDialog(null, "Se ha actualizado el registro "+persona.getNombre()+" "+persona.getApellido1()+" "+persona.getApellido2());
                                else
                                    JOptionPane.showMessageDialog(null, "Ha habido un error al modificar el registro: "+persona.getIdentificacion()+"', nombre='"+persona.getNombre()+"', apellido1='"+persona.getApellido1()+"',apellido2='"+persona.getApellido2());
                        }else{
                                JOptionPane.showMessageDialog(null, "Ya existe un registro con esa identificación: "+persona.getNombre()+" "+persona.getApellido1()+" "+persona.getApellido2());                
                        }
                }else{
                    st.executeUpdate("UPDATE sice.personas SET nombre='"+persona.getNombre()+"', apellido1='"+persona.getApellido1()+"',apellido2='"+persona.getApellido2()+"',telefono='"+persona.getTelefono()+"',direccion='"+persona.getDireccion()+"',fechaNacimiento='"+persona.getFechaNacimiento()+"',correo='"+persona.getCorreo()+"',genero='"+persona.getGenero()+"'  WHERE identificacion='"+identificacionBuscada+"'");
                    st.execute("DELETE FROM `idiomasprofesor` WHERE identificacion = '"+identificacionBuscada+"';");
                    modificado=this.insertaIdioma(persona);
                    if(modificado==true)
                        JOptionPane.showMessageDialog(null, "Se ha actualizado el registro "+persona.getNombre()+" "+persona.getApellido1()+" "+persona.getApellido2());
                    else
                        JOptionPane.showMessageDialog(null, "Ha habido un error al modificar el registro: "+persona.getIdentificacion()+"', nombre='"+persona.getNombre()+"', apellido1='"+persona.getApellido1()+"',apellido2='"+persona.getApellido2());
                }
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null,"Ha habido un error.");
                e.printStackTrace();
            }
        }else{
            //Si tipo de persona es estudiante
            if(persona.getIdTipoPersona()==1){
                try{
                if(!identificacionBuscada.equals(persona.getIdentificacion())) {
                        try {
                             persona = this.buscarRegistro(persona.getIdentificacion());
                         }catch (SQLException ex) {
                            ex.printStackTrace();
                        }                
                        if(persona==null){
                                st.executeUpdate("UPDATE personas SET identificacion='"+persona.getIdentificacion()+"', nombre='"+persona.getNombre()+"', apellido1='"+persona.getApellido1()+"',apellido2='"+persona.getApellido2()+"',telefono='"+persona.getTelefono()+"',direccion='"+persona.getDireccion()+"',fechaNacimiento='"+persona.getFechaNacimiento()+"',correo='"+persona.getCorreo()+"',genero='"+persona.getGenero()+"'  WHERE identificacion ='"+identificacionBuscada+"'");
                                JOptionPane.showMessageDialog(null, "Se ha actualizado el registro "+persona.getNombre()+" "+persona.getApellido1()+" "+persona.getApellido2());
                                modificado=true;
                        }else{
                                JOptionPane.showMessageDialog(null, "Ya existe un registro con esa identificación: "+persona.getNombre()+" "+persona.getApellido1()+" "+persona.getApellido2());                
                        }
                }else{
                    st.executeUpdate("UPDATE sice.personas SET nombre='"+persona.getNombre()+"', apellido1='"+persona.getApellido1()+"',apellido2='"+persona.getApellido2()+"',telefono='"+persona.getTelefono()+"',direccion='"+persona.getDireccion()+"',fechaNacimiento='"+persona.getFechaNacimiento()+"',correo='"+persona.getCorreo()+"',genero='"+persona.getGenero()+"'  WHERE identificacion='"+identificacionBuscada+"'");
                    JOptionPane.showMessageDialog(null, "Se ha actualizado el registro "+persona.getNombre()+" "+persona.getApellido1()+" "+persona.getApellido2());
                    modificado=true;
               }
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null,"Ha habido un error.");
                e.printStackTrace();
            }
          }
      }
        return modificado;
    }
   //////////////////////////////// BUSCAR PERSONA //////////////////////////////////////////////////////////////

    public Personas buscarRegistro(String identificacion) throws SQLException{
        Personas persona = null;
        String sql = "SELECT * FROM sice.personas WHERE identificacion='"+identificacion+"'";
        try {
            rs = st.executeQuery(sql);         
        }catch (Exception e) {
            System.out.println("Hubo un error");
            e.printStackTrace();
        }
        persona= asignar();
        return persona; 
    }
    
    //Asigna los datos de un registro de tipo personas e idiomasprofesor a una instancia de tipo Personas
    public Personas asignar(){
      Personas persona = null;
      IdiomasDAO idiomasDao = new IdiomasDAO(this.conexion,this.rs,this.st);
      String identificacion,Nombre,Apellido1,Apellido2,Direccion,FechaNacimiento,Correo,Contraseña;
      int Telefono,Genero,IdTipoPersona; //Guarda en la vaiable el valor recibido de cada txt.
      int[] Idioma;
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
                Idioma = new int[idiomasDao.cantidadIdiomasPersona(identificacion)];//Se trae la cantidad de idioma que tiene la persona
                //Idiomas
                try{
                    ps = accesoDB.prepareStatement("SELECT idIdioma FROM idiomasprofesor WHERE identificacion='"+identificacion+"'");
                    ps.executeQuery();
                    int i=0;
                    rs = ps.executeQuery();
                        while(rs.next()){
                            Idioma[i] = rs.getInt("idIdioma");
                            i++;
                        }
                }catch(Exception e){
                    e.printStackTrace();
                }
                //METODO BURBUJA
                for(int i = 0; i < Idioma.length - 1; i++){
                    for(int j = 0; j < Idioma.length - 1; j++){
                        if (Idioma[j] < Idioma[j + 1]){
                            int tmp = Idioma[j+1];
                            Idioma[j+1] = Idioma[j];
                            Idioma[j] = tmp;
                        }
                    }
                }
                persona= new Personas (identificacion,Nombre,Apellido1,Apellido2,Telefono,Direccion,FechaNacimiento,Correo,Contraseña,Genero,IdTipoPersona,Idioma);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return persona;          
    }    
   ///////////////////////////////////////////////////////////INSERTAR PERSONA///////////////////////////////////////////////////////////////////
   public String insertarPersona (Personas persona){
      String respuestaRegistro=null;
      if(persona.getIdTipoPersona()==2){  
         try{
             ps = accesoDB.prepareStatement(
                 "INSERT INTO `sice`.`personas` (`identificacion`, `nombre`, `apellido1`, `apellido2`, "
                         + "`telefono`, `genero`, `direccion`, `fechaNacimiento`, `correo`, `contraseña`, "
                         + "`idTipoPersona`) VALUES (?,?,?,?,?,?,?,?,?,?,?);");            
             ps.setString(1, persona.getIdentificacion());
             ps.setString(2, persona.getNombre());
             ps.setString(3, persona.getApellido1());
             ps.setString(4, persona.getApellido2());
             ps.setInt(5, persona.getTelefono());
             ps.setInt(6, persona.getGenero());
             ps.setString(7, persona.getDireccion());
             ps.setString(8, persona.getFechaNacimiento());
             ps.setString(9, persona.getCorreo());
             ps.setString(10, persona.getContraseña());
             ps.setInt(11, persona.getIdTipoPersona());            
             int numFAfectadas = ps.executeUpdate(); //Toma el numero de filas afectadas
             boolean insertado;
             insertado=insertaIdioma(persona);
             if(numFAfectadas>0 & insertado==true){                     
                 respuestaRegistro="¡El registro "+persona.getNombre()+" "+persona.getApellido1()+" "+persona.getApellido2()+" ha sido guardado con éxito! ";
             }else{
                 respuestaRegistro="Hubo un error al guardar el registro "+persona.getNombre()+" "+persona.getApellido1()+" "+persona.getApellido2()+". Intene de nuevo.";
             }            
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, "Ha habido un error. Intente de nuevo.");
             e.printStackTrace();
         }   
     }else{
        if(persona.getIdTipoPersona()==1){
            try{
                ps = accesoDB.prepareStatement(
                    "INSERT INTO `sice`.`personas` (`identificacion`, `nombre`, `apellido1`, `apellido2`, "
                            + "`telefono`, `genero`, `direccion`, `fechaNacimiento`, `correo`, `contraseña`, "
                            + "`idTipoPersona`) VALUES (?,?,?,?,?,?,?,?,?,?,?);");            
                ps.setString(1, persona.getIdentificacion());
                ps.setString(2, persona.getNombre());
                ps.setString(3, persona.getApellido1());
                ps.setString(4, persona.getApellido2());
                ps.setInt(5, persona.getTelefono());
                ps.setInt(6, persona.getGenero());
                ps.setString(7, persona.getDireccion());
                ps.setString(8, persona.getFechaNacimiento());
                ps.setString(9, persona.getCorreo());
                ps.setString(10, persona.getContraseña());
                ps.setInt(11, persona.getIdTipoPersona());            
                int numFAfectadas = ps.executeUpdate(); //Toma el numero de filas afectadas
                if(numFAfectadas>0){                     
                    respuestaRegistro="¡El registro "+persona.getNombre()+" "+persona.getApellido1()+" "+persona.getApellido2()+" ha sido guardado con éxito! ";
                }else{
                    respuestaRegistro="Hubo un error al guardar el registro "+persona.getNombre()+" "+persona.getApellido1()+" "+persona.getApellido2()+". Intene de nuevo.";
                }            
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Ha habido un error. Intente de nuevo.");
                e.printStackTrace();
            }
        }
    }
      return respuestaRegistro;
   }

    private boolean insertaIdioma(Personas persona){
        boolean insertado=false;
         try {
             //Insertar idiomas de profesor
             ps = accesoDB.prepareStatement(
                     "INSERT INTO `sice`.`idiomasprofesor` (`identificacion`, `idIdioma`) "
                             + "VALUES (?,?);");
             int[] auxiliarIdioma = persona.getIdioma();
             //Inserta los idiomas seleccionados para el profesor
             for(int i=0; i<auxiliarIdioma.length; i++){
                 if(auxiliarIdioma[i]>0){
                     ps.setString(1, persona.getIdentificacion());
                     ps.setInt(2, auxiliarIdioma[i]);  
                     ps.execute();
                 }
             }  
             insertado=true;
         } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Ha habido un error. Intente de nuevo.");
             e.printStackTrace();
         }
         return insertado;
   }
   
   ////////////////////////////////////////////////////////////LISTAR PERSONAS/////////////////////////////////////////////////////////7
 //-> Hasta el momento este método no se usa  
    public Vector<Personas> listarPersonas(int tipoPersona){  
        Vector vecPersonas = new Vector();
        Personas personas;
        //Cuando sea listar profesores
        if(tipoPersona==2){
                try{
                    ps = accesoDB.prepareStatement("SELECT * FROM sice.personas WHERE idTipoPersona=2;");
                    rs = ps.executeQuery();
                    while(rs.next()){ //si hay registros por leer entonces..
                       personas = new Personas();
                       String auxS="";
                       int auxI=0;
                       int[] auxIV={};vecPersonas.add(new Personas(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),auxI,auxS,auxS,auxS,auxS,auxI,auxI,auxIV));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
        }else{
                if(tipoPersona==1){
                try{
                    ps = accesoDB.prepareStatement("SELECT * FROM sice.personas WHERE idTipoPersona=1;");
                    rs = ps.executeQuery();
                    while(rs.next()){ //si hay registros por leer entonces..
                       personas = new Personas();
                       String auxS="";
                       int auxI=0;
                       int[] auxIV={};vecPersonas.add(new Personas(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),auxI,auxS,auxS,auxS,auxS,auxI,auxI,auxIV));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
         return vecPersonas;
    }
    
    ///////////////////////////////////////VALIDAR PERSONA/////////////////////////////////////////////////////////////////////
    public Boolean validarPersona(int id_per) {
        try {
           ps = accesoDB.prepareStatement("SELECT identificacion FROM sice.personas WHERE identificacion=?");
           ps.setInt(1, id_per); 
            
           rs = ps.executeQuery();
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
   
    ////////////////////////////////DESHABILITAR PERSONA////////////////////////////////////////////////////
     public void deshabiliar(String identificacion){       
       try{
            st.executeUpdate("UPDATE sice.personas SET Habilitado=0 WHERE identificacion='"+identificacion+"'");
        }
        catch (Exception e){//En caso de error
            JOptionPane.showMessageDialog(null,"Ha habido un error.");
            e.printStackTrace();
        }
    }
      
     ////////////////////////////////HABILITAR PERSONA////////////////////////////////////////////////////
     public void habiliar(String identificacion){       
       try{
            st.executeUpdate("UPDATE sice.personas SET Habilitado=1 WHERE identificacion='"+identificacion+"'");
        }
        catch (Exception e){//En caso de error
            JOptionPane.showMessageDialog(null,"Ha habido un error.");
            e.printStackTrace();
        }
    }
    
    ////////////////////////////////BOTON BUSCAR EN MANTENIMIENTO PROFESORES///////////////////////////////////////////////////
    public DefaultTableModel mostrarBuscarProfesores(String buscar){
        DefaultTableModel modelo;
        String[] titulos = {"Identificación", "Nombre", "Apellido 1", "Apellido 2","Teléfono", "Género", "Dirección", "Fecha Nacimiento", "Correo", "Idiomas", "Estado"};
        String[] registro = new String[11];
        modelo = new DefaultTableModel(null, titulos);
        ArrayList<String> generos = new ArrayList();
        ArrayList<String> idiomas = new ArrayList();
        ArrayList<Integer> ididiomas = new ArrayList();
        String estados[] = {"Deshablitado","Hablitado"};

        try {
            ps = accesoDB.prepareStatement("SELECT * FROM sice.generos");
            rs = ps.executeQuery();
            //generos
            while(rs.next()){
                generos.add(rs.getString("nombre"));
            }
            //Idiomas
            ps = accesoDB.prepareStatement("SELECT nombre FROM sice.idiomas");
            rs = ps.executeQuery();
            while(rs.next()){
                idiomas.add(rs.getString("nombre"));
            }
            //ID-Idiomas
            ps = accesoDB.prepareStatement("SELECT idIdioma FROM sice.idiomas");
            rs = ps.executeQuery();
            while(rs.next()){
                ididiomas.add(rs.getInt("idIdioma"));
            }
            ps = accesoDB.prepareStatement("SELECT * FROM vista_profesores WHERE identificacion LIKE '%"+buscar+"%' OR nombre LIKE '%"+buscar+"%' OR apellido1 LIKE '%"+buscar+
                                    "%' OR apellido2 LIKE '%"+buscar+"%' OR genero LIKE '%"+buscar+"%' OR direccion LIKE '%"+buscar+"%' OR Habilitado LIKE '%"+buscar+"%'");
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
                
                PreparedStatement ps2 = accesoDB.prepareStatement("SELECT `idIdioma` FROM `idiomasprofesor` WHERE `identificacion` = '"+rs.getString(1)+"'");
                ResultSet rs2 = ps2.executeQuery();
                String idioma="";
                while (rs2.next()) {
                    for(int i=0; i<ididiomas.size();i++){
                        if(ididiomas.get(i)==(rs2.getInt("idIdioma"))){idioma += idiomas.get(i)+".";
                            i++;
                        }
                    }
                }                
                registro[9] = idioma;
                registro[10] = estados[rs.getInt(12)];//Estados
                modelo.addRow(registro);
            }
            return modelo;
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ha habido un error.");
            e.printStackTrace();
        }
        return null;
    }
    
    ////////////////////////////////BOTON BUSCAR EN MANTENIMIENTO PROFESORES///////////////////////////////////////////////////
    public DefaultTableModel mostrarBuscarEstudiantes(String buscar){
        DefaultTableModel modelo;
        String[] titulos = {"Identificación", "Nombre", "Apellido 1", "Apellido 2","Teléfono", "Género", "Dirección", "Fecha Nacimiento", "Correo", "Estado"};
        String[] registro = new String[11];
        modelo = new DefaultTableModel(null, titulos);
        ArrayList<String> generos = new ArrayList();
       /* ArrayList<String> idiomas = new ArrayList();
        ArrayList<Integer> ididiomas = new ArrayList();*/
        String estados[] = {"Deshablitado","Hablitado"};

        try {
            ps = accesoDB.prepareStatement("SELECT * FROM sice.generos");
            rs = ps.executeQuery();
            //generos
            while(rs.next()){
                generos.add(rs.getString("nombre"));
            }
            ps = accesoDB.prepareStatement("SELECT * FROM vista_estudiantes WHERE identificacion LIKE '%"+buscar+"%' OR nombre LIKE '%"+buscar+"%' OR apellido1 LIKE '%"+buscar+
                                    "%' OR apellido2 LIKE '%"+buscar+"%' OR genero LIKE '%"+buscar+"%' OR direccion LIKE '%"+buscar+"%' OR Habilitado LIKE '%"+buscar+"%'");
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
                registro[9] = estados[rs.getInt(12)];//Estados
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
