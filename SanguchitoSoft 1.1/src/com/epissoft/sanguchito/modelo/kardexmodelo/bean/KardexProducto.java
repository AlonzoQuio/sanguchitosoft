
package com.epissoft.sanguchito.modelo.kardexmodelo.bean;

import com.epissoft.sanguchito.persistencia.Empleado;
import com.epissoft.sanguchito.persistencia.Kardex;

public class KardexProducto {
    private int dia;
    private int mes;
    private int anio;
    private int cantidad;
    private String tipo;
    private String usuario;
    public KardexProducto(Kardex k){
        dia=k.getKarFecDia();
        mes=k.getKarFecMes();
        anio=k.getKarFecAnio();
        
        if(k.getIngresoKardex()!=null){
            tipo="Ingreso";
            cantidad=k.getIngresoKardex().getCantProdAlm();
            Empleado e=k.getIngresoKardex().getGasCod().getEmpDni();
            usuario=e.getEmpNom()+" "+e.getEmpApePat()+" "+e.getEmpApeMat();
        }else{
            tipo="Salida";
            cantidad=k.getEgresoKardex().getCantProdAlm();
            usuario="";
        }
    }
    public String getFecha(){
        return ""+dia+"/"+mes+"/"+anio;
    }
    public String getCantidad(){
        return ""+cantidad;
    }
    public String getTipo(){
        return ""+tipo;
    }
    public String getUsuario(){
        return usuario;
    }
}
