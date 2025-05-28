/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;
import org.nfunk.jep.JEP;

/**
 *
 * @author jhosu
 */
public class MetodoMullerImp {
    
    
    public DefaultTableModel calculateMuller(String function, double Xo, double X1, double x2){
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"i","X0","X1","X2","F(X0)","F(X1)","F(X2)","h0","h1","d0","d1","a","b","c","X3","Tolerancia"});
        
        DecimalFormat formato = new DecimalFormat("#0.0000");
        double tolerancia = 1;
        int iteracion = 1;
        System.out.println("Hola desde calculateMuller");
        while(tolerancia > 0.001){
            double nuevoXo, nuevoX1, nuevoX2, nuevoFx0, nuevoFx1, nuevoFx2, nuevoh0, nuevoh1, nuevod0, nuevod1, nuevoA, nuevoB, nuevoC, nuevoX3;
            
            if(iteracion == 1){
                nuevoXo = Xo;
                nuevoX1 = X1;
                nuevoX2 = x2;
                nuevoFx0 = calculateFunction(function, nuevoXo);
                nuevoFx1 = calculateFunction(function, nuevoX1);
                nuevoFx2 = calculateFunction(function, nuevoX2);
                nuevoh0 = calculateHo(nuevoX1, nuevoXo);
                nuevoh1 = calculateH1(nuevoX2, nuevoX1);
                nuevod0 = calculateDo(nuevoFx1, nuevoFx0, nuevoX1, nuevoXo);
                nuevod1 = calculateD1(nuevoFx2, nuevoFx1, nuevoX2, nuevoX1);
                nuevoA = calculateA(nuevod1, nuevod0, nuevoh1, nuevoh0);
                nuevoB = calculateB(nuevoA, nuevoh1, nuevod1);
                nuevoC = nuevoFx2;
                nuevoX3 = calculateX3(nuevoX2, nuevoC, nuevoB, nuevoA);
                tolerancia = calculateTolerancia(nuevoX3, nuevoX2);
            } else {
                
                double previousX1 = Double.parseDouble(modelo.getValueAt(iteracion -2, 2).toString());
                double previousX2 = Double.parseDouble(modelo.getValueAt(iteracion -2, 3).toString());
                double previousX3 = Double.parseDouble(modelo.getValueAt(iteracion -2, 14).toString());
                nuevoXo = previousX1;
                nuevoX1 = previousX2;
                nuevoX2 = previousX3;
                nuevoFx0 = calculateFunction(function, nuevoXo);
                nuevoFx1 = calculateFunction(function, nuevoX1);
                nuevoFx2 = calculateFunction(function, nuevoX2);
                nuevoh0 = calculateHo(nuevoX1, nuevoXo);
                nuevoh1 = calculateH1(nuevoX2, nuevoX1);
                nuevod0 = calculateDo(nuevoFx1, nuevoFx0, nuevoX1, nuevoXo);
                nuevod1 = calculateD1(nuevoFx2, nuevoFx1, nuevoX2, nuevoX1);
                nuevoA = calculateA(nuevod1, nuevod0, nuevoh1, nuevoh0);
                nuevoB = calculateB(nuevoA, nuevoh1, nuevod1);
                nuevoC = nuevoFx2;
                nuevoX3 = calculateX3(nuevoX2, nuevoC, nuevoB, nuevoA);
                tolerancia = calculateTolerancia(nuevoX3, nuevoX2);
            }
            modelo.addRow(new Object[]{
                iteracion,
                formato.format(nuevoXo),
                formato.format(nuevoX1),
                formato.format(nuevoX2),
                formato.format(nuevoFx0),
                formato.format(nuevoFx1),
                formato.format(nuevoFx2),
                formato.format(nuevoh0),
                formato.format(nuevoh1),
                formato.format(nuevod0),
                formato.format(nuevod1),
                formato.format(nuevoA),
                formato.format(nuevoB),
                formato.format(nuevoC),
                formato.format(nuevoX3),
                formato.format(tolerancia)
            });
            
            iteracion ++;
        }
        
        
        return modelo;
    }
    
    
    public double calculateHo(double X1, double Xo){
        double ho = X1 - Xo;
        
        return ho;
    }
    
    public double calculateH1(double X2, double X1){
        
        double h1 = X2 - X1;
        
        return h1;
    }
    
    public double calculateDo(double fx1, double fxo, double X1, double Xo){
       
        double Do = (fx1 - fxo)/(X1 - Xo);
        
        return  Do;
    }
    
    
    public double calculateD1(double fx2, double fx1, double X2, double X1){
        
        double d1 = ((fx2-fx1)/(X2-X1));
        
        return d1;
    }
    
    public double calculateA(double d1, double d0, double h1, double h0){
        
        double a = (d1-d0)/(h1 + h0);
        
        return a; 
    }
    
    public double calculateB(double a, double h1, double d1){
        
        double b = (a * h1) + d1;
        
        return b;
    }
    
    public double calculateX3(double x2, double c, double b, double a){
        
        double X3;
        
        if(b < 0){
            X3 = x2 + ((-2*c)/(b - Math.sqrt(Math.pow(b,2) - 4*a*c))); 
        } else {
            X3 = x2 + ((-2*c)/(b + Math.sqrt(Math.pow(b,2) - 4*a*c)));
        }
        
        return X3;
    }
        
    public double calculateTolerancia(double X3, double X2){
        
        double ToleranciaE = Math.abs((X3 - X2)/X3);
        
        return ToleranciaE;
    }
    
    public double calculateFunction(String function, double x) {

        JEP parser = new JEP();
        parser.addStandardFunctions();
        parser.addStandardConstants();
        parser.setImplicitMul(true);
        parser.addVariable("x", x);
        
        try {
            parser.parseExpression(function);
            if (parser.hasError()){
                throw new IllegalArgumentException("Error en la expresión: " + parser.getErrorInfo());
            }
            return parser.getValue();
        } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("Expresión no válida: " + e.getMessage());
        
        }

    }
    
    
    
}
