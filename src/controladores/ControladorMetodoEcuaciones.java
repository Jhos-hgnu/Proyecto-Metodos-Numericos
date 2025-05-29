/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelos.ModeloMetodoEcuaciones;
import vistas.VistaEcuaciones2x2;

/**
 *
 * @author jhosu
 */
public class ControladorMetodoEcuaciones implements MouseListener {

    ModeloMetodoEcuaciones modelo;
    
    public ControladorMetodoEcuaciones(ModeloMetodoEcuaciones modelo){
    this.modelo = modelo;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        
     if (e.getComponent().equals(modelo.getMetodocuaciones()))
         System.out.print("Metodoecuacion");
        VistaEcuaciones2x2 VistaEcuaciones2x2 = VistaEcuaciones2x2();
        showPanel(VistaEcuaciones2x2);
        
 }
    

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private VistaEcuaciones2x2 VistaEcuaciones2x2() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void showPanel(VistaEcuaciones2x2 VistaEcuaciones2x2) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
