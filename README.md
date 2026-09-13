# Prueba1-estructura
# Tower Defense - Prueba Práctica de Estructura de Datos

## Descripción del proyecto

Este proyecto consiste en el desarrollo de un juego básico de tipo **Tower Defense** en Java. El jugador registra torres para defender una ruta de longitud 20, mientras diferentes oleadas de enemigos avanzan hacia la base.

Durante cada turno, los enemigos se mueven según su velocidad y las torres atacan a los enemigos que se encuentran dentro de su rango. Si un enemigo llega a la posición final de la ruta, el jugador pierde una vida. La partida termina cuando el jugador pierde todas sus vidas o cuando se completan todas las oleadas registradas.

El programa fue desarrollado aplicando estructuras de datos lineales implementadas manualmente, sin utilizar colecciones prediseñadas como `ArrayList`, `LinkedList`, `Queue` o `Deque`.

## Integrantes

- Shirley 
- Andrés 
- Josué
- Cris
- Héctor
- Lenin

## Requisitos cumplidos

- Implementación en lenguaje Java.
- Uso de una lista secuencial manual para almacenar torres.
- Uso de una lista doblemente enlazada para los enemigos activos.
- Uso de una lista simplemente enlazada circular para las oleadas.
- Registro, búsqueda, eliminación y visualización de torres.
- Registro, visualización e inicio de oleadas.
- Movimiento de enemigos por turnos.
- Ataque de torres a enemigos dentro de su rango.
- Eliminación de enemigos derrotados.
- Reducción de vidas cuando un enemigo llega a la base.
- Finalización de la partida al perder todas las vidas o completar las oleadas.
- No se utilizaron estructuras lineales prediseñadas de Java.

## Estructuras de datos utilizadas

| Estructura | Aplicación dentro del juego |
|---|---|
| Lista secuencial manual | Almacena las torres registradas por el jugador. |
| Lista doblemente enlazada | Gestiona los enemigos activos y permite recorrerlos hacia adelante y hacia atrás. |
| Lista simplemente enlazada circular | Administra las oleadas de enemigos de forma continua. |

## Clases del proyecto

| Clase | Responsabilidad |
|---|---|
| `Torre` | Representa una torre con identificador, posición, alcance y daño. |
| `ListaSecuencialTorres` | Administra las torres mediante un arreglo manual de capacidad fija. |
| `Enemigo` | Representa un enemigo con identificador, posición, velocidad y vida. |
| `NodoEnemigo` | Nodo utilizado para enlazar enemigos dentro de la lista doble. |
| `ListaDobleEnemigos` | Administra enemigos activos, derrotados y escapados. |
| `Oleada` | Contiene la configuración de una oleada: identificador, cantidad, velocidad y vida base. |
| `NodoOleada` | Nodo utilizado para enlazar las oleadas. |
| `ListaCircularOleadas` | Administra las oleadas mediante una lista circular. |
| `JuegoTowerDefense` | Controla la lógica principal: turnos, vidas, ataques, movimiento y fin del juego. |
| `PanelRuta` | Representa gráficamente la ruta, las torres, los enemigos y la base. |
| `VentanaTowerDefense` | Contiene los campos, botones y acciones de la interfaz gráfica. |
| `TowerDefenseApp` | Clase principal que inicia la interfaz gráfica.|

## Documentación por integrante

### Shirley — Lógica del juego y lista circular

**Archivos desarrollados:** `JuegoTowerDefense.java` y `ListaCircularOleadas.java`.

Se implementó la lógica principal del juego. La clase `JuegoTowerDefense` controla las vidas del jugador, los turnos, el inicio de oleadas, el movimiento de enemigos, los ataques de las torres y el final de la partida.

También se implementó `ListaCircularOleadas`, una lista simplemente enlazada circular. En esta estructura el último nodo apunta al primero, permitiendo recorrer las oleadas de forma continua.

**Operaciones implementadas:** registrar oleada, obtener oleada actual, avanzar a la siguiente oleada, reiniciar ciclo, iniciar oleada, avanzar turno y mostrar el estado general.

### Andrés — Torres y lista secuencial
Se desarrolló la clase `Torre`, la cual representa las torres defensivas del juego. Cada torre posee identificador, posición en la ruta, alcance de ataque y daño.

También se implementó `ListaSecuencialTorres`, la cual almacena las torres en un arreglo manual de capacidad fija. No se utilizaron colecciones prediseñadas de Java.

**Archivos desarrollados:** `Torre.java`, `ListaSecuencialTorres.java`.

**Estructura utilizada:** arreglo manual de tamaño fijo con contador de elementos.

### Josué — Enemigos y lista doblemente enlazada

### Cris — Oleadas y nodo de oleada

### Héctor — Panel visual de la ruta
Se desarrolló la clase `PanelRuta`, encargada de representar gráficamente la ruta por la que avanzan los enemigos. La ruta comprende las posiciones desde 0 hasta 20 y termina en la base que debe ser protegida.

El panel muestra las torres registradas sobre la ruta, los enemigos activos según su posición y las vidas restantes del jugador. La información visual se actualiza cada vez que el usuario avanza un turno.

**Archivo desarrollado:** `PanelRuta.java`.

**Tecnología utilizada:** Java Swing, utilizando `JPanel` y `Graphics`.

**Operaciones implementadas:** dibujar ruta, mostrar posiciones de 0 a 20, dibujar torres, dibujar enemigos, mostrar base y actualizar vidas.

### Lenin — Interfaz gráfica

## Funcionamiento del juego

1. El usuario registra torres indicando su identificador, posición, alcance y daño.
2. El usuario registra oleadas indicando su identificador, cantidad de enemigos, velocidad base y vida base.
3. Se inicia una oleada.
4. Al avanzar un turno, los enemigos se desplazan por la ruta.
5. Las torres atacan a los enemigos que se encuentran dentro de su alcance.
6. Los enemigos derrotados son eliminados de la lista de enemigos activos.
7. Si un enemigo llega a la posición 20, el jugador pierde una vida.
8. La partida termina cuando las vidas llegan a cero o cuando todas las oleadas son completadas.
