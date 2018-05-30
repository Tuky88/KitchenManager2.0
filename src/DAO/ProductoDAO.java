/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import modelo.Conection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Axel Reyez
 */
public class ProductoDAO extends ConsultaDAO
{
    
     private ResultSet rs;
     private String tipo;

    
     public ProductoDAO()
     {
       
          super(new Conection(),"root","root","km");
          
     }
public String getTipo() {
        return tipo;
    }

    
    //nuevos productos
public void insertar(String id, double precio,int stock,int cuantosD,int cuantosS,String tipo,String usiario,String pass)
     //public void insertar(String Id, double precio,int stock,int cuantosD,int cuantosS,String tipo)
     {
         try {
             getDatabase().MySQLConnection(this.getDatab(),this.getUser(),this.getContraseña());
             String sql="insert into productos (id,precio,stock,cuantosD,cuantosS,tipo) values('"+id+"',"+precio+","+stock+","+cuantosD+","+cuantosS+",'"+tipo+"')";
             System.out.println(sql);
            getDatabase().getStatement().execute(sql);
             

         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
            finally
         {
             JOptionPane.showMessageDialog(null, "Agregado con exito");
         }
         getDatabase().closeConnection();
     }
    //actualizar valores
      public void ActualizarPrecio(String Id,String tipo, double precio)
     {
         try {
             getDatabase().MySQLConnection(getDatab(),getUser(),getContraseña());
             getDatabase().getStatement().executeUpdate("update productos set precio="+precio+" where ID='"+Id+"' AND tipo='"+tipo+"'" );

         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         getDatabase().closeConnection();
     }
      public void ReiniciarVentas()              
     {
         try {
             getDatabase().MySQLConnection(getDatab(),getUser(),getContraseña());
             getDatabase().getStatement().executeUpdate("update productos set cuantosD=0 " );

         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         getDatabase().closeConnection();
     }
       public void ActualizarStock(String Id,String tipo, int stock)
     {
         try {
             getDatabase().MySQLConnection(getDatab(),getUser(),getContraseña());
             getDatabase().getStatement().executeUpdate("update productos set stock="+stock+" where ID='"+Id+"' AND Tipo='"+tipo+"'");

         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         getDatabase().closeConnection();
     }
        public void ActualizarcuantosD(String Id,double precio)
     {
         try {
             getDatabase().MySQLConnection(getDatab(),getUser(),getContraseña());
             rs=getDatabase().getStatement().executeQuery("select cuantosD from productos where id='"+Id+"' AND precio="+precio);
             if(rs.next())
             {
                 int actual;
             actual=rs.getInt("cuantosD");
             actual++;
             getDatabase().getStatement().executeUpdate("update productos set cuantosD="+actual+" where ID='"+Id+"'");
             }

         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         getDatabase().closeConnection();
     }
     public void ActualizarcuantosS(String Id,double precio)
     {
         try {
             getDatabase().MySQLConnection(getDatab(),getUser(),getContraseña());
             rs=getDatabase().getStatement().executeQuery("select cuantosS from productos where id='"+Id+"' AND precio="+precio);
             if(rs.next())
             {
                 int actual;
             actual=rs.getInt("cuantosS");
             actual++;
             getDatabase().getStatement().executeUpdate("update productos set cuantosS="+actual+" where ID='"+Id+"'");
             }

         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         getDatabase().closeConnection();
     }
                public void restarStock(String Id,double tipo)
     {
         try {
             getDatabase().MySQLConnection(getDatab(),getUser(),getContraseña());
             rs=getDatabase().getStatement().executeQuery("select stock from productos where id='"+Id+"' AND Precio="+tipo);
             if(rs.next())
             {
                 int actual;
             actual=rs.getInt("stock");
             actual--;
             String sql="update productos set cuantosS="+actual+" where ID='"+Id+"' AND Precio="+tipo;
             System.out.println(sql);
             getDatabase().getStatement().executeUpdate(sql);

             }
         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         getDatabase().closeConnection();
     }
     //borrar
        public void Eliminar(String Id,String Tipo)
        {
             try {
             getDatabase().MySQLConnection(getDatab(),getUser(),getContraseña());
             String sql="delete from productos where id='"+Id+"' AND Tipo='"+Tipo+"'";
             System.out.println(sql);
             getDatabase().getStatement().executeUpdate(sql);

         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
             getDatabase().closeConnection();
        }
     //select
        public double ObtenerPrecio(String Id,String Precio)
        {
            double precio=0;
            try {
             getDatabase().MySQLConnection(getDatab(),getUser(),getContraseña());
             String sql="select precio from productos where id='"+Id+"' AND precio='"+Precio+"'";
             rs=getDatabase().getStatement().executeQuery(sql);
             System.out.print(sql);
             if(rs.next())
             precio=rs.getInt("precio");

         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
            getDatabase().closeConnection();
            return precio;
        }
        public double getPrecio(String Id,String tipo)
        {
            double precio=0;
            try {
             getDatabase().MySQLConnection(getDatab(),getUser(),getContraseña());
             rs=getDatabase().getStatement().executeQuery("select precio from productos where id='"+Id+"' AND tipo='"+tipo+"'");
             if(rs.next())
             precio=rs.getDouble("precio");

         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
            getDatabase().closeConnection();
            return precio;
        }
        public int ObtenerStock(String Id,String tipo)
        {
            int stock=0;
            try {
             getDatabase().MySQLConnection(getDatab(),getUser(),getContraseña());
             rs=getDatabase().getStatement().executeQuery("select stock from productos where id='"+Id+"' AND tipo='"+tipo+"'");
             if(rs.next())
                 stock=rs.getInt("stock");

         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
            getDatabase().closeConnection();
            return stock;
        }
        public String getTipo(String Id)
        {
            String tipo="";
          try {
             getDatabase().MySQLConnection(getDatab(),getUser(),getContraseña());
             rs=getDatabase().getStatement().executeQuery("select tipo from productos where id='"+Id+"'");
             tipo=rs.getString("tipo");

         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         } getDatabase().closeConnection();
          return tipo;
        }
        public ResultSet ObtenerTodo()
        {
            ResultSet rs=null;
            try {
             getDatabase().MySQLConnection(getDatab(),getUser(),getContraseña());
             rs=getDatabase().getStatement().executeQuery("select * from productos");

         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
            //getDatabase().closeConnection();
            return rs;
        }
        public ResultSet ObtenerPorTipo(String Tipo)
        {
            ResultSet rs=null;
            try {
             getDatabase().MySQLConnection(getDatab(),getUser(),getContraseña());
             rs=getDatabase().getStatement().executeQuery("select * from  productos where tipo='"+Tipo+"'");

         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
            //getDatabase().closeConnection();
            return rs;
        }
        public boolean ValidarID(String id,String tipo)
        {
            boolean estado=false;
            try {
             getDatabase().MySQLConnection(getDatab(),getUser(),getContraseña());
             rs=getDatabase().getStatement().executeQuery("select * from  productos where tipo='"+tipo+"' AND id='"+id+"'");
             if(rs.next())
             {
                 estado=true;
             }

         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
            return estado;
        }
        

     
}
