```mermaid
classDiagram
    direction TB

    interface CarroParte {
        +getPeso() double
    }

    class Peca {
        -String nome
        -double peso
        +Peca(String nome, double peso)
        +getPeso() double
    }

    class ConjuntoDePecas {
        -String nome
        -List<CarroParte> partes
        +ConjuntoDePecas(String nome)
        +adicionarParte(CarroParte parte) void
        +getPeso() double
    }

    CarroParte <|.. Peca
    CarroParte <|.. ConjuntoDePecas
    ConjuntoDePecas "1" o-- "*" CarroParte
```
