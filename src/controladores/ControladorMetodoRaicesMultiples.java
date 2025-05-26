package controladores;

import implementaciones.MetodoRaicesMultiplesImp;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloMetodoRaicesMultiples;

/**
 *
 * @author jhosu
 */
public class ControladorMetodoRaicesMultiples implements MouseListener {

    MetodoRaicesMultiplesImp implementacion = new MetodoRaicesMultiplesImp();

    ModeloMetodoRaicesMultiples modelo;

    public ControladorMetodoRaicesMultiples(ModeloMetodoRaicesMultiples modelo) {
        this.modelo = modelo;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent().equals(modelo.getVistaRM().btnResolver)) {
            btnGroupSelected();
        } else if (e.getComponent().equals(modelo.getVistaRM().btnLimpiar)) {
            cleanData();
        } else if (e.getComponent().equals(modelo.getVistaRM().radBtnConIntervalo)) {
            radBtnSelected();
        } else if (e.getComponent().equals(modelo.getVistaRM().radBtnSinIntervalo)) {
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
        if (e.getComponent().equals(modelo.getVistaRM().btnResolver)) {
            modelo.getVistaRM().btnResolver.setBackground(new Color(0, 102, 255));
        } else if (e.getComponent().equals(modelo.getVistaRM().btnLimpiar)) {
            modelo.getVistaRM().btnLimpiar.setBackground(new Color(0, 102, 255));

        }

    }

    @Override
    public void mouseExited(MouseEvent e) {

        if (e.getComponent().equals(modelo.getVistaRM().btnResolver)) {
            modelo.getVistaRM().btnResolver.setBackground(new Color(0, 153, 204));
        } else if (e.getComponent().equals(modelo.getVistaRM().btnLimpiar)) {
            modelo.getVistaRM().btnLimpiar.setBackground(new Color(0, 153, 204));
        }

    }

    private void btnGroupSelected() {
        boolean conIntervalos = modelo.getVistaRM().radBtnConIntervalo.isSelected();
//        boolean sinIntervalos = modelo.getVistaRM().radBtnSinIntervalo.isSelected();

        if (!conIntervalos == true) {
            inputIsEmptySinIntervalo();
        } else {
            inputIsEmptyConIntervalo();
        }
    }

    public void inputIsEmptyConIntervalo() {

        if (modelo.getVistaRM().txtFuncion.getText().isEmpty()) {
            JOptionPane.showInternalMessageDialog(null, "Por favor debe de ingresar todos los datos CON", "ERROR \"DATOS VACIOS\"", JOptionPane.ERROR_MESSAGE);

        } else {
            capturaDatosCon();
        }

    }

    private void inputIsEmptySinIntervalo() {
        if (modelo.getVistaRM().txtFuncion.getText().isEmpty()) {
            JOptionPane.showInternalMessageDialog(null, "Por favor debe de ingresar todos los datos SINN", "ERROR \"DATOS VACIOS\"", JOptionPane.ERROR_MESSAGE);

        } else {
            capturaDatosSin();
        }
    }

    public void capturaDatosCon() {

        double xi = Double.parseDouble(modelo.getVistaRM().txtXI.getText());
        String function = modelo.getVistaRM().txtFuncion.getText();
        addTable(xi, function);
        visibleElements();
    }

    private void capturaDatosSin() {

        String xDesde = modelo.getVistaRM().txtXI.getText();
        String xHasta = modelo.getVistaRM().txtHastaX.getText();
        String function = modelo.getVistaRM().txtFuncion.getText();

        searchIntervalos(xDesde, xHasta, function);
        visibleElements();

    }

    private void radBtnSelected() {
        boolean conIntervalos = modelo.getVistaRM().radBtnConIntervalo.isSelected();
        boolean sinIntervalos = modelo.getVistaRM().radBtnSinIntervalo.isSelected();

        if (conIntervalos) {
            modelo.getVistaRM().labelXi.setVisible(true);
            modelo.getVistaRM().labelXi.setText("Xi");
            modelo.getVistaRM().txtXI.setVisible(true);
            modelo.getVistaRM().btnResolver.setVisible(true);
            modelo.getVistaRM().labelHastaX.setVisible(false);
            modelo.getVistaRM().txtHastaX.setVisible(false);
        } else if (sinIntervalos) {
            modelo.getVistaRM().labelXi.setText("X Desde:");
            modelo.getVistaRM().labelXi.setVisible(true);
            modelo.getVistaRM().txtXI.setVisible(true);
            modelo.getVistaRM().labelHastaX.setVisible(true);
            modelo.getVistaRM().txtHastaX.setVisible(true);
            modelo.getVistaRM().btnResolver.setVisible(true);
        }

    }

    public void cleanData() {
        modelo.getVistaRM().labelXi.setVisible(false);
        modelo.getVistaRM().txtXI.setVisible(false);
        modelo.getVistaRM().txtXI.setText("");
        modelo.getVistaRM().labelHastaX.setVisible(false);
        modelo.getVistaRM().txtHastaX.setVisible(false);
        modelo.getVistaRM().txtHastaX.setText("");
        modelo.getVistaRM().btnLimpiar.setVisible(false);
        modelo.getVistaRM().btnResolver.setVisible(false);
        modelo.getVistaRM().jLabelResultadoXR.setVisible(false);
        modelo.getVistaRM().jCombResultXR.removeAllItems();
        modelo.getVistaRM().jCombResultXR.revalidate();
        modelo.getVistaRM().jCombResultXR.repaint();
        modelo.getVistaRM().jCombResultXR.setVisible(false);
        modelo.getVistaRM().txtFuncion.setText("");
        modelo.getVistaRM().tableContainerRM.removeAll();
        modelo.getVistaRM().tableContainerRM.revalidate();
        modelo.getVistaRM().tableContainerRM.repaint();

    }

    //METODOS PARA DEFINIR LA TABLA DENTRO DEL JSCROLL
    public void tableSize(JTable tabla) {
        int rowHeight = tabla.getRowHeight();
        int rowCount = tabla.getRowCount();
        int headerHeight = tabla.getTableHeader().getPreferredSize().height;

        int alturaTotal = (rowHeight * rowCount) + headerHeight;
        tabla.setPreferredScrollableViewportSize(new Dimension(tabla.getPreferredSize().width, alturaTotal));
    }

    public void addTable(double Xi, String function) {

        JTable nuevaTabla = new JTable(implementacion.calculateRaicesMultiples(Xi, function));
        tableSize(nuevaTabla);

        System.out.println(nuevaTabla.getRowCount());

        JScrollPane tableScroll = new JScrollPane(nuevaTabla);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Tabla Raices Múltiples" + (modelo.getVistaRM().tableContainerRM.getComponentCount() + 1)));

        modelo.getVistaRM().tableContainerRM.add(tableScroll);
        modelo.getVistaRM().tableContainerRM.revalidate();
        modelo.getVistaRM().tableContainerRM.repaint();
        String XrCapture = captureXr(nuevaTabla);
        modelo.getVistaRM().jCombResultXR.addItem(XrCapture);

    }

    public void visibleElements() {
        modelo.getVistaRM().btnLimpiar.setVisible(true);
        modelo.getVistaRM().jLabelResultadoXR.setVisible(true);
        modelo.getVistaRM().jCombResultXR.setVisible(true);

    }

    public String captureXr(JTable nuevaTabla) {
        int filas = nuevaTabla.getRowCount();
        String xr = nuevaTabla.getValueAt(filas - 1, 5).toString();

        return xr;
    }

    //Método que realiza la tabla para encontrar los intervalos(Metodo sin intervalos)
    public void searchIntervalos(String xMax, String xMin, String fuction) {

        int xMaxInterva = Integer.parseInt(xMax);
        int xMinInterva = Integer.parseInt(xMin);

        List<Double> valoresX = new ArrayList<>();
        List<Double> valoresY = new ArrayList<>();

        for (int x = xMinInterva; x <= xMaxInterva; x++) {
            double valueY = implementacion.calculateFunction(fuction, x);
            valoresX.add((double) x);
            valoresY.add(valueY);
            System.out.println("X = " + x + " Y = " + valueY);
        }

        for (int i = 1; i < valoresY.size(); i++) {
            if (valoresY.get(i) * valoresY.get(i - 1) < 0) {

                double y2 = valoresY.get(i);
                double y1 = valoresY.get(i - 1);

                double x1 = valoresX.get(i - 1);
                double x2 = valoresX.get(i);
                System.out.println("Intervalo encontrado: [" + x1 + ", " + x2 + "]");
//                implementacion.calculateBiseccion(x1, x2, y1, y2, fuction);
                addTable(x1, fuction);
                addTable(x2, fuction);
            }
        }

    }

}
