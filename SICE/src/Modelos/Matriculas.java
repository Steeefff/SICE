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

    private String idEstudiante,nombreGrupo,fechaDeMatricula,fechaProxPago;

    public Matriculas() {
    }

    public Matriculas(String idEstudiante,String nombreGrupo,String fechaDeMatricula, String fechaProxPago) {
        this.idEstudiante = idEstudiante;
        this.nombreGrupo = nombreGrupo;
        this.fechaDeMatricula=fechaDeMatricula;
        this.fechaProxPago=fechaProxPago;
    }

    public String getFechaProxPago() {
        return fechaProxPago;
    }

    public void setFechaProxPago(String fechaProxPago) {
        this.fechaProxPago = fechaProxPago;
    }

    public String getFechaDeMatricula() {
        return fechaDeMatricula;
    }

    public void setFechaDeMatricula(String fechaDeMatricula) {
        this.fechaDeMatricula = fechaDeMatricula;
    }

    public String getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }
    
    
}
