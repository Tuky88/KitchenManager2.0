/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.ComprasDAO;
import Vista.Reportes;
import com.itextpdf.text.DocumentException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Reporte;


/**
 *
 * @author Alexander
 */
public class ControladorReportes {
    public Reportes reporte;

    public ControladorReportes(Reportes reporte) {
        this.reporte = reporte;
        this.reporte.Limpiar.addActionListener(new Limpiar());
        this.reporte.jButton1.addActionListener(new setFecha());
        this.reporte.Registrar.addActionListener(new generarReporte());
        this.reporte.FechaR.addActionListener(new MostrarFecha());
        this.reporte.IDR.addActionListener(new MostrarID());
        this.reporte.idtext.addKeyListener(new AutocompletarID());
    }
    private class Limpiar implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
         reporte.tipo.clearSelection();
         reporte.fechap.setVisible(false);
         reporte.idp.setVisible(false);
         reporte.idtext.setText("");
         reporte.nombretext.setText("");
         reporte.resultadospinner.setText("");
         reporte.spinneraño.setValue((Object)15);
         reporte.spinnerdia.setValue((Object)1);
         reporte.spinnermes.setValue((Object)1);
        }
        
    }
    private class setFecha implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            String value1=Integer.toString((int) reporte.spinnerdia.getValue()),value2=Integer.toString((int) reporte.spinnerdia.getValue());
            if((int) reporte.spinnermes.getValue()<10)
            {
                value1="0"+Integer.toString((int) reporte.spinnermes.getValue());
            }
        String fecha=value1+"/"+value2+"/"
                +Integer.toString((int) reporte.spinneraño.getValue());
            reporte.resultadospinner.setText(fecha);}


       
        
    }
    private class generarReporte implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            Reporte report=new Reporte();
            if(reporte.IDR.isSelected())
            {
                try {
                    report.ReporteCompras("ID",reporte.idtext.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorReportes.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DocumentException ex) {
                    Logger.getLogger(ControladorReportes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                try { 
                    report.ReporteCompras("Fecha",reporte.resultadospinner.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorReportes.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DocumentException ex) {
                    Logger.getLogger(ControladorReportes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
                       
            
        }
        
    }
    private class MostrarFecha implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            reporte.fechap.setVisible(true);
            reporte.idp.setVisible(false);
        }
    }
    private class MostrarID implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            reporte.fechap.setVisible(false);
            reporte.idp.setVisible(true);
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
    String id=reporte.idtext.getText();
    ComprasDAO com=new ComprasDAO();
    String nombre="";
    nombre=com.ObtenerNombre(id);
    reporte.nombretext.setText(nombre);
}
        }

        @Override
        public void keyReleased(KeyEvent e) {
           
        }

        
        
    }
    
}
