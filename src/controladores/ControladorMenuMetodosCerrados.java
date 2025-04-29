package controladores;

import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.SystemColor.menu;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import modelos.ModeloMenuMetodosCerrados;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import vistas.VentanaPrincipal;
import vistas.VistaMetodoBiseccion;
import vistas.VistaMetodoReglaFalsa;

public class ControladorMenuMetodosCerrados implements MouseListener {

    ModeloMenuMetodosCerrados modelo;
   
    

    public ControladorMenuMetodosCerrados(ModeloMenuMetodosCerrados modelo) {
        this.modelo = modelo;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getComponent().equals(modelo.getVistaMenuMC().btnBiseccion)) {
            VistaMetodoBiseccion panelMetodBis = new VistaMetodoBiseccion();
            MostrarPanel(panelMetodBis);

        } else if (e.getComponent().equals(modelo.getVistaMenuMC().btnReglaFalsa)) {
            VistaMetodoReglaFalsa panelMetodoRFalse = new VistaMetodoReglaFalsa();
            MostrarPanel(panelMetodoRFalse);
        } else if(e.getComponent().equals(modelo.getVistaMenuMC().btnVolver)){
            VentanaPrincipal vistaPrincipal = new VentanaPrincipal();
            vistaPrincipal.setVisible(true);
            modelo.getVistaMenuMC().dispose();
        }
     
    }
   
    
//    else if (e.getComponent().equals(modelo.getVistaMenuMC().btnVolver)) {
            
//        }
//    vistas.VentanaPrincipal JframePrincipal = new VentanaPrincipal();
//            System.out.println("Hola PAPU");
//
//            JframePrincipal.getMiJPanel().setSize(1280, 720);
//            JframePrincipal.getMiJPanel().setLocation(0, 0);
//
//            JframePrincipal.getMiContenedor().removeAll();
//            JframePrincipal.getMiContenedor().add(JframePrincipal.getMiJPanel(), BorderLayout.CENTER);
//            JframePrincipal.getMiContenedor().revalidate();
//            JframePrincipal.getMiContenedor().repaint();
//            System.out.println("Hola PAPU");

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

        if (e.getComponent().equals(modelo.getVistaMenuMC().btnBiseccion)) {
            modelo.getVistaMenuMC().btnBiseccion.setBackground(new Color(65,105,225));

        } else if(e.getComponent().equals(modelo.getVistaMenuMC().btnReglaFalsa)){
            modelo.getVistaMenuMC().btnReglaFalsa.setBackground(new Color(65,105,225));
        } else if(e.getComponent().equals(modelo.getVistaMenuMC().btnVolver)){
            modelo.getVistaMenuMC().btnVolver.setBackground(new Color(65,105,225));
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {

        if (e.getComponent().equals(modelo.getVistaMenuMC().btnBiseccion)) {
            modelo.getVistaMenuMC().btnBiseccion.setBackground(new Color(0,102,204));

        } else if(e.getComponent().equals(modelo.getVistaMenuMC().btnReglaFalsa)){
            modelo.getVistaMenuMC().btnReglaFalsa.setBackground(new Color(0,102,204));            
        } else if(e.getComponent().equals(modelo.getVistaMenuMC().btnVolver)){
            modelo.getVistaMenuMC().btnVolver.setBackground(new Color(0,102,204));
        }

    }

  

    //Metodo para llamar al JPaneForm
    public void MostrarPanel(JPanel p) {
        p.setSize(1280, 720);
        p.setLocation(0, 0);
        
        modelo.getVistaMenuMC().ContenedorVistas.setLayout(new BorderLayout());
        modelo.getVistaMenuMC().ContenedorVistas.removeAll();
        modelo.getVistaMenuMC().ContenedorVistas.add(p, BorderLayout.CENTER);
        modelo.getVistaMenuMC().ContenedorVistas.revalidate();
        modelo.getVistaMenuMC().ContenedorVistas.repaint();
    }

    

    
}
