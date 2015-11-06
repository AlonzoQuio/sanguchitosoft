package com.epissoft.sanguchito.controlador.kardexcontrolador;

import com.epissoft.sanguchito.modelo.almacenmodelo.implementacion.GestionarAlmacenImplementacion;
import com.epissoft.sanguchito.modelo.ingresokardexmodelo.implementacion.GestionarIngresoKardexImplementacion;
import com.epissoft.sanguchito.modelo.kardexmodelo.implementacion.GestionarKardexImplementacion;
import com.epissoft.sanguchito.persistencia.Gastos;
import com.epissoft.sanguchito.persistencia.IngresoKardex;
import com.epissoft.sanguchito.persistencia.Kardex;
import com.epissoft.sanguchito.persistencia.ProductoAlmacen;
import java.util.Calendar;
import java.util.List;

public class IngresoKardexControlador {
    public static void agregarIngresoKardex(int gasCod,int codProducto,int cantidad,float precio){
        Calendar fecha = Calendar.getInstance();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        Kardex k=new Kardex(0,dia,mes,anio);
        k.setProdAlmCod(new ProductoAlmacen(codProducto));
        new GestionarKardexImplementacion().nuevoKardex(k);
        IngresoKardex in=new IngresoKardex(k.getKarCod(), precio, cantidad);
        in.setGasCod(new Gastos(gasCod));
        new GestionarIngresoKardexImplementacion().nuevoIngresoKardex(in, k);
        new GestionarAlmacenImplementacion().actualizarAlmacen(codProducto, cantidad);
    }
    public static List<IngresoKardex> listaIngresoKardex(int codProducto){
        return null;
    }
}
