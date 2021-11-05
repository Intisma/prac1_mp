package mains;

import administrarEntradaSalida.AdministradorEntradaSalida;
import procesado.GestionPrimos;


public class Test {

    public static void main(String[] args) {
        AdministradorEntradaSalida administrador = new AdministradorEntradaSalida();
        System.out.println("Ejecutando primer test...");
        String[] test1 = administrador.leer("fichero.txt");
        if (test1 != null) {
            GestionPrimos gestor = new GestionPrimos(test1);
            gestor.procesarPrimos();
            if (!administrador.escribir(gestor, "resultados.csv")) {
                System.out.println("Error escribiendo en fichero");
            }
        } else {
            System.out.println("Error leyendo del fichero");
        }
    }
}
