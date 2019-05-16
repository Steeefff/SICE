package Modelos;

/**
 * @author Grupo #30 Ingeniería 2018-2019
 * @author David Rodríguez Zamora
 * @author Katherine Jiménez Soto
 * @author Melany Monge Montero
 * @author Stefanny Villalobos Uva Proyecto de Ingeniería - Universidad Nacional
 * de Costa Rica Sistema Interno de Control de Estudiantes, SICE Profesor:
 * Rafael Alvarado Arley Pablo Gamboa Camacho Dueño del producto: Yensy Soto,
 * Centro Cultural Corporación Costa Rica Versión 1.3, 17/03/2019 Since 1.0
 */ 
public class Permisos {

    private int idPermiso;
    private String descripcion;
    private Boolean matricular;
    private Boolean agregarCurso;
    private Boolean agregarGrupo;
    private Boolean agregarProfesor;
    private Boolean agregarEstudiante;
    private Boolean mantenimientoCurso;
    private Boolean mantenimientoGrupo;
    private Boolean mantenimientoProfesor;
    private Boolean mantenimientoEstudiante;
    private Boolean mantenimientoSistema;

    public Permisos() {
    }
    
        public Permisos(int idPermiso, String descripcion, Boolean matricular, Boolean agregarCurso, Boolean agregarGrupo, Boolean agregarProfesor, Boolean agregarEstudiante, Boolean mantenimientoCurso, Boolean mantenimientoGrupo, Boolean mantenimientoProfesor, Boolean mantenimientoEstudiante, Boolean mantenimientoSistema) {
        this.idPermiso = idPermiso;
        this.descripcion = descripcion;
        this.matricular = matricular;
        this.agregarCurso = agregarCurso;
        this.agregarGrupo = agregarGrupo;
        this.agregarProfesor = agregarProfesor;
        this.agregarEstudiante = agregarEstudiante;
        this.mantenimientoCurso = mantenimientoCurso;
        this.mantenimientoGrupo = mantenimientoGrupo;
        this.mantenimientoProfesor = mantenimientoProfesor;
        this.mantenimientoEstudiante = mantenimientoEstudiante;
        this.mantenimientoSistema = mantenimientoSistema;
    }

    public int getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(int idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getMatricular() {
        return matricular;
    }

    public void setMatricular(Boolean matricular) {
        this.matricular = matricular;
    }

    public Boolean getAgregarCurso() {
        return agregarCurso;
    }

    public void setAgregarCurso(Boolean agregarCurso) {
        this.agregarCurso = agregarCurso;
    }

    public Boolean getAgregarGrupo() {
        return agregarGrupo;
    }

    public void setAgregarGrupo(Boolean agregarGrupo) {
        this.agregarGrupo = agregarGrupo;
    }

    public Boolean getAgregarProfesor() {
        return agregarProfesor;
    }

    public void setAgregarProfesor(Boolean agregarProfesor) {
        this.agregarProfesor = agregarProfesor;
    }

    public Boolean getAgregarEstudiante() {
        return agregarEstudiante;
    }

    public void setAgregarEstudiante(Boolean agregarEstudiante) {
        this.agregarEstudiante = agregarEstudiante;
    }

    public Boolean getMantenimientoCurso() {
        return mantenimientoCurso;
    }

    public void setMantenimientoCurso(Boolean mantenimientoCurso) {
        this.mantenimientoCurso = mantenimientoCurso;
    }

    public Boolean getMantenimientoGrupo() {
        return mantenimientoGrupo;
    }

    public void setMantenimientoGrupo(Boolean mantenimientoGrupo) {
        this.mantenimientoGrupo = mantenimientoGrupo;
    }

    public Boolean getMantenimientoProfesor() {
        return mantenimientoProfesor;
    }

    public void setMantenimientoProfesor(Boolean mantenimientoProfesor) {
        this.mantenimientoProfesor = mantenimientoProfesor;
    }

    public Boolean getMantenimientoEstudiante() {
        return mantenimientoEstudiante;
    }

    public void setMantenimientoEstudiante(Boolean mantenimientoEstudiante) {
        this.mantenimientoEstudiante = mantenimientoEstudiante;
    }

    public Boolean getMantenimientoSistema() {
        return mantenimientoSistema;
    }

    public void setMantenimientoSistema(Boolean mantenimientoSistema) {
        this.mantenimientoSistema = mantenimientoSistema;
    }

    @Override
    public String toString() {
        return "Permisos{" + "idPermiso=" + idPermiso + ", descripcion=" + descripcion + ", matricular=" + matricular + ", agregarCurso=" + agregarCurso + ", agregarGrupo=" + agregarGrupo + ", agregarProfesor=" + agregarProfesor + ", agregarEstudiante=" + agregarEstudiante + ", mantenimientoCurso=" + mantenimientoCurso + ", mantenimientoGrupo=" + mantenimientoGrupo + ", mantenimientoProfesor=" + mantenimientoProfesor + ", mantenimientoEstudiante=" + mantenimientoEstudiante + ", mantenimientoSistema=" + mantenimientoSistema + '}';
    }
}
