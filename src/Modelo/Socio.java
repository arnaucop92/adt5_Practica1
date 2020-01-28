/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author arnau
 */
public class Socio {
    
    private int numsocio;
    private String nombre;
    private String apellidos;
    private String telefono;
    private int edad;
    private String falta;

    public Socio(int numsocio, String nombre, String apellidos, String telefono, int edad, String falta) {
        this.numsocio = numsocio;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.edad = edad;
        this.falta = falta;
    }
    Socio() {
      
    }

    public int getNumsocio() {
        return numsocio;
    }

    public void setNumsocio(int numsocio) {
        this.numsocio = numsocio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getFalta() {
        return falta;
    }

    public void setFalta(String falta) {
        this.falta = falta;
    }

    
}
