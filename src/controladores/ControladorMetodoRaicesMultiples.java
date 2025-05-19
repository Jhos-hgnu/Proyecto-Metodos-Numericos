package controladores;

import implementaciones.MetodoRaicesMultiplesImp;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
        if(e.getComponent().equals(modelo.getVistaRM().btnResolver)){
            inputIsEmpty();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void capturaDatos(){
        
        modelo.getVistaRM().txtFuncion.getText();
       
        
    }
    
    public void inputIsEmpty(){
        
        if(modelo.getVistaRM().txtFuncion.getText().isEmpty()){
                       JOptionPane.showInternalMessageDialog(null, "Por favor debe de ingresar todos los datos", "ERROR \"DATOS VACIOS\"", JOptionPane.ERROR_MESSAGE);
 
        } else {
            capturaDatos();
        }
        
    }
    
    public void cleanData(){
        
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
        
        JTable nuevaTabla = new JTable(implementacion.calculateRaicesMultiples(Xi, function));
        tableSize(nuevaTabla);
        
        System.out.println(nuevaTabla.getRowCount());
        
        JScrollPane tableScroll = new JScrollPane(nuevaTabla);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Tabla de Newton" + (modelo.getVistaRM().tableContainerRM.getComponentCount() + 1) ));
        
        modelo.getVistaRM().tableContainerRM.add(tableScroll);
        modelo.getVistaRM().tableContainerRM.revalidate();
        modelo.getVistaRM().tableContainerRM.repaint();
//        captureXr(nuevaTabla);
//        
    }
    
    
    
    
}
