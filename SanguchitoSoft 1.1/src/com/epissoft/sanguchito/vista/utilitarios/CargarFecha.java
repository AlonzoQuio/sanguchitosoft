package com.epissoft.sanguchito.vista.utilitarios;

import javax.swing.JTextField;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CargarFecha {

    public static void cargarFechaActual(JTextField jHora) {
        SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
        Date fechaDate = new Date();
        String fecha = formateador.format(fechaDate);
        jHora.setText(fecha);
    }
}
