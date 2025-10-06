public class Main {
    public static void main(String[] args) {
        // Resetando acumulador
        PesoAccumulator.reset();

        // Criando as peças individuais
        Peca paraLamas = new Peca("Para-lamas", 15.0);
        Peca portas = new Peca("Portas", 30.0);
        Peca paineis = new Peca("Painéis", 25.0);
        Peca portaMalas = new Peca("Porta-malas", 20.0);
        Peca capo = new Peca("Capô", 18.0);

        Peca motor = new Peca("Motor", 150.0);
        Peca transmissao = new Peca("Transmissão", 70.0);
        Peca diferencial = new Peca("Diferencial", 40.0);
        Peca rodas = new Peca("Rodas", 80.0);
        Peca suspensao = new Peca("Suspensão", 60.0);

        // Criando conjuntos
        ConjuntoDePecas carroceria = new ConjuntoDePecas("Carroceria");
        carroceria.adicionarParte(paraLamas);
        carroceria.adicionarParte(portas);
        carroceria.adicionarParte(paineis);
        carroceria.adicionarParte(portaMalas);
        carroceria.adicionarParte(capo);

        ConjuntoDePecas tremDeForca = new ConjuntoDePecas("Trem de Força");
        tremDeForca.adicionarParte(motor);
        tremDeForca.adicionarParte(transmissao);
        tremDeForca.adicionarParte(diferencial);
        tremDeForca.adicionarParte(rodas);

        ConjuntoDePecas chassi = new ConjuntoDePecas("Chassi");
        chassi.adicionarParte(tremDeForca);
        chassi.adicionarParte(suspensao);

        ConjuntoDePecas carro = new ConjuntoDePecas("Carro");
        carro.adicionarParte(carroceria);
        carro.adicionarParte(chassi);

        // Cálculo total
        double pesoTotal = carro.getPeso();
        System.out.println("Peso total do Carro: " + pesoTotal);
    }
}
