package implementaciones;

import interfaces.IMetodoNewton;
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
public class MetodoNewtonImp implements IMetodoNewton {

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

        double tolerancia = Math.abs(xRActual - xRAnterior);
        return tolerancia;
    }

//    @Override
//    public DefaultTableModel calculateNewton(double Xi, double fXi, double fxid, String function) {
//
//        DefaultTableModel modelo = new DefaultTableModel();
//        modelo.setColumnIdentifiers(new Object[]{"i","Xi","F(Xi)","F'(Xi)","Xr","Tolerancia"});
//        
//        double procexi = Xi;
//        double procefxi = fXi;
//        double procefxid = fxid;
//        double proceXr = 0;
//        
//        
//        DecimalFormat formato = new DecimalFormat();
//        double tolerancia = 1;
//        int iteracion = 1;
//        
//        while(tolerancia > 0.001){
//            double nuevoXi = 0, nuevofxi = 0, nuevofxid = 0, nuevoXr = 0;
//            String toleranciaStr = "";
//            
//            if (iteracion == 1){
//                toleranciaStr = "------";
//                nuevoXi = Xi;
//                nuevofxi = fXi;
//                nuevofxid = fxid;
//            } else {
//                
//               
//            }
//            nuevoXr = calculateXR(nuevoXi, nuevofxi, nuevofxid);
//            modelo.addRow(new Object[]{
//                iteracion,
//                formato.format(nuevoXi),
//                formato.format(nuevofxi),
//                formato.format(nuevofxi),
//                formato.format(nuevoXr),
//                toleranciaStr
//            });
//            
//            procexi = nuevoXi;
//            procefxi = nuevofxi;
//            procefxid = nuevofxi;
//            proceXr = nuevoXr;
//        }
//        
//        return modelo;
//    }

    @Override
    public double calculateXR(double Xi, double fXi, double fXid) {

        double resultXr = Xi - (fXi / fXid);

        return resultXr;
    }

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
    public DefaultTableModel calculateNewton(double Xi, String function) {
    
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"i","Xi","F(Xi)","F'(Xi)","Xr","Tolerancia"});
        
        double procexi = Xi;
        double procefxi = 0;
        double procefxid = 0;
        double proceXr = 0;
        
        
        DecimalFormat formato = new DecimalFormat("#0.0000");
        double tolerancia = 1;
        int iteracion = 1;
        String functionDerivada = calculateDerivative(function);
        System.out.println(functionDerivada);
        
        while(tolerancia > 0.001){
            double nuevoXi = 0, nuevofxi = 0, nuevofxid = 0, nuevoXr = 0;
            String toleranciaStr = "";
            
            if (iteracion == 1){
                toleranciaStr = "------";
                nuevoXi = Xi;
                nuevofxi = calculateFunction(function, Xi);
                nuevofxid = calculateFunction(functionDerivada, Xi);
                System.out.println(nuevofxid);
                nuevoXr = calculateXR(Xi, nuevofxi, nuevofxid);
                System.out.println(nuevoXr);
            } else {
                double previousXr = Double.parseDouble(modelo.getValueAt(iteracion -2, 4).toString());
                nuevoXi = previousXr;
                nuevofxi = calculateFunction(function, nuevoXi);
                nuevofxid = calculateFunction(functionDerivada, nuevoXi);
                nuevoXr = calculateXR(nuevoXi, nuevofxi, nuevofxid);
                
                tolerancia = calculateTolerancia(nuevoXr, previousXr);
                toleranciaStr = formato.format(tolerancia);
               
            }
            nuevoXr = calculateXR(nuevoXi, nuevofxi, nuevofxid);
            modelo.addRow(new Object[]{
                iteracion,
                formato.format(nuevoXi),
                formato.format(nuevofxi),
                formato.format(nuevofxid),
                formato.format(nuevoXr),
                toleranciaStr
            });
            
            procexi = nuevoXi;
            procefxi = nuevofxi;
            procefxid = nuevofxi;
            proceXr = nuevoXr;
            
            iteracion++;
        }
        
        return modelo;
        
        
    }

}
