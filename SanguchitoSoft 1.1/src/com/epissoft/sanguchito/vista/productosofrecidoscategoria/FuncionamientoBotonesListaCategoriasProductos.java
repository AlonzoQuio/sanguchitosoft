
package com.epissoft.sanguchito.vista.productosofrecidoscategoria;

import com.epissoft.sanguchito.persistencia.CategoriaProducto;
import com.epissoft.sanguchito.vista.Sanguchito;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FuncionamientoBotonesListaCategoriasProductos implements ActionListener{
    private CategoriaProducto cat;
    public FuncionamientoBotonesListaCategoriasProductos(CategoriaProducto c) {
        cat=c;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        ListaOpcionesCategoriaProductosOfrecidos lista=new ListaOpcionesCategoriaProductosOfrecidos(cat);
        lista.setLocation(Sanguchito.sanguchito.getWidth()/2-lista.getWidth()/2,Sanguchito.sanguchito.getHeight()/2-lista.getHeight()/2);
        Sanguchito.sanguchito.add(lista, new Integer( 10 ));
        lista.show();
    }
    
}
