package administrarEntradaSalida;

import procesado.GestionPrimos;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class AdministradorEntradaSalida {
    public String[] leer(String nomFit) {
        int nums = 0, j = 0;
        try {
            BufferedReader f = new BufferedReader(new FileReader(nomFit));
            int numLineas = (int) f.lines().count();
            f.close();
            Scanner ft = new Scanner(new File(nomFit));
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

    public void escribir(GestionPrimos gestor, String nomFit) throws IOException {

        BufferedWriter fresul = new BufferedWriter(new FileWriter(nomFit));

        fresul.write("Numero;Primo;Tiempo(ms)\n");
        fresul.write(gestor.toStringFichero());
        fresul.close();

    }
}
