# Ejercicio 2 — Refactorización: CalculadoraHoras

**Tipo**: Refactorización  
**Tiempo estimado**: 15 min  

---

## Instrucciones

Puedes crear un nuevo proyecto en IntelliJ y usar los sources o descomprimir el .zip que se te ha proporcionado y abrirlo en IntelliJ.

## Contexto

Tienes la clase `CalculadoraHoras` que calcula el sueldo neto de un trabajador según sus horas y categoría. La funcionalidad es correcta (los tests están en verde), pero el código tiene varios *code smells*. Tu tarea es refactorizarlo sin romper los tests.

---

## Se te pide

Corrige los **tres *smells*** siguientes. Usa los atajos de IntelliJ siempre que puedas.

### 1. Magic Numbers

Los valores `12.0`, `20.0`, `35.0`, `1.5` y `0.15` son números mágicos. Extráelos a **constantes** con nombre descriptivo (`private static final double`).

Nombres esperados: `TARIFA_JUNIOR`, `TARIFA_SENIOR`, `TARIFA_LEAD`, `FACTOR_HORAS_EXTRA`, `TASA_RETENCION`.

### 2. Long Method

El método `calcularSueldo` hace demasiadas cosas. Aplica **Extract Method** al menos dos veces:

- Extrae el cálculo de la tarifa según categoría en un método `obtenerTarifa(String categoria)`.
- Extrae el cálculo del sueldo neto (bruto menos retención) en un método `aplicarRetencion(double bruto)`.

### 3. Rename

El nombre `CalculadoraHoras` no refleja bien lo que hace la clase (calcula salarios, no horas). Renómbrala a `CalculadoraSalarios`.

---

## Requisito transversal

**Los tests deben seguir en verde** después de cada cambio. Ejecútalos tras cada refactorización para confirmarlo.

No cambies la firma pública de `calcularSueldo`.
