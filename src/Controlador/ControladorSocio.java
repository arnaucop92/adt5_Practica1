/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Socio;
import Modelo.SocioDAO;
import Vista.JDSocios;
import Vista.JPSocio;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author arnau
 */
public class ControladorSocio {
    private SocioDAO modeloSocio;
    private JPSocio vistaSocio;
    private JDSocios dialogSocio;

    
     public ControladorSocio( JPSocio vistaSocio) {
        this.modeloSocio = new SocioDAO();
        this.vistaSocio = vistaSocio;
    }

    public ControladorSocio( JDSocios dialogSocio) {
        this.modeloSocio = new SocioDAO();
        this.dialogSocio = dialogSocio;
    }
    
    public SocioDAO getModeloSocio() {
        return modeloSocio;
    }

    public void setModeloSocio(SocioDAO modeloSocio) {
        this.modeloSocio = modeloSocio;
    }

    public JPSocio getVistaSocio() {
        return vistaSocio;
    }

    public void setVistaSocio(JPSocio vistaSocio) {
        this.vistaSocio = vistaSocio;
    }

    public ControladorSocio() {
    }
    
    public void anyadirSocio(){
        
        Socio nuevoSocio = new Socio(Integer.parseInt(vistaSocio.getNumSocio().getText()), vistaSocio.getNombreSocio().getText(), vistaSocio.getApellidosSocio().getText(),vistaSocio.getTelSocio().getText() , Integer.parseInt(vistaSocio.getEdadSocio().getValue().toString()), vistaSocio.getFechaSocio().getText());
        modeloSocio.insertarSocio(nuevoSocio);
    }
    
     public void modificarSocio(){
        
        Socio nuevoSocio = new Socio(Integer.parseInt(vistaSocio.getNumSocio().getText()), vistaSocio.getNombreSocio().getText(), vistaSocio.getApellidosSocio().getText(),vistaSocio.getTelSocio().getText() , Integer.parseInt(vistaSocio.getEdadSocio().getValue().toString()), vistaSocio.getFechaSocio().getText());
        modeloSocio.modificarSocio(nuevoSocio);
    }

    public void busquedaSocios() {
        
      DefaultTableModel dtm = (DefaultTableModel) vistaSocio.getTableSocios().getModel();
       dtm.getDataVector().clear();
       
     ArrayList<Socio> socios =  modeloSocio.leerSocio(vistaSocio.getBusquedaSocio().getText());
        for (int i = 0; i < socios.size(); i++) {
            Socio socio = socios.get(i);
           dtm.addRow(new Object[]{socio.getNumsocio(),
           socio.getNombre(),
           socio.getApellidos(),
           socio.getTelefono(),
           socio.getEdad(),
           socio.getFalta()});
        }
        
    }

    public void eliminarSocio(){
        
        Socio nuevoSocio = new Socio(Integer.parseInt(vistaSocio.getNumSocio().getText())
                , vistaSocio.getNombreSocio().getText(), vistaSocio.getApellidosSocio().getText()
                ,vistaSocio.getTelSocio().toString(), (int)vistaSocio.getEdadSocio().getValue()
                , vistaSocio.getFechaSocio().toString());
        
        modeloSocio.eliminarSocio(nuevoSocio);
    }

    public void socioSeleccionado(DefaultTableModel tm) {
    
        vistaSocio.getNumSocio().setText(String.valueOf(tm.getValueAt(vistaSocio.getTableSocios().getSelectedRow(), 0)));
        vistaSocio.getNombreSocio().setText(String.valueOf(tm.getValueAt(vistaSocio.getTableSocios().getSelectedRow(), 1)));
        vistaSocio.getApellidosSocio().setText(String.valueOf(tm.getValueAt(vistaSocio.getTableSocios().getSelectedRow(), 2)));
        vistaSocio.getTelSocio().setText(String.valueOf(tm.getValueAt(vistaSocio.getTableSocios().getSelectedRow(), 3)));
        vistaSocio.getEdadSocio().setValue(Integer.valueOf(String.valueOf(tm.getValueAt(vistaSocio.getTableSocios().getSelectedRow(), 4))));
        vistaSocio.getFechaSocio().setText(String.valueOf(tm.getValueAt(vistaSocio.getTableSocios().getSelectedRow(), 5)));
        
    }
    
     public void limpiarSocio(){
        
         
        vistaSocio.getNumSocio().setText("");
        vistaSocio.getNombreSocio().setText("");
        vistaSocio.getApellidosSocio().setText("");
        vistaSocio.getTelSocio().setText("");
        vistaSocio.getEdadSocio().setValue(0);
        vistaSocio.getFechaSocio().setText(""); 
        
    }
     
     public void buscarSocioDialog(String palabraClave,JTable tabla){
        modeloSocio.buscarSocio(palabraClave, tabla);
    }
 
}
