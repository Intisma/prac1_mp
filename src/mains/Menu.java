package mains;

import administrarEntradaSalida.AdministradorEntradaSalida;
import primos.AnalisisPrimo;
import primos.AnalisisPrimoBigInt;
import primos.AnalisisPrimoLong;
import primos.NumPrimo;
import procesado.GestionPrimos;

import java.io.IOException;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    static Scanner teclado = new Scanner(System.in);

    /**
     * Main para tener un menu desde el que poder probar las funcionalidades del programa
     *
     * @param args no usados
     */
    public static void main(String[] args) {
        int opcion;
        do {
            muestraMenu();
            opcion = leerEntero("", 0, 4);
            switch (opcion) {
                case 1 -> comprobarPrimo();
                case 2 -> encontrarPrimoMayor();
                case 3 -> ficherosPrimos();
                case 4 -> juegoPruebas();
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
        System.out.println("\n\t1. Introduce un número y comprueba si es primo.");
        System.out.println("\t2. Introduce un número y encuentra el primo mayor, menor o igual a tu número.");
        System.out.println("\t3. Introduce el nombre de un fichero CSV donde leer una serie de números, se buscarán los" +
                " números primos más grandes menores a estos números y se escribirán en un fichero con el nombre que introduzcas.");
        System.out.println("\t4. Juego de pruebas.");
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
     * El programa se pausa hasta que el usuario pulse la tecla enter
     */
    public static void pausa() {
        System.out.println("\n\tPulsa \"ENTER\" para continuar...");
        try {
            if (System.in.read() == -1)
                System.out.println("\t\tNo deberías haber escrito nada, no se ha guardado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Función que se encargará de leer un número por teclado, comprobar si es primo y mostrar el resultado
     * de esta comprobación por pantalla
     */
    public static void comprobarPrimo() {
        String numero = leerNumero("\n\tIntroduce el número a examinar: ");
        boolean primo;
        if (numero.length() < 19)
            primo = NumPrimo.esPrimo(Long.parseLong(numero));
        else {
            BigInteger comprobarLong = new BigInteger(numero);
            if (comprobarLong.compareTo(new BigInteger("9223372036854775807")) > 0)
                primo = NumPrimo.esPrimo(comprobarLong);
            else
                primo = NumPrimo.esPrimo(Long.parseLong(numero));
        }

        if (primo)
            System.out.println("\t\tEl número introducido es primo");
        else
            System.out.println("\t\tEl número introducido no es primo");

        pausa();
    }

    /**
     * Función que lee un número por teclado y encuentra el número primo más grande que sea menor o igual al número
     * introducido por teclado. Muestra el resultado por pantalla con el tiempo que se ha tardado en encontrar el
     * número primo.
     */
    public static void encontrarPrimoMayor() {
        AnalisisPrimo analista;
        String numero = leerNumero("\n\tIntroduce el número a examinar: ");
        if (numero.length() < 19)
            analista = new AnalisisPrimoLong(numero);
        else {
            BigInteger comprobarLong = new BigInteger(numero);
            if (comprobarLong.compareTo(new BigInteger("9223372036854775807")) > 0)
                analista = new AnalisisPrimoBigInt(numero);
            else
                analista = new AnalisisPrimoLong(numero);
        }

        analista.encontrarPrimoMayor();
        System.out.println(analista);

        pausa();
    }

    /**
     * Función que lee por teclado el nombre de un fichero CSV. Este CSV debe contener números y a cada número
     * se le buscará el primo más grande que sea menor o igual al número. Luego se escribirán los resultados
     * en un fichero cuyo nombre se leerá por teclado
     */
    public static void ficherosPrimos() {
        AdministradorEntradaSalida gestorFicheros = new AdministradorEntradaSalida();
        System.out.print("\n\tIntroduce el nombre del fichero a leer: ");
        String nombreFichero = teclado.next();
        String[] numeros = gestorFicheros.leer(nombreFichero);
        if (numeros != null) {
            GestionPrimos gestorPrimos = new GestionPrimos(numeros);
            System.out.println("\n\tIniciando el procesado de los números... ");
            gestorPrimos.procesarPrimos();
            System.out.print("\n\tProcesado finalizado, introduzca el nombre del fichero donde quiere guardar los resultados: ");
            nombreFichero = teclado.next();
            if (gestorFicheros.escribir(gestorPrimos, nombreFichero))
                System.out.println("\n\tTodo ha ido correctamente");
            else
                System.out.println("\n\tError al escribir en el fichero");
        } else
            System.out.println("\t\tError, fichero no encontrado");

        pausa();
    }

    /**
     * Se ejecuta un juego de pruebas
     */
    public static void juegoPruebas() {
    }


}

