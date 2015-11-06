package com.epissoft.sanguchito.controlador.kardexcontrolador;

import com.epissoft.sanguchito.modelo.kardexmodelo.bean.KardexProducto;
import com.epissoft.sanguchito.modelo.kardexmodelo.implementacion.GestionarKardexImplementacion;
import com.epissoft.sanguchito.persistencia.Kardex;
import java.util.ArrayList;
import java.util.List;

public class KardexControlador {
    public static List<KardexProducto> listaKardex(int codProducto){
        List<Kardex> lista=new GestionarKardexImplementacion().kardexProducto(codProducto);
        List<KardexProducto> l=new ArrayList<>();
        for(Kardex k:lista){
            l.add(new KardexProducto(k));
        }
        return l;
    }
}
