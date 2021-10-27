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
    	String[] test1 = leerFichero("test1.txt");
    	if (test1 != null) {
    		GestionPrimos gestor = new GestionPrimos(test1);
    		gestor.procesarPrimos();
    		System.out.println(gestor);
    		escribirFichero(gestor,"ficheroSalida1.csv");
    	}
    	
    	System.out.println("Ejecutando segundo test...");
    	String[] test2 = leerFichero("fichero.txt");
    	if (test2 != null) {
    		GestionPrimos gestor = new GestionPrimos(test2);
    		gestor.procesarPrimos();
    		System.out.println(gestor);
    		escribirFichero(gestor,"ficheroSalida2.csv");
    	}
        
    }
    
    
       private static String[] leerFichero(String nomFit)  throws IOException {
		String[] numeros, aux;
		int i, linias, nums=0, j=0;	
	    String texto;
		
			try {
				BufferedReader  f = new BufferedReader(new FileReader(nomFit));
				linias = (int)f.lines().count();
				f.close();
				Scanner ft=new Scanner(new File(nomFit));
				aux = new String[linias];
				for (i=0;i<linias;i++){
				   texto=ft.nextLine(); 
				   if (texto!=null & texto != "" ) {
					   aux[j]=texto;
				       j++;
					   nums++;
				   }
				}
				ft.close();
				
				numeros = new String[nums];
				for (i=0;i<nums;i++) {numeros[i]=aux[i]; System.out.println(numeros[i]);}
				return numeros;	
				
			}catch (IOException e){
				System.out.println("Error!! Fichero no existe");
				return null;
				
			}
			
	}


	private static void escribirFichero( GestionPrimos gestor, String nomFit) throws IOException {
		
			BufferedWriter fresul=new BufferedWriter(new FileWriter(nomFit));

			fresul.write("Numero;Primo;Tiempo\n");
			fresul.write(gestor.toStringFichero());
			fresul.close();
				
	}
}

