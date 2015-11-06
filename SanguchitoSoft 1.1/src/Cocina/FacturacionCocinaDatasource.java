/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Cocina;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author JMJ
 */
public class FacturacionCocinaDatasource implements JRDataSource{
    private List<FacturacionCocina> listaPedidosCocina = new ArrayList<FacturacionCocina>();
    private int indicePedidoActualCocina = -1;
    
    public Object getFieldValue(JRField jrf) throws JRException {
         Object valor = null;

        if ("Descripcion".equals(jrf.getName()))
        {
            valor = listaPedidosCocina.get(indicePedidoActualCocina).getDescripcion();
        }
        else if ("Cantidad".equals(jrf.getName()))
        {
            valor = listaPedidosCocina.get(indicePedidoActualCocina).getCantidad();
        }
        else if("Modalidad".equals(jrf.getName()))
        {
            valor=listaPedidosCocina.get(indicePedidoActualCocina).getModalidad();
        }
        
        return valor;
    }
    
    public void addFacturacionCocina(FacturacionCocina facturacionCocina)
    {
        this.listaPedidosCocina.add(facturacionCocina);
    }
   
    public boolean next() throws JRException {
        return ++indicePedidoActualCocina < listaPedidosCocina.size();
    }

    
    
    
}
