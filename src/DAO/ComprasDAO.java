/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Conection;

/**
 *
 * @author Axel Reyez
 */
public class ComprasDAO extends ConsultaDAO
{
   

    public ComprasDAO() 
    {
     super(new Conection(),"root","root","BullTruckOO");
    }
    public void RegistrarCompra(String id,String nombre,double precio,String fecha,String Nombre)
    {
        try {
             getDatabase().MySQLConnection(getDatab(),getUser(),getContraseña());
             String sql="insert into compras (id,producto,precio,fecha,Nombre) values('"+id+"','"+nombre+"',"+precio+",'"+fecha+"','"+Nombre+"')";
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
    public void EliminarCompra(String id,String nombre,double precio,String fecha,String Nombre)
    {
        try {
             getDatabase().MySQLConnection(getDatab(),getUser(),getContraseña());
             String sql="delete from compras where '"+id+"'=id AND '"+nombre+"'=producto AND "+precio+"=precio AND '"+fecha+"'=fecha AND '"+Nombre+"'=Nombre";
             System.out.println(sql);
            getDatabase().getStatement().execute(sql);
             

         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
           
         getDatabase().closeConnection();
    }
    public ResultSet BuscarId(String id)
    {
        ResultSet rs=null;
        try {
             getDatabase().MySQLConnection(getDatab(),getUser(),getContraseña());
             String sql="select * from compras where id='"+id+"'";
             System.out.println(sql);
            rs=getDatabase().getStatement().executeQuery(sql);
         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
        return rs;
    }
    public ResultSet BuscarFecha(String fecha)
    {
        ResultSet rs=null;
        try {
             getDatabase().MySQLConnection(getDatab(),getUser(),getContraseña());
             String sql="select * from compras where fecha='"+fecha+"'";
             System.out.println(sql);
            rs=getDatabase().getStatement().executeQuery(sql);
         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
        return rs;
    }
    public String ObtenerNombre(String id)
    {
        String nombre="";
        ResultSet rs=null;
        try {
             getDatabase().MySQLConnection(getDatab(),getUser(),getContraseña());
             String sql="select * from compras where id='"+id+"'";
             System.out.println(sql);
            rs=getDatabase().getStatement().executeQuery(sql);
            if(rs.next())
            {
                nombre=rs.getString("Nombre");
            }
         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        return nombre;
    }
     
    
}
