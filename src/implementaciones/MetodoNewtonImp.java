package implementaciones;

import interfaces.IMetodoNewton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jhosu
 */
public class MetodoNewtonImp implements IMetodoNewton{

    @Override
    public String calculateFunction(String function) {
        return null;
    }

    @Override
    public String calculateDerivative(String function) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public double calculateTolerancia(double xRActual, double xRAnterior) {
        
        double tolerancia = Math.abs(xRActual - xRAnterior);
        return  tolerancia;
    }

    @Override
    public DefaultTableModel calculateNewton(double Xi, double fXi, double fxid, String function) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public double calculateXR(double Xi, double fXi, double fXid) {
        
        double resultXr = Xi - (fXi/fXid);
        
        return resultXr;
    }
    
}
