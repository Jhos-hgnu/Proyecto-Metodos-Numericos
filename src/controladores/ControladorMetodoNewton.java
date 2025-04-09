package controladores;

import implementaciones.MetodoNewtonImp;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelos.ModeloMetodoNewton;

public class ControladorMetodoNewton implements MouseListener {

    ModeloMetodoNewton modelo;
    MetodoNewtonImp implementacion;

    public ControladorMetodoNewton(ModeloMetodoNewton modelo) {
        this.modelo = modelo;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent().equals(modelo.getVistaNewton().btnResolver)) {

        }
        if (e.getComponent().equals(modelo.getVistaNewton().btnLimpiar)) {
            cleanData();
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
        if (e.getComponent().equals(modelo.getVistaNewton().btnResolver)) {
            modelo.getVistaNewton().btnResolver.setBackground(new Color(153, 255, 255));
        } else if(e.getComponent().equals(modelo.getVistaNewton().btnLimpiar)){
            modelo.getVistaNewton().btnLimpiar.setBackground(new Color(153, 255, 255));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

        if (e.getComponent().equals(modelo.getVistaNewton().btnResolver)) {
            modelo.getVistaNewton().btnResolver.setBackground(new Color(172, 229, 246));
        } else if(e.getComponent().equals(modelo.getVistaNewton().btnLimpiar)){
            modelo.getVistaNewton().btnLimpiar.setBackground(new Color(172, 229, 246));
        }
    }

    //METODO PARA OBTENER LOS DATOS DE LOS INPUTS
    public void captureDatos() {
        double inputXI = Double.parseDouble(modelo.getVistaNewton().txtXi.getText());
        String inputFuction = modelo.getVistaNewton().txtFuction.getText();
    }

    public void inputsIsEmpty() {

    }

    public void visibleElements() {
        modelo.getVistaNewton().btnLimpiar.setVisible(true);
        modelo.getVistaNewton().jLabelXRObtenido.setVisible(true);
        modelo.getVistaNewton().jComboBXR.setVisible(true);

    }

    public void cleanData() {
        modelo.getVistaNewton().txtFuction.setText("");
        modelo.getVistaNewton().txtXi.setText("");
        modelo.getVistaNewton().btnLimpiar.setVisible(false);
        modelo.getVistaNewton().jLabelXRObtenido.setVisible(false);
        modelo.getVistaNewton().jComboBXR.setVisible(false);
        modelo.getVistaNewton().jComboBXR.removeAll();
        modelo.getVistaNewton().jComboBXR.revalidate();
        modelo.getVistaNewton().jComboBXR.repaint();
        modelo.getVistaNewton().jScrollTablaMN.removeAll();
        modelo.getVistaNewton().jScrollTablaMN.revalidate();
        modelo.getVistaNewton().jScrollTablaMN.repaint();

    }
}
