/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import implementaciones.MetodoMullerImp;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import modelos.ModeloMetodoMuller;
import vistas.VentanaPrincipal;

/**
 *
 * @author jhosu
 */
public class ControladorMetodoMuller implements MouseListener {

    MetodoMullerImp implementacion = new MetodoMullerImp();

    ModeloMetodoMuller modelo;

    public ControladorMetodoMuller(ModeloMetodoMuller modelo) {
        this.modelo = modelo;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent().equals(modelo.getVistaMuller().btnResolver)) {
            inputIsEmpty();
        } else if (e.getComponent().equals(modelo.getVistaMuller().btnLimpiar)){
            cleanData();
        } else if (e.getComponent().equals(modelo.getVistaMuller().btnVolver)){
            VentanaPrincipal vistaP = new VentanaPrincipal();
            vistaP.setVisible(true);
            modelo.getVistaMuller().dispose();
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
        if(e.getComponent().equals(modelo.getVistaMuller().btnResolver)){
            modelo.getVistaMuller().btnResolver.setBackground(new Color(51,51,255));
        } else if (e.getComponent().equals(modelo.getVistaMuller().btnLimpiar)) {
            modelo.getVistaMuller().btnLimpiar.setBackground(new Color(51,51,255)); 
        } else if (e.getComponent().equals(modelo.getVistaMuller().btnVolver)){
            modelo.getVistaMuller().btnVolver.setBackground(new Color(51,51,255)); 
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getComponent().equals(modelo.getVistaMuller().btnResolver)){
            modelo.getVistaMuller().btnResolver.setBackground(new Color(0,102,204));
        } else if (e.getComponent().equals(modelo.getVistaMuller().btnLimpiar)) {
            modelo.getVistaMuller().btnLimpiar.setBackground(new Color(0,102,204)); 
        } else if (e.getComponent().equals(modelo.getVistaMuller().btnVolver)){
            modelo.getVistaMuller().btnVolver.setBackground(new Color(0,102,204)); 
        }
    }

    private void inputIsEmpty() {
        if (modelo.getVistaMuller().txtFunction.getText().isEmpty()
                || modelo.getVistaMuller().txtX1.getText().isEmpty()
                || modelo.getVistaMuller().txtX2.getText().isEmpty()
                || modelo.getVistaMuller().txtXo.getText().isEmpty()) {
            JOptionPane.showInternalMessageDialog(null, "Por favor debe de ingresar todos los datos", "ERROR \"DATOS VACIOS\"", JOptionPane.ERROR_MESSAGE);
        } else {
            captureData();
        }
    }
    
    private void captureData(){
        double X0 = Double.parseDouble(modelo.getVistaMuller().txtXo.getText());
        double X1 = Double.parseDouble(modelo.getVistaMuller().txtX1.getText());
        double X2 = Double.parseDouble(modelo.getVistaMuller().txtX2.getText());
        String function = modelo.getVistaMuller().txtFunction.getText();
        
        addTable(X0, X1, X2, function);
        visibleElements();
        
    }

    //METODOS PARA DEFINIR LA TABLA DENTRO DEL JSCROLL
    public void tableSize(JTable tabla) {
        int rowHeight = tabla.getRowHeight();
        int rowCount = tabla.getRowCount();
        int headerHeight = tabla.getTableHeader().getPreferredSize().height;

        int alturaTotal = (rowHeight * rowCount) + headerHeight;
        tabla.setPreferredScrollableViewportSize(new Dimension(tabla.getPreferredSize().width, alturaTotal));
    }

    public void addTable(double X0, double X1, double X2, String function) {
        
        System.out.println("Gol 1");
        JTable nuevaTabla = new JTable(implementacion.calculateMuller(function, X0, X1, X2));
        tableSize(nuevaTabla);
        //Posible captura de error por si el modelo es null
        System.out.println(nuevaTabla.getRowCount());

        JScrollPane tableScroll = new JScrollPane(nuevaTabla);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Tabla de Newton" + (modelo.getVistaMuller().contenedorTablaMuller.getComponentCount() + 1)));

        modelo.getVistaMuller().contenedorTablaMuller.add(tableScroll);
        modelo.getVistaMuller().contenedorTablaMuller.revalidate();
        modelo.getVistaMuller().contenedorTablaMuller.repaint();
        captureX3(nuevaTabla);

    }

    public void captureX3(JTable nuevaTabla) {

        int filas = nuevaTabla.getRowCount();
        String x3 = nuevaTabla.getValueAt(filas - 1, 14).toString();
        System.out.println();

        modelo.getVistaMuller().txtRespuestaX3.setText(x3);
        

    }
    
    private void visibleElements(){
        modelo.getVistaMuller().btnLimpiar.setVisible(true);
        modelo.getVistaMuller().txtRespuestaX3.setVisible(true);
        modelo.getVistaMuller().labelX3.setVisible(true);
    }
    
    private void cleanData(){
        modelo.getVistaMuller().txtFunction.setText("");
        modelo.getVistaMuller().txtXo.setText("");
        modelo.getVistaMuller().txtX1.setText("");
        modelo.getVistaMuller().txtX2.setText("");
        modelo.getVistaMuller().txtRespuestaX3.setText("");
        modelo.getVistaMuller().txtRespuestaX3.setVisible(false);
        modelo.getVistaMuller().labelX3.setVisible(false);
        modelo.getVistaMuller().btnLimpiar.setVisible(false);
        modelo.getVistaMuller().contenedorTablaMuller.removeAll();
        modelo.getVistaMuller().contenedorTablaMuller.revalidate();
        modelo.getVistaMuller().contenedorTablaMuller.repaint();
    }
        

}
