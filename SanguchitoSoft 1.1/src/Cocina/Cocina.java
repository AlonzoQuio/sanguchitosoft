/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Cocina;
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
/**
 *
 * @author JMJ
 */
public class Cocina {
    FacturacionCocinaDatasource datasourceCocina = new FacturacionCocinaDatasource();
    public void rellenarCocina(String descripcion,String cantidad,String modalidad){
        FacturacionCocina pc = new FacturacionCocina(descripcion,cantidad,modalidad);
        datasourceCocina.addFacturacionCocina(pc);
    }
    
    public void imprimirCocina(String pedi) {
        try {
//           

            JasperReport reporte = (JasperReport) JRLoader.loadObject("cocina.jasper");
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.put("pedido",pedi);
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(reporte, parametros, datasourceCocina);

            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            //JasperViewer.viewReport(jasperPrint, true);
           
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("Cocinar.pdf"));
            exporter.exportReport();

            PrinterJob printJob = PrinterJob.getPrinterJob();

            printJob.setJobName("Cocinar.pdf");
            JasperPrintManager.printReport(jasperPrint, false);

            try {
                printJob.print();
            } catch (Exception e) {
            }

        } catch (Exception e) {
        }
    }
}
