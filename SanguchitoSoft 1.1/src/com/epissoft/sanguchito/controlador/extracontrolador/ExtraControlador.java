

package com.epissoft.sanguchito.controlador.extracontrolador;

import com.epissoft.sanguchito.controlador.utilitarios.ValidacionGeneral;
import com.epissoft.sanguchito.modelo.extramodelo.implemenacion.GestionarExtraImplementacion;
import com.epissoft.sanguchito.persistencia.CategoriaExtra;
import com.epissoft.sanguchito.persistencia.Extras;
import java.util.List;

public class ExtraControlador {
    public static String agregarExtra(String nombre,String prec,String precCart,int categoria){
        float precio;
        float precioCarta;
        String mensaje="CORRECTO";
        try{
            precio=Float.parseFloat(prec);
            precioCarta=Float.parseFloat(precCart);
        }catch(Exception e){
            return "Ingrese un monto valido";
        }
        if(!ValidacionGeneral.precioValido(precio)||!ValidacionGeneral.precioValido(precioCarta)){
            mensaje= "Ingrese un monto valido";
        }else{
            Extras ex=new Extras(0,nombre,precio,precioCarta, true,-1);
            ex.setCatExtCod(new CategoriaExtra(categoria));
            if(!new GestionarExtraImplementacion().insertarExtra(ex)){
                mensaje="El nombre que desea utilizar, no esta disponible";
            }
        }
        return mensaje;
    }
    public static String actualizarExtra(int codigo,String nombre,String prec,String precCart,int categoria,int codProdAlm){
        float precio;
        float precioCarta;
        String mensaje="CORRECTO";
        try{
            precio=Float.parseFloat(prec);
            precioCarta=Float.parseFloat(precCart);
        }catch(Exception e){
            return "Ingrese un monto valido";
        }
        if(!ValidacionGeneral.precioValido(precio)||!ValidacionGeneral.precioValido(precioCarta)){
            mensaje= "Ingrese un monto valido";
        }else{
            Extras ex=new Extras(codigo,nombre,precio,precioCarta, true,codProdAlm);
            ex.setCatExtCod(new CategoriaExtra(categoria));
            if(!new GestionarExtraImplementacion().actualizarExtra(ex)){
                mensaje="El nombre que desea utilizar, no esta disponible";
            }
        }
        return mensaje;
    }
    public static List<Extras> listaExtrasPorCategoria(int codCat){
        return new GestionarExtraImplementacion().buscarExtrasPorCategoria(codCat);
    }
    public static List<Extras> listaExtrasInhabilitadosPorCategoria(int codCat){
        return new GestionarExtraImplementacion().buscarExtrasInhabilitadosPorCategoria(codCat);
    }
    public static List<Extras> listaExtras(){
        return new GestionarExtraImplementacion().listaExtras();
    }
    public static void inhabilitarExtra(int cod){
        new GestionarExtraImplementacion().inhabilitarExtra(cod);
    }
    public static void habilitarExtra(int cod){
        new GestionarExtraImplementacion().habilitarExtra(cod);
    }
}
