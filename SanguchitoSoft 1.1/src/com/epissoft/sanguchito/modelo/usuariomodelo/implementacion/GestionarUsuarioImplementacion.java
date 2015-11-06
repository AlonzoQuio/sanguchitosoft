/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epissoft.sanguchito.modelo.usuariomodelo.implementacion;

import java.util.List;
import com.epissoft.sanguchito.modelo.usuariomodelo.GestionarUsuario;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.Usuario;
import com.epissoft.sanguchito.persistencia.jpa.UsuarioJpaController;

/**
 *
 * @author HP
 */
public class GestionarUsuarioImplementacion implements GestionarUsuario{
    @Override
    public boolean insertarUsuario(Usuario u){
        UsuarioJpaController uC = new UsuarioJpaController(SManager.inicializar());
        try{
            uC.create(u);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean actualizarUsuario(Usuario u){
        UsuarioJpaController usuarioPController = new UsuarioJpaController(SManager.inicializar());
        Usuario usu = usuarioPController.findUsuario(u.getUsuCod());
        try{
            usu.setUsuNomUsu(u.getUsuNomUsu());
            usu.setUsuPasUsu(u.getUsuPasUsu());
            usuarioPController.edit(usu);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean habilitarUsuario(String codigo){
        UsuarioJpaController uC = new UsuarioJpaController(SManager.inicializar());
        Usuario usu = uC.findUsuario(codigo);
        try{
            usu.setUsuEst(true);
            uC.edit(usu);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean inhabilitarUsuario(String codigo){
        UsuarioJpaController uC = new UsuarioJpaController(SManager.inicializar());
        Usuario usu = uC.findUsuario(codigo);
        try{
            usu.setUsuEst(false);
            uC.edit(usu);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public Usuario buscarUsuario(int codigo){
        return new UsuarioJpaController(SManager.inicializar()).findUsuario(codigo);
    }
    public List<Usuario> buscarUsuarioDni(int codigo){
        return new UsuarioJpaController(SManager.inicializar()).findUsuarioDni(codigo);
    }
    @Override
    public List<Usuario> buscarPorCargo(int cargo){
        return null;
    }
    @Override
    public Usuario buscarUsuario(String nombre){
        return new UsuarioJpaController(SManager.inicializar()).findUsuario(nombre);
    }
    @Override
    public List<Usuario> mostrarUsuarios(){
        return new UsuarioJpaController(SManager.inicializar()).findUsuarioEntities();
    }
}
