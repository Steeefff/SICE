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
public class pagos {
    
    String idEstudiante,numeroRecibo,observacion,fechaDePago,fechaCancelada;
    
    public pagos(){}
    
    public pagos(String idEstudiante,String numeroRecibo,String observacion,String fechaDePago){
        this.idEstudiante=idEstudiante;
        this.numeroRecibo=numeroRecibo;
        this.observacion=observacion;
        this.fechaDePago=fechaDePago;
        this.fechaCancelada=fechaCancelada;
    }

    public String getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(String numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFechaDePago() {
        return fechaDePago;
    }

    public void setFechaDePago(String fechaDePago) {
        this.fechaDePago = fechaDePago;
    }
    
    public String getFechaCancelada() {
        return fechaCancelada;
    }

    public void setFechaCancelada(String fechaCancelada) {
        this.fechaCancelada = fechaCancelada;
    }
}
