
package com.epissoft.sanguchito.modelo.clientemodelo;

import com.epissoft.sanguchito.persistencia.Cliente;


public interface GestionarCliente {
    public boolean agregarCliente(Cliente c);
    public boolean actualizarCliente();
    public String buscarCliente(int dni);
}
