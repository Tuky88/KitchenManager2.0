/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.CajaDAO;
import Vista.Caja;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Alexander
 */
public class ControladorCaja 
{
private Caja c;
public ControladorCaja(Caja c)
{
    this.c=c;
    this.c.ActualizarB.addActionListener(new Actualizar());
}
private class Actualizar implements ActionListener
{

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            CajaDAO cd=new CajaDAO();
            cd.ModificarCaja(Double.parseDouble(c.CajaT.getText()));
            
        }
    
}

}
