/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epissoft.sanguchito.modelo.empleadomodelo.validacion;

import com.epissoft.sanguchito.modelo.empleadomodelo.implementacion.GestionarEmpleadoImplementacion;

/**
 *
 * @author HP
 */
public class EmpleadoValidacion {
    public static boolean validoDNI(String dni){
        if(!esPalabraNumeros(dni)){
            System.err.println("\n" + dni + "----");
            return false;
        }
        GestionarEmpleadoImplementacion gei = new GestionarEmpleadoImplementacion();
        return gei.buscarEmpleado(Integer.parseInt(dni)) == null;
    }
    public static boolean existeDNI(String dni){
        if(!esPalabraNumeros(dni)){
            System.err.println("\n" + dni + "----");
            return true;
        }
        GestionarEmpleadoImplementacion gei = new GestionarEmpleadoImplementacion();
        return gei.buscarEmpleado(Integer.parseInt(dni)) != null;
    }
    
    public boolean correctosNombre(String nombre, String ape_pat, String ape_mat){
        return esPalabraCaracteres(nombre) && esPalabraCaracteres(ape_pat) && esPalabraCaracteres(ape_mat);
    }
    
    public boolean correctosTelefonos(String t1, String t2){
        
        return esPalabraNumeros(t2) && esPalabraNumeros(t1);
    }
    
    public boolean correctoSueldo(String sueldo){
        float sue;
        try{
            sue = Float.parseFloat(sueldo);
            return true;
        }catch(Exception e){
            return false;
        }        
    }
    
    public static boolean esPalabraCaracteres(String palabra){ 
        for(int i = 0; i < palabra.length(); i++){ 
            if(!((palabra.charAt(i) > 64 && palabra.charAt(i) < 91) || 
                (palabra.charAt(i) > 96 && palabra.charAt(i) < 123) || palabra.charAt(i) == ' ')){
                System.out.println(palabra);
                return false;
            } 
                 
        } 
        return true; 
    }
    public static boolean esPalabraNumeros(String num){
        if(num.length() == 0) return true;
        if(num.length() < 6) 
            return false;
        
        for(int i = 0; i < num.length(); i++){ 
            if(!(num.charAt(i) > 47 && num.charAt(i) < 58)) 
                return false; 
        } 
        return true; 
    } 
}
