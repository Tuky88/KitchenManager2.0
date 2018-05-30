/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Axel Reyez
 */
public class Extra extends Producto {
    //atributos
    private String Nombre;
    private String Tipo;
    private int precio;
    private String Comanda;
    
   //constructores
    public Extra()
    {
        Nombre="";
        precio=0;
        Comanda="";
    }
    public Extra(String nombre,String Tipo)
    {
        super(nombre,0.0);
        this.Nombre=nombre;
        this.Tipo=Tipo;
        this.crearMenu();
    }
    public Extra(Extra e)
    {
        this.Nombre=e.Nombre;
    }
    //metodo
    public String getNombre()
    {
        
        return Nombre;
    }
    public void setPrecio(int i)
    {
        precio=i;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }
    
    public void setNombre(String nombre)
    {
        this.Nombre=nombre;
    }
    public void armarExtra()
    {
        Nombre=this.getNombre();
    }
    public void generarComanda()
    {
        Comanda="Extra-"+Nombre;
    }
    public String getComanda()
    {
        generarComanda();
        return Comanda;
    }
    //destructor
    public void destruir()
    {
        Nombre=null;
        System.gc();
    }
}
