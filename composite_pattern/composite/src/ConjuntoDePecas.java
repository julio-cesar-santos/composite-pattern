import java.util.ArrayList;
import java.util.List;

public class ConjuntoDePecas implements CarroParte {
    private String nome;
    private List<CarroParte> partes = new ArrayList<>();

    public ConjuntoDePecas(String nome) {
        this.nome = nome;
    }

    public void adicionarParte(CarroParte parte) {
        partes.add(parte);
    }

    @Override
    public double getPeso() {
        double total = 0.0;
        for (CarroParte parte : partes) {
            total += parte.getPeso(); // recursivo
        }
        return total;
    }
}
