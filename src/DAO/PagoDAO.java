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
 * @author Sistemas
 */
public class PagoDAO {
        private Conection database;
     private String user;
     private String contraseña;
     private String datab;
     private ResultSet rs;
     
     public PagoDAO()
     {
         database= new Conection();
          user="root";
          datab="km";
          contraseña="root";
     }
         public void ReiniciarConteoE()
     {
         try {
             database.MySQLConnection(datab,user,contraseña);
             int actual=0;
             database.getStatement().executeUpdate("update pago set valor="+actual+" where ID=1");

         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         database.closeConnection();
     }
    public double getNumeroE()
    {
        double actual=0;
        try {
             database.MySQLConnection(datab,user,contraseña);
             rs=database.getStatement().executeQuery("select valor from pago where id=1");
            if(rs.next())
             actual=rs.getDouble("valor");
         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
      return actual;  
    }
     public void ReiniciarConteoT()
     {
         try {
             database.MySQLConnection(datab,user,contraseña);
             int actual=0;
             database.getStatement().executeUpdate("update pago set valor="+actual+" where ID=2");

         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         database.closeConnection();
     }
    public double getNumeroT()
       {
        double actual=0;
        try {
             database.MySQLConnection(datab,user,contraseña);
             rs=database.getStatement().executeQuery("select valor from pago where id=2");
            if(rs.next())
             actual=rs.getDouble("valor");
         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
      return actual;  
    }
         public void AumentarE(double x)
     {
         try {
             database.MySQLConnection(datab,user,contraseña);
             rs=database.getStatement().executeQuery("select valor from pago where id=1");
             double actual;
             if(rs.next())
             {
               actual=rs.getDouble("valor");
             actual+=x;
             database.getStatement().executeUpdate("update pago set valor="+actual+" where ID=1");  
             }
         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         database.closeConnection();
     }
                  public void AumentarT(double x)
     {
         try {
             database.MySQLConnection(datab,user,contraseña);
             rs=database.getStatement().executeQuery("select valor from pago where id=2");
             double actual;
             if(rs.next())
             {
               actual=rs.getDouble("valor");
             actual+=x;
             database.getStatement().executeUpdate("update pago set valor="+actual+" where ID=2");  
             }
         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         database.closeConnection();
     }
    
}
