/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.logging.Logger;
import vistas.MetodoRaicesPolinomios;

/**
 *
 * @author merar
 */
public class ModeloMetodoMuller {

    MetodoRaicesPolinomios vistaMuller;

    public ModeloMetodoMuller() {
    }

    public ModeloMetodoMuller(MetodoRaicesPolinomios vistaMuller) {
        this.vistaMuller = vistaMuller;
    }

    public MetodoRaicesPolinomios getVistaMuller() {
        return vistaMuller;
    }

    public void setVistaMuller(MetodoRaicesPolinomios vistaMuller) {
        this.vistaMuller = vistaMuller;
    }

}
