/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epissoft.sanguchito.modelo.facturaciondetallemodelo.implementacion;

import com.epissoft.sanguchito.modelo.facturaciondetallemodelo.GestionarFacturacionDetalle;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.Facturacion;
import com.epissoft.sanguchito.persistencia.FacturacionDetalle;
import com.epissoft.sanguchito.persistencia.jpa.FacturacionDetalleJpaController;
import java.util.List;

/**
 *
 * @author HP
 */
public class GestionFacturacionDetalleImplementacion implements GestionarFacturacionDetalle {
    @Override
    public boolean insertarFacturacionDetalle(FacturacionDetalle fd){
        FacturacionDetalleJpaController cargoController = new FacturacionDetalleJpaController(SManager.inicializar());
        try{
            cargoController.create(fd);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
