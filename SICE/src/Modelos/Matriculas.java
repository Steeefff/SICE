/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

public class Matriculas {

    
    private int idMatriculas;
    private int idEstudiante;
    private int idGrupo;
    private String fechaPago;
    private int aprobado;

    public Matriculas() {
    }

    public Matriculas(int idMatriculas, int idEstudiante, int idGrupo, String fechaPago, int aprobado) {
        this.idMatriculas = idMatriculas;
        this.idEstudiante = idEstudiante;
        this.idGrupo = idGrupo;
        this.fechaPago = fechaPago;
        this.aprobado = aprobado;
    }
    
    public int getIdMatriculas() {
        return idMatriculas;
    }

    public void setIdMatriculas(int idMatriculas) {
        this.idMatriculas = idMatriculas;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getAprobado() {
        return aprobado;
    }

    public void setAprobado(int aprobado) {
        this.aprobado = aprobado;
    }

    
}
