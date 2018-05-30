/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.ConsultaDAO;
import DAO.EmpleadoDAO;
import Vista.UsuarioI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


/**
 *
 * @author Axel Reyez
 */
public class ControladorUsuario {
    UsuarioI usuario;
    public ControladorUsuario(UsuarioI usuario)
    {
        this.usuario=usuario;
        this.usuario.EliminarR.addActionListener(new MostrarEliminar());
        this.usuario.RegistrarR.addActionListener(new MostrarNuevo());
        this.usuario.Registrar.addActionListener(new RegistrarUsuario());
        this.usuario.Eliminar.addActionListener(new EliminarUsuario());
    }
    private class MostrarEliminar implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            usuario.eliminado.setVisible(true);
            usuario.nuevo.setVisible(false);
            usuario.ContraseñaE.setText("");
            usuario.UsuarioE.setText("");
        }
        
    }
    private class MostrarNuevo implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            usuario.eliminado.setVisible(false);
            usuario.nuevo.setVisible(true);
            usuario.ContraseñaN.setText("");
            usuario.UsuarioN.setText("");
            usuario.ValidarCN.setText("");
        }
        
    }
    private class RegistrarUsuario implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            EmpleadoDAO empleado=new EmpleadoDAO();
            if(empleado.VerificarUsuario(usuario.UsuarioN.getText()))
            {
                JOptionPane.showMessageDialog(usuario, "Usuario ya existente");
            }
            else
            {
                if(usuario.ContraseñaN.getText().equals(usuario.ValidarCN.getText()))
                {
                    empleado.insertar("",0L,0,0,0,"gerente",usuario.UsuarioN.getText(), usuario.ContraseñaN.getText());
                    JOptionPane.showMessageDialog(usuario,"Registro Exitoso");
                }
                else
                {
                    JOptionPane.showMessageDialog(usuario,"Las contraseñas no coinciden");
                }
            }
        }
        
    }
    private class EliminarUsuario implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            EmpleadoDAO empleado=new EmpleadoDAO();
            if(empleado.validarPass(usuario.UsuarioE.getText(),usuario.ContraseñaE.getText())==1)
            {
                empleado.Eliminar(usuario.UsuarioE.getText());
                JOptionPane.showMessageDialog(usuario,"Usuario eliminado");
            }
            else
            {
                JOptionPane.showMessageDialog(usuario, "Verifique los datos");
            }
        }
        
    }
    
}
