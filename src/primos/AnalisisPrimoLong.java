package primos;

public class AnalisisPrimoLong implements AnalisisPrimo {
    private final long num;
    private long primoResult;
    private long tiempo;
    private boolean esPar, primoEncontrado;

    public AnalisisPrimoLong(String num) {
        this.num = Long.parseLong(num);
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
        esPar = num % 2 == 0;
    }

    private boolean esPrimo(long num) {
        boolean primo = true;
        long max = (long) Math.sqrt(num);
        if (num == 0 || num == 1)
            primo = false;
        else if (num != 2) {
            if (num % 2 == 0)
                primo = false;
            else {
                for (int i = 3; i <= max; i += 2) {
                    if (num % i == 0) {
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
            primoResult = num - 1;

        while ((primoResult > 1) && (!primoEncontrado)) {
            primoEncontrado = this.esPrimo(primoResult);
            if (!primoEncontrado)
                primoResult -= 2;
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
