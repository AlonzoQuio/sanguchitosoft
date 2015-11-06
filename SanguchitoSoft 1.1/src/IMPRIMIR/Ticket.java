/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IMPRIMIR;

import java.awt.print.PrinterJob;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author gigo
 */
public class Ticket {
    FacturacionDatasource datasource = new FacturacionDatasource();
    public void rellenar(String descripcion, int cantidad,double total_){
        Facturacion p = new Facturacion(descripcion,cantidad,total_);
        datasource.addFacturacion(p);
    }
    
    public void imprimirticket(String usu,String descuento, String total,String pedi) {
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject("ticket.jasper");
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.put("nombre", usu);
            parametros.put("descuento", descuento);
            parametros.put("total_", total);
            parametros.put("pedido",pedi);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, datasource);

            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            JasperViewer.viewReport(jasperPrint, false);
           
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reporte2PDF.pdf"));
            exporter.exportReport();

            PrinterJob printJob = PrinterJob.getPrinterJob();

            printJob.setJobName("reporte2PDF.pdf");
            JasperPrintManager.printReport(jasperPrint, false);

            try {
                printJob.print();
            } catch (Exception e) {
            }

        } catch (Exception e) {
        }
    }
}
