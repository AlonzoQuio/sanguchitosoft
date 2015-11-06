
package com.epissoft.sanguchito.modelo.clientemodelo.implementacion;

import com.epissoft.sanguchito.modelo.clientemodelo.GestionarCliente;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.Cliente;
import com.epissoft.sanguchito.persistencia.jpa.ClienteJpaController;


public class GestionarClienteImplementacion implements GestionarCliente{

    @Override
    public boolean agregarCliente(Cliente c) {       
        ClienteJpaController cc=new ClienteJpaController(SManager.inicializar());
        try{
            cc.create(c);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean actualizarCliente() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public String buscarCliente(int dni){
        ClienteJpaController cc=new ClienteJpaController(SManager.inicializar());
        Cliente c=cc.findCliente(dni);
        if(c!=null){
            return c.getClieNom()+" "+c.getClieApe();
        }else{
            return "NN";
        }
    }
}
