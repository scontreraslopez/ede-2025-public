package ede;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraHorasTest {

    private CalculadoraHoras calc;

    @BeforeEach
    void setUp() {
        calc = new CalculadoraHoras();
    }

    @Test
    void calcularSueldo_juniorSinExtras_devuelveNetoCorrector() {
        // 40h * 12€ = 480 bruto; 480 - 15% = 408 neto
        assertEquals(408.0, calc.calcularSueldo(40, 0, "JUNIOR"), 0.001);
    }

    @Test
    void calcularSueldo_seniorConExtras_devuelveNetoCorrector() {
        // 40h * 20 = 800 base; 5h * 20 * 1.5 = 150 extra; 950 bruto; 950 * 0.85 = 807.5
        assertEquals(807.5, calc.calcularSueldo(40, 5, "SENIOR"), 0.001);
    }

    @Test
    void calcularSueldo_leadSinExtras_devuelveNetoCorrector() {
        // 8h * 35 = 280 bruto; 280 * 0.85 = 238 neto
        assertEquals(238.0, calc.calcularSueldo(8, 0, "LEAD"), 0.001);
    }

    @Test
    void calcularSueldo_categoriaDesconocida_usaTarifaJunior() {
        assertEquals(408.0, calc.calcularSueldo(40, 0, "BECARIO"), 0.001);
    }

    @Test
    void calcularSueldo_horasNormalesNegativas_lanzaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> calc.calcularSueldo(-1, 0, "JUNIOR"));
    }

    @Test
    void calcularSueldo_horasExtraNegativas_lanzaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> calc.calcularSueldo(40, -1, "JUNIOR"));
    }
}
