package mains;

import comparador.Comparador;

import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Comparaciones {

    static Scanner teclado = new Scanner(System.in);

    /**
     * Main para tener un menu desde el que poder comparar los dos métodos de comprobar si un número es primo
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
     * Menú
     */
    public static void muestraMenu() {
        System.out.println("\n Opciones:");
        System.out.println("\n\t1. Compara la exactitud de ambos métodos (falsos positivos del método estadístico).");
        System.out.println("\t2. Compara la velocidad de ambos métodos.");
        System.out.println("\t0. Exit.");
        System.out.print("\n\t\t\tEscoje una opción,\t");
    }

    /**
     * Método para leer un entero controlando InputMismatchException
     *
     * @param mensaje a mostrar
     * @param min     número mínimo
     * @param max     número máximo
     * @return número correcto
     */
    public static int leerEntero(String mensaje, int min, int max) {
        int resultado = 0;
        boolean leido = false;
        do {
            try {
                System.out.print(mensaje + "el número tiene que ser mayor o igual que " + min + " y menor o igual que " + max + ": ");
                resultado = teclado.nextInt();
                leido = true;
            } catch (InputMismatchException e) {
                System.out.println("Tienes que introducir un entero");
                teclado.nextLine();                                        /* Vamos a la siguiente línea para continuar leyendo el resultado */
            } finally {
                if (resultado > max || resultado < min) {
                    leido = false;
                }
            }
        } while (!leido);                                                        /* Bucle hasta que leamos el resultado */
        return resultado;
    }

    /**
     * Función que lee un número en formato String
     *
     * @param mensaje será el mensaje que mostraremos por pantalla para pedir al usuario que introduzca el número
     * @return String que contenga el número leído
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
     * Método para leer por teclado un rango de números, comparar el tiempo que se tarda en encontrar si son primos
     * por cada uno de los métodos, y escribirlo en un fichero cuyo nombre introduce el usuario.
     */
    public static void comparaTiempos() {
        String limiteInferior = leerNumero("\n\tIntroduce el límite inferior del rango de números a bucar: ");
        String limiteSuperior = leerNumero("\n\tIntroduce el límite superior del rango de números a bucar: ");
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
    }

    /**
     * Método para leer por teclado un rango de números, y comparar la precisión de ambos métodos para comprobar si un
     * número es primo. Se detectan los falsos positivos del método estadístico.
     */
    public static void comparaExactitud() {
        String limiteInferior = leerNumero("\n\tIntroduce el límite inferior del rango de números a bucar: ");
        String limiteSuperior = leerNumero("\n\tIntroduce el límite superior del rango de números a bucar: ");
        BigInteger limInf = new BigInteger(limiteInferior);
        BigInteger limSup = new BigInteger(limiteSuperior);
        BigInteger temporal;
        if (limInf.compareTo(limSup) >= 0) {
            temporal = limInf;
            limInf = limSup;
            limSup = temporal;
        }
        System.out.print("\n\tIntroduce el extra del fichero que se añadirá al fichero resultado: ");
        String extra = teclado.next();
        long errores = Comparador.encontrarPrimos(limInf, limSup, extra);
        if (errores == -1)
            System.out.println("\t\tError en la escritura de fichero");
        else
            System.out.println("\t\tEl número de errores del segundo método para este rango es: " + errores);
    }

}
