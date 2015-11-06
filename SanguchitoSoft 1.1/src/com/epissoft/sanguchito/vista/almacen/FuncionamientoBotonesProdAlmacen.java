package com.epissoft.sanguchito.vista.almacen;

import com.epissoft.sanguchito.persistencia.ProductoAlmacen;
import com.epissoft.sanguchito.vista.Sanguchito;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JTable;

public class FuncionamientoBotonesProdAlmacen extends AbstractAction{
    private JTable tabla;
    private List<ProductoAlmacen> list;
    public FuncionamientoBotonesProdAlmacen(JTable t,List<ProductoAlmacen> l) {
        tabla=t;
        list=l;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Entro a la actiuavasdfa ce acacion");
        ListaOpcionesProductoAlmacen opciones=new ListaOpcionesProductoAlmacen(list.get(tabla.getSelectedRow()));
        opciones.setLocation(Sanguchito.sanguchito.getWidth()/2-opciones.getWidth()/2,Sanguchito.sanguchito.getHeight()/2-opciones.getHeight()/2);
        Sanguchito.sanguchito.add(opciones, new Integer( 10 ));
        opciones.show();
    }
}
