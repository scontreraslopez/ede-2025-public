# Diagramas PlantUML - UP09

Esta carpeta contiene los códigos fuente PlantUML de todos los diagramas de la unidad UP09 (UML - Comportamiento e Interacción).

## Estructura de archivos

### Diagramas de Secuencia (seq-)

**Ejemplos completos:**
- **seq-01-autenticacion.puml**: Proceso de autenticación de usuario con login, validación y gestión de errores
- **seq-02-carrito.puml**: Flujo de añadir producto al carrito con verificación de stock
- **seq-03-basico.puml**: Ejemplo básico de interacción cliente-carrito-stock

**Elementos didácticos (seq-elemento-):**
- **seq-elemento-01-activacion.puml**: Concepto de activación (focus of control) con rectángulos sobre líneas de vida
- **seq-elemento-02-self-call.puml**: Mensajes a uno mismo (self-call) en validación
- **seq-elemento-03-creacion-destruccion.puml**: Creación (create) y destrucción (destroy) de objetos
- **seq-elemento-04-tipos-mensajes.puml**: Tipos de mensajes (síncrono, asíncrono, retorno, creación)
- **seq-elemento-05-alt.puml**: Fragmento ALT (if-else) - ejemplo con cuenta bancaria
- **seq-elemento-06-opt.puml**: Fragmento OPT (if sin else) - descuento para clientes premium
- **seq-elemento-07-loop.puml**: Fragmento LOOP (bucle) - cálculo de impuestos para items
- **seq-elemento-08-ref.puml**: Fragmento REF (referencia) - llamadas a otros diagramas
- **seq-elemento-09-combinado.puml**: Caso completo combinando varios fragmentos

### Diagramas de Comunicación (com-)
- **com-01-carrito.puml**: Mismo escenario que seq-03 pero en formato de comunicación (raramente usado)

### Diagramas de Actividades (act-)
- **act-01-compra-online.puml**: Proceso completo de compra online con decisiones y bucles
- **act-02-paralelo.puml**: Preparación de pedido con tareas en paralelo (fork/join)
- **act-03-swimlanes.puml**: Proceso de pedido con swimlanes (Cliente, Sistema, Almacén)

### Diagramas de Estados (state-)
- **state-01-pedido.puml**: Ciclo de vida de un pedido (Pendiente → Confirmado → Pagado → Enviado → Entregado)
- **state-02-conexion.puml**: Estados de una conexión de red (Desconectado, Conectando, Conectado, Cerrando)
- **state-03-reproductor.puml**: Estados de un reproductor de música (Parado, Reproduciendo, Pausado)

### Diagramas de Casos de Uso (uc-)
- **uc-01-tienda.puml**: Sistema de tienda con Cliente y Administrador

### Diagramas C4 (c4-)
- **c4-01-context.puml**: Nivel 1 - Diagrama de contexto (sistema e-commerce con usuarios y sistemas externos)
- **c4-02-containers.puml**: Nivel 2 - Diagrama de contenedores (Web App, API, BD, Cache)
- **c4-03-components.puml**: Nivel 3 - Diagrama de componentes (estructura interna de la API REST)
- **c4-04-code.puml**: Nivel 4 - Diagrama de código (clases del servicio de pedidos)

## Cómo generar las imágenes

### Opción 1: Plugin de VS Code
1. Instalar la extensión "PlantUML" de jebbs
2. Abrir cualquier archivo .puml
3. Presionar `Alt+D` para previsualizar
4. Exportar como PNG/SVG con `Ctrl+Shift+E`

### Opción 2: Línea de comandos
```bash
# Instalar PlantUML (requiere Java)
# Generar imagen PNG
java -jar plantuml.jar archivo.puml

# Generar todas las imágenes
java -jar plantuml.jar *.puml
```

### Opción 3: Online
Visitar https://www.plantuml.com/plantuml y pegar el código

## Convenciones de nomenclatura

Los archivos siguen el patrón: `{tipo}-{numero}-{descripcion}.puml`

- **tipo**: seq (secuencia), com (comunicación), act (actividades), state (estados), uc (casos de uso), c4
- **numero**: 01, 02, 03... (orden de aparición en el documento)
- **descripcion**: breve descripción del diagrama

## Notas

- Todos los códigos están también incluidos en el documento UP09.md
- Los diagramas se generan automáticamente al exportar a PDF/Word si están referenciados en el markdown
- Para mantener sincronizado, editar siempre el archivo .puml y luego actualizar el markdown
