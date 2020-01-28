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
public class SocioDAO {
    
    public void insertarSocio(Socio socio){
        GestionBD singeltonBDD = GestionBD.crearSingeltonBDD();
        try {
            Statement st = singeltonBDD.getConnection().createStatement();
            st.execute("INSERT INTO socio VALUES ('"+socio.getNumsocio()+"','"+socio.getNombre()+"','"+socio.getApellidos()+"','"+socio.getTelefono()+"','"+socio.getEdad()+"', STR_TO_DATE('"+socio.getFalta().toString()+"','%Y-%m-%d'))");
            st.close();
        } catch (Exception e) {
            System.out.println("Error "+e.getMessage());
        }
    }
    public Socio obtenerSocio(int id){
        
        return new Socio();
    }
    public void modificarSocio(Socio socio){
        
         GestionBD singeltonBDD = GestionBD.crearSingeltonBDD();
        try {
            Statement st = singeltonBDD.getConnection().createStatement();
            st.execute("UPDATE socio SET "
                    + "numsocio = "+ socio.getNumsocio() + ", nombre = '" + socio.getNombre()+ "',"
                    + " apellidos = '" + socio.getApellidos()+ "',"
                    + " telefono = '" + socio.getTelefono()+ "',"
                    + " edad = " + socio.getEdad()+ ","
                    + " falta = STR_TO_DATE('"+socio.getFalta()+"','%Y-%m-%d')"
                    + " WHERE numsocio = " + socio.getNumsocio());
            st.close();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }
    public void eliminarSocio(Socio socio){
        
         GestionBD singeltonBDD = GestionBD.crearSingeltonBDD();
        try {
            Statement st = singeltonBDD.getConnection().createStatement();
            st.execute("DELETE FROM socio WHERE numsocio = '" + socio.getNumsocio()+ "'");
            st.close();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }
    
    public ArrayList<Socio> leerSocio(String consulta) {

        GestionBD singeltonBDD = GestionBD.crearSingeltonBDD();
        try {
            PreparedStatement sentencia = null;
            if(consulta.equals("")){
                String sq1 = "SELECT * FROM socio";
                sentencia = singeltonBDD.getConnection().prepareStatement(sq1);
            }else{
                String sq1 = "SELECT * FROM socio WHERE numsocio LIKE ? OR apellidos LIKE ?";
                sentencia = singeltonBDD.getConnection().prepareStatement(sq1);
                sentencia.setString(1, consulta);
                sentencia.setString(2, consulta);
            }

            ResultSet filas = sentencia.executeQuery();
            ArrayList<Socio> socios = new ArrayList<Socio>();

            while (filas.next()) {
                Socio nuevo = new Socio(filas.getInt("numsocio"),
                        filas.getString("nombre"),
                        filas.getString("apellidos"),
                        filas.getString("telefono"),
                        filas.getInt("edad"),
                        filas.getString("falta"));
                socios.add(nuevo);
                
            }
            sentencia.close();
            
            return socios;
            
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return null;
    }
    
    public void buscarSocio(String palabraClave, JTable tabla ){
         GestionBD singletonBBDD = GestionBD.crearSingeltonBDD();
         ResultSet rs;
         boolean encontrado;
         String[] nombreColumnas = null;
         ArrayList<String[]> resultado = new ArrayList();
        try {
            Statement st = singletonBBDD.getConnection().createStatement();
            rs = st.executeQuery("SELECT * FROM socio WHERE  nombre LIKE '%"+palabraClave+"%' OR apellidos LIKE '%"+palabraClave+"%'");
            
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
            
            System.out.println("Socio encontrado con exito!");
            st.close();
        } catch (SQLException e) {
            System.out.println("Error"+e.getMessage());
        }
        
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        
        model.setRowCount(0);
        
        
        for (int i = 0; i < resultado.size(); i++) {
            String[] fila = resultado.get(i);
            
            model.addRow(new Object[]{fila[0],fila[1],fila[2],fila[3],fila[4],fila[5]});
        }
        
        
    }
}
