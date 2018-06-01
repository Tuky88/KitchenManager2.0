/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Axel Reyez
 */
public class Ticket{
    //atributos
    private double valor;
    private Comanda crea;
    private double pago;
    private double cambio;
    private String hora;
    private String dia;
    
    //constructores
    public Ticket()
    {
        valor=0;    
    }
    
    
    //metodos

    public Ticket(double valor, Comanda crea, double pago, String hora, String dia) {
        this.valor = valor;
        this.crea = crea;
        this.pago = pago;
        this.hora = hora;
        this.dia = dia;
    }


   
    
    
    public String getDia()
    {
        return dia;
    }
    public String getHora()
    {
        return hora;
    }
    public double getPago()
    {
        return pago;
    }
    public double getValor()
    {
        return valor;
    }
   public double getCambio()
   {
       cambio=pago-valor;
       return cambio;
   }
    
    public void generarTicket() throws IOException
    {
       
    
     String ruta ="C:\\KitchenManager2.0\\src\\Generados\\Ticket\\Ticket.txt";
        File archivo = new File(ruta);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(archivo));
        } catch (IOException ex) {
            Logger.getLogger(Ticket.class.getName()).log(Level.SEVERE, null, ex);
        }
            bw.write("Kitchen Manager S.H.\n");
            bw.write("\t\t"+dia);
            bw.write("\n\t\t"+hora);
            bw.write("\n");
            while(!crea.isEmpty())
            {
                Producto p;
               p=this.crea.sacar();
               bw.write("\n"+p.getNombre()+ "\t$"+p.getPrecio());
            }
            
            bw.write("\n");
            bw.write("\n" +"Total:\t"+ valor);
            bw.write("\n" +"Pago:\t" +pago);
            cambio=pago-valor;
            bw.write("\n" +"Cambio:\t" +cambio);
            bw.write("\n\nGRACIAS POR SU PREFERENCIA" );
            
        
        bw.close();
        
      
   imprimirTicket(ruta);


    }
    public void imprimirTicket(String ruta) throws IOException
    {
         Desktop d = Desktop.getDesktop();
        /*Verifica que el ambiente del SO soporte los procedimientos*/
        if(d.isDesktopSupported()){
            /*si es así manos a la obra*/
           d.print(new File(ruta));
          
        }
        else
            System.out.println(":(");  
    }
    public void LiberarTicket()
    {
        if(cambio>=0)
        {
            try {
                generarTicket();
            } catch (IOException ex) {
                Logger.getLogger(Ticket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    //destructor
    
}
