/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epissoft.sanguchito.modelo.reportes;
import java.sql.*;
import java.io.File;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter.*;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author gigo
 */
public class ReporteVentas {
    Calendar calendario = Calendar.getInstance();
      String _dia=Integer.toString(calendario.get(Calendar.DATE));
      String _mes=Integer.toString(calendario.get(Calendar.MONTH));
      String _anio=Integer.toString(calendario.get(Calendar.YEAR));
      String _hora="_"+Integer.toString(calendario.get(Calendar.HOUR))+"_"+Integer.toString(calendario.get(Calendar.MINUTE))+"_"+Integer.toString(calendario.get(Calendar.SECOND));
      String fec=_anio+"-"+_mes+"-"+_dia+"-"+_hora;
     public void reportdia(String concepto_,String autor_,String anio_, String mes_, String dia_,String tipo){
        // TODO code application logic here
        try {
          Class.forName("org.postgresql.Driver");
         Connection conexion = DriverManager.getConnection("jdbc:postgresql://hard-plum.db.elephantsql.com:5432/hqwzxyfi", "hqwzxyfi", "MGnLXRQtfoVG_weLGP1iUAgCJO2j4Cp8");
        String ruta="Reportexdia.jasper";
        if( "1".equals(tipo))
        {
        ruta="Reportexdia.jasper";
        }
         if( "2".equals(tipo))
        {
        ruta="Reportexdiabarrashorizontales.jasper";
        }
          if( "3".equals(tipo))
        {
        ruta="Reportexdia torta.jasper";
        }
        JasperReport reporte = (JasperReport) JRLoader.loadObject(ruta);

        Map<String, String> parametros = new HashMap<String, String>();
        parametros.put("Concepto", concepto_);
        parametros.put("Autor", autor_);
        parametros.put("anio", anio_);
        parametros.put("mes", mes_);
        parametros.put("dia", dia_);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
       

        JRExporter exporter = new JRPdfExporter();
        
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        JasperViewer.viewReport(jasperPrint,false);
         exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("C:/REPORTES_/reporte"+fec+".pdf"));
       
        exporter.exportReport();
  
        } catch (Exception e) { 
            System.out.print(e.getMessage());
        }
   }
     
     //////////////////////////////////////////////////////////
     
     public void reportsemana(String concepto_,String autor_,String anio_, String mes_, String dia_min,String dia_max,String tipo){
           // TODO code application logic here
        try {
          Class.forName("org.postgresql.Driver");
         Connection conexion = DriverManager.getConnection("jdbc:postgresql://hard-plum.db.elephantsql.com:5432/hqwzxyfi", "hqwzxyfi", "MGnLXRQtfoVG_weLGP1iUAgCJO2j4Cp8");
        String ruta="Reportexsemana.jasper";
        if( "1".equals(tipo))
        {
        ruta="Reportexsemana.jasper";
        }
         if( "2".equals(tipo))
        {
        ruta="Reportexsemana(barrashorizontales).jasper";
        }
          if( "3".equals(tipo))
        {
        ruta="Reportexsemanatorta.jasper";
        }

        JasperReport reporte = (JasperReport) JRLoader.loadObject(ruta);
        Map<String, String> parametros = new HashMap<String, String>();
        parametros.put("Concepto", concepto_);
        parametros.put("Autor", autor_);
        parametros.put("anio", anio_);
        parametros.put("mes", mes_);
        parametros.put("dia_min", dia_min);
        parametros.put("dia_max", dia_max);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
       

        JRExporter exporter = new JRPdfExporter();
        
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        JasperViewer.viewReport(jasperPrint,false);
         exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("C:/REPORTES_/reporte"+fec+".pdf"));
        
        exporter.exportReport();
  
        } catch (Exception e) { 
            System.out.print(e.getMessage());
        }
   }
     
     //////////////////////////////////
     public void reportmes(String concepto_,String autor_,String anio_, String mes_,String tipo){
        // TODO code application logic here
         try {
             Class.forName("org.postgresql.Driver");
              Connection conexion = DriverManager.getConnection("jdbc:postgresql://hard-plum.db.elephantsql.com:5432/hqwzxyfi", "hqwzxyfi", "MGnLXRQtfoVG_weLGP1iUAgCJO2j4Cp8");

             String ruta = "Reportexmes.jasper";
             if ("1".equals(tipo)) {
                 ruta = "Reportexmes.jasper";
             }
             if ("2".equals(tipo)) {
                 ruta = "Reportexmes(barrashorizontales).jasper";
             }
             if ("3".equals(tipo)) {
                 ruta = "Reportexmestorta.jasper";
             }

             JasperReport reporte = (JasperReport) JRLoader.loadObject(ruta);
             Map<String, String> parametros = new HashMap<String, String>();
             parametros.put("Concepto", concepto_);
             parametros.put("Autor", autor_);
             parametros.put("anio", anio_);
             parametros.put("mes", mes_);

             JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);


             JRExporter exporter = new JRPdfExporter();

             exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
             JasperViewer.viewReport(jasperPrint, false);
             exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("C:/REPORTES_/reporte" + fec + ".pdf"));

             exporter.exportReport();

         } catch (Exception e) {
             System.out.print(e.getMessage());
         }
   }
     
     ////////
      public void reportanio(String concepto_,String autor_,String anio_,String tipo){
        // TODO code application logic here
        try {
          Class.forName("org.postgresql.Driver");
         Connection conexion = DriverManager.getConnection("jdbc:postgresql://hard-plum.db.elephantsql.com:5432/hqwzxyfi", "hqwzxyfi", "MGnLXRQtfoVG_weLGP1iUAgCJO2j4Cp8");
        String ruta="Reportexanio.jasper";
        if( "1".equals(tipo))
        {
        ruta="Reportexanio.jasper";
        }
         if( "2".equals(tipo))
        {
        ruta="Reportexanio(barrashorizontales).jasper";
        }
          if( "3".equals(tipo))
        {
        ruta="Reportexaniotorta.jasper";
        }

        JasperReport reporte = (JasperReport) JRLoader.loadObject(ruta);
        Map<String, String> parametros = new HashMap<String, String>();
        parametros.put("Concepto", concepto_);
        parametros.put("Autor", autor_);
        parametros.put("anio", anio_);
       
        
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
       

        JRExporter exporter = new JRPdfExporter();
        
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        JasperViewer.viewReport(jasperPrint,false);
         exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("C:/REPORTES_/reporte"+fec+".pdf"));
        
        exporter.exportReport();
  
        } catch (Exception e) { 
            System.out.print(e.getMessage());
        }
   }
      
      
     ////////////////
       public void reportperiodo(String concepto_,String autor_,String fecha_inicio,String fecha_fin, String tipo){
       // TODO code application logic here
        try {
          Class.forName("org.postgresql.Driver");
         Connection conexion = DriverManager.getConnection("jdbc:postgresql://hard-plum.db.elephantsql.com:5432/hqwzxyfi", "hqwzxyfi", "MGnLXRQtfoVG_weLGP1iUAgCJO2j4Cp8");
        String ruta="Reportexperiodo.jasper";
        if( "1".equals(tipo))
        {
        ruta="Reportexperiodo.jasper";
        }
         if( "2".equals(tipo))
        {
        ruta="Reportexperiodo(barrashorizontales).jasper";
        }
          if( "3".equals(tipo))
        {
        ruta="Reportexperiodotorta.jasper";
        }

        JasperReport reporte = (JasperReport) JRLoader.loadObject(ruta);
        Map<String, String> parametros = new HashMap<String, String>();
        parametros.put("Concepto", concepto_);
        parametros.put("Autor", autor_);
        parametros.put("fecha_inicio", fecha_inicio);
        parametros.put("fecha_fin", fecha_fin);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
       

        JRExporter exporter = new JRPdfExporter();
        
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        JasperViewer.viewReport(jasperPrint,false);
         exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("C:/REPORTES_/reporte"+fec+".pdf"));
       
        
        exporter.exportReport();
  
        } catch (Exception e) { 
            System.out.print(e.getMessage());
        }
   }
      
}
