package com.epissoft.sanguchito.vista.extra;

import com.epissoft.sanguchito.persistencia.CategoriaExtra;
import com.epissoft.sanguchito.persistencia.Extras;
import com.epissoft.sanguchito.vista.Sanguchito;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JTable;

class FuncionamientoBotonesListaExtras extends AbstractAction {
    private JTable tabla;
    private List<Extras> list;
    private CategoriaExtra categoria;
    public FuncionamientoBotonesListaExtras(JTable t, List<Extras> l, CategoriaExtra cat) {
        tabla=t;
        list=l;
        categoria=cat;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ListaOpcionesExtras opciones=new ListaOpcionesExtras(list.get(tabla.getSelectedRow()), categoria);
        opciones.setLocation(Sanguchito.sanguchito.getWidth()/2-opciones.getWidth()/2,Sanguchito.sanguchito.getHeight()/2-opciones.getHeight()/2);
        Sanguchito.sanguchito.add(opciones, new Integer( 10 ));
        opciones.show();
    }
    
}
