package modelos;

import vistas.VistaMetodoSecante;


public class ModeloMetodoSecante {
    
    VistaMetodoSecante vistaSecante;

    public ModeloMetodoSecante() {
    }

    public ModeloMetodoSecante(VistaMetodoSecante vistaSecante) {
        this.vistaSecante = vistaSecante;
    }

    public VistaMetodoSecante getVistaSecante() {
        return vistaSecante;
    }

    public void setVistaSecante(VistaMetodoSecante vistaSecante) {
        this.vistaSecante = vistaSecante;
    }
      
}
