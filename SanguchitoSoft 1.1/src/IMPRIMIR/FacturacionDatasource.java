/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IMPRIMIR;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author gigo
 */
public class FacturacionDatasource implements JRDataSource{
    private List<Facturacion> listaPedidos = new ArrayList<Facturacion>();
    private int indicePedidoActual = -1;
    
    public Object getFieldValue(JRField jrf) throws JRException
    {
        Object valor = null;

        if ("Descripcion".equals(jrf.getName()))
        {
            valor = listaPedidos.get(indicePedidoActual).getDescripcion();
        }
        else if ("Cantidad".equals(jrf.getName()))
        {
            valor = listaPedidos.get(indicePedidoActual).getCantidad();
        }
        else if ("precio".equals(jrf.getName()))
        {
            valor = listaPedidos.get(indicePedidoActual).getPrecio();
        }
        
        return valor;
    }

    
    public void addFacturacion(Facturacion facturacion)
    {
        this.listaPedidos.add(facturacion);
    }

    
    public boolean next() throws JRException {
        return ++indicePedidoActual < listaPedidos.size();
    }
    
}
