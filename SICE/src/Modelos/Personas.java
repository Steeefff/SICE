package Modelos;

import javax.swing.JCheckBox;

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

public class Personas {

    private String identificacion;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int telefono;
    private String direccion;
    private String fechaNacimiento;
    private String correo;
    private String contraseña;
    private int genero;
    private int idTipoPersona;
    private int[] idioma;
    //private JCheckBox[] idiomas;
    private int habilitado;

    public Personas() {        
    }

    public Personas(String identificacion, String nombre, String apellido1, String apellido2, int telefono, String direccion, String fechaNacimiento, String correo, String contraseña, int genero, int idTipoPersona, int[] idioma) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.contraseña = contraseña;
        this.genero = genero;
        this.idTipoPersona = idTipoPersona;
        this.idioma=idioma;
        this.habilitado = 1;
    }   

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public int getIdTipoPersona() {
        return idTipoPersona;
    }

    public void setIdTipoPersona(int idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }

/*    public JCheckBox[] getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(JCheckBox[] idiomas) {
        this.idiomas = idiomas;
    }
*/
    public int[] getIdioma() {
        return idioma;
    }

    public void setIdioma(int[] idioma) {
        this.idioma = idioma;
    }

    public int getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(int habilitado) {
        this.habilitado = habilitado;
    }
    
}
