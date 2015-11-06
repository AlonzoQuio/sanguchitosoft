
package com.epissoft.sanguchito.vista.almacencategoria;

import com.epissoft.sanguchito.persistencia.CategoriaProductoAlmacen;
import com.epissoft.sanguchito.vista.Sanguchito;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FuncionamientoBotonesListaCategoriasAlmacen implements ActionListener{
    private CategoriaProductoAlmacen cat;
    public FuncionamientoBotonesListaCategoriasAlmacen(CategoriaProductoAlmacen c) {
        cat=c;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        ListaOpcionesCategoriaAlmacen lista=new ListaOpcionesCategoriaAlmacen(cat);
        lista.setLocation(Sanguchito.sanguchito.getWidth()/2-lista.getWidth()/2,Sanguchito.sanguchito.getHeight()/2-lista.getHeight()/2);
        Sanguchito.sanguchito.add(lista, new Integer( 10 ));
        lista.show();
    }
    
}
