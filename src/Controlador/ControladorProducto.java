/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.ProductoDAO;
import Vista.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Axel Reyez
 */
public class ControladorProducto {
    
    private Productos vista;
    public ControladorProducto(Productos vista)
    {
        this.vista=vista;
        this.vista.Agregar.addActionListener(new AgregarProducto());
        this.vista.Limpiar.addActionListener(new LimpiarVentana());
        this.vista.TipoP.addItemListener(new CambioTipo());
        this.vista.tabla.addMouseListener(new ObtenerDeTabla());
        this.vista.Eliminar.addActionListener(new Eliminar());
    }

    
    private class Eliminar implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            ProductoDAO pro=new ProductoDAO();
            pro.Eliminar(vista.NombreP.getText(),(String)vista.TipoP.getSelectedItem());
        }
        
    }
    private class AgregarProducto implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
    {
        String id="",tipo;
        double precio;
        int stock,cuantosD=0,cuantosS=0;
        
        
        if(!vista.NombreP.getText().equals("") && vista.TipoP.getSelectedIndex()!=0 )
            
        {
            
            ProductoDAO modeloProducto=new  ProductoDAO();
            id=vista.NombreP.getText();
            tipo=(String)vista.TipoP.getSelectedItem();
        precio=Double.parseDouble(vista.PrecioP.getText());
        stock=Integer.parseInt(vista.StockP.getText());
        if(modeloProducto.ValidarID(id, tipo))
        {
          if(modeloProducto.ObtenerStock(id, tipo)!=stock)
          {
              modeloProducto.ActualizarStock(id,tipo,stock);
              JOptionPane.showMessageDialog(vista, "Stock modificado");
          }
          if(modeloProducto.ObtenerPrecio(id,tipo)!=precio)
          {
              modeloProducto.ActualizarPrecio(id,tipo, precio);
              JOptionPane.showMessageDialog(vista, "Precio modificado");    
          }
        }
        else
        {
            modeloProducto.insertar(id, precio, stock, cuantosD, cuantosS, tipo,"","");
        }
        }
        
    }
    }
    private class LimpiarVentana implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
    {
        vista.NombreP.setText("");
        vista.PrecioP.setText("");
        vista.StockP.setText("");
        vista.TipoP.setSelectedIndex(0);
    }
        
    }
    private class CambioTipo implements ItemListener
    {

        @Override
        public void itemStateChanged(ItemEvent e) {
            for(int i=0;i<vista.modelo.getRowCount();i++)
        {
            vista.modelo.removeRow(i);
            i=i-1;
        }
            ProductoDAO pro=new ProductoDAO();
            String tipo=(String)vista.TipoP.getSelectedItem();
            ResultSet rs=pro.ObtenerPorTipo(tipo);
            try {
                while(rs.next())
                {
                   String nombre=rs.getString("id");
                   Double precio=rs.getDouble("precio");
                   int stock=rs.getInt("stock");
                   Object data[]={nombre,precio,stock};
                   vista.modelo.addRow(data);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        
    }
    private class ObtenerDeTabla implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e) {
            
          String nombre=(String)vista.tabla.getValueAt(vista.tabla.getSelectedRow(),0);
          double precio=(double)vista.tabla.getValueAt(vista.tabla.getSelectedRow(),1);
          int Stock=(int)vista.tabla.getValueAt(vista.tabla.getSelectedRow(),2);
          vista.NombreP.setText(nombre);
          vista.PrecioP.setText(Double.toString(precio));
          vista.StockP.setText(Integer.toString(Stock));
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
       
    

    
}
