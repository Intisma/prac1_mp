package mains;

import comparador.Comparador;

import java.io.IOException;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase enfocada en usar el Comparador de métodos esPrimo y esPrimo2
 */
public class Comparaciones {

    static Scanner teclado = new Scanner(System.in);

    /**
     * Main para tener un menu desde el que poder comparar los dos metodos de comprobar si un numero es primo
     *
     * @param args no usados
     */
    public static void main(String[] args) {
        int opcion;
        do {
            muestraMenu();
            opcion = leerEntero("", 0, 2);
            switch (opcion) {
                case 1 -> comparaExactitud();
                case 2 -> comparaTiempos();
                case 0 -> System.out.println("\tHasta otra!");
            }

        } while (opcion != 0);
        teclado.close();
    }

    /**
     * Menu
     */
    public static void muestraMenu() {
        System.out.println("\n Opciones:");
        System.out.println("\n\t1. Compara la exactitud de ambos metodos (falsos positivos del metodo estadistico).");
        System.out.println("\t2. Compara la velocidad de ambos metodos.");
        System.out.println("\t0. Exit.");
        System.out.print("\n\t\t\tEscoje una opcion,\t");
    }

    /**
     * Metodo para leer un entero controlando InputMismatchException
     *
     * @param mensaje a mostrar
     * @param min     numero minimo
     * @param max     numero maximo
     * @return numero correcto
     */
    public static int leerEntero(String mensaje, int min, int max) {
        int resultado = 0;
        boolean leido = false;
        do {
            try {
                System.out.print(mensaje + "el numero tiene que ser mayor o igual que " + min + " y menor o igual que " + max + ": ");
                resultado = teclado.nextInt();
                leido = true;
            } catch (InputMismatchException e) {
                System.out.println("Tienes que introducir un entero");
                teclado.nextLine();                                        /* Vamos a la siguiente linea para continuar leyendo el resultado */
            } finally {
                if (resultado > max || resultado < min) {
                    leido = false;
                }
            }
        } while (!leido);                                                        /* Bucle hasta que leamos el resultado */
        return resultado;
    }

    /**
     * Funcion que lee un numero en formato String
     *
     * @param mensaje sera el mensaje que mostraremos por pantalla para pedir al usuario que introduzca el numero
     * @return String que contenga el numero leido
     */
    public static String leerNumero(String mensaje) {
        String numero;
        boolean leido;
        do {
            System.out.print(mensaje);
            numero = teclado.next();
            leido = ((!numero.equals("")) && (numero.matches("[+-]?\\d*(\\.\\d+)?")));
        } while (!leido);
        return numero;
    }

    /**
     * Metodo para leer por teclado un rango de numeros, comparar el tiempo que se tarda en encontrar si son primos
     * por cada uno de los metodos, y escribirlo en un fichero cuyo nombre introduce el usuario.
     */
    public static void comparaTiempos() {
        String limiteInferior = leerNumero("\n\tIntroduce el limite inferior del rango de numeros a bucar: ");
        String limiteSuperior = leerNumero("\n\tIntroduce el limite superior del rango de numeros a bucar: ");
        BigInteger limInf = new BigInteger(limiteInferior);
        BigInteger limSup = new BigInteger(limiteSuperior);
        BigInteger temporal;
        if (limInf.compareTo(limSup) >= 0) {
            temporal = limInf;
            limInf = limSup;
            limSup = temporal;
        }
        System.out.print("\n\tIntroduce el nombre del fichero donde guardar los resultados: ");
        String nomFichero = teclado.next();
        if (!Comparador.comparaTiempo(limInf, limSup, nomFichero))
            System.out.println("\t\tError en la escritura de fichero");

        pausa();
    }

    /**
     * Metodo para leer por teclado un rango de numeros, y comparar la precision de ambos metodos para comprobar si un
     * numero es primo. Se detectan los falsos positivos del metodo estadistico.
     */
    public static void comparaExactitud() {
        String limiteInferior = leerNumero("\n\tIntroduce el limite inferior del rango de numeros a bucar: ");
        String limiteSuperior = leerNumero("\n\tIntroduce el limite superior del rango de numeros a bucar: ");
        BigInteger limInf = new BigInteger(limiteInferior);
        BigInteger limSup = new BigInteger(limiteSuperior);
        BigInteger temporal;
        if (limInf.compareTo(limSup) >= 0) {
            temporal = limInf;
            limInf = limSup;
            limSup = temporal;
        }
        System.out.print("\n\tIntroduce el extra del fichero que se añadira al fichero resultado: ");
        String extra = teclado.next();
        long errores = Comparador.encontrarPrimos(limInf, limSup, extra);
        if (errores == -1)
            System.out.println("\t\tError en la escritura de fichero");
        else
            System.out.println("\t\tEl numero de errores del segundo metodo para este rango es: " + errores);

        pausa();
    }

    /**
     * El programa se pausa hasta que el usuario pulse la tecla enter
     */
    public static void pausa() {
        System.out.println("\n\tPulsa \"ENTER\" para continuar...");
        try {
            if (System.in.read() == -1)
                System.out.println("\t\tNo deberias haber escrito nada, no se ha guardado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
