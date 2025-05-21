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
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jhosu
 */
public class MetodoReglaFalsaImp implements IMetodoBiseccion {

    @Override
    public DefaultComboBoxModel mostrarDatos() {
    return null;
    }

    @Override
    public double calcularXR(double a, double b) {
        //Método solo aplicable al método de bisección
        return 0;
    }

    @Override
    public double calculateTolerancia(double xRActual, double xRAnterior) {
        double tolerancia = Math.abs(xRActual-xRAnterior);
        
        return tolerancia;
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
    
    
    //METODO PARA DIFERENCIAR LOS VALORES
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
    public DefaultTableModel calculateBiseccion(double a, double b, double fA, double fB, String fuction) {
        //METODO PARA RESOLVER EL METODO (BISECCION/REGLA FALSA)
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
//                  nuevoXR = calcularXR(nuevoA, nuevoB);
                    nuevoXR = calculateXRReglaFalsa(nuevoA, nuevoB, nuevoFA, nuevoFB);

                } else {
                    nuevoA = previousA;
                    nuevoB = previousXR;
                    nuevoFA = previousFA;
                    nuevoFB = previousFXR;
//                  nuevoXR = calcularXR(nuevoA, nuevoB);
                    nuevoXR = calculateXRReglaFalsa(nuevoA, nuevoB, nuevoFA, nuevoFB);
                }

                tolerancia = calculateTolerancia(nuevoXR, previousXR);
                tolerancia = Math.round(tolerancia * 10000.0) / 10000.0;
//                System.out.println(tolerancia);
                toleranciaStr = formato.format(tolerancia);
                System.out.println(toleranciaStr);

            }
            nuevoXR = calculateXRReglaFalsa(nuevoA, nuevoB, nuevoFA, nuevoFB);
            System.out.println(nuevoXR + "nuevoxr");
            nuevoFXR = ResolverFuncion(ValoresNotacionPostFija(DiferenciarValores(fuction)), nuevoXR);
            System.out.println(nuevoFXR + "nuevoFXR");
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

        double resultXR = (((a) * (fb)) - ((b) * (fa))) / ((fb) - (fa));

        return resultXR;
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
    
    public double guardarIntervaFA(double FA, String fuction){
        
        double outPutFA = ResolverFuncion(ValoresNotacionPostFija(DiferenciarValores(fuction)), FA);
        
        return outPutFA;
    }
    
    public double guardarIntervaFB(double FB, String fuction){
        double outPutFB = ResolverFuncion(ValoresNotacionPostFija(DiferenciarValores(fuction)), FB);
        
        return outPutFB;
    }

   
}
