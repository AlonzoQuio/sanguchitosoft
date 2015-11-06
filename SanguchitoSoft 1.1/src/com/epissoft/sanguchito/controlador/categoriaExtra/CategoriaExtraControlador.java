
package com.epissoft.sanguchito.controlador.categoriaExtra;

import com.epissoft.sanguchito.modelo.categoriaextramodelo.implementacion.GestionarCategoriaExtraImplementacion;
import com.epissoft.sanguchito.persistencia.CategoriaExtra;
import java.util.List;

public class CategoriaExtraControlador {
    public static String agregarCategoriaExtra(String nombre, String observacion,String imagen){
        GestionarCategoriaExtraImplementacion ges=new GestionarCategoriaExtraImplementacion();
        CategoriaExtra ext=new CategoriaExtra(0,nombre,observacion,imagen,true);
        if(ges.agregarCategoriaExtra(ext))
            return "CORRECTO";
        return "El nombre de la categoria ya existe";
    }
    public static String actualizarCategoriaExtra(int cod,String nombre, String observacion,String imagen){
        GestionarCategoriaExtraImplementacion ges=new GestionarCategoriaExtraImplementacion();
        CategoriaExtra ext=new CategoriaExtra(cod,nombre,observacion,imagen,true);
        if(ges.modificarCategoriaExtra(ext))
            return "CORRECTO";
        return "El nombre de la categoria ya existe";
    }
//    public static String agregarCategoriaProductoAExtra(int codCategoriaExtra,int codCategoriaProducto){
//        GestionarCategoriaExtraImplementacion ges=new GestionarCategoriaExtraImplementacion();
//        CategoriaExtra ext=new CategoriaExtra(codCategoriaExtra);
//        if(ges.agregarCategoriaProductoAExtra(ext, codCategoriaProducto))
//            return "CORRECTO";
//        return "El nombre de la categoria ya existe";
//    }
    public static List<CategoriaExtra> listaCategoriaExtrasPorCategoriaProducto(int codCatProd){
        return new GestionarCategoriaExtraImplementacion().listaCategoriasExtraPorCategoriaProducto(codCatProd);
    }
    public static List<CategoriaExtra> listaCategoriaExtras(){
        return new GestionarCategoriaExtraImplementacion().listaCategoriaExtras();
    }
    public static List<CategoriaExtra> listaCategoriaExtrasInhabilitadas(){
        return new GestionarCategoriaExtraImplementacion().listaCategoriaExtrasInhabilitadas();
    }

    public static void inhabilitarCategoriaExtra(Integer catExtCod) {
        new GestionarCategoriaExtraImplementacion().inhabilitarCategoriaExtra(catExtCod);
    }
    public static void habilitarCategoriaExtra(Integer catExtCod) {
        new GestionarCategoriaExtraImplementacion().habilitarCategoriaExtra(catExtCod);
    }
}
