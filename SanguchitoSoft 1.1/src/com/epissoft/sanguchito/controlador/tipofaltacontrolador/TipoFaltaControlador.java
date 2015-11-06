/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador.tipofaltacontrolador;

import java.util.List;
import com.epissoft.sanguchito.modelo.tipofaltamodelo.implementacion.GestionarTipoFaltaImplementacion;
import com.epissoft.sanguchito.persistencia.TipoFalta;

/**
 *
 * @author HP
 */
public class TipoFaltaControlador {
    public static GestionarTipoFaltaImplementacion gtf;
    
    public TipoFaltaControlador(){
        gtf = new GestionarTipoFaltaImplementacion();
    }
    
    public boolean insertarTipoFalta(String descripcion){
        TipoFalta tf = new TipoFalta(0,descripcion,true);
        gtf.insertarTipoFalta(tf);
        return true;
    }
    public boolean actualizarTipoFalta(String codigo, String descripcion){
        Integer cod = Integer.parseInt(codigo);
        TipoFalta tf = gtf.buscarTipoFalta(cod);
        tf.setTipFalDes(descripcion);
        gtf.actualizarTipoFalta(tf);
        return true;
    }
    public boolean habilitarTipoFalta(String codigo){
        Integer cod = Integer.parseInt(codigo);
        gtf.habilitarTipoFalta(cod);
        return true; 
    }
    public boolean inhabilitarTipoFalta(String codigo){
        Integer cod = Integer.parseInt(codigo);
        gtf.inhabilitarTipoFalta(cod);
        return true;
    }
    public TipoFalta buscarTipoFalta(String codigo){
        Integer cod = Integer.parseInt(codigo);
        return gtf.buscarTipoFalta(cod);
    }
    public TipoFalta buscarTipoFaltaDes(String descripcion){
        return gtf.buscarTipoFalta(descripcion);
    }
    public List<TipoFalta> listaTipoFaltas(){
        return gtf.listaTipoFaltas();
    }
}
