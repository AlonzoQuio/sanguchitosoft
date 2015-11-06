
package com.epissoft.sanguchito.vista.proveedores;

import com.epissoft.sanguchito.persistencia.Proveedor;
import com.epissoft.sanguchito.vista.Sanguchito;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JTable;


public class FuncionamientoBotonesProveedor extends AbstractAction{
    private JTable tabla;
    private List<Proveedor> list;
    public FuncionamientoBotonesProveedor(JTable t,List<Proveedor> l) {
        tabla=t;
        list=l;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ListaOpcionesProveedor opciones=new ListaOpcionesProveedor(list.get(tabla.getSelectedRow()));
        opciones.setLocation(Sanguchito.sanguchito.getWidth()/2-opciones.getWidth()/2,Sanguchito.sanguchito.getHeight()/2-opciones.getHeight()/2);
        Sanguchito.sanguchito.add(opciones, new Integer( 10 ));
        opciones.show();
    }
}