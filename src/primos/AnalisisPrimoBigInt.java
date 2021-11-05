package primos;

import java.math.BigInteger;

public class AnalisisPrimoBigInt implements AnalisisPrimo {
    private final BigInteger num;
    private BigInteger primoResult;
    private long tiempo;

    /**
     * Constructor
     *
     * @param num número a guardar en la variable num
     */
    public AnalisisPrimoBigInt(String num) {
        this.num = new BigInteger(num);
    }

    /**
     * Comprueba si el número es par
     *
     * @return booleano que indica si es par o no
     */
    private boolean esPar() {
        return (num.mod(BigInteger.TWO).equals(BigInteger.ZERO));
    }

    /**
     * Comprueba si el número introducido por parámetro es primo
     *
     * @param num número a comprobar si es primo
     * @return booleano indicando si el número es o no primo
     */
    private boolean esPrimo(BigInteger num) {
        boolean primo = true;
        BigInteger max = num.sqrt();
        if ((num.equals(BigInteger.ZERO)) || (num.equals(BigInteger.ONE)))
            primo = false;
        else if (!(num.equals(BigInteger.TWO))) {
            if (num.mod(BigInteger.TWO).equals(BigInteger.ZERO))
                primo = false;
            else {
                for (BigInteger i = new BigInteger("3"); i.compareTo(max) != 1; i = i.add(BigInteger.TWO)) {
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
     * Se busca el número primo más grande que sea menor o igual al número que se encuentra
     * en la variable num. Una vez encontrado, se guarda en la variable primoResult
     */
    public void encontrarPrimoMayor() {
        long tiempoInicio = System.currentTimeMillis();
        boolean primoEncontrado = false;
        if (!this.esPar())
            primoResult = num;
        else
            primoResult = num.subtract(BigInteger.ONE);

        while ((primoResult.compareTo(BigInteger.ONE) == 1) && (!primoEncontrado)) {
            primoEncontrado = this.esPrimo(primoResult);
            if (!primoEncontrado)
                primoResult = primoResult.subtract(BigInteger.TWO);
        }

        long tiempoFinal = System.currentTimeMillis();
        tiempo = tiempoFinal - tiempoInicio;
    }

    /**
     * Transforma el objeto a formato string para mostrarlo por pantalla
     *
     * @return string con la información del objeto en formato pantalla
     */
    public String toString() {
        return "\nNumero entrada: " + num + " \nNumero primo: " + primoResult + "\nTiempo: " + tiempo + "ms\n";
    }

    /**
     * Transforma el objeto a formato string para escribirlo en ficheros csv
     *
     * @return string con la información del objeto en formato csv
     */
    public String toStringFichero() {
        return num + ";" + primoResult + ";" + tiempo + "\n";
    }
}
