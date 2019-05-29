/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

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


public class Grupos {

    
    private String idgrupos,nombre,idcurso,idProfesor,horaInicio,dia;
    private int estado;

    public Grupos() {
    }

    public Grupos(String idgrupos, String nombre, String idcurso, String idProfesor, String horaInicio, int estdado, String dia) {
        this.idgrupos=idgrupos;
        this.nombre=nombre;
        this.idcurso=idcurso;
        this.idProfesor=idProfesor;
        this.horaInicio=horaInicio;
        this.estado=estado;
        this.dia=dia;
    }

    public String getIdgrupos() {
        return idgrupos;
    }

    public void setIdgrupos(String idgrupos) {
        this.idgrupos = idgrupos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(String idcurso) {
        this.idcurso = idcurso;
    }

    public String getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(String idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }
    
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
}
