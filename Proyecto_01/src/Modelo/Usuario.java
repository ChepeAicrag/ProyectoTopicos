/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 * 
 * @author García García José Ángel
 */
public class Usuario {
    
    private String id, usuario, password, correo, permiso, curp;
    
    public Usuario(String id,String usuario,String password,String correo,String permiso,String curp){
        this.usuario = usuario;
        this.password = password;
        this.correo = correo;
        this.permiso = permiso;
        this.curp = curp;
    }
    
    public String getUsuario(){
        return usuario;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getCorreo(){
        return correo;
    } 
    
    public String getPermiso(){
        return permiso;
    }
    
    public String getCurp(){
        return curp;
    }
    
    public String getId(){
        return id;
    }
}
