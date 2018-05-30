/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.swing.JOptionPane;

/**
 *
 * @author Axel Reyez
 */
public class Restaurante {
    //atributos
    private Usuario[] atiende;
    private String Local;
    private String telefono;
    private String direccion;
    private String gerente;
    

    //constructores
public Restaurante()
{
    Local="";
    direccion="";
}
public Restaurante(String local,String direccion)
{
    this.Local=local;
    this.direccion=direccion;
}
public Restaurante(Restaurante r)
{
    this.Local=r.Local;
    this.direccion=r.direccion;
}
    //metodos
public void AgregarProducto()
{
int opc = JOptionPane.showOptionDialog(null, "¿Que desea agregar?", "ADVERTENCIA!", 2, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Carne", "Bebida","Extra"}, null); 
añadir(opc);
}
public void getValores()
{
    Local=getLocal();
    telefono=getTelefono();
    direccion=getDireccion();
    gerente=getGerente();
}
public String getLocal()
{
    return Local;
}
public String getTelefono()
{
    return telefono;
}
public String getDireccion()
{
    return direccion;
}
public String getGerente()
{
    return gerente;
}
public void registrarCliente()
{
    
}
public String getNombre()
{
    return atiende[1].Nombre;
}
public void añadir(int tipo)
{
    switch(tipo+1)
    {
        case 1:
            break;
        case 2:
            break;
        case 3:
            break;
    }
    
}
}

