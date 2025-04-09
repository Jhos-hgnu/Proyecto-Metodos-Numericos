package modelos;

import vistas.VentanaPrincipal;

public class ModeloVistaPrincipal {
    
    VentanaPrincipal vistaP;

    public ModeloVistaPrincipal() {
    }

    public ModeloVistaPrincipal(VentanaPrincipal vistaP) {
        this.vistaP = vistaP;
    }

    public VentanaPrincipal getVistaP() {
        return vistaP;
    }

    public void setVistaP(VentanaPrincipal vistaP) {
        this.vistaP = vistaP;
    }
     
}
