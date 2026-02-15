# UP8: ACTIVIDADES - UML FUNDAMENTOS Y ESTRUCTURA

## ÍNDICE

- [Actividad 1: Casos de Uso - Sistema de Biblioteca](#actividad-1-casos-de-uso---sistema-de-biblioteca)
- [Actividad 2: Casos de Uso - Aplicación de Gestión de Tareas](#actividad-2-casos-de-uso---aplicación-de-gestión-de-tareas)
- [Actividad 3: Diagrama de Clases - Sistema de Gestión Hotelera](#actividad-3-diagrama-de-clases---sistema-de-gestión-hotelera)
- [Actividad 4: Diagrama de Clases - Red Social Básica](#actividad-4-diagrama-de-clases---red-social-básica)
- [Actividad 5: Relaciones entre Clases - Tienda Online](#actividad-5-relaciones-entre-clases---tienda-online)

---

## Actividad 1: Casos de Uso - Sistema de Biblioteca

**Objetivo:** Identificar actores y casos de uso en un sistema de biblioteca.

**Enunciado:**

Una biblioteca quiere informatizar su sistema de préstamos. Los usuarios pueden:

- Buscar libros por título, autor o ISBN
- Reservar libros que están prestados
- Tomar libros en préstamo (máximo 3 simultáneos)
- Devolver libros

Los bibliotecarios pueden:

- Registrar nuevos usuarios
- Dar de alta nuevos libros
- Gestionar préstamos (registrar préstamos y devoluciones)
- Consultar el historial de préstamos de un usuario
- Enviar recordatorios de devolución automáticos

El sistema debe conectarse con una base de datos externa de libros (ISBN) para obtener información bibliográfica.

**Tareas:**

1. Identifica los **actores** del sistema (mínimo 3).
2. Identifica los **casos de uso** principales (mínimo 8).
3. Dibuja el **diagrama de casos de uso** incluyendo:
   - Al menos una relación `<<include>>`
   - Al menos una relación `<<extend>>`
   - Al menos una relación de generalización (herencia) entre actores o casos de uso
4. **Opcional:** Escribe el código PlantUML para el diagrama.

**Pistas:**

- ¿Hay actores que heredan de otros (generalización)?
- ¿Hay funcionalidades comunes que se incluyan en varios casos de uso?
- ¿Hay funcionalidades opcionales que extienden un caso de uso base?

---

## Actividad 2: Casos de Uso - Aplicación de Gestión de Tareas

**Objetivo:** Crear un diagrama de casos de uso completo desde cero.

**Enunciado:**

Diseña una aplicación de gestión de tareas (tipo Trello/Todoist) con las siguientes funcionalidades:

**Usuario básico:**

- Crear tareas
- Marcar tareas como completadas
- Organizar tareas en listas
- Establecer fechas de vencimiento

**Usuario premium** (hereda de usuario básico):

- Asignar tareas a otros usuarios
- Crear plantillas de proyectos
- Exportar informes en PDF
- Integrar con calendario externo (Google Calendar)

**Sistema externo:**

- Servicio de notificaciones por email

**Tareas:**

1. Crea el diagrama de casos de uso completo.
2. Usa relaciones `<<include>>` para funcionalidades compartidas (por ejemplo, todas las operaciones requieren autenticación).
3. Usa `<<extend>>` para funcionalidades opcionales (por ejemplo, al crear una tarea se puede opcionalmente añadir una etiqueta).
4. Muestra la relación de generalización entre Usuario y Usuario Premium.
5. Indica el actor externo (Servicio de email).

**Entrega:**

- Diagrama dibujado (a mano, con herramienta o con PlantUML)
- Lista de los casos de uso con una breve descripción de cada uno (1-2 líneas)

---

## Actividad 3: Diagrama de Clases - Sistema de Gestión Hotelera

**Objetivo:** Diseñar un diagrama de clases con relaciones básicas.

**Enunciado:**

Un hotel necesita un sistema para gestionar reservas. El modelo debe incluir:

- **Hotel**: tiene nombre, dirección, categoría (estrellas)
- **Habitación**: tiene número, tipo (individual, doble, suite), precio por noche, estado (disponible/ocupada)
- **Cliente**: tiene DNI, nombre, apellidos, teléfono, email
- **Reserva**: tiene fecha de entrada, fecha de salida, número de huéspedes, precio total
- **Pago**: tiene fecha, importe, método de pago (tarjeta, efectivo, transferencia)

**Reglas de negocio:**

- Un hotel tiene múltiples habitaciones
- Un cliente puede hacer muchas reservas
- Una reserva está asociada a una habitación específica y a un cliente
- Una reserva puede tener uno o más pagos (pago inicial + pago final, por ejemplo)

**Tareas:**

1. Dibuja el diagrama de clases con:
   - Todas las clases mencionadas
   - Atributos con sus tipos (`nombre: String`, `precio: double`, etc.)
   - Visibilidad adecuada (atributos privados `-`, métodos públicos `+`)
   - Al menos 3 métodos por clase (ej: `calcularPrecioTotal()`, `estaDisponible()`, `procesarPago()`)

2. Establece las relaciones con **multiplicidad**:
   - Hotel - Habitación
   - Cliente - Reserva
   - Reserva - Habitación
   - Reserva - Pago

3. Usa el tipo de relación correcto:
   - ¿Alguna es composición (◆)?
   - ¿Alguna es agregación (◇)?
   - ¿Alguna es asociación simple (─)?

---

## Actividad 4: Diagrama de Clases - Red Social Básica

**Objetivo:** Practicar relaciones complejas y herencia.

**Enunciado:**

Diseña el modelo de clases para una red social básica:

**Usuarios:**

- **Usuario** (clase base): id, nombre de usuario, email, fecha de registro
  - **UsuarioParticular**: foto de perfil, biografía
  - **UsuarioProfesional**: empresa, cargo, sector

**Contenido:**

- **Publicación** (clase abstracta): id, fecha, contenido de texto, número de likes
  - **Foto**: URL de la imagen, resolución
  - **Video**: URL del video, duración
  - **Artículo**: título, cuerpo del texto

**Interacciones:**

- **Comentario**: texto, fecha
- **Mensaje**: texto, fecha, leído (boolean)

**Reglas:**

- Un usuario puede crear muchas publicaciones
- Una publicación pertenece a un usuario
- Una publicación puede tener muchos comentarios
- Un comentario es escrito por un usuario
- Un usuario puede enviar mensajes a otro usuario
- Un usuario puede dar "like" a publicaciones (relación muchos a muchos)

**Tareas:**

1. Dibuja el diagrama de clases usando **herencia** para Usuario y Publicación.
2. Marca la clase `Publicación` como **abstracta**.
3. Modela la relación **muchos a muchos** entre Usuario y Publicación para los "likes".
4. Incluye **al menos 2 métodos** en cada clase.
5. Usa **multiplicidades** correctamente en todas las relaciones.

**Preguntas de reflexión:**

- ¿Por qué `Publicación` debería ser abstracta?
- ¿Cómo modelarías la relación de "amistad" entre usuarios (un usuario tiene muchos amigos, que son otros usuarios)?

---

## Actividad 5: Relaciones entre Clases - Tienda Online

**Objetivo:** Practicar todos los tipos de relaciones.

**Enunciado:**

Identifica y justifica el tipo de relación (composición, agregación, asociación simple, ...) entre las siguientes parejas de clases en una tienda online:

1. **Pedido - LineaPedido**
   - Un pedido contiene líneas de pedido
   - Si se elimina el pedido, las líneas también desaparecen
   - **¿Qué relación es? ¿Por qué?**

2. **LineaPedido - Producto**
   - Una línea de pedido hace referencia a un producto
   - El producto puede existir sin la línea de pedido
   - **¿Qué relación es? ¿Por qué?**

3. **Carro de Compra - Producto**
   - Un carro temporal tiene productos
   - Los productos siguen existiendo si se vacía el carro
   - **¿Qué relación es? ¿Por qué?**

4. **Pago - Pedido**
   - Un pedido puede tener uno o más pagos
   - **¿Qué relación es? ¿Por qué?**

5. **Producto - ProductoDigital / ProductoFísico**
   - Hay dos tipos de productos
   - **¿Qué relación es? ¿Por qué?**

6. **ServicioEnvío - Pedido**
   - El servicio de envío calcula el coste de un pedido
   - **¿Qué relación es? ¿Por qué?**

**Tarea:**

Dibuja el diagrama de clases completo con:

- Todas las clases mencionadas
- Relaciones correctamente tipificadas (asociación, agregación, composición, herencia, dependencia)
- Multiplicidades
- Atributos y métodos básicos

---

## Recursos de Apoyo

- **Herramientas recomendadas:**
  - PlantUML: [plantuml.com](https://www.plantuml.com)
  - Draw.io: [app.diagrams.net](https://app.diagrams.net)
  - Visual Paradigm Community Edition

- **Documentación:**
  - Apuntes de la UP08
  - Especificación UML 2.5: [omg.org](https://www.omg.org/spec/UML/)

- **Tutoriales PlantUML:**
  - [PlantUML Class Diagram](https://plantuml.com/class-diagram)
  - [PlantUML Use Case Diagram](https://plantuml.com/use-case-diagram)
