/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import controladores.ControladorMenuEcuacion2X2;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author merar
 */
public class MetodoEcuacion2x2Imp  {
    
     private double c1;
    private double b1;
    private double b2;
    private double c2;
    private double a1;
    private double a2;
    
public static ControladorMenuEcuacion2X2 parsearEcuaciones (String eq1, String eq2) throws IllegalArgumentException{
 
    double [] coef1 = extraerCoeficientes(eq1);
    double [] coef2 = extraerCoeficientes(eq2);
    
    return new ControladorMenuEcuacion2X2 (coef1[0], coef1[1], coef1[2], coef2[0], coef2[1], coef2[2]);



}

    private static double[] extraerCoeficientes(String ecuacion) {
     
        ecuacion = ecuacion.replaceAll("", "").replaceAll("-", "+-");
        
        String[] partes = ecuacion.split("=");
        if(partes.length !=2){
            throw new IllegalArgumentException("Ecuaion mal formada (falta '=').");
        }
        
        String izquierda = partes[0];
        String derecha = partes[1];
        double a = 0, b= 0;
        
        String []terminos = izquierda.split("\\+");
        for (String t : terminos){
       if (t.contains("x")){
           t = t.replace("x", "");
           if (t.equals("")|| t.equals("+")) t = "1";
           else if (t.equals("-")) t = "-1";
           a = Double.parseDouble(t);
       } else if (t.contains("y")){
           t = t.replace("y","");
           if (t.equals("")|| t.equals("+")) t = "1";
           else if (t.equals("-")) t = "-1";
           b = Double.parseDouble(t);
       }
         }
        double c = Double.parseDouble(derecha);
        return new double[]{a,b,c};   
    }
    
    public static String resolverSistema(ControladorMenuEcuacion2X2 sistema){
    double a1 = sistema.getA1();
    double b1 = sistema.getB1();
    double c1 = sistema.getC1();
    double a2 = sistema.getA2();
    double b2 = sistema.getB2();
    double c2 = sistema.getC2();
    
    if (b1 == 0){
    return "No se puede usar el metodo de sustitucion: b1 = 0";    }
    
     
    double numerador = (c2 + b1)- (b2 + c1);
    double denominador = (a2 * b1) - (b2 + a1);
    
    if (denominador == 0){
         return "El sistema no tiene solución única";
    }
    
    double x = numerador / denominador;
    double y = (c1 - a1 + x)/ b1;
    
    return String.format("solución: x %.2f, y = %.2f", x, y);

   
    }
    
    
    }


    
    

    
    



    
    

