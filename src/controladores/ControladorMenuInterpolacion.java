/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import implementaciones.MetodoInterpolacionImp;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelos.ModeloMenuInterpolacion;

/**
 *
 * @author jhosu
 */
public class ControladorMenuInterpolacion implements MouseListener {

    MetodoInterpolacionImp implementacion = new MetodoInterpolacionImp();
    
    ModeloMenuInterpolacion modelo;

    public ControladorMenuInterpolacion(ModeloMenuInterpolacion modelo) {
        this.modelo = modelo;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent().equals(modelo.getMenuInterpolacion().btnCalcular)) {
            System.out.println("Holaa");
        } else if (e.getComponent().equals(modelo.getMenuInterpolacion().btnLimpiar)) {

        } else if (e.getComponent().equals(modelo.getMenuInterpolacion().radioBtnNewton)) {
            radBtnSelected();
        } else if (e.getComponent().equals(modelo.getMenuInterpolacion().radioBtnLangrange)) {
            radBtnSelected();
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

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void radBtnSelected() {
        boolean newton = modelo.getMenuInterpolacion().radioBtnNewton.isSelected();
        boolean langrange = modelo.getMenuInterpolacion().radioBtnLangrange.isSelected();

        if (newton) {
            modelo.getMenuInterpolacion().LabelX1.setText("X1");
            modelo.getMenuInterpolacion().LabelX1.setVisible(true);
            modelo.getMenuInterpolacion().labelX2.setText("X2");
            modelo.getMenuInterpolacion().labelX2.setVisible(true);
            modelo.getMenuInterpolacion().labely1.setText("Y1");
            modelo.getMenuInterpolacion().labely1.setVisible(true);
            modelo.getMenuInterpolacion().labely2.setText("Y2");
            modelo.getMenuInterpolacion().labely2.setVisible(true);
            modelo.getMenuInterpolacion().btnCalcular.setVisible(true);
        } else if (langrange) {
            modelo.getMenuInterpolacion().LabelX1.setText("Xo");
            modelo.getMenuInterpolacion().LabelX1.setVisible(true);
            modelo.getMenuInterpolacion().labelX2.setText("X1");
            modelo.getMenuInterpolacion().labelX2.setVisible(true);
            modelo.getMenuInterpolacion().labely1.setText("F(Xo)");
            modelo.getMenuInterpolacion().labely1.setVisible(true);
            modelo.getMenuInterpolacion().labely2.setText("F(X1)");
            modelo.getMenuInterpolacion().labely2.setVisible(true);
            modelo.getMenuInterpolacion().btnCalcular.setVisible(true);
        }
    }

    private void inputVacio() {
        if (modelo.getMenuInterpolacion().txtLabelX1.getText().isEmpty()
                || modelo.getMenuInterpolacion().txtlabelX2.getText().isEmpty()
                || modelo.getMenuInterpolacion().txtLabelY1.getText().isEmpty()
                || modelo.getMenuInterpolacion().txtlabelY2.getText().isEmpty()) {
            JOptionPane.showInternalMessageDialog(null, "Por favor debe de ingresar todos los datos CON", "ERROR \"DATOS VACIOS\"", JOptionPane.ERROR_MESSAGE);

        } else {
            capturaData();
        }
    }

    private void capturaData(){
        double x1 = Double.parseDouble(modelo.getMenuInterpolacion().txtLabelX1.getText());
        double x2 = Double.parseDouble(modelo.getMenuInterpolacion().txtlabelX2.getText());
        double y1 = Double.parseDouble(modelo.getMenuInterpolacion().txtLabelY1.getText());
        double y2 = Double.parseDouble(modelo.getMenuInterpolacion().txtlabelY2.getText());
        double x = Double.parseDouble(modelo.getMenuInterpolacion().txtElementX.getText());
        
        double respuesta = implementacion.calcularInterpolacionNewton(x, x1, x2, y1, y2);
        resolverNewton(respuesta);
    }
    
    private void resolverNewton(double respuesta){
        
        modelo.getMenuInterpolacion().labelRespuesta.setVisible(true);
        modelo.getMenuInterpolacion().txtRespuesta.setText(String.valueOf(respuesta));
        modelo.getMenuInterpolacion().txtRespuesta.setVisible(true);
        modelo.getMenuInterpolacion().btnLimpiar.setVisible(true);
        
    }
    
}
