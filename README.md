# Prueba1-estructura
## Documentación del proyecto

### Shirley
Juego Tower Defense y Lista Circular de Oleadas.

### Andrés
Torres y Lista Secuencial de Torres.

### 1. Clase `Torre`

La clase `Torre` modela una unidad defensiva instalada en la ruta de combate, encapsulando sus atributos estructurales y comportamientos de alcance táctico.

#### Atributos Principales

* `id`: Identificador numérico único de la torre.
* `nombre`: Nombre descriptivo de la defensa.
* `tipo`: Categoría táctica de la torre.
* `posicion`: Coordenada numérica de ubicación a lo largo de la ruta del mapa.
* `danio`: Magnitud del impacto ofensivo infligido a las unidades enemigas.
* `rango`: Radio máximo de cobertura para interactuar con los enemigos.
* `coste`: Valor de recursos necesario para desplegar la torre.

#### Métodos Principales

* **Constructor (`Torre(...)`)**: Inicializa de forma inmutable todos los atributos de la entidad con los parámetros provistos.
* **Getters (`getId`, `getPosicion`, `getDanio`, `getRango`)**: Métodos de acceso rápido para consultar las propiedades específicas de la torre.
* **`estaEnRango(Enemigo enemigo)`**: Calcula si un enemigo se encuentra dentro del radio de cobertura evaluando si la diferencia absoluta entre las posiciones es menor o igual al rango de la torre (`Math.abs(posicion - enemigo.getPosicion()) <= rango`).
* **`toString()`**: Retorna una representación textual formateada con los detalles clave del objeto torre.

### 2. Clase `ListaSecuencialTorres`

La clase `ListaSecuencialTorres` implementa una estructura de datos lineal basada en un arreglo estático de tamaño fijo con un contador explícito, omitiendo el uso de colecciones preconfiguradas de Java.

#### Atributos Principales

* `torres`: Arreglo estático de tipo `Torre` que almacena los elementos de forma contigua en memoria.
* `cantidad`: Entero que lleva el registro del número actual de elementos almacenados en la estructura.

#### Métodos Principales

* **Constructor (`ListaSecuencialTorres(int capacidad)`)**: Instancia el arreglo estático con la capacidad máxima definida e inicializa el contador de elementos en cero.
* **`insertar(Torre torre)`**: Valida que el objeto no sea nulo, que el arreglo no haya alcanzado su capacidad máxima y que no exista una torre registrada con el mismo identificador. Si pasa las validaciones, añade la torre al final y actualiza el contador.
* **`buscarPorId(int id)`**: Realiza una búsqueda secuencial recorriendo el arreglo hasta encontrar la torre coincidente con el ID proporcionado, retornando `null` si no existe.
* **`eliminarPorId(int id)`**: Localiza el índice de la torre por su identificador, desplaza los elementos posteriores una posición hacia la izquierda para compactar el arreglo, decrementa el contador y limpia la referencia final asignando `null`.
* **`contarActivas()`**: Retorna el número actual de torres válidas almacenadas en la estructura.
* **`mostrar()`**: Imprime por la salida estándar cada una de las torres activas o un aviso indicando que no hay registros si la lista está vacía.
* **`resumen()`**: Construye y retorna un objeto `StringBuilder` convertido a `String` con la información consolidada de todas las torres.
* **`obtenerEn(int indice)`**: Retorna la torre ubicada en un índice específico tras validar que se encuentre dentro de los límites lógicos de la lista (`0` hasta `cantidad - 1`).

### Josué
Enemigos y Lista Doblemente Enlazada.

### Cris
Oleadas y Nodo de Oleadas.

### Héctor
Panel visual de la ruta.

### Lenin
Interfaz gráfica y ejecución del programa.
