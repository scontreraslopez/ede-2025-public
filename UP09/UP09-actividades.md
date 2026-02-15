# UP9: ACTIVIDADES - UML COMPORTAMIENTO E INTERACCIÓN

## ÍNDICE

- [Actividad 1: Diagramas de Secuencia - Cajero Automático](#actividad-1-diagramas-de-secuencia---cajero-automático)
- [Actividad 2: Diagramas de Secuencia - Sistema de Reservas](#actividad-2-diagramas-de-secuencia---sistema-de-reservas)
- [Actividad 3: Fragmentos Combinados - Proceso de Login](#actividad-3-fragmentos-combinados---proceso-de-login)
- [Actividad 4: Diagramas de Actividades - Proceso de Compra](#actividad-4-diagramas-de-actividades---proceso-de-compra)
- [Actividad 5: Diagramas de Estados - Ciclo de Vida](#actividad-5-diagramas-de-estados---ciclo-de-vida)

---

## Actividad 1: Diagramas de Secuencia - Cajero Automático

**Objetivo:** Crear un diagrama de secuencia básico.

**Enunciado:**

Modela la interacción para **retirar dinero de un cajero automático** con los siguientes pasos:

1. El usuario introduce su tarjeta
2. El cajero solicita el PIN
3. El usuario introduce el PIN
4. El cajero valida el PIN con el banco
5. El banco confirma que el PIN es correcto
6. El cajero muestra el menú de operaciones
7. El usuario selecciona "Retirar efectivo" e introduce la cantidad
8. El cajero verifica con el banco si hay saldo suficiente
9. Si hay saldo:
   - El banco autoriza la operación
   - El cajero dispensa el efectivo
   - El cajero actualiza el saldo con el banco
   - El cajero devuelve la tarjeta
10. Si NO hay saldo:
    - El banco rechaza la operación
    - El cajero muestra mensaje de error
    - El cajero devuelve la tarjeta

**Participantes:**

- `:Usuario`
- `:Cajero`
- `:SistemaBanco`

**Tareas:**

1. Dibuja el diagrama de secuencia completo.
2. Usa un fragmento `alt` para la decisión de saldo suficiente/insuficiente.
3. Marca correctamente las activaciones (rectángulos sobre líneas de vida).
4. Incluye mensajes de retorno cuando sea necesario.
5. **Opcional:** Escribe el código PlantUML.

**Pista:** Hay al menos 2 validaciones obligatorias. Piensa también en qué mensajes son síncronos (espera respuesta) y cuáles podrían ser asíncronos.

---

## Actividad 2: Diagramas de Secuencia - Sistema de Reservas

**Objetivo:** Practicar diagramas de secuencia con múltiples objetos y fragmentos.

**Enunciado:**

Un sistema de reservas de restaurantes funciona así:

1. El cliente busca restaurantes disponibles por zona y fecha
2. El sistema consulta la base de datos de restaurantes
3. El sistema muestra los restaurantes disponibles
4. El cliente selecciona un restaurante
5. El sistema verifica disponibilidad de mesas para esa fecha/hora
6. Si hay mesas disponibles:
   - El sistema crea una reserva temporal (15 minutos)
   - El sistema solicita datos del cliente (nombre, teléfono, email)
   - El cliente introduce los datos
   - El sistema envía confirmación por email (actor externo: ServicioEmail)
   - Si el email se envía correctamente:
     - La reserva se confirma definitivamente
   - Si el email falla:
     - La reserva se cancela
7. Si NO hay mesas:
   - El sistema ofrece la opción de lista de espera
   - Si el cliente acepta:
     - Se registra en lista de espera

**Participantes:**

- `:Cliente`
- `:SistemaReservas`
- `:BaseDatos`
- `:ServicioEmail` (externo)

**Tareas:**

1. Dibuja el diagrama de secuencia completo.
2. Usa fragmentos combinados:
   - `alt` para disponibilidad de mesas
   - `alt` anidado para éxito/fallo del email
   - `opt` para la opción de lista de espera
3. Marca la creación del objeto `:Reserva` cuando se crea la reserva temporal.
4. Indica qué mensajes son síncronos y cuáles asíncronos.

**Reflexión:** ¿Por qué el envío del email podría ser asíncrono? ¿Qué ventajas tiene?

---

## Actividad 3: Fragmentos Combinados - Proceso de Login

**Objetivo:** Dominar el uso de fragmentos combinados (alt, opt, loop).

**Enunciado:**

Implementa un proceso de login seguro con las siguientes reglas:

1. El usuario introduce credenciales (usuario y contraseña)
2. El sistema valida el formato (email válido, contraseña mínimo 8 caracteres)
3. Si el formato es incorrecto:
   - Mostrar error y terminar
4. Si el formato es correcto:
   - El sistema consulta la base de datos
   - Si el usuario no existe:
     - Registrar intento fallido
     - Mostrar error genérico ("Credenciales inválidas")
   - Si el usuario existe:
     - Verificar contraseña
     - Si la contraseña es incorrecta:
       - Incrementar contador de intentos fallidos
       - Si intentos < 3:
         - Mostrar error y permitir reintentar
       - Si intentos >= 3:
         - Bloquear cuenta temporalmente
         - Enviar email de alerta
     - Si la contraseña es correcta:
         - Resetear contador de intentos
         - Crear sesión directamente

**Tareas:**

1. Dibuja el diagrama de secuencia con TODOS los fragmentos combinados necesarios.
2. Usa fragmentos anidados donde sea necesario.
3. Marca claramente todas las condiciones entre corchetes `[condición]`.

---

## Actividad 4: Diagramas de Actividades - Proceso de Compra

**Objetivo:** Modelar un proceso de negocio completo.

**Enunciado:**

Modela el proceso completo de compra online desde que el cliente añade productos hasta que recibe el pedido:

1. Inicio: Cliente navega por la tienda
2. Cliente añade productos al carrito (puede hacerlo múltiples veces)
3. Cliente decide finalizar compra
4. Sistema verifica que haya productos en el carrito
   - Si el carrito está vacío: volver al inicio
5. Sistema solicita identificación
6. Cliente se identifica o se registra
7. Cliente selecciona dirección de envío
8. Cliente selecciona método de envío (estándar/express)
9. Sistema calcula el total (productos + envío)
10. Cliente selecciona método de pago (tarjeta/PayPal/transferencia)
11. Sistema procesa el pago
    - Si el pago falla: volver a seleccionar método de pago (máximo 3 intentos)
    - Si fallan todos los intentos: cancelar pedido
12. Si el pago tiene éxito:
    - Sistema genera pedido
    - Sistema envía confirmación por email
    - Sistema notifica al almacén
13. Fin

**Tareas:**

1. Dibuja el diagrama de actividades completo.
2. Usa correctamente:
   - Nodo inicial y final
   - Acciones (rectángulos redondeados)
   - Decisiones (rombos) con condiciones
   - Fusiones donde múltiples caminos se juntan
3. Usa un fragmento `loop` o decisión para los 3 intentos de pago.
4. **Opcional:** Añade swimlanes para distinguir Cliente, Sistema y Almacén.

---

## Actividad 5: Diagramas de Estados - Ciclo de Vida

**Objetivo:** Modelar el ciclo de vida completo de un objeto.

**Enunciado 1 - Pedido de ecommerce:**

Un pedido en una tienda online pasa por los siguientes estados:

- **Creado**: Cuando el cliente finaliza la compra
- **PagoPendiente**: Esperando confirmación del pago
- **Pagado**: Pago confirmado
- **EnPreparacion**: El almacén está preparando el pedido
- **Enviado**: El pedido ha sido entregado al transportista
- **Entregado**: El cliente ha recibido el pedido
- **Cancelado**: El pedido se ha cancelado
- **Devuelto**: El cliente ha devuelto el pedido

**Transiciones:**

- Desde Creado → PagoPendiente (automático)
- Desde PagoPendiente → Pagado (cuando se confirma el pago)
- Desde PagoPendiente → Cancelado (si falla el pago después de reintentos)
- Desde Pagado → EnPreparacion (automático)
- Desde EnPreparacion → Enviado (cuando se entrega al transportista)
- Desde Enviado → Entregado (cuando el cliente confirma recepción)
- Desde Enviado → Cancelado (si el transportista no puede entregar)
- Desde Entregado → Devuelto (si el cliente inicia una devolución)
- Desde cualquier estado excepto Cancelado/Devuelto/Entregado → Cancelado (el cliente puede cancelar)

**Tareas:**

1. Dibuja el diagrama de estados completo.
2. Incluye:
   - Estado inicial
   - Todos los estados (rectángulos redondeados)
   - Todas las transiciones con sus eventos/condiciones
   - Estado final desde Devuelto y Entregado
3. Añade guardas (condiciones) donde sea necesario: `evento [condición] / acción`
4. **Opcional:** Añade acciones `entry`, `do`, `exit` en algún estado (ej: en Enviado → entry/notificarCliente)

## Recursos de Apoyo

### Herramientas Recomendadas

- **PlantUML Online**: [plantuml.com/plantuml](https://www.plantuml.com/plantuml)
- **Mermaid Live Editor**: [mermaid.live](https://mermaid.live)
- **Draw.io**: [app.diagrams.net](https://app.diagrams.net)
- **Visual Paradigm Community**: [visual-paradigm.com](https://www.visual-paradigm.com)

### Documentación Útil

- Apuntes de UP09
- [PlantUML Sequence Diagram](https://plantuml.com/sequence-diagram)
- [PlantUML Activity Diagram](https://plantuml.com/activity-diagram-beta)
- [PlantUML State Diagram](https://plantuml.com/state-diagram)

### Ejemplos de Código PlantUML

**Fragmento alt:**

```plantuml
alt successful case
    A -> B: Request
    B -> A: Response
else failure case
    A -> B: Request
    B -> A: Error
end
```

**Loop:**

```plantuml
loop for each item
    A -> B: process(item)
end
```

**Fork/Join en actividades:**

```plantuml
fork
  :action 1;
fork again
  :action 2;
end fork
```

---

## Consejos

Hacer diagramas requiere de mucha experiencia en el desarrollo de software. Por eso mismo, no te preocupes si al principio te cuesta o no te salen bien. Aquí van algunos consejos:

- **Empieza por lo simple**: No intentes hacer el diagrama perfecto a la primera. Dibuja una versión básica y ve refinando.
- **Valida con casos reales**: Simula mentalmente el flujo del diagrama para detectar errores.
- **Compara con compañeros**: Ver cómo otros han resuelto el mismo problema te ayuda a mejorar.
- **Usa PlantUML para versionar**: Si escribes los diagramas como código, puedes usar Git para versionar y colaborar.
- **Piensa en mantenibilidad**: ¿Qué pasaría si cambias un requisito? ¿Tu diagrama es fácil de actualizar?
