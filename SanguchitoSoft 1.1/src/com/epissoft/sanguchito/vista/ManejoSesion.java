package com.epissoft.sanguchito.vista;

public class ManejoSesion {
    private static boolean sesionActiva=false;
    private static String usuario="NN";
    private static String cargo="NN";
    private static String ruta="";
    private static String dni="";
    private static String usua="";
    private ManejoSesion(){
    }
    public static void crearSesion(String us,String d,String uss,String carg,String r){
        sesionActiva=true;
        usuario=us;
        cargo=carg;
        ruta=r;
        dni=d;
        usua=uss;
    }
    public static String getUsuario(){
        return usuario;
    }
    public static String getCargo(){
        return cargo;
    }
    public static void deslogeo(){
        sesionActiva=false;
    }
    public static boolean logeado(){
        return sesionActiva;
    }
    public static String getRuta(){
        return ruta;
    }
    public static String getDNI(){
        return dni;
    }
    public static String getUser(){
        return usua;
    }
}
