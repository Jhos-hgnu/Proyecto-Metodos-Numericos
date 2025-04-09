package implementaciones;

import interfaces.IMetodoBiseccion;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.management.StringValueExp;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import vistas.VistaMetodoBiseccion;

/**
 *
 * @author jhosu
 */
public class MetodoBiseccionImp implements IMetodoBiseccion {

    @Override
    public DefaultComboBoxModel mostrarDatos() {

        return mostrarDatos();
    }
    
    //Metodos necesarios para escribir la función en notación postfija
    private static final Map<String, Integer> PrioridadSignos = new HashMap<>();
    //Creación de un Hash para definir las operaciones y la prioridades de cada una
    static {
        PrioridadSignos.put("+", 1);
        PrioridadSignos.put("-", 1);
        PrioridadSignos.put("*", 2);
        PrioridadSignos.put("/", 2);
        PrioridadSignos.put("^", 3);
    }

    @Override
    public List<String> DiferenciarValores(String fuction) {
        List<String> valores = new ArrayList<>();
        Pattern identificador = Pattern.compile("\\d+\\.\\d+|\\d+|[a-zA-Z]+|[+\\-*/^()]");
        Matcher coincidencias = identificador.matcher(fuction);
        while (coincidencias.find()) {
            valores.add(coincidencias.group());
        }
        return valores;
    }

    @Override
    public List<String> ValoresNotacionPostFija(List<String> valores) {
        List<String> notacionPostFija = new ArrayList<>();
        Stack<String> operadores = new Stack<>();

        for (String valor : valores) {
            if (valor.matches("\\d+")) {
                notacionPostFija.add(valor);
            } else if (valor.equals("x")) {
                notacionPostFija.add(valor);
            } else if (valor.equals("(")) {
                operadores.push(valor);
            } else if (valor.equals(")")) {
                while (!operadores.isEmpty() && !operadores.peek().equals("(")) {
                    notacionPostFija.add(operadores.pop());
                }
                operadores.pop();
            } else {
                while (!operadores.isEmpty() && PrioridadSignos.getOrDefault(valor, 0) <= PrioridadSignos.getOrDefault(operadores.peek(), 0)) {
                    notacionPostFija.add(operadores.pop());
                }
                operadores.push(valor);
            }
        }
        while (!operadores.isEmpty()) {
            notacionPostFija.add(operadores.pop());
        }
        return notacionPostFija;
    }

    @Override
    public double ResolverFuncion(List<String> notacionPostFija, double intervalo) {
        Stack<Double> conjuntoDeValores = new Stack<>();
        //Utilizamos estructuras de datos como pilas para almacenar los valores a usar para resolver la función original
        for (String valor : notacionPostFija) {
            if (valor.matches("\\d+")) {
                conjuntoDeValores.push(Double.parseDouble(valor));
            } else if (valor.equals("x")) {
                conjuntoDeValores.push(intervalo);
            } else {
                double b = conjuntoDeValores.pop();
                double a = conjuntoDeValores.pop();
                switch (valor) {
                    case "+":
                        conjuntoDeValores.push(a + b);
                        break;
                    case "-":
                        conjuntoDeValores.push(a - b);
                        break;
                    case "*":
                        conjuntoDeValores.push(a * b);
                        break;
                    case "/":
                        conjuntoDeValores.push(a / b);
                        break;
                    case "^":
                        conjuntoDeValores.push(Math.pow(a, b));
                        break;
                    default:
                        throw new AssertionError();
                }

            }
        }

        return conjuntoDeValores.pop();
    }

    @Override
    public double calcularXR(double a, double b) {

        double resultXR = (a + b) / 2;

        return resultXR;
    }

    @Override
    public double calculateTolerancia(double xRActual, double xRAnterior) {
        double tolerancia = Math.abs(xRActual - xRAnterior);
//        tolerancia = Math.round(tolerancia * 10000.0)/10000.0;
        return tolerancia;
    }


    DefaultTableModel modeloTabla = new DefaultTableModel();

    public DefaultTableModel modeloTablaBiseccion() {

        modeloTabla.setColumnIdentifiers(new Object[]{"i", "a", "b", "f(a)", "f(b)", "Xr", "f(Xr)", "Tolerancia"});
        return modeloTabla;
    }

    public DefaultTableModel agregarFilas(int i, double a, double b, double fA, double fB, double xR, double fXR, String tolerancia) {
        DecimalFormat formato = new DecimalFormat("#0.0000");//Formato con 4 decimales

        Object toleranciaFormateada = (i == 1) ? "-----" : tolerancia;

        modeloTabla.addRow(new Object[]{
            i,
            formato.format(a),
            formato.format(b),
            formato.format(fA),
            formato.format(fB),
            formato.format(xR),
            formato.format(fXR),
            toleranciaFormateada
        });

        return modeloTabla;

    }

    @Override
    public DefaultTableModel calculateBiseccion(double a, double b, double fA, double fB, String fuction) {
        //Metodo que calcula las filas para la tabla del metodo de bisección
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"i", "a", "b", "f(a)", "f(b)", "Xr", "f(Xr)", "Tolerancia"});

        double proceA = a;
        double proceB = b;
        double proceFA = fA;
        double proceFB = fB;
        double proceXR = 0;
        double proceFXR = 0;

        DecimalFormat formato = new DecimalFormat("#0.0000");//Formato con 4 decimales
        double tolerancia = 1; //Prueba inicializando tolerancia en 1
        int iteracion = 1;
//        String toleranciaFirstI;

        while (tolerancia > 0.001) {
            double nuevoA, nuevoB, nuevoFA, nuevoFB, nuevoXR = 0, nuevoFXR;
            String toleranciaStr;

            //Proceso para la primera iteración
            if (iteracion == 1) {
                toleranciaStr = "-----";
                System.out.println("HOLA EN CALCULATE BISECCION IF");
                nuevoA = a;
                nuevoB = b;
                nuevoFA = fA;
                nuevoFB = fB;
//                nuevoXR = calcularXR(nuevoA, nuevoB);

            } else {
                //Proceso para las siguientes iteraciones de la tabla              

                System.out.println("Hola en else DE CALCULATE BISECCION");

                double previousA = Double.parseDouble(modelo.getValueAt(iteracion - 2, 1).toString());
                double previousB = Double.parseDouble(modelo.getValueAt(iteracion - 2, 2).toString());
                double previousFA = Double.parseDouble(modelo.getValueAt(iteracion - 2, 3).toString());
                double previousFB = Double.parseDouble(modelo.getValueAt(iteracion - 2, 4).toString());
                double previousXR = Double.parseDouble(modelo.getValueAt(iteracion - 2, 5).toString());
                double previousFXR = Double.parseDouble(modelo.getValueAt(iteracion - 2, 6).toString());

                if ((previousFXR < 0 && previousFA < 0) || (previousFXR > 0 && previousFA > 0)) {
                    nuevoA = previousXR;
                    nuevoB = previousB;
                    nuevoFA = previousFXR;
                    nuevoFB = previousFB;
                    nuevoXR = calcularXR(nuevoA, nuevoB);
//                    nuevoFXR = ResolverFuncion(ValoresNotacionPostFija(DiferenciarValores(fuction)), proceXR);
//                    tolerancia = calculateTolerancia(proceXR, previousXR);

                } else {
                    nuevoA = previousA;
                    nuevoB = previousXR;
                    nuevoFA = previousFA;
                    nuevoFB = previousFXR;
                    nuevoXR = calcularXR(nuevoA, nuevoB);
//                    nuevoFXR = ResolverFuncion(ValoresNotacionPostFija(DiferenciarValores(fuction)), proceXR);
//                    tolerancia = calculateTolerancia(proceXR, previousXR);
                }

                tolerancia = calculateTolerancia(nuevoXR, previousXR);
//                tolerancia = Math.round(tolerancia * 10000000000.0)/10000000000.0;
                tolerancia = Math.round(tolerancia * 10000.0) / 10000.0;
                System.out.println(tolerancia);
                toleranciaStr = formato.format(tolerancia);
//                tolerancia = Double.parseDouble(toleranciaStr);//Agregando còdigo para prueba de aproximaciòn
                System.out.println(toleranciaStr);

            }
            nuevoXR = calcularXR(nuevoA, nuevoB);
            nuevoFXR = ResolverFuncion(ValoresNotacionPostFija(DiferenciarValores(fuction)), nuevoXR);

            modelo.addRow(new Object[]{
                iteracion,
                formato.format(nuevoA),
                formato.format(nuevoB),
                formato.format(nuevoFA),
                formato.format(nuevoFB),
                formato.format(nuevoXR),
                formato.format(nuevoFXR),
                toleranciaStr
            });

            proceA = nuevoA;
            proceB = nuevoB;
            proceFA = nuevoFA;
            proceFB = nuevoFB;
            proceXR = nuevoXR;
            proceFXR = nuevoFXR;

            iteracion++;

        }

        return modelo;
    }

    @Override
    public double calculateXRReglaFalsa(double a, double b, double fa, double fb) {
        return 0;
    }

    @Override
    public boolean compareInterva(double intervaFuctionA, double intervaFuctionB, String fuction) {
        
         boolean makeFuction = true;
        
        double outPutIntervalFA = ResolverFuncion(ValoresNotacionPostFija(DiferenciarValores(fuction)), intervaFuctionA);
        double outPutIntervalFB = ResolverFuncion(ValoresNotacionPostFija(DiferenciarValores(fuction)), intervaFuctionB);
        
        if (outPutIntervalFA <= 0 && outPutIntervalFB <= 0 || outPutIntervalFA > 0 && outPutIntervalFB > 0) {
            makeFuction = true;
        }else {
            makeFuction = false;
        }
        
        return makeFuction;
    }

    //Intento para acceder al xr (Spoiler no funcionó)
//    @Override
//    public String getResultXR() {
////        int filas = modelo.getRowCount();
//        String xr = "modeloTabla.getValueAt(filas -2, 5).toString();";
//                //modeloTabla.getValueAt(filas -2, 5).toString();
//        return xr;
//    }
}
