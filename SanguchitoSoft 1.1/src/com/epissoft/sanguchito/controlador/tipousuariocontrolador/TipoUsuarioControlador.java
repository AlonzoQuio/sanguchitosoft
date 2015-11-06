/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epissoft.sanguchito.controlador.tipousuariocontrolador;

import com.epissoft.sanguchito.modelo.tipousuario.implementacion.GestionarTipoUsuarioImplementacion;
import com.epissoft.sanguchito.persistencia.Tipousuario;

/**
 *
 * @author HP
 */
public class TipoUsuarioControlador {
    public static GestionarTipoUsuarioImplementacion gtf;
    
    public TipoUsuarioControlador(){
        gtf = new GestionarTipoUsuarioImplementacion();
    }
    
    public Tipousuario buscarTipoUsuario(String codigo){
        Integer cod = Integer.parseInt(codigo);
        return gtf.buscarTipoUsuario(cod);
    }
    public Tipousuario buscarTipoUsuarioNom(String codigo){
        return gtf.buscarTipoUsuario(codigo);
    }
    
}
