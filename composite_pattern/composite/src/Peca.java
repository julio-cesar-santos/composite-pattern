public class Peca implements CarroParte {
    private String nome;
    private double peso;

    public Peca(String nome, double peso) {
        this.nome = nome;
        this.peso = peso;
    }

    @Override
    public double getPeso() {
        // Mensagem de contabilização
        double somaParcial = PesoAccumulator.adicionarPeso(this.nome, this.peso);
        System.out.println("Somando agora o peso de " + this.nome + ": " + this.peso + ". Total parcial: " + somaParcial);
        return this.peso;
    }
}
