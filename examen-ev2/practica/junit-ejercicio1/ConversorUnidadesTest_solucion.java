package io.github.scontreraslopez;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource; //Se me olvidó añadiros este import en el starting
import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * Esta es una solución propuesta
 * Algunas cosas son MUY mejorables,
 * por ejemplo:
 * 1. Para BVA tests separados con nombres descriptivos
 * kmAMillas_conValorPositivo_devuelveConversionCorrecta(), kmAMillas_conCero_devuelveCero() , kmAMillas_conValorNegativo_lanzaExcepcion
 * 2. y/o usar @ParameterizedTest en los tests del mismo tipo.
 *
 * Pero para sepáis las simplificaciones que se aceptan en el examen, siendo conscientes de que son simplificaciones, de cara a vuestro futuro profesional.
 *
 * | Método | Entrada | Resultado esperado |
 * |--------|---------|-------------------|
 * | `kmAMillas` | `1.0` | `0.6214` (±0.0001) |
 * | `kmAMillas` | `25.0` | `15.5343` (±0.0001) |
 * | `kmAMillas` | `50.0` | `31.0686` (±0.0001) |
 * | `kmAMillas` | `100.0` | `62.1371` (±0.0001) |
 * | `millasAKm` | `1.0` | `1.6093` (±0.0001) |
 * | `millasAKm` | `5.0` | `8.0467` (±0.0001) |
 * | `millasAKm` | `10.0` | `16.0934` (±0.0001) |
 * | `millasAKm` | `20.0` | `32.1869` (±0.0001) |
 * | `celsiusAFahrenheit` | `-40.0` | `-40.0` |
 * | `celsiusAFahrenheit` | `0.0` | `32.0` |
 * | `celsiusAFahrenheit` | `20.0` | `68.0` |
 * | `celsiusAFahrenheit` | `37.0` | `98.6` |
 * | `celsiusAFahrenheit` | `100.0` | `212.0` |
 *
 *
 *
 */

class ConversorUnidadesTest {

    private ConversorUnidades conversor;

    @BeforeEach
    void setUp() {
        // El enunciado pide explícitamente el BeforeEach pero...
        // Conversor Unidades es un objeto sin estado por lo que BeforeAll
        // o incluso inicialización directa del campo podría ser más eficiente.
        conversor = new ConversorUnidades();
    }

    @Test
    void kmAMillas() {
        // MEJORABLE: idealmente serían tres @Test separados con nombres descriptivos:
        // kmAMillas_conValorPositivo_devuelveConversionCorrecta()
        // kmAMillas_conCero_devuelveCero()
        // kmAMillas_conValorNegativo_lanzaExcepcion()
        // Así, si falla un caso sabes exactamente cuál, y si falla el primero los demás sí se ejecutan.

        // BVA: -1.0 (excepción), 0.0 (frontera exacta), 1.0 (valor positivo válido)

        // Given: valor negativo
        double kmNegativo = -1.0;
        // When / Then: debe lanzar excepción
        assertThrows(IllegalArgumentException.class, () -> conversor.kmAMillas(kmNegativo));

        // Given: 0 km (frontera exacta)
        double kmCero = 0.0;
        double millasEsperadasCero = 0.0;
        // When: convierto a millas
        double resultadoCero = conversor.kmAMillas(kmCero);
        // Then: el resultado es 0
        assertEquals(millasEsperadasCero, resultadoCero, 0.0001);

        // Given: 1 km (valor positivo válido)
        double kmPositivo = 1.0;
        double millasEsperadas = 0.6214;
        // When: convierto a millas
        double resultado = conversor.kmAMillas(kmPositivo);
        // Then: el resultado coincide con el valor esperado (±0.0001)
        assertEquals(millasEsperadas, resultado, 0.0001);
    }

    @Test
    void millasAKm() {
        // MEJORABLE: mismo problema que kmAMillas, idealmente tres @Test separados.

        // BVA: -1.0 (excepción), 0.0 (frontera exacta), 1.0 (valor positivo válido)

        // Given: valor negativo
        double millasNegativas = -1.0;
        // When / Then: debe lanzar excepción
        assertThrows(IllegalArgumentException.class, () -> conversor.millasAKm(millasNegativas));

        // Given: 0 millas (frontera exacta)
        double millasCero = 0.0;
        double kmEsperadosCero = 0.0;
        // When: convierto a km
        double resultadoCero = conversor.millasAKm(millasCero);
        // Then: el resultado es 0
        assertEquals(kmEsperadosCero, resultadoCero, 0.0001);

        // Given: 1 milla (valor positivo válido)
        double millasPositivas = 1.0;
        double kmEsperados = 1.6093;
        // When: convierto a km
        double resultado = conversor.millasAKm(millasPositivas);
        // Then: el resultado coincide con el valor esperado (±0.0001)
        assertEquals(kmEsperados, resultado, 0.0001);
    }

    @Test
    void celsiusAFahrenheit() {
        // MEJORABLE: mismo problema, idealmente un @Test por caso o @ParameterizedTest.
        // celsiusAFahrenheit no lanza excepción por lo que BVA se centra en valores notables.

        // Aquí no hay ninguna frontera especial por lo que lo normal es probar, para la entrada:
        // negativo, cero, positivo

        // Caso entrada negativa
        // Given: -40 °C
        double celsiusMenosCuarenta = -40.0;
        double fahrenheitEsperadoMenosCuarenta = -40.0;
        // When: convierto
        double resultadoMenosCuarenta = conversor.celsiusAFahrenheit(celsiusMenosCuarenta);
        // Then: -40 °F
        assertEquals(fahrenheitEsperadoMenosCuarenta, resultadoMenosCuarenta, 0.0001);

        // Caso entrada cero
        // Given: 0 °C
        double celsiusCero = 0.0;
        double fahrenheitEsperadoCero = 32.0;
        // When: convierto
        double resultadoCero = conversor.celsiusAFahrenheit(celsiusCero);
        // Then: 32 °F
        assertEquals(fahrenheitEsperadoCero, resultadoCero, 0.0001);

        // Caso entrada positivo
        // Given: 100 °C
        double celsiusCien = 100.0;
        double fahrenheitEsperadoCien = 212.0;
        // When: convierto
        double resultadoCien = conversor.celsiusAFahrenheit(celsiusCien);
        // Then: 212 °F
        assertEquals(fahrenheitEsperadoCien, resultadoCien, 0.0001);
    }

    // MEJORABLE: separar en dos @ParameterizedTest:
    // uno para negativos (true)
    // y otro para positivos (false)
    @ParameterizedTest
    @CsvSource({
            "-1.0, true", // -1 es negativo
            "0.0, false", // 0 no es negativo
            "1.0, false"}) // 1 no es negativoº
    void esNegativo_variosValores_devuelveEsperado(double valor, boolean esperado) {
        boolean resultado = conversor.esNegativo(valor);
        assertEquals(esperado, resultado);
    }


    //Estos no se pedían pero los dejo como referencia
    @ParameterizedTest
    @ValueSource(doubles = {
            -1.0, -0.001, -100.0})
    void esNegativo_conValoresNegativos_devuelveTrue(double valor) {
        // Given: valor negativo (parametrizado con @ValueSource, que va de uno en uno)
        // When: compruebo si es negativo
        boolean resultado = conversor.esNegativo(valor);
        // Then: debe devolver true
        assertTrue(resultado);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.001, 100.0})
    void esNegativo_conValoresNoNegativos_devuelveFalse(double valor) {
        // Given: valor >= 0 (parametrizado con @ValueSource, que va de uno en uno)
        // When: compruebo si es negativo
        boolean resultado = conversor.esNegativo(valor);
        // Then: debe devolver false
        assertFalse(resultado);
    }


}
