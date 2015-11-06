package com.epissoft.sanguchito.controlador.gastoscontrolador;

import com.epissoft.sanguchito.controlador.kardexcontrolador.IngresoKardexControlador;
import com.epissoft.sanguchito.controlador.utilitarios.ValidacionGeneral;
import com.epissoft.sanguchito.modelo.almacenmodelo.bean.InsumoAlmacen;
import com.epissoft.sanguchito.modelo.gastosmodelo.implementacion.GestionarGastosImplementacion;
import com.epissoft.sanguchito.persistencia.Empleado;
import com.epissoft.sanguchito.persistencia.Gastos;
import com.epissoft.sanguchito.persistencia.Proveedor;
import com.epissoft.sanguchito.persistencia.TipoDocumento;
import com.epissoft.sanguchito.persistencia.TipoGasto;
import com.epissoft.sanguchito.vista.ManejoSesion;
import java.util.ArrayList;
import java.util.Date;

public class GastosControlador {
    public static String agregarGasto(int tipo,Date fecha,int tipoDoc,String numDoc,String descripcion,String monto){
        float montoTotal;
        int numDocumento;
        try{
            montoTotal=Float.parseFloat(monto);
        }catch(Exception e){
            return "Ingrese un monto valido";
        }
        try{
            numDocumento=Integer.parseInt(numDoc);
        }catch(Exception e){
            return "El numero de documento solo puede contener numeros";
        }
        if(ValidacionGeneral.precioValido(montoTotal)){
            Gastos g=new Gastos(1,fecha.getDay(),fecha.getMonth(),fecha.getYear(),montoTotal);
            g.setGasNumDoc(numDocumento);
            g.setTipDocCod(new TipoDocumento(tipoDoc));
            g.setEmpDni(new Empleado(Integer.parseInt(ManejoSesion.getDNI())));
            g.setProvRuc(new Proveedor("00000000001"));
            g.setTipGasCod(new TipoGasto(tipo));
            new GestionarGastosImplementacion().agregarGasto(g);
            return "CORRECTO";
        }else{
            return "Ingrese un monto valido";
        }
    }
    public static String agregarGasto(Date fecha,int tipoDoc,String numDoc,String descripcion,float montoTotal,ArrayList<InsumoAlmacen> insumos,String ruc){
        int numDocumento;        
        try{
            numDocumento=Integer.parseInt(numDoc);
        }catch(Exception e){
            return "Error: El numero de documento solo puede contener numeros";
        }
        Gastos g = new Gastos(0, fecha.getDay(), fecha.getMonth(), fecha.getYear(), montoTotal);
        g.setGasDes("Pago por insumos");
        g.setGasNumDoc(numDocumento);
        g.setGasNBol(numDocumento);
        g.setTipDocCod(new TipoDocumento(tipoDoc));
        g.setEmpDni(new Empleado(Integer.parseInt(ManejoSesion.getDNI())));
        g.setProvRuc(new Proveedor(ruc));
        g.setTipGasCod(new TipoGasto(1));
        if(new GestionarGastosImplementacion().agregarGasto(g)){
            for(InsumoAlmacen ins:insumos){
                IngresoKardexControlador.agregarIngresoKardex(g.getGasCod(),ins.getCodInsumo(),ins.getCantidad(),ins.getPrecio());
            }
            return "CORRECTO";
        }else{
            return "No se pudo agregar el gasto";
        }
        

    }
}
