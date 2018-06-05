/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.PagoDAO;
import DAO.ProductoDAO;
import DAO.TicketDAO;
import DAO.VentasDAO;
import Vista.Estado;
import com.itextpdf.text.DocumentException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Reporte;

/**
 *
 * @author Alexander
 */
public class ControladorEstado {
    public Estado estado;

    public ControladorEstado(Estado e) 
    {
        this.estado=e;
        this.estado.Ventas.addActionListener(new ObtenerTabla());
        this.estado.Corte.addActionListener(new CorteCaja());
        
    }
    private class  ObtenerTabla implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
             ProductoDAO p=new ProductoDAO();
            ResultSet r;
            r=p.ObtenerTodo();
            for(int i=0;i<estado.model.getRowCount();i++)
        {
            estado.model.removeRow(i);
            i=i-1;
        }
            try {
                while(r.next())
                {
                 String id , tipo;
                 int cuantos;
                 id=r.getString("id");
                 cuantos=r.getInt("cuantosD");
                Object datos[]={id,cuantos};
                estado.model.addRow(datos);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControladorEstado.class.getName()).log(Level.SEVERE, null, ex);
            }
            TicketDAO tic=new TicketDAO();
            VentasDAO ven=new VentasDAO();
            PagoDAO pag=new PagoDAO();
            estado.ticket.setText(Integer.toString(tic.getNumero()));
            estado.ventas.setText(Double.toString(ven.getVentas()));
           estado.EfectivoTxt.setText(""+pag.getNumeroE());
           estado.TerminalTxt.setText(""+pag.getNumeroT());
            
            
            
        }
    }
    
    private class CorteCaja implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Reporte reporte=new Reporte(estado.fecha.getText(),estado.hora.getText(),estado.GerenteT.getText());
            try {
                reporte.ReportePDF();
            } catch (DocumentException ex) {
                Logger.getLogger(ControladorEstado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorEstado.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JOptionPane.showMessageDialog(estado,"Hasta mañana");
            System.exit(0);
        }
        
    }
       

    
}
