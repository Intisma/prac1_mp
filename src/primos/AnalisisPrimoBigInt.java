package primos;

import java.math.BigInteger;

/**
 * Clase que nos permite a partir de un numero calcular su primo mayor que sea inferior o igual al numero y nos calcula
 * tambien el tiempo tardado.
 */
public class AnalisisPrimoBigInt implements AnalisisPrimo {
    private final BigInteger num;
    private BigInteger primoResult;
    private long tiempo;

    /**
     * Constructor
     *
     * @param num numero a guardar en la variable num
     */
    public AnalisisPrimoBigInt(String num) {
        this.num = new BigInteger(num);
    }

    /**
     * Comprueba si el numero es par
     *
     * @return booleano que indica si es par o no
     */
    private boolean esPar() {
        return (num.mod(BigInteger.TWO).equals(BigInteger.ZERO));
    }

    /**
     * Se busca el numero primo mas grande que sea menor o igual al numero que se encuentra
     * en la variable num. Una vez encontrado, se guarda en la variable primoResult. Si no
     * se ha encontrado un primo menor o igual que el numero de la variable num, se guardara 1
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
     * Sobrecarga del metodo encontrarPrimoMayor. Se busca el numero primo mas grande que sea menor o igual al numero
     * que se encuentra en la variable num. Una vez encontrado, se guarda en la variable primoResult. Si no
     * se ha encontrado un primo menor o igual que el numero de la variable num, se guardara 1
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
     * @return string con la informacion del objeto en formato pantalla
     */
    public String toString() {
        return "\n\tNumero entrada: " + num + " \n\tNumero primo: " + primoResult + "\n\tTiempo: " + tiempo + "ms";
    }

    /**
     * Transforma el objeto a formato string para escribirlo en ficheros csv
     *
     * @return string con la informacion del objeto en formato csv
     */
    public String toStringFichero() {
        return num + ";" + primoResult + ";" + tiempo + "\n";
    }
}
