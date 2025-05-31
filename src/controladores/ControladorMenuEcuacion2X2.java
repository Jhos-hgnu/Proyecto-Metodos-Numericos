/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import implementaciones.MetodoEcuacion2x2Imp;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import modelos.ModeloMenuEcuacion2X2;
import vistas.VistaEcuaciones2x2;


/**
 *
 * @author merar
 */
public class ControladorMenuEcuacion2X2 implements MouseListener {
    
      private double a1, b1, c1;
    private double a2, b2, c2;
    
    private JTextField txtFuncion1, txtFuncion2;
    private JButton btnResolver;
    private JLabel JPaneRespuesta;
    

    private static ControladorMenuEcuacion2X2 parsearEcuaciones(String eq1, String eq2) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    ModeloMenuEcuacion2X2 modelo;
    MetodoEcuacion2x2Imp implementacion = new MetodoEcuacion2x2Imp();
    
    
    public ControladorMenuEcuacion2X2 (ModeloMenuEcuacion2X2 modelo) {
        this.modelo = modelo;
}
    ButtonGroup MetodoSelec = new ButtonGroup();
    

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent().equals(modelo.getVistaEcuaciones2x2().btnResolver)){
            inputIsEmpty();
        }
        else if (e.getComponent().equals(modelo.getVistaEcuaciones2x2().btnLimpiar)){
        CleannDataInput();
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
           if (e.getComponent().equals(modelo.getVistaEcuaciones2x2().btnResolver)){
            modelo.getVistaEcuaciones2x2().btnResolver.setBackground(new Color(153, 255, 255));
        }else if (e.getComponent().equals(modelo.getVistaEcuaciones2x2().btnLimpiar)) {
            modelo.getVistaEcuaciones2x2().btnLimpiar.setBackground(new Color(153, 255, 255));
        }  
    }

    @Override
    public void mouseExited(MouseEvent e) {
    if (e.getComponent().equals(modelo.getVistaEcuaciones2x2().btnResolver)) {
            modelo.getVistaEcuaciones2x2().btnResolver.setBackground(new Color(172, 229, 246));
        } else if (e.getComponent().equals(modelo.getVistaEcuaciones2x2().btnLimpiar)) {
            modelo.getVistaEcuaciones2x2().btnLimpiar.setBackground(new Color(172, 229, 246));
        }}

    private void inputIsEmpty() {
      
  }

    private void CleannDataInput() {
        
        
    }

    
     private static String resolversistema(ControladorMenuEcuacion2X2 sistema) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
  
    
     public  ControladorMenuEcuacion2X2( double b1, double c1, double a2, double b2, double c2){
     
        this.a1 = a1; 
        this.b1 = b1;
        this.c1 = c1;
        this.a2 = a2;
        this.b2 = b2;
        this.c2 = c2;
        
        
    }

    public ControladorMenuEcuacion2X2(double d, double d0, double d1, double d2, double d3, double d4) {
   }
    
    public double getA1() {return a1;}
    public double getB1() {return b1;}
    public double getC1() {return c1;}
    public double getA2() {return a2;}
    public double getB2() {return b2;}
    public double getC2() {return c2;}

    public void MostrarPanel(JPanel p) {
        p.setSize(1280, 720);
        p.setLocation(0, 0);
 
        modelo.getVistaEcuaciones2x2().ContenedorVisEcuaciones.removeAll();
         modelo.getVistaEcuaciones2x2().ContenedorVisEcuaciones.add(p, BorderLayout.CENTER);
          modelo.getVistaEcuaciones2x2().ContenedorVisEcuaciones.revalidate();
           modelo.getVistaEcuaciones2x2().ContenedorVisEcuaciones.repaint();
        
    }
    
        public ControladorMenuEcuacion2X2 (){
      
      
      txtFuncion1 = new JTextField();
      txtFuncion2 = new JTextField();
      btnResolver = new JButton("Resolver");
      JPaneRespuesta = new JLabel("Resultado: ", SwingConstants.CENTER);

        add(new JLabel("Ecuación 1 (ej: 2x+3y=6):"));
         add(txtFuncion1);
        add(new JLabel("Ecuación 2 (ej: -x+y=4):"));
        add(txtFuncion2);
        add(btnResolver);
        add(JPaneRespuesta);
        
         btnResolver.addActionListener((ActionListener) this);

        setLocationRelativeTo(null);
        setVisible(true);

       

    }  
    public void actionPerformed(ActionEvent e) {
        try {
            String eq1 = txtFuncion1.getText();
            String eq2 = txtFuncion2.getText();

           ControladorMenuEcuacion2X2 sistema = ControladorMenuEcuacion2X2.parsearEcuaciones(eq1, eq2);
            String resultado = ControladorMenuEcuacion2X2.resolversistema (sistema);
            JPaneRespuesta.setText(resultado);
        } catch (Exception ex) {
            JPaneRespuesta.setText("Error: " + ex.getMessage());
        }
    }

    private void add(JLabel jLabel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void add(JTextField txtEcuacion1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void add(JButton btnResolver) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void setLocationRelativeTo(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

 
    private void resolverEcuacion (double respuesta){
        modelo.getVistaEcuaciones2x2().jLabRespuesta.setVisible(true);
        modelo.getVistaEcuaciones2x2().jPaneRespuesta.setToolTipText(String.valueOf(respuesta));
        modelo.getVistaEcuaciones2x2().jPaneRespuesta.setVisible(true);
        modelo.getVistaEcuaciones2x2().btnLimpiar.setVisible(true);
    }
    
  



}
            
    