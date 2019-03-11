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
     
   ////////////////////////////////MODIFICAR PROFESOR ////////////////////////////////////////////////////////////
     
    public boolean modificar(String identificacionBuscada, String identificacion, String nombre, String apellido1, String apellido2, int telefono, String direccion, String fechaNacimiento, String correo, int genero, int[] idioma){// Metodo para modificar
        boolean modificado = false;
        IdiomasDAO idiomasDao = new IdiomasDAO();
        try{
            Personas persona = new Personas();
            //PersonasDAO personasDAO = new PersonasDAO();
            //int[] idiomaAUX;
            boolean bandera = false;//Sirve para encontrar la diferencia entre dos vectores
            
            if(!identificacionBuscada.equals(identificacion)) {
                try {
                     persona = this.buscarRegistro(identificacion);
                 }catch (SQLException ex) {
                    ex.printStackTrace();
                }
                
                if(persona==null){
                    //ARREGLAR
                   // idiomaAUX = idiomasDao.vectorIdiomasPersona(identificacion);
                    
                   // for(int i=0; i<idiomaAUX.length; i++){
                        st.executeUpdate("DELETE FROM idiomasprofesor WHERE identificacion= '"+identificacionBuscada+"'");
                        
                   // }
                    
                    st.executeUpdate("UPDATE personas SET identificacion='"+identificacion+"', nombre='"+nombre+"', apellido1='"+apellido1+"',apellido2='"+apellido2+"',telefono='"+telefono+"',direccion='"+direccion+"',fechaNacimiento='"+fechaNacimiento+"',correo='"+correo+"',genero='"+genero+"'  WHERE identificacion ='"+identificacionBuscada+"'");
                    
                    
                    for(int i=0; i<idioma.length; i++){
                        ps = accesoDB.prepareStatement("INSERT INTO sice.idiomasprofesor(identificacion, idIdioma) VALUES (?,?)");
                        ps.setString(1, identificacion);
                        ps.setInt(2, idioma[i]);
                        ps.execute();
                        System.out.println("idioma="+idioma[i]);
                    }
                    
                    JOptionPane.showMessageDialog(null, "Se ha actualizado el profesor "+nombre+" "+apellido1+" "+apellido2);
                    modificado=true;   
                }else{
                        JOptionPane.showMessageDialog(null, "Ya existe un profesor con esa identificación: "+persona.getNombre()+" "+persona.getApellido1()+" "+persona.getApellido2());
                }
            }else{
                st.executeUpdate("UPDATE sice.personas SET nombre='"+nombre+"', apellido1='"+apellido1+"',apellido2='"+apellido2+"',telefono='"+telefono+"',direccion='"+direccion+"',fechaNacimiento='"+fechaNacimiento+"',correo='"+correo+"',genero='"+genero+"'  WHERE identificacion='"+identificacionBuscada+"'");
                //Segunda consulta a la base de datos
                
                //idiomaAUX = idiomasDao.vectorIdiomasPersona(identificacion); 
                
                //for(int i=0; i<idiomaAUX.length; i++){
                    st.execute("DELETE FROM `idiomasprofesor` WHERE identificacion = '"+identificacionBuscada+"';");
                    //st.executeUpdate("DELETE FROM sice.idiomasprofesor WHERE identificacion='"+identificacionBuscada+"' AND idIdioma="+idiomaAUX[i]);
                    //System.out.println("DELETE FROM sice.idiomasprofesor WHERE identificacion="+identificacionBuscada+", AND idIdioma="+idiomaAUX[i]);
                //}
            }
        }
        catch (Exception e){//En caso de error
            JOptionPane.showMessageDialog(null,"Ha habido un error");
            e.printStackTrace();
        }
        try{
            ps = accesoDB.prepareStatement(
                "INSERT INTO `sice`.`idiomasprofesor` (`identificacion`, `idIdioma`) "
                + "VALUES (?,?);"            
            );
            for(int i=0; i<idioma.length; i++){                    
                    ps.setString(1, identificacion);
                    ps.setInt(2, idioma[i]);
                    ps.execute();
            }
                JOptionPane.showMessageDialog(null, "Se ha actualizado el profesor "+nombre+" "+apellido1+" "+apellido2);
                modificado=true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Ha habido un error al modificar el idioma.");
            e.printStackTrace();
        }
        return modificado;
    }
   //////////////////////////////// BUSCAR PROFESOR //////////////////////////////////////////////////////////////

    public Personas buscarRegistro(String identificacion) throws SQLException{
        Personas persona = null;
        String sql = "SELECT * FROM sice.personas WHERE identificacion='"+identificacion+"'";
// -> *****************Tambien debe traer a los idioma que tiene actualmente idioma[null]    FALTA ***************************
        try {
            rs = st.executeQuery(sql);
            System.out.println("Profesor encontrado");           
        }catch (Exception e) {
            System.out.println("Hubo un error");
            e.printStackTrace();
        }
        persona= asignar();
        return persona; 
    }
    
    public Personas asignar(){
      Personas r = null;
      IdiomasDAO idiomasDao = new IdiomasDAO();
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
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                
                //METODO BURBUJA AL VECTOR
                for(int i = 0; i < Idioma.length - 1; i++)
                {
                    for(int j = 0; j < Idioma.length - 1; j++)
                    {
                        if (Idioma[j] < Idioma[j + 1])
                        {
                            int tmp = Idioma[j+1];
                            Idioma[j+1] = Idioma[j];
                            Idioma[j] = tmp;
                        }
                    }
                }
                r= new Personas (identificacion,Nombre,Apellido1,Apellido2,Telefono,Direccion,FechaNacimiento,Correo,Contraseña,Genero,IdTipoPersona,Idioma);
                r.setIdioma(Idioma);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
      
      return r;
                
    }
    
   ///////////////////////////////////////////////////////////INSERTAR PROFESOR///////////////////////////////////////////////////////////////////
   public String insertarPersona (Personas p){
       String respuestaRegistro=null;
       
        try{
            ps = accesoDB.prepareStatement(
            "INSERT INTO `sice`.`personas` (`identificacion`, `nombre`, `apellido1`, `apellido2`, `telefono`, `genero`, `direccion`, "
                    + "`fechaNacimiento`, `correo`, `contraseña`, `idTipoPersona`) VALUES (?,?,?,?,?,?,?,?,?,?,?);"            
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
            
            int numFAfectadas = ps.executeUpdate(); //Toma el numero de filas afectadas
            ps = accesoDB.prepareStatement(
            "INSERT INTO `sice`.`idiomasprofesor` (`identificacion`, `idIdioma`) "
            + "VALUES (?,?);"            
            );
            int[] auxiliarIdioma = p.getIdioma();
            
             //Evaluar          
            for(int i=0; i<auxiliarIdioma.length; i++)
            {
                if(auxiliarIdioma[i]>0){
                    ps.setString(1, p.getIdentificacion()); 
                    ps.setInt(2, auxiliarIdioma[i]);  
                    ps.execute();
                }
            }
           
            if(numFAfectadas>0){                     
                respuestaRegistro="El registro "+p.getNombre()+" "+p.getApellido1()+" "+p.getApellido2()+" ha sido registrado con éxito! ";
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return respuestaRegistro;
    }
   
   
   ////////////////////////////////////////////////////////////LISTAR PROFESOR/////////////////////////////////////////////////////////7
   
    public ArrayList<Personas> listarPersonas(){       
       
        ArrayList listarPersonas = new ArrayList();
        Personas tmp; 
        try{
            ps = accesoDB.prepareStatement("SELECT * FROM sice.personas;");
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
                    
                    ps = accesoDB.prepareStatement("SELECT * FROM `sice`.`idiomasprofesor` where identificacion='"+tmp.getIdentificacion()+"'");
                    ResultSet rs2 = ps.executeQuery();
                    
                    int[] idiomaAuxiliar = new int[listaIdiomas.size()];
                    int i = 0;
                    
                    while(rs2.next()){
                        idiomaAuxiliar[i]= rs2.getInt(2);
                        i++;
                    }
                    tmp.setIdioma(idiomaAuxiliar);
                    
 //PENDIENTE -> tmp.setIdioma(rs.getInt(12));
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
           ps = accesoDB.prepareStatement("SELECT identificacion FROM sice.personas where identificacion=?");
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
   
    ////////////////////////////////DESHABILITAR ES COMO UN UPDATE////////////////////////////////////////////////////
     public void deshabiliar(String identificacion){       
       try{
            st.executeUpdate("UPDATE sice.personas SET Habilitado=0 WHERE identificacion='"+identificacion+"'");
        }
        catch (Exception e){//En caso de error
            JOptionPane.showMessageDialog(null,"Ha habido un error");
            e.printStackTrace();
        }
    }
      
     ////////////////////////////////HABILITAR ES COMO UN UPDATE////////////////////////////////////////////////////
     public void habiliar(String identificacion){       
       try{
            st.executeUpdate("UPDATE sice.personas SET Habilitado=1 WHERE identificacion='"+identificacion+"'");
        }
        catch (Exception e){//En caso de error
            JOptionPane.showMessageDialog(null,"Ha habido un error");
            e.printStackTrace();
        }
    }
    
    ////////////////////////////////BOTON BUSCAR EN MANTENIMIENTO PROFESORES///////////////////////////////////////////////////
    public DefaultTableModel mostrarBuscar(String buscar){
        DefaultTableModel modelo;
        String[] titulos = {"identificacion", "Nombre", "Apellido 1", "Apellido 2","Teléfono", "Género", "Dirección", "Nacimiento", "Correo", "Idiomas", "Estado"};
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
            ps = accesoDB.prepareStatement("SELECT * FROM sice.personas WHERE identificacion LIKE '%"+buscar+"%' OR nombre LIKE '%"+buscar+"%' OR apellido1 LIKE '%"+buscar+
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
                
                //LOGICA ESTA BIEN....
                PreparedStatement ps2 = accesoDB.prepareStatement("SELECT `idIdioma` FROM `idiomasprofesor` WHERE `identificacion` = '"+rs.getString(1)+"'");
                ResultSet rs2 = ps2.executeQuery();
                String idioma="";
                //int i=0;
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
            JOptionPane.showMessageDialog(null, "Ha habido un error");
            e.printStackTrace();
        }
        return null;
    }
}
