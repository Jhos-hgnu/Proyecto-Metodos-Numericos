package controladores;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import modelos.ModeloVistaPrincipal;
import vistas.MenuMetodosAbiertos;
import vistas.MenuMetodosCerrados;
import vistas.MenuMetodosCerradosA;

public class ControladorVistaPrincipal implements MouseListener{

    ModeloVistaPrincipal modelo;

    public ControladorVistaPrincipal(ModeloVistaPrincipal modelo) {
        this.modelo = modelo;
    }
    
    
    //Metodo para llamar al JPaneForm
    public void MostrarPanel(JPanel p){
        p.setSize(1280, 720);
        p.setLocation(0, 0);
        
        modelo.getVistaP().pnlContenedor.removeAll();
        modelo.getVistaP().pnlContenedor.add(p,BorderLayout.CENTER);
        modelo.getVistaP().pnlContenedor.revalidate();
        modelo.getVistaP().pnlContenedor.repaint();   
    }
 
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getComponent().equals(modelo.getVistaP().btnMetodoCerrado)){
            MenuMetodosCerrados menuMC = new MenuMetodosCerrados();
            menuMC.setVisible(true);
            modelo.getVistaP().dispose();
            
        } else if (e.getComponent().equals(modelo.getVistaP().btnMetodosAbiertos)){
            System.out.println("Gol");
            MenuMetodosAbiertos menuMA = new MenuMetodosAbiertos();
            menuMA.setVisible(true);
            modelo.getVistaP().dispose();
            
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
        
        if(e.getComponent().equals(modelo.getVistaP().btnMetodoCerrado)){
            modelo.getVistaP().btnMetodoCerrado.setBackground(new Color(153,255,255));   
        } else if(e.getComponent().equals(modelo.getVistaP().btnMetodosAbiertos)){
            modelo.getVistaP().btnMetodosAbiertos.setBackground(new Color(153,255,255));
        }else if (e.getComponent().equals(modelo.getVistaP().btnRaicesPolinomios)){
            modelo.getVistaP().btnRaicesPolinomios.setBackground(new Color(153,255,255));
        }else if (e.getComponent().equals(modelo.getVistaP().btnEcuaciones)){
            modelo.getVistaP().btnEcuaciones.setBackground(new Color(153,255,255));
        } else if(e.getComponent().equals(modelo.getVistaP().btnVolver)){
            modelo.getVistaP().btnVolver.setBackground(new Color(153,255,255));
        }
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
        if(e.getComponent().equals(modelo.getVistaP().btnMetodoCerrado)){
            modelo.getVistaP().btnMetodoCerrado.setBackground(new Color(172,229,246));           
        } else if(e.getComponent().equals(modelo.getVistaP().btnMetodosAbiertos)){
            modelo.getVistaP().btnMetodosAbiertos.setBackground(new Color(172,229,246));
        }else if (e.getComponent().equals(modelo.getVistaP().btnRaicesPolinomios)){
            modelo.getVistaP().btnRaicesPolinomios.setBackground(new Color(172,229,246));
        }else if (e.getComponent().equals(modelo.getVistaP().btnEcuaciones)){
            modelo.getVistaP().btnEcuaciones.setBackground(new Color(172,229,246));
        }else if(e.getComponent().equals(modelo.getVistaP().btnVolver)){
            modelo.getVistaP().btnVolver.setBackground(new Color(172,229,246));
        }
    }
    
    
    
}
