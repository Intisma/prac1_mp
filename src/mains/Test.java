package mains;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import procesado.GestionPrimos;


public class Test {

    public static void main(String[] args) throws IOException {
      
    	System.out.println("Ejecutando primer test...");
    	String[] test1 = leerFichero("fichero.txt");
        GestionPrimos gestor = new GestionPrimos(test1);
        gestor.procesarPrimos();
        System.out.println(gestor);
        escribirFichero(gestor,"ficheroSalida1.csv");
        
    }
    
    
    private static String[] leerFichero(String nomFit)  throws IOException {
		String[] numeros;
		int i, linias;	
			
		    BufferedReader  f = new BufferedReader(new FileReader(nomFit));
		    linias = (int)f.lines().count();
		    f.close();
		    Scanner ft=new Scanner(new File(nomFit));
			numeros = new String[linias];
			for (i=0;i<linias;i++){
				numeros[i]=ft.nextLine();
			}
			ft.close();
			
			return numeros;	
	}


	private static void escribirFichero( GestionPrimos gestor, String nomFit) throws IOException {
		
			BufferedWriter fresul=new BufferedWriter(new FileWriter(nomFit));

			fresul.write("Numero;Primo;Tiempo\n");
			fresul.write(gestor.toStringFichero());
			fresul.close();
				
	}
}

