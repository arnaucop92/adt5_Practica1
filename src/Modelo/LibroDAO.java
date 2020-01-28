/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author arnau
 */
public class LibroDAO {

    public void insertarLibro(Libro libro) {
        GestionBD singeltonBDD = GestionBD.crearSingeltonBDD();
        try {
            Statement st = singeltonBDD.getConnection().createStatement();
            st.execute("INSERT INTO libro VALUES ('" + libro.getIsbn() + "','" + libro.getTitulo() + "','" + libro.getAutor() + "','" + libro.getNumejemplares() + "','" + libro.getAnyopublicacion() + "','" + libro.getEditorial() + "','" + libro.getNumpag() + "')");
            st.close();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public Libro obtenerLibro(int id) {
        return new Libro();
    }

    public void modificarLibro(Libro libro) {
        GestionBD singeltonBDD = GestionBD.crearSingeltonBDD();
        try {
            Statement st = singeltonBDD.getConnection().createStatement();
            st.execute("UPDATE libro SET titulo = '"+ libro.getTitulo() 
                    +"', autor = '"+ libro.getAutor() +"', numejemplares = "+libro.getNumejemplares()
                    +",anyopublicacion = "+libro.getAnyopublicacion()+",editorial='"+ libro.getEditorial()
                    + "', numpag =" + libro.getNumpag() +" WHERE isbn =" + libro.getIsbn());
            st.close();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

    }

    public void eliminarLibro(Libro libro) {
        GestionBD singeltonBDD = GestionBD.crearSingeltonBDD();
        try {
            Statement st = singeltonBDD.getConnection().createStatement();
            st.execute("DELETE FROM libro WHERE isbn = '" + libro.getIsbn() + "'");
            st.close();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

    }

     
    public ArrayList<Libro> leerLibro(String consulta) {

        GestionBD singeltonBDD = GestionBD.crearSingeltonBDD();
        try {
            PreparedStatement sentencia = null;
            if(consulta.equals("")){
                String sq1 = "SELECT * FROM libro";
                sentencia = singeltonBDD.getConnection().prepareStatement(sq1);
            }else{
                String sq1 = "SELECT * FROM libro WHERE titulo LIKE ? OR autor LIKE ?";
                sentencia = singeltonBDD.getConnection().prepareStatement(sq1);
                sentencia.setString(1, consulta);
                sentencia.setString(2, consulta);
            }

            ResultSet filas = sentencia.executeQuery();
            ArrayList<Libro> libros = new ArrayList<Libro>();

            while (filas.next()) {
                Libro nuevo = new Libro(filas.getInt("isbn"),
                        filas.getString("titulo"),
                        filas.getString("autor"),
                        filas.getInt("numejemplares"),
                        filas.getInt("anyopublicacion"),
                        filas.getString("editorial"),
                        filas.getInt("numpag"));
                libros.add(nuevo);
                
            }
            sentencia.close();
            
            return libros;
            
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return null;
    }
    
    public void buscarLibro(String palabraClave, JTable tabla ){
         GestionBD singletonBBDD = GestionBD.crearSingeltonBDD();
         ResultSet rs;
         boolean encontrado;
         String[] nombreColumnas = null;
         ArrayList<String[]> resultado = new ArrayList();
        try {
            Statement st = singletonBBDD.getConnection().createStatement();
            rs = st.executeQuery("SELECT * FROM libro WHERE  titulo LIKE '%"+palabraClave+"%' OR autor LIKE '%"+palabraClave+"%'");
            
            int columnas = rs.getMetaData().getColumnCount();
            System.out.println("Columnas: "+columnas);
            nombreColumnas = new String[columnas];
            
            for (int i = 0; i < nombreColumnas.length; i++) {
                nombreColumnas[i] = rs.getMetaData().getColumnName(i+1);
            }
            
            while (rs.next()) {
                String[] row = new String[columnas];
                for (int i = 0; i < row.length; i++) {
                    row[i] = rs.getString(i + 1);
                }
                resultado.add(row);
            }
            
            System.out.println("Libro encontrado con exito!");
            st.close();
        } catch (SQLException e) {
            System.out.println("Error"+e.getMessage());
        }
        
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        
        model.setRowCount(0);
        
        
        for (int i = 0; i < resultado.size(); i++) {
            String[] fila = resultado.get(i);
            
            model.addRow(new Object[]{fila[0],fila[1],fila[2],fila[3],fila[4],fila[5],fila[6]});
        }
        
        
    }
   
}
