/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.text.DateFormat;
import java.util.Date;
import javax.swing.table.TableColumnModel;
import modelo.Tablachida;
import modelo.reloj;

/**
 *
 * @author Alumno
 */
public class Estado extends javax.swing.JPanel {

   public Tablachida model;
    public Estado() {
        initComponents();
        
  
        String cabecera []={"Producto","Cuantos"};
                reloj hilo=new reloj(hora);
        hilo.start();
String datos[][]={};

boolean t=true;
boolean f=false;
Ventas.setVisible(f);
model = new Tablachida(datos,cabecera);
tabla.setModel(model);
TableColumnModel columnModel = tabla.getColumnModel();
columnModel.getColumn(0).setPreferredWidth(480);
DateFormat df= DateFormat.getDateInstance();
Date Fecha = new Date();
tabla.setRowHeight(25);
DateFormat df2 = DateFormat.getDateInstance(DateFormat.LONG);
fecha.setText(df2.format(Fecha));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fecha = new javax.swing.JLabel();
        hora = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        Corte = new javax.swing.JButton();
        ticket = new javax.swing.JTextField();
        ventas = new javax.swing.JTextField();
        Ventas = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        GerenteT = new javax.swing.JTextField();

        fecha.setFont(new java.awt.Font("Tekton Pro", 0, 18)); // NOI18N
        fecha.setText("FECHA");

        hora.setFont(new java.awt.Font("Tekton Pro", 0, 24)); // NOI18N
        hora.setText("Hora");

        jLabel3.setFont(new java.awt.Font("Tekton Pro", 0, 18)); // NOI18N
        jLabel3.setText("#Ticket");

        jLabel4.setFont(new java.awt.Font("Tekton Pro", 0, 18)); // NOI18N
        jLabel4.setText("Ventas");

        tabla.setFont(new java.awt.Font("Tekton Pro", 0, 18)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        Corte.setFont(new java.awt.Font("Tekton Pro", 0, 18)); // NOI18N
        Corte.setText("Corte de caja");

        ticket.setFont(new java.awt.Font("Tekton Pro", 0, 18)); // NOI18N
        ticket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ticketActionPerformed(evt);
            }
        });

        ventas.setFont(new java.awt.Font("Tekton Pro", 0, 18)); // NOI18N

        Ventas.setText("Ventas");

        jLabel5.setFont(new java.awt.Font("Tekton Pro", 0, 18)); // NOI18N
        jLabel5.setText("Gerente");

        GerenteT.setFont(new java.awt.Font("Tekton Pro", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 928, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Corte, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fecha)
                                    .addComponent(hora))
                                .addGap(605, 605, 605)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)))
                            .addComponent(Ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ticket)
                            .addComponent(ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(GerenteT, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(6, 6, 6)))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fecha)
                    .addComponent(jLabel3)
                    .addComponent(ticket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hora)
                    .addComponent(jLabel4)
                    .addComponent(ventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(Ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(GerenteT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(Corte)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ticketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ticketActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ticketActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton Corte;
    public javax.swing.JTextField GerenteT;
    public javax.swing.JButton Ventas;
    public javax.swing.JLabel fecha;
    public javax.swing.JLabel hora;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tabla;
    public javax.swing.JTextField ticket;
    public javax.swing.JTextField ventas;
    // End of variables declaration//GEN-END:variables
}