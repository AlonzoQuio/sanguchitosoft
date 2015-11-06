
package com.epissoft.sanguchito.controlador.kardexcontrolador;

import com.epissoft.sanguchito.modelo.almacenmodelo.bean.InsumoAlmacen;
import com.epissoft.sanguchito.modelo.almacenmodelo.implementacion.GestionarAlmacenImplementacion;
import com.epissoft.sanguchito.modelo.egresokardexmodelo.implementacion.GestionarEgresoKardexImplementacion;
import com.epissoft.sanguchito.modelo.kardexmodelo.implementacion.GestionarKardexImplementacion;
import com.epissoft.sanguchito.persistencia.EgresoKardex;
import com.epissoft.sanguchito.persistencia.Kardex;
import com.epissoft.sanguchito.persistencia.ProductoAlmacen;
import java.util.ArrayList;
import java.util.Calendar;


public class EgresoKardexControlador {
    public static void agregarEgresoKardex(int codProducto,int cantidad){
        Calendar fecha = Calendar.getInstance();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        
        Kardex k=new Kardex(0,dia,mes,anio);
        k.setProdAlmCod(new ProductoAlmacen(codProducto));
        new GestionarKardexImplementacion().nuevoKardex(k);
        EgresoKardex in=new EgresoKardex(k.getKarCod(),cantidad);
        new GestionarEgresoKardexImplementacion().nuevoEgresoKardex(in, k);
        new GestionarAlmacenImplementacion().actualizarAlmacen(codProducto, cantidad*(-1));
    }
    public static void agregarEgresoKardex(ArrayList<InsumoAlmacen> prod){
        for(InsumoAlmacen p:prod){
            agregarEgresoKardex(p.getCodInsumo(),p.getCantidad());
        }
    }
}
