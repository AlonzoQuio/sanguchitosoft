
package com.epissoft.sanguchito.vista.gastos;

import com.epissoft.sanguchito.persistencia.TipoGasto;
import com.epissoft.sanguchito.vista.Sanguchito;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JTable;


public class FuncionamientoBotonesTipoGasto extends AbstractAction {
    private JTable tabla;
    private List<TipoGasto> list;
    public FuncionamientoBotonesTipoGasto(JTable t, List<TipoGasto> l) {
        tabla=t;
        list=l;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ListaOpcionesTipoGastos opciones=new ListaOpcionesTipoGastos(list.get(tabla.getSelectedRow()));
        opciones.setLocation(Sanguchito.sanguchito.getWidth()/2-opciones.getWidth()/2,Sanguchito.sanguchito.getHeight()/2-opciones.getHeight()/2);
        Sanguchito.sanguchito.add(opciones, new Integer( 10 ));
        opciones.show();
    }
    
}
