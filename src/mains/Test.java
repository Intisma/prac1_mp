package mains;

import procesado.GestionPrimos;

public class Test {

    public static void main(String[] args) {
        String[] numeros = {"843", "465", "3", "656", "235", "1111111111111111112"};
        GestionPrimos gestor = new GestionPrimos(numeros);
        gestor.procesarPrimos();
        System.out.println(gestor);
    }
}
