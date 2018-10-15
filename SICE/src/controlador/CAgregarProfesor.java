
package controlador;

import Datos.GenerosDAO;
import Datos.IdiomasDAO;
import Datos.PersonasDAO;
import Modelos.ComboItem;
import Modelos.Generos;
import Modelos.Idiomas;
import Modelos.Personas;
import Vista.Agregar_Profesor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author fany-
 */
public class CAgregarProfesor implements ActionListener,KeyListener{
    
    Agregar_Profesor VAgreProf;
    PersonasDAO MAgreProf;
    GenerosDAO gd;
    IdiomasDAO id;

    public CAgregarProfesor() {
        VAgreProf = new Agregar_Profesor();
        MAgreProf = new PersonasDAO();
        gd = new GenerosDAO();
        id = new IdiomasDAO();
        this.VAgreProf = VAgreProf;
        this.MAgreProf = MAgreProf;
        VAgreProf.txtApellido1.addActionListener(this);
        VAgreProf.txtApellido2.addActionListener(this);
        VAgreProf.txtCedula.addActionListener(this);
        VAgreProf.txtCorreo.addActionListener(this);
        VAgreProf.txtDireccion.addActionListener(this);
        VAgreProf.comboGenero.addActionListener(this);
        VAgreProf.txtNombre.addActionListener(this);
        VAgreProf.txtTelefono.addActionListener(this);
        VAgreProf.btnGuardar.addActionListener(this);
        VAgreProf.btnVolver.addActionListener(this);
        //VAgreProf.comboFechaNacimiento.addActionListener(this);
        VAgreProf.comboIdiomas.addActionListener(this);
        
        //Cargar generos
        /*ArrayList<Generos> listaG = gd.listarGeneros();
        for(int i=0;i<listaG.size();i++){
            VAgreProf.comboGenero.addItem(new ComboItem("yy", "yy"));
        }
        
        //Cargar idiomas
        ArrayList<Idiomas> listaI = id.listarIdiomas();
        for(int i=0;i<listaG.size();i++){
            VAgreProf.comboIdiomas.addItem(new ComboItem(key, value));
        }*/
        
        
    }
 
    
    public void Limpiar(){
        VAgreProf.txtApellido1.setText("");
        VAgreProf.txtApellido2.setText("");
        VAgreProf.txtCedula.setText("");
        VAgreProf.txtCorreo.setText("");
        VAgreProf.txtDireccion.setText("");
        VAgreProf.comboGenero.setSelectedIndex(0);
        VAgreProf.txtNombre.setText("");
        VAgreProf.txtTelefono.setText("");
        VAgreProf.comboFechaNacimiento.setDate(new Date());
        VAgreProf.comboIdiomas.setSelectedIndex(0);
         
     }
   

    @Override
    public void actionPerformed(ActionEvent ae) {
            ///////////////////////////////////////////////////BOTON GUARDAR////////////////////////////////////////////////
    
         if(ae.getSource() == VAgreProf.btnGuardar){
             System.err.println("CLcika");
             //cargandole los datos ingresados de la vista a la persona
            Personas p= new Personas();
            p.setCedula(Integer.parseInt(VAgreProf.txtCedula.getText()));
            p.setApellido1(VAgreProf.txtApellido1.getText());
            p.setApellido2(VAgreProf.txtApellido2.getText());
            p.setCorreo(VAgreProf.txtCorreo.getText());
            p.setDireccion(VAgreProf.txtDireccion.getText());
            p.setGenero(VAgreProf.comboGenero.getSelectedIndex());
            p.setNombre(VAgreProf.txtNombre.getText());
            p.setTelefono(Integer.parseInt(VAgreProf.txtTelefono.getText()));
            p.setFechaNacimiento(VAgreProf.comboFechaNacimiento.getDateFormatString());
            p.setIdioma(VAgreProf.comboIdiomas.getSelectedIndex());
            p.setIdTipoPersona(2);//Se pone 2 porque es profesor
            
            //despues mandamos la persona al metodo que lo inserta en la base de datos 
            
            String respuestaRegistro = MAgreProf.insertarPersona(p);
            
            if(respuestaRegistro!=null){
                JOptionPane.showMessageDialog(null, respuestaRegistro);
                Limpiar();
            }
            else{
                JOptionPane.showMessageDialog(null, "Registro Erróneo");
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
