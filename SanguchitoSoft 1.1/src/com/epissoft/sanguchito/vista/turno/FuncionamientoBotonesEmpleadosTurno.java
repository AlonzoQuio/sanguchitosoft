package com.epissoft.sanguchito.vista.turno;

import com.epissoft.sanguchito.persistencia.Empleado;
import com.epissoft.sanguchito.vista.Sanguchito;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JTable;

public class FuncionamientoBotonesEmpleadosTurno extends AbstractAction{
    private JTable tabla;
    private List<Empleado> list;
    public FuncionamientoBotonesEmpleadosTurno(JTable t,List<Empleado> l) {
        tabla=t;
        list=l;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
//        ListaOpcionesEmpleadosTurno opciones=new ListaOpcionesEmpleadosTurno(list.get(tabla.getSelectedRow()));
//        opciones.setLocation(Sanguchito.sanguchito.getWidth()/2-opciones.getWidth()/2,Sanguchito.sanguchito.getHeight()/2-opciones.getHeight()/2);
//        Sanguchito.sanguchito.add(opciones, new Integer( 10 ));
//        opciones.show();
    }
}
