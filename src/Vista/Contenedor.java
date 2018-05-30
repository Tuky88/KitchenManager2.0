/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import Controlador.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Alumno
 */
public class Contenedor extends javax.swing.JFrame {

    public JTabbedPane menu;
    public int bandera=0;
    public Admin admin;
    public Ventana mesa1,mesa2,mesa3,mesa4,mesa5,out1,out2;
    public ControladorVenta cv1;
    public ControladorVenta cv2;
    public ControladorVenta cv3;
    public ControladorVenta cv4;
    public ControladorVenta cv5;
    public ControladorVenta cv6;
    public ControladorVenta cv7;
    public Contenedor() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
  try {
             UIManager.setLookAndFeel("UpperEssential.UpperEssentialLookAndFeel");
         } catch (UnsupportedLookAndFeelException ex) {
             Logger.getLogger(Contenedor.class.getName()).log(Level.SEVERE, null, ex);
         }
  setTitle("Bull Truck System ");
    
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/logo.png")).getImage());
        ((JPanel)getContentPane()).setOpaque(false);
        ImageIcon uno=new ImageIcon(this.getClass().getResource("/imagenes/loguin_2.png"));
        JLabel fondo= new JLabel();
        fondo.setIcon(uno);
        getLayeredPane().add(fondo,JLayeredPane.FRAME_CONTENT_LAYER);
        fondo.setBounds(0,0,uno.getIconWidth(),uno.getIconHeight());
         menu=new JTabbedPane();
         mesa1= new Ventana();
         mesa2= new Ventana();
         mesa3= new Ventana();
         mesa4= new Ventana();
         mesa5= new Ventana();
         out1= new Ventana();
         out2= new Ventana();
        admin=new Admin();
        admin.setVisible(false);
        menu.addTab("Mesa 1",mesa1);
        menu.addTab("Mesa 2",mesa2);
        menu.addTab("Mesa 3",mesa3);
        menu.addTab("Mesa 4",mesa4);
        menu.addTab("Mesa 5",mesa5);
        menu.addTab("OUT 1",out1);
        menu.addTab("OUT 2",out2);
        menu.addTab("ADMINISTRADOR",admin);
        this.add(menu);
        //controladores
         cv1=new ControladorVenta(mesa1);
         cv2=new ControladorVenta(mesa2);
         cv3=new ControladorVenta(mesa3);
         cv4=new ControladorVenta(mesa4);
         cv5=new ControladorVenta(mesa5);
         cv6=new ControladorVenta(out1);
         cv7=new ControladorVenta(out2);
        this.pack();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 839, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

   
}
