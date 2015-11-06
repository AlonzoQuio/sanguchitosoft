package com.epissoft.sanguchito.vista.unidades;

import com.epissoft.sanguchito.persistencia.UnidadMedida;
import com.epissoft.sanguchito.vista.Sanguchito;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JTable;

public class FuncionamientoBotonesUnidades extends AbstractAction{
    private JTable tabla;
    private List<UnidadMedida> list;
    public FuncionamientoBotonesUnidades(JTable t,List<UnidadMedida> l) {
        tabla=t;
        list=l;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Sanguchito.sanguchito.removeAll();
        ActualizarUnidad opciones=new ActualizarUnidad(list.get(tabla.getSelectedRow()));
        Sanguchito.sanguchito.add(opciones);
        try {
            opciones.setMaximum(true);
        } catch (Exception ex) {
        }
        opciones.show();
    }
}
