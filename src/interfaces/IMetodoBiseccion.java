package interfaces;

//import java.awt.List;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jhosu
 */
public interface IMetodoBiseccion {
    
    public DefaultComboBoxModel mostrarDatos();
    public double calcularXR(double a,double b);
    public double calculateTolerancia(double xActual, double xAnterior);
    public List<String> DiferenciarValores(String fuction);
    public List<String> ValoresNotacionPostFija(List<String> valores);
    public double ResolverFuncion(List<String> notacionPostFija, double intervalo);
    public DefaultTableModel calculateBiseccion(double a,double b, double fA, double fB, String fuction);
    public boolean compareInterva(double intervaFuctionA, double intervaFuctionB, String fuction);
    public double calculateXRReglaFalsa(double a, double b, double fa, double fb);
    
    
    
}
