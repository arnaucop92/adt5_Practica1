/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Libro;
import Modelo.LibroDAO;
import Modelo.SocioDAO;
import Vista.JDLibros;
import Vista.JPLibro;
import Vista.JPSocio;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author arnau
 */
public class ControladorLibro {
    
    private LibroDAO modeloLibro;
    private JPLibro vistaLibro;
    private JDLibros dialogLibros;

    public ControladorLibro(JPLibro vistaLibro) {
        this.modeloLibro = new LibroDAO();
        this.vistaLibro = vistaLibro;
    }
     public ControladorLibro( JDLibros dialogLibros) {
        this.modeloLibro = new LibroDAO();
        this.dialogLibros = dialogLibros;
    }

    public ControladorLibro(SocioDAO socioDAO, JPSocio aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public LibroDAO getModeloLibro() {
        return modeloLibro;
    }

    public void setModeloLibro(LibroDAO modeloLibro) {
        this.modeloLibro = modeloLibro;
    }

    public JPLibro getVistaLibro() {
        return vistaLibro;
    }

    public void setVistaLibro(JPLibro vistaLibro) {
        this.vistaLibro = vistaLibro;
    }

    public ControladorLibro() {
    }
    
    public void anyadirLibro(){
        
        System.out.println(vistaLibro.getTfIsbn().getText()+ vistaLibro.getTfTitulo().getText()+vistaLibro.getTfAutor().getText()+ (int)vistaLibro.getSpEjemplares().getValue()+ (int)vistaLibro.getSpAno().getValue()+ vistaLibro.getCbEditorial().getSelectedItem().toString()+ (int)vistaLibro.getSpNumPags().getValue());
        Libro nuevoLibro = new Libro(Integer.parseInt(vistaLibro.getTfIsbn().getText()), vistaLibro.getTfTitulo().getText(), vistaLibro.getTfAutor().getText(), (int)vistaLibro.getSpEjemplares().getValue(), (int)vistaLibro.getSpAno().getValue(), vistaLibro.getCbEditorial().getSelectedItem().toString(), (int)vistaLibro.getSpNumPags().getValue());
        
        modeloLibro.insertarLibro(nuevoLibro);
        
    }
    
    public void modificarLibro(){
        Libro nuevoLibro = new Libro(Integer.parseInt(vistaLibro.getTfIsbn().getText()), vistaLibro.getTfTitulo().getText(), vistaLibro.getTfAutor().getText(), (int)vistaLibro.getSpEjemplares().getValue(), (int)vistaLibro.getSpAno().getValue(), vistaLibro.getCbEditorial().getSelectedItem().toString(), (int)vistaLibro.getSpNumPags().getValue());
        modeloLibro.modificarLibro(nuevoLibro);
    }
    
    public void eliminarLibro(){
        Libro nuevoLibro = new Libro(Integer.parseInt(vistaLibro.getTfIsbn().getText()), vistaLibro.getTfTitulo().getText(), vistaLibro.getTfAutor().getText(), (int)vistaLibro.getSpEjemplares().getValue(), (int)vistaLibro.getSpAno().getValue(), vistaLibro.getCbEditorial().getSelectedItem().toString(), (int)vistaLibro.getSpNumPags().getValue());
        modeloLibro.eliminarLibro(nuevoLibro);
    }
    
    public void busquedaLibros(){
       DefaultTableModel dtm = (DefaultTableModel) vistaLibro.getTablaLibros().getModel();
       dtm.getDataVector().clear();
       
     ArrayList<Libro> libros=  modeloLibro.leerLibro(vistaLibro.getBusquedaLibro().getText());
        for (int i = 0; i < libros.size(); i++) {
            Libro libro = libros.get(i);
           dtm.addRow(new Object[]{libro.getIsbn(),
           libro.getTitulo(),
           libro.getAutor(),
           libro.getNumejemplares(),
           libro.getAnyopublicacion(),
           libro.getEditorial(),
           libro.getNumpag()});
        }
    }
    public void libroSeleccionado(DefaultTableModel tm){
    
        vistaLibro.getTfIsbn().setText(String.valueOf(tm.getValueAt(vistaLibro.getTablaLibros().getSelectedRow(), 0)));
        vistaLibro.getTfTitulo().setText(String.valueOf(tm.getValueAt(vistaLibro.getTablaLibros().getSelectedRow(), 1)));
        vistaLibro.getTfAutor().setText(String.valueOf(tm.getValueAt(vistaLibro.getTablaLibros().getSelectedRow(), 2)));
        vistaLibro.getCbEditorial().setSelectedItem(String.valueOf(tm.getValueAt(vistaLibro.getTablaLibros().getSelectedRow(), 5)));
        vistaLibro.getSpAno().setValue(Integer.valueOf(String.valueOf(tm.getValueAt(vistaLibro.getTablaLibros().getSelectedRow(), 4))));
        vistaLibro.getSpEjemplares().setValue(Integer.valueOf(String.valueOf(tm.getValueAt(vistaLibro.getTablaLibros().getSelectedRow(), 3))));
        vistaLibro.getSpNumPags().setValue(Integer.valueOf(String.valueOf(tm.getValueAt(vistaLibro.getTablaLibros().getSelectedRow(), 6))));
    }
    public void limpiarLibro(){
        
        vistaLibro.getTfIsbn().setText("");
        vistaLibro.getTfTitulo().setText("");
        vistaLibro.getTfAutor().setText("");
        vistaLibro.getCbEditorial().setSelectedItem("");
        vistaLibro.getSpAno().setValue(0);
        vistaLibro.getSpEjemplares().setValue(0);
        vistaLibro.getSpNumPags().setValue(0);
        
        
    }
    public void buscarLibroDialog(String palabraClave,JTable tabla){
        modeloLibro.buscarLibro(palabraClave, tabla);
    }

}
