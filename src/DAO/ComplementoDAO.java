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
public class ComplementoDAO extends ConsultaDAO
{
    

    public ComplementoDAO() 
    {
        super(new Conection(),"root","root","BullTruckOO");
    }
    //Agregar
    @Override
    public void insertar(String id, double precio,int stock,int cuantosD,int cuantosS,String tipo,String usiario,String pass)
    {
        try {
             getDatabase().MySQLConnection(getDatab(),getUser(),getContraseña());
             String sql="insert into complementos (id,valor,tipo) values('"+id+"',"+precio+",'"+tipo+"')";
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
    //Modificar
    
    //Eliminar
    @Override
    public void Eliminar(String id)
    {
        try {
             getDatabase().MySQLConnection(getDatab(),getUser(),getContraseña());
             String sql="delete from complementos where id='"+id+"'";
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
    //Consultar
    public ResultSet getDatos(String tipo)
    {
        ResultSet rs=null;
        try {
             getDatabase().MySQLConnection(getDatab(),getUser(),getContraseña());
             String sql="select * from complementos where tipo='"+tipo+"'";
             System.out.println(sql);
            rs=getDatabase().getStatement().executeQuery(sql);
         } catch (Exception ex) {
             Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
        return rs;
    }
    
}
