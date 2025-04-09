package controladores;

import implementaciones.MetodoBiseccionImp;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import modelos.ModeloMetodoBisecion;

public class ControladorMetodoBiseccion implements MouseListener {

    ModeloMetodoBisecion modelo;
    MetodoBiseccionImp implementacion = new MetodoBiseccionImp();

    public ControladorMetodoBiseccion(ModeloMetodoBisecion modelo) {
        this.modelo = modelo;
    }

    ButtonGroup MetodoSelec = new ButtonGroup();

//    public ButtonGroup seleccionMetodo(){
//        
//        return 
//    }
    //METODOS PARA LA INTERACCIÓN DEL MOUSE PARA LA VISTA
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent().equals(modelo.getVistaMetodBis().btnResolver)) {
            inputIsEmpty();
//            tipoMetodoSeleccionado();
        } else if (e.getComponent().equals(modelo.getVistaMetodBis().btnLimpiar)) {
            cleanDataInput();
        } else if (e.getComponent().equals(modelo.getVistaMetodBis().jrbSinIntervalos)) {
            tipoMetodoSeleccionado();
        } else if (e.getComponent().equals(modelo.getVistaMetodBis().jrbConIntervalos)) {
            tipoMetodoSeleccionado();
        }

        /*
        } else if (e.getComponent().equals(modelo.getVistaMetodBis().btnSalir)) {
            vistas.MenuMetodosCerrados panelMetodCerrados = new MenuMetodosCerrados();
            MostrarPanel(panelMetodCerrados);
         */
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getComponent().equals(modelo.getVistaMetodBis().btnResolver)) {
            modelo.getVistaMetodBis().btnResolver.setBackground(new Color(153, 255, 255));
        } else if (e.getComponent().equals(modelo.getVistaMetodBis().btnSalir)) {
            modelo.getVistaMetodBis().btnSalir.setBackground(new Color(153, 255, 255));
        } else if (e.getComponent().equals(modelo.getVistaMetodBis().btnLimpiar)) {
            modelo.getVistaMetodBis().btnLimpiar.setBackground(new Color(153, 255, 255));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

        if (e.getComponent().equals(modelo.getVistaMetodBis().btnResolver)) {
            modelo.getVistaMetodBis().btnResolver.setBackground(new Color(172, 229, 246));
        } else if (e.getComponent().equals(modelo.getVistaMetodBis().btnSalir)) {
            modelo.getVistaMetodBis().btnSalir.setBackground(new Color(172, 229, 246));
        } else if (e.getComponent().equals(modelo.getVistaMetodBis().btnLimpiar)) {
            modelo.getVistaMetodBis().btnLimpiar.setBackground(new Color(172, 229, 246));
        }
    }

    public void inputIsEmpty() {
        if (modelo.getVistaMetodBis().txtFA.getText().equals("")
                || modelo.getVistaMetodBis().txtFB.getText().equals("")
                || modelo.getVistaMetodBis().txtFuction.getText().equals("")
                || oneSelected() == false) {
            JOptionPane.showInternalMessageDialog(null, "Por favor debe de ingresar todos los datos", "ERROR \"FALTA DE DATOS\"", JOptionPane.ERROR_MESSAGE);
        } else {
            capturaDatos();
        }
    }

    public void inputIsEmptySinIntervalos() {
        if (modelo.getVistaMetodBis().txtFA.getText().equals("")
                || modelo.getVistaMetodBis().txtFB.getText().equals("")
                || modelo.getVistaMetodBis().txtFuction.getText().equals("")) {
            JOptionPane.showInternalMessageDialog(null, "Por favor debe de ingresar todos los datos", "ERROR \"FALTA DE DATOS\"", JOptionPane.ERROR_MESSAGE);
        } else {
            capturaDatosSinIntervalos();
            System.out.println("Hola captura datos sin intervalos");
        }
    }

    //Metodos para capturar los datos ingresados
    public void capturaDatos() {
        double inputIntervalA = Double.parseDouble(modelo.getVistaMetodBis().txtFA.getText());
        double inputIntervalB = Double.parseDouble(modelo.getVistaMetodBis().txtFB.getText());
        String inputFuction = modelo.getVistaMetodBis().txtFuction.getText();

        implementacion.DiferenciarValores(inputFuction);
        implementacion.ValoresNotacionPostFija(implementacion.DiferenciarValores(inputFuction));

        double outputIntervalfA = implementacion.ResolverFuncion(implementacion.ValoresNotacionPostFija(implementacion.DiferenciarValores(inputFuction)), inputIntervalA);
        double outputIntervalfB = implementacion.ResolverFuncion(implementacion.ValoresNotacionPostFija(implementacion.DiferenciarValores(inputFuction)), inputIntervalB);

        boolean respMakeBis = implementacion.compareInterva(inputIntervalA, inputIntervalB, inputFuction);

        if (modelo.getVistaMetodBis().jrbSinIntervalos.isSelected() == true) {
            capturaDatosSinIntervalos();
            visibleElements();
            modelo.getVistaMetodBis().btnResolver.setVisible(false);
        } else {
            if (respMakeBis == true) {
                JOptionPane.showMessageDialog(null, "No se puede resolver por este método", "ERROR", JOptionPane.ERROR_MESSAGE);
                modelo.getVistaMetodBis().btnLimpiar.setVisible(true);
            } else {
                addTabla(inputIntervalA, inputIntervalB, outputIntervalfA, outputIntervalfB, inputFuction);
                visibleElements();
                modelo.getVistaMetodBis().btnResolver.setVisible(false);
            }
        }

//        if (respMakeBis == true) {
//            JOptionPane.showMessageDialog(null, "No se puede resolver por este método", "ERROR", JOptionPane.ERROR_MESSAGE);
//            modelo.getVistaMetodBis().btnLimpiar.setVisible(true);
//        } else {
//            addTabla(inputIntervalA, inputIntervalB, outputIntervalfA, outputIntervalfB, inputFuction);
//            visibleElements();
//            modelo.getVistaMetodBis().btnResolver.setVisible(false);
//        }
//compareOutput(outputIntervalA, outputIntervalB, inputIntervalA, inputIntervalB, inputFuction);
//        tipoMetodoSeleccionado();
    }

    public void capturaDatosSinIntervalos() {
        String inputXMax = modelo.getVistaMetodBis().txtFA.getText();
        String inputXMin = modelo.getVistaMetodBis().txtFB.getText();
        String inputFuction = modelo.getVistaMetodBis().txtFuction.getText();

        searchIntervalos(inputXMin, inputXMax, inputFuction);;
    }

    /*Método que compara el resultado de los intervalos en la función, determina
    si se puede hacer el método.
     */
    public void compareOutput(double fa, double fb, double intervaA, double intervaB, String fuction) {

        if (fa <= 0 && fb <= 0 || fa > 0 && fb > 0) {
            JOptionPane.showMessageDialog(null, "No se puede resolver por este método", "EROR", JOptionPane.ERROR_MESSAGE);
        } else {
            cleanDataInput();
            modelo.getVistaMetodBis().txtResultadoFA.setText(String.valueOf(fa));
            modelo.getVistaMetodBis().txtResultadoFB.setText(String.valueOf(fb));
            modelo.getVistaMetodBis().jComBResultadoFA.addItem(String.valueOf(fa));
            modelo.getVistaMetodBis().jComBResultFB.addItem(String.valueOf(fb));
//            modelo.getVistaMetodBis().jComBResultXR.addItem(implementacion.getResultXR());
            //implementacion.calculateMethodBiseccion(intervaA, intervaB, fa, fb, fuction);
            addTabla(intervaA, intervaB, fa, fb, fuction);

        }
    }

    public void tipoMetodoSeleccionado() {

        if (modelo.getVistaMetodBis().jrbConIntervalos.isSelected()) {
            JOptionPane.showMessageDialog(null, "Método con intervalos", "Método Bisección", JOptionPane.WARNING_MESSAGE);
            modelo.getVistaMetodBis().jLIntervaAStart.setText("Intervalo F(a)");
            modelo.getVistaMetodBis().jLIntervaBEnd.setText("Intervalo F(b)");

//            inputIsEmpty();
        } else if (modelo.getVistaMetodBis().jrbSinIntervalos.isSelected()) {
            JOptionPane.showMessageDialog(null, "Método sin intervalos", "Método Bisección", JOptionPane.WARNING_MESSAGE);
            modelo.getVistaMetodBis().jLIntervaAStart.setText("X desde:");
            modelo.getVistaMetodBis().jLIntervaBEnd.setText("X hasta:");

//            inputIsEmptySinIntervalos();
        } //else if (!modelo.getVistaMetodBis().jrbConIntervalos.isSelected() && !modelo.getVistaMetodBis().jrbSinIntervalos.isSelected()) {
        //JOptionPane.showMessageDialog(null, "Por favor selecciona si el método tiene o no tiene intervalos", "Selecciona el tipo de método", JOptionPane.WARNING_MESSAGE);
        //}

    }

    //Método que realiza la tabla para encontrar los intervalos(Metodo sin intervalos)
    public void searchIntervalos(String xMax, String xMin, String fuction) {

        int xMaxInterva = Integer.parseInt(xMax);
        int xMinInterva = Integer.parseInt(xMin);

        List<Double> valoresX = new ArrayList<>();
        List<Double> valoresY = new ArrayList<>();

        for (int x = xMinInterva; x <= xMaxInterva; x++) {
            double valueY = implementacion.ResolverFuncion(implementacion.ValoresNotacionPostFija(implementacion.DiferenciarValores(fuction)), x);
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
                addTabla(x1, x2, y1, y2, fuction);
            }
        }

    }

    //Metodos para definir la tabla y agregar los datos
    public void tamañoDeTabla(JTable tabla) {
        int rowHeight = tabla.getRowHeight();
        int rowCount = tabla.getRowCount();
        int headerHeight = tabla.getTableHeader().getPreferredSize().height;

        int alturaTotal = (rowHeight * rowCount) + headerHeight;
        tabla.setPreferredScrollableViewportSize(new Dimension(tabla.getPreferredSize().width, alturaTotal));

    }

    public void addTabla(double intervaA, double intervaB, double fa, double fb, String fuction) {

        JTable nuevaTabla = new JTable(implementacion.calculateBiseccion(intervaA, intervaB, fa, fb, fuction));
        tamañoDeTabla(nuevaTabla);

        System.out.println(nuevaTabla.getRowCount());
        System.out.println(nuevaTabla.getColumnCount() + "Columnas");

        JScrollPane tableScroll = new JScrollPane(nuevaTabla);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Tabla De Bisección" + (modelo.getVistaMetodBis().jPContenedorTablas.getComponentCount() + 1)));

        modelo.getVistaMetodBis().jPContenedorTablas.add(tableScroll);
        modelo.getVistaMetodBis().jPContenedorTablas.revalidate();
        modelo.getVistaMetodBis().jPContenedorTablas.repaint();

        String XrCapture = captureXr(nuevaTabla);
        modelo.getVistaMetodBis().jComBResultXR.addItem(XrCapture);

    }

    public boolean oneSelected() {
        boolean conIntervalos = modelo.getVistaMetodBis().jrbConIntervalos.isSelected();
        boolean sinIntervalos = modelo.getVistaMetodBis().jrbSinIntervalos.isSelected();
        boolean selected;
        if (conIntervalos == true || sinIntervalos == true) {
            selected = true;
        } else {
            selected = false;
        }
        return selected;
    }

    public void visibleElements() {
        modelo.getVistaMetodBis().btnLimpiar.setVisible(true);
        modelo.getVistaMetodBis().jLabelResultadoXR.setVisible(true);
        modelo.getVistaMetodBis().jComBResultXR.setVisible(true);
//        modelo.getVistaMetodBis().jLabelResultadoFA.setVisible(true);
//        modelo.getVistaMetodBis().jComBResultadoFA.setVisible(true);
//        modelo.getVistaMetodBis().jLabelResultadoFB.setVisible(true);
//        modelo.getVistaMetodBis().jComBResultFB.setVisible(true);
    }

    public String captureXr(JTable nuevaTabla) {
        int filas = nuevaTabla.getRowCount();
        String xr = nuevaTabla.getValueAt(filas - 1, 5).toString();

        return xr;
    }

    //METODO PARA LIMPIAR LOS DATOS
    public void cleanDataInput() {
        modelo.getVistaMetodBis().txtFA.setText("");
        modelo.getVistaMetodBis().txtFB.setText("");
        modelo.getVistaMetodBis().txtFuction.setText("");
        modelo.getVistaMetodBis().btnLimpiar.setVisible(false);
        modelo.getVistaMetodBis().jLabelResultadoXR.setVisible(false);
        modelo.getVistaMetodBis().jComBResultXR.setVisible(false);
        modelo.getVistaMetodBis().jComBResultXR.removeAllItems();
        modelo.getVistaMetodBis().jComBResultXR.revalidate();
        modelo.getVistaMetodBis().jComBResultXR.repaint();
        modelo.getVistaMetodBis().jPContenedorTablas.removeAll();
        modelo.getVistaMetodBis().jPContenedorTablas.revalidate();
        modelo.getVistaMetodBis().jPContenedorTablas.repaint();
        modelo.getVistaMetodBis().btnResolver.setVisible(true);
    }

    //Metodo para llamar al JPaneForm
    public void MostrarPanel(JPanel p) {
        p.setSize(1280, 720);
        p.setLocation(0, 0);

        modelo.getVistaMetodBis().ContenedorMetodoBis.removeAll();
        modelo.getVistaMetodBis().ContenedorMetodoBis.add(p, BorderLayout.CENTER);
        modelo.getVistaMetodBis().ContenedorMetodoBis.revalidate();
        modelo.getVistaMetodBis().ContenedorMetodoBis.repaint();
    }

}
