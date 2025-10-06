# Histórico de prompts

*A IA utilizada para a realização dessa atividade foi o ChatGPT 5*

Prompt: 
1. Persona e Missão

    Persona: Você é um Arquiteto de Software Sênior especializado em Java e na aplicação de Padrões de Projeto para modelar estruturas de objetos complexas e hierárquicas.

    Missão: Sua missão é projetar e implementar uma aplicação Java que modele a estrutura de um carro como uma composição de partes. O objetivo é utilizar o padrão de projeto Composite para tratar tanto as peças individuais (folhas) quanto os conjuntos de peças (compostos) de maneira uniforme, permitindo o cálculo recursivo do peso total do carro.

2. Descrição do Problema (Ponto de Partida)

O desafio é criar do zero um modelo de classes que represente a hierarquia de componentes de um carro. A estrutura é definida da seguinte forma:

    Carro (o objeto raiz, um composto)

        Carroceria (composto)

            Para-lamas (peça individual/folha)

            Portas (peça individual/folha)

            Painéis (peça individual/folha)

            Porta-malas (peça individual/folha)

            Capô (peça individual/folha)

        Chassi (composto)

            Trem de Força (composto)

                Motor (peça individual/folha)

                Transmissão (peça individual/folha)

                Diferencial (peça individual/folha)

                Rodas (peça individual/folha)

            Suspensão (peça individual/folha)

A característica fundamental é que cada elemento da hierarquia, seja uma peça simples ou um conjunto, possui nome (String) e peso (double).

3. Especificação da Implementação (O Desafio)

Você deve executar os seguintes passos para implementar o padrão Composite:

3.1. Criar a Abstração do Componente

    Crie uma nova interface pública chamada CarroParte.java.

    Esta interface representará a abstração unificada para qualquer parte do carro.

    Ela deve conter um único método: double getPeso().

3.2. Implementar a "Folha" (Leaf)

    Crie uma classe pública chamada Peca.java que implementa a interface CarroParte.

    Esta classe representará as partes individuais que não podem ter sub-partes (ex: Motor, Porta).

    Ela deve ter atributos para nome (String) e peso (double), inicializados via construtor.

    A implementação do método getPeso() nesta classe é crucial: ela deve imprimir a mensagem de contabilização no console (veja Critérios de Aceitação) e depois retornar o seu próprio peso.

3.3. Implementar o "Composto" (Composite)

    Crie uma classe pública chamada ConjuntoDePecas.java que também implementa a interface CarroParte.

    Esta classe representará os componentes que são formados por outras CarroParte (ex: Chassi, Carroceria).

    Ela deve conter uma lista interna para armazenar seus componentes filhos (ex: List<CarroParte> partes).

    Deve possuir métodos para gerenciar seus filhos, como adicionarParte(CarroParte parte).

    A implementação do método getPeso() será recursiva: ela deve iterar sobre todos os seus filhos, invocar getPeso() em cada um deles e retornar a soma total. O peso próprio de um ConjuntoDePecas pode ser considerado zero, já que seu peso total é derivado de seus constituintes.

3.4. Criar o Cliente (Montagem da Estrutura)

    Na classe Main.java, você deve instanciar e montar a árvore de objetos completa do Carro, conforme a hierarquia descrita na Seção 2.

    Você é livre para atribuir pesos fictícios, mas realistas, para cada Peca individual.

4. Critérios de Aceitação

Para que a tarefa seja considerada concluída, a execução da classe Main deve:

    Calcular o peso total corretamente, que deve ser a soma exata de todas as peças individuais (folhas) que você definiu.

    Imprimir no console uma linha para cada peça individual no momento em que seu peso é contabilizado. A saída deve seguir exatamente o formato abaixo, onde SOMA_PARCIAL representa o total acumulado até aquele ponto do cálculo:
    "Somando agora o peso de NOME_DA_PARTE: PESO_DA_PARTE. Total parcial: SOMA_PARCIAL"

    Ao final, imprimir o peso total do carro.

Exemplo de Saída (considerando apenas duas peças para simplificar):
Se um Carro fosse composto apenas por um Motor (150kg) e uma Roda (25kg), a saída esperada seria:

Somando agora o peso de Motor: 150.0. Total parcial: 150.0
Somando agora o peso de Roda: 25.0. Total parcial: 175.0
Peso total do Carro: 175.0

5. Entregáveis

Forneça o código-fonte completo e final para os seguintes arquivos, prontos para compilação e execução, juntamente com o diagrama:

    Diagrama de Classes (Mermaid): Um diagrama em sintaxe Mermaid que ilustre a relação entre CarroParte, Peca e ConjuntoDePecas.

    CarroParte.java (Nova interface)

    Peca.java (Nova classe - Leaf)

    ConjuntoDePecas.java (Nova classe - Composite)

    Main.java (Classe cliente que monta o carro e dispara o cálculo)
    
Solução:

Diagrama de Classes (Mermaid)

classDiagram
    direction TB

    interface CarroParte {
        +double getPeso()
    }

    class Peca {
        -String nome
        -double peso
        +Peca(String nome, double peso)
        +double getPeso()
    }

    class ConjuntoDePecas {
        -String nome
        -List<CarroParte> partes
        +ConjuntoDePecas(String nome)
        +void adicionarParte(CarroParte parte)
        +double getPeso()
    }

    CarroParte <|.. Peca
    CarroParte <|.. ConjuntoDePecas
    ConjuntoDePecas "1" o-- "*" CarroParte


Código-Fonte Java

CarroParte.java

public interface CarroParte {
    double getPeso();
}

Peca.java (Leaf)

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

ConjuntoDePecas.java (Composite)

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

PesoAccumulator.java (Classe auxiliar para manter soma parcial)

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

Main.java (Cliente)

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

Avaliação: O código funciona perfeitamente e como saída obtemos. Apenas com um problema ao gerar o diagrama

Somando agora o peso de Para-lamas: 15.0. Total parcial: 15.0
Somando agora o peso de Portas: 30.0. Total parcial: 45.0
Somando agora o peso de Painéis: 25.0. Total parcial: 70.0
Somando agora o peso de Porta-malas: 20.0. Total parcial: 90.0
Somando agora o peso de Capô: 18.0. Total parcial: 108.0
Somando agora o peso de Motor: 150.0. Total parcial: 258.0
Somando agora o peso de Transmissão: 70.0. Total parcial: 328.0
Somando agora o peso de Diferencial: 40.0. Total parcial: 368.0
Somando agora o peso de Rodas: 80.0. Total parcial: 448.0
Somando agora o peso de Suspensão: 60.0. Total parcial: 508.0
Peso total do Carro: 508.0

Process finished with exit code 0
