# Prueba1-estructura
### Andrés — Torres y lista secuencial

Se desarrolló la clase `Torre`, la cual representa las unidades defensivas instaladas en la ruta del juego. Cada torre encapsula atributos inmutables como su identificador único (`id`), nombre, tipo, posición en la ruta, daño, rango de cobertura y costo de despliegue, incorporando además la lógica exacta para determinar si un enemigo se encuentra dentro de su radio de ataque (`estaEnRango`) mediante el cálculo de valor absoluto.

Asimismo, se implementó la clase `ListaSecuencialTorres`, que administra un conjunto de torres utilizando un arreglo estático manual de tamaño fijo controlado por un contador de elementos (`cantidad`), omitiendo por completo el uso de colecciones preconfiguradas de Java. Esta estructura gestiona la inserción con validación de capacidad límite y control de duplicados por ID, la búsqueda secuencial lineal, y la eliminación física de elementos con compactación de índices mediante desplazamiento de memoria.

**Archivos desarrollados:** `Torre.java`, `ListaSecuencialTorres.java`.

**Estructura utilizada:** Arreglo estático de tamaño fijo con contador explícito de elementos.

**Operaciones implementadas:** 
* `insertar(Torre torre)`: Valida nulos, límite del arreglo y unicidad de ID antes de agregar al final.
* `buscarPorId(int id)`: Recorre secuencialmente la estructura para localizar y retornar la torre coincidente.
* `eliminarPorId(int id)`: Localiza el elemento, desplaza los índices posteriores hacia la izquierda para compactar y libera la última referencia.
* `contarActivas()`: Retorna el total actual de elementos válidos.
* `mostrar()` / `resumen()`: Imprimen o consolidan en un `StringBuilder` la información de las torres activas.
* `obtenerEn(int indice)`: Recupera una torre validando los límites lógicos del arreglo.

