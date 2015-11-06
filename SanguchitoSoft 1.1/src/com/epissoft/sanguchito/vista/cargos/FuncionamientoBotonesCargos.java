package com.epissoft.sanguchito.vista.cargos;

import com.epissoft.sanguchito.persistencia.Cargo;
import com.epissoft.sanguchito.vista.Sanguchito;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JTable;

public class FuncionamientoBotonesCargos extends AbstractAction{
    private JTable tabla;
    private List<Cargo> list;
    public FuncionamientoBotonesCargos(JTable t,List<Cargo> l) {
        tabla=t;
        list=l;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ListaOpcionesCargos opciones=new ListaOpcionesCargos(list.get(tabla.getSelectedRow()));
        opciones.setLocation(Sanguchito.sanguchito.getWidth()/2-opciones.getWidth()/2,Sanguchito.sanguchito.getHeight()/2-opciones.getHeight()/2);
        Sanguchito.sanguchito.add(opciones, new Integer( 10 ));
        opciones.show();
    }
}
