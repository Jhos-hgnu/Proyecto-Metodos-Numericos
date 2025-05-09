package controladores;

import implementaciones.MetodoSecanteImp;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import modelos.ModeloMetodoSecante;

public class ControladorMetodoSecante implements MouseListener{
    
    ModeloMetodoSecante modelo;
    MetodoSecanteImp implementacion = new MetodoSecanteImp();

    public ControladorMetodoSecante(ModeloMetodoSecante modelo) {
        this.modelo = modelo;
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (e.getComponent().equals(modelo.getVistaSecante().btnResolver)) {
            inputsIsEmpty();
            System.out.println("HOLA EN EL BTN RESOLVER");
        } else if (e.getComponent().equals(modelo.getVistaSecante().btnLimpiar)) {
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
        if (e.getComponent().equals(modelo.getVistaSecante().btnResolver)) {
            modelo.getVistaSecante().btnResolver.setBackground(new Color(153, 255, 255));
        } else if (e.getComponent().equals(modelo.getVistaSecante().btnLimpiar)) {
            modelo.getVistaSecante().btnLimpiar.setBackground(new Color(153, 255, 255));
        }
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
        if (e.getComponent().equals(modelo.getVistaSecante().btnResolver)) {
            modelo.getVistaSecante().btnResolver.setBackground(new Color(51,153,255));
        } else if (e.getComponent().equals(modelo.getVistaSecante().btnLimpiar)) {
            modelo.getVistaSecante().btnLimpiar.setBackground(new Color(51,153,255));
        }
        
    }
    
     public void inputsIsEmpty() {
        if (modelo.getVistaSecante().txtFuncion.getText().isEmpty() || modelo.getVistaSecante().txtXi.getText().isEmpty() || modelo.getVistaSecante().txtXi1.getText().isEmpty()) {
            JOptionPane.showInternalMessageDialog(null, "Por favor debe de ingresar todos los datos", "ERROR \"DATOS VACIOS\"", JOptionPane.ERROR_MESSAGE);
        } else {
            captureDatos();
        }
    }
    
    
    public void captureDatos(){
        double inputXi1 = Double.parseDouble(modelo.getVistaSecante().txtXi1.getText());
        double inputXi = Double.parseDouble(modelo.getVistaSecante().txtXi.getText());
        String inputFunction = modelo.getVistaSecante().txtFuncion.getText();
        addTable(inputXi1, inputXi, inputFunction);
        visibleElements();
         modelo.getVistaSecante().btnLimpiar.setVisible(true);
        modelo.getVistaSecante().btnResolver.setVisible(false);
    }
    
    public void tableSize(JTable tabla) {
        int rowHeight = tabla.getRowHeight();
        int rowCount = tabla.getRowCount();
        int headerHeight = tabla.getTableHeader().getPreferredSize().height;

        int alturaTotal = (rowHeight * rowCount) + headerHeight;
        tabla.setPreferredScrollableViewportSize(new Dimension(tabla.getPreferredSize().width, alturaTotal));
    }
    
    public void addTable(double Xi1, double Xi, String function){
        
        JTable nuevaTabla = new JTable(implementacion.calculateSecante(Xi1, Xi, function));
        tableSize(nuevaTabla);
        
        System.out.println(nuevaTabla.getRowCount());
        
        JScrollPane tableScroll = new JScrollPane(nuevaTabla);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Tabla de Secante" + (modelo.getVistaSecante().jScrollPaneTablaMS.getComponentCount() + 1) ));
        
        modelo.getVistaSecante().jScrollPaneTablaMS.add(tableScroll);
        modelo.getVistaSecante().jScrollPaneTablaMS.revalidate();
        modelo.getVistaSecante().jScrollPaneTablaMS.repaint();
        captureXr(nuevaTabla);
        
    }
    
     public void captureXr(JTable nuevaTabla){
        
        int filas = nuevaTabla.getRowCount();
        String xr = nuevaTabla.getValueAt(filas -1, 5).toString();
        System.out.println(xr);
        
        modelo.getVistaSecante().jComboBXR.addItem(xr);
       
    }
     
     
    public void visibleElements() {
        modelo.getVistaSecante().btnLimpiar.setVisible(true);
        modelo.getVistaSecante().jlabelResulXr.setVisible(true);
        modelo.getVistaSecante().jComboBXR.setVisible(true);

    } 
    
     
    public void cleanData() {
        modelo.getVistaSecante().txtFuncion.setText("");
        modelo.getVistaSecante().txtXi1.setText("");
        modelo.getVistaSecante().txtXi.setText("");
        modelo.getVistaSecante().btnLimpiar.setVisible(false);
        modelo.getVistaSecante().jlabelResulXr.setVisible(false);
        modelo.getVistaSecante().jComboBXR.setVisible(false);
        modelo.getVistaSecante().jComboBXR.removeAll();
        modelo.getVistaSecante().jComboBXR.revalidate();
        modelo.getVistaSecante().jComboBXR.repaint();
        modelo.getVistaSecante().jScrollPaneTablaMS.removeAll();
        modelo.getVistaSecante().jScrollPaneTablaMS.revalidate();
        modelo.getVistaSecante().jScrollPaneTablaMS.repaint();
        modelo.getVistaSecante().btnResolver.setVisible(true); 
    }
}
