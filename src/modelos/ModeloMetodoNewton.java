package modelos;

import vistas.VistaMetodoNewton;

public class ModeloMetodoNewton {
    
    VistaMetodoNewton vistaNewton;

    public ModeloMetodoNewton() {
    }

    public ModeloMetodoNewton(VistaMetodoNewton vistaNewton) {
        this.vistaNewton = vistaNewton;
    }

    public VistaMetodoNewton getVistaNewton() {
        return vistaNewton;
    }

    public void setVistaNewton(VistaMetodoNewton vistaNewton) {
        this.vistaNewton = vistaNewton;
    }
    
}
