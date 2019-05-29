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

public class Cursos {

    private String idcurso;
    private String nombre;
    private int estado;
    private String[] requisito;
    private int idIdioma;

    public Cursos(){
    }
    
    public Cursos(String idcurso, String nombre, int estado, String[] requisito, int idIdioma) {
        this.idcurso=idcurso;
        this.nombre=nombre;
        this.estado=estado;
        this.requisito=requisito;
        this.idIdioma=idIdioma;
    }

    public String getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(String idcurso) {
        this.idcurso = idcurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String[] getRequisito() {
        return requisito;
    }

    public void setRequisito(String[] requisito) {
        this.requisito = requisito;
    }

    public int getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(int idIdioma) {
        this.idIdioma = idIdioma;
    }   
}
