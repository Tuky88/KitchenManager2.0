
package modelo;
/**
 *
 * @author Axel Reyez
 */
public class Carne extends Producto {
    //atributos
    private String Nombre;
    private String Termino;
    private String Complemento;
    private String Tortilla;
    private int precio;
    private String TipoCarne;
    private String Comanda;

    //constructores
    public Carne()
    {
        Nombre="";
        Termino="";
        Complemento="";
        Tortilla="";
        precio=0;
        Comanda="";
    }
    public Carne(String Nombre,String Termino,String Complemento,String Tortilla,String TipoCarne)
    {
        super(Nombre,0.0);
        this.Nombre=Nombre;
       this.Termino=Termino;
        this.Complemento=Complemento;
        this.Tortilla=Tortilla;
        this.TipoCarne=TipoCarne;
        this.crearMenu();
        
        
      
    }
    public Carne(String Nombre,int precio,String TipoCarne)
    {
       this.Nombre=Nombre;
       this.precio=precio;
        this.TipoCarne=TipoCarne;

    }
    
    public Carne(Carne c)
    {
        this.Nombre=c.Nombre;
       this.Termino=c.Termino;
        this.Complemento=c.Complemento;
        this.Tortilla=c.Tortilla;
        precio=c.precio;
        
    }
  
    //metodos
    public void setNombre(String Nombre)
    {
        this.Nombre=Nombre;
    }
    public void setTermino(String Termino)
    {
        this.Termino=Termino;
    }
    public void setComplemento(String Complemento)
    {
        this.Complemento=Complemento;
    }
    public void setTortilla(String Tortilla)
    {
        this.Tortilla=Tortilla;
    }
    public String getNombre()
    {
        
        return Nombre;
    }
    public String getTermino()
    {
        return Termino;
    }
    public String getTortilla()
    {
        return Tortilla;
    }
    public String getComplemento()
    {
        return Complemento;
    }
    public String getComanda()
    {
        generarComanda();
        return Comanda;
    }
    public void armarCarne()
    {
        this.getNombre();
        this.getTermino();
        this.getComplemento();
        this.getTortilla();
        this.getComanda();
    }
    public void setTipoCarne(String i)
    {
        TipoCarne=i;
    }
    public String getTipoCarne()
    {
        return TipoCarne;
    }
    public void generarComanda()
    {
        Comanda=TipoCarne+"-"+Nombre+"-"+Termino+"-"+Complemento+"-"+Tortilla;
    }
    
    //destructor
    public void destruir()
    {
        Nombre=null;
        Termino="";
        Complemento=null;
        Tortilla=null;
        Comanda=null;
        System.gc();
    }
}
