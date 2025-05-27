/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import implementaciones.MetodoInterpolacionImp;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelos.ModeloMenuInterpolacion;
import vistas.VentanaPrincipal;

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
            inputVacio();
        } else if (e.getComponent().equals(modelo.getMenuInterpolacion().btnLimpiar)) {
            cleanData();
        } else if (e.getComponent().equals(modelo.getMenuInterpolacion().radioBtnNewton)) {
            radBtnSelected();
        } else if (e.getComponent().equals(modelo.getMenuInterpolacion().radioBtnLangrange)) {
            radBtnSelected();
        } else if(e.getComponent().equals(modelo.getMenuInterpolacion().btnVolver)){
            VentanaPrincipal vistaP = new VentanaPrincipal();
            vistaP.setVisible(true);
            modelo.getMenuInterpolacion().dispose();
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
        if(e.getComponent().equals(modelo.getMenuInterpolacion().btnCalcular)){
            modelo.getMenuInterpolacion().btnCalcular.setBackground(new Color(51,51,255));
        } else if (e.getComponent().equals(modelo.getMenuInterpolacion().btnLimpiar)){
            modelo.getMenuInterpolacion().btnLimpiar.setBackground(new Color(51,51,255));
        } else if (e.getComponent().equals(modelo.getMenuInterpolacion().btnVolver)){
            modelo.getMenuInterpolacion().btnVolver.setBackground(new Color(51,51,255));
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getComponent().equals(modelo.getMenuInterpolacion().btnCalcular)){
            modelo.getMenuInterpolacion().btnCalcular.setBackground(new Color(0,102,204));
        } else if (e.getComponent().equals(modelo.getMenuInterpolacion().btnLimpiar)){
            modelo.getMenuInterpolacion().btnLimpiar.setBackground(new Color(0,102,204));
        } else if (e.getComponent().equals(modelo.getMenuInterpolacion().btnVolver)){
            modelo.getMenuInterpolacion().btnVolver.setBackground(new Color(0,102,204));
        }
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
            modelo.getMenuInterpolacion().labelElementX.setVisible(true);
            modelo.getMenuInterpolacion().txtElementX.setVisible(true);
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
            modelo.getMenuInterpolacion().labelElementX.setVisible(true);
            modelo.getMenuInterpolacion().txtElementX.setVisible(true);
        }
    }

    private void inputVacio() {
        if (modelo.getMenuInterpolacion().txtLabelX1.getText().isEmpty()
                || modelo.getMenuInterpolacion().txtlabelX2.getText().isEmpty()
                || modelo.getMenuInterpolacion().txtLabelY1.getText().isEmpty()
                || modelo.getMenuInterpolacion().txtlabelY2.getText().isEmpty()) {
            JOptionPane.showInternalMessageDialog(null, "Por favor debe de ingresar todos los datos", "ERROR \"DATOS VACIOS\"", JOptionPane.ERROR_MESSAGE);

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
        
        if(modelo.getMenuInterpolacion().radioBtnNewton.isSelected()){
            double respuestaNewton = implementacion.calcularInterpolacionNewton(x, x1, x2, y1, y2);
        resolverInterpolacion(respuestaNewton);
        } else if (modelo.getMenuInterpolacion().radioBtnLangrange.isSelected()){
            double respuestaLangrange = implementacion.calculateInterpolacionLangrange(x, x1, x2, y1, y2);
            resolverInterpolacion(respuestaLangrange);
        }
    }
    
    private void resolverInterpolacion(double respuesta){
        
        modelo.getMenuInterpolacion().labelRespuesta.setVisible(true);
        modelo.getMenuInterpolacion().txtRespuesta.setText(String.valueOf(respuesta));
        modelo.getMenuInterpolacion().txtRespuesta.setVisible(true);
        modelo.getMenuInterpolacion().btnLimpiar.setVisible(true);
        
    }
    
    private void cleanData(){
        
        modelo.getMenuInterpolacion().LabelX1.setVisible(false);
        modelo.getMenuInterpolacion().labelX2.setVisible(false);
        modelo.getMenuInterpolacion().labely1.setVisible(false);
        modelo.getMenuInterpolacion().labely2.setVisible(false);
        modelo.getMenuInterpolacion().btnCalcular.setVisible(false);
        modelo.getMenuInterpolacion().btnLimpiar.setVisible(false);
        modelo.getMenuInterpolacion().btnGroup.clearSelection();
        modelo.getMenuInterpolacion().txtElementX.setText("");
        modelo.getMenuInterpolacion().labelElementX.setVisible(false);
        modelo.getMenuInterpolacion().txtElementX.setVisible(false);
        modelo.getMenuInterpolacion().txtLabelX1.setText("");
        modelo.getMenuInterpolacion().txtlabelX2.setText("");
        modelo.getMenuInterpolacion().txtLabelY1.setText("");
        modelo.getMenuInterpolacion().txtlabelY2.setText("");
        modelo.getMenuInterpolacion().labelRespuesta.setVisible(false);
        modelo.getMenuInterpolacion().txtRespuesta.setText("");
        modelo.getMenuInterpolacion().txtRespuesta.setVisible(false);
        
    }
        
    
}
