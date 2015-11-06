/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epissoft.sanguchito.controlador.usuariocontrolador;
import com.epissoft.sanguchito.controlador.cargocontrolador.CargoControlador;
import com.epissoft.sanguchito.controlador.empleadocontrolador.EmpleadoControlador;
import com.epissoft.sanguchito.controlador.tipousuariocontrolador.TipoUsuarioControlador;
import com.epissoft.sanguchito.controlador.turnocontrolador.TurnoControlador;
import java.util.List;
import com.epissoft.sanguchito.modelo.usuariomodelo.implementacion.GestionarUsuarioImplementacion;
import com.epissoft.sanguchito.persistencia.Empleado;
import com.epissoft.sanguchito.persistencia.Usuario;
import com.epissoft.sanguchito.controlador.utilitarios.StringEncrypter;
import com.epissoft.sanguchito.modelo.empleadomodelo.implementacion.GestionarEmpleadoImplementacion;
import com.epissoft.sanguchito.persistencia.Tipousuario;
import java.util.ArrayList;
import java.util.Iterator;

public class UsuarioControlador {
    public static GestionarUsuarioImplementacion gui;
    public UsuarioControlador(){
        gui = new GestionarUsuarioImplementacion();
    }
    
    public ArrayList<ArrayList<String>> getUsuarios(String dni, String username){
        ArrayList<ArrayList<String>> usuarios = new ArrayList<ArrayList<String>>();
        GestionarEmpleadoImplementacion gei = new GestionarEmpleadoImplementacion();
        TurnoControlador tc = new TurnoControlador();
        TipoUsuarioControlador tu = new TipoUsuarioControlador();
        if(!username.equals("")){
            Usuario user = gui.buscarUsuario(username);            
            if(user == null){
                return usuarios;
            }
            Empleado emp = gei.buscarEmpleado(user.getEmpDni().getEmpDni());
            
            String nombre = emp.getEmpNom() + " " + emp.getEmpApePat() + " " + emp.getEmpApeMat();            
            String estado = user.getUsuEst()? "Habilitado":"Inhabilitado";            
            String turno = tc.BuscarTurno(emp.getTurCod().getTurCod()).getTurDes();
            String cargo = tu.buscarTipoUsuario("" + user.getTipUsuCod().getTipUsuCod()).getTipUsuNom();
            ArrayList<String> usuario = new ArrayList<String>();
            usuario.add("" + user.getEmpDni().getEmpDni());
            usuario.add(username);
            usuario.add(nombre); 
            usuario.add(turno);            
            usuario.add(cargo);
            usuario.add(estado);
            usuarios.add(usuario);
        }
        else{
            int dni_c = 0;
            try{
                dni_c = Integer.parseInt(dni);
            }catch(Exception e){
                return usuarios;
            }
            List<Usuario> usuarios2 = gui.buscarUsuarioDni(dni_c);
            Iterator<Usuario> iter = usuarios2.iterator();
            Empleado emp = gei.buscarEmpleado(dni_c);
            String turno = tc.BuscarTurno(emp.getTurCod().getTurCod()).getTurDes();
            String nombre = emp.getEmpNom() + " " + emp.getEmpApePat() + " " + emp.getEmpApeMat();            
            CargoControlador cc = new CargoControlador();
            while(iter.hasNext()){
                Usuario user = iter.next();    
                String estado = user.getUsuEst()? "Habilitado":"Deshabilitado";
                String cargo = tu.buscarTipoUsuario("" + user.getTipUsuCod().getTipUsuCod()).getTipUsuNom();
                ArrayList<String> usuario = new ArrayList<String>();
                usuario.add(dni);
                usuario.add(user.getUsuNomUsu());
                usuario.add(nombre); 
                usuario.add(turno);            
                usuario.add(cargo);
                usuario.add(estado);
                usuarios.add(usuario);           
            }            
        }
        return usuarios;
    }
    
    
    public String insertarUsuario(String dni, String user, String tipo , String pass){
        if(gui.buscarUsuario(user) != null){
            return "El nombre de usuario ya esta en uso.";
        }
        StringEncrypter se = new StringEncrypter("");
        TipoUsuarioControlador tuc = new TipoUsuarioControlador();
         
        pass = se.encrypt(pass);
        Tipousuario tu = new Tipousuario();
        tu = tuc.buscarTipoUsuarioNom(tipo);
        
        Usuario u = new Usuario(1, user, pass, true);
        EmpleadoControlador ec = new EmpleadoControlador();
        System.out.println("DNI: " + dni);
        Empleado emp = ec.buscarEmpleado(dni);
        if(emp == null){
            return "El DNI no es valido";
        }
        if(tu == null){
            return "El tipo de usuario no es valido";
        }
        else{
            u.setEmpDni(emp);
            u.setTipUsuCod(tu);
            if(gui.insertarUsuario(u)){
                return "CORRECTO";
            }
            else{
                return "Ocurrio un problema, verifique los datos";
            }
        }                 
    }
    public String actualizarUsuario(String dni, String pass, String cargo){
        System.err.println("DNI: " + dni + "-------");
        Usuario u = gui.buscarUsuario(dni);
        System.out.println("u = " + u);         
        TipoUsuarioControlador tuc = new TipoUsuarioControlador();
        Tipousuario tu = tuc.buscarTipoUsuarioNom(cargo);
        if(tuc == null){
            return "El Tipo de Usuario no es valido";
        }
        else{
            System.err.println("Contraseña: " + pass);
            StringEncrypter se = new StringEncrypter("");
            String newpass = se.encrypt(pass);
            System.err.println("Contraseña nueva : " + newpass);
            u.setUsuPasUsu(newpass);
            u.setTipUsuCod(tu);
            if(gui.actualizarUsuario(u)){
                return "Se guardaron los cambios satisfactoriamente";
            }
            return "Verifique los datos.";
        }        
    }
    public String habilitarUsuario(String nombre){
        
        return gui.habilitarUsuario(nombre)? "El Usuario fue Habilitado" : "Ocurrio un problema, no se pudo habilitarlo";
    }
    public String inhabilitarUsuario(String nombre){
        return gui.inhabilitarUsuario(nombre)? "El Usuario fue Inhabilitado" : "Ocurrio un problema, no se pudo inhabilitarlo";

    }
    public Usuario buscarUsuario(String codigo){
        return gui.buscarUsuario(Integer.parseInt(codigo));
    }
    public static Usuario buscarUsuarioNom(String nombre){
        return gui.buscarUsuario(nombre);
    }
    public boolean verificarPassword(String user, String pass){
        Usuario usu = buscarUsuarioNom(user);
        StringEncrypter se = new StringEncrypter("");
        String password = se.decrypt(usu.getUsuPasUsu());
        System.out.println("Pass Codificada: "+password);
        return pass.equals(password);
    }
    public boolean existeUser(String nombre){
        return buscarUsuarioNom(nombre) != null;
    }
    public int getCarUser(String nombre){
        Usuario user = buscarUsuarioNom(nombre);
        
        if(user == null) 
            return -1;
        if(!user.getUsuEst()){
            return -2;
        }
        return user.getTipUsuCod().getTipUsuCod();
    }
    
    public List<Usuario> mostrarUsuarios(){
        return gui.mostrarUsuarios();
    }

    public String getDniUser(String user) {
        Usuario usu = buscarUsuarioNom(user);
        if(usu == null)
            return "";
        return ""+usu.getEmpDni().getEmpDni();
    }
}
