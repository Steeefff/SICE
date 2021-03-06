package Vista;

import Datos.Conexion;
import Datos.CursosDAO;
import Modelos.Cursos;
import Modelos.Requisitos;
import static Vista.AdministracionUsuarios.lblNombre;
import static Vista.AdministracionUsuarios.lblUsuario;
import java.awt.FlowLayout;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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

public class AgregarCurso extends javax.swing.JFrame {

    VentanaPrincipal ventanaPrincipal;
    Image icon;
    private static Conexion conexion;
    public static ResultSet rs;
    public static Statement st;
    CursosDAO cursosDAO;
    Vector<JCheckBox> cursosCheckBox = new Vector<JCheckBox>();
    Vector<Cursos> vectorCursos = new Vector<Cursos>();
    
    public AgregarCurso(Image icono,Conexion conexion,ResultSet rs,Statement st) {
        initComponents();
        this.setSize(1295,710); 
        setLocationRelativeTo(null);
        this.setResizable(false);
        fecha();
        setTitle("SICE - Agregar Curso");
        this.icon = icono;
        this.conexion=conexion;
        this.rs=rs; 
        this.st=st;
        this.cargarIdiomas();
        this.lblRequisitos1.setVisible(false);
        this.lblRequisitos2.setVisible(false);
        
       this.comboIdiomas.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    textoVisible();
                    cargarCursosParaRequisitos(comboIdiomas.getSelectedIndex());
                } catch (SQLException ex) {
                    Logger.getLogger(AgregarCurso.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        setIconImage(this.icon);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void textoVisible(){
        if(this.comboIdiomas.getSelectedIndex()==0){
            this.lblRequisitos1.setVisible(false);
            this.lblRequisitos2.setVisible(false);
            this.btnGuardar.setEnabled(false);
        }else{
            this.lblRequisitos1.setVisible(true);
            this.lblRequisitos2.setVisible(true);
            this.btnGuardar.setEnabled(true);
        }
    }
    
    public void fecha(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaComoCadena = sdf.format(new Date());
        jLbFecha.setText(fechaComoCadena);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        comboIdiomas = new javax.swing.JComboBox<>();
        txtBuscar = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        jLbFecha = new javax.swing.JLabel();
        lblRequisitos1 = new javax.swing.JLabel();
        panelCursos = new javax.swing.JPanel();
        lblRequisitos2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCursos = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setAutoscrolls(true);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Agregar Curso");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sice.jpeg"))); // NOI18N
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Nombre:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Idioma:");

        txtNombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtNombre.setAutoscrolls(false);
        txtNombre.setEnabled(false);
        txtNombre.setFocusCycleRoot(true);
        txtNombre.setNextFocusableComponent(comboIdiomas);
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(0, 133, 202));
        btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar curso");
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(0, 133, 202));
        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Cursos existentes");

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

        comboIdiomas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        comboIdiomas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        comboIdiomas.setEnabled(false);
        comboIdiomas.setNextFocusableComponent(panelCursos);

        txtBuscar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

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

        lblUsuario.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsuario.setText("Rol:");

        lblNombre.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombre.setText("Nombre");

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
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblUsuario)
                    .addComponent(lblNombre)
                    .addComponent(jLbFecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblRequisitos1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblRequisitos1.setText("Si el curso tiene otros cursos como requisito para ser matriculado ");
        lblRequisitos1.setEnabled(false);

        panelCursos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        panelCursos.setMaximumSize(new java.awt.Dimension(488, 132));
        panelCursos.setMinimumSize(new java.awt.Dimension(488, 132));
        panelCursos.setName(""); // NOI18N

        javax.swing.GroupLayout panelCursosLayout = new javax.swing.GroupLayout(panelCursos);
        panelCursos.setLayout(panelCursosLayout);
        panelCursosLayout.setHorizontalGroup(
            panelCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelCursosLayout.setVerticalGroup(
            panelCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblRequisitos2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblRequisitos2.setText("por favor márquelos. Si no tiene requisitos oprima el botón de guardar.");
        lblRequisitos2.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Código:");

        txtCodigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCodigo.setAutoscrolls(false);
        txtCodigo.setNextFocusableComponent(txtNombre);
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        tablaCursos = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Requisitos", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaCursos);

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
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelCursos, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel2))
                                    .addGap(79, 79, 79)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboIdiomas, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(lblRequisitos2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblRequisitos1)))))
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(264, 264, 264)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel9)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 121, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 1262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboIdiomas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addComponent(lblRequisitos1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblRequisitos2)
                        .addGap(18, 18, 18)
                        .addComponent(panelCursos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        if(this.txtCodigo.getText().length()>=0)  {
            try {
                buscar();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        ventanaPrincipal = new VentanaPrincipal(this.icon,this.conexion,this.rs,this.st);
        ventanaPrincipal.lblUsuario.setText(lblUsuario.getText().toString());
        ventanaPrincipal.lblNombre.setText(lblNombre.getText());
        ventanaPrincipal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(this.validaFormulario()==true){
            insertarCurso();
            this.limpiarBusqueda();
            this.deshabilitar();
            try {
               this.cursosDAO=new CursosDAO(this.conexion,this.rs,this.st);
                DefaultTableModel modelo;
                modelo = this.cursosDAO.mostrarBuscarCursosEnAgregar(this.txtBuscar.getText());
                this.tablaCursos.setModel(modelo);
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Hubo un error al cargar la tabla con los cursos. Si el error persiste contacte a su equipo de TI.");
                e.printStackTrace();
            }
        }else
            JOptionPane.showMessageDialog(null, "Es necesario completar los espacios para código y nombre del curso, y seleccionar un idioma. Por favor vuelva a intentar.");
    }//GEN-LAST:event_btnGuardarActionPerformed

    private boolean validaFormulario(){
        boolean valido = false;
        if(!this.txtCodigo.getText().equals(""))
            if(!this.txtNombre.getText().equals(""))
                if(this.validaComboIdioma()==true)
                    valido=true;
        return valido;
    }
    
    private boolean validaComboIdioma(){
        boolean valido= false;
        if(this.comboIdiomas.getSelectedIndex()!=0)
            valido=true;
        return valido;
    }
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
    try {
           this.cursosDAO=new CursosDAO(this.conexion,this.rs,this.st);
            DefaultTableModel modelo;
            modelo = this.cursosDAO.mostrarBuscarCursosEnAgregar(this.txtBuscar.getText());
            this.tablaCursos.setModel(modelo);
        }catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Hubo un error al cargar la tabla con los cursos. Si el error persiste contacte a su equipo de TI.");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased

    }//GEN-LAST:event_txtNombreKeyReleased
       
    private void insertarCurso(){
        //Crea un objeto de tipo curso
        Cursos curso= new Cursos();
        //Se cargan los atributos del curso
        curso.setIdcurso(txtCodigo.getText());
        curso.setNombre(txtNombre.getText());
        curso.setIdIdioma(comboIdiomas.getSelectedIndex());
        curso.setEstado(1);
        int cantidadRequisitos=0;
        for(int i=0; i<this.cursosCheckBox.size(); i++){
            if(this.cursosCheckBox.get(i).isSelected()==true)
                cantidadRequisitos++;
        }
        String[] requisitos = new String[cantidadRequisitos];
        int Aux=0;
        for(int a=0; a<this.cursosCheckBox.size(); a++){
            if(this.cursosCheckBox.get(a).isSelected()==true){
                    for(int j=0; j<this.vectorCursos.size(); j++){
                            if(this.cursosCheckBox.get(a).getText().equals(vectorCursos.get(j).getNombre())){
                                requisitos[Aux]=this.vectorCursos.get(j).getIdcurso();
                                System.out.println("COD: "+this.vectorCursos.get(j).getIdcurso());
                                System.out.println("VEC: "+this.vectorCursos.get(j).getNombre()+" CHECKBOX: "+this.cursosCheckBox.get(a).getText());
                                Aux++;
                            }
                    }
            }
        }
        for(int i=0; i<requisitos.length; i++){
        System.out.println("REQUISITO "+i+" "+requisitos[i]);
        }
        curso.setRequisito(requisitos);
        //Envía el curso al método insertaCurso del cursosDAO que inserta en la base de datos
        cursosDAO = new CursosDAO(this.conexion,this.rs,this.st);
        String respuestaRegistro = cursosDAO.insertarCurso(curso,requisitos);
        //Si respuestaRegistro es diferente de null quiere decir que se ingresó el profesor correctamente
        if(respuestaRegistro!=null){
            JOptionPane.showMessageDialog(null, respuestaRegistro);
            Limpiar();
        }else{
         JOptionPane.showMessageDialog(null, "Ese curso ya existe en el registro.");
        }
    }
    
    
    //Este metodo limpia el formulario después de agregar un profesor
    public void Limpiar(){
        this.txtNombre.setText("");
        this.txtCodigo.setText("");
        this.comboIdiomas.setSelectedIndex(0);
        this.panelCursos.removeAll();
    }
        
    public void limpiarBusqueda(){
        this.comboIdiomas.setSelectedIndex(0);
        this.txtNombre.setText("");
        if(this.cursosCheckBox.size()>0)            
              this.cursosCheckBox.clear();           
        this.panelCursos.removeAll();
        this.panelCursos.revalidate();
        this.panelCursos.repaint();
    }
    
    public void habilitar(){
        this.comboIdiomas.setEnabled(true);
        this.txtNombre.setEnabled(true);
        this.btnGuardar.setEnabled(true);
        for(int i=0; i<this.cursosCheckBox.size(); i++)
            this.cursosCheckBox.get(i).setEnabled(true);
    }
    
    public void deshabilitar(){
        this.comboIdiomas.setEnabled(false);
        this.txtNombre.setEnabled(false);
        this.btnGuardar.setEnabled(false);
        for(int i=0; i<this.cursosCheckBox.size(); i++)
            this.cursosCheckBox.get(i).setEnabled(false);
    }
    
    public void cargarIdiomas(){
        String sql = "SELECT nombre FROM sice.idiomas";
        try{     
            rs = st.executeQuery(sql);
            this.comboIdiomas.addItem("Seleccione un idioma");
            while(rs.next()){
                this.comboIdiomas.addItem(rs.getString("nombre"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
   
    public void buscar() throws SQLException{
        //Se crea un objeto de tipo Cursos
        Cursos cursos= null;
        try {
            //Se llama a buscarCurso en CursosDAO que valida si existe o no el nombre en la tabla de cursos
            //cursosDAO es un objeto de la clase CursosDAO
            cursosDAO = new CursosDAO(this.conexion,this.rs,this.st);
            cursos = cursosDAO.buscarCursoCodigo(this.txtCodigo.getText());
            if(cursos==null){
                //Se limpian los datos de las busquedas anteriores y se habilita el formulario para agregar el curso
                limpiarBusqueda();
                //Habilita el formulario
                habilitar();
            }else{//Si el nombre ya existe en la base de datos se carga la infomacion de ese curso con el
                //formulario inabilitado para editar
                    
                    this.txtNombre.setText(cursos.getNombre());
                    this.comboIdiomas.setSelectedIndex(cursos.getIdIdioma());
                    setCursosRequisitos(cursos.getIdcurso());
                    this.btnGuardar.setEnabled(false);
                    deshabilitar();
                    JOptionPane.showMessageDialog(null, "Ya existe un curso con ese código. Curso: "+cursos.getIdcurso()+" "+cursos.getNombre()+" Idioma: "+this.comboIdiomas.getItemAt(cursos.getIdIdioma()));            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }  
    }
    
    public void setCursosRequisitos(String idcurso) throws SQLException{
        this.cursosDAO=new CursosDAO(this.conexion,this.rs,this.st);
        Vector<Cursos> vecCursosAux= cursosDAO.buscarCursosRequisitos(idcurso);
        for(int i=0; i<this.cursosCheckBox.size(); i++){
            for(int j=0; j<vecCursosAux.size(); j++){
                if(this.cursosCheckBox.get(i).getText().equals(vecCursosAux.get(j).getNombre())){
                    this.cursosCheckBox.get(i).setSelected(true);
                }
            }
        }
    }
    
    public void cargarCursosParaRequisitos(int idIdioma) throws SQLException{
        if(this.cursosCheckBox.size()>0)            
              this.cursosCheckBox.clear();           
        this.panelCursos.removeAll();
        this.panelCursos.revalidate();
        this.panelCursos.repaint();
        cursosDAO=new CursosDAO(this.conexion,this.rs,this.st);
        vectorCursos = cursosDAO.buscarCursosPorIdioma(idIdioma);
        this.panelCursos.setLayout(new GridLayout(5,3,5,5));
        
        try{     
            //Rellenamos primero el cursosCheckBox
            for(int i=0; i<vectorCursos.size(); i++){
                cursosCheckBox.add(new JCheckBox(vectorCursos.get(i).getNombre().toString()));
            }
            //Despues agregamos en el panel
            for(int i=0; i<cursosCheckBox.size(); i++){   
               this.panelCursos.add(cursosCheckBox.get(i));
               cursosCheckBox.get(i).setEnabled(true);
            }         
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> comboIdiomas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLbFecha;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblRequisitos1;
    private javax.swing.JLabel lblRequisitos2;
    public static javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel panelCursos;
    private javax.swing.JTable tablaCursos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
