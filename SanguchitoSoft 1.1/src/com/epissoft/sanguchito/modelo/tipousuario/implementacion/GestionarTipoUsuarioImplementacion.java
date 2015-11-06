/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epissoft.sanguchito.modelo.tipousuario.implementacion;

import com.epissoft.sanguchito.modelo.tipousuario.GestionarTipoUsuario;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.Tipousuario;
import com.epissoft.sanguchito.persistencia.jpa.TipousuarioJpaController;

/**
 *
 * @author HP
 */
public class GestionarTipoUsuarioImplementacion implements GestionarTipoUsuario {
    public Tipousuario buscarTipoUsuario( int tu){
        return new TipousuarioJpaController(SManager.inicializar()).findTipousuario(tu);        
    }
    public Tipousuario buscarTipoUsuario( String des){
        return new TipousuarioJpaController(SManager.inicializar()).findTipousuario(des);        
    }

}
