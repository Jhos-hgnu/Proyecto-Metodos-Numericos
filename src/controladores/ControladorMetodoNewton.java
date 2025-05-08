package controladores;

import implementaciones.MetodoNewtonImp;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import modelos.ModeloMetodoNewton;

public class ControladorMetodoNewton implements MouseListener {

    ModeloMetodoNewton modelo;
    MetodoNewtonImp implementacion = new MetodoNewtonImp();

    public ControladorMetodoNewton(ModeloMetodoNewton modelo) {
        this.modelo = modelo;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent().equals(modelo.getVistaNewton().btnResolver)) {
            inputsIsEmpty();
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
        } else if (e.getComponent().equals(modelo.getVistaNewton().btnLimpiar)) {
            modelo.getVistaNewton().btnLimpiar.setBackground(new Color(153, 255, 255));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

        if (e.getComponent().equals(modelo.getVistaNewton().btnResolver)) {
            modelo.getVistaNewton().btnResolver.setBackground(new Color(172, 229, 246));
        } else if (e.getComponent().equals(modelo.getVistaNewton().btnLimpiar)) {
            modelo.getVistaNewton().btnLimpiar.setBackground(new Color(172, 229, 246));
        }
    }

    //METODO PARA OBTENER LOS DATOS DE LOS INPUTS
    public void captureDatos() {
        double inputXI = Double.parseDouble(modelo.getVistaNewton().txtXi.getText());
        String inputFuction = modelo.getVistaNewton().txtFuction.getText();
        addTable(inputXI, inputFuction);
        visibleElements();
        modelo.getVistaNewton().btnLimpiar.setVisible(true);
        modelo.getVistaNewton().btnResolver.setVisible(false);

    }

    public void inputsIsEmpty() {
        if (modelo.getVistaNewton().txtFuction.getText().isEmpty() || modelo.getVistaNewton().txtXi.getText().isEmpty()) {
            JOptionPane.showInternalMessageDialog(null, "Por favor debe de ingresar todos los datos", "ERROR \"DATOS VACIOS\"", JOptionPane.ERROR_MESSAGE);
        } else {
            captureDatos();
        }

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
        modelo.getVistaNewton().btnResolver.setVisible(true);
    }
    
     //METODOS PARA DEFINIR LA TABLA DENTRO DEL JSCROLL
    public void tableSize(JTable tabla) {
        int rowHeight = tabla.getRowHeight();
        int rowCount = tabla.getRowCount();
        int headerHeight = tabla.getTableHeader().getPreferredSize().height;

        int alturaTotal = (rowHeight * rowCount) + headerHeight;
        tabla.setPreferredScrollableViewportSize(new Dimension(tabla.getPreferredSize().width, alturaTotal));
    }
    
    public void addTable(double Xi, String function){
        
        JTable nuevaTabla = new JTable(implementacion.calculateNewton(Xi, function));
        tableSize(nuevaTabla);
        
        System.out.println(nuevaTabla.getRowCount());
        
        JScrollPane tableScroll = new JScrollPane(nuevaTabla);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Tabla de Newton" + (modelo.getVistaNewton().jScrollTablaMN.getComponentCount() + 1) ));
        
        modelo.getVistaNewton().jScrollTablaMN.add(tableScroll);
        modelo.getVistaNewton().jScrollTablaMN.revalidate();
        modelo.getVistaNewton().jScrollTablaMN.repaint();
        captureXr(nuevaTabla);
        
    }
        
    public void captureXr(JTable nuevaTabla){
        
        int filas = nuevaTabla.getRowCount();
        String xr = nuevaTabla.getValueAt(filas -1, 4).toString();
        System.out.println(xr);
        
        modelo.getVistaNewton().jComboBXR.addItem(xr);
       
    }
      
}
