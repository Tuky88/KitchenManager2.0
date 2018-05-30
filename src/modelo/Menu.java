/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import DAO.ProductoDAO;
import java.util.LinkedList;

/**
 *
 * @author Axel Reyez
 */
public class Menu {
    //Atributos
    private LinkedList<Producto> productos;
    String Fecha;
    //constructores
    public Menu()
    {
     Fecha="hoy";   
    }
    public Menu(LinkedList productos)
    {
        this.productos=productos;
        }
    public Menu(Menu m)
    {
        productos=m.productos;
    }
    //MEtodos
    public void addProducto( Producto p ) {
        productos.add(p);
    }
    
    public void nuevoCarne()
    {
        String nombre="";
        int precio=0;
        String Tipo="";
        Carne producto=new Carne(nombre,precio,Tipo);
        productos.add(producto);
    }
    public void nuevoBebida()
    {
        String nombre="";
        int precio=0;
        Bebida producto=new Bebida(nombre,precio);
        productos.add(producto);
    }
    public void nuevoExtra()
    {
        String nombre="";
        int precio=0;
        Extra producto=new Extra(nombre,"Extra");
       productos.add(producto);
    }
    public double buscarProducto(String prod, String tipo){
       ProductoDAO pro=new ProductoDAO();
               Double precio=0.0;
                       precio=pro.getPrecio(prod,tipo);
               return precio;
    }
    public boolean validarStock(String prod,String tipo)
    {
        boolean estado=true;
        ProductoDAO pro=new ProductoDAO();
        int cuantos=pro.ObtenerStock(prod, tipo);
        if(cuantos<=1)
        {
            estado=false;
        }
        else
            estado=true;
        return estado;
    }
    public void restarStock(String prod,double tipo)
    {
        ProductoDAO producto=new ProductoDAO();
        producto.restarStock(prod,tipo);
    }
    public void Aumentar(String prod,double tipo)
    {
        ProductoDAO producto=new ProductoDAO();
        producto.ActualizarcuantosD(prod,tipo);
        producto.ActualizarcuantosS(prod,tipo);
    }
    //Destructor
    public void Destruir()
    {
        productos=null;
        System.gc();
    }
}
