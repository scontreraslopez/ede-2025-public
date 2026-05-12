# Ejercicio — JUnit 5: ConversorUnidades

**Tipo**: Pruebas unitarias  
**Tiempo estimado**: 25-30 min  
---

## Instrucciones

Puedes crear un nuevo proyecto en IntelliJ y usar los sources o descomprimir el .zip que se te ha proporcionado y abrirlo en IntelliJ.

## Contexto

Tienes implementada la clase `ConversorUnidades` con métodos para convertir distancias y temperaturas. Tu tarea es escribir los tests unitarios que la verifiquen. Para ello ya cuentas con la clase de test `ConversorUnidadesTest` con la estructura básica, pero sin contenido.

---

## Especificación por ejemplos

La siguiente tabla recoge los resultados reales de cada método para distintas entradas. **No tienes que escribir un test por cada fila**: tu tarea es seleccionar los casos que, aplicando BVA, realmente aporten valor. Ten en cuenta también que algunos métodos pueden lanzar excepciones para ciertos valores.

| Método | Entrada | Resultado esperado |
|--------|---------|-------------------|
| `kmAMillas` | `1.0` | `0.6214` (±0.0001) |
| `kmAMillas` | `25.0` | `15.5343` (±0.0001) |
| `kmAMillas` | `50.0` | `31.0686` (±0.0001) |
| `kmAMillas` | `100.0` | `62.1371` (±0.0001) |
| `millasAKm` | `1.0` | `1.6093` (±0.0001) |
| `millasAKm` | `5.0` | `8.0467` (±0.0001) |
| `millasAKm` | `10.0` | `16.0934` (±0.0001) |
| `millasAKm` | `20.0` | `32.1869` (±0.0001) |
| `celsiusAFahrenheit` | `-40.0` | `-40.0` |
| `celsiusAFahrenheit` | `0.0` | `32.0` |
| `celsiusAFahrenheit` | `20.0` | `68.0` |
| `celsiusAFahrenheit` | `37.0` | `98.6` |
| `celsiusAFahrenheit` | `100.0` | `212.0` |

---

## Se te pide

Completa `ConversorUnidadesTest.java` para verificar el comportamiento de la clase. Ten en cuenta lo siguiente:

- Usa **`@BeforeEach`** para crear la instancia de `ConversorUnidades` antes de cada test.
- Usa nombres de test descriptivos y estructura **Given-When-Then** (aunque sea en comentarios).
- Busca una **buena cobertura**: no todas las filas de la tabla tienen el mismo valor como caso de prueba; razona cuáles son más representativas.
- Aplica la técnica **BVA (Boundary Value Analysis)**: presta especial atención a los valores frontera de cada método (cero, valores muy próximos al límite, etc.).
- Para `esNegativo`, usa un **`@ParameterizedTest`** con `@ValueSource` en lugar de tests repetidos.

---

## Pistas

- Para comparar `double` usa `assertEquals(esperado, actual, delta)`.
- La sintaxis de `assertThrows`:
  ```java
  assertThrows(IllegalArgumentException.class, () -> conversor.kmAMillas(-5));
  ```
- ¡No olvides mirar la cheatsheet de JUnit 5 para recordar la sintaxis!

## Solución

Dejo el código de la solución de referencia en [./ConversorUnidadesTest_solucion.java](./ConversorUnidadesTest_solucion.java). No es la única forma de hacerlo, pero te puede servir para comparar tu solución.


