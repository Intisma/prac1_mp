package primos;

public class AnalisisPrimoLong implements AnalisisPrimo {
    private final long num;
    private long primoResult;
    private long tiempo;

    /**
     * Constructor
     *
     * @param num numero a guardar en la variable num
     */
    public AnalisisPrimoLong(String num) {
        this.num = Long.parseLong(num);
    }


    /**
     * Comprueba si el numero es par
     *
     * @return booleano que indica si es par o no
     */
    private boolean esPar() {
        return (num % 2 == 0);
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
        if (num >= 2) {
            if (!this.esPar() || num == 2)
                primoResult = num;
            else
                primoResult = num - 1;

            while ((primoResult > 1) && (!primoEncontrado)) {
                primoEncontrado = NumPrimo.esPrimo(primoResult);
                if (!primoEncontrado)
                    primoResult -= 2;
            }
        } else {
            primoResult = 1;
        }
        long tiempoFinal = System.currentTimeMillis();
        tiempo = tiempoFinal - tiempoInicio;
    }

    /**
     * Sobrecarga de encontrarPrimoMayor. Se busca el numero primo mas grande que sea menor o igual
     * al numero que se encuentra en la variable num. Una vez encontrado, se guarda en la variable primoResult.
     * Si no se ha encontrado un primo menor o igual que el numero de la variable num, se guardara 1
     * en primo
     */
    public void encontrarPrimoMayor2() {
        long tiempoInicio = System.currentTimeMillis();
        boolean primoEncontrado = false;
        if (num >= 2) {
            if (!this.esPar() || num == 2)
                primoResult = num;
            else
                primoResult = num - 1;

            while ((primoResult > 1) && (!primoEncontrado)) {
                primoEncontrado = NumPrimo.esPrimo2(primoResult);
                if (!primoEncontrado)
                    primoResult -= 2;
            }
        } else {
            primoResult = 1;
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
