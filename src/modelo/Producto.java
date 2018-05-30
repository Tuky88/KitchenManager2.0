/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.LinkedList;

/**
 *
 * @author Axel Reyez
 */
public class Producto {
    //atributos 
  private Menu contiene;
    private int i;
    private String Nombre;
    private String Comanda;
    private Double precio;
    
    //Constructores
    public Producto()
    {
        
    }

    public Producto(String Nombre, Double precio) {
        this.Nombre = Nombre;
        this.precio = precio;
        
    }

    public Double getPrecio() {
        return precio;
    }
    public void crearMenu()
    {
        this.contiene=new Menu();
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public void setComanda()
    {
        Comanda=Nombre+precio;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    public double buscarPrecio(String Nombre,String Tipo)
    {
        double valor=0;
        valor=contiene.buscarProducto(Nombre,Tipo);
        return valor;
    }
    public boolean validarStock(String Nombre,String Tipo)
    {
        return contiene.validarStock(Nombre, Tipo);
    }
    public void restarStock(String Nombre,double Tipo)
    {
        contiene.restarStock(Nombre, Tipo);
    }
    public void Aumentar(String Nombre,double precio)
    {
        contiene.Aumentar(Nombre, precio);
    }
    
    //metodo
    public String getNombre()
    {
        return Nombre;
    }

    //destructor

}
