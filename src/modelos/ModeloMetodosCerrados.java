/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

//import vistas.MenuMetodosCerrados;
import vistas.MenuMetodosCerrados2;

/**
 *
 * @author jhosu
 */
public class ModeloMetodosCerrados {
    
    MenuMetodosCerrados2 vistaMenuMC;

    public ModeloMetodosCerrados() {
    }

    //public ModeloMetodosCerrados(MenuMetodosCerrados vistaMenuMC) {
    //    this.vistaMenuMC = vistaMenuMC;
   // }

    //public MenuMetodosCerrados getVistaMenuMC() {
    //    return vistaMenuMC;
   // }

   // public void setVistaMenuMC(MenuMetodosCerrados vistaMenuMC) {
   //     this.vistaMenuMC = vistaMenuMC;
   // }

    public ModeloMetodosCerrados(MenuMetodosCerrados2 vistaMenuMC) {
        this.vistaMenuMC = vistaMenuMC;
    }

    public MenuMetodosCerrados2 getVistaMenuMC() {
        return vistaMenuMC;
    }

    public void setVistaMenuMC(MenuMetodosCerrados2 vistaMenuMC) {
        this.vistaMenuMC = vistaMenuMC;
    }
    

}
