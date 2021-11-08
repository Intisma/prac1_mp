package administrarEntradaSalida;

import procesado.GestionPrimos;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;


public class AdministradorEntradaSalida {

    /**
     * Constructor vacio
     */
    public AdministradorEntradaSalida() {

    }

    /**
     * Lee informacion de un fichero con formato CSV
     *
     * @param nomFichero nombre del fichero del que se leera la informacion
     * @return devuelve una lista de Strings con los numeros leidos del fichero
     */
    public String[] leer(String nomFichero) {
        int nums = 0, j = 0;
        try {
            BufferedReader f = new BufferedReader(new FileReader(nomFichero));
            int numLineas = (int) f.lines().count();
            f.close();
            Scanner ft = new Scanner(new File(nomFichero));
            String[] aux = new String[numLineas];
            int i;
            for (i = 0; i < numLineas; i++) {
                String texto = ft.nextLine();
                if (texto != null & !Objects.equals(texto, "") && (texto.matches("[+-]?\\d*(\\.\\d+)?"))) {
                    aux[j] = texto;
                    j++;
                    nums++;
                }
            }
            ft.close();
            String[] numeros = new String[nums];
            for (i = 0; i < nums; i++) numeros[i] = aux[i];
            return numeros;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Escribe la informacion almacenada en un objeto de clase GestionPrimos en un fichero nuevo que tendra
     * el nombre introducido por parametro
     *
     * @param gestor     sera el parametro del que obtendremos la informacion a escribir
     * @param nomFichero sera el nombre que tomara el fichero en el que escribamos
     * @return booleano indicando si se ha escrito correctamente o ha habido un error con el fichero
     */
    public boolean escribir(GestionPrimos gestor, String nomFichero) {
        try {
            BufferedWriter fresul = new BufferedWriter(new FileWriter(nomFichero));
            fresul.write("Numero;Primo;Tiempo(ms)\n");
            fresul.write(gestor.toStringFichero());
            fresul.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
