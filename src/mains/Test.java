package mains;

import administrarEntradaSalida.AdministradorEntradaSalida;
import procesado.GestionPrimos;

import java.io.*;


public class Test {

    public static void main(String[] args) throws IOException {

        AdministradorEntradaSalida administrador = new AdministradorEntradaSalida();
        System.out.println("Ejecutando primer test...");
        String[] test1 = administrador.leer("fichero.txt");
        if (test1 != null) {
            GestionPrimos gestor = new GestionPrimos(test1);
            gestor.procesarPrimos();
            administrador.escribir(gestor, "resultados.csv");
        }

    }
}
