package procesado;

import primos.AnalisisPrimo;
import primos.AnalisisPrimoBigInt;
import primos.AnalisisPrimoLong;

public class GestionPrimos {
    private final AnalisisPrimo[] numeros;

    public GestionPrimos(String[] numeros) {
        int size = numeros.length;
        this.numeros = new AnalisisPrimo[size];
        for (int indice = 0; indice < size; indice++) {
            if (numeros[indice].length() < 19)
                this.numeros[indice] = new AnalisisPrimoLong(numeros[indice]);
            else
                this.numeros[indice] = new AnalisisPrimoBigInt(numeros[indice]);
        }
    }

    public void procesarPrimos() {
        for (AnalisisPrimo num : numeros) {
            num.encontrarPrimoMayor();
        }
    }

    public String toString() {
        StringBuilder total = new StringBuilder();
        for (AnalisisPrimo num : numeros) {
            total.append(num.toString());
        }
        return total.toString();
    }
}
