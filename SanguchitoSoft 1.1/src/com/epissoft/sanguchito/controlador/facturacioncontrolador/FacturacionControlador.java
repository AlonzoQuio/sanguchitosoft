
package com.epissoft.sanguchito.controlador.facturacioncontrolador;

import com.epissoft.sanguchito.controlador.usuariocontrolador.UsuarioControlador;
import com.epissoft.sanguchito.modelo.facturaciondetallemodelo.implementacion.GestionFacturacionDetalleImplementacion;
import com.epissoft.sanguchito.modelo.facturacionextramodelo.implementacion.FacturacionExtraImplementacion;
import com.epissoft.sanguchito.modelo.facturacionmodelo.implementacion.GestionarFacturacionImplentacion;
import com.epissoft.sanguchito.modelo.productomodelo.implementacion.GestionarProductosImplementacion;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.Cliente;
import com.epissoft.sanguchito.persistencia.Extras;
import com.epissoft.sanguchito.persistencia.Facturacion;
import com.epissoft.sanguchito.persistencia.FacturacionDetalle;
import com.epissoft.sanguchito.persistencia.FacturaciondExtra;
import com.epissoft.sanguchito.persistencia.Producto;
import com.epissoft.sanguchito.persistencia.TipoFacturacion;
import com.epissoft.sanguchito.persistencia.jpa.FacturacionDetalleJpaController;
import com.epissoft.sanguchito.vista.ManejoSesion;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FacturacionControlador {

    public static float retornarTotal(ArrayList<Producto> productos, ArrayList<ArrayList<Extras>> extras,ArrayList<ArrayList<Integer>> cantidades){
        ArrayList<Float> arr=funcionTotal(productos, extras,cantidades);
        float total=0;
        for(Float f:arr){
            total+=f;
        }
        return total;
    }
    public static float retornarTotal(Producto producto,ArrayList<Extras> extras,ArrayList<Integer> cantidades){
        float total=producto.getProPreCar();
        if(!extras.isEmpty()){
            for(int i=0;i<extras.size();i++){
                total+=extras.get(i).getExtPrecSolCar()*cantidades.get(i);
            }
        }
        return total;
    }
    public static ArrayList<Float> funcionTotal(ArrayList<Producto> productos, ArrayList<ArrayList<Extras>> extras, ArrayList<ArrayList<Integer>> cantidades){
        ArrayList<Float> precios = new ArrayList<>();
        float total = 0;
        GestionarProductosImplementacion gpi = new GestionarProductosImplementacion();
        for(int i = 0; i < productos.size(); i++){
            total += productos.get(i).getProPreCar();
            if(!extras.get(i).isEmpty()){
                for(int j = 0; j < extras.get(i).size(); j++){
                    total+=extras.get(i).get(j).getExtPrecSolCar()*cantidades.get(i).get(j);
                }
            }
            precios.add(total);
            total = 0;
        }        
        return precios;
    }
    
    public static String agregarFacturacion(ArrayList<Producto> productos, ArrayList<ArrayList<Extras>> extras, ArrayList<ArrayList<Integer>> cantidades,String desc,boolean conTarjeta){
        float descuento;
        if(desc.length()>0){
            try{
                descuento=Float.parseFloat(desc);
            }catch(Exception e){
                return "Ingrese un monto de descuento valido";
            }
        }else{
            descuento=0;
        }
        Calendar fecha = Calendar.getInstance();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        Time hora=new Time(fecha.getTime().getHours(),fecha.getTime().getMinutes(),fecha.getTime().getSeconds());
        float total=retornarTotal(productos, extras, cantidades);
        Facturacion f=new Facturacion(0,dia,mes,anio,hora,total);
        f.setFacDesc(descuento);
        f.setClieDni(new Cliente(1));
        if(conTarjeta){
            f.setTipFacCod(new TipoFacturacion(2));
        }else{
            f.setTipFacCod(new TipoFacturacion(1));
        }
        f.setUsuCod(UsuarioControlador.buscarUsuarioNom(ManejoSesion.getUser()));
        new GestionarFacturacionImplentacion().insertarFacturacion(f);
        for(int i=0;i<productos.size();i++){
            FacturacionDetalle facDet=new FacturacionDetalle(new FacturacionDetalleJpaController(SManager.inicializar()).getFacturacionDetalleCount()+1, 0,productos.get(i).getProPreCar());            
            facDet.setProCod(productos.get(i));
            facDet.setFacCod(f);
            new GestionFacturacionDetalleImplementacion().insertarFacturacionDetalle(facDet);
            if(!extras.get(i).isEmpty()){
                for(int j = 0; j < extras.get(i).size(); j++){
                    FacturaciondExtra facDetExt=new FacturaciondExtra(0,cantidades.get(i).get(j),extras.get(i).get(j).getExtPrecSolCar());
                    facDetExt.setExtCod(extras.get(i).get(j));
                    facDetExt.setFacDetSec(facDet);
                    //facDetExt.
                    new FacturacionExtraImplementacion().insertarFacturacionExtraDetalle(facDetExt);
                }
            }
        }
        
        return "CORRECTO";
    }
    public static String agregarFacturacion(ArrayList<Producto> productos, ArrayList<ArrayList<Extras>> extras, ArrayList<ArrayList<Integer>> cantidades,String desc,int cliente,boolean conTarjeta){
        float descuento;
        if(desc.length()>0){
            try{
                descuento=Float.parseFloat(desc);
            }catch(Exception e){
                return "Ingrese un monto de descuento valido";
            }
        }else{
            descuento=0;
        }
        Calendar fecha = Calendar.getInstance();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        Time hora=new Time(fecha.getTime().getHours(),fecha.getTime().getMinutes(),fecha.getTime().getSeconds());
        float total=retornarTotal(productos, extras, cantidades);
        Facturacion f=new Facturacion(0,dia,mes,anio,hora,total);
        f.setFacDesc(descuento);
        f.setClieDni(new Cliente(cliente));
        if(conTarjeta){
            f.setTipFacCod(new TipoFacturacion(2));
        }else{
            f.setTipFacCod(new TipoFacturacion(1));
        }
        f.setTipFacCod(new TipoFacturacion(1));
        f.setUsuCod(UsuarioControlador.buscarUsuarioNom(ManejoSesion.getUser()));
        new GestionarFacturacionImplentacion().insertarFacturacion(f);
        for(int i=0;i<productos.size();i++){
            FacturacionDetalle facDet=new FacturacionDetalle(new FacturacionDetalleJpaController(SManager.inicializar()).getFacturacionDetalleCount()+1, 0,productos.get(i).getProPreCar());            
            facDet.setProCod(productos.get(i));
            facDet.setFacCod(f);
            new GestionFacturacionDetalleImplementacion().insertarFacturacionDetalle(facDet);
            if(!extras.get(i).isEmpty()){
                for(int j = 0; j < extras.get(i).size(); j++){
                    FacturaciondExtra facDetExt=new FacturaciondExtra(0,cantidades.get(i).get(j),extras.get(i).get(j).getExtPrecSolCar());
                    facDetExt.setExtCod(extras.get(i).get(j));
                    facDetExt.setFacDetSec(facDet);
                    new FacturacionExtraImplementacion().insertarFacturacionExtraDetalle(facDetExt);
                }
            }
        }
        
        return "CORRECTO";
    }
    public static List<Facturacion> facturasPeriodo(int inicio,int fin){
        return new GestionarFacturacionImplentacion().facturasPeriodo(inicio,fin);
    }
    public static int facturaAcual(){
        return new GestionarFacturacionImplentacion().facturaAcual();
    }
}
