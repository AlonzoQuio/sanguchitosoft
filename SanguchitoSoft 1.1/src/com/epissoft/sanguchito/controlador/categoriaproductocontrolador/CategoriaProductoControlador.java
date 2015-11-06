package com.epissoft.sanguchito.controlador.categoriaproductocontrolador;

import com.epissoft.sanguchito.modelo.categoriaproductomodelo.implementacion.GestionarCategoriaProductosImplementacion;
import com.epissoft.sanguchito.modelo.categoriaproductomodelo.validacion.ValidacionTipoProducto;
import com.epissoft.sanguchito.persistencia.CategoriaExtra;
import com.epissoft.sanguchito.persistencia.CategoriaProducto;
import java.util.List;

public class CategoriaProductoControlador {
    public static String agregarCategoriaProducto(String detalle, String imagen){
        CategoriaProducto tipoP = new CategoriaProducto(0, detalle, true, imagen);
        GestionarCategoriaProductosImplementacion gest = new GestionarCategoriaProductosImplementacion();
        if(gest.insertarCategoriaProducto(tipoP)){
            return "CORRECTO";
        }else{
            return "Error: El nombre para la categoria ya esta en uso";
        }
    }
    public static String actualizarCategoriaProducto(int codigo,String detalle, String imagen){
        CategoriaProducto tipoP = new CategoriaProducto(codigo, detalle, true, imagen);
        GestionarCategoriaProductosImplementacion gest = new GestionarCategoriaProductosImplementacion();
        if(gest.actualizarCategoriaProducto(tipoP)){
            return "CORRECTO";
        }else{
            return "Error: El nombre para la categoria ya esta en uso";
        }
    }
    public static boolean habilitarCategoriaProducto(int codigo){
        GestionarCategoriaProductosImplementacion gest = new GestionarCategoriaProductosImplementacion();
        return gest.habilitarCategoriaProducto(codigo);
    }
    public static boolean inhabilitarCategoriaProducto(int codigo){
        GestionarCategoriaProductosImplementacion gest = new GestionarCategoriaProductosImplementacion();
        return gest.inhabilitarCategoriaProducto(codigo);
    }
    public static List<CategoriaProducto> listaCategoriaProducto(){
        return new GestionarCategoriaProductosImplementacion().listaCategoriaProductos();
    }
    public static List<CategoriaProducto> listaCategoriaProductoInhabilitadas(){
        return new GestionarCategoriaProductosImplementacion().listaCategoriaProductosInhabilitados();
    }
    public static List<CategoriaExtra> listaCategoriaExtraPorProducto(int codigo){
        return new GestionarCategoriaProductosImplementacion().listaExtraPorCategoriaProducto(codigo);
    }
    public static String agregarCategoriaExtraPorProducto(int codigo,List<CategoriaExtra> cat){
        GestionarCategoriaProductosImplementacion gest = new GestionarCategoriaProductosImplementacion();
        if(gest.agregarCategoriaExtraACategoriaProducto(codigo, cat)){
            return "CORRECTO";
        }else{
            return "Error: La(s) categoria(s) no pudieron ser asignadas";
        }
    }
}
