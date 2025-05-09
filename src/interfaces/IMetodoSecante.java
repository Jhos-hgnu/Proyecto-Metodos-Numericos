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
public interface IMetodoSecante {
    
    public double calculateTolerancia(double xRActual, double xRAnterior);
    public double calculateXR(double Xi1,double Xi, double fXi1, double fXi);
    public DefaultTableModel calculateSecante (double Xi1, double Xi, String function);
    public double calculateFunction(String function, double x);
    
}
