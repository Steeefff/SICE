package Vista;

import Datos.Conexion;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

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

public class VentanaPrincipal extends javax.swing.JFrame {
    
    MatriculaEstudiante matriculaEstudiante;
    AgregarProfesor agregarProfesor;
    AgregarGrupo agregarGrupo;
    AgregarCurso agregarCurso;
    AdministracionUsuarios administracionUsuarios;
    MantenimientoEstudiante mantenimientoEstudiantes;
    MantenimientoProfesor mantenimientoProfesores;
    Pagos pago;
    MantenimientoGrupos mantenimientoGrupos;
    MantenimientoCursos mantenimientoCursos;
    Login login;
    Image icon;
    private static Conexion conexion;
    public static ResultSet rs;
    public static Statement st;
    
    public VentanaPrincipal(Image icono,Conexion conexion,ResultSet rs,Statement st) {
        initComponents(); 
        this.setTitle("SICE - Principal");
        this.setSize(1290,710); 
        setLocationRelativeTo(null);
        this.setResizable(false);
        fecha();
        this.icon = icono;
        this.conexion=conexion;
        this.rs=rs;
        this.st=st;
        setIconImage(icon);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void fecha(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaComoCadena = sdf.format(new Date());
        jLbFecha.setText(fechaComoCadena);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnAgregarGrupo = new javax.swing.JButton();
        btnMatricularEstudiante = new javax.swing.JButton();
        btnAgregarProfesor = new javax.swing.JButton();
        btnAgregarCursos = new javax.swing.JButton();
        btnAgregarPago = new javax.swing.JButton();
        btnMantenimientoEstudiantes = new javax.swing.JButton();
        btnMantenimientoGrupos = new javax.swing.JButton();
        btnMantenimientoProfesores = new javax.swing.JButton();
        btnAdministracionUsuarios = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        btnMantenimientoCursos = new javax.swing.JButton();
        btnMantenimientoSistema = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLbFecha = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconImage(getIconImage());
        setName("Principal"); // NOI18N
        setSize(new java.awt.Dimension(2000, 1000));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logopdf.png"))); // NOI18N

        btnAgregarGrupo.setBackground(new java.awt.Color(0, 133, 202));
        btnAgregarGrupo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAgregarGrupo.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarGrupo.setText("Agregar grupo");
        btnAgregarGrupo.setBorder(null);
        btnAgregarGrupo.setBorderPainted(false);
        btnAgregarGrupo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarGrupoActionPerformed(evt);
            }
        });

        btnMatricularEstudiante.setBackground(new java.awt.Color(0, 133, 202));
        btnMatricularEstudiante.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnMatricularEstudiante.setForeground(new java.awt.Color(255, 255, 255));
        btnMatricularEstudiante.setText("Matricular estudiante");
        btnMatricularEstudiante.setBorder(null);
        btnMatricularEstudiante.setBorderPainted(false);
        btnMatricularEstudiante.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMatricularEstudiante.setRolloverEnabled(false);
        btnMatricularEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMatricularEstudianteActionPerformed(evt);
            }
        });

        btnAgregarProfesor.setBackground(new java.awt.Color(0, 133, 202));
        btnAgregarProfesor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAgregarProfesor.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarProfesor.setText("Agregar profesor");
        btnAgregarProfesor.setBorder(null);
        btnAgregarProfesor.setBorderPainted(false);
        btnAgregarProfesor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProfesorActionPerformed(evt);
            }
        });

        btnAgregarCursos.setBackground(new java.awt.Color(0, 133, 202));
        btnAgregarCursos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAgregarCursos.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarCursos.setText("Agregar curso");
        btnAgregarCursos.setBorder(null);
        btnAgregarCursos.setBorderPainted(false);
        btnAgregarCursos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarCursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCursosActionPerformed(evt);
            }
        });

        btnAgregarPago.setBackground(new java.awt.Color(0, 133, 202));
        btnAgregarPago.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAgregarPago.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarPago.setText("Pagos");
        btnAgregarPago.setBorder(null);
        btnAgregarPago.setBorderPainted(false);
        btnAgregarPago.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPagoActionPerformed(evt);
            }
        });

        btnMantenimientoEstudiantes.setBackground(new java.awt.Color(0, 133, 202));
        btnMantenimientoEstudiantes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnMantenimientoEstudiantes.setForeground(new java.awt.Color(255, 255, 255));
        btnMantenimientoEstudiantes.setText("Mantenimiento estudiantes");
        btnMantenimientoEstudiantes.setBorder(null);
        btnMantenimientoEstudiantes.setBorderPainted(false);
        btnMantenimientoEstudiantes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMantenimientoEstudiantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMantenimientoEstudiantesActionPerformed(evt);
            }
        });

        btnMantenimientoGrupos.setBackground(new java.awt.Color(0, 133, 202));
        btnMantenimientoGrupos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnMantenimientoGrupos.setForeground(new java.awt.Color(255, 255, 255));
        btnMantenimientoGrupos.setText("Mantenimiento grupos");
        btnMantenimientoGrupos.setBorder(null);
        btnMantenimientoGrupos.setBorderPainted(false);
        btnMantenimientoGrupos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMantenimientoGrupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMantenimientoGruposActionPerformed(evt);
            }
        });

        btnMantenimientoProfesores.setBackground(new java.awt.Color(0, 133, 202));
        btnMantenimientoProfesores.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnMantenimientoProfesores.setForeground(new java.awt.Color(255, 255, 255));
        btnMantenimientoProfesores.setText("Mantenimiento profesores");
        btnMantenimientoProfesores.setBorder(null);
        btnMantenimientoProfesores.setBorderPainted(false);
        btnMantenimientoProfesores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMantenimientoProfesores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMantenimientoProfesoresActionPerformed(evt);
            }
        });

        btnAdministracionUsuarios.setBackground(new java.awt.Color(0, 133, 202));
        btnAdministracionUsuarios.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAdministracionUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        btnAdministracionUsuarios.setText("Administración de usuarios");
        btnAdministracionUsuarios.setBorder(null);
        btnAdministracionUsuarios.setBorderPainted(false);
        btnAdministracionUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdministracionUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdministracionUsuariosActionPerformed(evt);
            }
        });

        btnCerrarSesion.setBackground(new java.awt.Color(0, 133, 202));
        btnCerrarSesion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion.setText("Cerrar sesión");
        btnCerrarSesion.setBorder(null);
        btnCerrarSesion.setBorderPainted(false);
        btnCerrarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        btnMantenimientoCursos.setBackground(new java.awt.Color(0, 133, 202));
        btnMantenimientoCursos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnMantenimientoCursos.setForeground(new java.awt.Color(255, 255, 255));
        btnMantenimientoCursos.setText("Mantenimiento cursos");
        btnMantenimientoCursos.setBorder(null);
        btnMantenimientoCursos.setBorderPainted(false);
        btnMantenimientoCursos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMantenimientoCursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMantenimientoCursosActionPerformed(evt);
            }
        });

        btnMantenimientoSistema.setBackground(new java.awt.Color(0, 133, 202));
        btnMantenimientoSistema.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnMantenimientoSistema.setForeground(new java.awt.Color(255, 255, 255));
        btnMantenimientoSistema.setText("Mantenimiento del sistema");
        btnMantenimientoSistema.setBorder(null);
        btnMantenimientoSistema.setBorderPainted(false);
        btnMantenimientoSistema.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMantenimientoSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMantenimientoSistemaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnMatricularEstudiante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnAgregarProfesor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnAgregarGrupo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnAgregarCursos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnAgregarPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMantenimientoEstudiantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMantenimientoProfesores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMantenimientoGrupos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnAdministracionUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCerrarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(btnMantenimientoCursos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMantenimientoSistema, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMatricularEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregarProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregarGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregarCursos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregarPago, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMantenimientoEstudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMantenimientoProfesores, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMantenimientoGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMantenimientoCursos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdministracionUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMantenimientoSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(144, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 133, 202));

        jPanel4.setBackground(new java.awt.Color(232, 17, 41));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 12, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Sistema Interno de Control de Estudiantes, SICE");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Usuario:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Nombre");

        jLbFecha.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLbFecha.setForeground(new java.awt.Color(255, 255, 255));
        jLbFecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLbFecha.setText("Fecha");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLbFecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setBackground(new java.awt.Color(0, 133, 202));

        jPanel6.setBackground(new java.awt.Color(232, 17, 41));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 12, Short.MAX_VALUE)
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Centro Cultural Corporación Costa Rica");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Acerca de");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Ayuda");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sice.jpeg"))); // NOI18N
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(166, 166, 166))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(262, 262, 262))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1290, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 673, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMatricularEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMatricularEstudianteActionPerformed
        matriculaEstudiante = new MatriculaEstudiante(this.icon,this.conexion,this.rs,this.st);
        matriculaEstudiante.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMatricularEstudianteActionPerformed

    private void btnAdministracionUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministracionUsuariosActionPerformed
        administracionUsuarios = new AdministracionUsuarios(this.icon,this.conexion,this.rs,this.st);
        administracionUsuarios.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAdministracionUsuariosActionPerformed

    private void btnMantenimientoEstudiantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMantenimientoEstudiantesActionPerformed
        mantenimientoEstudiantes = new MantenimientoEstudiante(this.icon,this.conexion,this.rs,this.st);
        mantenimientoEstudiantes.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMantenimientoEstudiantesActionPerformed

    private void btnAgregarProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProfesorActionPerformed
        AgregarProfesor agregarProfesor = new AgregarProfesor(this.icon,this.conexion,this.rs,this.st);
        agregarProfesor.setVisible(true);
    }//GEN-LAST:event_btnAgregarProfesorActionPerformed

    private void btnAgregarGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarGrupoActionPerformed
        try {
            agregarGrupo = new AgregarGrupo(this.icon,this.conexion,this.rs,this.st);
        } catch (SQLException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        agregarGrupo.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAgregarGrupoActionPerformed

    private void btnAgregarCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCursosActionPerformed
        agregarCurso = new AgregarCurso(this.icon,this.conexion,this.rs,this.st);
        agregarCurso.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAgregarCursosActionPerformed

    private void btnAgregarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPagoActionPerformed
        try {
            pago = new Pagos(this.icon,this.conexion,this.rs,this.st);
        } catch (SQLException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        pago.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAgregarPagoActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
       login = new Login();
       this.conexion.desconectar();
       login.setVisible(true); 
       this.dispose();
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnMantenimientoGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMantenimientoGruposActionPerformed
        mantenimientoGrupos = new MantenimientoGrupos(this.icon,this.conexion,this.rs,this.st);
        mantenimientoGrupos.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMantenimientoGruposActionPerformed

    private void btnMantenimientoCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMantenimientoCursosActionPerformed
        mantenimientoCursos = new MantenimientoCursos(this.icon);
        mantenimientoCursos.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMantenimientoCursosActionPerformed

    private void btnMantenimientoSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMantenimientoSistemaActionPerformed
    }//GEN-LAST:event_btnMantenimientoSistemaActionPerformed

    private void btnMantenimientoProfesoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMantenimientoProfesoresActionPerformed
        mantenimientoProfesores = new MantenimientoProfesor(this.icon,this.conexion,this.rs,this.st);
        mantenimientoProfesores.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMantenimientoProfesoresActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        AcercaDe acercaDe = new AcercaDe();
        acercaDe.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdministracionUsuarios;
    private javax.swing.JButton btnAgregarCursos;
    private javax.swing.JButton btnAgregarGrupo;
    private javax.swing.JButton btnAgregarPago;
    private javax.swing.JButton btnAgregarProfesor;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnMantenimientoCursos;
    private javax.swing.JButton btnMantenimientoEstudiantes;
    private javax.swing.JButton btnMantenimientoGrupos;
    private javax.swing.JButton btnMantenimientoProfesores;
    private javax.swing.JButton btnMantenimientoSistema;
    private javax.swing.JButton btnMatricularEstudiante;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLbFecha;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables
}
