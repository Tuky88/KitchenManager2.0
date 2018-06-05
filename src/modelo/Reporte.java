/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import DAO.ComprasDAO;
import DAO.PagoDAO;
import DAO.ProductoDAO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Axel Reyez
 */
public class Reporte{
    //atributos
    private CajaRegistradora genera;
    private String dia;
    private String hora;
    private String Gerente;
    private String cadena="C:\\KitchenManager2.0\\src\\Generados\\Reporte\\";

    public Reporte(String dia, String hora, String Gerente) {
        this.dia = dia;
        this.hora = hora;
        this.Gerente = Gerente;
        genera=new CajaRegistradora(Gerente);
    }
    public Reporte()
    {
        dia="05-06-2018";
        hora="10:18 am";
        Gerente="Axel";
    }
    
    
    public void ReporteCompras(String tipoReporte,String Busqueda) throws SQLException, DocumentException
    {
        ComprasDAO com=new ComprasDAO();
            ResultSet rs;
                    String nombrearchivo;

nombrearchivo="Reporte"+tipoReporte+"-"+dia+ ".pdf";
 Document documento = new Document();
 FileOutputStream ficheroPdf;
 String fin ;
 try 
{
    fin=cadena+nombrearchivo;
 ficheroPdf = new FileOutputStream(fin);
 PdfWriter.getInstance(
                       documento, 
                       ficheroPdf
                       ).setInitialLeading(20);
}
catch (FileNotFoundException | DocumentException ex) 
{
 System.out.println(ex.toString());
}
 String introduccion;
        if(tipoReporte.equals("ID"))
        {
            introduccion="Reporte por ID"+Busqueda;
            rs=com.BuscarId(Busqueda);
        }  
        else
        {
           rs=com.BuscarFecha(Busqueda);
           introduccion="Reporte por Fecha de:"+Busqueda;
        }
       documento.open();
        documento.add(new Paragraph("KITCHEN MANAGER\n ",FontFactory.getFont("arial",20)));
        Paragraph parrafo2 = new Paragraph(introduccion+ " \nGenerado : "+dia +" A las:"+ hora+"\n",FontFactory.getFont("arial",20));
    parrafo2.setAlignment(0);
documento.add(parrafo2);
PdfPTable tabla = new PdfPTable(3);
float[] medidaCeldas = {5f, 2f,2.3f};
tabla.setWidths(medidaCeldas);
documento.add(new Paragraph("\n"));
int cuantascompras=0;
                double totalinvertido=0;
if(tipoReporte.equals("ID"))
{
    tabla.addCell(new Paragraph(("Producto"),FontFactory.getFont("arial",16)));
    tabla.addCell(new Paragraph(("Precio"),FontFactory.getFont("arial",16)));
    tabla.addCell(new Paragraph(("Fecha"),FontFactory.getFont("arial",16)));
    documento.add(new Paragraph("\n"));
    PdfPCell celda,celda1,celda2;
    int tamaño=14;
                while(rs.next())
                {
                String producto,fecha;
                double precio;
                producto=rs.getString("Producto");
                cuantascompras++;
                precio=rs.getDouble("precio");
                totalinvertido+=precio;
                fecha=rs.getString("fecha");
                  
                    celda = new PdfPCell (new Paragraph(producto,  FontFactory.getFont("arial",tamaño)));         
                    celda1 = new PdfPCell (new Paragraph("$"+precio,  FontFactory.getFont("arial",tamaño)));          
                    celda2 = new PdfPCell (new Paragraph(fecha,  FontFactory.getFont("arial",tamaño)));
      
          tabla.addCell(celda);
          tabla.addCell(celda1);
          tabla.addCell(celda2);

                }
documento.add(new Paragraph("\nCompras realizadas: "+totalinvertido,FontFactory.getFont("arial",24)));
    documento.add(new Paragraph("\nTotal invertido:$ "+totalinvertido,FontFactory.getFont("arial",24)));
                documento.add(tabla);
}
else
{
 tabla.addCell(new Paragraph(("Id"),FontFactory.getFont("arial",16)));
    tabla.addCell(new Paragraph(("Producto"),FontFactory.getFont("arial",16)));
    tabla.addCell(new Paragraph(("Precio"),FontFactory.getFont("arial",16)));
    documento.add(new Paragraph("\n"));
    PdfPCell celda,celda1,celda2;
    int tamaño=14;
                while(rs.next())
                {
                String id,producto;
                double precio;
                id=rs.getString("ID");
                cuantascompras++;
                precio=rs.getDouble("precio");
                totalinvertido+=precio;
                producto=rs.getString("producto");
                  
                    celda = new PdfPCell (new Paragraph(id,  FontFactory.getFont("arial",tamaño)));         
                    celda1 = new PdfPCell (new Paragraph(producto,  FontFactory.getFont("arial",tamaño)));          
                    celda2 = new PdfPCell (new Paragraph("$"+precio,  FontFactory.getFont("arial",tamaño)));
      
          tabla.addCell(celda);
          tabla.addCell(celda1);
          tabla.addCell(celda2);

                }
documento.add(new Paragraph("\nCompras realizadas: "+totalinvertido+"\n",FontFactory.getFont("arial",24)));
    documento.add(new Paragraph("\nTotal invertido:$ "+totalinvertido+"\n",FontFactory.getFont("arial",24)));
                documento.add(tabla);   
}
    
                

documento.close();
    }
    
    
    
     public void ReportePDF() throws DocumentException, SQLException
     {
         ProductoDAO productos=new ProductoDAO();
         ResultSet rs;
         rs=productos.ObtenerTodo();
         String nombrearchivo="Reporte"+"-"+dia+ ".pdf";
         Document documento = new Document();
         FileOutputStream ficheroPdf;
         String fin ;
        try 
            {
            fin=cadena+nombrearchivo;
            ficheroPdf = new FileOutputStream(fin);
            PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
            }
        catch (FileNotFoundException | DocumentException ex) 
            {
            System.out.println(ex.toString());
            }
        
        documento.open();
        boolean add = documento.add(new Paragraph("Kitchen Manager System\n ",FontFactory.getFont("arial",20)));
        Paragraph parrafo2 = new Paragraph("Reporte de ventas"+ " \nGenerado : "+dia +" A las:"+ hora+"\n",FontFactory.getFont("arial",20));
        documento.add(new Paragraph("\nGenerado por:"+Gerente,FontFactory.getFont("arial",18)));
        parrafo2.setAlignment(0);
        documento.add(parrafo2);
        PdfPTable tabla = new PdfPTable(3);
        float[] medidaCeldas = {5f,2f,2.3f};
        tabla.setWidths(medidaCeldas);
        documento.add(new Paragraph("\n\n\n"));
        int cuantascompras=0;
                double totalinvertido=0;
    tabla.addCell(new Paragraph(("Producto"),FontFactory.getFont("arial",14)));
    tabla.addCell(new Paragraph(("Cuantos"),FontFactory.getFont("arial",14)));
    tabla.addCell(new Paragraph(("Subtotal"),FontFactory.getFont("arial",14)));
    documento.add(new Paragraph("\n"));
    PdfPCell celda,celda1,celda2;
    int tamaño=14;
    double total=0;
                while(rs.next())
                {
                String producto;
                double precio;
                producto=rs.getString("id");
                cuantascompras=rs.getInt("CuantosD");
                precio=rs.getDouble("precio");
                totalinvertido=cuantascompras*precio;
                total+=totalinvertido;
                    celda = new PdfPCell (new Paragraph(producto,  FontFactory.getFont("arial",tamaño)));         
                    celda1 = new PdfPCell (new Paragraph(Integer.toString(cuantascompras),  FontFactory.getFont("arial",tamaño)));          
                    celda2 = new PdfPCell (new Paragraph("$"+totalinvertido,  FontFactory.getFont("arial",tamaño)));
      
          tabla.addCell(celda);
          tabla.addCell(celda1);
          tabla.addCell(celda2);

                }
                genera.setAcumulado(total);
                genera.setCajaFinal();
                documento.add(new Paragraph("\n\nCaja inicial:$ "+genera.getCajaInicial(),FontFactory.getFont("arial",14)));
                PagoDAO pd=new PagoDAO();
                double efe,ter;
                efe=pd.getNumeroE();
                ter=pd.getNumeroT();
                documento.add(new Paragraph("\n\nVentas totales:$ "+genera.getAcumulado() +"\tEfectivo: "+efe + "\tTerminal"+ter
                        ,FontFactory.getFont("arial",14)));
                documento.add(new Paragraph("\n\nCaja final$ "+(genera.getCajaFinal()-ter)+"\n\n",FontFactory.getFont("arial",14)));
                documento.add(tabla);  
                this.genera.CorteCaja();
                documento.close();
     }
    
}
