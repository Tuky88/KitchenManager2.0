/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.ComplementoDAO;
import DAO.ConsultaDAO;
import DAO.ProductoDAO;
import Vista.Ventana;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Bebida;
import modelo.CajaRegistradora;
import modelo.Carne;
import modelo.Comanda;
import modelo.Extra;
import modelo.Producto;
import modelo.Usuario;

/**
 *
 * @author Alexander
 */
public class ControladorVenta {
    private Ventana ventana;

    public ControladorVenta(Ventana ventana) {
        this.ventana = ventana;
        this.ventana.Agregar.addActionListener(new agregarProducto());
        this.ventana.Corral.addActionListener(new corralSeleccionado());
        this.ventana.Fierro.addActionListener(new fierroSeleccionado());
        this.ventana.Especial.addActionListener(new especialSeleccionado());
        this.ventana.Bebida.addActionListener(new bebidaSeleccionado());
        this.ventana.Extra.addActionListener(new extraSeleccionado());
        this.ventana.Corral.addItemListener(new CambioEstado());
        this.ventana.Fierro.addItemListener(new CambioEstado());
        this.ventana.Especial.addItemListener(new CambioEstado());
        this.ventana.Bebida.addItemListener(new CambioEstado());
        this.ventana.Extra.addItemListener(new CambioEstado());
        this.ventana.Ccorral.addItemListener(new Validar());
        this.ventana.Cfierro.addItemListener(new Validar());
        this.ventana.Extras.addItemListener(new Validar());
        this.ventana.Pan.addItemListener(new Validar());
        this.ventana.Sabor.addItemListener(new Validar());
        this.ventana.Tamaño.addItemListener(new Validar());
        this.ventana.Termino.addItemListener(new Validar());
        this.ventana.cEspecial.addItemListener(new Validar());
        this.ventana.guarnicion.addItemListener(new Validar());
        this.ventana.Actualizar.addActionListener(new LlenarCombos());
        this.ventana.CancelarO.addActionListener(new CancelarOrden());
        this.ventana.FinalizarO.addActionListener(new FinalizarOrden());
        this.ventana.RecibidoT.addKeyListener(new Cobra());
        this.ventana.Eliminar.addActionListener(new EliminarTabla());
        this.ventana.Duplicar.addActionListener(new DuplicarTabla());
        this.ventana.pila= new Stack<Producto>();
        this.ventana.pila1= new Stack<Producto>();
    }
    private class DuplicarTabla implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
           String comanda=(String)ventana.tabla.getValueAt(ventana.tabla.getSelectedRow(),0);
            Double precio=(Double)ventana.tabla.getValueAt(ventana.tabla.getSelectedRow(),1);
            Object data[]={comanda,precio};
            ventana.modelo.addRow(data);
            ventana.TotalT.setText(Double.toString(Double.parseDouble(ventana.TotalT.getText())+precio));
            
        }
        
    }
    private class EliminarTabla implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            String comanda=(String)ventana.tabla.getValueAt(ventana.tabla.getSelectedRow(),0);
            Double precio=(Double)ventana.tabla.getValueAt(ventana.tabla.getSelectedRow(),1);
            ventana.modelo.removeRow(ventana.tabla.getSelectedRow());
            ventana.TotalT.setText(Double.toString(Double.parseDouble(ventana.TotalT.getText())-precio));
            while(!ventana.pila.empty())
            {
                Producto P;
                P=ventana.pila.pop();
                if(P.getNombre().equals(comanda.substring(0,comanda.indexOf("-"))))
                {
                    JOptionPane.showMessageDialog(ventana,"Eliminado");
                    break;
                }
                else
                {
                    ventana.pila1.push(P);
                }
            }
            while(!ventana.pila1.empty())
            {
                ventana.pila.push(ventana.pila1.pop());
            }
            
        }
        
    }
    private class Cobra implements KeyListener
    {

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            char car=(char) e.getKeyCode();
        if(car==e.VK_ENTER){
            double debe,paga,cambia;
            if(!ventana.RecibidoT.getText().equals(""))
            {
                paga=Double.parseDouble(ventana.RecibidoT.getText());
                debe=Double.parseDouble(ventana.TotalT.getText());
                cambia=paga-debe;
                if(cambia>=0)
                ventana.CambioT.setText(Double.toString(cambia));
                else
                ventana.CambioT.setText("$");
            }
            else
            ventana.RecibidoT.setText("0");
            
        }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }
        
    }
    private  class CancelarOrden implements ActionListener {

        

        @Override
        public void actionPerformed(ActionEvent e) {
          ventana.ClienteT.setText("-");
          for(int i=0;i<ventana.modelo.getRowCount();i++)
        {
            ventana.modelo.removeRow(i);
            i=i-1;
        }
         ventana.TotalT.setText("0");
         ventana.RecibidoT.setText("0");
         ventana.CambioT.setText("0");
         
        }
    }
    private class FinalizarOrden implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String cuanto=ventana.RecibidoT.getText();
            Usuario user=new Usuario(ventana.ClienteT.getText(),Integer.parseInt(ventana.ComandaT.getText()));
            
            if(cuanto.equals(""))
            {
                JOptionPane.showMessageDialog(ventana,"Campo Vacio");
            }
            else
            {
                double pago=Double.parseDouble(cuanto);
                double total=Double.parseDouble(ventana.TotalT.getText());
                double cambio=pago-total;
                if(cambio>=0)
                {
                    String orden;
                    double precio;
                    user.generarComanda(Integer.parseInt(ventana.ComandaT.getText()));
                    Comanda c=new Comanda(user.getNombre(),Integer.parseInt(ventana.ComandaT.getText()));
                    int i=0;
                    while(!ventana.pila.empty())
                    {
                        Producto p=ventana.pila.pop();
                        orden=(String)ventana.tabla.getValueAt(i,0);
                        precio=(double)ventana.tabla.getValueAt(i,1);
                        p.setPrecio(precio);
                        p.restarStock(p.getNombre(),p.getPrecio());
                        p.Aumentar(p.getNombre(),p.getPrecio());
                        p.setNombre(orden);
                        user.Ordenar(p);
                        c.agregar(p);
                        i++;
                    }
                    
                    user.generarOrden();
                    user.crearTicket(total,c, pago,ventana.dia.getText(), ventana.Fecha.getText());
                    user.generarTicket();
                    CajaRegistradora caja=new CajaRegistradora(user.getTicket());
                    caja.ActualizarVentas(total);
                    caja.ActualizarTicket();
                    ventana.ComandaT.setText(Integer.toString(caja.getTicket()));
                    JOptionPane.showMessageDialog(ventana,"Su cambio es de:$"+cambio);
                    
                    ventana.CancelarO.doClick();
                }
                else
                {
                    JOptionPane.showMessageDialog(ventana,"Falta por pagar:$"+cambio*-1);
                }
            }
                      
            
        }
        
    }


    private class agregarProducto implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            String Comanda="",tipo;
            double precio=0;
            boolean estado=true;
            if(ventana.Corral.isSelected() || ventana.Fierro.isSelected() || ventana.Especial.isSelected() || ventana.Extra.isSelected() || ventana.Bebida.isSelected())
            {
               if(ventana.Corral.isSelected())
               {
                   tipo="Corral";
                   Carne c= new Carne((String)ventana.Ccorral.getSelectedItem(),(String)ventana.Termino.getSelectedItem(),(String)ventana.Pan.getSelectedItem(),
                   (String)ventana.guarnicion.getSelectedItem(),tipo);
                   JOptionPane.showMessageDialog(ventana, c.getNombre()+c.getTipoCarne() );
                   precio=c.buscarPrecio(c.getNombre(),c.getTipoCarne());
                   Comanda=c.getComanda();
                   if(c.validarStock(c.getNombre(), c.getTipoCarne()))
                   ventana.pila.push(c);
                   else
                   {
                       JOptionPane.showMessageDialog(ventana, "Ya no hay:"+c.getNombre());
                       estado=false;
                   }
               }
               else if(ventana.Fierro.isSelected())
               {
                   tipo="Fierro";
                   Carne c= new Carne((String)ventana.Cfierro.getSelectedItem(),(String)ventana.Termino.getSelectedItem(),(String)ventana.Pan.getSelectedItem(),
                   (String)ventana.guarnicion.getSelectedItem(),tipo);
                   precio=c.buscarPrecio(c.getNombre(),c.getTipoCarne());
                   Comanda=c.getComanda();
                   if(c.validarStock(c.getNombre(), c.getTipoCarne()))
                   ventana.pila.push(c);
                   else
                   {
                       JOptionPane.showMessageDialog(ventana, "Ya no hay:"+c.getNombre());
                       estado=false;
                   }
               }
               else if(ventana.Bebida.isSelected())
               {
                   tipo="Tamaño";
                   Bebida b=new Bebida((String)ventana.Sabor.getSelectedItem(),(String)ventana.Tamaño.getSelectedItem(),tipo);
                   precio=b.buscarPrecio(b.getNombre(),b.getTipo());
                   b.setTipo("Bebida");
                   Comanda=b.getComanda();
                   b.setTipo(tipo);
                   if(b.validarStock(b.getNombre(), b.getTipo()))
                   ventana.pila.push(b);
                   else
                   {
                       JOptionPane.showMessageDialog(ventana, "Ya no hay:"+b.getNombre());
                       estado=false;
                   }
               }
               else if(ventana.Especial.isSelected())
               {
                   tipo="Especial";
                   Carne c= new Carne((String)ventana.cEspecial.getSelectedItem(),(String)ventana.Termino.getSelectedItem(),(String)ventana.Pan.getSelectedItem(),
                   (String)ventana.guarnicion.getSelectedItem(),tipo);
                   precio=c.buscarPrecio(c.getNombre(),c.getTipoCarne());
                   Comanda=c.getComanda();
                   if(c.validarStock(c.getNombre(), c.getTipoCarne()))
                   ventana.pila.push(c);
                   else
                   {
                       JOptionPane.showMessageDialog(ventana, "Ya no hay:"+c.getNombre());
                       estado=false;
                   }
               }
               else if(ventana.Extra.isSelected())
               {
                   tipo="Extra";
                   Extra e=new Extra((String)ventana.Extras.getSelectedItem(),tipo);
                   precio=e.buscarPrecio(e.getNombre(),e.getTipo());
                   Comanda=e.getComanda();
                   if(e.validarStock(e.getNombre(), e.getTipo()))
                   ventana.pila.push(e);
                   else
                   {
                       JOptionPane.showMessageDialog(ventana, "Ya no hay:"+e.getNombre());
                       estado=false;
                   }
               }
               if(estado)
               {
                   Object data[]={Comanda,precio};
               ventana.TotalT.setText(Double.toString(Double.parseDouble(ventana.TotalT.getText())+precio));
               ventana.modelo.addRow(data);
               }
            }
            else
            {
                JOptionPane.showMessageDialog(ventana,"Seleccione un producto");
            }
        }
        
        
    }
    private class corralSeleccionado implements ActionListener{
    public void actionPerformed(java.awt.event.ActionEvent evt){
    boolean v=true;
    boolean f=false;
        if(ventana.Corral.isSelected()){
    ventana.Ccorral.setVisible(v);
 ventana.Cfierro.setVisible(f);
 ventana.cEspecial.setVisible(f);
 ventana.Extras.setVisible(f);
 ventana.Sabor.setVisible(f);
 ventana.Pan.setVisible(v);
 ventana.Tamaño.setVisible(f);
 ventana.guarnicion.setVisible(v);
 ventana.Termino.setVisible(v);
    }
    } 
    }
    
    private class fierroSeleccionado implements ActionListener{
    public void actionPerformed(java.awt.event.ActionEvent evt){
    boolean v=true;
    boolean f=false;
        if(ventana.Fierro.isSelected()){
    ventana.Ccorral.setVisible(f);
 ventana.Cfierro.setVisible(v);
 ventana.cEspecial.setVisible(f);
 ventana.Extras.setVisible(f);
 ventana.Sabor.setVisible(f);
 ventana.Pan.setVisible(v);
 ventana.Tamaño.setVisible(f);
 ventana.guarnicion.setVisible(v);
 ventana.Termino.setVisible(v);
    }
    } 
    }
    
    private class especialSeleccionado implements ActionListener{
    public void actionPerformed(java.awt.event.ActionEvent evt){
    boolean v=true;
    boolean f=false;
        if(ventana.Especial.isSelected()){
    ventana.Ccorral.setVisible(f);
 ventana.Cfierro.setVisible(f);
 ventana.cEspecial.setVisible(v);
 ventana.Extras.setVisible(f);
 ventana.Sabor.setVisible(f);
 ventana.Pan.setVisible(v);
 ventana.Tamaño.setVisible(f);
 ventana.guarnicion.setVisible(v);
 ventana.Termino.setVisible(v);
    }
    } 
    }
       
    private class bebidaSeleccionado implements ActionListener{
    public void actionPerformed(java.awt.event.ActionEvent evt){
    boolean v=true;
    boolean f=false;
        if(ventana.Bebida.isSelected()){
    ventana.Ccorral.setVisible(f);
 ventana.Cfierro.setVisible(f);
 ventana.cEspecial.setVisible(f);
 ventana.Extras.setVisible(f);
 ventana.Sabor.setVisible(v);
 ventana.Pan.setVisible(f);
 ventana.Tamaño.setVisible(v);
 ventana.guarnicion.setVisible(f);
 ventana.Termino.setVisible(f);
    }
    } 
    }
    
    private class extraSeleccionado implements ActionListener{
    public void actionPerformed(java.awt.event.ActionEvent evt){
    boolean v=true;
    boolean f=false;
        if(ventana.Extra.isSelected()){
    ventana.Ccorral.setVisible(f);
 ventana.Cfierro.setVisible(f);
 ventana.cEspecial.setVisible(f);
 ventana.Extras.setVisible(v);
 ventana.Sabor.setVisible(f);
 ventana.Pan.setVisible(f);
 ventana.Tamaño.setVisible(f);
 ventana.guarnicion.setVisible(f);
 ventana.Termino.setVisible(f);
    }
    } 
    }
    private class CambioEstado implements ItemListener
    {

        public void itemStateChanged(ItemEvent e) 
        {
        ventana.Agregar.setEnabled(false);
        ventana.Ccorral.setSelectedIndex(0);
        ventana.Cfierro.setSelectedIndex(0);
        ventana.guarnicion.setSelectedIndex(0);
        ventana.Pan.setSelectedIndex(0);
        ventana.cEspecial.setSelectedIndex(0);
        ventana.Extras.setSelectedIndex(0);
        ventana.Sabor.setSelectedIndex(0);
        ventana.Tamaño.setSelectedIndex(0);
        ventana.Termino.setSelectedIndex(0);
        }
        
    }
    private class Validar implements ItemListener
    {

        @Override
        public void itemStateChanged(ItemEvent e) 
        {
            if(ventana.Corral.isSelected())
            {
                if(ventana.Ccorral.getSelectedIndex()!=0 && ventana.Termino.getSelectedIndex()!=0 && ventana.guarnicion.getSelectedIndex()!=0 && ventana.Pan.getSelectedIndex()!=0)
                {
                    ventana.Agregar.setEnabled(true);
                }
                else
                {
                    ventana.Agregar.setEnabled(false);
                }
            }
            else if(ventana.Fierro.isSelected())
            {
                if(ventana.Cfierro.getSelectedIndex()!=0 && ventana.Termino.getSelectedIndex()!=0 && ventana.guarnicion.getSelectedIndex()!=0 && ventana.Pan.getSelectedIndex()!=0)
                {
                    ventana.Agregar.setEnabled(true);
                }
                else
                {
                    ventana.Agregar.setEnabled(false);
                }
            }
            else if(ventana.Especial.isSelected())
            {
                if(ventana.cEspecial.getSelectedIndex()!=0 && ventana.Termino.getSelectedIndex()!=0 && ventana.guarnicion.getSelectedIndex()!=0 && ventana.Pan.getSelectedIndex()!=0)
                {
                    ventana.Agregar.setEnabled(true);
                }
                else
                {
                    ventana.Agregar.setEnabled(false);
                }
            }
            else if(ventana.Bebida.isSelected())
            {
                if(ventana.Tamaño.getSelectedIndex()!=0 && ventana.Sabor.getSelectedIndex()!=0 )
                {
                    ventana.Agregar.setEnabled(true);
                }
                else
                {
                    ventana.Agregar.setEnabled(false);
                }
            }
            else if(ventana.Extra.isSelected())
            {
                if(ventana.Extras.getSelectedIndex()!=0)
                {
                    ventana.Agregar.setEnabled(true);
                }
                else
                {
                    ventana.Agregar.setEnabled(false);
                }
            }
                
        }
  
    }
    private class LlenarCombos implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            ComplementoDAO co=new ComplementoDAO();
            ventana.Ccorral.removeAllItems();
        ventana.Cfierro.removeAllItems();
        ventana.Extras.removeAllItems();
        ventana.Pan.removeAllItems();
        ventana.Sabor.removeAllItems();
        ventana.Tamaño.removeAllItems();
        ventana.Termino.removeAllItems();
        ventana.cEspecial.removeAllItems();
        ventana.guarnicion.removeAllItems();
        ventana.Ccorral.addItem((Object)"Corral");
        ventana.Cfierro.addItem((Object)"Fierro");
        ventana.Extras.addItem((Object)"Extras");
        ventana.Pan.addItem((Object)"Pan/Tortilla");
        ventana.Sabor.addItem((Object)"Sabor");
        ventana.Tamaño.addItem((Object)"Tamaño");
        ventana.Termino.addItem((Object)"Termino");
        ventana.cEspecial.addItem((Object)"Especial");
        ventana.guarnicion.addItem((Object)"Guarnicion");
        ResultSet rs;
        rs=co.getDatos("Pan/Tortilla");
            try {
                while(rs.next())
                {
                    ventana.Pan.addItem((Object)rs.getString("id"));
                }   } catch (SQLException ex) {
                Logger.getLogger(ControladorVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        rs=co.getDatos("Sabor");
            try {
                while(rs.next())
                {
                    ventana.Sabor.addItem((Object)rs.getString("id"));
                }   } catch (SQLException ex) {
                Logger.getLogger(ControladorVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        rs=co.getDatos("Guarnicion");
            try {
                while(rs.next())
                {
                    ventana.guarnicion.addItem((Object)rs.getString("id"));
                }   } catch (SQLException ex) {
                Logger.getLogger(ControladorVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        rs=co.getDatos("Termino");
            try {
                while(rs.next())
                {
                    ventana.Termino.addItem((Object)rs.getString("id"));
                }   } catch (SQLException ex) {
                Logger.getLogger(ControladorVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
            ProductoDAO pr=new ProductoDAO();
            rs=pr.ObtenerPorTipo("Extra");
            try {
                while(rs.next())
                {
                    ventana.Extras.addItem((Object)rs.getString("id"));
                }   } catch (SQLException ex) {
                Logger.getLogger(ControladorVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
            rs=pr.ObtenerPorTipo("Corral");
            try {
                while(rs.next())
                {
                    ventana.Ccorral.addItem((Object)rs.getString("id"));
                }   } catch (SQLException ex) {
                Logger.getLogger(ControladorVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
            rs=pr.ObtenerPorTipo("Fierro");
            try {
                while(rs.next())
                {
                    ventana.Cfierro.addItem((Object)rs.getString("id"));
                }   } catch (SQLException ex) {
                Logger.getLogger(ControladorVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
            rs=pr.ObtenerPorTipo("Especial");
            try {
                while(rs.next())
                {
                    ventana.cEspecial.addItem((Object)rs.getString("id"));
                }   } catch (SQLException ex) {
                Logger.getLogger(ControladorVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
                    rs=pr.ObtenerPorTipo("Tamaño");
            try {
                while(rs.next())
                {
                    ventana.Tamaño.addItem((Object)rs.getString("id"));
                }   } catch (SQLException ex) {
                Logger.getLogger(ControladorVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
