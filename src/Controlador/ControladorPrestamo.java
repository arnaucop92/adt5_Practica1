/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.GestionBD;
import Modelo.Prestamo;
import Modelo.PrestamoDAO;
import Vista.JPPrestamo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author arnau
 */
public class ControladorPrestamo {
    
    private PrestamoDAO prestamoDAO; 
    private JPPrestamo vistaPrestamo;
    
     public ControladorPrestamo( JPPrestamo vistaLibro) {
        this.prestamoDAO = new PrestamoDAO();
        this.vistaPrestamo = vistaLibro;
    }

    public ControladorPrestamo() {
    }

    public PrestamoDAO getPrestamoDAO() {
        return prestamoDAO;
    }

    public void setPrestamoDAO(PrestamoDAO prestamoDAO) {
        this.prestamoDAO = prestamoDAO;
    }

    public JPPrestamo getVistaPrestamo() {
        return vistaPrestamo;
    }

    public void setVistaPrestamo(JPPrestamo vistaPrestamo) {
        this.vistaPrestamo = vistaPrestamo;
    }
    
     public void anyadirPrestamo(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        int libro = Integer.parseInt(vistaPrestamo.getTfIsbn().getText());
        int socio = Integer.parseInt(vistaPrestamo.getTfNumSocio().getText());
        String fechaPrest = format.format(vistaPrestamo.getDataPrest().getDate());
        String fechaDev = format.format(vistaPrestamo.getDataDevol().getDate());
        
        Prestamo prest = new Prestamo(libro,socio,fechaPrest,fechaDev);
        
        prestamoDAO.insertarPrestamo(prest);
        
    }
    
    public void modificarPrestamo(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        int libro = Integer.parseInt(vistaPrestamo.getTfIsbn().getText());
        int socio = Integer.parseInt(vistaPrestamo.getTfNumSocio().getText());
        String fechaPrest = format.format(vistaPrestamo.getDataPrest().getDate());
        String fechaDev = format.format(vistaPrestamo.getDataDevol().getDate());
        
        Prestamo prest = new Prestamo(libro,socio,fechaPrest,fechaDev);
        
        prestamoDAO.modificarPrestamo(prest);
    }
    
    public void eliminarPrestamo(){
        int numsocio = Integer.parseInt(vistaPrestamo.getTfNumSocio().getText());
        int isbn = Integer.parseInt(vistaPrestamo.getTfIsbn().getText());
        prestamoDAO.eliminarPrestamo(numsocio,isbn);
    }
    
    public void buscarPrestamo(String palabraClave, JTable tabla){
        
        GestionBD singletonBBDD = GestionBD.crearSingeltonBDD();
         ResultSet rs;
         boolean encontrado;
         String[] nombreColumnas = null;
         ArrayList<String[]> resultado = new ArrayList();
        try {
            Statement st = singletonBBDD.getConnection().createStatement();
            rs = st.executeQuery("SELECT * FROM prestamo WHERE  fprestamo='"+palabraClave+"'");
            
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
            
            System.out.println("Prestamo encontrado con exito!");
            st.close();
        } catch (SQLException e) {
            System.out.println("Error"+e.getMessage());
        }
        
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        
        model.setRowCount(0);
        
        
        for (int i = 0; i < resultado.size(); i++) {
            String[] fila = resultado.get(i);
            
            model.addRow(new Object[]{fila[0],fila[1],fila[2],fila[3]});
        }
        
    }
    public void limpiarPrestamo(){
        
        vistaPrestamo.getTfApellidosSocio().setText("");
        vistaPrestamo.getTfAutorLibro().setText("");
        vistaPrestamo.getTfIsbn().setText("");
        vistaPrestamo.getTfNombreSocio().setText("");
        vistaPrestamo.getTfNumSocio().setText("");
        vistaPrestamo.getTfTituloLibro().setText("");
        vistaPrestamo.getDataDevol().setCalendar(null);
        vistaPrestamo.getDataPrest().setCalendar(null);
    }
}
