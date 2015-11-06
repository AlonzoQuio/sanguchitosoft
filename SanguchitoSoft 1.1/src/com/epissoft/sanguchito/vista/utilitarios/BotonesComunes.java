package com.epissoft.sanguchito.vista.utilitarios;

import com.epissoft.sanguchito.vista.ManejoSesion;
import com.epissoft.sanguchito.vista.Sanguchito;
import com.epissoft.sanguchito.vista.empleados.IngresoAsistencia;
import com.epissoft.sanguchito.vista.login.Login;
import com.epissoft.sanguchito.vista.ventas.Venta;

public class BotonesComunes {
    public static void ingresoAsistencia(){
        IngresoAsistencia ingAsist=new IngresoAsistencia();
        ingAsist.setVisible(true);
    }
    public static void deslogear(){
        Sanguchito.sanguchito.removeAll();
        Login login=new Login();
        Sanguchito.sanguchito.add(login);
        try{
            login.setMaximum(true);
        }catch(Exception e){            
        }
        login.show();
        ManejoSesion.deslogeo();
    }
    public static void limpiarVenta(){
        Sanguchito.sanguchito.removeAll();
        Venta venta=new Venta();
        Sanguchito.sanguchito.add(venta);
        try{
            venta.setMaximum(true);
        }catch(Exception e){            
        }
        venta.show();
        
    }
}
