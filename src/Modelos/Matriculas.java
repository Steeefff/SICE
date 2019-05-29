/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.util.Date;


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
 * Versión 1.2, 21/10/2018
 * Since 1.0
 */

public class Matriculas {

    private String estudiante, nombre, Grupo,curso,horario,fechaDeMatricula,fechaProxPago;

    public Matriculas() {
    }

    public Matriculas(String estudiante, String nombre, String Grupo, String curso, String horario, String fechaDeMatricula, String fechaProxPago) {
        this.estudiante = estudiante;
        this.nombre = nombre;
        this.Grupo = Grupo;
        this.curso = curso;
        this.horario = horario;
        this.fechaDeMatricula = fechaDeMatricula;
        this.fechaProxPago = fechaProxPago;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGrupo() {
        return Grupo;
    }

    public void setGrupo(String Grupo) {
        this.Grupo = Grupo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getFechaDeMatricula() {
        return fechaDeMatricula;
    }

    public void setFechaDeMatricula(String fechaDeMatricula) {
        this.fechaDeMatricula = fechaDeMatricula;
    }

    public String getFechaProxPago() {
        return fechaProxPago;
    }

    public void setFechaProxPago(String fechaProxPago) {
        this.fechaProxPago = fechaProxPago;
    }
    
    
}
