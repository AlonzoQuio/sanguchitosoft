
package com.epissoft.sanguchito.vista.productosofrecidos;

import com.epissoft.sanguchito.persistencia.CategoriaProducto;
import com.epissoft.sanguchito.persistencia.Producto;
import com.epissoft.sanguchito.vista.Sanguchito;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JTable;


public class FuncionamientoBotonesListaProductos extends AbstractAction{
    private JTable tabla;
    private List<Producto> list;
    private CategoriaProducto categoria;
    public FuncionamientoBotonesListaProductos(JTable t,List<Producto> l,CategoriaProducto cat) {
        tabla=t;
        list=l;
        categoria=cat;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ListaOpcionesProductosOfrecidos opciones=new ListaOpcionesProductosOfrecidos(list.get(tabla.getSelectedRow()), categoria);
        opciones.setLocation(Sanguchito.sanguchito.getWidth()/2-opciones.getWidth()/2,Sanguchito.sanguchito.getHeight()/2-opciones.getHeight()/2);
        Sanguchito.sanguchito.add(opciones, new Integer( 10 ));
        opciones.show();
    }
}
