/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epissoft.sanguchito.vista.ventas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class FuncionamientoBotonOpciones implements ActionListener {
    Venta venta;
    public FuncionamientoBotonOpciones(Venta v) {
        venta=v;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        venta.mostrarOpciones();
    }
    
}
