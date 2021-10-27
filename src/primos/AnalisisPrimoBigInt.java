package primos;

import java.math.BigInteger;

public class AnalisisPrimoBigInt implements AnalisisPrimo {
    private final BigInteger num;
    private BigInteger primoResult;
    private long tiempo;
    private boolean esPar, primoEncontrado;

    public AnalisisPrimoBigInt(String num) {
        this.num = new BigInteger(num);
    }

    public String getNum() {
        return "" + num;
    }

    public String getPrimoResult() {
        return "" + primoResult;
    }

    public String getTiempo() {
        return "" + tiempo;
    }

    public boolean getPrimoEncontrado() {
        return primoEncontrado;
    }

    private void esPar() {
        esPar = num.mod(BigInteger.TWO).equals(BigInteger.ZERO);
    }

    private boolean esPrimo(BigInteger num) {
        boolean primo = true;
        BigInteger max = num.sqrt();
        if ((num.equals(BigInteger.ZERO)) || (num.equals(BigInteger.ONE)))
            primo = false;
        else if (!(num.equals(BigInteger.TWO))) {
            if (num.mod(BigInteger.TWO).equals(BigInteger.ZERO))
                primo = false;
            else {
                for (BigInteger i = new BigInteger("3"); i.compareTo(max) != 1; i = i.add(BigInteger.TWO)) {
                    if (num.mod(i).equals(BigInteger.ZERO)) {
                        primo = false;
                        break;
                    }
                }
            }
        }

        return primo;
    }

    public void encontrarPrimoMayor() {
        long tiempoInicio = System.currentTimeMillis();
        primoEncontrado = false;
        this.esPar();
        if (!esPar)
            primoResult = num;
        else
            primoResult = num.subtract(BigInteger.ONE);

        while ((primoResult.compareTo(BigInteger.ONE) == 1) && (!primoEncontrado)) {
            primoEncontrado = this.esPrimo(primoResult);
            if (!primoEncontrado)
                primoResult = primoResult.subtract(BigInteger.TWO);
        }

        long tiempoFinal = System.currentTimeMillis();
        tiempo = tiempoFinal - tiempoInicio;
    }

    public String toString() {
        return "\nNumero entrada: " + num + " \nNumero primo: " + primoResult + "\nTiempo: " + tiempo + "ms\n";
    }
    
    public String toStringFichero() {
        return num + ";" + primoResult + ";" + tiempo + "\n";
    }
}
