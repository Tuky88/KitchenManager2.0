/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import modelo.Producto;
import java.awt.Desktop;
import java.awt.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Comanda {
    private Menu efectua;
    private LinkedList productos;    
    private int i ;
    private String Consumidor;
    private int numTicket;
    
//construc

    public Comanda(String Consumidor, int numTicket) {
        this.productos = new LinkedList<Producto>();
        this.Consumidor = Consumidor;
        this.numTicket = numTicket;
    }

    public Comanda(Comanda c)
    {
        this.productos = c.productos;
        this.productos=c.productos;
        this.efectua=c.efectua;
        this.Consumidor=c.Consumidor;
        this.numTicket=c.numTicket;
    }
    
    public void imprimirComanda()
    {
        try {
            String ruta="C:\\Users\\Axel Reyez\\Desktop\\Comanda.txt";
            File archivo = new File(ruta);
            BufferedWriter bw=null;
            bw = new BufferedWriter(new FileWriter(archivo));
             bw.write("\n"+Consumidor+"\n");
             bw.write("numero de pedido:"+this.numTicket);
            while(!productos.isEmpty())
            {
                Producto p;
                p=(Producto) productos.pop();
                bw.write(p.getNombre()+"\n");
            }
            bw.close();
            Desktop d = Desktop.getDesktop();
            /*Verifica que el ambiente del SO soporte los procedimientos*/
            if(Desktop.isDesktopSupported()){
                /*si es así manos a la obra*/
                d.print(new File(ruta));
                
            }
           
        } catch (IOException ex) {
            Logger.getLogger(Comanda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean isEmpty()
    {
        return this.productos.isEmpty();
    }
    public void addProducto( Producto p ){
                efectua.addProducto( p );
    }
    public void agregar(Producto p)
    {
        this.productos.add(p);
    }
    public int sizeOf()
    {
        return this.productos.size();
    }
    public Producto sacar()
    {
        return (Producto) this.productos.pop();
    }
    
   
   //Destructor
  
   public void destruir()
   {
       productos=null;
       
       System.gc();
   }
}


