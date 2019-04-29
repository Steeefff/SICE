package Vista;

import Datos.Conexion;
import Datos.GruposDAO;
import Datos.MatriculasDAO;
import Datos.PagosDAO;
import Datos.PersonasDAO;
import Modelos.Grupos;
import Modelos.Matriculas;
import Modelos.Personas;
import Modelos.Vista_mantenimientoGrupos;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*
  @author Grupo #30 Ingeniería 2018-2019 
 *@author David Rodríguez Zamora
 *@author Katherine Jiménez Soto
 *@author Melany Monge Montero
 *@author Stefanny Villalobos Uva
 * Proyecto de Ingeniería - Universidad Nacional de Costa Rica
 * Sistema Interno de Control de Estudiantes, SICE
 * Profesor: Rafael Alvarado Arley
 * Dueño del producto: Yensy Soto, Centro Cultural Corporación Costa Rica
 * Versión 1.2, 21/10/2018
 * Since 1.0
 */


public class MatriculaEstudiante extends javax.swing.JFrame {

    VentanaPrincipal ventanaPrincipal;
    AgregarEstudiante agregarEstudiante;
    private static Conexion conexion;
    public static ResultSet rs;
    public static Statement st;
    Image icon;
    PersonasDAO personasDAO;
    PagosDAO pagosDAO;
    Vector<Personas> estudiantes;
    GruposDAO gruposDAO;
    MatriculasDAO matriculasDAO;
    Vector<Vista_mantenimientoGrupos> vista_mantenimientoGrupos;
    
    public MatriculaEstudiante(Image icono,Conexion conexion,ResultSet rs,Statement st) throws SQLException {
        initComponents();
        this.setSize(1290,710); 
        setLocationRelativeTo(null);
        this.setResizable(false);
        fecha();
        setTitle("SICE - Matrícula de Estudiante");
        this.icon = icono;
        this.conexion=conexion;
        this.rs=rs;
        this.st=st;
        cargarEstudiantes();  
        cargarGrupos();
        
        this.comboEstudiantes.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(comboEstudiantes.getSelectedIndex()==0){
                    txtIdentificacion.setText("");
                    comboGrupos.setEnabled(false);
                    comboGrupos.setSelectedIndex(0);
                }else{ 
                    comboGrupos.setEnabled(true);
                    txtIdentificacion.setText(estudiantes.get(comboEstudiantes.getSelectedIndex()-1).getIdentificacion());
               }
            }
        });
        
        this.comboGrupos.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(comboGrupos.getSelectedIndex()==0){
                    txtCurso.setText("");
                    txtHorario.setText("");
                    btnMatricular.setEnabled(false);
                    comboProxFechaPago.setEnabled(false);
                }else{
                    txtCurso.setText(vista_mantenimientoGrupos.get(comboGrupos.getSelectedIndex()-1).getNombreCurso());
                    txtHorario.setText(vista_mantenimientoGrupos.get(comboGrupos.getSelectedIndex()-1).getDia()+" "+vista_mantenimientoGrupos.get(comboGrupos.getSelectedIndex()-1).getHoraInicio());
                    comboProxFechaPago.setEnabled(true);
                    btnMatricular.setEnabled(true);
                }
            }
        });
        
        setIconImage(this.icon);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void fecha(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaComoCadena = sdf.format(new Date());
        jLbFecha.setText(fechaComoCadena);
    }

    private void cargarEstudiantes() throws SQLException{
        this.personasDAO=new PersonasDAO(this.conexion,this.rs,this.st);
        this.estudiantes=this.personasDAO.listarPersonas(1);
        try{     
            this.comboEstudiantes.addItem("Seleccione un estudiante");
            for(int i=0; i<this.estudiantes.size(); i++){
                this.comboEstudiantes.addItem(this.estudiantes.get(i).getNombre()+" "+this.estudiantes.get(i).getApellido1()+" "+this.estudiantes.get(i).getApellido2());
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Hubo un error al cargar el listado de los estudiantes. Intente de nuevo por favor y si el error persiste contacte a su equipo de TI.");
            e.printStackTrace();
        }
    }
    
    private void cargarGrupos() throws SQLException{
        this.gruposDAO=new GruposDAO(this.conexion,this.rs,this.st);
        this.vista_mantenimientoGrupos=this.gruposDAO.listarGruposVista();
        try{     
            this.comboGrupos.addItem("Seleccione un grupo");
            for(int i=0; i<this.vista_mantenimientoGrupos.size(); i++){
                this.comboGrupos.addItem(this.vista_mantenimientoGrupos.get(i).getNombreGrupo());
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Hubo un error al cargar el listado de los grupos. Intente de nuevo por favor y si el error persiste contacte a su equipo de TI.");
            e.printStackTrace();
        }
    }
    
    public boolean validar() throws SQLException{
        boolean valido=false;
        if(this.comboEstudiantes.getSelectedIndex()!=0)
            if(this.comboGrupos.getSelectedIndex()!=0){
                    if(this.comboProxFechaPago.getCalendar()!=null){
                        if(validaRangoFecha()==true){
                            if(this.validaFechaPago()==true)
                                valido=true;
                        }else{
                            JOptionPane.showMessageDialog(null, "La fecha para el próximo pago no debe de ser anterior ni igual a la fecha actual. Por favor intente de nuevo.");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Es necesario que seleccione una fecha para próximo pago de matrícula.");
                    }
            }else{
                JOptionPane.showMessageDialog(null, "Es necesario que seleccione un grupo a matricular.");
            }
        return valido;
    }
    private boolean validaRangoFecha(){
        boolean valido=false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");        
        Date actual=new Date();
        System.out.println("Fecha actual: "+actual);
        Date ingresada=this.comboProxFechaPago.getDate();
        System.out.println("Fecha ingresada: "+ingresada);
        int resultado=ingresada.compareTo(actual);
        System.out.println("Resultado: "+resultado);
        if(resultado>0)
            valido=true;    
        return valido;
    }
    
    private boolean validaFechaPago() throws SQLException{
        boolean valido=false;
        this.pagosDAO =new PagosDAO(this.conexion,this.rs,this.st);
        if(this.pagosDAO.validaPendienteDePago(this.txtIdentificacion.getText())=="")
            valido=true;
        else{
            JOptionPane.showMessageDialog(null,"El estudiante tiene pendientes de pago. Para matricular es necesario cancelar los pendientes.");
        }
        return valido;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtIdentificacion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        comboGrupos = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        btnMatricular = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        btnEstudianteNuevo = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLbFecha = new javax.swing.JLabel();
        comboEstudiantes = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaMatriculas = new javax.swing.JTable();
        txtCurso = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtHorario = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        comboProxFechaPago = new com.toedter.calendar.JDateChooser();
        btnVolver1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jPanel8.setMaximumSize(new java.awt.Dimension(1260, 35));
        jPanel8.setMinimumSize(new java.awt.Dimension(1260, 35));
        jPanel8.setPreferredSize(new java.awt.Dimension(1260, 35));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Matrícula de Estudiantes");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sice.jpeg"))); // NOI18N
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Nombre del estudiante:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Identificación del estudiante:");

        txtIdentificacion.setEditable(false);
        txtIdentificacion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Grupos:");

        comboGrupos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        comboGrupos.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Curso:");

        btnMatricular.setBackground(new java.awt.Color(0, 133, 202));
        btnMatricular.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnMatricular.setForeground(new java.awt.Color(255, 255, 255));
        btnMatricular.setText("Matricular");
        btnMatricular.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMatricular.setEnabled(false);
        btnMatricular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMatricularActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Cursos matriculados anteriormente");

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

        btnEstudianteNuevo.setBackground(new java.awt.Color(0, 133, 202));
        btnEstudianteNuevo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEstudianteNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnEstudianteNuevo.setText("¿Estudiante nuevo?");
        btnEstudianteNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEstudianteNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstudianteNuevoActionPerformed(evt);
            }
        });

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

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Usuario:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Nombre");

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
                .addGap(18, 18, 18)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(jLabel12)
                        .addComponent(jLbFecha)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        comboEstudiantes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        tablaMatriculas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tablaMatriculas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Curso", "Idioma", "Grupo", "Profesor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaMatriculas);

        txtCurso.setEditable(false);
        txtCurso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Horario:");

        txtHorario.setEditable(false);
        txtHorario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Próxima fecha de pago:");

        comboProxFechaPago.setDateFormatString("yyyy/MM/dd");
        comboProxFechaPago.setEnabled(false);
        comboProxFechaPago.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        btnVolver1.setBackground(new java.awt.Color(0, 133, 202));
        btnVolver1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnVolver1.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver1.setText("Buscar");
        btnVolver1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVolver1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolver1ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Seleccione un estudiante para buscar registro de matrículas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboEstudiantes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtIdentificacion)
                            .addComponent(comboGrupos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnMatricular, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtCurso)
                            .addComponent(txtHorario)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel13)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(btnEstudianteNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel14))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(comboProxFechaPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(341, 341, 341))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 944, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnVolver1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                        .addGap(491, 491, 491))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(182, 182, 182))))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(comboEstudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(btnEstudianteNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboProxFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnMatricular, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(btnVolver1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(7, 7, 7)))
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEstudianteNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstudianteNuevoActionPerformed
        agregarEstudiante = new AgregarEstudiante(this.icon,this.conexion,this.rs,this.st);
        agregarEstudiante.setVisible(true);
    }//GEN-LAST:event_btnEstudianteNuevoActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        ventanaPrincipal = new VentanaPrincipal(this.icon,this.conexion,this.rs,this.st);
        ventanaPrincipal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnMatricularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMatricularActionPerformed
        try {
            if(this.validar()==true){
                String identificacion = this.txtIdentificacion.getText();
                matricular();
                this.limpiar();
                mostrarTabla(identificacion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MatriculaEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnMatricularActionPerformed

    private void btnVolver1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolver1ActionPerformed
        if(this.comboEstudiantes.getSelectedIndex()>0){
            mostrarTabla(this.txtIdentificacion.getText());
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione un estudiante.");
        }
    }//GEN-LAST:event_btnVolver1ActionPerformed

    private void matricular(){
        //Crea un objeto de tipo curso
        Matriculas matricula= new Matriculas();
        //Se cargan los atributos del curso
        matricula.setIdEstudiante(this.txtIdentificacion.getText());
        matricula.setNombreGrupo(this.comboGrupos.getItemAt(this.comboGrupos.getSelectedIndex()));
        matricula.setFechaProxPago(((JTextField)this.comboProxFechaPago.getDateEditor().getUiComponent()).getText());
        //Envía el curso al método insertaCurso del cursosDAO que inserta en la base de datos
        matriculasDAO = new MatriculasDAO(this.conexion,this.rs,this.st);        
        String respuestaRegistro = matriculasDAO.matricular(matricula);
        //Si respuestaRegistro es diferente de null quiere decir que se ingresó la matricula correctamente
        if(respuestaRegistro!=null){
            JOptionPane.showMessageDialog(null, respuestaRegistro);
            limpiar();
        }else{
         JOptionPane.showMessageDialog(null, "No se pudo matricular, intente de nuevo. Si el error persiste contacte a su equipo de TI.");
        }
    }
    
    private void limpiar(){
        this.comboEstudiantes.setSelectedIndex(0);
        this.comboProxFechaPago.setDate(new Date());
        this.comboProxFechaPago.setCalendar(null);
    }
    
    public void mostrarTabla( String identificacion){
        DefaultTableModel modelo;
        matriculasDAO=new MatriculasDAO(this.conexion,this.rs,this.st);
        modelo = matriculasDAO.mostrarMatriculas(identificacion);
        tablaMatriculas.setModel(modelo);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEstudianteNuevo;
    private javax.swing.JButton btnMatricular;
    private javax.swing.JButton btnVolver;
    private javax.swing.JButton btnVolver1;
    private javax.swing.JComboBox<String> comboEstudiantes;
    private javax.swing.JComboBox<String> comboGrupos;
    private com.toedter.calendar.JDateChooser comboProxFechaPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLbFecha;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaMatriculas;
    private javax.swing.JTextField txtCurso;
    private javax.swing.JTextField txtHorario;
    private javax.swing.JTextField txtIdentificacion;
    // End of variables declaration//GEN-END:variables
}
