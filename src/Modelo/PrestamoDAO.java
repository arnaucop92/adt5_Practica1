/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.SQLException;
import java.sql.Statement;

public class PrestamoDAO {
      
   public void insertarPrestamo(Prestamo prestamo){
        GestionBD singletonBBDD = GestionBD.crearSingeltonBDD();
        
        try {
            Statement st = singletonBBDD.getConnection().createStatement();
            st.executeUpdate("INSERT INTO prestamo VALUES ("+prestamo.getLibro()+","+prestamo.getSocio()+",'"+prestamo.getFprestamo()+"','"+prestamo.getFdevolucion()+"')");
            System.out.println("Prestamo insertado con exito!");
            st.close();
        } catch (SQLException e) {
            System.out.println("Error "+e.getMessage());
        }
    }
    
    public Prestamo obtenerPrestamo(int id){
        return new Prestamo();
    }
    
    public void modificarPrestamo(Prestamo prestamo){
        GestionBD singletonBBDD = GestionBD.crearSingeltonBDD();
        
        try {
            Statement st = singletonBBDD.getConnection().createStatement();
            st.executeUpdate("UPDATE prestamo SET fprestamo='"+prestamo.getFprestamo()+"', fdevolucion='"+prestamo.getFdevolucion()+"' "
                    + "WHERE libro="+prestamo.getLibro()+" AND socio="+prestamo.getSocio()+"");
            System.out.println("Prestamo modificado con exito!");
            st.close();
        } catch (SQLException e) {
            System.out.println("Error "+e.getMessage());
        }
    }
    
    public void eliminarPrestamo(int socio,int libro){
        GestionBD singletonBBDD = GestionBD.crearSingeltonBDD();
        
        try {
            Statement st = singletonBBDD.getConnection().createStatement();
            st.executeUpdate("DELETE from prestamo WHERE socio="+socio+" AND libro="+libro+"");
            
            System.out.println("Prestamo eliminado con exito!");
            st.close();
        } catch (SQLException e) {
            System.out.println("Error"+e.getMessage());
        }
    }
}
