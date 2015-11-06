
package com.epissoft.sanguchito.vista.extracategoria;

import com.epissoft.sanguchito.persistencia.CategoriaExtra;
import com.epissoft.sanguchito.vista.Sanguchito;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class FuncionamientoBotonesListaCategoriasExtras implements ActionListener {
    CategoriaExtra catExt;
    public FuncionamientoBotonesListaCategoriasExtras(CategoriaExtra get) {
        catExt=get;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ListaOpcionesCategoriaExtras lista=new ListaOpcionesCategoriaExtras(catExt);
        lista.setLocation(Sanguchito.sanguchito.getWidth()/2-lista.getWidth()/2,Sanguchito.sanguchito.getHeight()/2-lista.getHeight()/2);
        Sanguchito.sanguchito.add(lista, new Integer( 10 ));
        lista.show();
    }
    
}
