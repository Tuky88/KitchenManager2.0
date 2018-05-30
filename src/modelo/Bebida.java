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
public class Bebida extends Producto{
    //atributos
    private String Nombre;
    private String Tamaño;
    private String Tipo;
    private int precio;
    private String Comanda;

    //constructores
    public Bebida()
    {
        Nombre="";
        Tamaño="";
        precio=0;
        Comanda="";
        Tipo="";
    }
    public Bebida(String Nombre,String Tamaño,String tipo)
    {
       super(Nombre,0.0);
       this.Nombre=Nombre;
       this.Tamaño=Tamaño;
       this.Tipo=tipo;
       this.crearMenu();
      
    }
    public Bebida(String Nombre,int precio)
    {
       this.Nombre=Nombre;
       this.precio=precio;
      
    }
    
    public Bebida(Bebida b)
    {
        this.Nombre=b.Nombre;
       this.Tamaño=b.Tamaño;
       this.precio=b.precio;
    }
  
    //metodos
    public void setNombre(String Nombre)
    {
        this.Nombre=Nombre;
    }
    public void setTamaño(String Tamaño)
    {
        this.Tamaño=Tamaño;
    }
    public String getNombre()
    {
        
        return Nombre;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }
    public String getTamaño()
    {
        
        return Tamaño;
    }
    
    public String getComanda()
    {
        Comanda="Bebida"+"/"+Nombre+"/"+Tamaño;
        return Comanda;
    }
    public void armarBebida()
    {
        this.getNombre();
        this.getTamaño();
        this.getComanda();
    }
    //destructor
    public void destruir()
    {
        Nombre=null;
        Tamaño=null;
        Comanda=null;
        System.gc();
    }
    
           
}
