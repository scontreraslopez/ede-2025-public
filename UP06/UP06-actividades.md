# Actividad: Planificación del Desarrollo de una Aplicación Móvil para Compras

## Contexto
Una empresa te ha encomendado el desarrollo de una aplicación móvil que  
permitirá a sus clientes consultar y realizar compras de sus productos. Los objetivos  
principales de esta aplicación incluyen la consulta de productos por categorías, la  
recepción de notificaciones de novedades, la visualización de ofertas personalizadas y  
la gestión de pedidos. Tu tarea es diseñar la planificación del proyecto de desarrollo de  
software, considerando todos los aspectos estudiados.

## Instrucciones
1. **Modelo de Ciclo de Vida:** Determina el modelo de ciclo de vida que consideras  
   más apropiado para el desarrollo de esta aplicación móvil. Justifica tu elección  
   explicando por qué consideras que este modelo es el más adecuado para  
   cumplir con los objetivos del proyecto.  
2. **Planificación de Etapas:** Desarrolla un plan que detalle las distintas etapas del  
   proyecto de desarrollo de software. Asegúrate de incluir las siguientes etapas  
   clave, aunque puedes agregar otras según lo consideres necesario.

---

## Solución

### Ciclo de Vida
El modelo de ciclo de vida idóneo es un modelo en cascada con retroalimentación ya  
que nos encontramos ante un modelo de software clásico, pero donde es necesario  
introducir realimentación entre etapas, de forma que podamos volver atrás en  
cualquier momento y corregir, modificar o depurar aspectos necesarios. Dado que  
no se prevén muchos cambios durante el desarrollo es el modelo más idóneo. Se  
trata de un proyecto con pocos cambios, poco evolutivo y los requisitos están claros.  
El inconveniente de este modelo es que puede ser muy rígido para este proyecto.  
No obstante, también podría ser válido un modelo iterativo incremental, que está  
basado en el modelo en cascada con retroalimentación, ya que se trata de una  
tecnología novedosa donde se van ha producir muchos cambios y es posible que  
haya que introducir mejoras en diferentes versiones para adaptarnos a los avances  
tecnológicos. En este caso sería interesante desarrollar varias fases que se repitan  
y refinan donde se vayan propagando las mejoras de una fase a las siguientes. Con  
este modelo también evitamos la rigidez que puede introducir el modelo en cascada  
con retroalimientación.

---

### Análisis de Requisitos del Sistema

#### Requisitos funcionales
- Consultar los productos por categoría.  
- Posibilitar la recepción de notificaciones en el móvil con las principales novedades.  
- Visualizar las ofertas en función de las preferencias y compras previas de los clientes.  
- Realizar pedidos.  
- Consultar el estado de un pedido realizado.  

#### Requisitos no funcionales
- Tratamiento simultáneo de peticiones.  
- Tiempo de respuesta rápido del programa.  
- Seguridad.  

---

### Diseño
En esta etapa vamos a dividir el sistema en partes y a determinar la función de cada  
una de ellas. Debemos de determina que hará exactamente cada parte, para ello  
debemos crear un modelo funcional-estructural de los requerimientos del sistema  
global y poder así dividirlo y afrontar las distintas pares por separado.  

De este modo, podríamos distinguir las siguientes partes:
- **Gestión de productos:** alta, baja, modificación, consulta.  
- **Gestión de clientes:** alta, baja, modificación, consulta.  
- **Gestión de pedidos:** alta, baja, modificación, consulta.  
- **Gestión de novedades:** alta, baja, modificación, consulta, configuración.  

---

### Codificación
- Lenguaje de programación multiplataforma para portabilidad en distintos sistemas operativos móviles.  
- Base de datos: MySQL.  
- Entorno de desarrollo: NetBeans.  

---

### Pruebas
- **Pruebas unitarias:** probar cada parte de forma independiente (JUnit).  
- **Pruebas de integración:** comprobar funcionamiento del sistema completo.  
- **Beta Test:** pruebas en entorno de producción (teléfono móvil).  

---

### Documentación
- **Guía técnica:** diseño, codificación y pruebas (para analistas y programadores).  
- **Guía de uso:** funcionalidad, ejemplos, requisitos y resolución de problemas (para clientes).  
- **Guía de instalación:** información para implantación segura y confiable (para personal técnico y clientes).  

---

### Explotación
- Instalación y puesta en marcha en dispositivos móviles.  
- Configuración de parámetros de funcionamiento.  
- Pruebas Beta en equipos del cliente bajo condiciones reales.  

---

### Mantenimiento
- Actualización y evolución del software según mejoras de hardware y nuevas necesidades.  
- Corrección de errores y publicación de nuevas versiones.  
- Servicio de mantenimiento pactado con el cliente (coste temporal y económico).  
- Control, mejora y optimización continua del software.  








# Actividad Práctica: Creación de un Plan de Desarrollo de Software

## Objetivo
El objetivo de esta actividad es que el alumnado aplique sus conocimientos sobre las fases en el desarrollo de software para crear un plan detallado de desarrollo de un proyecto ficticio.

## Instrucciones
1. **Definición del Proyecto:** En este caso se debería proporcionar el enunciado ficticio de un producto software.  
   Por ejemplo, un sistema de gestión de biblioteca en línea o una aplicación de seguimiento de tareas.  

2. **División en Fases:** Se deben identificar al menos cuatro fases en el desarrollo del software, como  
   "Requisitos", "Diseño", "Implementación" y "Pruebas". Cada fase debe tener una breve descripción de las actividades involucradas.  

3. **Planificación Detallada:** Para cada fase, se deben crear un plan detallado que incluya lo siguiente:  
   - Objetivos y metas específicas de la fase.  
   - Actividades detalladas que se llevarán a cabo en esa fase.  
   - Recursos necesarios (personal, herramientas, software, etc.).  
   - Duración estimada de la fase.  
   - Dependencias entre las fases (por ejemplo, la fase de "Diseño" debe completarse antes de la fase de "Implementación").  

4. **Cronograma:** Se debe crear un cronograma que muestre la secuencia de las fases y su duración estimada.  
   Pueden utilizar gráficos de Gantt u otras herramientas de planificación.  

---

## Solución Propuesta

### Fase 1: Requisitos
- **Objetivo:** Definir los requisitos funcionales y no funcionales del sistema.  
- **Actividades:** Entrevistar a usuarios, recopilar información, definir casos de uso.  
- **Recursos:**  
  - Personales: Analista de requisitos.  
  - Herramienta usada: encuestas a usuarios.  
- **Duración:** 2 semanas.  
- **Dependencias:** Ninguna.  

### Fase 2: Diseño
- **Objetivo:** Diseñar la arquitectura del sistema y la interfaz de usuario.  
- **Actividades:** Crear diagramas de arquitectura, diseñar la interfaz de usuario.  
- **Recursos:**  
  - Personales: Diseñadores de interfaz, arquitectos de software.  
- **Duración:** 3 semanas.  
- **Dependencias:** Compleción de la fase de Requisitos.  

### Fase 3: Implementación
- **Objetivo:** Desarrollar el código fuente del sistema.  
- **Actividades:** Escribir código, pruebas unitarias.  
- **Recursos:**  
  - Personales: Desarrolladores Software.  
  - Software: entorno de desarrollo.  
- **Duración:** 6 semanas.  
- **Dependencias:** Compleción de la fase de Diseño.  

### Fase 4: Pruebas
- **Objetivo:** Verificar y validar el sistema.  
- **Actividades:** Pruebas de unidad, pruebas de integración, pruebas de aceptación.  
- **Recursos:**  
  - Humanos: Equipo de pruebas.  
  - Herramienta usada: datos de prueba.  
- **Duración:** 4 semanas.  
- **Dependencias:** Compleción de la fase de Implementación.  

---

## Cronograma
- **Semana 1-2:** Requisitos  
- **Semana 3-5:** Diseño  
- **Semana 6-11:** Implementación  
- **Semana 12-15:** Pruebas  







# Actividad: Planificación del Desarrollo de una Aplicación Móvil para Compras

## Contexto
Una empresa te ha encomendado el desarrollo de una aplicación móvil que  
permitirá a sus clientes consultar y realizar compras de sus productos. Los objetivos  
principales de esta aplicación incluyen la consulta de productos por categorías, la  
recepción de notificaciones de novedades, la visualización de ofertas personalizadas y  
la gestión de pedidos. Tu tarea es diseñar la planificación del proyecto de desarrollo de  
software, considerando todos los aspectos estudiados.

## Instrucciones
1. **Modelo de Ciclo de Vida:** Determina el modelo de ciclo de vida que consideras  
   más apropiado para el desarrollo de esta aplicación móvil. Justifica tu elección  
   explicando por qué consideras que este modelo es el más adecuado para  
   cumplir con los objetivos del proyecto.  
2. **Planificación de Etapas:** Desarrolla un plan que detalle las distintas etapas del  
   proyecto de desarrollo de software. Asegúrate de incluir las siguientes etapas  
   clave, aunque puedes agregar otras según lo consideres necesario.

---

## Solución

### Ciclo de Vida
El modelo de ciclo de vida idóneo es un modelo en cascada con retroalimentación ya  
que nos encontramos ante un modelo de software clásico, pero donde es necesario  
introducir realimentación entre etapas, de forma que podamos volver atrás en  
cualquier momento y corregir, modificar o depurar aspectos necesarios. Dado que  
no se prevén muchos cambios durante el desarrollo es el modelo más idóneo. Se  
trata de un proyecto con pocos cambios, poco evolutivo y los requisitos están claros.  
El inconveniente de este modelo es que puede ser muy rígido para este proyecto.  
No obstante, también podría ser válido un modelo iterativo incremental, que está  
basado en el modelo en cascada con retroalimentación, ya que se trata de una  
tecnología novedosa donde se van ha producir muchos cambios y es posible que  
haya que introducir mejoras en diferentes versiones para adaptarnos a los avances  
tecnológicos. En este caso sería interesante desarrollar varias fases que se repitan  
y refinan donde se vayan propagando las mejoras de una fase a las siguientes. Con  
este modelo también evitamos la rigidez que puede introducir el modelo en cascada  
con retroalimientación.

---

### Análisis de Requisitos del Sistema

#### Requisitos funcionales
- Consultar los productos por categoría.  
- Posibilitar la recepción de notificaciones en el móvil con las principales novedades.  
- Visualizar las ofertas en función de las preferencias y compras previas de los clientes.  
- Realizar pedidos.  
- Consultar el estado de un pedido realizado.  

#### Requisitos no funcionales
- Tratamiento simultáneo de peticiones.  
- Tiempo de respuesta rápido del programa.  
- Seguridad.  

---

### Diseño
En esta etapa vamos a dividir el sistema en partes y a determinar la función de cada  
una de ellas. Debemos de determina que hará exactamente cada parte, para ello  
debemos crear un modelo funcional-estructural de los requerimientos del sistema  
global y poder así dividirlo y afrontar las distintas pares por separado.  

De este modo, podríamos distinguir las siguientes partes:
- **Gestión de productos:** alta, baja, modificación, consulta.  
- **Gestión de clientes:** alta, baja, modificación, consulta.  
- **Gestión de pedidos:** alta, baja, modificación, consulta.  
- **Gestión de novedades:** alta, baja, modificación, consulta, configuración.  

---

### Codificación
- Lenguaje de programación multiplataforma para portabilidad en distintos sistemas operativos móviles.  
- Base de datos: MySQL.  
- Entorno de desarrollo: NetBeans.  

---

### Pruebas
- **Pruebas unitarias:** probar cada parte de forma independiente (JUnit).  
- **Pruebas de integración:** comprobar funcionamiento del sistema completo.  
- **Beta Test:** pruebas en entorno de producción (teléfono móvil).  

---

### Documentación
- **Guía técnica:** diseño, codificación y pruebas (para analistas y programadores).  
- **Guía de uso:** funcionalidad, ejemplos, requisitos y resolución de problemas (para clientes).  
- **Guía de instalación:** información para implantación segura y confiable (para personal técnico y clientes).  

---

### Explotación
- Instalación y puesta en marcha en dispositivos móviles.  
- Configuración de parámetros de funcionamiento.  
- Pruebas Beta en equipos del cliente bajo condiciones reales.  

---

### Mantenimiento
- Actualización y evolución del software según mejoras de hardware y nuevas necesidades.  
- Corrección de errores y publicación de nuevas versiones.  
- Servicio de mantenimiento pactado con el cliente (coste temporal y económico).  
- Control, mejora y optimización continua del software.  


