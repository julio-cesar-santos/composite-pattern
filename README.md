```mermaid
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
```
