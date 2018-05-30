/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.EmpleadoDAO;
import Vista.Contenedor;
import Vista.Ventana;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Alexander
 */
public class ControladorContenedor {
    private Contenedor contenedor;
    public ControladorContenedor(Contenedor contenedor)
    {
        this.contenedor=contenedor;
        this.contenedor.admin.barra.setVisible(false);
        this.contenedor.menu.addMouseListener(new Acceso());
        this.contenedor.addWindowListener(new Abrir());
        this.contenedor.mesa1.MoverA1.addActionListener(new moverA1());
        this.contenedor.mesa1.MoverA2.addActionListener(new moverA2());
        this.contenedor.mesa1.MoverA3.addActionListener(new moverA3());
        this.contenedor.mesa1.MoverA4.addActionListener(new moverA4());
        this.contenedor.mesa1.MoverA5.addActionListener(new moverA5());
        this.contenedor.mesa1.MoverA6.addActionListener(new moverO1());
        this.contenedor.mesa1.MoverA7.addActionListener(new moverO2());
        
        this.contenedor.mesa2.MoverA1.addActionListener(new moverA1());
        this.contenedor.mesa2.MoverA2.addActionListener(new moverA2());
        this.contenedor.mesa2.MoverA3.addActionListener(new moverA3());
        this.contenedor.mesa2.MoverA4.addActionListener(new moverA4());
        this.contenedor.mesa2.MoverA5.addActionListener(new moverA5());
        this.contenedor.mesa2.MoverA6.addActionListener(new moverO1());
        this.contenedor.mesa2.MoverA7.addActionListener(new moverO2());
                
        this.contenedor.mesa2.MoverA1.addActionListener(new moverA1());
        this.contenedor.mesa3.MoverA2.addActionListener(new moverA2());
        this.contenedor.mesa3.MoverA3.addActionListener(new moverA3());
        this.contenedor.mesa3.MoverA4.addActionListener(new moverA4());
        this.contenedor.mesa3.MoverA5.addActionListener(new moverA5());
        this.contenedor.mesa3.MoverA6.addActionListener(new moverO1());
        this.contenedor.mesa3.MoverA7.addActionListener(new moverO2());
                
        this.contenedor.mesa3.MoverA1.addActionListener(new moverA1());
        this.contenedor.mesa4.MoverA2.addActionListener(new moverA2());
        this.contenedor.mesa4.MoverA3.addActionListener(new moverA3());
        this.contenedor.mesa4.MoverA4.addActionListener(new moverA4());
        this.contenedor.mesa4.MoverA5.addActionListener(new moverA5());
        this.contenedor.mesa4.MoverA6.addActionListener(new moverO1());
        this.contenedor.mesa4.MoverA7.addActionListener(new moverO2());
                
        this.contenedor.mesa4.MoverA1.addActionListener(new moverA1());
        this.contenedor.mesa5.MoverA2.addActionListener(new moverA2());
        this.contenedor.mesa5.MoverA3.addActionListener(new moverA3());
        this.contenedor.mesa5.MoverA4.addActionListener(new moverA4());
        this.contenedor.mesa5.MoverA5.addActionListener(new moverA5());
        this.contenedor.mesa5.MoverA6.addActionListener(new moverO1());
        this.contenedor.mesa5.MoverA7.addActionListener(new moverO2());
                
        this.contenedor.out1.MoverA1.addActionListener(new moverA1());
        this.contenedor.out1.MoverA2.addActionListener(new moverA2());
        this.contenedor.out1.MoverA3.addActionListener(new moverA3());
        this.contenedor.out1.MoverA4.addActionListener(new moverA4());
        this.contenedor.out1.MoverA5.addActionListener(new moverA5());
        this.contenedor.out1.MoverA6.addActionListener(new moverO1());
        this.contenedor.out1.MoverA7.addActionListener(new moverO2());
        
        this.contenedor.out2.MoverA1.addActionListener(new moverA1());
        this.contenedor.out2.MoverA2.addActionListener(new moverA2());
        this.contenedor.out2.MoverA3.addActionListener(new moverA3());
        this.contenedor.out2.MoverA4.addActionListener(new moverA4());
        this.contenedor.out2.MoverA5.addActionListener(new moverA5());
        this.contenedor.out2.MoverA6.addActionListener(new moverO1());
        this.contenedor.out2.MoverA7.addActionListener(new moverO2());
        
    }
private class Acceso implements MouseListener
{

        @Override
        public void mouseClicked(MouseEvent me) {
            
                           if(contenedor.menu.getSelectedIndex()==7 && contenedor.bandera==0)           
{
    
    JTextField jf=new JTextField();
    JPasswordField jpf = new JPasswordField(); 
     JLabel titulo = new JLabel ("Usuario:"); 
     JLabel titulo1 = new JLabel ("Contraseña:"); 
     JOptionPane.showConfirmDialog (contenedor.menu, new Object[]{titulo,jf,titulo1, jpf}, "CONTENIDO RESTRINGIDO IDENTIFIQUESE", JOptionPane.OK_CANCEL_OPTION); 
     
     String user =jf.getText();
     char p[] = jpf.getPassword(); 
     String pass = new String(p); 
     
         EmpleadoDAO empleado=new EmpleadoDAO();
         int estado=empleado.validarPass(user, pass);
       
           if(estado==1)
                   {
                       contenedor.bandera=1;
                   }
        else
        {
            contenedor.bandera=0;
        }
            
        if(contenedor.bandera==1)
        {
           contenedor.admin.barra.setVisible(true);
           contenedor.admin.contraseña.usuariot.setText(user);
           contenedor.admin.estado.GerenteT.setText(user);
           contenedor.admin.estado.Ventas.doClick();
           contenedor.mesa1.Actualizar.doClick();
           contenedor.mesa2.Actualizar.doClick();
           contenedor.mesa3.Actualizar.doClick();
           contenedor.mesa4.Actualizar.doClick();
           contenedor.mesa5.Actualizar.doClick();
           contenedor.out1.Actualizar.doClick();
           contenedor.out2.Actualizar.doClick();
        }
            }
                           else
                           {
                               contenedor.bandera=0;
                               contenedor.admin.barra.setVisible(false);
                           }
          if(contenedor.menu.getSelectedIndex()==0)
          {
              contenedor.mesa1.Actualizar.doClick();
           contenedor.mesa2.Actualizar.doClick();
           contenedor.mesa3.Actualizar.doClick();
           contenedor.mesa4.Actualizar.doClick();
           contenedor.mesa5.Actualizar.doClick();
           contenedor.out1.Actualizar.doClick();
           contenedor.out2.Actualizar.doClick();
          }
                           
            //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mousePressed(MouseEvent me) {
     
        }

        @Override
        public void mouseReleased(MouseEvent me) {
 
        }

        @Override
        public void mouseEntered(MouseEvent me) {
        }

        @Override
        public void mouseExited(MouseEvent me) {
        }
}
    private class Abrir implements WindowListener
            {

            @Override
            public void windowOpened(WindowEvent e) {
                contenedor.mesa1.Actualizar.doClick();
                contenedor.mesa2.Actualizar.doClick();
                contenedor.mesa3.Actualizar.doClick();
                contenedor.mesa4.Actualizar.doClick();
                contenedor.mesa5.Actualizar.doClick();
                contenedor.out1.Actualizar.doClick();
                contenedor.out2.Actualizar.doClick();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                
            }

            @Override
            public void windowClosed(WindowEvent e) {
                
            }

            @Override
            public void windowIconified(WindowEvent e) {
                
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
              
            }

            @Override
            public void windowActivated(WindowEvent e) {
               
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                
            }
        
    }
    private class moverA1 implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
        switch(contenedor.menu.getSelectedIndex())
                {
            case 0:
                MoverA(contenedor.mesa1,contenedor.mesa1);
                break;
             case 1:
                MoverA(contenedor.mesa2,contenedor.mesa1);
                break;
             case 2:
                MoverA(contenedor.mesa3,contenedor.mesa1);
                break;
             case 3:
                MoverA(contenedor.mesa4,contenedor.mesa1);
                break;
             case 4:
                MoverA(contenedor.mesa5,contenedor.mesa1);
                break;
             case 5:
                MoverA(contenedor.out1,contenedor.mesa1);
                break;
             case 6:
                MoverA(contenedor.out2,contenedor.mesa1);
                break;
                }
        }
        
    }
    private class moverA2 implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
        switch(contenedor.menu.getSelectedIndex())
                {
            case 0:
                MoverA(contenedor.mesa1,contenedor.mesa2);
                break;
             case 1:
                MoverA(contenedor.mesa2,contenedor.mesa2);
                break;
             case 2:
                MoverA(contenedor.mesa3,contenedor.mesa2);
                break;
             case 3:
                MoverA(contenedor.mesa4,contenedor.mesa2);
                break;
             case 4:
                MoverA(contenedor.mesa5,contenedor.mesa2);
                break;
             case 5:
                MoverA(contenedor.out1,contenedor.mesa2);
                break;
             case 6:
                MoverA(contenedor.out2,contenedor.mesa2);
                break;
                }
        }
        
    }
    private class moverA3 implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
        switch(contenedor.menu.getSelectedIndex())
                {
            case 0:
                MoverA(contenedor.mesa1,contenedor.mesa3);
                break;
             case 1:
                MoverA(contenedor.mesa2,contenedor.mesa3);
                break;
             case 2:
                MoverA(contenedor.mesa3,contenedor.mesa3);
                break;
             case 3:
                MoverA(contenedor.mesa4,contenedor.mesa3);
                break;
             case 4:
                MoverA(contenedor.mesa5,contenedor.mesa3);
                break;
             case 5:
                MoverA(contenedor.out1,contenedor.mesa3);
                break;
             case 6:
                MoverA(contenedor.out2,contenedor.mesa3);
                break;
                }
        }
        
    }
    private class moverA4 implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
        switch(contenedor.menu.getSelectedIndex())
                {
            case 0:
                MoverA(contenedor.mesa1,contenedor.mesa4);
                break;
             case 1:
                MoverA(contenedor.mesa2,contenedor.mesa4);
                break;
             case 2:
                MoverA(contenedor.mesa3,contenedor.mesa4);
                break;
             case 3:
                MoverA(contenedor.mesa4,contenedor.mesa4);
                break;
             case 4:
                MoverA(contenedor.mesa5,contenedor.mesa4);
                break;
             case 5:
                MoverA(contenedor.out1,contenedor.mesa4);
                break;
             case 6:
                MoverA(contenedor.out2,contenedor.mesa4);
                break;
                }
        }
        
    }
    private class moverA5 implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
        switch(contenedor.menu.getSelectedIndex())
                {
            case 0:
                MoverA(contenedor.mesa1,contenedor.mesa5);
                break;
             case 1:
                MoverA(contenedor.mesa2,contenedor.mesa5);
                break;
             case 2:
                MoverA(contenedor.mesa3,contenedor.mesa5);
                break;
             case 3:
                MoverA(contenedor.mesa4,contenedor.mesa5);
                break;
             case 4:
                MoverA(contenedor.mesa5,contenedor.mesa5);
                break;
             case 5:
                MoverA(contenedor.out1,contenedor.mesa5);
                break;
             case 6:
                MoverA(contenedor.out2,contenedor.mesa5);
                break;
                }
        }
        
    }
    private class moverO1 implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
        switch(contenedor.menu.getSelectedIndex())
                {
            case 0:
                MoverA(contenedor.mesa1,contenedor.out1);
                break;
             case 1:
                MoverA(contenedor.mesa2,contenedor.out1);
                break;
             case 2:
                MoverA(contenedor.mesa3,contenedor.out1);
                break;
             case 3:
                MoverA(contenedor.mesa4,contenedor.out1);
                break;
             case 4:
                MoverA(contenedor.mesa5,contenedor.out1);
                break;
             case 5:
                MoverA(contenedor.out1,contenedor.out1);
                break;
             case 6:
                MoverA(contenedor.out2,contenedor.out1);
                break;
                }
        }
        
    }
private class moverO2 implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
        switch(contenedor.menu.getSelectedIndex())
                {
            case 0:
                MoverA(contenedor.mesa1,contenedor.out2);
                break;
             case 1:
                MoverA(contenedor.mesa2,contenedor.out2);
                break;
             case 2:
                MoverA(contenedor.mesa3,contenedor.out2);
                break;
             case 3:
                MoverA(contenedor.mesa4,contenedor.out2);
                break;
             case 4:
                MoverA(contenedor.mesa5,contenedor.out2);
                break;
             case 5:
                MoverA(contenedor.out1,contenedor.out2);
                break;
             case 6:
                MoverA(contenedor.out2,contenedor.out2);
                break;
                }
        }
        
    }
    
    public void MoverA(Ventana vieja,Ventana nueva)
        {
            nueva.TotalT.setText(vieja.TotalT.getText());
            vieja.TotalT.setText("0");
            nueva.pila.push(vieja.pila.pop());
            for(int i=0;i<vieja.tabla.getRowCount();i++)
            {
                String comanda=(String)vieja.tabla.getValueAt(i,0);
            Double precio=(Double)vieja.tabla.getValueAt(i,1);
            Object data[]={comanda,precio};
            nueva.modelo.addRow(data);
            vieja.modelo.removeRow(i);
            }
        }
    
}

