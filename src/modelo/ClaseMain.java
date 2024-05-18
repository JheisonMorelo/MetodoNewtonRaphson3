package modelo;

import controlador.Controlador;
import org.nfunk.jep.ParseException;

public class ClaseMain {

    public static void main(String[] args) throws ParseException {
        Controlador controlador = new Controlador();
        controlador.run();
    }
}
