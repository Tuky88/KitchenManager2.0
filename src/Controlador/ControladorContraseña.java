/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.EmpleadoDAO;
import Vista.Contraseña;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexander
 */
public class ControladorContraseña {
    public Contraseña contraseña;

    public ControladorContraseña(Contraseña contraseña) {
        this.contraseña = contraseña;
        this.contraseña.ActualizarB.addActionListener(new cambiarContraseña());
    }
    private class cambiarContraseña implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            EmpleadoDAO empleado =new EmpleadoDAO();
            if(empleado.validarPass(contraseña.usuariot.getText(),contraseña.ActualP.getText())!=1)
            {
                JOptionPane.showMessageDialog(contraseña, "Contraseña incorrecta");
            }
            else
            {
                if(contraseña.NuevaP.getText().equals(contraseña.ConfirmarP.getText()))
                {
                    empleado.ActualizarContraseña(contraseña.usuariot.getText(),contraseña.NuevaP.getText());
                }
            }
        }
        
    }
    
    
    
}
