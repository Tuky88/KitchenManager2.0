/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.ComprasDAO;
import Vista.Compras;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexander
 */
public class ControladorCompras {
    public Compras compras;

    public ControladorCompras(Compras compras) {
        this.compras = compras;
        this.compras.Registrar.addActionListener(new AgregarCompra());
        this.compras.IDT.addKeyListener(new AutocompletarID());
        this.compras.Limpiar.addActionListener(new Limpiar());
        this.compras.tabla.addMouseListener(new ObtenerDeTabla());
        this.compras.Actualizar.addActionListener(new MostrarDeFecha());
        this.compras.Eliminar.addActionListener(new EliminarCompra());
    }
    private class Limpiar implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            compras.DistribuidorT.setText("");
            compras.IDT.setText("");
            compras.PrecioT.setText("");
            compras.ProductoT.setText("");
            
        }
        
    }
    private class AgregarCompra implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            String dist=compras.DistribuidorT.getText();
            String id=compras.IDT.getText();
            String precio=compras.PrecioT.getText();
            String producto=compras.ProductoT.getText();
            String fecha=compras.fecha.getText();
            if(!dist.equals("") && !id.equals("") && !precio.equals("") && !producto.equals(""))
            {
                ComprasDAO com=new ComprasDAO();
                com.RegistrarCompra(id, producto,Double.parseDouble(precio),fecha,dist);
                Object data[]={id,dist,producto,precio};
                compras.model.addRow(data);
            }
            else
            {
                JOptionPane.showMessageDialog(compras,"Verifique todos los campos");
            }
           
        }
        
    }
    private class EliminarCompra implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            String dist=(String)compras.tabla.getValueAt(compras.tabla.getSelectedRow(),1);
            String id=(String)compras.tabla.getValueAt(compras.tabla.getSelectedRow(),0);
            String precio=(String)compras.tabla.getValueAt(compras.tabla.getSelectedRow(),3);
            String producto=(String)compras.tabla.getValueAt(compras.tabla.getSelectedRow(),2);
            String fecha=compras.fecha.getText();
            if(!dist.equals("") && !id.equals("") && !precio.equals("") && !producto.equals(""))
            {
                ComprasDAO com=new ComprasDAO();
                com.EliminarCompra(id,producto,Double.parseDouble(precio),fecha,dist);
                compras.model.removeRow(compras.tabla.getSelectedRow());
                
            }
            else
            {
                JOptionPane.showMessageDialog(compras,"Verifique todos los campos");
            }
            
            
        }
        
    }
    private class ObtenerDeTabla implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e) {
        String dist=(String)compras.tabla.getValueAt(compras.tabla.getSelectedRow(),1);
            String id=(String)compras.tabla.getValueAt(compras.tabla.getSelectedRow(),0);
            String precio=(String)compras.tabla.getValueAt(compras.tabla.getSelectedRow(),3);
            String producto=(String)compras.tabla.getValueAt(compras.tabla.getSelectedRow(),2);
            compras.DistribuidorT.setText(dist);
            compras.IDT.setText(id);
            compras.PrecioT.setText(precio);
            compras.ProductoT.setText(producto);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }

        
        
    }
    private class MostrarDeFecha implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            String fecha=compras.fecha.getText();
            ComprasDAO com=new ComprasDAO();
            ResultSet rs=com.BuscarFecha(fecha);
            
            try {
                while(rs.next())
                {
                    String dist=rs.getString("Nombre");
            String id=rs.getString("id");
            String precio=rs.getString("precio");
            String producto=rs.getString("producto");
            Object data[]={id,dist,producto,precio};
            compras.model.addRow(data);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControladorCompras.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    private class AutocompletarID implements KeyListener
    {

        @Override
        public void keyTyped(KeyEvent e) {
           
        }

        @Override
        public void keyPressed(KeyEvent e) {
                    char car=(char) e.getKeyCode();
if(car==e.VK_ENTER)
{
    String id=compras.IDT.getText();
    ComprasDAO com=new ComprasDAO();
    String nombre="";
    nombre=com.ObtenerNombre(id);
    compras.DistribuidorT.setText(nombre);
}
        }

        @Override
        public void keyReleased(KeyEvent e) {
           
        }

        
        
    }
    
    
}
