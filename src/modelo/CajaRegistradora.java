/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import DAO.CajaDAO;
import DAO.PagoDAO;
import DAO.ProductoDAO;
import DAO.TicketDAO;
import DAO.VentasDAO;
import java.sql.Connection;

import java.sql.ResultSet;

import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;

/**
 *
 * @author Axel Reyez
 */
public class CajaRegistradora  {
    
    //atributos
    private String Gerente;
    private Ticket registra;
    private double CajaInicial;
    private double CajaFinal;
    private double Acumulado;
    private int ContadorTicket;
    
    //Constructores

    public CajaRegistradora(String Gerente) {
        this.Gerente = Gerente;
        this.setCajaInicial();
    }

    public CajaRegistradora(String Gerente, double CajaInicial) {
        this.Gerente = Gerente;
        this.CajaInicial = CajaInicial;
    }

    public CajaRegistradora(CajaRegistradora c)
    {
        this.CajaInicial=c.CajaInicial;
    }

    public CajaRegistradora(Ticket registra) {
        this.registra = registra;
    }
    
    // metodos

    public String getGerente() {
        return Gerente;
    }

    public void setGerente(String Gerente) {
        this.Gerente = Gerente;
    }

    public double getCajaInicial() {
        return CajaInicial;
    }

    public void setCajaInicial() {
        CajaDAO caja=new CajaDAO();
        CajaInicial=caja.ObtenerCaja();
    }

    public double getCajaFinal() {
        return CajaFinal;
    }

    public void setCajaFinal() {
        this.CajaFinal=CajaInicial+Acumulado;
    }

    public double getAcumulado() {
        return Acumulado;
    }

    public void setAcumulado(double Acumulado) {
        this.Acumulado = Acumulado;
    }
    
    public void ActualizarTicket()
    {
        TicketDAO tic=new TicketDAO();
        ContadorTicket=tic.getNumero();
        tic.AumentarConteo();
    }
    public void ActualizarVentas(double valor)
    {
        VentasDAO ven=new VentasDAO();
        ven.RegistrarVenta(valor);
        Acumulado=ven.getVentas();
    }
    public void CorteCaja()
    {
        TicketDAO tic=new TicketDAO();
            VentasDAO ven=new VentasDAO();
            ProductoDAO prod=new ProductoDAO();
            PagoDAO pag=new PagoDAO();
            tic.ReiniciarConteo();
            ven.ReiniciarCuenta();
            prod.ReiniciarVentas();
            pag.ReiniciarConteoE();
            pag.ReiniciarConteoT();
    }
    public void registrarVenta()
    {
        VentasDAO ven=new VentasDAO();
        ven.RegistrarVenta(registra.getValor());
    }
    public int getTicket()
    {
        TicketDAO tic=new TicketDAO();
        return tic.getNumero();
    }
    
    //Destructor
    public void Destruir()
    {
   Gerente=null;
    }
    
    
}
