package Vista;

import Datos.Conexion;
import Datos.PersonasDAO;
import Modelos.Personas;
import static Vista.ModificarProfesor.rs;
import static Vista.ModificarProfesor.st;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

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

public class ModificarEstudiante extends javax.swing.JFrame {
        
    Image icon;
    PersonasDAO personasDAO;
    private static Conexion conexion;
    public static ResultSet rs;
    public static Statement st;
    
    public ModificarEstudiante(Image icono,Conexion conexion,ResultSet rs,Statement st) {
        initComponents();
        setLocationRelativeTo(null);
        this.setSize(590,768); 
        this.setResizable(false);
        setTitle("SICE - Modificar Estudiante");
        this.icon = icono;
        this.conexion=conexion;
        this.rs=rs;
        this.st=st;
        setIconImage(this.icon);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public void limpiar(){
        this.txtApellido1.setText("");
        this.txtApellido2.setText("");
        this.txtIdentificacion.setText("");
        this.txtCorreo.setText("");
        this.txtDireccion.setText("");
        this.comboGenero.setSelectedIndex(0);
        this.txtNombre.setText("");
        this.txtTelefono.setText("");
        this.comboFechaNacimiento.setCalendar(null);
    }
    
    public void cargarIdiomasGeneros(JComboBox generos){        
       String sql = "SELECT nombre FROM sice.generos";
        try{     
            rs = st.executeQuery(sql);
            this.comboGenero.addItem("Seleccione un género");
            while(rs.next()){
                this.comboGenero.addItem(rs.getString("nombre"));
            }
                    
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void mostrar(Personas persona) throws ParseException{
        this.txtIdentificacion.setText(" ");        
        habilitar();    
        txtIdentificacion.setText(persona.getIdentificacion());
        txtNombre.setText(persona.getNombre());
        txtApellido1.setText(persona.getApellido1());
        txtApellido2.setText(persona.getApellido2());
        txtCorreo.setText(persona.getCorreo());
        txtDireccion.setText(persona.getDireccion());
        txtTelefono.setText(Integer.toString(persona.getTelefono()));       
        this.comboGenero.setSelectedIndex(persona.getGenero());                    
        String dateValue = persona.getFechaNacimiento(); 
        java.util.Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateValue);
        this.comboFechaNacimiento.setDate(date);            
    } 
    
    public void buscar(String identificacion){
        //Se crea un objeto de tipo Personas
        Personas personas = new Personas();
        try {
            //Se llama a buscaReg en PersonasDAO que validad si existe o no la identificacion en la base de datos
            //personasDAO es un objeto de la clase PersonaDAO
            personasDAO = new PersonasDAO(this.conexion,this.rs,this.st);
            personas = personasDAO.buscarRegistro(identificacion);
            
            if(personas.getIdTipoPersona() == 2)
            {
                JOptionPane.showMessageDialog(null, "Error! Esta persona no es un estudiante");
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }  
        //Si la identificacion no existe buscarRegistro devuelve null
        if(personas==null){
           //Se limpian los datos de las busquedas anteriores y se habilita el formulario para agregar al profesor
           limpiar();
           //Habilita el formulario
           habilitar();
        }
        else{//Si la identificacion ya existe en la base de datos se carga la infomacion de ese profesor con el
            //formulario inabilitado para editar
            try {
                //muestra los datos del profesor encontrado en los espacios del formulario
                mostrar(personas);
                
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }
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
        txtIdentificacion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtApellido2 = new javax.swing.JTextField();
        txtApellido1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        comboGenero = new javax.swing.JComboBox<>();
        txtTelefono = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        btnvolver = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        comboFechaNacimiento = new com.toedter.calendar.JDateChooser();
        btnBuscar = new javax.swing.JButton();

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
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE))
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
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Modificar Estudiante");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        jLabel2.setText("Identificación:");

        txtIdentificacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtIdentificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdentificacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdentificacionKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Nombre:");

        txtNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Apellido 1:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Apellido 2:");

        txtApellido2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtApellido2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellido2KeyTyped(evt);
            }
        });

        txtApellido1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtApellido1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellido1KeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Fecha de nacimiento:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Género:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Teléfono:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Correo:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Dirección:");

        comboGenero.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        comboGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Femenino", "Masculino" }));
        comboGenero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txtTelefono.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtDireccion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtCorreo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCorreo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCorreoFocusLost(evt);
            }
        });

        btnvolver.setBackground(new java.awt.Color(0, 133, 202));
        btnvolver.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnvolver.setForeground(new java.awt.Color(255, 255, 255));
        btnvolver.setText("Volver");
        btnvolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnvolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvolverActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(0, 133, 202));
        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        comboFechaNacimiento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        btnBuscar.setBackground(new java.awt.Color(0, 133, 202));
        btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtApellido1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnvolver, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(txtApellido2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(comboFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addComponent(comboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(16, 16, 16))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel9)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(comboFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtApellido1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtApellido2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnvolver, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnvolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvolverActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnvolverActionPerformed

    private void insertarEstudinte(){
        //Crea un objeto de tipo persona
        Personas persona= new Personas();
        //Se cargan los atributos de la persona profesor
        persona.setIdentificacion(txtIdentificacion.getText());
        persona.setApellido1(this.txtApellido1.getText());
        persona.setApellido2(this.txtApellido2.getText());
        persona.setCorreo(this.txtCorreo.getText());
        persona.setDireccion(this.txtDireccion.getText());
        persona.setGenero(this.comboGenero.getSelectedIndex());
        persona.setNombre(this.txtNombre.getText());
        persona.setTelefono(Integer.parseInt(this.txtTelefono.getText().trim()));
        Date date = comboFechaNacimiento.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat(this.comboFechaNacimiento.getDateFormatString());
        persona.setFechaNacimiento(String.valueOf(sdf.format(date)));
        persona.setIdTipoPersona(1);//1 significa que es una persona estudiante

        //Envía la persona al método insertaPersona del personaDAO que inserta en la base de datos
        personasDAO = new PersonasDAO(this.conexion,this.rs,this.st);
                                                                   
        boolean respuestaRegistro = personasDAO.modificar(persona,this.txtIdentificacion.getText());
        //Si respuestaRegistro es diferente de null quiere decir que se ingresó el profesor correctamente
        if(respuestaRegistro==true){
            limpiar(); 
        }else{
         JOptionPane.showMessageDialog(null, "No se pudo modificar el registro Identificación: "+persona.getIdentificacion()+" Nombre: "+persona.getNombre()+" "+persona.getApellido1()+" "+persona.getApellido2());
        }
    }
    
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(validaciones()==true){
            insertarEstudinte();
            this.dispose();
        }        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String identificacion = txtIdentificacion.getText();
        buscar(txtIdentificacion.getText());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private boolean validaciones(){
        boolean completos=false; 
        if(validarEspacios()==true){
            if(validarTamanoTelefono()==true){
                if(validarEdad()==true){
                    completos=true;
                }
            }
        } 
        return completos;
    }
    
    private boolean validarEspacios(){
        boolean completos = false;
        if(!this.txtIdentificacion.getText().equals("") & !this.txtNombre.getText().equals("") & 
           !this.txtApellido1.getText().equals("") & !this.txtApellido2.getText().equals("") & 
           !this.txtCorreo.getText().equals("") & !this.txtDireccion.getText().equals("") & 
           !this.txtTelefono.getText().equals("") & this.comboGenero.getSelectedIndex()!=0)
                completos = true;
        else
            JOptionPane.showMessageDialog(null, "Es necesario completar todos los espacios.");
           return completos;
    }
    
    private boolean validarTamanoTelefono(){
        boolean valido = false;
        if(this.txtTelefono.getText().length()<8 || this.txtTelefono.getText().length()>9)
            JOptionPane.showMessageDialog(null, "El teléfono debe de tener un mínimo de 8 dígitos.");
        else
            valido = true;
       return valido;
    }
    
    private boolean validarEdad(){
        boolean mayorDeEdad=false;
        if(this.comboFechaNacimiento.getCalendar()!=null){
            int annioActual=0, annioMinimo=0, annioElegido=0;        
            Calendar cal= Calendar.getInstance(); 
            annioElegido= this.comboFechaNacimiento.getCalendar().get(Calendar.YEAR);
            annioActual=cal.get(Calendar.YEAR);
            annioMinimo=annioActual-annioElegido;
            System.out.print(annioActual);
            System.out.print(annioElegido);
            System.out.print(annioMinimo); 
            if(annioMinimo<=100 & annioMinimo>=7)
                mayorDeEdad=true;
            else
                JOptionPane.showMessageDialog(null,"Edad inválida. El estudiante debe de ser tener 7 años o ser mayor de 7 años.");
        }else
            JOptionPane.showMessageDialog(null, "Es necesario completar todos los espacios.");
        return mayorDeEdad;
    }
    
    private void txtIdentificacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyReleased
        if(this.txtIdentificacion.getText().length()>=9)   
            buscar(this.txtIdentificacion.getText());
    }//GEN-LAST:event_txtIdentificacionKeyReleased

    private void txtIdentificacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyTyped
        if(this.txtIdentificacion.getText().length()>=9)   
            buscar(this.txtIdentificacion.getText());
    }//GEN-LAST:event_txtIdentificacionKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        char car = evt.getKeyChar();
        if((car<'a' || car>'z') && (car<'A' || car>'Z') && (car != (char)KeyEvent.VK_BACK_SPACE)
           && (car != (char)KeyEvent.VK_SPACE)){
            
            evt.consume();
            JOptionPane.showMessageDialog(null, "Formato del nombre incorrecto,solo se permite ingresar letras. Por favor intente de nuevo.");
        }
        if(this.txtNombre.getText().length() >= 46){
            JOptionPane.showMessageDialog(null,"El nombre debe ser menor a 45 caracteres.");
            this.txtNombre.transferFocus();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtApellido1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellido1KeyTyped
        char car = evt.getKeyChar();
        if((car<'a' || car>'z') && (car<'A' || car>'Z') && (car != (char)KeyEvent.VK_BACK_SPACE)){
            
            evt.consume();
            JOptionPane.showMessageDialog(null, "Formato incorrecto,solo se permite ingresar letras. Por favor intente de nuevo.");
        }
        if(this.txtApellido1.getText().length() >= 46){
            JOptionPane.showMessageDialog(null,"El largo del apellido no puede ser mayor a 45 caracteres.");
            this.txtApellido1.transferFocus();
        }
    }//GEN-LAST:event_txtApellido1KeyTyped

    private void txtApellido2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellido2KeyTyped
        char car = evt.getKeyChar();
        if((car<'a' || car>'z') && (car<'A' || car>'Z') && (car != (char)KeyEvent.VK_BACK_SPACE)){            
            evt.consume();
            JOptionPane.showMessageDialog(null, "Formato incorrecto,solo se permite ingresar letras. Por favor intente de nuevo.");
        }
        if(this.txtApellido2.getText().length() >= 46){
            JOptionPane.showMessageDialog(null,"El largo del apellido no puede ser mayor a 45 caracteres.");
            this.txtApellido2.transferFocus();
        }
    }//GEN-LAST:event_txtApellido2KeyTyped

    public boolean isEmail(String correo){
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^[\\w\\\\\\+]+(\\.[\\w\\\\]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
        mat = pat.matcher(correo);
        if(mat.find()){
            return true;
        }else{
            return false;
        }
    }
    
    private void txtCorreoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorreoFocusLost
        if(isEmail(this.txtCorreo.getText())){
            
        }else{
            JOptionPane.showMessageDialog(null, "Formato de correo inválido, debe ser de la forma ejemplo@ejemplo.ej");
            this.txtCorreo.requestFocus();
        }
    }//GEN-LAST:event_txtCorreoFocusLost

    //Habilita los espacios del formulario y el boton de guardar
    public void habilitar(){
        this.txtApellido1.setEnabled(true);
        this.txtApellido2.setEnabled(true);
        this.txtCorreo.setEnabled(true);
        this.txtDireccion.setEnabled(true);
        this.txtNombre.setEnabled(true);
        this.txtTelefono.setEnabled(true);
        this.comboFechaNacimiento.setEnabled(true);
        this.comboGenero.setEnabled(true);
        this.btnGuardar.setEnabled(true);
    }
    
    //Deshabilita los espacios del formulario y el boton de guardar
    public void deshabilitar(){
        this.txtApellido1.setEnabled(false);
        this.txtApellido2.setEnabled(false);
        this.txtCorreo.setEnabled(false);
        this.txtDireccion.setEnabled(false);
        this.txtNombre.setEnabled(false);
        this.txtTelefono.setEnabled(false);
        this.comboFechaNacimiento.setEnabled(false);
        this.comboGenero.setEnabled(false);
        this.btnGuardar.setEnabled(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnvolver;
    private com.toedter.calendar.JDateChooser comboFechaNacimiento;
    private javax.swing.JComboBox<String> comboGenero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTextField txtApellido1;
    private javax.swing.JTextField txtApellido2;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
