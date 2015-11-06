
package com.epissoft.sanguchito.controlador.unidadmedidacontrolador;

import com.epissoft.sanguchito.controlador.utilitarios.ValidacionGeneral;
import com.epissoft.sanguchito.modelo.unidadmedidamodelo.implementacion.GestionarUnidadMedidaImplementacion;
import com.epissoft.sanguchito.persistencia.UnidadMedida;
import java.util.List;


public class UnidadMedidaControlador {
    public static String agregarUnidadMedida(String descripcion){
        if(ValidacionGeneral.tamValido(descripcion)&&!ValidacionGeneral.caracteresInvalidos(descripcion)){
            UnidadMedida u=new UnidadMedida(0,descripcion,true);
            GestionarUnidadMedidaImplementacion uni=new GestionarUnidadMedidaImplementacion();
            if(uni.agregarUnidadMedida(u)){
                return "CORRECTO";
            }else{
                return "El nombre que desea utilizar, ya esta en uso";
            }
        }else{
            return "El nombre no debe contener caracteres invalidos";
        }
    }
    public static List<UnidadMedida> listaUnidad(){
        return new GestionarUnidadMedidaImplementacion().listaUnidadMedida();
    }

    public static String actualizarUnidadMedida(Integer uniMedCod, String descripcion) {
        if(ValidacionGeneral.tamValido(descripcion)&&!ValidacionGeneral.caracteresInvalidos(descripcion)){
            UnidadMedida u=new UnidadMedida(uniMedCod,descripcion,true);
            GestionarUnidadMedidaImplementacion uni=new GestionarUnidadMedidaImplementacion();
            if(uni.modificarUnidadMedida(u)){
                return "CORRECTO";
            }else{
                return "El nombre que desea utilizar, ya esta en uso";
            }
        }else{
            return "El nombre no debe contener caracteres invalidos";
        }
    }
}
