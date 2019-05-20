package Vista;

import Datos.Conexion;
import Datos.PermisosDAO;
import Datos.TipoPersonasDAO;
import Modelos.Permisos;
import Modelos.TipoPersonas;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/*
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
public class ModificarTipoPersona extends javax.swing.JFrame {

    TipoPersonasDAO tipoPersonasDAO;
    PermisosDAO permisosDAO;
    private static Conexion conexion;
    public static ResultSet rs;
    public static Statement st;
    Vector<JCheckBox> permisos = new Vector<>();
    Image icon;
    String rolBuscado;


    public ModificarTipoPersona(Image icono, Conexion conexion, ResultSet rs, Statement st, String rolBuscado) {
        initComponents();
        this.rolBuscado = rolBuscado;
        setLocationRelativeTo(null);
        this.setSize(570, 640);
        this.setResizable(false);
        this.icon = icono;
        this.conexion = conexion;
        this.rs = rs;
        this.st = st;
        setTitle("SICE - Modificar Tipo de persona");
        setIconImage(this.icon);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void Limpiar() {
        this.txtRol.setText("");
        this.txtDescripcion.setText("");
        this.btnModificar.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtRol = new javax.swing.JTextField();
        btnVolver = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jCheckBoxMatricular = new javax.swing.JCheckBox();
        jCheckBoxAgregarCurso = new javax.swing.JCheckBox();
        jCheckBoxAgregarGrupo = new javax.swing.JCheckBox();
        jCheckBoxAgregarProfesor = new javax.swing.JCheckBox();
        jCheckBoxMantenimientoCurso = new javax.swing.JCheckBox();
        jCheckBoxMantenimientoGrupo = new javax.swing.JCheckBox();
        jCheckBoxMantenimientoEstudiante = new javax.swing.JCheckBox();
        jCheckBoxMantenimientoProfesor = new javax.swing.JCheckBox();
        jCheckBoxMantenimientoSistema = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setName("Matricula Estudiantes"); // NOI18N

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Modificar tipo de persona");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sice.jpeg"))); // NOI18N
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Rol:");

        txtRol.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtRol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRolKeyReleased(evt);
            }
        });

        btnVolver.setBackground(new java.awt.Color(0, 133, 202));
        btnVolver.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver.setText("Volver");
        btnVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(0, 133, 202));
        btnModificar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setText("Modificar");
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Descripción:");

        txtDescripcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Permisos:");

        jCheckBoxMatricular.setText("Matricular");

        jCheckBoxAgregarCurso.setText("Agregar Curso");

        jCheckBoxAgregarGrupo.setText("Agregar Grupo");

        jCheckBoxAgregarProfesor.setText("Agregar Profesor");

        jCheckBoxMantenimientoCurso.setText("Mantenimiento Curso");

        jCheckBoxMantenimientoGrupo.setText("Mantenimiento Grupo");

        jCheckBoxMantenimientoEstudiante.setText("Mantenimiento Estudiante");

        jCheckBoxMantenimientoProfesor.setText("Mantenimiento Profesor");

        jCheckBoxMantenimientoSistema.setText("Mantenimiento Sistema");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(256, 256, 256))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtRol, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(151, 151, 151))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(243, 243, 243)
                                .addComponent(jLabel6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(229, 229, 229)
                                .addComponent(jLabel7)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDescripcion))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 47, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBoxAgregarProfesor)
                                    .addComponent(jCheckBoxMatricular))
                                .addGap(40, 40, 40))
                            .addComponent(jCheckBoxMantenimientoEstudiante, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBoxMantenimientoCurso)
                                    .addComponent(jCheckBoxAgregarCurso))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBoxAgregarGrupo)
                                    .addComponent(jCheckBoxMantenimientoGrupo)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCheckBoxMantenimientoProfesor)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBoxMantenimientoSistema)))))
                .addGap(36, 36, 36))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRol, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(4, 4, 4)
                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxAgregarCurso)
                    .addComponent(jCheckBoxMatricular)
                    .addComponent(jCheckBoxAgregarGrupo))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxMantenimientoCurso)
                    .addComponent(jCheckBoxAgregarProfesor)
                    .addComponent(jCheckBoxMantenimientoGrupo))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxMantenimientoEstudiante)
                    .addComponent(jCheckBoxMantenimientoProfesor)
                    .addComponent(jCheckBoxMantenimientoSistema))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)))))
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private boolean validaciones() {
        boolean completos = false;
        if (validarEspacios() == true) {
            completos = true;
        }
        return completos;
    }

    private boolean validarEspacios() {
        boolean completo = false;
        if (!this.txtRol.getText().equals("")) {
            completo = true;
        } else {
            JOptionPane.showMessageDialog(null, "Es necesario completar el espacio para el rol de la persona.");
        }
        if (!this.txtDescripcion.getText().equals("")) {
            completo = true;
        } else {
            JOptionPane.showMessageDialog(null, "Es necesario completar el espacio de la descripción la persona.");
        }
        return completo;
    }

    private boolean modificarTipoPersona() throws SQLException {
        //Crea un objeto de tipo tipoPersonas
        TipoPersonas tipoPersonas = new TipoPersonas();
        //Se cargan los atributos del tipoPersonas
        tipoPersonas.setRol(txtRol.getText());

        //Envía el idioma al método insertaRol del tipoPersonasDAO que inserta en la base de datos
        tipoPersonasDAO = new TipoPersonasDAO(this.conexion, this.rs, this.st);
        String respuestaRegistro = tipoPersonasDAO.modificarTipoPersona(tipoPersonas, this.rolBuscado);
        
        
        //Si respuestaRegistro es diferente de null quiere decir que se ingresó el tipoPersonas correctamente
        if (respuestaRegistro != null) {
            JOptionPane.showMessageDialog(null, respuestaRegistro);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "El rol " + tipoPersonas.getRol() + " ya existe en el registro.");
            return false;
        }
    }

    private boolean modificarPermisos() throws SQLException{
        Permisos permisos = new Permisos();
        /*1*/permisos.setDescripcion(txtDescripcion.getText());
        /*2*/permisos.setMatricular(jCheckBoxMatricular.isSelected());
        /*3*/permisos.setAgregarCurso(jCheckBoxAgregarCurso.isSelected());
        /*4*/permisos.setAgregarGrupo(jCheckBoxAgregarGrupo.isSelected());
        /*5*/permisos.setAgregarProfesor(jCheckBoxAgregarProfesor.isSelected());
        /*6*/permisos.setAgregarEstudiante(jCheckBoxMatricular.isSelected());//Se repite ya que agregarEstudiante sería lo mismo que matricular
        /*7*/permisos.setMantenimientoCurso(jCheckBoxMantenimientoCurso.isSelected());
        /*8*/permisos.setMantenimientoGrupo(jCheckBoxAgregarGrupo.isSelected());
        /*9*/permisos.setMantenimientoProfesor(jCheckBoxMantenimientoProfesor.isSelected());
        /*10*/permisos.setMantenimientoEstudiante(jCheckBoxMantenimientoEstudiante.isSelected());
        /*11*/permisos.setMantenimientoSistema(jCheckBoxMantenimientoSistema.isSelected());
        
        //CONSULTAMOS CUAL ID-PERMISO TENGO QUE MODIFICAR
        tipoPersonasDAO = new TipoPersonasDAO(this.conexion, this.rs, this.st);
        TipoPersonas tipoPersonas = new TipoPersonas();
        tipoPersonas = tipoPersonasDAO.buscarRegistro(txtRol.getText());

        permisosDAO = new PermisosDAO(this.conexion,this.rs,this.st);
        String respuestaRegistro = permisosDAO.modificarPermisos(permisos,tipoPersonas.getIdPermiso());//PASO EL ID-PERMISO
        
        
        //Si respuestaRegistro es diferente de null quiere decir que se ingresó el tipoPersonas correctamente
        if(respuestaRegistro!=null){
            JOptionPane.showMessageDialog(null, respuestaRegistro);
            Limpiar();
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "Error al agregar los permisos.");
            return false;
        }
    }
    
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if(validaciones()==true){
            try {
                if(modificarTipoPersona() == true){
                    modificarPermisos();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Problema al editar los permisos. Consulte con un técnico");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AgregarTipoPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    public void buscar(String rol) throws SQLException {
        //Se crea un objeto de tipoPersonas
        TipoPersonas tipoPersonas = new TipoPersonas();
        try {
            //Se llama a buscaReg en tipoPersonasDAO que validad si existe o no el rol en la base de datos
            //tipoPersonasDAO es un objeto de la clase TipoPersonasDAO
            tipoPersonasDAO = new TipoPersonasDAO(this.conexion, this.rs, this.st);
            tipoPersonas = tipoPersonasDAO.buscarRegistro(rol);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //Si el rol no existe buscarRegistro devuelve null
        if (tipoPersonas == null) {
            JOptionPane.showMessageDialog(null, "El rol que se intenta modificar no existe en el registro");
            this.btnModificar.setEnabled(false);
        } else {
            this.txtRol.setText(rol);
            
            //CONSULTAMOS CUAL ID-PERMISO TENGO QUE MODIFICAR
            tipoPersonasDAO = new TipoPersonasDAO(this.conexion, this.rs, this.st);
            tipoPersonas = tipoPersonasDAO.buscarRegistro(rol);
            
            //Cargamos la descricion y sus debidos permisos
            Permisos permisos = new Permisos();
            permisosDAO = new PermisosDAO(this.conexion,this.rs,this.st);
            permisos = permisosDAO.buscarRegistro(tipoPersonas.getIdPermiso());       
            
            System.out.println("importantre"+permisos.toString());
            txtDescripcion.setText(permisos.getDescripcion());
            jCheckBoxMatricular.setSelected(permisos.getMatricular());
            jCheckBoxAgregarCurso.setSelected(permisos.getAgregarCurso());
            jCheckBoxAgregarGrupo.setSelected(permisos.getAgregarGrupo());
            jCheckBoxAgregarProfesor.setSelected(permisos.getAgregarProfesor());
            //jCheckBoxMatricular.setSelected(permisos.getMatricular());
            jCheckBoxMantenimientoCurso.setSelected(permisos.getMantenimientoCurso());
            jCheckBoxMantenimientoGrupo.setSelected(permisos.getMantenimientoGrupo());
            jCheckBoxMantenimientoProfesor.setSelected(permisos.getMantenimientoProfesor());        
            jCheckBoxMantenimientoEstudiante.setSelected(permisos.getMantenimientoEstudiante());        
            jCheckBoxMantenimientoSistema.setSelected(permisos.getMantenimientoSistema());
                    
            this.btnModificar.setEnabled(true);
        }
    }

    private void txtRolKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRolKeyReleased
        this.btnModificar.setEnabled(true);
    }//GEN-LAST:event_txtRolKeyReleased

    private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JCheckBox jCheckBoxAgregarCurso;
    private javax.swing.JCheckBox jCheckBoxAgregarGrupo;
    private javax.swing.JCheckBox jCheckBoxAgregarProfesor;
    private javax.swing.JCheckBox jCheckBoxMantenimientoCurso;
    private javax.swing.JCheckBox jCheckBoxMantenimientoEstudiante;
    private javax.swing.JCheckBox jCheckBoxMantenimientoGrupo;
    private javax.swing.JCheckBox jCheckBoxMantenimientoProfesor;
    private javax.swing.JCheckBox jCheckBoxMantenimientoSistema;
    private javax.swing.JCheckBox jCheckBoxMatricular;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTextField txtDescripcion;
    public javax.swing.JTextField txtRol;
    // End of variables declaration//GEN-END:variables
}
