/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.*;



/**
 *
 * @author Alexander
 */
public class Admin extends javax.swing.JPanel {

    public Caja caja;
    public Compras compras;
    public Contraseña contraseña;
    public Estado estado;
    public Productos productos;
    public Reportes reportes;
    public Complementos complementos;
    public UsuarioI usuario;
    public Admin() {
        initComponents();
        caja=new Caja();
        compras=new Compras();
         contraseña=new Contraseña();
        estado=new Estado();
         productos=new Productos();
        reportes=new Reportes();
        complementos=new Complementos();
        usuario=new UsuarioI();
        barra.addTab("Estado",estado);
        barra.addTab("Compras", compras);
        barra.addTab("Caja", caja);
        barra.addTab("Productos", productos);
        barra.addTab("Complementos", complementos);
        barra.addTab("Reportes", reportes);
        barra.addTab("Contraseña", contraseña);
        barra.addTab("Usuarios", usuario);
        this.add(barra);
        //controlador
        
        ControladorUsuario coc= new ControladorUsuario(usuario);
        ControladorCaja cc= new ControladorCaja(caja);
        ControladorComplemento cco= new ControladorComplemento(complementos);
        ControladorEstado coe=new ControladorEstado(estado);
        ControladorCompras coco=new ControladorCompras(compras);
        ControladorProducto copr=new ControladorProducto(productos);
        ControladorReportes core=new ControladorReportes(reportes);
        ControladorContraseña cocon=new ControladorContraseña(contraseña);
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        barra = new javax.swing.JTabbedPane();

        barra.setTabPlacement(javax.swing.JTabbedPane.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barra, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barra, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTabbedPane barra;
    // End of variables declaration//GEN-END:variables
}
