package primos;

public interface AnalisisPrimo {

    /**
     * Se busca el numero primo mas grande que sea menor o igual al numero que se encuentra
     * en la variable num. Una vez encontrado, se guarda en la variable primoResult
     */
    void encontrarPrimoMayor();

    /**
     * Sobrecarga del metodo encontrarPrimoMayor. Se busca el numero primo mas grande que sea menor o igual al numero
     * que se encuentra en la variable num. Una vez encontrado, se guarda en la variable primoResult. Si no
     * se ha encontrado un primo menor o igual que el numero de la variable num, se guardara 1
     * en primo
     */
    void encontrarPrimoMayor2();

    /**
     * Transforma el objeto a formato string para mostrarlo por pantalla
     *
     * @return string con la informacion del objeto en formato pantalla
     */
    String toString();

    /**
     * Transforma el objeto a formato string para escribirlo en ficheros csv
     *
     * @return string con la informacion del objeto en formato csv
     */
    String toStringFichero();

}
