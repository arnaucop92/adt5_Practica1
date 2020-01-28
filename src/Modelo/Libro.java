/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author arnau
 */
public class Libro {
    
    private int isbn;
    private String titulo;
    private String autor;
    private int numejemplares;
    private int anyopublicacion;
    private String editorial;
    private int numpag;

    public Libro(int isbn, String titulo, String autor, int numejemplares, int anyopublicacion, String editorial, int numpag) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.numejemplares = numejemplares;
        this.anyopublicacion = anyopublicacion;
        this.editorial = editorial;
        this.numpag = numpag;
    }

    Libro() {
       
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getNumejemplares() {
        return numejemplares;
    }

    public void setNumejemplares(int numejemplares) {
        this.numejemplares = numejemplares;
    }

    public int getAnyopublicacion() {
        return anyopublicacion;
    }

    public void setAnyopublicacion(int anyopublicacion) {
        this.anyopublicacion = anyopublicacion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getNumpag() {
        return numpag;
    }

    public void setNumpag(int numpag) {
        this.numpag = numpag;
    }
    
    
}
