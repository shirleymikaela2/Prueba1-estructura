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
**Archivos desarrollados:** `Enemigo.java`, `NodoEnemigo.java` y `ListaDobleEnemigos.java`.

Se implementó la clase `Enemigo`, que representa a cada enemigo activo del juego. Sus atributos principales son identificador, tipo, vida, velocidad, posición y recompensa.

También se implementó una lista doblemente enlazada mediante las clases `NodoEnemigo` y `ListaDobleEnemigos`. Esta estructura conserva las referencias `primero` y `ultimo`, por lo que permite recorrer los enemigos tanto hacia adelante como hacia atrás.

La lista administra el movimiento de los enemigos en cada turno, elimina a los que llegan a la base, aplica los ataques recibidos desde las torres y elimina a los enemigos derrotados.

**Operaciones implementadas:** insertar enemigo al final, buscar por identificador, mover enemigos, eliminar escapados, atacar con torres, eliminar enemigos destruidos, mostrar hacia adelante y mostrar hacia atrás.

### Cris — Oleadas y nodo de oleada
Se desarrolló la clase `Oleada`, la cual representa la configuración de cada grupo de enemigos del juego. Cada oleada posee identificador, cantidad de enemigos, tipo de enemigo, vida base y velocidad base.

También se implementó `NodoOleada`, el cual almacena los datos de una oleada y una referencia al siguiente nodo. Esto permite enlazar las oleadas dentro de una lista circular, donde el último nodo apunta al primero. No se utilizaron colecciones prediseñadas de Java.

**Archivos desarrollados:** `Oleada.java`, `NodoOleada.java`.

**Estructura utilizada:** nodo con un objeto de tipo `Oleada` y una referencia al siguiente nodo, utilizado en una lista simplemente enlazada circular.

### Héctor — Panel visual de la ruta
Se desarrolló la clase `PanelRuta`, encargada de representar gráficamente la ruta por la que avanzan los enemigos. La ruta comprende las posiciones desde 0 hasta 20 y termina en la base que debe ser protegida.

El panel muestra las torres registradas sobre la ruta, los enemigos activos según su posición y las vidas restantes del jugador. La información visual se actualiza cada vez que el usuario avanza un turno.

**Archivo desarrollado:** `PanelRuta.java`.

**Tecnología utilizada:** Java Swing, utilizando `JPanel` y `Graphics`.

**Operaciones implementadas:** 
**Operaciones implementadas (Métodos):**
* `actualizarDatos(...)`: Sincroniza el panel con el estado actual de las listas (torres y enemigos) y refresca la pantalla (`repaint`).
* `paintComponent(...)`: Renderiza el entorno estático (fondo, ruta, marcadores numéricos de 0 a 20, zona INI y BASE).
* `dibujarTorres(...)`: Recorre la estructura secuencial para calcular y pintar cada torre en su posición.
* `dibujarEnemigos(...)`: Itera sobre la lista doblemente enlazada para graficar el avance de cada enemigo en la ruta.

### Lenin — Interfaz gráfica

**Archivos desarrollados:** `VentanaTowerDefense.java` y `TowerDefenseApp.java`.

Se implementó la interfaz gráfica del juego utilizando Java Swing. La clase `VentanaTowerDefense` contiene los campos de texto, botones y mensajes que permiten al usuario interactuar con el sistema sin utilizar un menú por consola.

Desde la ventana se pueden registrar torres indicando su identificador, nombre, tipo, posición, daño, rango y costo. También se pueden registrar oleadas con su identificador, cantidad de enemigos, tipo, vida base y velocidad.

La interfaz permite mostrar las torres registradas, eliminar una torre por su identificador, mostrar oleadas, iniciar una oleada, avanzar turnos, mostrar enemigos hacia adelante y hacia atrás, cargar un ejemplo y reiniciar el ciclo de oleadas.

La clase `TowerDefenseApp` es el punto de inicio de la aplicación. Su método `main` ejecuta `VentanaTowerDefense`, mostrando la interfaz gráfica al usuario.

**Tecnología utilizada:** Java Swing.

**Operaciones implementadas:** registrar torre, eliminar torre, registrar oleada, iniciar oleada, avanzar turno, mostrar torres, mostrar oleadas, mostrar enemigos, cargar ejemplo, reiniciar ciclo y actualizar el estado visual del juego.

## Funcionamiento del juego

1. El usuario registra torres indicando su identificador, posición, alcance y daño.
2. El usuario registra oleadas indicando su identificador, cantidad de enemigos, velocidad base y vida base.
3. Se inicia una oleada.
4. Al avanzar un turno, los enemigos se desplazan por la ruta.
5. Las torres atacan a los enemigos que se encuentran dentro de su alcance.
6. Los enemigos derrotados son eliminados de la lista de enemigos activos.
7. Si un enemigo llega a la posición 20, el jugador pierde una vida.
8. La partida termina cuando las vidas llegan a cero o cuando todas las oleadas son completadas.
