/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epissoft.sanguchito.modelo.tipousuario;

import com.epissoft.sanguchito.persistencia.Tipousuario;

/**
 *
 * @author HP
 */
public interface GestionarTipoUsuario {
    public Tipousuario buscarTipoUsuario(int codigo);
    public Tipousuario buscarTipoUsuario( String des);

}
