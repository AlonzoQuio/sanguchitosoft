package com.epissoft.sanguchito.vista.ventas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FuncionamientoBotones implements ActionListener{
    private int seleccionado;
    private int tipoBoton;
    private Venta venta;
    public FuncionamientoBotones(int posicion,Venta v,int tipoB){
        seleccionado=posicion;
        venta=v;
        tipoBoton=tipoB;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(tipoBoton){
            case 0:venta.actualizarSeccion(Venta.SECCION_PEDIDO_NUEVO);
                break;
            case 1: venta.actualizarSeleccionado(seleccionado);
                    venta.actualizarSeccion(Venta.SECCION_PEDIDO);
                break;
            case 2:venta.actualizarSeleccionado(seleccionado);
                    venta.actualizarSeccion(Venta.SECCION_LISTA_CATEGORIAS);
                break;
            case 3:venta.actualizarSeleccionado(seleccionado);
                    venta.actualizarSeccion(Venta.SECCION_BOTONES);
                break;
        }
        venta.resolverCambios();
    }
}



