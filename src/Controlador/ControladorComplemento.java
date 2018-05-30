/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.ComplementoDAO;
import DAO.ConsultaDAO;
import Vista.Complementos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Axel Reyez
 */
public class ControladorComplemento {
    private Complementos complementos;
    

    public ControladorComplemento(Complementos complementos) {
        this.complementos = complementos;
        this.complementos.Agregar.addActionListener(new AgregarTabla());
        this.complementos.Eliminar.addActionListener(new Eliminar());
        this.complementos.TipoP.addItemListener(new Desplegar());
        this.complementos.tabla.addMouseListener(new ObtenerDeTabla());
        this.complementos.Limpiar.addActionListener(new Limpiar());
    }
    private class AgregarTabla implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(complementos.PrecioP.getText().equals(""))
            {
                complementos.PrecioP.setText("0");
            }
            if(complementos.NombreP.getText().equals("") && complementos.PrecioP.getText().equals("") && complementos.TipoP.getSelectedIndex()!=0)
            {
                JOptionPane.showMessageDialog(complementos,"Verifique los campos");
            }
            else
            {
                ConsultaDAO alta=new ComplementoDAO();
                String nombre=complementos.NombreP.getText();
                double precio=Double.parseDouble(complementos.PrecioP.getText());
                String tipo=(String)complementos.TipoP.getSelectedItem();
                alta.insertar(nombre,precio,0,0,0,tipo,"","");
                Object datos[]={nombre,precio};
                complementos.modelo.addRow(datos);
            }
        }
        
    }
    private class Eliminar implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
           ConsultaDAO com=new ComplementoDAO();
           com.Eliminar(complementos.NombreP.getText());
        }
        
    }
    private class Desplegar implements ItemListener
    {

        @Override
        public void itemStateChanged(ItemEvent e) {
            ComplementoDAO com=new ComplementoDAO();
            ResultSet rs;
            for(int i=0;i<complementos.modelo.getRowCount();i++)
        {
            complementos.modelo.removeRow(i);
            i=i-1;
        }
            rs=com.getDatos((String)complementos.TipoP.getSelectedItem());
            try {
                while(rs.next())
                {
                    String nombre;
                    double valor;
                    nombre=rs.getString("id");
                    valor=rs.getDouble("valor");
                    Object datos[]={nombre,valor};
                    complementos.modelo.addRow(datos);
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(ControladorComplemento.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    private class Limpiar implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
           complementos.NombreP.setText("");
           complementos.TipoP.setSelectedIndex(0);
           complementos.tabla.removeAll();
           complementos.PrecioP.setText("");
        }
        
    }
    private class ObtenerDeTabla implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e) {
            complementos.NombreP.setText((String) complementos.tabla.getValueAt(complementos.tabla.getSelectedRow(),0));
            complementos.PrecioP.setText(Double.toString((double)complementos.tabla.getValueAt(complementos.tabla.getSelectedRow(),1)));
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
