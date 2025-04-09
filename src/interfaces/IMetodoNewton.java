/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jhosu
 */
public interface IMetodoNewton {
    
    public String calculateFunction(String function);
    public String calculateDerivative(String function);
    public double calculateTolerancia(double xRActual, double xRAnterior);
    public double calculateXR(double Xi,double fXi, double fXid);
    public DefaultTableModel calculateNewton (double Xi, double fXi, double fxid, String function);
    
    
}
