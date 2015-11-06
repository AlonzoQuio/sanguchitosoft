

package com.epissoft.sanguchito.controlador.proveedorcontrolador;

import com.epissoft.sanguchito.controlador.utilitarios.ValidacionGeneral;
import java.util.List;
import com.epissoft.sanguchito.modelo.proveedormodelo.implementacion.GestionarProveedorImplementacion;
import com.epissoft.sanguchito.persistencia.Proveedor;


public class ProveedorControlador {
    public static GestionarProveedorImplementacion gpi ;
    public ProveedorControlador(){
        gpi = new GestionarProveedorImplementacion();
    }
    public String insertarProveedor(String ruc, String nombre, String ape_pat, String ape_mat, String mail, String tlfn, String raz_social){
        if(!ValidacionGeneral.tamValido(raz_social)){
            return "El campo Razon social no puede estar vacio";
        }
        if(ruc.length()!=11){
            return "Ingrese un RUC Valido";
        }
        for(int i=0;i<ruc.length();i++){
            if(ruc.charAt(i)<='0'&&ruc.charAt(i)>='1'){
                return "El RUC solo puede contener numeros";
            }
        }
        int numTel=0;
        if(tlfn.length()>=6){
            try{
                numTel=Integer.parseInt(tlfn);
            }catch(NumberFormatException e){
                return "El numero de telefono es incorrecto";
            }
        }else{
            if(tlfn.length()>0){
                return "Ingrese un numero de telefono correcto";
            }
        }
        Proveedor p = new Proveedor(ruc,nombre, ape_pat, ape_mat,raz_social,true);
        p.setProvMail(mail);
        if(numTel!=0){
            p.setProvTel(numTel);
        }
        if(gpi.insertarProveedor(p)){
            return "CORRECTO";
        }else{
            return "El RUC ya esta siendo utilizado";
        }
    }
    public String actualizarProveedor(String ruc, String nombre, String ape_pat, String ape_mat, String tlfn, String mail, String raz_social){
        
        if(!ValidacionGeneral.tamValido(raz_social)){
            return "El campo Razon social no puede estar vacio";
        }
        int numTel=0;
        if(tlfn.length()>=6){
            try{
                numTel=Integer.parseInt(tlfn);
            }catch(NumberFormatException e){
                return "El numero de telefono es incorrecto";
            }
        }else{
            if(tlfn.length()>0){
                return "Ingrese un numero de telefono correcto";
            }
        }
        Proveedor p = new Proveedor(ruc,nombre, ape_pat, ape_mat,raz_social,true);
        p.setProvMail(mail);
        if(numTel!=0){
            p.setProvTel(numTel);
        }
        if(gpi.actualizarProveedor(p)){
            return "CORRECTO";
        }else{
            return "Error al actualizar el proveedor, revise los datos, por favor";
        }
    }
    public boolean inhabilitarProveedor(String ruc){
        
        gpi.inhabilitarProveedor(ruc);
        return true;      
    }
    public boolean habilitarProveedor(String ruc){
        
        gpi.habilitarProveedor(ruc);
        return true;
    }
    public Proveedor buscarProveedor(String ruc){
        
        return gpi.buscarProveedor(ruc);
    }
    public List<Proveedor> listaProveedors(){
        return gpi.listaProveedors();
    }   
}
