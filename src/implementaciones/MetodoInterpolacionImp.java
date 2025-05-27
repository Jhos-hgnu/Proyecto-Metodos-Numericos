/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

/**
 *
 * @author jhosu
 */
public class MetodoInterpolacionImp {
    
    public double calcularInterpolacionNewton(double X, double X1, double X2, double y1, double y2){
        
        double y = (((X - X1)/(X2 - X1)) * (y2 - y1)) + y1;
        return y;
    }
    
    
    public double calculateInterpolacionLangrange (double X, double Xo, double X1, double FXo, double FX1){
    
        double fx =( ((X - X1)/(Xo - X1)) * (FXo) )+ ( ((X - Xo)/(X1 - Xo)) * FX1 );
        return fx;
    }
    
}
