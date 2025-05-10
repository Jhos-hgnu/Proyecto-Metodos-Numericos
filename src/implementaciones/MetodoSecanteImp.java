package implementaciones;

import interfaces.IMetodoSecante;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;
import org.nfunk.jep.JEP;

public class MetodoSecanteImp implements IMetodoSecante {

    @Override
    public double calculateTolerancia(double xRActual, double xRAnterior) {

        double tolerancia = Math.abs(xRActual - xRAnterior);

        return tolerancia;
    }

    @Override
    public double calculateXR(double Xi1, double Xi, double fXi1, double fXi) {

        double Xr = Xi - ((fXi * (Xi1 - Xi)) / (fXi1 - fXi));

        return Xr;
    }

    @Override
    public DefaultTableModel calculateSecante(double Xi1, double Xi, String function) {

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"i", "Xi_1", "Xi", "F(Xi_1)", "F(Xi)", "Xr", "Tolerancia"});

        double proceXi1;
        double proceXi;
        double procefxi_1;
        double procefxi;
        double proceXr;

        DecimalFormat formato = new DecimalFormat("#0.0000");
        double tolerancia = 1;
        int iteracion = 1;

        while (tolerancia > 0.001) {

            double nuevoXi_1 = 0, nuevoXi = 0, nuevoFXi_1 = 0, nuevoFXi = 0, nuevoXr = 0;
            String tolerenciaStr = "";

            if (iteracion == 1) {

                tolerenciaStr = "-----";
                nuevoXi_1 = Xi1;
                nuevoXi = Xi;
                nuevoFXi_1 = calculateFunction(function, Xi1);
                nuevoFXi = calculateFunction(function, Xi);
                //nuevoXr = calculateXR(nuevoXi_1, nuevoXi, nuevoFXi_1, nuevoFXi);
                     
            } else {
                
                double previousXi = Double.parseDouble(modelo.getValueAt(iteracion -2, 2).toString());
                double previousfxi = Double.parseDouble(modelo.getValueAt(iteracion -2, 4).toString());
                double previousXr = Double.parseDouble(modelo.getValueAt(iteracion -2, 5).toString());
                nuevoXi_1 = previousXi;
                nuevoXi = previousXr;
                nuevoFXi = calculateFunction(function, previousXr);
                nuevoFXi_1 = previousfxi;
                nuevoXr = calculateXR(nuevoXi_1, nuevoXi, nuevoFXi_1, nuevoFXi);
                
                tolerancia = calculateTolerancia(nuevoXr, previousXr);
                tolerenciaStr = formato.format(tolerancia);

            }
            nuevoXr = calculateXR(nuevoXi_1, nuevoXi, nuevoFXi_1, nuevoFXi);
            modelo.addRow(new Object[]{
                iteracion,
                formato.format(nuevoXi_1),
                formato.format(nuevoXi),
                formato.format(nuevoFXi_1),
                formato.format(nuevoFXi),
                formato.format(nuevoXr),
                tolerenciaStr
            });
            
            proceXi1 = nuevoXi_1;
            proceXi = nuevoXi;
            procefxi_1 = nuevoFXi_1;
            procefxi = nuevoFXi;
            proceXr = nuevoXr;
            
            iteracion++;
            
        }

        return modelo;

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
            if (parser.hasError()) {
                throw new IllegalArgumentException("Error en la expresión: " + parser.getErrorInfo());
            }
            return parser.getValue();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Expresión no válida: " + e.getMessage());

        }
    }

}
