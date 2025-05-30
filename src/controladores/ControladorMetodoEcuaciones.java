/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import modelos.ModeloMetodoEcuaciones;
import vistas.VentanaPrincipal;
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
        
       if (e.getComponent().equals(modelo.getVistaMetodoEcuaciones().btnVolver)){
            VentanaPrincipal vistaP = new VentanaPrincipal();
            vistaP.setVisible(true);
            modelo.getVistaMetodoEcuaciones().dispose();
        } else if (e.getComponent().equals(modelo.getVistaMetodoEcuaciones().btnEcua2x2)){
            System.out.println("Ecua2x2");
            VistaEcuaciones2x2 VistaE2 = new VistaEcuaciones2x2();
            showPanel(VistaE2);
           
        }

          //else if(e.getComponent().equals(modelo.getVistaMetodoEcuaciones().btnEcua3x3)){
          //  System.out.println("Ecuacion3x3");
          //  VistaEcuaciones2x2 vistaE3 = new VistaEcuaciones2x2();
           // showPanel(vistaE3);
        //}//else if(e.getComponent().equals(modelo.getVistaMetodoEcuaciones().btnEcua4x4)){
          //  System.out.println("Ecuacion4x4");
          //  VistaEcuaciones2x2 vistaE4 = new VistaEcuaciones2x2();
          //  showPanel(vistaE4  );
        //}
    }
    

    @Override
    public void mousePressed(MouseEvent e) {
     }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getComponent().equals(modelo.getVistaMetodoEcuaciones().btnEcua2x2)){
            modelo.getVistaMetodoEcuaciones().btnEcua2x2.setBackground(new Color(51,51,255));   
        }else if (e.getComponent().equals(modelo.getVistaMetodoEcuaciones().btnEcua3x3)){
            modelo.getVistaMetodoEcuaciones().btnEcua3x3.setBackground(new Color(51,51,255));
        } else if (e.getComponent().equals(modelo.getVistaMetodoEcuaciones().btnEcua4x4)){
            modelo.getVistaMetodoEcuaciones().btnEcua4x4.setBackground(new Color(51,51,255));
        } else if(e.getComponent().equals(modelo.getVistaMetodoEcuaciones().btnVolver)){
            modelo.getVistaMetodoEcuaciones().btnVolver.setBackground(new Color(51,51,255));
      
    }}

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getComponent().equals(modelo.getVistaMetodoEcuaciones().btnEcua2x2)){
            modelo.getVistaMetodoEcuaciones().btnEcua2x2.setBackground(new Color(0,102,204));
        } else if(e.getComponent().equals(modelo.getVistaMetodoEcuaciones().btnEcua3x3)){
            modelo.getVistaMetodoEcuaciones().btnEcua3x3.setBackground(new Color(0,102,204));
        }  else if(e.getComponent().equals(modelo.getVistaMetodoEcuaciones().btnEcua4x4)){
            modelo.getVistaMetodoEcuaciones().btnEcua4x4.setBackground(new Color(0,102,204));
        }  else if(e.getComponent().equals(modelo.getVistaMetodoEcuaciones().btnVolver)){
            modelo.getVistaMetodoEcuaciones().btnVolver.setBackground(new Color(0,102,204));
        } 
  
    }

    private void showPanel(JPanel p) {
    p.setSize(1171, 720);
        p.setLocation(0, 0);
        
        modelo.getVistaMetodoEcuaciones().ContenedorVistas.removeAll();
        modelo.getVistaMetodoEcuaciones().ContenedorVistas.add(p,BorderLayout.CENTER);
        modelo.getVistaMetodoEcuaciones().ContenedorVistas.revalidate();
        modelo.getVistaMetodoEcuaciones().ContenedorVistas.repaint();
    }

   
    
}
