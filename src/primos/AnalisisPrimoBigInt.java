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
     * Se busca el número primo más grande que sea menor o igual al número que se encuentra
     * en la variable num. Una vez encontrado, se guarda en la variable primoResult. Si no
     * se ha encontrado un primo menor o igual que el número de la variable num, se guardará 1
     * en primo
     */
    public void encontrarPrimoMayor() {
        long tiempoInicio = System.currentTimeMillis();
        boolean primoEncontrado = false;
        if (num.compareTo(BigInteger.TWO) > -1) {
            if (!this.esPar() || num.equals(BigInteger.TWO))
                primoResult = num;
            else
                primoResult = num.subtract(BigInteger.ONE);

            while ((primoResult.compareTo(BigInteger.ONE) > 0) && (!primoEncontrado)) {
                primoEncontrado = NumPrimo.esPrimo(primoResult);
                if (!primoEncontrado)
                    primoResult = primoResult.subtract(BigInteger.TWO);
            }
        } else {
            primoResult = new BigInteger("1");
        }
        long tiempoFinal = System.currentTimeMillis();
        tiempo = tiempoFinal - tiempoInicio;
    }

    /**
     * Sobrecarga del método encontrarPrimoMayor. Se busca el número primo más grande que sea menor o igual al número
     * que se encuentra en la variable num. Una vez encontrado, se guarda en la variable primoResult. Si no
     * se ha encontrado un primo menor o igual que el número de la variable num, se guardará 1
     * en primo
     */
    public void encontrarPrimoMayor2() {
        long tiempoInicio = System.currentTimeMillis();
        boolean primoEncontrado = false;
        if (num.compareTo(BigInteger.TWO) > -1) {
            if (!this.esPar() || num.equals(BigInteger.TWO))
                primoResult = num;
            else
                primoResult = num.subtract(BigInteger.ONE);

            while ((primoResult.compareTo(BigInteger.ONE) > 0) && (!primoEncontrado)) {
                primoEncontrado = NumPrimo.esPrimo2(primoResult);
                if (!primoEncontrado)
                    primoResult = primoResult.subtract(BigInteger.TWO);
            }
        } else {
            primoResult = new BigInteger("1");
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
        return "\n\tNumero entrada: " + num + " \n\tNumero primo: " + primoResult + "\n\tTiempo: " + tiempo + "ms";
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
