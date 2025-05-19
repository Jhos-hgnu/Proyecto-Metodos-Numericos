/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import interfaces.IMetodoRaicesMultiples;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;
import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.JEP;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

/**
 *
 * @author jhosu
 */
public class MetodoRaicesMultiplesImp implements IMetodoRaicesMultiples {

    @Override
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
    
    @Override
    public String calculateDerivative(String function) {
        
        try {
            DJep djep = new DJep();
            djep.addStandardFunctions();
            djep.addStandardConstants();
            djep.setImplicitMul(true);
            djep.setAllowUndeclared(true);

            Node nodeFunction = djep.parse(function);
            Node diff = djep.differentiate(nodeFunction, "x");
            Node simplified = djep.simplify(diff);
            return djep.toString(simplified);
        } catch (ParseException e) {

            return "Error al derivar: " + e.getMessage();
        }
        
    }

    @Override
    public double calculateTolerancia(double xRActual, double xRAnterior) {
        
        double tolerancia = Math.abs(xRActual-xRAnterior);
        
        return tolerancia;
    }

    @Override
    public DefaultTableModel calculateRaicesMultiples(double Xi, String function) {

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"i","Xi","F(Xi)","F'(Xi)","F''(Xi)","Xr","Tolerancia"});

        double proceXi = Xi;
        double proceFxi;
        double proceFxiD; 
        double proceFxiDD;
        double proceXr;
        
        DecimalFormat formato = new DecimalFormat("#0.0000");
        double tolerancia = 1;
        int iteracion = 1;
        String functionDerivada = calculateDerivative(function);
        String segundaDerivada = calculateDerivative(functionDerivada);
        
        while(tolerancia > 0.001){
            double nuevoXi = 0, nuevoFxi = 0, nuevoFxiD = 0, nuevoFxiDD = 0, nuevoXr = 0;
            String toleranciaSTR;
            if(iteracion == 1){
               toleranciaSTR = "-----";
               nuevoXi = Xi;
               nuevoFxi = calculateFunction(function, Xi);
               nuevoFxiD = calculateFunction(functionDerivada, Xi);
               nuevoFxiDD = calculateFunction(segundaDerivada, Xi);
//               nuevoXr = calculateXR(nuevoXi, nuevoFxi, nuevoFxiD, nuevoFxiDD);
            } else{
                double previousXr = Double.parseDouble(modelo.getValueAt(iteracion -2, 4).toString());
                nuevoXi = previousXr;
                nuevoFxi = calculateFunction(function, nuevoXi);
                nuevoFxiD = calculateFunction(functionDerivada, nuevoXi);
                nuevoFxiDD = calculateFunction(segundaDerivada, nuevoXi);
                nuevoXr = calculateXR(nuevoXi, nuevoFxi, nuevoFxiD, nuevoFxiDD);
                
                tolerancia = calculateTolerancia(nuevoXr, previousXr);
                toleranciaSTR = formato.format(tolerancia);
                
            }
            nuevoXr = calculateXR(nuevoXi, nuevoFxi, nuevoFxiD, nuevoFxiDD);
            modelo.addRow(new Object[]{
                iteracion,
                formato.format(nuevoXi),
                formato.format(nuevoFxi),
                formato.format(nuevoFxiD),
                formato.format(nuevoFxiDD),
                formato.format(nuevoXr),
                toleranciaSTR
            });
            iteracion++;
        }
        
        
        return modelo;
    }

    @Override
    public double calculateXR(double Xi, double fXi, double fXid, double fxidd) {
    
        double XrObtenido = Xi - ((fXi * fXid)/(Math.pow(fXid, 2) * fxidd));
    
    
        return XrObtenido;
    }
    
}
