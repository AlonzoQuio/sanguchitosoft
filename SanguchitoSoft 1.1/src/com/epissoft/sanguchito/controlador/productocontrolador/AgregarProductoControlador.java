
package com.epissoft.sanguchito.controlador.productocontrolador;

import com.epissoft.sanguchito.controlador.utilitarios.ValidacionGeneral;
import com.epissoft.sanguchito.modelo.productomodelo.implementacion.GestionarProductosImplementacion;
import com.epissoft.sanguchito.persistencia.Producto;
import com.epissoft.sanguchito.persistencia.CategoriaProducto;

public class AgregarProductoControlador {
    public static String insertarProducto(String detalle,String imagen,String precio,String precioCarta,String tipo){
        float prec;
        float precCarta;
        int codTipo;
        try{
            prec=Float.parseFloat(precio);
            precCarta=Float.parseFloat(precioCarta);
        }catch(NumberFormatException e){
            return "Error: Ingrese un monto valido";
        }
        if(!ValidacionGeneral.precioValido(prec)||!ValidacionGeneral.precioValido(precCarta)){
            return "Error: Ingrese un monto valido";
        }else{
            codTipo=Integer.parseInt(tipo);
        
            Producto p=new Producto(0, detalle, prec,precCarta,imagen, true,-1);
            p.setCatProdCod(new CategoriaProducto(codTipo));
            GestionarProductosImplementacion gest=new GestionarProductosImplementacion();
            if(gest.insertarProducto(p)){
                return "CORRECTO";
            }else{
                return "Error: El nombre que desea utilizar, no esta disponible";
            }
        }
    }
}
