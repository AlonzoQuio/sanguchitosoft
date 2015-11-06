/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epissoft.sanguchito.modelo.usuariomodelo;

import java.util.List;
import com.epissoft.sanguchito.persistencia.Usuario;

/**
 *
 * @author HP
 */
public interface GestionarUsuario {
    public boolean insertarUsuario(Usuario u);
    public boolean actualizarUsuario(Usuario u);
    public boolean habilitarUsuario(String nombre);
    public boolean inhabilitarUsuario(String nombre);
    public Usuario buscarUsuario(int codigo);
    public Usuario buscarUsuario(String nombre);
    public List<Usuario> buscarPorCargo(int cargo);
    public List<Usuario> mostrarUsuarios();
}
