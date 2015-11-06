
package com.epissoft.sanguchito.controlador.clientecontrolador;

import com.epissoft.sanguchito.modelo.clientemodelo.implementacion.GestionarClienteImplementacion;
import com.epissoft.sanguchito.persistencia.Cliente;
import com.epissoft.sanguchito.persistencia.TipoCliente;


public class ClienteControlador {
    public static String agregarCliente(String ndni,String nombre,String ap1){
        if(ndni.length()!=8){
            return "Error: Ingrese un DNI Correcto";
        }
        int dni;
        try{
            dni=Integer.parseInt(ndni);
        }catch(Exception e){
            return "Error: Ingrese un DNI Correcto";
        }
        Cliente c=new Cliente(dni,true);
        c.setClieNom(nombre);
        c.setClieApe(ap1);
        
        c.setTipClieCod(new TipoCliente(1));
        if(new GestionarClienteImplementacion().agregarCliente(c)){
            return "CORRECTO";
        }else{
            return "Error: El numero de DNI ya esta siendo utilizado";
        }
    }
    public static String buscarCliente(String ndni){
        if(ndni.length()!=8){
            return "";
        }
        int dni;
        try{
            dni=Integer.parseInt(ndni);
        }catch(Exception e){
            return "";
        }
        GestionarClienteImplementacion g=new GestionarClienteImplementacion();
        return g.buscarCliente(dni);
    }
}
