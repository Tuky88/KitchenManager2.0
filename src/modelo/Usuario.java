/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Axel Reyez
 */
public class Usuario {
    //atributos
    String Nombre;
    private int numTicket;
    private Ticket paga;
    private Comanda ordena;
    
    //constructores
    public  Usuario()
    {
        Nombre="";
        numTicket=0;
    }
    public Usuario(String Nombre , int numTicket)
    {
        this.Nombre=Nombre;
        this.numTicket=numTicket;
    }
    public Usuario(Usuario u)
    {
        this.Nombre=u.Nombre;
        this.numTicket=u.numTicket;
    }
    
 
    //metodos
    public String getNombre()
    {
        return Nombre;
    }
    public  int getNumero()
    {
        return numTicket;
    }
    public void setNombre(String Nombre)
    {
        this.Nombre=Nombre;
    }
    public void generarComanda(int numero)
    {
         ordena=new Comanda(Nombre,numero);
    }
    public void Ordenar(Producto P)
    {
        ordena.agregar(P);
        
    }
    
    public double getPago()
    {
        return paga.getValor();
    }
    public void generarOrden()
    {
        ordena.imprimirComanda();
    }
    public void generarTicket() 
    {
        paga.LiberarTicket();
    }
    public void crearTicket(double valor, Comanda crea, double pago, String hora, String dia)
    {
        paga=new Ticket(valor,crea,pago,hora,dia);
    }
    public Comanda getComanda()
    {
        return ordena;
    }
    public Ticket getTicket()
    {
        return paga;
    }

    //destrcutor
    public void destruir()
    {
        
    }
}
