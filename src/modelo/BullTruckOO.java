/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import Controlador.ControladorContenedor;
import Vista.Contenedor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Axel Reyez
 */
public class BullTruckOO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            Contenedor co=new Contenedor();
            ControladorContenedor coco=new ControladorContenedor(co);
            co.setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BullTruckOO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(BullTruckOO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BullTruckOO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }
    
}
