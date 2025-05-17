/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import interfaces.IMetodoRaicesMultiples;
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public double calculateXR(double Xi, double fXi, double fXid, double fXide2, double fxidd) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DefaultTableModel calculateRaicesMultiples(double Xi, String function) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
