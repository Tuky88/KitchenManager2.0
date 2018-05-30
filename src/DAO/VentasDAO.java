/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Conection;

/**
 *
 * @author Axel Reyez
 */
public class VentasDAO {
    private Conection database;
     private String user;
     private String contraseña;
     private String datab;
     private ResultSet rs;
     public VentasDAO()
     {
         database= new Conection();
          user="root";
          datab="BullTruckOO";
          contraseña="root";
     }
     public void RegistrarVenta(double valor)
     {
         try {
             database.MySQLConnection(datab,user,contraseña);
             rs=database.getStatement().executeQuery("select valor from ventas where id=1");
             double actual;
             if(rs.next())
             {
               actual=rs.getDouble("valor");
             actual+=valor;
             String sql="update ventas set valor="+actual+" where id=1";
             System.out.println(sql);
             database.getStatement().executeUpdate(sql);  
             }
         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         database.closeConnection();
     }
     public void ReiniciarCuenta()
     {
         try {
             database.MySQLConnection(datab,user,contraseña);
             double actual=0;
             database.getStatement().executeUpdate("update ventas set valor="+actual+"where ID=1");  

         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         database.closeConnection();
     }
     public double getVentas()
     {
         int actual=0;
         try {
             database.MySQLConnection(datab,user,contraseña);
             rs=database.getStatement().executeQuery("select valor from ventas where id=1");
             
             if(rs.next())
             {
               actual=rs.getInt("valor");
             }
         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         database.closeConnection();
         return actual;
     }
     
}
