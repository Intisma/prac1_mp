package mains;

import administrarEntradaSalida.AdministradorEntradaSalida;
import comparador.Comparador;
import primos.AnalisisPrimo;
import primos.AnalisisPrimoBigInt;
import primos.AnalisisPrimoLong;
import primos.NumPrimo;
import procesado.GestionPrimos;

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
        AdministradorEntradaSalida gestorFicheros = new AdministradorEntradaSalida();

        System.out.println("\n***********************************************");
        System.out.println("\n\t\tJUEGOS DE PRUEBAS EN CURSO");
        System.out.println("\n***********************************************\n");

        /*Juegos de pruebas para comprobacion de ficheros*/
        System.out.println("\n *********PRUEBAS GESTION DE FICHEROS*********\n");

        //PRUEBA 1: Abrir fichero que no existe
        System.out.println("\nPrueba 1: Lectura fichero que no existe.");
        String[] numeros = gestorFicheros.leer("Prueba1.txt");
        if (numeros!=null) {
            GestionPrimos gestorPrimos = new GestionPrimos(numeros);
            gestorPrimos.procesarPrimos();
            if (gestorFicheros.escribir(gestorPrimos, "Prueba1Result.csv"))
                System.out.println("\n\tTodo ha ido correctamente");
            else
                System.out.println("\n\tError al escribir en el fichero");
        }
        else {
            System.out.println("\t\tError, fichero no encontrado");
        }
        //PRUEBA 2: Abrir fichero vacio
        System.out.println("\nPrueba2: Lectura fichero ");
        numeros = gestorFicheros.leer("Prueba2.txt");
        if (numeros!=null) {
            GestionPrimos gestorPrimos = new GestionPrimos(numeros);
            gestorPrimos.procesarPrimos();
            if (gestorFicheros.escribir(gestorPrimos, "Prueba2Result.csv"))
                System.out.println("\n\tTodo ha ido correctamente");
            else
                System.out.println("\n\tError al escribir en el fichero");
        }

        //PRUEBA 3: Fichero con contenido incorrecto
        System.out.println("\nPrueba3: Lectura fichero con contenido incorrecto");
        numeros = gestorFicheros.leer("Prueba3.txt");
        if (numeros!=null) {
            GestionPrimos gestorPrimos = new GestionPrimos(numeros);
            gestorPrimos.procesarPrimos();
            if (gestorFicheros.escribir(gestorPrimos, "Prueba3Result.csv"))
                System.out.println("\n\tTodo ha ido correctamente");
            else
                System.out.println("\n\tError al escribir en el fichero");
        }

        //PRUEBA 4: Fichero con números negativos
        System.out.println("\nPrueba4: Lectura fichero con números negativos ");
        numeros = gestorFicheros.leer("Prueba4.txt");
        if (numeros!=null) {
            GestionPrimos gestorPrimos = new GestionPrimos(numeros);
            gestorPrimos.procesarPrimos();
            if (gestorFicheros.escribir(gestorPrimos, "Prueba4Result.csv"))
                System.out.println("\n\tTodo ha ido correctamente");
            else
                System.out.println("\n\tError al escribir en el fichero");
        }

        //PRUEBA 5: Abrir fichero con espacios intercalados
        System.out.println("\nPrueba5: Lectura fichero con espacios intercalados.");
        numeros = gestorFicheros.leer("Prueba5.txt");
        if (numeros!=null) {
            GestionPrimos gestorPrimos = new GestionPrimos(numeros);
            gestorPrimos.procesarPrimos();
            System.out.println(gestorPrimos);
            if (gestorFicheros.escribir(gestorPrimos, "Prueba5Result.csv"))
                System.out.println("\n\tTodo ha ido correctamente");
            else
                System.out.println("\n\tError al escribir en el fichero");
        }

        /*Juegos de pruebas para comprobacion de funciones*/
        System.out.println("\n *********PRUEBAS ANÁLISIS RESULTADOS*********\n");

        System.out.println("---> PRUEBAS CON MÉTODO 1 esPrimo()");
        //PRUEBA 6: Abrir fichero con todos los números de tipo long
        System.out.println("\nPrueba6: Lectura fichero con numeros de tipo long.");
        numeros = gestorFicheros.leer("Prueba6.txt");
        if (numeros!=null) {
            GestionPrimos gestorPrimos = new GestionPrimos(numeros);
            gestorPrimos.procesarPrimos();
            System.out.println(gestorPrimos);
            if (gestorFicheros.escribir(gestorPrimos, "Prueba6Result.csv"))
                System.out.println("\n\tTodo ha ido correctamente");
            else
                System.out.println("\n\tError al escribir en el fichero");
        }
        //PRUEBA 7: Abrir fichero con todos los números de tipo bigInteger
        System.out.println("\nPrueba7: Lectura fichero con numeros de tipo BigInteger.");
        numeros = gestorFicheros.leer("Prueba7.txt");
        if (numeros!=null) {
            GestionPrimos gestorPrimos = new GestionPrimos(numeros);
            gestorPrimos.procesarPrimos();
            System.out.println(gestorPrimos);
            if (gestorFicheros.escribir(gestorPrimos, "Prueba7Result.csv"))
                System.out.println("\n\tTodo ha ido correctamente");
            else
                System.out.println("\n\tError al escribir en el fichero");
        }
        //PRUEBA 8: Realizar fichero mixto (long + bigInteger)
        System.out.println("\nPrueba8: Lectura fichero con numeros long + BigInteger.");
        numeros = gestorFicheros.leer("Prueba8.txt");
        if (numeros!=null) {
            GestionPrimos gestorPrimos = new GestionPrimos(numeros);
            gestorPrimos.procesarPrimos();
            System.out.println(gestorPrimos);
            if (gestorFicheros.escribir(gestorPrimos, "Prueba8Result.csv"))
                System.out.println("\n\tTodo ha ido correctamente");

            else
                System.out.println("\n\tError al escribir en el fichero");
        }

        //Análisis de método 2 de encontrar primo
        System.out.println("\n---> PRUEBAS CON MÉTODO 2 esPrimo()");
        //PRUEBA 9: Abrir fichero con todos los números de tipo long
        System.out.println("\nPrueba9: Lectura fichero con numeros de tipo long.");
        numeros = gestorFicheros.leer("Prueba9.txt");
        if (numeros!=null) {
            GestionPrimos gestorPrimos = new GestionPrimos(numeros);
            gestorPrimos.procesarPrimos2();
            System.out.println(gestorPrimos);
            if (gestorFicheros.escribir(gestorPrimos, "Prueba9Result.csv"))
                System.out.println("\n\tTodo ha ido correctamente");
            else
                System.out.println("\n\tError al escribir en el fichero");
        }
        //PRUEBA 10: Abrir fichero con todos los números de tipo bigInteger
        System.out.println("\nPrueba10: Lectura fichero con numeros de tipo BigInteger.");
        numeros = gestorFicheros.leer("Prueba10.txt");
        if (numeros!=null) {
            GestionPrimos gestorPrimos = new GestionPrimos(numeros);
            gestorPrimos.procesarPrimos2();
            System.out.println(gestorPrimos);
            if (gestorFicheros.escribir(gestorPrimos, "Prueba10Result.csv"))
                System.out.println("\n\tTodo ha ido correctamente");
            else
                System.out.println("\n\tError al escribir en el fichero");
        }

        //PRUEBA 11: Realizar fichero mixto (long + bigInteger)
        System.out.println("\nPrueba6: Lectura fichero con numeros long + BigInteger.");
        numeros = gestorFicheros.leer("Prueba11.txt");
        if (numeros!=null) {
            GestionPrimos gestorPrimos = new GestionPrimos(numeros);
            gestorPrimos.procesarPrimos2();
            System.out.println(gestorPrimos);
            if (gestorFicheros.escribir(gestorPrimos, "Prueba11Result.csv"))
                System.out.println("\n\tTodo ha ido correctamente");

            else
                System.out.println("\n\tError al escribir en el fichero");
        }

        //*********************************************
        //PRUEBA 12: Prueba para comparativas de tiempos con los dos algoritmos de encontrarPrimo
        System.out.println("\n---> PRUEBA FUNCION COMPARATIVA");
        System.out.println("\nPrueba12: Comparativa tiempos para ambos algoritmos de encontrarPrimo.");
        Comparador comp = new Comparador();
        BigInteger num1 = new BigInteger("0");
        BigInteger num2 = new BigInteger("265000");
        boolean encontrado=comp.comparaTiempo(num1, num2, "Prueba12Result");

    }


}

