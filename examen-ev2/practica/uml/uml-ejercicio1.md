# Enunciado - Parking UML

Dibuja en la hoja facilitada el **diagrama de clases UML** que modele la siguiente situación:

Un **Parking** tiene un nombre y una capacidad máxima, y está compuesto por varias **Plazas**. Si el Parking cierra, sus Plazas dejan de existir.

Cada Plaza tiene un número y un estado (libre u ocupada), y expone el método `estaOcupada()` que devuelve un booleano. Hay dos tipos de Plaza: **PlazaEstandar** y **PlazaElectrica** (que añade el atributo `potenciaCargador` en kW).

Un **Vehiculo** tiene matrícula y marca. Los vehículos pueden aparcarse en una Plaza, pero una Plaza solo puede tener un Vehículo a la vez (o ninguno). Un Vehículo puede no estar aparcado.

El **Parking** expone el método `plazasLibres()` que devuelve un entero.

## Se te pide

1. Identificar las **clases** con sus atributos principales.
2. Modelar las **relaciones** con el tipo correcto (composición, asociación, herencia).
3. Indicar las **multiplicidades** (cardinalidad) donde proceda.
4. Usar la **visibilidad** correcta en atributos y métodos (`-` privado, `+` público).
5. **No es necesario incluir getters ni setters.**