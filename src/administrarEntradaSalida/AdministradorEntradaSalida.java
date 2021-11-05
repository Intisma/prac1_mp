package administrarEntradaSalida;

import procesado.GestionPrimos;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;


public class AdministradorEntradaSalida {

    /**
     * Constructor vacío
     */
    public AdministradorEntradaSalida() {

    }

    /**
     * Lee información de un fichero con formato CSV
     *
     * @param nomFichero nombre del fichero del que se leera la información
     * @return devuelve una lista de Strings con los números leídos del fichero
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
                if (texto != null & !Objects.equals(texto, "")) {
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
     * Escribe la información almacenada en un objeto de clase GestionPrimos en un fichero nuevo que tendrá
     * el nombre introducido por parámetro.
     *
     * @param gestor     será el parámetro del que obtendremos la información a escribir
     * @param nomFichero será el nombre que tomará el fichero en el que escribamos
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
