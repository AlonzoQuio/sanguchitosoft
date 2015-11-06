
package com.epissoft.sanguchito.controlador.tipogastocontrolador;

import com.epissoft.sanguchito.controlador.utilitarios.ValidacionGeneral;
import com.epissoft.sanguchito.modelo.tipogastomodelo.implementacion.GestionarTipoGastoImplementacion;
import com.epissoft.sanguchito.persistencia.TipoGasto;
import java.util.List;


public class TipoGastoControlador {
    public static String agregarTipoGasto(String descripcion){
        if(ValidacionGeneral.caracteresInvalidos(descripcion)||!ValidacionGeneral.tamValido(descripcion)){
            return "El nombre no debe contener caracteres invalidos";
        }else{
            if(new GestionarTipoGastoImplementacion().agregarTipoGasto(new TipoGasto(0,descripcion,"true"))){
                return "CORRECTO";
            }else{
                return "El nombre que desea utilizar no esta disponible";
            }
        }   
    }
    public static String actualizarTipoGasto(int codigo,String descripcion){
        if(ValidacionGeneral.caracteresInvalidos(descripcion)||!ValidacionGeneral.tamValido(descripcion)){
            return "El nombre no debe contener caracteres invalidos";
        }else{
            if(new GestionarTipoGastoImplementacion().actualizarTipoGasto(new TipoGasto(codigo,descripcion,"true"))){
                return "CORRECTO";
            }else{
                return "El nombre que desea utilizar no esta disponible";
            }
        }
    }
    public static List<TipoGasto> listaTipoGasto(){
        return new GestionarTipoGastoImplementacion().listaTipoGasto();
    }
}
