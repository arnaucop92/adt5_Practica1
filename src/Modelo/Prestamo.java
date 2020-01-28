/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author Guillem
 */
public class Prestamo {
    
    private int libro;
    private int socio;
    private String fprestamo;
    private String fdevolucion;

    Prestamo() {
      
    }

    public Prestamo(int libro, int socio, String fprestamo, String fdevolucion) {
        this.libro = libro;
        this.socio = socio;
        this.fprestamo = fprestamo;
        this.fdevolucion = fdevolucion;
    }
    
    public int getLibro() {
        return libro;
    }

    public void setLibro(int libro) {
        this.libro = libro;
    }

    public int getSocio() {
        return socio;
    }

    public void setSocio(int socio) {
        this.socio = socio;
    }

    public String getFprestamo() {
        return fprestamo;
    }

    public void setFprestamo(String fprestamo) {
        this.fprestamo = fprestamo;
    }

    public String getFdevolucion() {
        return fdevolucion;
    }

    public void setFdevolucion(String fdevolucion) {
        this.fdevolucion = fdevolucion;
    }

    
}
