package controladores;

import implementaciones.MetodoRaicesMultiplesImp;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
        if (e.getComponent().equals(modelo.getVistaRM().btnResolver)){
        modelo.getVistaRM().btnResolver.setBackground(new Color(153,255,255)); 
        }else if (e.getComponent().equals(modelo.getVistaRM().btnLimpiar)){
            modelo.getVistaRM().btnLimpiar.setBackground(new Color(153,255,255));
        
        }
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
        if (e.getComponent().equals(modelo.getVistaRM().btnResolver)){
            modelo.getVistaRM().btnResolver.setBackground(new Color (172,229,246));
        }else if (e.getComponent().equals(modelo.getVistaRM().btnLimpiar)){
            modelo.getVistaRM().btnLimpiar.setBackground(new Color(172,229,246));
        }
      
    
    }
    
    public void capturaDatos(){
        
        modelo.getVistaRM().txtFuncion.getText();
        {
           double xi = Double.parseDouble(modelo.getVistaRM().txtFuncion.getText());
           String funcion = modelo.getVistaRM().txtFuncion.getText();
           
           MetodoRaicesMultiplesImp metodo = new MetodoRaicesMultiplesImp();
           
            DefaultTableModel ModeloTabla = metodo.calculateRaicesMultiples(xi, funcion);
           
         modelo.getVistaRM().btnResolver.setVisible(false);
        
        }
        
       // modelo.getVistaRM().txtFuncion.getText();
       
        
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
      String XrCapture =  captureXr (nuevaTabla);
      modelo.getVistaRM().jCombResultXR.addItem(XrCapture);
      
     
    }
    
    public void visivleElements (){
        modelo.getVistaRM().btnLimpiar.setVisible(true);
        modelo.getVistaRM().jLabelResultadoXR.setVisible(true);
        modelo.getVistaRM().jCombResultXR.setVisible(true);
    
    }
    
     public String captureXr (JTable nuevaTabla){
         int filas = nuevaTabla.getRowCount();
         String xr = nuevaTabla.getValueAt (filas - 1, 5).toString();
         
         return xr;
         
     
     } 
       
    
    
    
}
