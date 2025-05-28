/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import jdk.jfr.SettingControl;



/**
 *
 * @author merar
 */
public class ControladorMenuEcuacion2X2 {

    private static ControladorMenuEcuacion2X2 parsearEcuaciones(String eq1, String eq2) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static String resolversistema(ControladorMenuEcuacion2X2 sistema) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private double a1, b1, c1;
    private double a2, b2, c2;
    
    private JTextField txtEcuacion1, txtEcuacion2;
    private JButton btnResolver;
    private JLabel lblResultado;
    
    
     public  ControladorMenuEcuacion2X2( double b1, double c1, double a2, double b2, double c2){
     
        this.a1 = a1; 
        this.b1 = b1;
        this.c1 = c1;
        this.a2 = a2;
        this.b2 = b2;
        this.c2 = c2;
        
        
    }

    public ControladorMenuEcuacion2X2(double d, double d0, double d1, double d2, double d3, double d4) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public double getA1() {return a1;}
    public double getB1() {return b1;}
    public double getC1() {return c1;}
    public double getA2() {return a2;}
    public double getB2() {return b2;}
    public double getC2() {return c2;}
    
    public ControladorMenuEcuacion2X2 (){
      SetTitle("Ecuaciones 2x2 - Metodo de sustitución");
      setSize(400,250);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setLayout(new GridLayout(5,1));
      
      txtEcuacion1 = new JTextField();
      txtEcuacion2 = new JTextField();
      btnResolver = new JButton("Resolver");
      lblResultado = new JLabel("Resultado: ", SwingConstants.CENTER);

        add(new JLabel("Ecuación 1 (ej: 2x+3y=6):"));
         add(txtEcuacion1);
        add(new JLabel("Ecuación 2 (ej: -x+y=4):"));
        add(txtEcuacion2);
        add(btnResolver);
        add(lblResultado);
        
         btnResolver.addActionListener((ActionListener) this);

        setLocationRelativeTo(null);
        setVisible(true);
    
    }  
    public void actionPerformed(ActionEvent e) {
        try {
            String eq1 = txtEcuacion1.getText();
            String eq2 = txtEcuacion2.getText();

           ControladorMenuEcuacion2X2 sistema = ControladorMenuEcuacion2X2.parsearEcuaciones(eq1, eq2);
            String resultado = ControladorMenuEcuacion2X2.resolversistema (sistema);
            lblResultado.setText(resultado);
        } catch (Exception ex) {
            lblResultado.setText("Error: " + ex.getMessage());
        }
    }

    
    private void SetTitle(String ecuaciones_2x2__Metodo_de_sustitución) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void setSize(int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void setDefaultCloseOperation(int EXIT_ON_CLOSE) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void setLayout(GridLayout gridLayout) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    
    
}
