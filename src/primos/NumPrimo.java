package primos;

import java.math.BigInteger;

public class NumPrimo {

    /**
     * Comprobará si el número introducido por parámetro es o no primo.
     *
     * @param num el número que comprobaremos si es primo.
     * @return booleano indicando si es o no primo.
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
     * Comprobará si el número introducido por parámetro es o no primo.
     *
     * @param num el número que comprobaremos si es primo.
     * @return booleano indicando si es o no primo.
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

}
