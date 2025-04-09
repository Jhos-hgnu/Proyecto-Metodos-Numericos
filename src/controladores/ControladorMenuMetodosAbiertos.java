package controladores;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import modelos.ModeloMenuMetodosAbiertos;
import vistas.VentanaPrincipal;
import vistas.VistaMetodoNewton;
import vistas.VistaMetodoSecante;

public class ControladorMenuMetodosAbiertos implements MouseListener{
    
    ModeloMenuMetodosAbiertos modelo;

    public ControladorMenuMetodosAbiertos(ModeloMenuMetodosAbiertos modelo) {
        this.modelo = modelo;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getComponent().equals(modelo.getVistaMetodosAbiertos().btnMetodoNewton)){
            System.out.println("MetodoNewton");
            VistaMetodoNewton vistaMN = new VistaMetodoNewton();
            showPanel(vistaMN);
            
        } else if(e.getComponent().equals(modelo.getVistaMetodosAbiertos().btnMetodoSecante)){
            System.out.println("MetodoSecante");
            VistaMetodoSecante vistaMS = new VistaMetodoSecante();
            showPanel(vistaMS);
        } else if (e.getComponent().equals(modelo.getVistaMetodosAbiertos().btnVolver)){
            VentanaPrincipal vistaP = new VentanaPrincipal();
            vistaP.setVisible(true);
            modelo.getVistaMetodosAbiertos().dispose();
        }
        
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getComponent().equals(modelo.getVistaMetodosAbiertos().btnMetodoNewton)){
            modelo.getVistaMetodosAbiertos().btnMetodoNewton.setBackground(new Color(153, 255, 255));
        } else if(e.getComponent().equals(modelo.getVistaMetodosAbiertos().btnMetodoSecante)){
            modelo.getVistaMetodosAbiertos().btnMetodoSecante.setBackground(new Color(153, 255, 255));
        } else if(e.getComponent().equals(modelo.getVistaMetodosAbiertos().btnVolver)){
            modelo.getVistaMetodosAbiertos().btnVolver.setBackground(new Color(153, 255, 255));
        }
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getComponent().equals(modelo.getVistaMetodosAbiertos().btnMetodoNewton)){
            modelo.getVistaMetodosAbiertos().btnMetodoNewton.setBackground(new Color(172, 229, 246));
        } else if(e.getComponent().equals(modelo.getVistaMetodosAbiertos().btnMetodoSecante)){
            modelo.getVistaMetodosAbiertos().btnMetodoSecante.setBackground(new Color(172, 229, 246));
        } else if (e.getComponent().equals(modelo.getVistaMetodosAbiertos().btnVolver)){
            modelo.getVistaMetodosAbiertos().btnVolver.setBackground(new Color(172, 229, 246));
        }
    }
    
    public void showPanel(JPanel p){
        p.setSize(1273, 720);
        p.setLocation(0, 0);
        
        modelo.getVistaMetodosAbiertos().contenedorVistas.removeAll();
        modelo.getVistaMetodosAbiertos().contenedorVistas.add(p, BorderLayout.CENTER);
        modelo.getVistaMetodosAbiertos().contenedorVistas.revalidate();
        modelo.getVistaMetodosAbiertos().contenedorVistas.repaint();
    }
       
}