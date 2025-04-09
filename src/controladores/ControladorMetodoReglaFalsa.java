package controladores;

import implementaciones.MetodoReglaFalsaImp;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import modelos.ModeloMetodoReglaFalsa;

/**
 *
 * @author jhosu
 */
public class ControladorMetodoReglaFalsa implements MouseListener {

    ModeloMetodoReglaFalsa modelo;
    MetodoReglaFalsaImp implementacion = new MetodoReglaFalsaImp();

    public ControladorMetodoReglaFalsa(ModeloMetodoReglaFalsa modelo) {
        this.modelo = modelo;
    }

   
    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getComponent().equals(modelo.getVistaReglaFalsa().btnResolver)) {
            inputIsEmpty();
        } else if(e.getComponent().equals(modelo.getVistaReglaFalsa().btnLimpiar)){
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
        if (e.getComponent().equals(modelo.getVistaReglaFalsa().btnResolver)) {
            modelo.getVistaReglaFalsa().btnResolver.setBackground(new Color(153, 255, 255));
        } else if(e.getComponent().equals(modelo.getVistaReglaFalsa().btnLimpiar)){
            modelo.getVistaReglaFalsa().btnLimpiar.setBackground(new Color(153, 255, 255));
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getComponent().equals(modelo.getVistaReglaFalsa().btnResolver)) {
            modelo.getVistaReglaFalsa().btnResolver.setBackground(new Color(172, 229, 246));
        } else if(e.getComponent().equals(modelo.getVistaReglaFalsa().btnLimpiar)){
            modelo.getVistaReglaFalsa().btnLimpiar.setBackground(new Color(172, 229, 246));
        }

    }

    //METODOS PARA LLEVAR A CABO VALIDACIONES DE LOS DATOS (Datos vacios)
    public void inputIsEmpty() {

        if (modelo.getVistaReglaFalsa().txtIntervaloFA.getText().equals("")
                || modelo.getVistaReglaFalsa().txtIntervaloFB.getText().equals("")
                || modelo.getVistaReglaFalsa().txtFuction.getText().equals("")) {
            JOptionPane.showInternalMessageDialog(null, "Por favor debe de ingresar todos los datos", "ERROR \"DATOS VACIOS\"", JOptionPane.ERROR_MESSAGE);
        } else {
            captureDatos();
        }

    }

    
    //MÉTODO PARA CAPTURAR LOS DATOS INGRESADOS
    //ESTE MÉTODO UTILIZA OTROS MÉTODOS DE LA CLASE IMPLEMENTACIÓN(MetodoReglaFalsaImp) Y DE ESTA MISMA CLASE(ControladorMetodoReglaFalsa)
    public void captureDatos() {
        double inputIntervalA = Double.parseDouble(modelo.getVistaReglaFalsa().txtIntervaloFA.getText());
        double inputIntervalB = Double.parseDouble(modelo.getVistaReglaFalsa().txtIntervaloFB.getText());
        String inputFuction = modelo.getVistaReglaFalsa().txtFuction.getText();
        
        System.out.println("Hola en captura datos");
        boolean respMakeRF = implementacion.compareInterva(inputIntervalA, inputIntervalB, inputFuction);
       
        if (respMakeRF == true) {
            JOptionPane.showMessageDialog(null, "No se puede resolver por este método", "ERROR", JOptionPane.ERROR_MESSAGE);
            modelo.getVistaReglaFalsa().btnLimpiar.setVisible(true);
        } else {
            addTable(inputIntervalA, inputIntervalB, implementacion.guardarIntervaFA(inputIntervalA, inputFuction), implementacion.guardarIntervaFB(inputIntervalB, inputFuction), inputFuction);
            visibleELements();
            modelo.getVistaReglaFalsa().btnResolver.setVisible(false);
        }

    }

    //METODOS PARA DEFINIR LA TABLA DENTRO DEL JSCROLL
    public void tableSize(JTable tabla) {
        int rowHeight = tabla.getRowHeight();
        int rowCount = tabla.getRowCount();
        int headerHeight = tabla.getTableHeader().getPreferredSize().height;

        int alturaTotal = (rowHeight * rowCount) + headerHeight;
        tabla.setPreferredScrollableViewportSize(new Dimension(tabla.getPreferredSize().width, alturaTotal));
    }

    public void addTable(double intervaA, double intervaB, double fa, double fb, String fuction) {

        JTable nuevaTabla = new JTable(implementacion.calculateBiseccion(intervaA, intervaB, fa, fb, fuction));
        tableSize(nuevaTabla);

        System.out.println(nuevaTabla.getRowCount());
        System.out.println(nuevaTabla.getColumnCount() + "Columnas");

        JScrollPane tableScroll = new JScrollPane(nuevaTabla);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Tabla De Bisección" + (modelo.getVistaReglaFalsa().jPaneScrollTablaRF.getComponentCount() + 1)));
        //jScrollPTabla
        modelo.getVistaReglaFalsa().jPaneScrollTablaRF.add(tableScroll);
        modelo.getVistaReglaFalsa().jPaneScrollTablaRF.revalidate();
        modelo.getVistaReglaFalsa().jPaneScrollTablaRF.repaint();
        
        captureXr(nuevaTabla);
              
    }
    
    
    //MÉTODO PARA VOLVER VISIBLE LOS ELEMENTOS REQUERIDOS
    public void visibleELements (){
        modelo.getVistaReglaFalsa().btnLimpiar.setVisible(true);
        modelo.getVistaReglaFalsa().jLabelXRObtenido.setVisible(true);
        modelo.getVistaReglaFalsa().jComboBXR.setVisible(true);
    }
    
    
    //METODO PARA CAPTURAR EL ÚLTIMO XR DE LA TABLA DE REGLA FALSA
    public void captureXr(JTable nuevaTabla){
        int filas = nuevaTabla.getRowCount();
        String xr = nuevaTabla.getValueAt(filas -1, 5).toString();
        
        modelo.getVistaReglaFalsa().jComboBXR.addItem(xr);
    }
    
    
    //METODO PARA LIMPIAR LOS DATOS
    public void cleanData() {
        modelo.getVistaReglaFalsa().txtFuction.setText("");
        modelo.getVistaReglaFalsa().txtIntervaloFA.setText("");
        modelo.getVistaReglaFalsa().txtIntervaloFB.setText("");
        modelo.getVistaReglaFalsa().btnLimpiar.setVisible(false);
        modelo.getVistaReglaFalsa().jLabelXRObtenido.setVisible(false);
        modelo.getVistaReglaFalsa().jComboBXR.setVisible(false);
        modelo.getVistaReglaFalsa().jComboBXR.removeItem(modelo.getVistaReglaFalsa().jComboBXR.getItemAt(0));
        modelo.getVistaReglaFalsa().jComboBXR.revalidate();
        modelo.getVistaReglaFalsa().jComboBXR.repaint();
        modelo.getVistaReglaFalsa().jPaneScrollTablaRF.removeAll();
        modelo.getVistaReglaFalsa().jPaneScrollTablaRF.revalidate();
        modelo.getVistaReglaFalsa().jPaneScrollTablaRF.repaint();
        modelo.getVistaReglaFalsa().btnResolver.setVisible(true);
    }

}
