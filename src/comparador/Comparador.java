package comparador;

import primos.NumPrimo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class Comparador {

    /**
     * Funcion que compara la eficiencia de encontrar primos del segundo metodo con una precision por defecto de 20
     *
     * @param limite numero en el que parar de comprobar si se detectan el mismo numero de primos
     * @param extra  frase extra añadida al nombre de fichero
     * @return long con el total de numeros diferentes entre ambos metodos, si se devuelve -1, ha habido error al escribir en fichero
     */
    public static long encontrarPrimos(BigInteger inicio, BigInteger limite, String extra) {
        BigInteger actual = inicio;
        boolean esPrimoPrimerMetodo, esPrimoSegundoMetodo;
        long totalDiferencias = 0;
        StringBuilder primosPrimerMetodo = new StringBuilder();
        StringBuilder primosSegundoMetodo = new StringBuilder();
        while (limite.compareTo(actual) > 0) {
            if (NumPrimo.esPrimo(actual)) {
                primosPrimerMetodo.append(actual);
                primosPrimerMetodo.append(";");
                esPrimoPrimerMetodo = true;
            } else
                esPrimoPrimerMetodo = false;

            if (NumPrimo.esPrimo2(actual)) {
                primosSegundoMetodo.append(actual);
                primosSegundoMetodo.append(";");
                esPrimoSegundoMetodo = true;
            } else
                esPrimoSegundoMetodo = false;

            if (esPrimoPrimerMetodo != esPrimoSegundoMetodo)
                totalDiferencias++;
            actual = actual.add(BigInteger.ONE);
        }
        if (!escribir("primerMetodo" + extra + ".csv", primosPrimerMetodo.toString()))
            return -1;
        if (!escribir("segundoMetodo" + extra + ".csv", primosSegundoMetodo.toString()))
            return -1;
        return totalDiferencias;
    }

    /**
     * Funcion que compara el tiempo que tardan en detectar cuantos numeros primos hay desde el 0 al limite introducido
     * por parametro. Se compararan los metodos esPrimo y esPrimo2
     *
     * @param limite numero en el que parar de comprobar si el numero es primo
     * @return true si todo ha salido bien, false si no se ha conseguido escribir en el fichero
     */
    public static boolean comparaTiempo(BigInteger inicio, BigInteger limite, String nomFichero) {
        BigInteger actual = inicio;
        BigInteger totalPrimosPrimer = new BigInteger("0");
        BigInteger totalPrimosSegundo = new BigInteger("0");
        long tiempoInicio = System.currentTimeMillis();
        while (limite.compareTo(actual) > 0) {
            if (NumPrimo.esPrimo(actual))
                totalPrimosPrimer = totalPrimosPrimer.add(BigInteger.ONE);
            actual = actual.add(BigInteger.ONE);
        }
        long tiempoFinal = System.currentTimeMillis();
        long tiempoPrimer = tiempoFinal - tiempoInicio;

        tiempoInicio = System.currentTimeMillis();

        actual = inicio;
        while (limite.compareTo(actual) > 0) {
            if (NumPrimo.esPrimo2(actual))
                totalPrimosSegundo = totalPrimosSegundo.add(BigInteger.ONE);
            actual = actual.add(BigInteger.ONE);
        }
        tiempoFinal = System.currentTimeMillis();
        long tiempoSegundo = tiempoFinal - tiempoInicio;
        String resultado = ("\tPrimer metodo -> Tiempo: " + tiempoPrimer + "ms || Primos encontrados: " + totalPrimosPrimer + "\n\n\tSegundo metodo -> Tiempo: " + tiempoSegundo + "ms || Primos encontrados: " + totalPrimosSegundo);
        return escribir(nomFichero + ".txt", resultado);
    }

    /**
     * Escribe la informacion del String numeros en el fichero
     *
     * @param nomFichero sera el nombre que tomara el fichero en el que escribamos
     * @param numeros    String con la informacion a escribir
     * @return booleano indicando si se ha escrito correctamente o ha habido un error con el fichero
     */
    private static boolean escribir(String nomFichero, String numeros) {
        try {
            BufferedWriter fresul = new BufferedWriter(new FileWriter(nomFichero));
            fresul.write(numeros);
            fresul.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
