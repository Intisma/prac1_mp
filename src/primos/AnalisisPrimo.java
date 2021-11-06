package primos;

public interface AnalisisPrimo {

    /**
     * Se busca el número primo más grande que sea menor o igual al número que se encuentra
     * en la variable num. Una vez encontrado, se guarda en la variable primoResult
     */
    void encontrarPrimoMayor();

    /**
     * Transforma el objeto a formato string para mostrarlo por pantalla
     *
     * @return string con la información del objeto en formato pantalla
     */
    String toString();

    /**
     * Transforma el objeto a formato string para escribirlo en ficheros csv
     *
     * @return string con la información del objeto en formato csv
     */
    String toStringFichero();

}
