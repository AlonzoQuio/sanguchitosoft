package IMPRIMIR;

import com.epissoft.sanguchito.controlador.facturacioncontrolador.FacturacionControlador;
import com.epissoft.sanguchito.persistencia.Extras;
import com.epissoft.sanguchito.persistencia.Producto;
import com.epissoft.sanguchito.vista.ManejoSesion;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import net.sf.jasperreports.engine.JRException;


public class imprimirTicket {

    public static void imprimirTicket(ArrayList<Producto> producto,ArrayList<ArrayList<Extras>> extras,ArrayList<ArrayList<Integer>> cantidades,String precio,String descuento) throws JRException, PrinterException {
        Ticket nn=new Ticket();
        for(int i=0;i<producto.size();i++){
            nn.rellenar(producto.get(i).getProDes(),1,producto.get(i).getProPreCar());
            for(int j=0;j<extras.get(i).size();j++){
                if(extras.get(i).get(j).getExtPrecSolCar()!=0){
                    nn.rellenar("-- Con:"+extras.get(i).get(j).getExtDes(),cantidades.get(i).get(j),extras.get(i).get(j).getExtPrecSolCar());
                 }    
             }
            }
        nn.imprimirticket(ManejoSesion.getUsuario(),descuento,precio,""+FacturacionControlador.facturaAcual());        
        }
}
