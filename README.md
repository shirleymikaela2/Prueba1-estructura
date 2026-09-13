# Prueba1-estructura
### Andrés — Gestión de Torres y Lista Secuencial

Este módulo implementa la entidad defensiva y la estructura de datos lineal secuencial encargada de administrar las torres dentro del juego, desarrollada de manera personalizada con un arreglo estático y sin el uso de colecciones nativas de Java.

### 1. Clase `Torre`

La clase `Torre` modela una unidad defensiva instalada en la ruta de combate, encapsulando sus atributos estructurales y el comportamiento táctico de alcance.

#### Atributos Principales

* `id`: Identificador numérico único de la torre.
* `nombre`: Nombre descriptivo de la defensa.
* `tipo`: Categoría táctica de la torre.
* `posicion`: Coordenada numérica de ubicación a lo largo de la ruta del mapa.
* `danio`: Magnitud del impacto ofensivo infligido a las unidades enemigas.
* `rango`: Radio máximo de cobertura para interactuar con los enemigos.
* `coste`: Valor de recursos necesario para desplegar la torre.

#### Métodos y Lógica Implementada

* **Constructor (`Torre(...)`)**: Inicializa de forma inmutable todos los atributos de la entidad con los parámetros provistos.
* **Getters (`getId`, `getPosicion`, `getDanio`, `getRango`)**: Métodos de acceso para consultar las propiedades específicas de la torre.
* **`estaEnRango(Enemigo enemigo)`**: Calcula si un enemigo se encuentra dentro del radio de cobertura evaluando si la diferencia absoluta entre la posición de la torre y la del enemigo es menor o igual al rango (`Math.abs(posicion - enemigo.getPosicion()) <= rango`).
* **`toString()`**: Retorna una representación textual formateada con los detalles clave del objeto (ID, nombre, tipo, posición, daño, rango y coste).

### 2. Clase `ListaSecuencialTorres`

La clase `ListaSecuencialTorres` implementa una estructura de datos lineal basada en un arreglo estático de tamaño fijo con un contador explícito de elementos actuales (`cantidad`), omitiendo el uso de colecciones preconfiguradas como `ArrayList`.

#### Atributos Principales

* `torres`: Arreglo estático de tipo `Torre` que almacena los elementos de forma contigua en memoria.
* `cantidad`: Entero que lleva el registro dinámico del número actual de torres almacenadas en la estructura.

#### Operaciones y Métodos Implementados

* **Constructor (`ListaSecuencialTorres(int capacidad)`)**: Instancia el arreglo estático con la capacidad máxima definida por parámetro e inicializa el contador en cero.
* **`insertar(Torre torre)`**: Valida que el objeto no sea nulo, que el arreglo no haya alcanzado su capacidad máxima (`cantidad == torres.length`) y que no exista una torre registrada con el mismo identificador llamando a `buscarPorId`. Si las validaciones son exitosas, almacena la torre al final del arreglo e incrementa el contador.
* **`buscarPorId(int id)`**: Realiza una búsqueda secuencial recorriendo el arreglo desde el índice `0` hasta `cantidad - 1` para encontrar y retornar la torre coincidente, o `null` si no existe.
* **`eliminarPorId(int id)`**: Localiza el índice de la torre por su identificador mediante un recorrido lineal. Al encontrarla, ejecuta un bucle de desplazamiento de los elementos posteriores una posición hacia la izquierda para compactar el arreglo, decrementa el contador y limpia la referencia final asignando `null` para evitar fugas de memoria.
* **`contarActivas()`**: Retorna el número actual de torres válidas almacenadas en la estructura.
* **`mostrar()`**: Imprime por la salida estándar cada una de las torres activas o un mensaje de advertencia si la lista se encuentra vacía.
* **`resumen()`**: Construye y retorna un objeto `StringBuilder` convertido a `String` con la información consolidada de todas las torres para su visualización en interfaces gráficas o reportes.
* **`obtenerEn(int indice)`**: Retorna la torre ubicada en un índice específico tras validar que se encuentre dentro de los límites lógicos de la lista (`0` hasta `cantidad - 1`).

### 3. Análisis de Complejidad Algorítmica

* **Inserción (`insertar`)**: O(n) en el peor de los casos debido a la verificación de duplicados mediante `buscarPorId`, aunque la adición al final del arreglo es O(1).
* **Búsqueda (`buscarPorId`)**: O(n) en el peor de los casos al requerir una revisión lineal secuencial de los elementos activos.
* **Eliminación (`eliminarPorId`)**: O(n) debido a la necesidad de desplazar los elementos adyacentes hacia la izquierda para mantener la contigüidad del arreglo estático.

**Archivos desarrollados:** `Torre.java`, `ListaSecuencialTorres.java`.

**Estructura utilizada:** Arreglo estático de tamaño fijo con contador explícito de elementos.

