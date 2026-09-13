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
| `TowerDefenseApp` | Clase principal que inicia la aplicación por consola. |

## Documentación por integrante

### Shirley — Lógica del juego y lista circular

### Andrés — Torres y lista secuencial

### Josué — Enemigos y lista doblemente enlazada

### Cris — Oleadas y nodo de oleada

### Héctor — Panel visual de la ruta

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
