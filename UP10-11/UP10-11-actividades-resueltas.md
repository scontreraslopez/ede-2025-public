# UP10-11: Práctica guiada — Diseño e implementación de pruebas con JUnit 5

> Esta práctica guiada recorre el proceso real de trabajo en dos fases: primero se **diseñan** los casos de prueba (UP10), luego se **implementan** de forma automatizada (UP11). El punto de partida es un repositorio real en GitHub que el alumno clona y abre en IntelliJ IDEA.

---

## Paso 0: Preparación del entorno

### Clonar el repositorio

Abre una terminal (en Windows, usa Git Bash o el terminal integrado de IntelliJ) y ejecuta:

```bash
git clone https://github.com/scontreraslopez/calculadora-basica.git
cd calculadora-basica
```

### Abrir en IntelliJ IDEA

1. En IntelliJ IDEA: **File → Open** y selecciona la carpeta `calculadora-basica`.
2. Cuando IntelliJ detecte el archivo `build.gradle.kts`, hará la sincronización de Gradle automáticamente. Espera a que finalice (barra de progreso inferior).
3. Verifica la estructura de proyecto en el panel lateral:

```text
calculadora-basica/
├── src/
│   └── main/
│       └── java/
│           └── io/github/scontreraslopez/
│               ├── Calculadora.java
│               └── Main.java
└── build.gradle.kts
```

### Verificar que el proyecto compila y funciona

Abre `Main.java` y ejecútalo (botón ▶ junto al método `main`). Deberías ver en la consola:

```text
5 + 3 = 8.0
8 / 2 = 4.0
```

Si ves esta salida, el proyecto está listo. Dedica un momento a leer `Main.java` para entender el flujo de uso de la calculadora antes de continuar.

---

## FASE 1 — Diseño de pruebas (UP10)

---

### Paso 1: Leer el código bajo prueba

Abre `Calculadora.java` y léelo completo. La clase modela una **calculadora de memoria secuencial**: el usuario introduce un número, selecciona una operación, introduce el segundo número y ejecuta el cálculo. El resultado queda almacenado en memoria (el equivalente al display de una calculadora física).

La API pública completa:

```java
public void ingresarNumero(double numero)
public void seleccionarOperacion(Operacion op)
public void calcular()
public double getMemoria()
public void inicializarMemoria()
public double absoluto(double a)
public boolean esPar(int a)
public int redondearDefecto(double d)
```

Anota dos observaciones antes de continuar:

1. Las operaciones aritméticas **no son métodos directos** como `suma(a, b)`. Son una **secuencia de tres llamadas**: `ingresarNumero` → `seleccionarOperacion` → `calcular`. Esto tiene implicaciones importantes para el diseño de tests: el estado del objeto cambia con cada llamada.
2. `redondearDefecto` implementa el redondeo hacia el entero inferior (`Math.floor`). El javadoc documenta el comportamiento esperado. Reservamos el análisis detallado para el Paso 4 (BVA).

---

### Paso 2: Identificar escenarios y condiciones de prueba

Antes de entrar en valores concretos, identificamos los **escenarios** principales y las **condiciones de prueba** que derivan de cada uno.

**Escenario A**: Estado inicial

**Condición de prueba**: La condición que vamos a probar es que el Objeto recién creado tiene memoria a `0.0` y sin operación pendiente.

**Escenario B**: Introducir un número

**Condición de prueba**: En este casos probamos que el método `ingresarNumero` actualiza la memoria al valor introducido. Es un caso muy simple, pero es importante verificar que el estado interno se modifica correctamente.

**Escenario C**: Operaciones aritméticas básicas (SUMA, RESTA, MULTIPLICACION)

Aquí ya entramos en múltiples situaciones:
    - La secuencia completa con dos positivos devuelve el resultado correcto.
    - La secuencia con un positivo y un negativo devuelve el resultado correcto.
    - La operación con cero devuelve el resultado correcto (ej. multiplicar por cero da cero, o sumar cero no cambia el resultado).

**Escenario D**: División (DIVISION)

**Condición de prueba**: De nuevo nos interesan varias situaciones:
    - División normal (divisor ≠ 0) devuelve el cociente correcto.
    - División por cero con dividendo positivo devuelve `+Infinity` (comportamiento definido en Java, sí para mi esto también fue una sorpresa, pero es así no lanza excepción).

**Escenario E**: Reiniciar memoria (`inicializarMemoria`)

**Condición de prueba**: Tras una operación, `inicializarMemoria` pone la memoria a `0.0` y borra la operación pendiente. Esto es importante para garantizar que cada nueva secuencia de operaciones parte de un estado limpio.

**Escenario F**: Valor absoluto (`absoluto`)

**Condición de prueba**: Verificamos que el método devuelve el valor absoluto correcto para diferentes tipos de entrada:
    - Entrada positiva devuelve el mismo valor
    - Entrada negativa devuelve el valor cambiado de signo
    - Entrada cero devuelve cero

**Escenario G**: Paridad (`esPar`)

**Condición de prueba**: Verificamos el comportamiento para diferentes tipos de enteros:
    - Entero par positivo devuelve `true`
    - Entero impar positivo devuelve `false`
    - Cero devuelve `true`
    - Entero par negativo devuelve `true`

**Escenario H**: Redondeo por defecto (`redondearDefecto`)

**Condición de prueba**: Verificamos el comportamiento de redondeo hacia abajo para diferentes tipos de entrada:
    - Positivo con decimales se redondea al entero inferior
    - Positivo entero exacto no cambia |
    - Cero devuelve cero |
    - Negativo con decimales se redondea al entero inferior (más negativo) |
    - Negativo entero exacto no cambia |

---

### Paso 3: Clases de equivalencia

### Paso 3: Clases de equivalencia (Equivalence Partitioning)

Ahora que ya tenemos identificadas las **condiciones de prueba** (*test conditions*), toca seleccionar los valores concretos para las pruebas; es decir, construir los **casos de prueba** (*test cases*) para cada método. Aplicaremos tanto *Equivalent Partitioning* (clases de equivalencia) como *Boundary Value Analysis* (análisis de valores límite).

Nos centraremos primero en los métodos más sencillos — `absoluto`, `esPar` y `redondearDefecto` — que nos permitirán ilustrar el proceso con claridad antes de pasar a las operaciones aritméticas más complejas.

#### `absoluto(double a)`

| Clase de equivalencia | Rango | Representativo |
| --- | --- | --- |
| **Válida — Positivo** (Valid — Positive) | `a > 0` | `3.0` |
| **Válida — Negativo** (Valid — Negative) | `a < 0` | `-3.0` |
| **Válida — Cero** (Valid — Zero) | `a = 0` | `0.0` |

#### `esPar(int a)`

| Clase de equivalencia | Condición | Representativo |
| --- | --- | --- |
| **Par positivo** (Even Positive) | `a > 0` y `a % 2 = 0` | `4` |
| **Impar positivo** (Odd Positive) | `a > 0` y `a % 2 ≠ 0` | `3` |
| **Cero** (Zero) | `a = 0` | `0` |
| **Par negativo** (Even Negative) | `a < 0` y `a % 2 = 0` | `-4` |

#### `redondearDefecto(double d)`

| Clase de equivalencia | Rango | Representativo |
| --- | --- | --- |
| **Positivo con decimales** (Positive with Decimals) | `d > 0`, `d ∉ ℤ` | `2.7` |
| **Positivo entero** (Positive Integer) | `d > 0`, `d ∈ ℤ` | `2.0` |
| **Cero** (Zero) | `d = 0` | `0.0` |
| **Negativo con decimales** (Negative with Decimals) | `d < 0`, `d ∉ ℤ` | `-1.5` |
| **Negativo entero** (Negative Integer) | `d < 0`, `d ∈ ℤ` | `-1.0` ← límite a revisar |

Repasillo rápido de la notación matemática empleada en las tablas:
    - `d ∈ ℤ` significa "d pertenece al conjunto de los enteros"
    - `d ∉ ℤ` significa "d no pertenece al conjunto de los enteros" (es decir, tiene parte decimal)

No hace falta que emplees notación matemática formal en tus casos de prueba, el texto natural siempre que no tenga ambigüedades es suficiente. La idea es que el diseño de casos de prueba sea claro y fácil de entender para cualquier persona que lo lea, incluso sin conocimientos avanzados de matemáticas.

---

### Paso 4: Análisis de valores límite

El método más interesante para BVA es `redondearDefecto`. La frontera entre "negativo con decimales" y "negativo entero exacto" es el punto crítico: ambas clases son negativas, pero una tiene parte decimal y la otra no.

Floor redondea al entero inferior, lo que implica un comportamiento diferente para los negativos con decimales (que se redondean a un número más negativo, un número más negativo es inferior) y los negativos enteros exactos (que no cambian). En el caso de los números positivos basta con truncar la parte decimal. Por eso, los valores límite a probar son `-1.0` y `-2.0`, que son enteros exactos, y `-1.5`, que es un decimal negativo justo en la frontera.

Para `redondearDefecto`, los valores límite alrededor de las fronteras negativas:

| Valor | Resultado esperado (`Math.floor`) | Clase |
| --- | --- | --- |
| `0.5` | `0` | positivo con decimales |
| `0.0` | `0` | cero (límite inferior positivo) |
| `-0.5` | `-1` | negativo con decimales |
| `-1.0` | `-1` | negativo entero exacto ← **límite crítico** |
| `-1.5` | `-2` | negativo con decimales |
| `-2.0` | `-2` | negativo entero exacto |

Fíjate en `-1.0`: es un negativo, pero ya es un entero exacto, por lo que `Math.floor(-1.0) = -1`. El método debería devolver `-1`, no `-2`. Este valor límite determinará si hay un bug.

---

### Paso 5: Plantilla de casos de prueba

Llegados aquí, tenemos dos conjuntos de entradas candidatas: los **representativos de cada clase EP** (Paso 3) y los **valores límite del BVA** (Paso 4). El trabajo de este paso es fusionarlos en un único conjunto sin redundancias innecesarias, y documentar formalmente el resultado.

Cada caso de prueba debe incluir:

- Un **ID único** (ej. `CP-ABS-01`).
- Las **precondiciones** necesarias (ej. "Objeto `Calculadora` inicializado").
- La **acción concreta** a realizar (ej. `absoluto(-3.0)`).
- El **resultado esperado**.

#### Fusionar EP y BVA: cómo eliminar redundancias

Al combinar ambas técnicas, es habitual encontrar solapamientos: un valor BVA puede caer exactamente dentro de una clase EP. Si ese valor ya verifica el comportamiento de la clase, añadir también el representativo EP original sería redundante.

El criterio de simplificación:

| Situación | Decisión |
| --- | --- |
| Un valor BVA cae dentro de una clase EP existente | Usar el valor BVA; descartar el representativo EP original |
| Una clase EP no tiene ningún valor BVA próximo | Conservar el representativo EP original |
| Un valor BVA no pertenece a ninguna clase EP | Añadirlo como caso nuevo |

> **Una suite compacta es una suite sana.** Eliminar redundancias no supone perder cobertura: cada caso debe aportar algo que los demás no aportan. Si dos casos ejercen exactamente la misma clase de equivalencia y ninguno está en un límite, uno de los dos sobra.

#### `redondearDefecto`: tabla de decisión EP + BVA

Este es el método donde EP y BVA interaccionan con más profundidad. Aplicamos el criterio antes de escribir ningún caso:

| Clase EP | Representativo EP | Valor(es) BVA | Decisión |
| --- | --- | --- | --- |
| Positivo con decimales | `2.7` | `0.5` | Mantenemos `2.7`; descartamos `0.5` (misma clase, no es un límite adicional) |
| **Positivo entero** | **`2.0`** | *(ninguno)* | **Conservamos `2.0`** — sin BVA que lo cubra, el representativo EP es imprescindible |
| Cero | `0.0` | `0.0` | Un único caso cubre EP y BVA |
| Negativo con decimales | `-1.5` | `-0.5`, `-1.5` | Conservamos **ambos** BVA: están en fronteras distintas |
| Negativo entero | `-1.0` | `-1.0` | Un único caso cubre EP y BVA (y es el límite crítico) |
| *(sin clase EP)* | — | `-2.0` | Añadimos como BVA adicional |

**Nota sobre `0.5` descartado**: pertenece a la misma clase que `2.7` (positivo con decimales) y no añade ninguna frontera relevante nueva. Quedarse con `2.7` es suficiente.

**Nota sobre `-0.5` y `-1.5`**: aunque ambos son «negativo con decimales», BVA los sitúa en fronteras distintas —entre `0` y `-1`, y entre `-1` y `-2` respectivamente—. Cada uno ejercita una transición diferente del `floor`, así que conservamos ambos.

**Nota sobre `2.0`**: es el único representativo EP sin ningún valor BVA que lo cubra. Si lo omitimos, la clase «Positivo entero» queda sin probar. Para `Math.floor(2.0)` el resultado debería ser `2` exacto —sin pérdida decimal—, y eso merece verificarse explícitamente.

---

#### Operaciones aritméticas (Escenarios C y D)

Los casos de operaciones no provienen de EP/BVA formal, sino de los **escenarios** identificados en el Paso 2.

| ID | Secuencia de llamadas | Resultado esperado en `getMemoria()` |
| --- | --- | --- |
| CP-OP-01 | `ingresarNumero(5)` → `seleccionarOperacion(SUMA)` → `ingresarNumero(3)` → `calcular()` | `8.0` |
| CP-OP-02 | `ingresarNumero(10)` → `seleccionarOperacion(RESTA)` → `ingresarNumero(4)` → `calcular()` | `6.0` |
| CP-OP-03 | `ingresarNumero(5)` → `seleccionarOperacion(MULTIPLICACION)` → `ingresarNumero(0)` → `calcular()` | `0.0` |
| CP-OP-04 | `ingresarNumero(10)` → `seleccionarOperacion(DIVISION)` → `ingresarNumero(2)` → `calcular()` | `5.0` |
| CP-OP-05 | `ingresarNumero(1)` → `seleccionarOperacion(DIVISION)` → `ingresarNumero(0)` → `calcular()` | `Double.POSITIVE_INFINITY` |

#### `absoluto` — casos de prueba

Las tres clases EP (`Positivo`, `Negativo`, `Cero`) se corresponden directamente con los valores que BVA sugeriría en el límite `0.0`. No hay redundancias: los tres casos son distintos y necesarios.

| ID | Precondiciones | Acción | Resultado esperado | Origen |
| --- | --- | --- | --- | --- |
| CP-ABS-01 | Objeto `Calculadora` inicializado | `absoluto(3.0)` | `3.0` | EP |
| CP-ABS-02 | Objeto `Calculadora` inicializado | `absoluto(-3.0)` | `3.0` | EP |
| CP-ABS-03 | Objeto `Calculadora` inicializado | `absoluto(0.0)` | `0.0` | EP + BVA |

#### `esPar` — casos de prueba

La paridad no tiene fronteras numéricas en el sentido clásico del BVA, así que los casos provienen directamente de las clases EP. Añadimos aquí la clase **Impar negativo** (`-3`), que quedó fuera de la tabla EP del Paso 3 pero completa la cobertura de combinaciones signo × paridad.

| ID | Precondiciones | Acción | Resultado esperado | Origen |
| --- | --- | --- | --- | --- |
| CP-PAR-01 | Objeto `Calculadora` inicializado | `esPar(4)` | `true` | EP |
| CP-PAR-02 | Objeto `Calculadora` inicializado | `esPar(3)` | `false` | EP |
| CP-PAR-03 | Objeto `Calculadora` inicializado | `esPar(0)` | `true` | EP + BVA |
| CP-PAR-04 | Objeto `Calculadora` inicializado | `esPar(-4)` | `true` | EP |
| CP-PAR-05 | Objeto `Calculadora` inicializado | `esPar(-3)` | `false` | EP |

#### `redondearDefecto` — casos de prueba

Aplicando la tabla de decisión anterior, el conjunto final (7 casos) es:

| ID | Precondiciones | Acción | Resultado esperado | Origen |
| --- | --- | --- | --- | --- |
| CP-RD-01 | Objeto `Calculadora` inicializado | `redondearDefecto(2.7)` | `2` | EP |
| CP-RD-02 | Objeto `Calculadora` inicializado | `redondearDefecto(2.0)` | `2` | EP |
| CP-RD-03 | Objeto `Calculadora` inicializado | `redondearDefecto(0.0)` | `0` | EP + BVA |
| CP-RD-04 | Objeto `Calculadora` inicializado | `redondearDefecto(-0.5)` | `-1` | BVA |
| CP-RD-05 | Objeto `Calculadora` inicializado | `redondearDefecto(-1.5)` | `-2` | EP + BVA |
| CP-RD-06 | Objeto `Calculadora` inicializado | `redondearDefecto(-1.0)` | `-1` | EP + BVA ← **límite crítico** |
| CP-RD-07 | Objeto `Calculadora` inicializado | `redondearDefecto(-2.0)` | `-2` | BVA ← **límite crítico** |

Con el diseño completo, sabemos exactamente qué probar y qué resultado esperamos. Pasamos a la implementación.

Se recoje por conveniencia en un archivo Excel [./UP10-11-casos-de-prueba.xlsx](./UP10-11-casos-de-prueba.xlsx) aparte para facilitar su lectura y edición, pero el formato es libre: lo importante es que cada caso de prueba esté claramente documentado con sus componentes esenciales (ID, precondiciones, acción, resultado esperado).

---

## FASE 2 — Implementación con JUnit 5 (UP11)

> [!IMPORTANT]
> Esta fase está actualmente en revisión. El contenido que sigue es funcional pero puede sufrir cambios en su presentación y estructura antes de la versión definitiva.

---

### Paso 6: Crear la clase de test

IntelliJ puede generar automáticamente el esqueleto de la clase de test:

1. Abre `Calculadora.java`.
2. Haz clic sobre el nombre de la clase `Calculadora` en la línea de declaración.
3. Pulsa `Alt+Enter` y selecciona **Create Test**.
4. En el diálogo que aparece:
   - **Testing library**: JUnit 5
   - **Class name**: `CalculadoraTest` (por defecto)
   - **Destination package**: `io.github.scontreraslopez`
5. Acepta. IntelliJ creará `src/test/java/io/github/scontreraslopez/CalculadoraTest.java` y añadirá la carpeta de tests al proyecto.

<!-- TODO ADD IMAGE: Captura del diálogo "Create Test" de IntelliJ con JUnit 5 seleccionado y el package correcto. -->

> **¿Por qué `src/test/java`?** Gradle separa el código de producción (`src/main`) del código de pruebas (`src/test`). Los tests solo se compilan y ejecutan durante la fase de verificación; no se incluyen en el artefacto final desplegable.

---

### Paso 7: Configurar `@BeforeEach`

La calculadora es un **objeto con estado interno** (la memoria). Para garantizar el aislamiento entre tests — principio **I** de FIRST —, cada test debe partir de una instancia recién creada. Añade el siguiente código a la clase generada:

```java
import io.github.scontreraslopez.Calculadora;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

    Calculadora c;

    @BeforeEach
    void setUp() {
        c = new Calculadora();
    }
}
```

> **¿Por qué no `static` con `@BeforeAll`?** Si usáramos una sola instancia compartida, el estado que deja un test (memoria modificada, operación pendiente) contaminaría el siguiente, haciendo que los tests dependan entre sí y que los fallos sean difíciles de aislar. Con `@BeforeEach` cada test arranca con memoria `0.0` y sin operación pendiente.

---

### Paso 8: Verificar el estado inicial y `ingresarNumero`

Empezamos por lo más simple: el estado de un objeto recién creado.

```java
@Test
void getMemoria_objetoNuevo_devuelveCero() {
    assertEquals(0.0, c.getMemoria());
}

@Test
void ingresarNumero_actualizaMemoria() {
    c.ingresarNumero(7.0);
    assertEquals(7.0, c.getMemoria());
}
```

Ejecuta los tests con **Run ▶** sobre la clase o con `Ctrl+Shift+F10`. Deben aparecer en verde.

<!-- TODO ADD IMAGE: Captura de IntelliJ con la barra verde y los dos primeros tests pasando. -->

---

### Paso 9: Implementar los tests de operaciones aritméticas

Cada operación requiere la secuencia completa. Los casos CP-OP-01 a CP-OP-05 de la plantilla se traducen directamente:

```java
@Test
void calcular_suma_dosPositivos_devuelveResultadoCorrecto() {
    c.ingresarNumero(5);
    c.seleccionarOperacion(Calculadora.Operacion.SUMA);
    c.ingresarNumero(3);
    c.calcular();
    assertEquals(8.0, c.getMemoria());
}

@Test
void calcular_resta_dosPositivos_devuelveResultadoCorrecto() {
    c.ingresarNumero(10);
    c.seleccionarOperacion(Calculadora.Operacion.RESTA);
    c.ingresarNumero(4);
    c.calcular();
    assertEquals(6.0, c.getMemoria());
}

@Test
void calcular_multiplicacion_porCero_devuelveCero() {
    c.ingresarNumero(5);
    c.seleccionarOperacion(Calculadora.Operacion.MULTIPLICACION);
    c.ingresarNumero(0);
    c.calcular();
    assertEquals(0.0, c.getMemoria());
}

@Test
void calcular_division_normal_devuelveResultadoCorrecto() {
    c.ingresarNumero(10);
    c.seleccionarOperacion(Calculadora.Operacion.DIVISION);
    c.ingresarNumero(2);
    c.calcular();
    assertEquals(5.0, c.getMemoria());
}

@Test
void calcular_division_porCero_devuelveInfinitoPositivo() {
    c.ingresarNumero(1);
    c.seleccionarOperacion(Calculadora.Operacion.DIVISION);
    c.ingresarNumero(0);
    c.calcular();
    assertEquals(Double.POSITIVE_INFINITY, c.getMemoria());
}
```

> **¿Por qué `Double.POSITIVE_INFINITY`?** En Java, `1.0 / 0.0` no lanza excepción: devuelve `Infinity`, tal como define la especificación IEEE 754. Este es un comportamiento **definido** — el test lo verifica explícitamente.

---

### Paso 10: Tests de `inicializarMemoria`

```java
@Test
void inicializarMemoria_trasOperacion_reiniciaMemoriaACero() {
    c.ingresarNumero(5);
    c.seleccionarOperacion(Calculadora.Operacion.SUMA);
    c.ingresarNumero(3);
    c.calcular();                  // memoria = 8.0
    c.inicializarMemoria();
    assertEquals(0.0, c.getMemoria());
}
```

---

### Paso 11: Implementar los tests de `absoluto` y `esPar`

```java
@Test
void absoluto_positivo_devuelveMismo() {
    assertEquals(3.0, c.absoluto(3.0));
}

@Test
void absoluto_negativo_devuelvePositivo() {
    assertEquals(3.0, c.absoluto(-3.0));
}

@Test
void absoluto_cero_devuelveCero() {
    assertEquals(0.0, c.absoluto(0.0));
}

@Test
void esPar_numeroParPositivo_devuelveTrue() {
    assertTrue(c.esPar(4));
}

@Test
void esPar_numeroImparPositivo_devuelveFalse() {
    assertFalse(c.esPar(3));
}

@Test
void esPar_cero_devuelveTrue() {
    assertTrue(c.esPar(0));
}

@Test
void esPar_numeroParNegativo_devuelveTrue() {
    assertTrue(c.esPar(-4));
}

@Test
void esPar_numeroImparNegativo_devuelveFalse() {
    assertFalse(c.esPar(-3));
}
```

Ejecuta toda la suite. Todos los tests deben estar en verde.

---

### Paso 12: Implementar los tests de `redondearDefecto` — BVA en acción

Implementamos los casos CP-RD-01 a CP-RD-07 diseñados en la Fase 1:

```java
@Test
void redondearDefecto_positivoConDecimales_redondeoHaciaAbajo() {
    assertEquals(2, c.redondearDefecto(2.7));     // CP-RD-01
}

@Test
void redondearDefecto_positivoEntero_sinDecimales() {
    assertEquals(2, c.redondearDefecto(2.0));     // CP-RD-02
}

@Test
void redondearDefecto_cero_devuelveCero() {
    assertEquals(0, c.redondearDefecto(0.0));     // CP-RD-03
}

@Test
void redondearDefecto_negativoConDecimalesCercaCero_redondeoHaciaAbajo() {
    assertEquals(-1, c.redondearDefecto(-0.5));   // CP-RD-04
}

@Test
void redondearDefecto_negativoConDecimales_redondeoHaciaAbajo() {
    assertEquals(-2, c.redondearDefecto(-1.5));   // CP-RD-05
}

@Test
void redondearDefecto_negativoEnteroExacto_sinDecimales() {
    assertEquals(-1, c.redondearDefecto(-1.0));   // CP-RD-06: límite crítico
}

@Test
void redondearDefecto_negativoEnteroExactoMayor_sinDecimales() {
    assertEquals(-2, c.redondearDefecto(-2.0));   // CP-RD-07: límite crítico
}
```

**Ejecuta estos tests. Los dos últimos fallarán.**

<!-- TODO ADD IMAGE: Captura de IntelliJ con CP-RD-06 y CP-RD-07 en rojo, mostrando "expected: <-1> but was: <-2>" y "expected: <-2> but was: <-3>". -->

El análisis de valores límite ha descubierto un bug real. Inspeccionemos el código en `Calculadora.java`:

```java
public int redondearDefecto(double d) {
    int result = (int) d;   // truncación hacia cero: (int)(-1.0) = -1
    if (d < 0) {
        result--;            // -1-- = -2  ← se decrementa aunque ya era entero exacto
    }
    return result;
}
```

La condición `if (d < 0)` aplica el decremento a *todos* los negativos, incluidos los enteros exactos. El resultado: `redondearDefecto(-1.0)` devuelve `-2` en lugar de `-1`. La corrección sería usar `if (d < result)`, que solo decrementa cuando la truncación perdió información decimal.

> Este resultado ilustra el valor real del diseño previo: el caso CP-RD-03 con `-1.5` pasaba (correcto, porque `-1.5` sí tiene parte decimal negativa). Solo al incluir CP-RD-05 con `-1.0` — el valor **límite** entre las dos clases — se descubre el defecto. Sin BVA, el bug habría pasado desapercibido.

---

## Resultado final

La práctica produce una suite de tests que cubre todos los escenarios identificados en la Fase 1. El flujo completo es:

```text
Clonar el repositorio y abrir en IntelliJ IDEA
    ↓
Verificar que el proyecto compila y Main funciona
    ↓
Leer el código bajo prueba
    ↓
Identificar escenarios y condiciones de prueba
    ↓
Clases de equivalencia → selección de valores representativos
    ↓
BVA en las fronteras críticas
    ↓
Documentar casos de prueba (plantilla)
    ↓
Crear clase de test con @BeforeEach
    ↓
Implementar en JUnit 5 paso a paso
    ↓
Ejecutar → verde excepto redondearDefecto con negativos enteros (bug detectado)
```
