# Enunciado - Empresa UML

Dibuja en la hoja facilitada el **diagrama de clases UML** que modele la siguiente situación:

Una **Empresa** tiene un nombre y un CIF, y está formada por varios **Departamentos**. Si la Empresa desaparece, sus Departamentos también.

Cada Departamento tiene un nombre y un presupuesto anual, y expone el método `numEmpleados()` que devuelve un entero. Un Departamento tiene exactamente un **Responsable** (que es un Empleado), pero un Empleado puede no ser responsable de ningún departamento.

Un **Empleado** tiene un ID, nombre y salario base, y expone el método `esResponsable()` que devuelve un booleano. Hay dos tipos: **EmpleadoFijo** (que añade el atributo `antiguedad` en años) y **EmpleadoTemporal** (que añade la `fechaFinContrato`). Todos los empleados pertenecen a un único Departamento.

## Se te pide

1. Identificar las **clases** con sus atributos principales.
2. Modelar las **relaciones** con el tipo correcto (composición, asociación, herencia).
3. Indicar las **multiplicidades** (cardinalidad) donde proceda.
4. Usar la **visibilidad** correcta en atributos y métodos (`-` privado, `+` público).
5. **No es necesario incluir getters ni setters.**