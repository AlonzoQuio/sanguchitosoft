package com.epissoft.sanguchito.modelo.utilitarios;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SManager {
    private static EntityManagerFactory eManager=null;
    private SManager(){
    }
    public static EntityManagerFactory inicializar(){
        if(eManager==null) {
            eManager=Persistence.createEntityManagerFactory("SanguchitoPU", System.getProperties());
        }
        return eManager;
    }
    
}
