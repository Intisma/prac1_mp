package primos;

import java.math.BigInteger;
import java.util.Random;

public class NumPrimo {

    // El generador de números aleatorios para el segundo método de comprobar si un número es primo
    private static final Random rnd = new Random();

    /**
     * Comprobará si el número introducido por parámetro es o no primo
     *
     * @param num el número que comprobaremos si es primo
     * @return booleano indicando si es o no primo
     */
    public static boolean esPrimo(long num) {
        boolean primo = true;
        long max = (long) Math.sqrt(num);
        if (num == 0 || num == 1)
            primo = false;
        else if (num != 2) {
            if (num % 2 == 0)
                primo = false;
            else {
                for (int i = 3; i <= max; i += 2) {
                    if (num % i == 0) {
                        primo = false;
                        break;
                    }
                }
            }
        }
        return primo;
    }

    /**
     * Comprobará si el número introducido por parámetro es o no primo
     *
     * @param num el número que comprobaremos si es primo
     * @return booleano indicando si es o no primo
     */
    public static boolean esPrimo(BigInteger num) {
        boolean primo = true;
        BigInteger max = num.sqrt();
        if ((num.equals(BigInteger.ZERO)) || (num.equals(BigInteger.ONE)))
            primo = false;
        else if (!(num.equals(BigInteger.TWO))) {
            if (num.mod(BigInteger.TWO).equals(BigInteger.ZERO))
                primo = false;
            else {
                for (BigInteger i = new BigInteger("3"); i.compareTo(max) < 1; i = i.add(BigInteger.TWO)) {
                    if (num.mod(i).equals(BigInteger.ZERO)) {
                        primo = false;
                        break;
                    }
                }
            }
        }
        return primo;
    }

    /**
     * Método que comprueba con el test de miller si el número introducido es primo
     *
     * @param numero    es el número que comprobaremos si es primo
     * @param precision cuanto mayor sea el número, más buena será la predicción
     * @return true si el número es primo, false si es compuesto
     */
    public static boolean esPrimo2(BigInteger numero, int precision) {
        if (numero.compareTo(BigInteger.ONE) < 1)
            return false;
        if (numero.mod(BigInteger.TWO).equals(BigInteger.ZERO) && (!numero.equals(BigInteger.TWO)))
            return false;
        for (int i = 0; i < precision; i++) {
            BigInteger aleatorio;
            do {
                aleatorio = new BigInteger(numero.bitLength(), rnd);
            } while (aleatorio.equals(BigInteger.ZERO) || (aleatorio.compareTo(numero) == 0));
            if (!millerTest(aleatorio, numero)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Método que comprueba con el test de miller si el número introducido es primo
     *
     * @param numero es el número que comprobaremos si es primo
     * @return true si el número es primo, false si es compuesto
     */
    public static boolean esPrimo2(BigInteger numero) {
        if (numero.compareTo(BigInteger.ONE) < 1)
            return false;
        if (numero.mod(BigInteger.TWO).equals(BigInteger.ZERO) && (!numero.equals(BigInteger.TWO)))
            return false;
        for (int i = 0; i < 20; i++) {
            BigInteger aleatorio;
            do {
                aleatorio = new BigInteger(numero.bitLength(), rnd);
            } while (aleatorio.equals(BigInteger.ZERO) || (aleatorio.compareTo(numero) == 0));
            if (!millerTest(aleatorio, numero)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Método que comprueba con el test de miller si el número introducido es primo
     *
     * @param lNumero   es el número que comprobaremos si es primo
     * @param precision cuanto mayor sea el número, más buena será la predicción
     * @return true si el número es primo, false si es compuesto
     */
    public static boolean esPrimo2(long lNumero, int precision) {
        BigInteger numero = new BigInteger(Long.toString(lNumero));
        if (numero.compareTo(BigInteger.ONE) < 1)
            return false;
        if (numero.mod(BigInteger.TWO).equals(BigInteger.ZERO) && (!numero.equals(BigInteger.TWO)))
            return false;
        for (int i = 0; i < precision; i++) {
            BigInteger aleatorio;
            do {
                aleatorio = new BigInteger(numero.bitLength(), rnd);
            } while (aleatorio.equals(BigInteger.ZERO) || (aleatorio.compareTo(numero) == 0));
            if (!millerTest(aleatorio, numero)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Método que comprueba con el test de miller si el número introducido es primo
     *
     * @param lNumero es el número que comprobaremos si es primo
     * @return true si el número es primo, false si es compuesto
     */
    public static boolean esPrimo2(long lNumero) {
        BigInteger numero = new BigInteger(Long.toString(lNumero));
        if (numero.compareTo(BigInteger.ONE) < 1)
            return false;
        if (numero.mod(BigInteger.TWO).equals(BigInteger.ZERO) && (!numero.equals(BigInteger.TWO)))
            return false;
        for (int i = 0; i < 20; i++) {
            BigInteger aleatorio;
            do {
                aleatorio = new BigInteger(numero.bitLength(), rnd);
            } while (aleatorio.equals(BigInteger.ZERO) || (aleatorio.compareTo(numero) == 0));
            if (!millerTest(aleatorio, numero)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Función de miller.
     *
     * @param aleatorio número aleatorio calculado anteriormente
     * @param numero    número a comprobar si es primo
     * @return false si el número es compuesto, true si puede ser primo
     */
    private static boolean millerTest(BigInteger aleatorio, BigInteger numero) {
        BigInteger numMenosUno = numero.subtract(BigInteger.ONE);
        BigInteger d = numMenosUno;
        int s = d.getLowestSetBit();
        d = d.shiftRight(s);
        BigInteger aleatorioElevado = aleatorio.modPow(d, numero);
        if (aleatorioElevado.equals(BigInteger.ONE)) return true;
        for (int i = 0; i < s - 1; i++) {
            if (aleatorioElevado.equals(numMenosUno)) return true;
            aleatorioElevado = aleatorioElevado.multiply(aleatorioElevado).mod(numero);
        }
        return aleatorioElevado.equals(numMenosUno);
    }

}
