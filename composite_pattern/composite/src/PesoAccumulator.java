public class PesoAccumulator {
    private static double soma = 0.0;

    public static double adicionarPeso(String nome, double peso) {
        soma += peso;
        return soma;
    }

    public static void reset() {
        soma = 0.0;
    }

    public static double getTotal() {
        return soma;
    }
}
