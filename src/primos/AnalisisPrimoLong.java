package primos;

public class AnalisisPrimoLong implements AnalisisPrimo {
    private final long num;
    private long primoResult;
    private long tiempo;

    /**
     * Constructor
     *
     * @param num número a guardar en la variable num
     */
    public AnalisisPrimoLong(String num) {
        this.num = Long.parseLong(num);
    }


    /**
     * Comprueba si el número es par
     *
     * @return booleano que indica si es par o no
     */
    private boolean esPar() {
        return (num % 2 == 0);
    }

    /**
     * Comprueba si el número introducido por parámetro es primo
     *
     * @param num número a comprobar si es primo
     * @return booleano indicando si el número es o no primo
     */
    private boolean esPrimo(long num) {
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
     * Se busca el número primo más grande que sea menor o igual al número que se encuentra
     * en la variable num. Una vez encontrado, se guarda en la variable primoResult
     */
    public void encontrarPrimoMayor() {
        long tiempoInicio = System.currentTimeMillis();
        boolean primoEncontrado = false;
        if (!this.esPar())
            primoResult = num;
        else
            primoResult = num - 1;

        while ((primoResult > 1) && (!primoEncontrado)) {
            primoEncontrado = this.esPrimo(primoResult);
            if (!primoEncontrado)
                primoResult -= 2;
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
