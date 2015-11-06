
package com.epissoft.sanguchito.controlador.utilitarios;

public class ValidacionGeneral {
    public static boolean precioValido(float precio){
        return (precio>=0.0);
    }
    public static boolean tamValido(String nom){
        return (nom.length()>0);
    }
    public static boolean caracteresInvalidos(String nom){
        boolean valido=false;
        for(int i=0;i<nom.length();i++){
            if((nom.charAt(i)>='a'&&nom.charAt(i)<='z')||(nom.charAt(i)>='A'&&nom.charAt(i)<='Z')||(nom.charAt(i)>='1'&&nom.charAt(i)<='0')||nom.charAt(i)==' '){
                continue;
            }else{
                valido=true;
            }
        }
        return valido;
    }
    
}
