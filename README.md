# Prueba1-estructura
### Héctor — Representación Gráfica y Panel de Ruta

Este módulo implementa la interfaz gráfica principal del juego utilizando componentes de Java Swing, encargándose del renderizado visual de la ruta, las torres, los enemigos y la base.

### 1. Clase `PanelRuta`

La clase `PanelRuta` hereda de `JPanel` y funciona como el lienzo gráfico, gestionando la representación en pantalla de todos los elementos visuales sobre la ruta de combate.

#### Atributos Principales

* `torres`: Instancia de `ListaSecuencialTorres` que contiene las referencias a las defensas activas en el mapa.
* `enemigos`: Instancia de `ListaDobleEnemigos` que mantiene el registro de las unidades hostiles en movimiento a lo largo de la ruta.
* `vidasJugador`: Valor numérico entero que representa la salud actual o los puntos de vida restantes de la base.

#### Métodos y Lógica Implementada

* **Constructor (`PanelRuta()`)**: Inicializa el panel estableciendo un color de fondo específico y configurando sus dimensiones preferidas en 620x300 píxeles.
* **`actualizarDatos(...)`**: Recibe y actualiza las referencias de las estructuras de datos (torres y enemigos) y las vidas del jugador, invocando posteriormente el método `repaint()` para refrescar la interfaz con la información más reciente.
* **`paintComponent(Graphics g)`**: Método sobrescrito responsable de dibujar el entorno estático; renderiza el texto de las vidas, la geometría de la ruta principal, las marcas numéricas de posición, la zona de inicio ("INI") y la zona final ("BASE"). Finalmente, delega la responsabilidad visual a los métodos de dibujo dinámico.
* **`dibujarTorres(...)`**: Tras validar que la lista no sea nula, recorre secuencialmente las torres activas, calcula su posición en la ruta de forma proporcional y las dibuja como rectángulos con su identificador correspondiente (ej. "T1").
* **`dibujarEnemigos(...)`**: Verifica la existencia de la lista y recorre los nodos de la estructura doblemente enlazada, calculando la coordenada correspondiente al avance de cada enemigo para dibujarlo como un óvalo acompañado de su identificador (ej. "E1").

### 2. Análisis de Complejidad Algorítmica

* **Actualización de estado (`actualizarDatos`)**: O(1), debido a que la operación se limita a realizar asignaciones de variables por referencia y llamar a la actualización de la cola de eventos visuales.
* **Renderizado de la interfaz (`paintComponent`)**: O(T + E), donde T representa la cantidad de torres activas y E la cantidad de enemigos en pantalla. La complejidad es lineal combinada, ya que la función debe invocar iteraciones completas sobre ambas listas (`dibujarTorres` y `dibujarEnemigos`) para proyectar cada objeto gráfico en cada fotograma o ciclo de repintado.

**Archivos desarrollados:** `PanelRuta.java`.

**Estructura utilizada:** `JPanel` de la biblioteca estándar de Java para renderizado de gráficos 2D.
