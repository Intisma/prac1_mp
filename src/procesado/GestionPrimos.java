package procesado;

import primos.AnalisisPrimo;
import primos.AnalisisPrimoBigInt;
import primos.AnalisisPrimoLong;

import java.math.BigInteger;

public class GestionPrimos {
    private final AnalisisPrimo[] numeros;

    /**
     * Constructor
     *
     * @param numeros es un string con el cuál llenaremos la variable numeros (vector de AnalisisPrimo). Crearemos
     *                un AnalisisPrimoLong o AnalisisPrimoBigInt según el tamaño del número del String
     */
    public GestionPrimos(String[] numeros) {
        int size = numeros.length;
        BigInteger comprobarLong;
        this.numeros = new AnalisisPrimo[size];
        for (int indice = 0; indice < size; indice++) {
            if (numeros[indice].length() < 19)
                this.numeros[indice] = new AnalisisPrimoLong(numeros[indice]);
            else {
                comprobarLong = new BigInteger(numeros[indice]);
                if (comprobarLong.compareTo(new BigInteger("9223372036854775807")) > 0)
                    this.numeros[indice] = new AnalisisPrimoBigInt(numeros[indice]);
                else
                    this.numeros[indice] = new AnalisisPrimoLong(numeros[indice]);

            }
        }
    }

    /**
     * Se encarga de recorrer la lista de AnalisisPrimo y encontrar el número primo mayor, menor o igual al número
     * que hay en estas variables.
     */
    public void procesarPrimos() {
        for (AnalisisPrimo num : numeros) {
            num.encontrarPrimoMayor();
        }
    }

    /**
     * Crea un String con la información de todas las variables de la lista de AnalisisPrimo para mostrar por
     * pantalla.
     *
     * @return string con la información de numeros
     */
    public String toString() {
        StringBuilder total = new StringBuilder();
        for (AnalisisPrimo num : numeros) {
            total.append(num.toString());
        }
        return total.toString();
    }

    /**
     * Crea un String con la información de todas las variables de la lista de AnalisisPrimo para escribir en
     * un fichero CSV
     *
     * @return string con la información de numeros en formato CSV
     */
    public String toStringFichero() {
        StringBuilder total = new StringBuilder();
        for (AnalisisPrimo num : numeros) {
            total.append(num.toStringFichero());
        }
        return total.toString();
    }
}
